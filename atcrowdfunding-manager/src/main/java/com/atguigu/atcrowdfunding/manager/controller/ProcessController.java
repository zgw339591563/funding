package com.atguigu.atcrowdfunding.manager.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.Page;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}
	
	@RequestMapping("/show")
	public String show( String id ) {
		return "process/show";
	}
	
	@RequestMapping("/loadImg")
	public void loadImg( String id, HttpServletResponse response ) throws Exception {
		
		// 查询流程定义
		ProcessDefinitionQuery query =
			repositoryService.createProcessDefinitionQuery();
		ProcessDefinition pd = 
			query.processDefinitionId(id).singleResult();
		
		// 查询图片
		InputStream in =
			repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
		
		// 通过响应输出流将图片输出到浏览器中
		OutputStream out = response.getOutputStream();
		
		IOUtils.copy(in, out);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( String id ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 流程定义ID ==> 流程定义对象 ==》 部署ID
			// 查询流程定义
			ProcessDefinitionQuery query =
				repositoryService.createProcessDefinitionQuery();
			ProcessDefinition pd = 
				query.processDefinitionId(id).singleResult();
			
			// 删除流程定义(级联)
			repositoryService.deleteDeployment(pd.getDeploymentId(), true);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload( HttpServletRequest req ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 上传流程定义文件
			MultipartHttpServletRequest request =
				(MultipartHttpServletRequest)req;
			
			MultipartFile file = request.getFile("procDefFile");
			
			// 部署流程定义文件
			repositoryService
			    .createDeployment()
			    //.addClasspathResource(file.getOriginalFilename())
			    .addInputStream(file.getOriginalFilename(), file.getInputStream())
			    .deploy();
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( Integer pageno, Integer pagesize ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 查询流程定义数据
			ProcessDefinitionQuery query =
				repositoryService.createProcessDefinitionQuery();
			
			List<ProcessDefinition> pds = query.listPage((pageno-1)*pagesize, pagesize);
			// 使用Map来代替流程定义对象
			List<Map<String, Object>> pdMaps = new ArrayList<Map<String, Object>>();
			
			for ( ProcessDefinition pd : pds ) {
				Map<String, Object> pdMap = new HashMap<String, Object>();
				pdMap.put("id", pd.getId());
				pdMap.put("name", pd.getName());
				pdMap.put("key", pd.getKey());
				pdMap.put("version", pd.getVersion());
				pdMaps.add(pdMap);
			}
			
			int count = (int)query.count();
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<Map<String, Object>> pdPage = new Page<Map<String, Object>>();
			pdPage.setDatas(pdMaps);
			pdPage.setTotalno(totalno);
			pdPage.setTotalsize(count);
			pdPage.setPageno(pageno);
			pdPage.setPagesize(pagesize);
			result.setPageObj(pdPage);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
