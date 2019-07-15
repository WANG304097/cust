<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>销售信息表</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>

	<fieldset class="lay-elem-field" style="margin: 20px; padding: 20px;">
 
		<form class="layui-form">
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
			
				<span> <input class="layui-btn" type="reset" value="重置" /> <input
					class="layui-btn" type="button" value="查询" onclick="refresh()" /> <input
					class="layui-btn" type="button" onclick="openorderAdd()" value="添加" />

				</span>
			</div>
			<input type="hidden" name="pageIndex" value="1" /> <input
				type="hidden" name="pageLimit" value="10" />
		</form>
	</fieldset>
	<table class="layui-hide" id="demo" lay-filter="test"></table>
	
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
	 
	

<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
  </div>
</script>

<script type="text/javascript">  
 form.render();
refresh();
function refresh(){

layui.use('table', function(){
   var table = layui.table;
var ins1 =  table.render({
	    elem: '#demo'
	
	    ,toolbar:'#toolbarDemo'  // true  左侧不显示
	  ,  defaultToolbar: ['filter', 'print', 'exports']
	    ,url: con.app+"/order/selectModel"//数据接口
	    ,page: true //开启分页
	    ,height:"full-100"  //容器高度
	     ,request: {
	     pageName: 'pageIndex' //页码的参数名称，默认：page
	     ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	     }
	     ,where: {custCode: $("select[name='custCode']").val()
//		     , custName:$("input[name='custName']").val()
//             , userName:$("input[name='userName']").val()
            , userCode:$("select[name='userCode']").val()
            , prodCode:$("select[name='prodCode']").val()
		     }
	    
	    ,cols: [[ //表头
	    	 {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	     ,{field:'custName', width:120, title: '客户名称'}
	     ,{field:'custCode', width:120, title: '客户编号' }
	      ,{field:'userName', width:120, title: '员工姓名'}
       ,{field:'userCode', width:120, title: '员工编号' }
       ,{field:'prodCode', width:120, title: '商品编号'}
       ,{field:'prodName', width:120, title: '商品名称'}
       
       ,{field:'time', width:120, title: '时间' }
       ,{field:'count', width:120, title: '数量' }
       ,{field:'status', width:120, title: '状态' }
	     ,{fixed: 'right',title: '操作一', width: 150, toolbar: '#titleTpl'}
// 	     ,{fixed: 'right',title: '操作二', width: 150, toolbar:'#titleTpl2' }
//	     ,{title: '操作三', width: 150, templet:'#titleTpl3' }
	     
	    ]]
	  });

   table.on('toolbar(test)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      case 'getCheckData':
	        var data = checkStatus.data;
	        layer.alert(JSON.stringify(data));
	      break;
	      case 'getCheckLength':
	        var data = checkStatus.data;
	        layer.msg('选中了：'+ data.length + ' 个');
	      break;
	      case 'isAll':
	        layer.msg(checkStatus.isAll ? '全选': '未全选');
	      break;
	    };
	  });

	    

});


   
}
</script>
	<script type="text/html" id="titleTpl">
<input type='button' class='layui-btn' onclick='openorderUpd("{{d.id}}")' value='修改' />
<a href="javascript:delorder('{{d.id}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>


	<script type="text/html" id="titleTpl2">
<a href="javascript:delorder('{{d.id}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>

	<script type="text/html" id="titleTpl3">

<input type='button' class='layui-btn' onclick='openorderPas("{{d.id}}")' value='重置密码' />
</script>
	<script type="text/javascript">

function openorderAdd(){
	
	 openLayer('/order/orderAddUpd',refresh);
}

function openorderUpd(id){
	 
		openLayer('/order/orderAddUpd?id='+id,refresh)
	}


function delorder(id){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/order/delete",
				dataType:"json",  
		    	data:{id:id},
			   
				success: function (data) {
					
					if (data == 1) {
			            layer.msg('删除成功');
			            $("input[name='pageIndex']").val(1);
			            refresh();
			        }else if(data==2){
			        	layer.msg('无权限');			        	
			        }
					
			     else {
		                layer.msg('删除失败');
		            }
				}			
			});   	 
			
			
		})
	}



</script>



</body>
</html>