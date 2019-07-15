var con = {
	app : "/cust",
	jsp_login : "/web/login.jsp",
	jsp_reg : "/web/reg.jsp",
};
var form = layui.form;
var $ = layui.jquery;
var layer = layui.layer;
var element = layui.element;
var laypage = layui.laypage;
var laytpl = layui.laytpl;
var laydate = layui.laydate;
var upload = layui.upload;
// layui.use(['form','layer','jquery'], function () {
// var form = layui.form;// 获取操作对象
// var $ = layui.jquery;
// ...
// });

// 强烈不推荐下面的做法
// var form,$;
// layui.use(['form','layer','jquery'], function () {
// form = layui.form;// 获取操作对象
// $ = layui.jquery;
// ...
// });
  
function toJsp(url) {
	location.href = con.app + url;
}
function goJspLogin() {
	toJsp(con.jsp_login);
}  
function goJspReg() {
	toJsp(con.jsp_reg)
}
function goLogout() {
	
	
	openConfirm(function(index) {
		layer.close(index); 
		//alert(666)
		toJsp("/user/logout2.do");

	}, '确定注销？');
}
function openConfirm(func, title) {
	layer.confirm(title ? title : "确定进行该操作？", {
		icon : 3,
		title : '提示'
	}, func);
}

function formSubmit(url, submit, dataType, func) {
	form.on(submit, function(data) {
		ajax(url, data.field, dataType, func);
	});
}
function ajax(url, field, dataType, func) {
	$.ajax({
		url : con.app + url,
		data : field,
		dataType : dataType,
		type : 'post',
		success : func
	});
}
function openLayer(url, end) {
	layer.open({
		type : 2,
		area : [ '800px', '550px' ],
		fixed : false, // 不固定
		shadeClose: true,
		maxmin : true,
		shade:0.7,
				
		end : end,
		content : con.app + url,						  
		 closeBtn: 1,		   						  		
	});
}
function closeThis(time) {
	setTimeout(function() {// 先得到当前iframe层的索引
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}, time ? time : 200)
}
function setPageInfo(elem, count, curr, limit, jump) {
	laypage
			.render({
				elem : elem,// id
				count : count,// 数据总数
				curr : curr,// 当前页
				limit : limit,// 每页显示的条数
				limits : [ 10, 20, 30, 40, 50 ],// 每页条数的选择项
				layout : [ 'count', 'prev', 'page', 'next', 'limit', 'refresh',
						'skip' ],
				jump : jump
			});
}
function getlaytpl(sel, data) {
	return laytpl($(sel).html()).render(data);
}
function renderLaydate(id) {
	laydate.render({
		elem : '#' + id,
		type : 'date'
	});
}
function renderUpload(id, url, data, done) {
	//alert(6696);
	var uploadInst = upload.render({
		elem : '#' + id, // 绑定元素
		url : con.app + url, // 上传接口
		data : data,
		done : done
	});
}
