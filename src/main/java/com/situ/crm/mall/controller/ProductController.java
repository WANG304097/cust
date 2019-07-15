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
import com.situ.crm.mall.service.IProductService;
import com.situ.until.FmtEmpty;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService service;

	/**
	 * 跳转list页
	 * 
	 * @return
	 */
	@RequestMapping("/productList")
	public String userList(Model model) {
		System.out.println("66666666666666666666");
//			List<ProductModel> list2=selectAll(new ProductModel());
//			model.addAttribute("list2", list2);
		return "product/list";
	}

	
	
	/**
	 * 跳转图表页
	 * 
	 * @return
	 */
	@RequestMapping("/getList")
	public String getList(Model model) {
		System.out.println("66666666666666666666");
	List<ProductModel> list = service.selectAll(new ProductModel());

		model.addAttribute("list", list);

		return "tu/tu2";
	}
	/**
	 * 跳转add/update
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/productAddUpd")
	public String userAdd(Model model, ProductModel UserModel,HttpServletRequest request) {
		

		//权限二级经理
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		
		List<String>  status=new ArrayList<>();
		if ("B".equals(sessionUser.getRoleCode()) || "A".equals(sessionUser.getRoleCode())) {
			return "500";
		}
		
		
		
		System.out.println("UserModel" + UserModel);
		if (FmtEmpty.isEmpty(UserModel.getCode())) {
			System.out.println(6666666);
			return "product/addUpd";
		} else {

			System.out.println(999999);
			// 修改回显
			ProductModel um = service.selectByCode(UserModel.getCode());
			model.addAttribute("product2", um);
			return "product/addUpd";
		}

	}
	
	@ResponseBody
	@RequestMapping("/addUpd")
	public Integer addUpd(ProductModel Model) {

		System.out.println("执行addUpd" + Model);
		if (FmtEmpty.isEmpty(Model.getId())) {

			System.out.println("执行add" + Model);
			System.out.println(6666666);
//			Date d = new Date();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time = df.format(d);
//			CustCommModel.setTime(time);
			return service.insert(Model);

		} else {
			System.out.println("执行UPDATE" +Model);
			System.out.println(999999);
			return service.update(Model);
		
			
		}


	}

	@ResponseBody
	@RequestMapping("/add")
	public Integer add(ProductModel ProductModel) {

		System.out.println("执行add" + ProductModel);

		return service.insert(ProductModel);

	}

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(ProductModel ProductModel, HttpServletRequest request) {
		
				
				

				//权限二级经理/员工
				UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
				
				List<String>  status=new ArrayList<>();
				if ("B".equals(sessionUser.getRoleCode())|| "A".equals(sessionUser.getRoleCode())) {
					return 2;
				}
		return service.deleteModel(ProductModel);
	}

	@ResponseBody
	@RequestMapping("/update")
	public Integer update(ProductModel ProductModel) {

		System.out.println("执行UPDATE" + ProductModel);
		return service.update(ProductModel);

	}

	@RequestMapping("/selectByCode")
	private ProductModel selectByCode(ProductModel ProductModel) {
		System.out.println("执行selectByCode" + ProductModel);
		ProductModel um = service.selectByCode(ProductModel.getCode());
		System.out.println("++++++++" + um);
		return um;
	}

	@ResponseBody
	@RequestMapping("/selectAll")
	private List<ProductModel> selectAll(ProductModel ProductModel) {
		System.out.println("+++" + ProductModel);
		List<ProductModel> list = service.selectAll(ProductModel);

		return list;
	}

	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String, Object> selectModel(ProductModel ProductModel) {
		ProductModel.setPageOn(true);
		System.out.println("执行slectModel:" + ProductModel);
		ProductModel.setCode("%" + ProductModel.getCode() + "%");
		ProductModel.setName("%" + ProductModel.getName() + "%");
		List<ProductModel> list = service.selectModel(ProductModel);

		Map<String, Object> map = new HashMap<>();

		map.put("data", list);
		map.put("code", 0);
		map.put("count", service.selectCount(ProductModel));

		return map;
	}

}
