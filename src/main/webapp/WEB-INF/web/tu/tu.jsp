<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@   include file="/web/header.jsp"%>
</head>
<body>
<div id="main" style="width: 600px;height: 400px;float: left;"></div>





<script type="text/javascript">
init();
var pro=new Array();
var sum=new Array();
function  init(){

	ajax("/order/selectAll","" , "json", function(data){

                $.each(data,function(i,dom){
//                 	  console.log(dom);
                       pro[i]=dom.time;
                       sum[i]=dom.count;
                   
                    })

                    var t=document.getElementById("main");	
                var  myChart=echarts.init(t);
                console.log(pro);
              var option = {
              	    xAxis: {
              	        type: 'category',
              	        data: pro
              	    },
              	    yAxis: {
              	        type: 'value'
              	    },
              	    series: [{
              	        data: sum,
              	        type: 'line'
              	    }]
              	};
              	myChart.setOption(option);
                    


		})
}

</script>
              

          


</body>
</html>