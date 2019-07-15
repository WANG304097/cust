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

import com.situ.crm.grant.model.MenuModel;
import com.situ.crm.grant.model.RelModel;
import com.situ.crm.grant.model.RoleModel;

import com.situ.crm.grant.service.IMenuService;
import com.situ.crm.grant.service.IRelService;
import com.situ.crm.grant.service.IRoleService;

@Controller
@RequestMapping("/rel")
public class RelController {

	@Autowired
	private IRelService service;
	@Autowired
	private IRoleService service2;
	@Autowired
	private IMenuService service3;

	@RequestMapping("/relList")
	public String roleList(Model model) {
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<MenuModel> list2 = selectMenu();
		model.addAttribute("list2", list2);

		return "rel/list";
	}

	/**
	 * 查找角色
	 * 
	 * @return
	 */
	public List<RoleModel> selectRole() {

		List<RoleModel> list = service2.selectAll(new RoleModel());

		return list;
	}

	/**
	 * 查找菜单
	 * 
	 * @return
	 */
	public List<MenuModel> selectMenu() {

		List<MenuModel> list = service3.selectAll(new MenuModel());

		return list;
	}

	/**
	 * 回显下拉框
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/relAdd")
	public String relAdd(Model model) {
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<MenuModel> list2 = selectMenu();
		model.addAttribute("list2", list2);
		return "rel/add";
	}

	@ResponseBody
	@RequestMapping("/add")
	public Integer add(RelModel RelModel) {

		System.out.println("执行add" + RelModel);

		return service.insert(RelModel);

	}

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(RelModel RelModel, HttpServletRequest request) {

		return service.deleteModel(RelModel);
	}

	/**
	 * 回显下拉框
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/uprel")
	public String uprel(Model model,RelModel RelModel) {
		RelModel um=selectById(RelModel);
		model.addAttribute("rel2",um);
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<MenuModel> list2 = selectMenu();
		model.addAttribute("list2", list2);
		return "rel/relupd";
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Integer update(RelModel RelModel) {

		System.out.println("执行UPDATE" + RelModel);
		return service.update(RelModel);

	}

	@RequestMapping("/selectByCode")
	private RelModel selectByCode(RelModel RelModel) {
		System.out.println("执行selectByCode" + RelModel);
		RelModel um = service.selectByCode(RelModel.getRoleCode(), RelModel.getMenuCode());
		System.out.println("++++++++" + um);
		return um;
	}
	
	@RequestMapping("/selectById")
	private RelModel selectById(RelModel RelModel) {
		System.out.println("执行selectByCode" + RelModel);
		RelModel um = service.selectById(RelModel.getId());
		System.out.println("++++++++" + um);
		return um;
	}


	@RequestMapping("/selectAll")
	private List<RelModel> selectAll(RelModel RelModel) {
		System.out.println("+++" + RelModel);
		List<RelModel> list = service.selectAll(RelModel);

		return list;
	}

	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String, Object> selectModel(RelModel RelModel) {
		RelModel.setPageOn(true);
		System.out.println("执行slectModel:" + RelModel);
		RelModel.setRoleCode("%" + RelModel.getRoleCode() + "%");
		RelModel.setMenuCode("%" + RelModel.getMenuCode() + "%");
		List<RelModel> list = service.selectModel(RelModel);
		System.out.println("6666" + list);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("code", 0);
		map.put("count", service.selectCount(RelModel));

		return map;
	}

}
