<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单主页</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>
<fieldset class="lay-elem-field"
             style="margin:20px;
             padding:20px;"
  
>

	<form class="layui-form" >
		<div class="layui-form-item">
			<label class="layui-form-label">菜单名：</label>
			<div class="layui-input-inline">
				<input type="text" name="menuName" size="20" maxlength="6"
					class="layui-input" />
			</div>
		
			</div>
		
			 <div class="layui-form-item" >
				<label class="layui-form-label">一级菜单：</label>
				<div class="layui-input-inline">
					<select name="parentCode"  lay-search lay-filter="parentCode">
					</select>
				</div>
				
				<label class="layui-form-label">二级菜单：</label>
				<div class="layui-input-inline" lay-search>
					<select name="menuCode">
					</select>
				</div>
		</div>
			<div class="layui-form-item" pane>
				<span>			
				<input class="layui-btn" type="reset" value="重置" /> 
				<input
					class="layui-btn" type="button" value="查询" onclick="refresh()"
					/> 
				<input
					class="layui-btn" type="button" onclick="openmenuAdd()" value="添加" />
				
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
	    ,url: con.app+"/menu/selectModel"//数据接口
	    ,page: true //开启分页
	    ,height:"full-230"  //容器高度
	     ,request: {
	     pageName: 'pageIndex' //页码的参数名称，默认：page
	     ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
	     }
	     ,where: {
		     menuName:$("input[name='menuName']").val()
	    	   ,menuCode:$("select[name='menuCode']").val()
	    	   ,parentCode:$("select[name='parentCode']").val()
             
		     }
	    
	    ,cols: [[ //表头
	    	 {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	     ,{field:'menuCode', width:120, title: '编号'}
	     ,{field:'menuName', width:120, title: '菜单名称' , sort: true}
	     ,{field:'menuUrl', width:200, title: '路径'}
	    
	     ,{field:'parentCode',  width:120,title: '上级菜单'}
	     ,{title: '操作一', width: 150, templet: '#titleTpl'}
	     ,{title: '操作二', width: 150, templet:'#titleTpl2' }
// 	     ,{title: '操作三', width: 150, templet:'#titleTpl3' }
	     
	    ]]
	  });



  
 
});
}
</script> 
<script type="text/html" id="titleTpl">
<input type='button' class='layui-btn' onclick='openmenuUpd("{{d.menuCode}}")' value='修改' />
</script>


<script type="text/html" id="titleTpl2">
<a href="javascript:delmenu('{{d.menuCode}}')"
		       class="layui-btn layui-btn-xs layui-btn-danger">
		      <i class="layui-icon layui-icon-delete"></i></a>
</script>

<script type="text/html" id="titleTpl3">

<input type='button' class='layui-btn' onclick='openmenuPas("{{d.menuCode}}")' value='重置密码' />
</script>
<script type="text/javascript">

function openmenuAdd(){
	
	 openLayer('/menu/menuAdd',refresh);
}

function openmenuUpd(menuCode){
	 
		openLayer('/menu/upmenu?menuCode='+menuCode,refresh)
	}

function openmenuPas(menuCode){
	 
		openLayer('/menu/menuPass?menuCode='+menuCode,refresh)
	}

// function openmenuImg(code){
	 
//		openLayer('/web/t1/menu/pic.jsp?code='+code,refresh)
//	}
function delmenu(code){
	
		openConfirm(function(index){
			
			
			$.ajax({
				type:"post",
				url: con.app+"/menu/delete",
				dataType:"json",  
		    	data:{menuCode:code},
			   
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

<script>
getSelect('parentCode',"a1");
function getSelect(selectid,parentCode){
	if(parentCode==''){
		$("select[name='menuCode']").empty();
		form.render('select')
	}
		
	$.ajax({
	    url:con.app+'/menu/selectAll',
	    data : {parentCode:parentCode},
	    dataType : 'json',
	    type : 'post',
	    success : function(data) {
	      var html="<option value=''>请选择</option>";
	      $.each(data,function(i,d){
	    	 
	    	  html+=laytpl($('#opt').html()).render(d);    	      	  
	      })
	      $("select[name='"+selectid+"']").html(html);
	      form.render('select'); //刷新select下拉框
	    }
	})
	
}



form.on('select(parentCode)',function(data){
	
	getSelect('menuCode',data.value)
})
     
</script>
<script type="text/html" id='opt'>

<option value='{{d.menuCode}}'> {{d.menuName}} </option>
</script>





</body>
</html>