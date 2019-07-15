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
		<form class="layui-form layui-form-pane" lay-filter="updrole"
			method="post">
			<div class="layui-form-item" pane>
				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="roleCode" required lay-verify="required"
					value="${role2.roleCode }" 	placeholder="请输入编号" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">职位：</label>
				<div class="layui-input-inline">

					<input type="text" name="roleName" required lay-verify="required"
					value="${role2.roleName }"	placeholder="请输入职位" autocomplete="off" class="layui-input">
				</div>
			</div>
             
			<div class="layui-form-item" pane>
				<label class="layui-form-label"></label>
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" lay-submit lay-filter="updrole"
						value="确定" /> <input type="button" class="layui-btn"
						onclick="closeThis()" value="取消" />
				</div>
			</div>
			
		</form>
	</fieldset>

<script type="text/javascript">


//提交修改
formSubmit('/role/update', 'submit(updrole)', 'json', function(data) {
	if (data == 1) {
        layer.msg('修改成功');
        closeThis(3000);
    } else {
        layer.msg('修改失败');
    }
});



</script>

<script type="text/javascript">

form.render();

</script>


</body>
</html>