<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     isELIgnored="false"
    %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
<%@   include file="/web/header.jsp"%>
</head>
<body class="layui-layout-body" background="/b2c/web/common/img/005.jpg">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">信息维护</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
   
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
         <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          admin
        </a>
        <dl class="layui-nav-child">
          <dd><a href="");">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:shan()">注销</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">员工信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:changeHtml('/user/userList');">用户信息维护</a></dd>
            <dd><a href="javascript:changeHtml('/role/roleList');">职业信息维护</a></dd>
            <dd><a href="javascript:changeHtml('/menu/menuList');">菜单信息维护</a></dd>
             <dd><a href="javascript:changeHtml('/rel/relList');">权限信息维护</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">商品信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:changeHtml('/web/goods/type/list.jsp');">商品类别维护</a></dd>
            <dd><a href="javascript:changeHtml('/web/goods/goods/list.jsp');">商品信息维护</a></dd>
           <dd><a href="javascript:changeHtml('/web/goods/img/list.jsp');">商品图片维护</a></dd>
           
            </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">订单信息维护</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:changeHtml('/web/order/car/list.jsp');">购物车信息维护</a></dd>
            <dd><a href="javascript:changeHtml('/web/order/order/list.jsp');">订单信息维护</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">基础配置</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:changeHtml('/web/404.jsp');">列表一</a></dd>
            <dd><a href="javascript:changeHtml('/web/404.jsp');">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe name="ff" src="" width="100%" height="100%"></iframe>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>

<script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});

function changeHtml(url){
	window.open(con.app+url,"ff")
	
}
function updHtml(url){
	openLayer(url, "null");
	
}

function shan(){
	
	 location.href = con.app + "/user/logout";

}
</script>
</body>
</html>