 package com.situ.until;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class FmtMail {
    // foxmail
    // outlook

    private String[] to;   // 收件人电子邮箱
    private String   from; // 发件人电子邮箱
    private String   pass;
    // private String hostSend; // 指定发送邮件的主机 smtp.qq.com
    // private Session session; // 获取默认session对象
    private MimeMessage message; // 创建默认的 MimeMessage 对象
    public FmtMail() {
    }
    public FmtMail(String[] to, String from, String pass, String hostSend, String portSend) {
        this.to = to;
        this.from = from;
        this.pass = pass;
        init(hostSend, portSend);
    }

    public void init(String hostSend, String portSend) {
        // 获取系统属性
        // Properties properties = System.getProperties();
        Properties properties = new Properties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", hostSend);
        properties.setProperty("mail.smtp.port", portSend);
        properties.put("mail.smtp.auth", "true");
        // Session session = Session.getDefaultInstance(properties);
        Session session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
            }
        });
        // 创建默认的 MimeMessage 对象
        message = new MimeMessage(session);
    }

    public void send(String subject, String content) throws MessagingException {
        // Set From:
        message.setFrom(new InternetAddress(from));
        // Set To:
        for (String t : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(t));
        }
        // Set Subject:
        message.setSubject(subject);
        // 设置消息体
        message.setText(content);
        // 或者 发送 HTML 消息, 可以插入html标签
        // message.setContent("<h1>This is actual message</h1>",
        // "text/html;charset=utf-8" );

        // 发送消息
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }

    public static void main(String[] args) throws MessagingException {
        String[] to = { "418219107@qq.com" };// 收件人
        String from = "915515288@qq.com";// 发件人
        String pass = "ylmalnxrmtfybffi";
        String hostSend = "smtp.qq.com";
        String portSend = "587";
        FmtMail ms = new FmtMail(to, from, pass, hostSend, portSend);
        ms.send("AAA", "BBB");
    }
}

 class MailReceives {
    public static void main(String[] args) throws Exception {
        // 定义连接POP3服务器的属性信息
        String pop3Server = "pop.qq.com";
        String protocol = "pop3";
        String username = "915515288@qq.com";
        String password = "ylmalnxrmtfybffi"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址

        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = session.getStore(protocol);
        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件

        for (Message message : messages) {
            String subject = message.getSubject();// 获得邮件主题
            Address from = (Address) message.getFrom()[0];// 获得发送者地址
            System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from);
            System.out.println("邮件的内容为：");
            message.writeTo(System.out);// 输出邮件内容到控制台
        }

        folder.close(false);// 关闭邮件夹对象
        store.close(); // 关闭连接对象
    }
    
    public Map<String, Object>  getEmail()  throws Exception{
    	
    	
    	        // 定义连接POP3服务器的属性信息
    	        String pop3Server = "pop.qq.com";
    	        String protocol = "pop3";
    	        String username = "915515288@qq.com";
    	        String password = "ylmalnxrmtfybffi"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

    	        Properties props = new Properties();
    	        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
    	        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址

    	        // 获取连接
    	        Session session = Session.getDefaultInstance(props);
    	        session.setDebug(false);

    	        // 获取Store对象
    	        Store store = session.getStore(protocol);
    	        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

    	        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
    	        Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
    	        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

    	        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件
                  List<String> list1 = new ArrayList<>();
                  List<Address> list2 = new ArrayList<>();
                  List<Object> list3 = new ArrayList<>();
    	        for (Message message : messages) {
    	            String subject = message.getSubject();// 获得邮件主题
    	            Address from = (Address) message.getFrom()[0];// 获得发送者地址
    	            Object content = message.getContent();
    	            System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from);
    	            System.out.println("邮件的内容为：");
    	            list1.add(subject);
    	            list2.add( from);
    	            list3.add(content);
    	            message.writeTo(System.out);// 输出邮件内容到控制台
    	        }

    	        folder.close(false);// 关闭邮件夹对象
    	        store.close(); // 关闭连接对象
    	    	Map<String, Object> map = new HashMap<>();
    	        
    	        map.put("list1",list1);
    	        map.put("list2",list2);
    	        map.put("list3",list3);
    	        return map;
    }
    
    
}
