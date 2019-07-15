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
		<form class="layui-form layui-form-pane" lay-filter="updrel"
			method="post">
			<div class="layui-form-item" pane>
			
				
				<label class="layui-form-label">职业名称：</label>
				<div class="layui-input-inline">
					<select name="roleCode"  lay-search  >
					<option  value="">请选择"</option>
                     <c:forEach items="${list}" var="W" varStatus="WW">
                      <option  value="${W.roleCode}">${W.roleName}</option>
                             
                   </c:forEach>
					
					
					</select>
				</div>
				
				<label class="layui-form-label">菜单名称：</label>
				<div class="layui-input-inline" lay-search>
					<select name="menuCode">
					  <option  value="">请选择"</option>
                     <c:forEach items="${list2}" var="W" varStatus="WW">
                      <option  value="${W.menuCode}">${W.menuName}</option>
                             
                   </c:forEach>
					</select>
				</div>
			
			
			</div>
             
			<div class="layui-form-item" pane>
				<label class="layui-form-label"></label>
				<div class="layui-input-inline">
					<input type="button" class="layui-btn" lay-submit lay-filter="updrel"
						value="确定" /> <input type="button" class="layui-btn"
						onclick="closeThis()" value="取消" />
				</div>
			</div>
			
		</form>
	</fieldset>

<script type="text/javascript">


//提交修改
formSubmit('/rel/update', 'submit(updrel)', 'json', function(data) {
	if (data == 1) {
        layer.msg('修改成功');
        closeThis(3000);
    } else if(data == 0){
    	layer.msg('此菜单，职业记录已存在');
        closeThis(3000);
        }else{
        layer.msg('修改失败');
    }
});



</script>

<script type="text/javascript">
init()
function init(){
	$("select[name='menuCode']").val("${rel2.menuCode}");
	$("select[name='roleCode']").val("${rel2.roleCode}");
};
form.render();

</script>



</body>
</html>