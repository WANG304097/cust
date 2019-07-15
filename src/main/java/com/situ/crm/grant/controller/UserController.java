package com.situ.crm.grant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.situ.crm.grant.model.MenuModel;
import com.situ.crm.grant.model.RelModel;
import com.situ.crm.grant.model.RoleModel;
import com.situ.crm.grant.model.UserModel;
import com.situ.crm.grant.service.IRelService;
import com.situ.crm.grant.service.IRoleService;

import com.situ.crm.grant.service.IUserService;
import com.situ.crm.mall.model.CustCommModel;
import com.situ.crm.mall.model.CustUserModel;
import com.situ.crm.mall.service.ICustCommService;
import com.situ.crm.mall.service.ICustUserService;
import com.situ.until.FmtEmpty;
import com.situ.until.FmtMail;
import com.situ.until.FormatPOI;
import com.situ.until.MD5;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService service2;
	@Autowired
	private IRelService service3;
	@Autowired
	private ICustUserService service4;
	@Autowired
	private ICustCommService service5;

	/**
	 * 邮箱
	 * @return
	 */
	@RequestMapping("/email")
	public String loginEmail(Model model) {
        
		List<CustUserModel> list = service4.selectAll(new CustUserModel());
		
		model.addAttribute("list",list);
		return "tu/email";
	}
	
	@RequestMapping("/setemail")
	public String setEmail( String  to1,String subject, String content, HttpServletRequest request) throws MessagingException {
	     
		
		
		
		    String[] to = { to1};// 收件人
		    String from = "915515288@qq.com";// 发件人
	         String pass="ylmalnxrmtfybffi";
	        String hostSend = "smtp.qq.com";
	        String portSend = "587";
	        FmtMail ms = new FmtMail(to, from, pass, hostSend, portSend);
	        ms.send(subject, content);
	   //插入记录
	        custComm(to1,subject,request);
	        
	        
	        return "/tu/email";
	}

	//邮件发送成功后插入一条记录
	private  int  custComm(String  to1,String subject, HttpServletRequest request) {
		//权限二级经理
				UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
				String userCode = sessionUser.getUserCode();	
				
				CustUserModel    model =new  CustUserModel  ();
				model.setEmail(to1);
				List<CustUserModel> list = service4.selectModel(model);
				CustUserModel cum = list.get(0);
		       String custCode = cum.getCustCode();
		       
		       Date d = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				String time = df.format(d);
			
		       
				return      service5.insert(new CustCommModel(custCode,userCode,time,"email",subject));
		
	}
	
	@RequestMapping("/login")
	public String login() {
System.out.println("666666");
		return "login";
	}
  
	@RequestMapping("/login2")
	public String login(UserModel userModel, HttpServletRequest request, Model model) {
		System.out.println("执行login" + userModel);

//		if (!isOKAuthCode(request)) {
//			model.addAttribute("msg", "验证码错误");
//			return "login";// "登录失败，验证码错误。"
//		}
		UserModel em = userService.selectByCode(userModel.getUserCode());
		userModel.setUserPass(MD5.encode(userModel.getUserPass()));
		String msg = null;
		String view;
		if (em == null) {
			// "登录失败，账号不存在。"
			msg = "账号不存在";
			view = "login";
		} else if (userModel.getUserPass().equals(em.getUserPass())) {

			request.getSession().setAttribute("user", em);
			model.addAttribute("menus", getMenu(em));
			view = "user/zhuye";
//			view = "main";
			view = "index";
			// 登陆成功
		} else {
			msg = "密码错误";
			view = "login";
			// "登录失败，密码错误。"
		}
		model.addAttribute("msg", msg);
		return view;
	}
/**
 * RBAC【基于角色的权限访问控制（Role-Based Access Control）】
 * 分类显示菜单
 * @param userModel
 * @return
 */
	private List<MenuModel> getMenu(UserModel userModel) {
		String roleCode = userModel.getRoleCode();
		if (FmtEmpty.isEmpty(roleCode)) {
			return null;
		}
		List<RelModel> list = service3.selectAll(new RelModel(roleCode, ""));

		if (FmtEmpty.isEmpty(list)) {

			return null;
		}
		List<MenuModel> result = new ArrayList<>();
		for (RelModel rel : list) {
			MenuModel menuModel = rel.getMenuModel();
			System.out.println("6666"+menuModel);
			String parentCode = menuModel.getParentCode();
			if (FmtEmpty.isEmpty(parentCode) || "a1".equals(parentCode)) {
				System.out.println("6666666");
				result.add(menuModel);
				continue;
			}
			for(MenuModel m :result) {
				System.out.println("9999"+m.getMenuCode());
			
				if(m.getMenuCode().equals(parentCode)) {
					
					m.getChild().add(menuModel);
					break;
				}
			}
		}
        System.out.println("result:"+result);
		return result;
	}

	/**
	 * 验证码功能
	 * 
	 * @param request
	 * @return
	 */
	private boolean isOKAuthCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("authCode");
		String a1 = (String) obj;
		String a2 = request.getParameter("text1");
		return a1.equals(a2);
	}

	/**
	 * 跳转list页
	 * 
	 * @return
	 */
	@RequestMapping("/userList")
	public String userList(Model model,HttpServletRequest request) {
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		if ("B".equals(sessionUser.getRoleCode())) {
			System.out.println("******************");
			return "user/list2";
		}
		
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<UserModel> list2 = selectAll(new UserModel("B"));
		UserModel um = selectAll(new UserModel("C")).get(0);
		list2.add(um);
		model.addAttribute("list2", list2);
	

		return "user/list";
	}

	/**
	 * 回显下拉框
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(Model model) {
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<UserModel> list2 = selectAll(new UserModel("B"));
		UserModel um = selectAll(new UserModel("C")).get(0);
		list2.add(um);
		model.addAttribute("list2", list2);
		return "user/add";
	}

	@ResponseBody
	@RequestMapping("/add")
	public Integer add(UserModel userModel) {

		System.out.println("执行add" + userModel);

		return userService.insert(userModel);

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

	@ResponseBody
	@RequestMapping("/delete")
	public Integer delete(UserModel UserModel, HttpServletRequest request) {
		System.out.println("执行delete:" + UserModel);
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		if (UserModel.getUserCode().equals(sessionUser.getUserCode())) {
			return 3;
		}
		return userService.deleteModel(UserModel);
	}

	@ResponseBody
	@RequestMapping("/update")
	public Integer update(UserModel UserModel) {

		System.out.println("执行UPDATE" + UserModel);
		return userService.update(UserModel);

	}

	/**
	 * 修改回显
	 */

	@RequestMapping("/upuser")
	private String upuser(UserModel UserModel, Model model) {
		System.out.println("执行selectByCode" + UserModel.getUserCode());
		UserModel um = userService.selectByCode(UserModel.getUserCode());
		model.addAttribute("user2", um);
		List<RoleModel> list = selectRole();
		model.addAttribute("list", list);
		List<UserModel> list2 = selectAll(new UserModel("B"));
		UserModel ume = selectAll(new UserModel("C")).get(0);
		list2.add(ume);
		model.addAttribute("list2", list2);
		return "user/userupd";
	}

	/**
	 * 修改密码
	 * 
	 * @param UserModel
	 * @return
	 */
	@RequestMapping("/userPass")
	public String userPass(Model model, UserModel UserModel) {
		System.out.println("执行selectByCode" + UserModel.getUserCode());
		UserModel um = userService.selectByCode(UserModel.getUserCode());
		model.addAttribute("user3", um);
		return "user/userpass";
	}

	@ResponseBody
	@RequestMapping("/updatePas")
	public Integer updatePas(UserModel UserModel) {

		return userService.update(UserModel);

	}

	@ResponseBody
	@RequestMapping("/selectByCode")
	private UserModel selectByCode(UserModel UserModel) {
		System.out.println("执行selectByCode" + UserModel.getUserCode());
		UserModel um = userService.selectByCode(UserModel.getUserCode());
		System.out.println("++++++++" + um);
		return um;
	}

	@ResponseBody
	@RequestMapping("/selectAll")
	private List<UserModel> selectAll(UserModel UserModel) {
		System.out.println("+++" + UserModel);
		List<UserModel> list = userService.selectAll(UserModel);

		return list;
	}
    
	

	/**
	 * 查找全部
	 * 
	 * @param UserModel
	 * @param pageIndex
	 * @param pageLimit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectModel")
	private Map<String, Object> selectModel(UserModel UserModel,HttpServletRequest request ,Integer pageIndex, Integer pageLimit) {

	//权限二级经理
		UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
		String parentCode = sessionUser.getUserCode();		
		if ("B".equals(sessionUser.getRoleCode())) {	
			UserModel.setParentCode(parentCode);
		}
		
		System.out.println("执行slectModel:" + UserModel + pageIndex + pageLimit);

		String userCode = UserModel.getUserCode();
		if (!FmtEmpty.isEmpty(userCode)) {
			UserModel.setUserCode("%" + userCode + "%");
		}
		String userName = UserModel.getUserName();
		if (!FmtEmpty.isEmpty(userName)) {
			UserModel.setUserName("%" + UserModel.getUserName() + "%");
		}

		if (!FmtEmpty.isEmpty(pageIndex)) {
			UserModel.setPageIndex(pageIndex);
		}
		if (!FmtEmpty.isEmpty(pageLimit)) {
			UserModel.setPageLimit(pageLimit);
		}
		UserModel.setPageOn(true);
		List<UserModel> list = userService.selectModel(UserModel);

		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("code", 0);
		map.put("count", userService.selectCount(UserModel));

		return map;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");

		return "redirect:/web/login.jsp";
	}

	@RequestMapping("/logout2")
	public String logout2(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/web/index.jsp";
	}
	
	
	/**
	 * 导出Excel
	 * @throws Exception 
	 */
	@RequestMapping(value="exportExcel")
	public void  exportExcel(UserModel userModel,HttpServletResponse  response ) throws Exception {
		
		List<UserModel> dataList = userService.selectAll(new UserModel());
		List<String> propList = Arrays.asList("userCode","userName","userPass","roleCode","roleName","parentCode");
		List<String> fieldName=Arrays.asList("账号","姓名","密码","职位编号","职位名称","上司");
		
		Workbook wb = FormatPOI.exportExcel(dataList, propList, fieldName);
		
		// 1). 设置响应的头文件，会自动识别文件内容
		
		response.setContentType("multipart/form-data");
		//2).是指Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=test.xls");
		//3).输出流
		
		OutputStream  out=response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	/**
	 * 导出Excel
	 * @throws Exception 
	 */
	@RequestMapping(value="exportExcelTpl")
	public void  exportExcelTpl(HttpServletResponse  response ) throws Exception {
		
		List<UserModel> dataList = null;
		List<String> propList = null ;
		List<String> fieldName=Arrays.asList("账号","姓名","密码","职位编号","职位名称","上司");
		
		Workbook wb = FormatPOI.exportExcel(dataList, propList, fieldName);
		
		// 1). 设置响应的头文件，会自动识别文件内容
		
		response.setContentType("multipart/form-data");
		//2).是指Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=test.xls");
		//3).输出流
		
		OutputStream  out=response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	/**
	 * 上传文件
	 * @return 
	 */
	@RequestMapping(value="uploadExcel")
	@ResponseBody
	public String  uploadExcelTpl(CommonsMultipartResolver multipartResolver,HttpServletRequest request) throws Exception {
		System.out.println("999999999999999999999");
		HashMap<String,Object> result = new HashMap<>();
		result.put("code", "0");
		if(multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest  multiRequest=(MultipartHttpServletRequest)request;
			Iterator<String> iter = multiRequest.getFileNames();
			
			while(iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				result.put("data", parse(file.getInputStream()));
			}
		}
		return  new JSONObject(result).toString();
	}
	
	private ArrayList<UserModel>  parse(InputStream fis) throws IOException {
		
		//有输入流得到工作簿
		XSSFWorkbook  workbook=new XSSFWorkbook(fis);
		//得到工作表
		XSSFSheet sheet = workbook.getSheet("list");
		ArrayList<UserModel>list = new ArrayList<>();
		
		for(Row row :sheet) {
			if(0==row.getRowNum()) {
				continue;
			}
			//"userCode","userName","userPass","roleCode","roleName","parentCode"
			UserModel userModel = new UserModel();
			userModel.setUserCode(getValue(row.getCell(0)));
			userModel.setUserName(getValue(row.getCell(1)));
			userModel.setUserPass(getValue(row.getCell(2)));
			userModel.setRoleCode(getValue(row.getCell(3)));
			userModel.setRoleName(getValue(row.getCell(4)));
			userModel.setParentCode(getValue(row.getCell(5)));
			list.add(userModel);
			add(userModel);
		}
		workbook.close();
		fis.close();
		return list;
	}

	

	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if(CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		}else if(CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}
	
	
	
}









