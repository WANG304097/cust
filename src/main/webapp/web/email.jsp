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
<body background="">



	<fieldset class="lay-elem-field" style="margin: 20px; padding: 20px;">
		<legend>登录</legend>
		<form class="layui-form" action="/mis/user/login2.do" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">发件人：</label>
				<div class="layui-input-inline">
					<input type="text" name="UserCode" size="20" maxlength="6"
						class="layui-input" />
				</div>
			</div>


			<div class="layui-form-item">
				<label class="layui-form-label">收件人：</label>
				<div class="layui-input-inline">
					<input type="text" name="userName" size="20" maxlength="9"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文件：</label>
				<textarea id="demo" style="display: none;"></textarea>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<input class="layui-btn" lay-submit lay-filter="login"
						type="submit" value="发送"> <input class="layui-btn "
						type="reset" value="重置" />

				</div>
			</div>




		</form>
	</fieldset>

	<script>
		layui.use('layedit', function() {
			var layedit = layui.layedit;
			layedit.build('demo'); //建立编辑器
		});
	</script>

</body>
</html>