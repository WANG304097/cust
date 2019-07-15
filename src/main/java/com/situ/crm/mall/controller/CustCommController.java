package com.situ.crm.mall.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.situ.crm.mall.model.CustCommModel;
import com.situ.crm.mall.model.ProductModel;
import com.situ.crm.mall.service.ICustCommService;
import com.situ.until.FmtEmpty;


@Controller
@RequestMapping("/custComm")
public class CustCommController {

	@Autowired
	private ICustCommService service;

	@RequestMapping("/custCommList")
	public String roleList(Model model) {
  System.out.println("888888888");
		return "custComm/list";
	}

	
	/**
	 * 跳转add/update
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/custCommAddUpd")
	public String userAdd(Model model, CustCommModel UserModel,HttpServletRequest request) {
		

		//权限二级经理
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		
		List<String>  status=new ArrayList<>();
		if ("B".equals(sessionUser.getRoleCode()) || "A".equals(sessionUser.getRoleCode())) {
			return "500";
		}
		
		
		System.out.println("UserModel" + UserModel);
		if (FmtEmpty.isEmpty(UserModel.getId())) {
			System.out.println(6666666);
			return "custComm/addUpd";
		} else {

			System.out.println(999999);
			// 修改回显
			 CustCommModel um = service.selectById(UserModel.getId());
			model.addAttribute("custComm2", um);
			return "custComm/addUpd";
		}

	}
	
	@ResponseBody
	@RequestMapping("/addUpd")
	public Integer addUpd(CustCommModel CustCommModel) {

		System.out.println("执行addUpd" + CustCommModel);
		if (FmtEmpty.isEmpty(CustCommModel.getId())) {

			System.out.println("执行add" + CustCommModel);
			System.out.println(6666666);
			Date d = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
			String time = df.format(d);
			CustCommModel.setTime(time);
			return service.insert(CustCommModel);

		} else {
			System.out.println("执行UPDATE" + CustCommModel);
			System.out.println(999999);
			return service.update(CustCommModel);
		
			
		}


	}


	
	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(CustCommModel CustCommModel, HttpServletRequest request) {
	
			

			//权限二级经理/员工
			UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
			
			List<String>  status=new ArrayList<>();
			if ("B".equals(sessionUser.getRoleCode())|| "A".equals(sessionUser.getRoleCode())) {
				return 2;
			}
			
		return service.deleteModel(CustCommModel);
	}


	@RequestMapping("/selectByCode")
	private CustCommModel selectByCode(CustCommModel CustCommModel) {
		System.out.println("执行selectByCode" + CustCommModel);
		CustCommModel um = service.selectByCode(CustCommModel.getCustCode(), CustCommModel.getUserCode());
		System.out.println("++++++++" + um);
		return um;
	}
	
	@RequestMapping("/selectById")
	private CustCommModel selectById(CustCommModel CustCommModel) {
		System.out.println("执行selectByCode" + CustCommModel);
		CustCommModel um = service.selectById(CustCommModel.getId());
		System.out.println("++++++++" + um);
		return um;
	}


	@RequestMapping("/selectAll")
	private List<CustCommModel> selectAll(CustCommModel CustCommModel) {
		System.out.println("+++" + CustCommModel);
		List<CustCommModel> list = service.selectAll(CustCommModel);

		return list;
	}

	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String, Object> selectModel(CustCommModel CustCommModel,HttpServletRequest request ) {
		
		
			

			//权限二级经理
			UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
			
			List<String>  status=new ArrayList<>();
			if ( "A".equals(sessionUser.getRoleCode()) ) {
				
				CustCommModel.setUserCode(sessionUser.getUserCode());
			}
			
		
		CustCommModel.setPageOn(true);
		System.out.println("执行slectModel:" + CustCommModel);
		CustCommModel.setCustCode("%" + CustCommModel.getCustCode() + "%");
		CustCommModel.setCustName("%" + CustCommModel.getCustName() + "%");
		CustCommModel.setUserCode("%" + CustCommModel.getUserCode() + "%");
		CustCommModel.setUserName("%" + CustCommModel.getUserName() + "%");
		List<CustCommModel> list = service.selectModel(CustCommModel);
		System.out.println("6666" + list);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("code", 0);
		map.put("count", service.selectCount(CustCommModel));

		return map;
	}

}
