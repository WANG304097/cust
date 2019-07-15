package com.situ.crm.mall.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.situ.crm.grant.service.IUserService;
import com.situ.crm.mall.model.CustUserModel;
import com.situ.crm.mall.model.OrderModel;
import com.situ.crm.mall.model.ProductModel;
import com.situ.crm.mall.service.ICustUserService;
import com.situ.crm.mall.service.IOrderService;
import com.situ.crm.mall.service.IProductService;
import com.situ.until.FmtEmpty;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService service;

	@Autowired
	private IUserService service2;

	@Autowired
	private ICustUserService service3;

	@Autowired
	private IProductService service4;

	/**
	 * 跳转list
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderList")
	public String roleList(Model model, HttpServletRequest request) {
		

		//权限二级经理/员工
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		
		List<UserModel> list1=null;
		if ("A".equals(sessionUser.getRoleCode())) {
		list1 =selectUser(new UserModel("A",sessionUser.getUserCode()));
		}else {
		 list1 = selectUser(new UserModel("A"));
		}
		List<CustUserModel> list2 = selectCustUser(new CustUserModel());
		List<ProductModel> list3 = selectProduct(new ProductModel());
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);

		return "order/list";
	}

	/**
	 * 跳转图表
	 */
	@RequestMapping("/orderTu")
	public String orderTu(Model model, HttpServletRequest request) {


		return "tu/tu";
	}

	private String dateToWeek(String datetime) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance(); // 获得一个日历
		Date datet = null;
		try {
			datet = f.parse(datetime);
			cal.setTime(datet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 得到销售人员
	 * 
	 * @param model
	 * @return
	 */
	private List<UserModel> selectUser(UserModel model) {
		List<UserModel> list = service2.selectAll(model);
		return list;
	}

	/**
	 * 得到客户人员
	 * 
	 * @param model
	 * @return
	 */
	private List<CustUserModel> selectCustUser(CustUserModel model) {
		List<CustUserModel> list = service3.selectAll(model);
		return list;
	}

	/**
	 * 得到商品
	 * 
	 * @param model
	 * @return
	 */
	private List<ProductModel> selectProduct(ProductModel model) {
		List<ProductModel> list = service4.selectAll(model);
		return list;
	}

	/**
	 * 跳转add/update
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderAddUpd")
	public String userAdd(Model model, OrderModel UserModel,HttpServletRequest request ) {
		System.out.println("UserModel" + UserModel);

		List<UserModel> list1 = selectUser(new UserModel("A"));
		List<CustUserModel> list2 = selectCustUser(new CustUserModel());
		List<ProductModel> list3 = selectProduct(new ProductModel());
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		//权限二级经理/员工
				UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
				
				List<String>  status=new ArrayList<>();
				if ("B".equals(sessionUser.getRoleCode())) {
					
					status.add("预付订金");
					status.add("仓库备货");
					status.add("确认收货");
					status.add("售后回访");
				}else if("A".equals(sessionUser.getRoleCode())) {
				
					status.add("购买意向");
					
				}else {
					status.add("购买意向");
					status.add("预付订金");
					status.add("仓库备货");
					status.add("确认收货");
					status.add("售后回访");
				}
				model.addAttribute("list4", status);	
		
		
		if (FmtEmpty.isEmpty(UserModel.getId())) {
			System.out.println(6666666);
			return "order/addUpd";
		} else {
            
			if("A".equals(sessionUser.getRoleCode())) {
				
				return "500";
				
			}
			System.out.println(999999);
			// 修改回显
			OrderModel um = service.selectById(UserModel.getId());
			model.addAttribute("order2", um);
			return "order/addUpd";
		}

	}

	@ResponseBody
	@RequestMapping("/addUpd")
	public Integer addUpd(OrderModel OrderModel) {

		System.out.println("执行addUpd" + OrderModel);
		if (FmtEmpty.isEmpty(OrderModel.getId())) {

			System.out.println("执行add" + OrderModel);
			System.out.println(6666666);
			Date d = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String time = df.format(d);
			OrderModel.setTime(time);
			//修改商品销售数量
			int sum=Integer.parseInt(OrderModel.getCount());
			String prodCode=OrderModel.getProdCode();
			ProductModel prodmodel = service4.selectByCode(prodCode);
			 
			sum+=Integer.parseInt(prodmodel.getSum());
			
			prodmodel.setSum(String.valueOf(sum));
			int i=service.insert(OrderModel);
			 service4.update(prodmodel);
			 return  i;

		} else {
			System.out.println("执行UPDATE" + OrderModel);
			System.out.println(999999);
			return service.update(OrderModel);

		}

	}

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(OrderModel OrderModel, HttpServletRequest request) {

		//权限二级经理/员工
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		
		List<String>  status=new ArrayList<>();
		if ("B".equals(sessionUser.getRoleCode())|| "A".equals(sessionUser.getRoleCode())) {
			return 2;
		}
		return service.deleteModel(OrderModel);
	}

	@RequestMapping("/selectByCode")
	private OrderModel selectByCode(OrderModel OrderModel) {
		System.out.println("执行selectByCode" + OrderModel);
		OrderModel um = service.selectByCode(OrderModel.getCustCode(), OrderModel.getUserCode(),
				OrderModel.getProdCode());
		System.out.println("++++++++" + um);
		return um;
	}

	@RequestMapping("/selectById")
	private OrderModel selectById(OrderModel OrderModel) {
		System.out.println("执行selectByCode" + OrderModel);
		OrderModel um = service.selectById(OrderModel.getId());
		System.out.println("++++++++" + um);
		return um;
	}
     @ResponseBody
	@RequestMapping("/selectAll")
	private List<OrderModel> selectAll(OrderModel OrderModel,HttpServletRequest request) {
		System.out.println("+++" + OrderModel);
		
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		 String userCode = sessionUser.getUserCode();
		// 登录人员查询自己谈的订单
//		
		if ("A".equals(sessionUser.getRoleCode())) {
			List<OrderModel> list = service.selectAll(new OrderModel("", userCode , ""));
			return list;
		}
	
		List<OrderModel> list = service.selectAll(OrderModel);

		return list;
	}

	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String, Object> selectModel(OrderModel OrderModel,HttpServletRequest request ) {
		
		

		//权限二级经理/员工
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		
	
		if ("A".equals(sessionUser.getRoleCode())) {
			OrderModel.setUserCode(sessionUser.getUserCode());
		}
		
	
		OrderModel.setPageOn(true);
		System.out.println("执行slectModel:" + OrderModel);
		OrderModel.setCustCode("%" + OrderModel.getCustCode() + "%");
		OrderModel.setUserCode("%" + OrderModel.getUserCode() + "%");
		OrderModel.setProdCode("%" + OrderModel.getProdCode() + "%");
		List<OrderModel> list = service.selectModel(OrderModel);
		System.out.println("6666" + list);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("code", 0);
		map.put("count", service.selectCount(OrderModel));

		return map;
	}

}
