package com.situ.until;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



public class Job3 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        JobDataMap jobData = detail.getJobDataMap();
        Object list = jobData.get("list");
      
        String[] to = (String[]) jobData.get("list");// 收件人
		String from = "915515288@qq.com";// 发件人
		String pass = "ylmalnxrmtfybffi";
		String hostSend = "smtp.qq.com";
		String portSend = "587";
		FmtMail ms = new FmtMail(to, from, pass, hostSend, portSend);
		try {
			ms.send((String) jobData.get("zhuti"), (String) jobData.get("neirong"));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

    }

}
