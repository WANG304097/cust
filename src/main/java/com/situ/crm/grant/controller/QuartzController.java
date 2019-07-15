package com.situ.crm.grant.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.until.BaiKe;
import com.situ.until.FmtEmpty;
import com.situ.until.FmtScheduler;
import com.situ.until.Job3;

@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @ResponseBody
	@RequestMapping(value = "/setEmail", produces = "application/json;charset=UTF-8")
	public String setEmail(String subject, String content, String time,  String  to1) throws ParseException {

    	System.out.println("+++"+subject+content+time+to1);
    	SimpleDateFormat dddd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date  time2=dddd .parse(time);
    	System.out.println("+++"+subject+content+time+to1);
       fmtSch( subject,  content,time2,   to1);
		return "1";
	}
	
	private void fmtSch(String subject, String content, Date time,  String  to1) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();

	    String[] to = { to1};// 收件人
		map.put("list", to);
		map.put("neirong",content );
		map.put("zhuti", subject);
		FmtScheduler fmt = FmtScheduler.getInit(Job3.class, map);
//    	fmt.startSimpleTrigger(3);
		SimpleDateFormat dddd = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		String da = dddd.format(time);
		fmt.startCronTrigger(da);
	}

}
