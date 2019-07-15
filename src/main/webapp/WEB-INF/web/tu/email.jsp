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
		<legend>我的邮箱</legend>
		<div class="layui-form-item">
			<input class="layui-btn" type="button" onclick="getEmail()"
				value="我的收件箱">
		</div>

		<form class="layui-form"
			action="${pageContext.request.contextPath}/user/setemail"
			method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">收件人：</label>
				<div class="layui-input-inline">

					<select name="to1">
						<c:forEach items="${list}" var="W" varStatus="WW">
							<option value="${W.email}">${W.custName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">主题：</label>
				<div class="layui-input-inline">
					<input type="text" name="subject" size="20" maxlength="100"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文件：</label>
			</div>
			<div class="layui-form-item">
				<textarea id="demo" name="content" style="display: none;"></textarea>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-inline">
					<input class="layui-btn" type="submit" value="发送"> <input
						class="layui-btn " type="reset" value="重置" />
				</div>
				<div class="layui-input-inline">
					<input class="layui-btn" type="button" onclick="setEmail()"
					value="定时 发送"	>
						
				</div>
				<div class="layui-input-inline">
					<input type="text" size="20" maxlength="100" class="layui-input"
						id="test1" name="time">
				</div>

			</div>




		</form>
	</fieldset>



	<script>
		form.render();
		var index;
		layui.use('layedit', function() {
			var layedit = layui.layedit;
			  index = layedit.build("demo",{})//建立编辑器
		
		});

		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#test1',
				format : 'yyyy-MM-dd HH:mm:ss' //可任意组合
				,
				change : function(value, date) { //监听日期被切换
					lay('#test1').val(value);
				}
			});
		});
	</script>


	<script type="text/javascript">
		function getEmail() {
			openLayer(
					'https://mail.qq.com/cgi-bin/frame_html?sid=KLAuc9Q6c_LALhhF&r=80df09c35bce04769dc7b8d660ac66f7',
					null);
		}
		function setEmail() {
			
			layui.use('layedit', function() {
				var layedit = layui.layedit;
				
				var 	content=layedit.getContent(index);

			 var  to1=$("select[name='to1']").val();
			 var  subject=$("input[name='subject']").val();
			 var  time=$("input[name='time']").val();
			 alert(content+to1+subject+time);
// 				var temp = $('form').serialize();
				
// 	           temp.content=content;
// 	        console.log(temp)
				$.ajax({
					type : "post",
					url : con.app + "/quartz/setEmail",
					dataType : "Json",
					data : { to1: to1,subject:subject,content:content,time,time}, //data发送到服务器
					async : false,
					success : function(data) {
						if (data == 1) {
							layer.msg("定时成功");
							closeThis(2000);
						}
					}
				})
			
				
			});

		

		}
	</script>

</body>
</html>