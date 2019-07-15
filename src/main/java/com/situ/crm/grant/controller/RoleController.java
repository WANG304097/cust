package com.situ.crm.grant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.grant.model.RoleModel;

import com.situ.crm.grant.service.IRoleService;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService service;
	
	@RequestMapping("/roleList")
	public String roleList() {

		return "role/list";
	}

	
	@RequestMapping("/roleAdd")
	public String roleAdd(Model model) {
	
		return "role/add";
	}
	@ResponseBody
	@RequestMapping("/add")
	public Integer add(RoleModel RoleModel) {						
		

	System.out.println("执行add"+RoleModel);
			    
	  return service.insert(RoleModel);

	} 

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(RoleModel RoleModel,HttpServletRequest request ) {
	
	      return  service.deleteModel(RoleModel);
	}
	/**
	 * 回显修改
	 * @param RoleModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/upRole")
	private String uprole(RoleModel RoleModel, Model model) {
		System.out.println("执行selectByCode" + RoleModel.getRoleCode());
		RoleModel um = service.selectByCode(RoleModel.getRoleCode());
		model.addAttribute("role2", um);
		return "role/roleupd";
	}
	@ResponseBody
	@RequestMapping("/update")
	public Integer update(RoleModel RoleModel) {
	 
		
		System.out.println("执行UPDATE"+RoleModel);
	    return  service.update(RoleModel);
		
	}

	


	@RequestMapping("/selectByCode")
	private RoleModel selectByCode(RoleModel RoleModel) {
		System.out.println("执行selectByCode"+RoleModel.getRoleCode());
		RoleModel um = service.selectByCode(RoleModel.getRoleCode());
		System.out.println("++++++++"+um);
		return um;
	}




     
	@RequestMapping("/selectAll")
	private List<RoleModel> selectAll(RoleModel RoleModel) {
		System.out.println("+++"+RoleModel);
		List<RoleModel> list = service.selectAll(RoleModel);
		
		return list;
	}

	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String,Object> selectModel(RoleModel RoleModel) {
		RoleModel.setPageOn(true);
		System.out.println("执行slectModel:"+RoleModel);
		RoleModel.setRoleCode("%"+RoleModel.getRoleCode()+"%");
		RoleModel.setRoleName("%"+RoleModel.getRoleName()+"%");
		List<RoleModel> list = service.selectModel(RoleModel);
		
	    Map<String,Object> map=new HashMap<>();
	    map.put("data", list);
		map.put("code", 0);
	   
	    map.put("count", service.selectCount(RoleModel));
	   
		
		
		return map;
	}


}
