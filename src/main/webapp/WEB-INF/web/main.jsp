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
<body class="layui-layout-body" >
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">项目二--RBAC</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
   
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
         <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          admin
        </a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:updHtml();">基本资料</a></dd>
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
       <c:forEach items="${menus}" var="m" >
				
	<li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">${m.menuName}</a>
          <dl class="layui-nav-child">
          <c:forEach items="${m.child}" var="m2" >
           <dd><a href="javascript:changeHtml('${m2.menuUrl}');" >
           ${m2.menuName}</a></dd>
           
          
          </c:forEach>
            </dl>
        </li>
				
		</c:forEach>

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
	
	 location.href = con.app + "/user/logout.do";

}
</script>
</body>
</html>