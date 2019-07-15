<%
    String app = request.getContextPath();
    app += "/web";
    String path = app + "/common/js";
%>
<meta charset="utf-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="renderer" content="webkit">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="icon" href="<%=path%>/img/logo.ico">

<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/layui/layui.all.js"></script>
<script type="text/javascript" src="<%=path%>/common.js"></script>


<script type="text/javascript" src="<%=path%>/echarts/echarts.min.js"></script>
