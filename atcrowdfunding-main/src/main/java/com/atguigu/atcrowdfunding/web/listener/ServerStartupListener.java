package com.atguigu.atcrowdfunding.web.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.atcrowdfunding.manager.bean.Advert;
import com.atguigu.atcrowdfunding.manager.bean.ProjectType;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.project.bean.Project;
import com.atguigu.atcrowdfunding.project.service.ProjectService;
import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.StringUtil;

public class ServerStartupListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent evt) {
		// 将当前web应用路径保存到指定的范围中，让所有的用户可以访问
		ServletContext application = evt.getServletContext();
		String path = application.getContextPath();
		
		// 在应用范围中保存用户共享的数据
		application.setAttribute("APP_PATH", path);
		
		// 将需要进行权限控制的路径保存到应用范围中
		
		// 获取Spring环境对象
		WebApplicationContext applicationContext =
			WebApplicationContextUtils.getWebApplicationContext(application);
		
		// 获取服务对象
		PermissionService permissionService =
			applicationContext.getBean(PermissionService.class);
		
		List<Permission> permissions = permissionService.queryAll();
		Set<String> uriSet = new HashSet<String>();
		for ( Permission permission : permissions ) {
			if ( StringUtil.isNotEmpty(permission.getUrl()) ) {
				uriSet.add("/" + permission.getUrl());
			}
		}
		application.setAttribute("uriSet", uriSet);
		
		// 广告
		AdvertService advertService =
			applicationContext.getBean(AdvertService.class);
		
		List<Advert> adverts = advertService.queryAllDeploys();
		Map<Integer, Advert> advertMap = new HashMap<Integer, Advert>();
		for ( Advert advert : adverts ) {
			advertMap.put(advert.getId(), advert);
		}
		application.setAttribute(Const.APP_ADVERT_MAP, advertMap);
		
		// 热点项目（关注度高的项目）
		ProjectService projectService =
			applicationContext.getBean(ProjectService.class);
		ProjectTypeService projectTypeService =
			applicationContext.getBean(ProjectTypeService.class);
		
		List<Project> hotProjects = projectService.queryHotProjects();
		
		application.setAttribute(Const.APP_HOT_PROJECTS, hotProjects);
		
		// 分类项目
		List<Project> projects = projectService.queryAll();
		Map<Integer, Project> projectMap = new HashMap<Integer, Project>();
		for ( Project project : projects ) {
			projectMap.put(project.getId(), project);
		}
		
		List<ProjectType> projectTypes = projectTypeService.queryAll();
		Map<Integer, ProjectType> projectTypeMap = new HashMap<Integer, ProjectType>();
		for ( ProjectType projectType : projectTypes ) {
			projectTypeMap.put(projectType.getId(), projectType);
		}
		
		List<Map<String, Object>> maps =
			projectTypeService.queryProject4Type();
		
		for ( Map<String, Object> map : maps ) {
			int typeid = (Integer)map.get("typeid");
			int projectid = (Integer)map.get("projectid");
			ProjectType projectType = projectTypeMap.get(typeid);
			Project project = projectMap.get(projectid);
			projectType.getProjects().add(project);
		}
		
		application.setAttribute(Const.APP_TYPE_PROJECTS, projectTypes);
		
	}

}
