<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   isELIgnored="false"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>
<fieldset class="layui-elem-field" style="margin: 20px; padding: 15px;">
		<legend>用户维护--修改信息</legend>
		<form class="layui-form layui-form-pane" lay-filter="updcust"
			method="post">
			<div class="layui-form-item" pane>
				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="custCode" required lay-verify="required"
					 value="${custUser2.custCode }"	placeholder="请输入编号" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">姓名：</label>
				<div class="layui-input-inline">

					<input type="text" name="custName" required lay-verify="required"
					 value="${custUser2.custName }"	placeholder="请输入职位" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">状态：</label> 
				<div class="layui-input-inline">
					<select name="status">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">邮箱地址：</label>
				<div class="layui-input-inline">

					<input type="text" name="email" required lay-verify="required"
				 value="${custUser2.email }"		placeholder="@qq.com" autocomplete="off" class="layui-input">
				</div>
			</div>

             
			<div class="layui-form-item" pane>
				<label class="layui-form-label"></label>
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" lay-submit lay-filter="updcust"
						value="确定" /> <input type="button" class="layui-btn"
						onclick="closeThis()" value="取消" />
				</div>
			</div>
			
		</form>
	</fieldset>

<script type="text/javascript">


//提交修改
formSubmit('/custUser/update', 'submit(updcust)', 'json', function(data) {
	if (data == 1) {
        layer.msg('修改成功');
        closeThis(3000);
    } else {
        layer.msg('修改失败');
    }
});



</script>

<script type="text/javascript">
init()
function init(){
	
	$("select[name='status']").val("${custUser2.status}");
};
form.render();

</script>


</body>
</html>