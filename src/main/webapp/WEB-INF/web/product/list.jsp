<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品信息维护</title>
<%@   include file="/web/header.jsp"%>
<style type="text/css">

</style>
</head>
<body >


<fieldset class="lay-elem-field"
             style="margin:20px;
             padding:20px; color: blue; border-color: blue;"
  
>

	<form class="layui-form" >
		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-inline">
				<input type="text" name="name" size="20" maxlength="6"
					class="layui-input" />
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label">编号：</label>
			<div class="layui-input-inline">
				<input type="text" name="code" size="20" maxlength="6"
					class="layui-input" />
			</div>
				<span>			
				<input class="layui-btn" type="reset" value="重置" /> 
				<input
					class="layui-btn" type="button" value="查询" onclick="refresh()"
					/> 
				<input
					class="layui-btn" type="button" onclick="openprodAdd()" value="添加" />
				
			</span>
		</div>
		</div>
	
		<input type="hidden" name="pageIndex" value="1" />
		<input type="hidden" name="pageLimit" value="10" />
	</form>
</fieldset>
<table class="layui-hide" id="demo"></table>
              
          

 <script type="text/javascript">  
refresh();
function refresh(){

layui.use('table', function(){
  var table = layui.table;

  table.render({
	    elem: '#demo'
	 
	    ,url: con.app+"/product/selectModel"//数据接口
	    ,page: true //开启分页
	    ,height:"full-100"  //容器高度
	     ,request: {
	     pageName: 'pageIndex' //页码的参数名称，默认：page
	     ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	     }
	     ,where: {code: $("input[name='code']").val(), name:$("input[name='name']").val()}
	    
	    ,cols: [[ //表头
	    	 {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	     ,{field:'code', width:120, title: '编号'}
	     ,{field:'name', width:120, title: '名称' , sort: true}
	     ,{field:'sum',  width:120,title: '数量'}
	     ,{field:'cost',  width:120,title: '单价'}
	     ,{title: '操作一', width: 150, templet: '#titleTpl'}
	     ,{title: '操作二', width: 150, templet:'#titleTpl2' }
// 	     ,{title: '操作三', width: 150, templet:'#titleTpl3' }
	     
	    ]]
	  });



  
 
});
}
</script> 
<script type="text/html" id="titleTpl">
<input type='button' class='layui-btn' onclick='openprodUpd("{{d.code}}")' value='修改' />
</script>


<script type="text/html" id="titleTpl2">
<a href="javascript:delprod('{{d.code}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>


<script type="text/javascript">

function openprodAdd(){
	
	 openLayer('/product/productAddUpd',refresh);
}

function openprodUpd(code){
	 
		openLayer('/product/productAddUpd?code='+code,refresh)
	}


function delprod(code){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/product/delete",
				dataType:"json",  
		    	data:{code:code},
			   
				success: function (data) {
					
					if (data == 1) {
			            layer.msg('删除成功');
			            $("input[name='pageIndex']").val(1);
			            refresh();
			        }else if(data==2){
			        	layer.msg('无法删除');			        	
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