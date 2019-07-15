<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
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
		<form class="layui-form layui-form-pane" 
			>
		<div class="layui-form-item" pane>
				<label class="layui-form-label">客户编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="custCode" required lay-verify="required"
					value="${custComm2.custCode }"	 autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">员工编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="userCode" required lay-verify="required"
					value="${custComm2.userCode}"	 autocomplete="off" class="layui-input">
				</div>
			</div>
		<div class="layui-form-item" pane>
				<label class="layui-form-label">方式：</label>
				<div class="layui-input-inline">

					<input type="text" name="type" required lay-verify="required"
				value="${custComm2.type}"		 autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">内容：</label>
				<div class="layui-input-inline">

					<input type="text" name="content" required lay-verify="required"
				value="${custComm2.content}"		 autocomplete="off" class="layui-input">
				</div>
			</div>
			
				
				<input type="hidden" name="id" value="${custComm2.id}">
				
			
                   
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
	var code = $('input[name="userCode"]')
	var code2 = $('input[name="custCode"]')
	if (code.val() != ''){
		code.prop('readonly', true)
		code2.prop('readonly', true)
	}
}
			
	form.render();//渲染
		
		    form.on('submit(add)', function(data){
			  		    
			  console.log(data) //被执行事件的元素DOM对象，一般为button对象
			  $.ajax({
					type:"post",
					url:con.app+"/custComm/addUpd",
					dataType:"json",  
			    	data:data.field, //data发送到服务器
				   
					success: function (data) {
						if(data==0){
							layer.msg("注册成功");
							closeThis(2000);
						}else if(data==1){
							layer.msg("修改成功");
						}
						else if(data==1){
							layer.msg("无权限");
						}else{
							layer.msg("失败");
						}
					}			
				});   	 
			});


</script>
</body>
</html>