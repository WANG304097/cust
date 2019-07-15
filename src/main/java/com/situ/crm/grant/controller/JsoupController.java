package com.situ.crm.grant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.until.BaiKe;
import com.situ.until.FmtEmpty;

@Controller
@RequestMapping("/jsoup")
public class JsoupController {

	/**
	 * 跳转baike
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/baike", produces = "application/json;charset=UTF-8")

	public String baike() {

		return "tu/baike";
	}

	/**
	 * 检索
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/item",produces="application/json;charset=UTF-8") // ,produces="application/json;charset=UTF-8"
	@ResponseBody
	public String item(String item) {
		System.out.println("欢迎使用百科");
		if (FmtEmpty.isEmpty(item)) {
			return "";
		}
		String content = "";
		try {
			content = new BaiKe().getContent2(BaiKe.baseUrl + item);
			System.out.println("内容：" + content);
		} catch (Exception e) {

		}
		return  content;
//		Map<String, Object> map = new HashMap<>();
//		map.put("mess", content);
//		return map;
	}

}
