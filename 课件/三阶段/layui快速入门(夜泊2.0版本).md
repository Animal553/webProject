

## 第一章 layui简介
```
layui（谐音：类UI) 是一款采用自身模块规范编写的前端 UI 框架，遵循原生 HTML/CSS/JS 的书写与组织形式，门槛极低，拿来即用;
layui 兼容人类正在使用的全部浏览器（IE6/7除外），可作为 PC 端后台系统与前台界面的速成开发方案;
```
---
## 第二章 layui安装
* 下载地址
```
https://www.layui.com/
```
* 目录结构
```
 ├─css //css目录
  │  │─modules //模块css目录（一般如果模块相对较大，我们会单独提取，比如下面三个：）
  │  │  ├─laydate
  │  │  ├─layer
  │  │  └─layim
  │  └─layui.css //核心样式文件
  ├─font  //字体图标目录
  ├─images //图片资源目录（目前只有layim和编辑器用到的GIF表情）
  │─lay //模块核心目录
  │  └─modules //各模块组件
  │─layui.js //基础核心库
  └─layui.all.js //包含layui.js和所有模块的合并文件
```
* 入门文件
```
/layui/css/layui.css
/layui/layui.js //提示：如果是采用非模块化方式（最下面有讲解），此处可换成：layui/layui.all.js
```
* 环境配置
```
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>分页组件</title>
		<!-- 引入css和js -->
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
		<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
		<!-- do something -->
		
	</body>
</html>
```
---
## 第三章 常见内置模块

### 第0节 内置模块介绍
```
layui 的模块是基于 layui.js 内部实现的异步模块加载方式，它并不遵循于AMD（没有为什么，毕竟任性呀！），而是自己定义了一套更轻量的模块规范。并且这种方式在经过了大量的实践后，成为 layui 最核心的模块加载引擎
```
* 预先加载
```
<script type="text/javascript">
	/* 
		加载分页模块
		layui使用layui.use加载模块
		比如加载form模块和laypage模块,可以一次加载多个,如果加载一个需要使用[]
	*/
   //加载模块
   layui.use(['form','laypage'],function(){
	   //获取form对象
	   var form = layui.form;
	   //获取分页对象laypage
	   var laypage = layui.laypage;
	   
   });
</script>
```
* 按需加载(不推荐)略

---
### 第1节 分页模块
```
<!-- 分页容器 -->
<div id="page_id">
	
</div>

<script type="text/javascript">
   //加载模块
   layui.use('laypage',function(){
	   //获取分页对象laypage
	   var laypage = layui.laypage;
	   //执行一个分页操作
	   laypage.render({
		   elem:"page_id",  //分页显示的容器
		   count:50,     //数据库总记录数,从后台获取
		   limit:10      //每页显示多少条数
	   });
   });
</script>
```
---
### 第2节 数据表格组件
```
<!-- table 数据表格 -->
<table id="table_id" lay-filter="test"></table>

<script type="text/javascript">
   //加载table模块
   layui.use('table',function(){
	   //获取table模块实例
	   var table = layui.table;
	   
	   //构建数据表格
	   table.render({
		   elem:"#table_id",
		   url:"http://localhost:8080/web01/getJson",
		   page:true,
		   limit:2,
		   limits:[2,4,6,8],
		   request:{
			   pageName:'pageNo',
			   limitName:"pageSize"
		   },
		   cols:[[
			   {
				   field:"bookId",
				   title:"图书ID",
				   width:"10%",
				   sort: true,
				   fixed: 'left'
			   },{
				   field:"bookName",
				   title:"图书名称",
				   width:"10%",
				   sort: true
			   },{
				   field:"authorName",
				   title:"作者",
				   width:"10%",
				   sort: true
			   },{
				   field:"category.categoryName",
				   title:"图书分类",
				   width:"10%",
				   sort: true,
				   templet:function(d){
					   return d.category.categoryName;
				   }
			   },{
				   field:"price",
				   title:"图书价格",
				   width:"10%",
				   sort: true
			   }
		   ]]
	   });
   });
</script>
```

> 数据表格的常见属性字段

>> table参数

参数 | 类型 | 说明 | 示例值
---|---|---|---
elem | String/DOM | 指定容器的选择器或DOM、方法渲染方式必填 | "#demo"
url | String | 异步数据的请求地址 | 必填
width | Number | 设定容器宽度。table容器的默认宽度是跟随它的父元素铺满，<br/>你也可以设定一个固定值，当容器中的内容超出了该宽度时，会自动出现横向滚动条 | 1000
height | Number/String | 设定容器高度
data | Array | 展示数据 | [{}, {}, {}, {}, …]
page | Boolean/Object | 开启分页（默认：false） | 
limit | Number | 每页显示多少条数 | 
limits | Array | 每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90] | [10,20,30]

>> cols - 表头参数


参数 | 类型 | 说明 | 示例值
---|---|---|---
field | String | 设定字段名。字段名的设定非常重要，且是表格数据列的唯一标识 | userId
title | String | 设定标题名称 | 用户ID
width | Number/String |设定列宽，若不填写，则自动分配；<br/>若填写，则支持值为：数字、百分比请结合实际情况，对不同列做不同设定 | 200    30%
fixed | String | 固定列。可选值有：left（固定在左）、right（固定在右）。<br/>一旦设定，对应的列将会被固定在左或右，不随滚动条而滚动。<br/>注意：如果是固定在左，该列必须放在表头最前面；<br/>如果是固定在右，该列必须放在表头最后面 | left（同 true） right
sort | Boolean | 是否允许排序（默认：false）。<br/>如果设置true，则在对应的表头显示排序icon，从而对列开启排序功能 | false
templet | String | 自定义列模板，模板遵循 laytpl语法。<br/>这是一个非常实用的功能，你可借助它实现逻辑处理，以及将原始数据转化成其它格式，<br/>如时间戳转化为日期字符等 | 例如将一个多表联查对象属性显示在前端

>> 异步数据接口


参数 | 功能
---|---
method | 请求方式默认 get
where | 设置接口的其他参数,除了分页的参数 where: {token: 'sasasas', id: 123}
contentType | 发送到服务端的内容编码类型。如果你要发送 json 内容，可以设置：contentType: 'application/json'
parseData | 数据格式解析的回调函数，用于将返回的任意数据格式解析成 table 组件规定的数据格式
request | 用于对分页请求的参数：page、limit重新设定名称
response | 借助 response 参数来重新设定返回的数据格式

* parseData
* * 默认数据格式
```
{
  "code": 0,
  "msg": "",
  "count": 1000,
  "data": [{}, {}]
} 
```
* * 假设返回的数据格式
```
{
  "status": 0,
  "message": "", 
  "total": 180, 
  "data": {
    "item": [{}, {}]
  }
}
```
* * 使用parseData修改(layui 2.4.0 新增)
```
parseData: function(res){ //res 即为原始返回的数据
    return {
      "code": res.status, //解析接口状态
      "msg": res.message, //解析提示文本
      "count": res.total, //解析数据长度
      "data": res.data.item //解析数据列表
    };
```
* request
```
request: {
    pageName: 'pageNo', //当前页,默认:page
    limitName: 'pageSize' //每页显示条数,默认:limit
}
```
* response
```
response: {
    statusName: 'status' //规定数据状态的字段名称，默认：code
    statusCode: 200 //规定成功的状态码，默认：0
    msgName: 'hint' //规定状态信息的字段名称，默认：msg
    countName: 'total' //规定数据总数的字段名称，默认：count
    dataName: 'rows' //规定数据列表的字段名称，默认：data
}
```

>> done - 数据渲染完的回调(类型：Function，默认值：无)

```
无论是异步请求数据，还是直接赋值数据，都会触发该回调。你可以利用该回调做一些表格以外元素的渲染
```
```
done:function(res, curr, count){
   console.log(res);
   console.log(curr); 
   console.log(count);
   //给表格的表格设置背景色,因为表格已经渲染完成所以可以进行DOM操作
   var thEles = document.getElementsByTagName("th");
   for(var i=0;i<thEles.length;i++){
	   thEles[i].style.backgroundColor="#007DDB"
   }
}
```
---
### 第3节 添加编辑删除查看详情操作按钮

* 表格中添加按钮样式
```
cols:[[
   {
	   field:"bookId",
	   title:"图书ID",
	   width:"10%",
	   sort: true,
	   fixed: 'left'
   },{
	   field:"bookName",
	   title:"图书名称",
	   width:"10%",
	   sort: true
   },{
	   field:"authorName",
	   title:"作者",
	   width:"10%",
	   sort: true
   },{
	   field:"category.categoryName",
	   title:"图书分类",
	   width:"10%",
	   sort: true,
	   templet:function(d){
		   return d.category.categoryName;
	   }
   },{
	   field:"price",
	   title:"图书价格",
	   width:"10%",
	   sort: true
   },{
	  title:"操作",
	  align:'center',
	  width:"10%",
	  templet:function(d){
		  var str="<a href='#' class=\"layui-btn layui-btn-xs\" >编辑</a>";
		  str+="<a href='#' class=\"layui-btn layui-btn-danger layui-btn-xs\">删除</a>";
		  return str;
	  }
   }
]]
```
* templet - 自定义列模板
```
在默认情况下，单元格的内容是完全按照数据接口返回的content原样输出的，如果你想对某列的单元格添加链接等其它元素，你可以借助该参数来轻松实现。这是一个非常实用且强大的功能，你的表格内容会因此而丰富多样
```
> 在上面给数据添加编辑和删除按钮的时候已经使用了templet 模板,上面是其中的一种方式(函数转义),如果数据量比较大的话,可以采用绑定模版选择器方式

```
{
  title:"操作",
  align:'center',
  width:"10%",
  templet: "#edittem"
}
					   
<script type="text/html" id="edittem">
  <a href='#' class="layui-btn layui-btn-xs" >编辑</a>
  <a href='#' class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
</script>
```

* 添加事件
```
<script type="text/html" id="edittem">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

lay-event: 给属性添加事件
```
```
/* 
    给表格的行添加事件
    tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
*/
table.on("tool(test)",function(d){
   console.log(d);
   if(d.event=='del'){
	   //删除，跳出弹窗
	   layer.confirm("是否删除?",{btn:['确认','取消']},function(index){
		   //调用删除接口 AJAX
		   
		   //重新加载
		   location.reload();
	   });
	   
   }
});
```
### 第4节 使用layui模板实现一个增删改查操作
* 获取列表和删除
```
layui.use(["table","layer","form","jquery"],function(){
			
	var table = layui.table, layer = layui.layer,form=layui.form,$=layui.jquery;
	
	table.render({
		elem:"#booksId",
		url:"http://localhost:8080/getBookPage",
		page:true,
		limit:2,
		limits:[2,4,6],
		request:{
			pageName:"pageNo",
			limitName:"pageSize"
		},
		cols:[[
			{field: 'bookId', title: '图书ID',  sort: true, fixed: 'left'},
			{field: 'bookName', title: '图书名字',  sort: true},
			{field: 'authorName', title: '图书作者',  sort: true},
			{field: 'price', title: '价格',  sort: true},
			{
				field: 'category.categoryName', 
				title: '分类名称',  
				sort: true,
				templet:function(d){
					return d.category.categoryName;
				}
			},
			{
				title: '操作',
				align:'center',
				templet:function(d){
					var obj = "<a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>";
					obj+="<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"del\">删除</a>";
					return obj;
				}
			}
		]]
	});
	/* 获取删除的事件 */
	table.on("tool(demo)",function(d){
		console.log(d.data.bookId);
		if(d.event=="del"){
			//删除
			$.getJSON("http://localhost:8080/deleteBook",{bookId:d.data.bookId},function(d){
				
				if(d.code==200){
					//删除成功
					location.reload();
				}
			});
		}else if(d.event=="edit"){
			//打开更新的弹出层
			layer.open({
				type: 2,
				title: '修改图书',
				closeBtn: 1, //显示关闭按钮
				anim: 2,
				area: ['800px', '600px'],
				shadeClose: true, //开启遮罩关闭
				content: 'book_update.html',
				success:function(layero, index){
					
					//获取弹出层的body元素
					var body = layer.getChildFrame("body",index);
					/**
					 * layero: 获取到的弹出层dom对象
					 */
					var bId = d.data.bookId;
					$.getJSON("http://localhost:8080/getBook",{"bookId":bId},function(r){
						//回显
						body.find("#bookName").val(r.bookName);
						body.find("#authorName").val(r.authorName);
						body.find("#price").val(r.price);
						body.find("#bookId").val(r.bookId);
						//下拉列表回显,重新定义回显方式,原有(下面)方式实现
						layero.find('iframe').contents().find('[name="categoryId"]').val(r.category.categoryId);
						
						//获取新窗口对象
						var iframeWindow = layero.find('iframe')[0].contentWindow;
						//重新渲染
						iframeWindow.layui.form.render();
					});
					
				}
			});
		}
	});
});
```
* 添加操作
```
//获取分类下拉列表数据
$.getJSON("http://localhost:8080/getCategoryList",function(d){
	var $select = $("#selectId");
	var sel = "";
	for(var i=0;i<d.length;i++){
		sel+="<option value=\""+d[i].categoryId+"\">"+d[i].categoryName+"</option>";
	}
	$select.html(sel);
});

layui.use(['form', 'layer'],function() {
    var form = layui.form,layer = layui.layer;

    //监听提交
    form.on('submit(add)',function(data) {
		console.log(data);
        console.log(data.field);
        //发异步，把数据提交给后台
		$.ajax({
				url:"http://localhost:8080/addBook",
				type:"post",
				data:JSON.stringify(data.field),
				contentType:"application/json;charset=utf-8",
				success:function(msg){
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
					//修改成功后刷新父界面
					window.parent.location.reload();
				},
				dataType:"json"
			});
        return false;
    });
});
```
* 更新操作
```
layui.use("form",function(){
	var form = layui.form;
	//获取分类数据
	$.getJSON("http://localhost:8080/getCategoryList",function(d){
		var $select = $("#selectId");
		var sel = "";
		for(var i=0;i<d.length;i++){
			sel+="<option value=\""+d[i].categoryId+"\">"+d[i].categoryName+"</option>";
		}
		$select.html(sel);
		//重新渲染
		form.render("select");
	});
	
	//提交更新
	//监听提交
	form.on('submit(update_)',function(data) {
	    console.log("----",data);
	    //发异步，把数据提交给后台
		$.ajax({
				url:"http://localhost:8080/updateBook",
				type:"post",
				data:JSON.stringify(data.field),
				contentType:"application/json;charset=utf-8",
				success:function(msg){
					// 获得frame索引
					var index = parent.layer.getFrameIndex(window.name);
					//关闭当前frame
					parent.layer.close(index);
					//修改成功后刷新父界面
					window.parent.location.reload();
				},
				dataType:"json"
			});
		
	    
	    return false;
	});
	
});
```