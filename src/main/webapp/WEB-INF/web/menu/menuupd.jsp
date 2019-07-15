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
		<form class="layui-form layui-form-pane" lay-filter="updmenu"
			method="post">
				<div class="layui-form-item" pane>
				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">

					<input type="text" name="menuCode" required lay-verify="required"
					 value="${menu2.menuCode }"	placeholder="请输入账号" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-inline">

					<input type="text" name="menuName" required lay-verify="required"
					 value="${menu2.menuName }"		placeholder="请输入姓名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">路径：</label>
				<div class="layui-input-inline">

					<input type="text" name="menuUrl" required lay-verify="required"
					 value="${menu2.menuUrl}"		placeholder="请输入" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label">菜单：</label>
				<div class="layui-input-inline">
					<select name="parentCode">
						<c:forEach items="${list2}" var="W" varStatus="WW">
							<option value="${W.menuCode}">${W.menuName}</option>
						</c:forEach>

					</select>
				</div>
			</div>



		
			<div class="layui-form-item" pane>
				<label class="layui-form-label"></label>
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" lay-submit lay-filter="updmenu"
						value="确定" /> <input type="button" class="layui-btn"
						onclick="closeThis()" value="取消" />
				</div>
			</div>
			
		</form>
	</fieldset>

<script type="text/javascript">


//提交修改
formSubmit('/menu/update', 'submit(updmenu)', 'json', function(data) {
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
	
	$("select[name='parentCode']").val("${menu2.parentCode}");
};
form.render();

</script>


</body>
</html>