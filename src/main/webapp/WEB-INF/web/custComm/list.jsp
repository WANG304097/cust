<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
 isELIgnored="false"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>沟通记录表</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>
<fieldset class="lay-elem-field"
             style="margin:20px;
             padding:20px;"
  
>

	<form class="layui-form" >
		<div class="layui-form-item">
			<label class="layui-form-label">姓名：</label>
			<div class="layui-input-inline">
				<input type="text" name="userName" size="20" maxlength="6"
					class="layui-input" />
			</div>
		
			<label class="layui-form-label">账号：</label>
			<div class="layui-input-inline">
				<input type="text" name="userCode" size="20" maxlength="6"
					class="layui-input" />
			</div>
			</div>

			<div class="layui-form-item">
			<label class="layui-form-label">客户：</label>
			<div class="layui-input-inline">
				<input type="text" name="custName" size="20" maxlength="6"
					class="layui-input" />
			</div>
		
			<label class="layui-form-label">编号：</label>
			<div class="layui-input-inline">
				<input type="text" name="custCode" size="20" maxlength="6"
					class="layui-input" />
			</div>
			</div>
				
				<div class="layui-form-item">
				<span>			
				<input class="layui-btn" type="reset" value="重置" /> 
				<input
					class="layui-btn" type="button" value="查询" onclick="refresh()"
					/> 
				<input
					class="layui-btn" type="button" onclick="opencustCommAdd()" value="添加" />
				
			</span>
	</div>
		<input type="hidden" name="pageIndex" value="1" />
		<input type="hidden" name="pageLimit" value="10" />
	</form>
</fieldset>
<table class="layui-hide" id="demo"></table>
              
          

 <script type="text/javascript">  
 form.render();
refresh();
function refresh(){

layui.use('table', function(){
  var table = layui.table;

  table.render({
	    elem: '#demo'
	    ,height: 312
	    ,url: con.app+"/custComm/selectModel"//数据接口
	    ,page: true //开启分页
	    ,height:"full-230"  //容器高度
	     ,request: {
	     pageName: 'pageIndex' //页码的参数名称，默认：page
	     ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	     }
	     ,where: {custCode: $("input[name='custCode']").val()
		     , custName:$("input[name='custName']").val()
             , userName:$("input[name='userName']").val()
             , userCode:$("input[name='userCode']").val()
		     }
	    
	    ,cols: [[ //表头
	    	 {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	     ,{field:'custName', width:120, title: '客户名称'}
	     ,{field:'custCode', width:120, title: '客户编号' }
	      ,{field:'userName', width:120, title: '员工姓名'}
        ,{field:'userCode', width:120, title: '员工编号' }
        ,{field:'time', width:120, title: '时间' }
        ,{field:'type', width:120, title: '方式' }
        ,{field:'content', width:120, title: '内容' }
	     ,{title: '操作一', width: 150, templet: '#titleTpl'}
	     ,{title: '操作二', width: 150, templet:'#titleTpl2' }
// 	     ,{title: '操作三', width: 150, templet:'#titleTpl3' }
	     
	    ]]
	  });



  
 
});
}
</script> 
<script type="text/html" id="titleTpl">
<input type='button' class='layui-btn' onclick='opencustCommUpd("{{d.id}}")' value='修改' />
</script>


<script type="text/html" id="titleTpl2">
<a href="javascript:delcustComm('{{d.id}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>

<script type="text/html" id="titleTpl3">

<input type='button' class='layui-btn' onclick='opencustCommPas("{{d.id}}")' value='重置密码' />
</script>
<script type="text/javascript">

function opencustCommAdd(){
	
	 openLayer('/custComm/custCommAddUpd',refresh);
}

function opencustCommUpd(id){
	 
		openLayer('/custComm/custCommAddUpd?id='+id,refresh)
	}


function delcustComm(id){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/custComm/delete",
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