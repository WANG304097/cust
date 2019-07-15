package com.situ.crm.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.grant.model.UserModel;
import com.situ.crm.mall.model.CustUserModel;
import com.situ.crm.mall.service.ICustUserService;



@Controller
@RequestMapping("/custUser")
public class CustUserController {
	
	@Autowired
	private ICustUserService service;
	
	
	/**
	 * 跳转list页
	 * @return
	 */
		@RequestMapping("/custUserList")
		public String userList(Model model) {
			
//			List<CustUserModel> list2=selectAll(new CustUserModel());
//			model.addAttribute("list2", list2);
			return "custUser/list";
		}
		
		/**
		 * 回显下拉框
		 * @param model
		 * @return
		 */
			@RequestMapping("/custUserAdd")
			public String userAdd(Model model,HttpServletRequest request) {
				

				//权限二级经理
				UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
				
				List<String>  status=new ArrayList<>();
				if ("B".equals(sessionUser.getRoleCode())|| "A".equals(sessionUser.getRoleCode())) {
					return "500";
				}
			return "custUser/add";
			}
	
	@ResponseBody
	@RequestMapping("/add")
	public Integer add(CustUserModel CustUserModel) {						
		

	System.out.println("执行add"+CustUserModel);
			    
	  return service.insert(CustUserModel);

	} 

	 @ResponseBody
	@RequestMapping("/delete")
	public Integer delete(CustUserModel CustUserModel,HttpServletRequest request ) {
				
				

				//权限二级经理/员工
				UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
				
				List<String>  status=new ArrayList<>();
				if ("B".equals(sessionUser.getRoleCode())|| "A".equals(sessionUser.getRoleCode())) {
					return 2;
				}
		 
	      return  service.deleteModel(CustUserModel);
	}
	 
	 /**
		 * 修改回显
		 */

		@RequestMapping("/upCustUser")
		private String upCustUser(CustUserModel UserModel, Model model,HttpServletRequest request) {
			

			//权限二级经理
			UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
			
			List<String>  status=new ArrayList<>();
			if ("B".equals(sessionUser.getRoleCode()) || "A".equals(sessionUser.getRoleCode())) {
				return "500";
			}
			System.out.println("执行selectByCode" + UserModel.getCustCode());
			CustUserModel um = service.selectByCode(UserModel.getCustCode());
			model.addAttribute("custUser2", um);
		return "custUser/custUserupd";
		}
	 
	 
	  @ResponseBody
	@RequestMapping("/update")
	public Integer update(CustUserModel CustUserModel) {
	 
		
		System.out.println("执行UPDATE"+CustUserModel);
	    return  service.update(CustUserModel);
		
	}

	


    
	@RequestMapping("/selectByCode")
	private CustUserModel selectByCode(CustUserModel CustUserModel) {
		System.out.println("执行selectByCode"+CustUserModel);
		CustUserModel um = service.selectByCode(CustUserModel.getCustCode());
		System.out.println("++++++++"+um);
		return um;
	}




@ResponseBody
	@RequestMapping("/selectAll")
	private List<CustUserModel> selectAll(CustUserModel CustUserModel) {
		System.out.println("+++"+CustUserModel);
		List<CustUserModel> list = service.selectAll(CustUserModel);
		
		return list;
	}

    @ResponseBody
	@RequestMapping("/selectModel")
	private Map<String,Object> selectModel(CustUserModel CustUserModel) {
		CustUserModel.setPageOn(true);
		System.out.println("执行slectModel:"+CustUserModel);
		CustUserModel.setCustCode("%"+CustUserModel.getCustCode()+"%");
		CustUserModel.setCustName("%"+CustUserModel.getCustName()+"%");
		List<CustUserModel> list = service.selectModel(CustUserModel);
		
	    Map<String,Object> map=new HashMap<>();
	  
	    map.put("data", list);
		map.put("code", 0);
	    map.put("count", service.selectCount(CustUserModel));
	   
		
		
		return map;
	}


}
