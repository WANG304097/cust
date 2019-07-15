package com.situ.until;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BaiKe {

    // 网络爬虫（又被称为网页蜘蛛，网络机器人，更经常的称为网页追逐者）
    // 是一种按照一定的规则，自动地抓取万维网信息的程序或者脚本。
    // 另外一些不常使用的名字还有蚂蚁、自动索引、模拟程序或者蠕虫。
    //
    // 实现步骤：
    // 1-通过url获取到需要访问的网址内容。
    // 2-将获取到的内容，解析成类似的DOM对象。
    // 3-通过选择器等方法查找到所要抓取的内容。

    public static String baseUrl = "https://baike.baidu.com/item/";
    private Scanner      input   = new Scanner(System.in);
    private String       url     = "";

    public static void main(String[] args) {
        new BaiKe().test();
    }

    public void test() {
        // 如果输入文字不是"00"，则爬取其百度百科的介绍部分，否则退出该程序
        while (true) {
            System.out.println("======请输入关键字，结束请输入“00”：======");
            url = input.nextLine();
            if (url.equals("00")) {
                System.out.println("程序已退出结束！");
                break;
            }
            String introduction = getContent2(baseUrl + url);
            System.out.println(introduction + '\n');
        }
    }

    // getContent()函数主要实现爬取输入文字的百度百科的介绍部分
    public String getContent(String url) {
        try {
            // 利用URL解析网址
            URL urlObj = new URL(url);
            // 打开URL连接
            URLConnection urlCon = urlObj.openConnection();
            return parse(urlCon.getInputStream(), url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContent2(String url) {
        // 1.使用默认的配置的httpclient
        CloseableHttpClient client = HttpClients.createDefault();
        // 2.使用get方法
        HttpGet httpGet = new HttpGet(url);
        try {
            // 3.执行请求，获取响应
            CloseableHttpResponse response = client.execute(httpGet);
            // 看请求是否成功，这儿打印的是http状态码
            System.out.println("[状态码=" + response.getStatusLine().getStatusCode() + "]");
            // 4.获取响应的实体内容，就是我们所要抓取得网页内容
            HttpEntity entity = response.getEntity();
            // 将其打印到控制台上面
            // System.out.println(EntityUtils.toString(entity, "utf-8"));
            // EntityUtils.consume(entity);
            return parse(entity.getContent(), url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parse(InputStream is, String url) {
        String contentText = "";
        try {
            // 将HTML内容解析成UTF-8格式
            Document doc = Jsoup.parse(is, "utf-8", url);
            // 刷选需要的网页内容
            contentText = doc.select("div.lemma-summary").first().text();
            // 利用正则表达式去掉字符串中的"[数字]"
            contentText = contentText.replaceAll("\\[\\d+\\]", "");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentText;
    }
}
