<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加</title>
</head>
<body>
	<%@   include file="/web/header.jsp"%>
</head>
<body>

	<fieldset class="layui-elem-field" style="margin: 20px; padding: 15px;">
		<legend>用户维护--添加信息</legend>
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item" pane>
				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="code" required lay-verify="required"
					value="${product2.code }"	 autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-inline">

					<input type="text" name="name" required lay-verify="required"
					value="${product2.name}"	 autocomplete="off" class="layui-input">
				</div>
			</div>
		<div class="layui-form-item" pane>
				<label class="layui-form-label">数量：</label>
				<div class="layui-input-inline">

					<input type="text" name="sum" required lay-verify="required"
				value="${product2.sum}"		 autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">单价：</label>
				<div class="layui-input-inline">

					<input type="text" name="cost" required lay-verify="required"
				value="${product2.cost}"		 autocomplete="off" class="layui-input">
				</div>
			</div>

	<input type="hidden" name="id" value="${product2.id}">
			<div class="layui-form-item" pane>
				<label class="layui-form-label"></label>
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" lay-submit lay-filter="add"
						value="确定" /> <input type="button" class="layui-btn"
						onclick="closeThis()" value="取消" />
				</div>
			</div>


		</form>
	</fieldset>


	<script>
	init();
	function init() {
		var code = $('input[name="code"]')
		
		if (code.val() != '')
			code.prop('readonly', true)
			
	}			
	form.render();//渲染
		
		    form.on('submit(add)', function(data){
			  		    
			  console.log(data) //被执行事件的元素DOM对象，一般为button对象
			  $.ajax({
					type:"post",
					url:con.app+"/product/addUpd",
					dataType:"json",  
			    	data:data.field, //data发送到服务器
				   
					success: function (data) {
						if(data==0){
							layer.msg("注册成功");
							closeThis(2000);
						}else if(data==1){
							layer.msg("修改成功");
						}else{
							layer.msg("注册失败");
						}
					}			
				});   	 
			});


</script>
</body>
</html>