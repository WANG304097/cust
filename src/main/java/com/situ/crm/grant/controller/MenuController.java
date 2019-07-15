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
import com.situ.crm.grant.model.RoleModel;
import com.situ.crm.grant.model.UserModel;
import com.situ.crm.grant.service.IMenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private IMenuService service;
	
	
	/**
	 * 跳转list页
	 * @return
	 */
		@RequestMapping("/menuList")
		public String userList(Model model) {
			
//			List<MenuModel> list2=selectAll(new MenuModel());
//			model.addAttribute("list2", list2);
			return "menu/list";
		}
		
		/**
		 * 回显下拉框
		 * @param model
		 * @return
		 */
			@RequestMapping("/menuAdd")
			public String userAdd(Model model) {
				List<MenuModel> list2=selectAll(new MenuModel("","a1"));
				MenuModel  mm=selectByCode(new MenuModel("a1","a0"));
				System.out.println("66666666666666"+mm);
				list2.add(mm);
				model.addAttribute("list2", list2);
				return "menu/add";
			}
	
	@ResponseBody
	@RequestMapping("/add")
	public Integer add(MenuModel MenuModel) {						
		

	System.out.println("执行add"+MenuModel);
			    
	  return service.insert(MenuModel);

	} 

	 @ResponseBody
	@RequestMapping("/delete")
	public Integer delete(MenuModel MenuModel,HttpServletRequest request ) {
	
	      return  service.deleteModel(MenuModel);
	}
	 
	 /**
		 * 修改回显
		 */

		@RequestMapping("/upmenu")
		private String upmenu(MenuModel UserModel, Model model) {
			System.out.println("执行selectByCode" + UserModel.getMenuCode());
			MenuModel um = service.selectByCode(UserModel.getMenuCode());
			model.addAttribute("menu2", um);
			List<MenuModel> list2=selectAll(new MenuModel("","a1"));
			MenuModel  mm=selectByCode(new MenuModel("a1","a0"));
		
			list2.add(mm);
			model.addAttribute("list2", list2);
			return "menu/menuupd";
		}
	 
	 
	  @ResponseBody
	@RequestMapping("/update")
	public Integer update(MenuModel MenuModel) {
	 
		
		System.out.println("执行UPDATE"+MenuModel);
	    return  service.update(MenuModel);
		
	}

	


    
	@RequestMapping("/selectByCode")
	private MenuModel selectByCode(MenuModel MenuModel) {
		System.out.println("执行selectByCode"+MenuModel);
		MenuModel um = service.selectByCode(MenuModel.getMenuCode());
		System.out.println("++++++++"+um);
		return um;
	}




@ResponseBody
	@RequestMapping("/selectAll")
	private List<MenuModel> selectAll(MenuModel MenuModel) {
		System.out.println("+++"+MenuModel);
		List<MenuModel> list = service.selectAll(MenuModel);
		
		return list;
	}

    @ResponseBody
	@RequestMapping("/selectModel")
	private Map<String,Object> selectModel(MenuModel MenuModel) {
		MenuModel.setPageOn(true);
		System.out.println("执行slectModel:"+MenuModel);
		MenuModel.setMenuCode("%"+MenuModel.getMenuCode()+"%");
		MenuModel.setMenuName("%"+MenuModel.getMenuName()+"%");
		List<MenuModel> list = service.selectModel(MenuModel);
		
	    Map<String,Object> map=new HashMap<>();
	  
	    map.put("data", list);
		map.put("code", 0);
	    map.put("count", service.selectCount(MenuModel));
	   
		
		
		return map;
	}


}
