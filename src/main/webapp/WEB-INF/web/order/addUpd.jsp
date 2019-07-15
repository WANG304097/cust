<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加/修改</title>
</head>
<body>
<%@   include file="/web/header.jsp"%>
</head>
<body>

	<fieldset class="layui-elem-field" style="margin: 20px; padding: 15px;">
		<legend>用户维护--添加信息</legend>
		<form class="layui-form layui-form-pane" 
			>
		
		<div class="layui-form-item">
				<label class="layui-form-label">姓名：</label>
				<div class="layui-input-inline">		
						<select name="userName"  lay-search lay-filter="userCode" >
					<option  value="">请选择"</option>
                     <c:forEach items="${list1}" var="W" varStatus="WW">
                      <option  value="${W.userCode}">${W.userName}</option>           
                   </c:forEach>
					</select>	
				</div>

				<label class="layui-form-label">账号：</label>
				<div class="layui-input-inline">
				<select name="userCode"  lay-search  lay-filter="userCode" >
					<option  value="">请选择"</option>
                     <c:forEach items="${list1}" var="W" varStatus="WW">
                      <option  value="${W.userCode}">${W.userCode}</option>           
                   </c:forEach>
					</select>		
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">客户：</label>
				<div class="layui-input-inline">
				<select name="custName"  lay-search  lay-filter="custCode">
					<option  value="">请选择"</option>
                     <c:forEach items="${list2}" var="W" varStatus="WW">
                      <option  value="${W.custCode}">${W.custName}</option>           
                   </c:forEach>
					</select>	
					</div>
				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">
					<select name="custCode"  lay-search  lay-filter="custCode">
					<option  value="">请选择"</option>
                     <c:forEach items="${list2}" var="W" varStatus="WW">
                      <option  value="${W.custCode}">${W.custCode}</option>           
                   </c:forEach>
					</select>	
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">商品：</label>
				<div class="layui-input-inline">
					<select name="prodName"  lay-search lay-filter="prodCode" >
					<option  value="">请选择"</option>
                     <c:forEach items="${list3}" var="W" varStatus="WW">
                      <option  value="${W.code}">${W.name}</option>           
                   </c:forEach>
					</select>	
				</div>

				<label class="layui-form-label">编号：</label>
				<div class="layui-input-inline">
					<select name="prodCode"  lay-search lay-filter="prodCode" >
					<option  value="">请选择"</option>
                     <c:forEach items="${list3}" var="W" varStatus="WW">
                      <option  value="${W.code}">${W.code}</option>           
                   </c:forEach>
					</select>	
				</div>
				</div>
		
		
		
		<div class="layui-form-item" pane>
				<label class="layui-form-label">数量：</label>
				<div class="layui-input-inline">

					<input type="text" name="count" required lay-verify="required"
				value="${order2.count}"		 autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">状态：</label>
				<div class="layui-input-inline">

                 <select name="status">
                  <option value=""></option>
						<c:forEach items="${list4}" var="W" varStatus="WW">
							<option value="${W}">${W}</option>
						</c:forEach>
                        
                         
					</select>
						</div>
			</div>
			
				
				<input type="hidden" name="id" value="${order2.id}">
				
			
                   
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
				
	
	init()
	function init(){
		var code = $('select[name="userCode"]')
		var code2 = $('select[name="custCode"]')
		var code3 = $('select[name="prodCode"]')
		if (code.val() != ''){
			code.prop('readonly', true)
			code2.prop('readonly', true)
            code2.prop('readonly', true)
		}
		
        code.val("${order2.userCode}");
        code2.val("${order2.custCode}");
        code3.val("${order2.prodCode}");

        $('select[name="userName"]').val("${order2.userCode}");
        $('select[name="custName"]').val("${order2.custCode}");
        $('select[name="prodName"]').val("${order2.prodCode}");
		$("select[name='status']").val("${order2.status}");
		
		form.render();
	
	};
		    form.on('submit(add)', function(data){
			  		    
			  console.log(data) //被执行事件的元素DOM对象，一般为button对象
			  $.ajax({
					type:"post",
					url:con.app+"/order/addUpd",
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

<script type="text/javascript">
   
	form.on('select(custCode)', function(data){
	
		 console.log(data.value);
		 $("select[name='custCode']").val(data.value);
		$("select[name='custName']").val(data.value);
		form.render();
		});      
	form.on('select(userCode)', function(data){
		
		 console.log(data.value);
		 $("select[name='userCode']").val(data.value);
		$("select[name='userName']").val(data.value);
		form.render();
		}); 
	form.on('select(prodCode)', function(data){
		
		 console.log(data.value);
		 $("select[name='prodCode']").val(data.value);
		$("select[name='prodName']").val(data.value);
		form.render();
		}); 

	</script>
	
</body>
</html>