<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>职业维护</title>
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
				<input type="text" name="roleName" size="20" maxlength="6"
					class="layui-input" />
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label">账号：</label>
			<div class="layui-input-inline">
				<input type="text" name="roleCode" size="20" maxlength="6"
					class="layui-input" />
			</div>
				<span>			
				<input class="layui-btn" type="reset" value="重置" /> 
				<input
					class="layui-btn" type="button" value="查询" onclick="refresh()"
					/> 
				<input
					class="layui-btn" type="button" onclick="openroleAdd()" value="添加" />
				
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
	    ,height: 312
	    ,url: con.app+"/role/selectModel"//数据接口
	    ,page: true //开启分页
	    ,height:"full-230"  //容器高度
	     ,request: {
	     pageName: 'pageIndex' //页码的参数名称，默认：page
	     ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	     }
	     ,where: {roleCode: $("input[name='roleCode']").val(), roleName:$("input[name='roleName']").val()}
	    
	    ,cols: [[ //表头
	    	 {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	     ,{field:'roleCode', width:120, title: '账号'}
	     ,{field:'roleName', width:120, title: '名称' , sort: true}
	     ,{field:'descr',  width:80,title: '描述'}
	     ,{title: '操作一', width: 150, templet: '#titleTpl'}
	     ,{title: '操作二', width: 150, templet:'#titleTpl2' }
// 	     ,{title: '操作三', width: 150, templet:'#titleTpl3' }
	     
	    ]]
	  });



  
 
});
}
</script> 
<script type="text/html" id="titleTpl">
<input type='button' class='layui-btn' onclick='openroleUpd("{{d.roleCode}}")' value='修改' />
</script>


<script type="text/html" id="titleTpl2">
<a href="javascript:delrole('{{d.roleCode}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>

<script type="text/html" id="titleTpl3">

<input type='button' class='layui-btn' onclick='openrolePas("{{d.roleCode}}")' value='重置密码' />
</script>
<script type="text/javascript">

function openroleAdd(){
	
	 openLayer('/role/roleAdd',refresh);
}

function openroleUpd(roleCode){
	 
		openLayer('/role/upRole?roleCode='+roleCode,refresh)
	}

// function openrolePas(roleCode){
	 
// 		openLayer('/role/rolePass?roleCode='+roleCode,refresh)
// 	}

// function openroleImg(code){
	 
//		openLayer('/web/t1/role/pic.jsp?code='+code,refresh)
//	}
function delrole(code){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/role/delete",
				dataType:"json",  
		    	data:{roleCode:code},
			   
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





<!-- 	<table class="layui-table">
		<tr>
			<td>id</td>
			<td>账号</td>
			<td>密码</td>
			<td>姓名</td>
			<td>角色</td>
			<td>所属领导</td>
			
			<td>操作</td>
		</tr>

		<tbody id="tbody_id">
		</tbody>
	</table>
	<div id="pageInforole" style="text-align: right;padding-right: 30px"></div>
<script type="text/javascript"> 
 form.on('submit(search)', function(data){			
			 // console.log(data) //被执行事件的元素DOM对象，一般为button对象
			  $.ajax({
					type:"post",
					url:con.app+"/role/selectModel",
					dataType:"json",  
			    	data:data.field, //data发送到服务器
				   
					success: function (data) {
						//console.log(data.count);
						var curr = $("input[name='pageIndex']").val();
					    var limit = $("input[name='pageLimit']").val();
					 
					    laypage.render({
					  elem : 'pageInforole',
					  count : data.count,// 数据总数
					  curr : curr,// 当前页
					  limit : limit,// 每页显示的条数
					  layout : [ 'count', 'prev', 'page', 'next', 'limit', 'skip' ],
					  jump : function(obj, first){
						  $("input[name='pageIndex']").val(obj.curr);
					        $("input[name='pageLimit']").val(obj.limit);
					        if(!first){refresh();}//首次不执行
						  
					  }
					  });
					    
					    
					    setPageInfo('pageInforole',data.count,curr,limit
					    		,function(obj, first){
					        $("input[name='pageIndex']").val(obj.curr);
					        $("input[name='pageLimit']").val(obj.limit);
 					        if(!first){refresh();}//首次不执行
					    })
						
						
						var html="";
						 $.each(data.data,function(i, dom) {
							   var roleCode = dom.roleCode?dom.roleCode:'';
						        var roleName = dom.roleName?dom.roleName:'';						        
						        var rolePass = dom.rolePass?dom.rolePass:'';
						        
							// console.log(rolecode);
								
	 							html +="<tr><td>"+(i+1)+"</td><td>"+roleCode+"</td><td>"+
	 							dom.rolePass+"</td><td>"+dom.roleName+"</td><td>"+dom.roleName
	 							+"</td><td>"+dom.parentCode
	 							   
	 							+   "</td><td>"	 							 	 								
	 							+"<input type='button' class='layui-btn' onclick='openroleUpd(\""+roleCode+"\")' value='修改' />&nbsp;"
	 							+'<a href="javascript:delrole(\''+roleCode+'\')"'
	 						      +' class="layui-btn layui-btn-xs layui-btn-danger">'
	 						       +' <i class="layui-icon layui-icon-delete"></i></a>'

	 							+"<input type='button' class='layui-btn' onclick='openrolePas(\""+roleCode+"\")' value='重置密码' />&nbsp;"
// 	 							+"<input type='button' class='layui-btn' onclick='openroleImg(\""+code+"\")' value='头像' />&nbsp;"		 							   	 								 						     
	 							  + "</td></tr>"							       						      						      						        
						    });
						 $("#tbody_id").html(html);
					}
			  });					
 });



 refresh();
 function refresh(){
     $("input[value='查询']").click();
 } 
 function openroleAdd(){
	
	 openLayer('/role/roleAdd',refresh);
 }

 function openroleUpd(roleCode){
	 
		openLayer('/role/uprole?roleCode='+roleCode,refresh)
	}
 
 function openrolePas(roleCode){
	 
		openLayer('/role/rolePass?roleCode='+roleCode,refresh)
	}

//  function openroleImg(code){
	 
// 		openLayer('/web/t1/role/pic.jsp?code='+code,refresh)
// 	}
function delrole(code){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/role/delete",
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

 -->

</body>
</html>