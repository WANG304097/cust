package com.situ.until;

import javax.mail.MessagingException;

public class Job1 {

	
	public void exe() throws MessagingException {
		
		   String[] to = { "418219107@qq.com" };// 收件人
	        String from = "915515288@qq.com";// 发件人
	        String pass = "ylmalnxrmtfybffi";
	        String hostSend = "smtp.qq.com";
	        String portSend = "587";
	        FmtMail ms = new FmtMail(to, from, pass, hostSend, portSend);
	        ms.send("AAA", "BBB");
		
	}
}
