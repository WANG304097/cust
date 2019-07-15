<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>

	<fieldset class="lay-elem-field" style="margin: 20px; padding: 20px;">
		<legend>百科</legend>
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">词条：</label>
				<div class="layui-input-inline">
					<input type="text" name="item" size="20" maxlength="6"
						class="layui-input" />
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" 
						type="button" value="检索" onclick="sel()">检索</button>
					<input class="layui-btn " type="reset" value="重置" />

				</div>
			</div>
		</form>
	</fieldset>

	<fieldset class="lay-elem-field"
		style="margin: 20px; padding: 20px; height: 300px;">
		<legend>内容</legend>
		<div id="baike"></div>


	</fieldset>



	<script type="text/javascript">

		function sel() {
			var temp = $('form').serialize();
			$.ajax({
				type : "post",
				url : con.app + "/jsoup/item",
				dataType : "text",
				data : temp, //data发送到服务器
				async:false,   
				success : function(data) {
// 					alert(data)
					console.log(data);
					$("#baike").html(data);

				}
			})
		}
	</script>
</body>
</html>