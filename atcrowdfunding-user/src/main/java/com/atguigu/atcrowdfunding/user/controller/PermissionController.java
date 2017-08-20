package com.atguigu.atcrowdfunding.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.service.PermissionService;
import com.atguigu.atcrowdfunding.user.service.RoleService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 主键查询许可信息
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		
		return "permission/edit";
	}
	
	/**
	 * 新增许可数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission permission ) {
		AJAXResult result = new AJAXResult();
		
		try {
			permissionService.insertPermission(permission);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 修改许可数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission permission ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = permissionService.updatePermission(permission);
			result.setSuccess(count == 1);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 删除许可数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = permissionService.deletePermissionById(id);
			result.setSuccess(count == 1);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/asyncLoadAssignData")
	public Object asyncLoadAssignData( Integer roleid ) {
		List<Permission> rootPermissions = new ArrayList<Permission>();
		List<Permission> permissions = permissionService.queryAll();
		
		// 从关系表中获取当前角色已经分配的权限信息
		List<Integer> permissionIds = roleService.queryPermissionIdsByRoleid(roleid);
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permission : permissions ) {
			permissionMap.put(permission.getId(), permission);
			if ( permissionIds.contains(permission.getId()) ) {
				permission.setChecked(true);
			}
		}
		
		for ( Permission permission : permissions ) {
			// 子节点
			Permission child = permission;
			if ( permission.getPid() == 0 ) {
				rootPermissions.add(permission);
			} else {
				// 父节点
				Permission parent = permissionMap.get(child.getPid());
				// 组合父子节点的关系
				parent.getChildren().add(child);
			}
		}
		
		return rootPermissions;
	}
	
	@ResponseBody
	@RequestMapping("/asyncLoadData")
	public Object asyncLoadData() {
		List<Permission> rootPermissions = new ArrayList<Permission>();
		List<Permission> permissions = permissionService.queryAll();
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permission : permissions ) {
			permissionMap.put(permission.getId(), permission);
		}
		
		for ( Permission permission : permissions ) {
			// 子节点
			Permission child = permission;
			if ( permission.getPid() == 0 ) {
				rootPermissions.add(permission);
			} else {
				// 父节点
				Permission parent = permissionMap.get(child.getPid());
				// 组合父子节点的关系
				parent.getChildren().add(child);
			}
		}
		
		return rootPermissions;
	}
	
	/**
	 * 读取数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 封装树形结构数据
			 
			// 查询
			
			// 父节点
			/*
			List<Permission> rootPermissions = permissionService.queryRootPermissions();
			
			for ( Permission root : rootPermissions ) {
				queryChildPermissions(root);
			}
			
			for ( Permission permission : rootPermissions ) {
				permission.setOpen(true);
				Integer id = permission.getId();
				// 子节点（集合）
				List<Permission> childrenPermissions = permissionService.queryChildrenPermissionsByPid(id);
				for ( Permission childPermission : childrenPermissions ) {
					childPermission.setOpen(true);
					List<Permission> innerChildrenPermissions = permissionService.queryChildrenPermissionsByPid(childPermission.getId());
					childPermission.setChildren(innerChildrenPermissions);
				}
				
				// 组合父子节点的关系
				permission.setChildren(childrenPermissions);
			}

			*/
			
			// 使用递归方式读取许可数据，但是效率比较低，所以需要进行改善
//			Permission p = new Permission();
//			p.setId(0);
//			queryChildPermissions(p);
			
			List<Permission> rootPermissions = new ArrayList<Permission>();
			List<Permission> permissions = permissionService.queryAll();
			
			// 使用嵌套循环查询许可信息
			/*
			for ( Permission permission : permissions ) {
				// 子节点
				Permission child = permission;
				
				if ( permission.getPid() == 0 ) {
					rootPermissions.add(permission);
				} else {
					for ( Permission innerPermission : permissions ) {
						// 父节点
						if ( child.getPid() == innerPermission.getId() ) {
							Permission parent = innerPermission;
							
							// 组合父子节点的关系
							parent.getChildren().add(child);
							break;
						}
					}
				}
			}
			*/
			
			// 使用Map集合将ID和许可数据进行绑定
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			for ( Permission permission : permissions ) {
				permissionMap.put(permission.getId(), permission);
			}
			
			for ( Permission permission : permissions ) {
				// 子节点
				Permission child = permission;
				if ( permission.getPid() == 0 ) {
					rootPermissions.add(permission);
				} else {
					// 父节点
					Permission parent = permissionMap.get(child.getPid());
					// 组合父子节点的关系
					parent.getChildren().add(child);
				}
				
				
			}
			
			
/*			List<Permission> permissions = new ArrayList<Permission>();
			
			Permission permission = new Permission();
			permission.setName("123");
			permission.setOpen(true);
			
			Permission p1 = new Permission();
			p1.setName("1");
			
			Permission p2 = new Permission();
			p2.setName("2");
			
			Permission p3 = new Permission();
			p3.setName("3");
			
			permission.getChildren().add(p1);
			permission.getChildren().add(p2);
			permission.getChildren().add(p3);
			
			permissions.add(permission);*/
			result.setDatas(rootPermissions);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	/**
	 * 使用递归的方法查询节点数据
	 * 	1. 方法调用自身 ： 查询父节点的子节点数据
	 *  2. 方法调用时，参数应该规律
	 *  3. 方法一定要有跳出的逻辑
	 */
	private void queryChildPermissions( Permission parent ) {
		parent.setOpen(true);
		System.out.println( "1111111111" );
		// 查询子节点数据集合
		// pid = parent id
		List<Permission> childrenPermissions =
			permissionService.queryChildrenPermissionsByPid(parent.getId());
		
		for ( Permission permission : childrenPermissions ) {
			queryChildPermissions(permission);
		}
		
		// 组合父子节点的关系
		parent.setChildren(childrenPermissions);
	}
}
