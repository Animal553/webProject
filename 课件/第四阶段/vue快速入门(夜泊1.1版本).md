

## 第一章 vue简介

### 第1节 vue的简介
```
Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架，Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合
```
### 第2节 vue的下载和安装
* 下载(开发版)
```
https://cn.vuejs.org/js/vue.js
```
* 安装
* * script标签安装
```
script引入本地js文件
```
* * CDN
```
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.11"></script>
```
* * 脚手架(略)
---
## 第二章 vue的使用

### 第1节 创建第一个vue实例
```
<div id="app">
	{{msg}}
</div>
<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		data:{
			msg:"Hello Vue!!!"
		}
	});
	//获取vm对象中的属性
	console.log(vm.msg);
</script>
```
```
1. el   : 元素标签
2. data : 设置元素的数据
3. {{}} : 插值表达式
```
---
### 第2节 vue的挂载点、模板、实例

* 挂载点
```
id="app"这个标签被称为下面new Vue实例的挂载点,而vue只会处理挂载点下面的内容
```
* 模板
```
<div id="app">
	<h1>强大的 Vue {{msg}}</h1>
</div>
在挂载点内部的内容都可以称之为模板内容,所以h1标签以及内部的数据都属于模板内容,我们可以将模板从元素标签提取出来放在vue实例里面,使用template属性.
```
```
<div id="app"></div>
<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		template:"<h1>强大的 Vue {{msg}}</h1>",
		data:{
			msg:"Hello Vue!!!"
		}
	});
	//获取vm对象中的属性
	console.log(vm.msg);
</script>
```
* 实例
```
实例中使用el属性关联挂载点,使用template属性设置模板,这样使用插值表达式就可以将data数据内容设置到模板中并且显示带挂载点中。
```
---
### 第3节 vue中的数据、事件和方法

* 数据
```
vue实例中使用data属性定义数据,data是一个对象({})类型,内部可以定义多少属性,在挂载点或者模板中使用插值表达式获取data中数据,使用属性名称获取
```
* * 数据在挂载点的显示方式
```
1. 插值表达式
<div id="app">
	<h1>Hello {{msg}}</h1>
</div>

2. 使用v-text  --> 将标签解析成字符串
<div id="app">
	<h1 v-text="msg"></h1>
</div>

3. 使用v-html  --> 可以解析标签
<div id="app">
    <h1 v-html="msg"></h1>
</div>

<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		data:{
			msg:"<span style='color:red'>Vue!!!</span>"
		}
	});
</script>
```
* 事件/方法
```
<div id="app">
	<!-- 
		v-on: 绑定事件,也可以将v-on修改成@,绑定事件的另一种方式
		click: 单击事件
		handlerClick: 单击事件要执行的方法名
	 -->
	<div v-on:click="handlerClick">{{msg}}</div>
</div>
<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		data:{
			msg:"hello"
		},
		methods:{  //在method属性中定义事件执行的方法
			handlerClick:function(){
				//当点击的时候修改挂载点数据，this代表当前Vue实例
				this.msg="Vue";
			}
		}
	});
</script>
```

---
### 第4节 vue中的属性绑定和双向数据绑定

* 属性绑定
```
<div id="app">
	<!-- 
		v-bind:属性绑定的方式,可以将v-bind省略,但是不能省略冒号
	 -->
	<div v-bind:title="msg">属性绑定测试</div>
</div>
<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		data:{
			msg:"我是一个标题属性"
		}
	});
</script>
```
* 双向数据绑定
```
<div id="app">
	<!-- 
		v-bind:属性绑定的方式,可以将v-bind省略,但是不能省略冒号
	 -->
	<div v-bind:title="msg">属性绑定测试</div>
	<!-- :value="content"属性绑定获取vue实例中data对象中的content属性值 -->
	<!-- <input type="text" :value="content"/> -->
	<!-- 双向数据绑定 v-model -->
	<input v-model="content"/>
	<div>{{content}}</div>
</div>
<script type="text/javascript">
	//实例化Vue对象
	var vm = new Vue({
		el:"#app",
		data:{
			msg:"我是一个标题属性",
			content:"测试双向数据绑定"
		}
	});
</script>
```
---
### 第5节 vue中的计算属性和侦听器

* 计算属性
```
<div id="app">
	姓:<input v-model="firstName" />
	名:<input v-model="lastName" />
	<div>{{firstName}}{{lastName}}</div>
	<div>{{fullName}}</div>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			firstName:'',
			lastName:''
		},
		computed:{ /* computed用来监控自己定义的变量，该变量不在data里面声明，直接在computed里面定义，然后就可以在页面上进行双向数据绑定展示出结果或者用作其他处理*/
			fullName:function(){
				/* this代表当前的vue对象 */
				return this.firstName+" "+this.lastName;
			}
		}
	});
</script>
```
* 侦听器
```
<div id="app">
	姓:<input v-model="firstName" />
	名:<input v-model="lastName" />
	<div>{{fullName}}</div>
	<div>{{count}}</div>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			firstName:'',
			lastName:'',
			count:0
		},
		computed:{ /* computed用来监控自己定义的变量，该变量不在data里面声明，直接在computed里面定义，然后就可以在页面上进行双向数据绑定展示出结果或者用作其他处理*/
			fullName:function(){
				/* this代表当前的vue对象 */
				return this.firstName+" "+this.lastName;
			}
		},
		watch:{  /* 使用watch属性监听firstName和lastName数值变化,或者直接监听计算属性*/
			/* firstName:function(){
				this.count++;
			},
			lastName:function(){
				this.count++;
			} */
			fullName:function(){
				this.count++;
			}
		}
	});
</script>
```
---
### 第6节 v-if/v-show和v-for指令

* v-if
```
<div id="app">
	<!-- 使用v-if在进行隐藏和显示时候会在dom节点中移除/添加此元素 -->
	<div v-if="show">Hello Vue</div>
	<button v-on:click="handleClick">显示/隐藏</button>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			show:false /* 设置成true*/
		},
		methods:{
			handleClick:function(){
				this.show=!this.show
			}
		}
	});
</script>
```
* v-show
```
<div id="app">
	<!-- 使用v-show标签元素存在使用display设置none来进行隐藏和显示 -->
	<div v-show="show">Hello Vue</div>
	<button v-on:click="handleClick">显示/隐藏</button>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			show:false /* 设置成true*/
		},
		methods:{
			handleClick:function(){
				this.show=!this.show
			}
		}
	});
</script>
```
* v-for
```
<div id="app">
	<input v-model="inputValue" />
	<button @click="inputClick" type="button">添加</button>
	<ul>
		<!-- 当数据没有内容的时候li标签不会循环 -->
		<li v-for="item of list" :key=item.id>{{item.name}}{{item.id}}</li>
	</ul>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			inputValue:"",
			list:[]
		},
		methods:{
			inputClick:function(){
				var obj={"id":this.list.length+1,"name":this.inputValue};
				this.list.push(obj);
				//将输入框清空
				this.inputValue="";
			}
		}
	});
</script>
```
---
## 第三章 简单例子开发

### 第1节 例子描述
```
<!--编写一个小栗子在输入框汇中输入数据的时候在下面的ul+li中添加数据,并且清空输入框-->
<div id="app">
	<input v-model="inputValue" />
	<button @click="inputClick" type="button">添加</button>
	<ul>
		<!-- 当数据没有内容的时候li标签不会循环 -->
		<li v-for="item of list" :key=item.id>{{item.name}}{{item.id}}</li>
	</ul>
</div>
<script type="text/javascript">
	new Vue({
		el:"#app",
		data:{
			inputValue:"",
			list:[]
		},
		methods:{
			inputClick:function(){
				var obj={"id":this.list.length+1,"name":this.inputValue};
				this.list.push(obj);
				//将输入框清空
				this.inputValue="";
			}
		}
	});
</script>
```
```
<!--
    1. 编写一个小栗子在输入框汇中输入数据的时候在下面的ul+li中添加数据,并且清空输入框
    2. 点击新增的内容然后删除数据
-->
<div id="app">
	<input v-model="inputValue" />
	<button @click="inputClick" type="button">添加</button>
	<ul>
		<!-- 
			当数据没有内容的时候li标签不会循环,给li标签绑定事件并且传递参数
			1. 如果事件直接绑定函数名称，那么默认会传递事件对象作为事件函数的第一个参数
			2. 如果事件绑定函数调用，那么事件对象必须作为最后一个参数显示传递，并且事件对象的名称必须是$event
		-->
		<li @click="handlerDelete(item.id,$event)" v-for="item of list" :key=item.id>{{item.name}}{{item.id}}</li>
	</ul>
</div>
<script type="text/javascript">
	
	new Vue({
		el:"#app",
		data:{
			inputValue:"",
			list:[]
		},
		methods:{
			inputClick:function(){
				var obj={"id":this.list.length+1,"name":this.inputValue};
				this.list.push(obj);
				//将输入框清空
				this.inputValue="";
			},
			handlerDelete:function(d,event){
				//splice函数删除数组中的数据根据,第一个参数数组下标,第二个参数,从下标数据开始删除几个
				this.list.splice(d-1,1);
			}
		}
	});
</script>
```
---
### 第2节 组件拆分
```
<div id="app">
	<input v-model="inputValue" />
	<button @click="inputClick" type="button">添加</button>
	<ul>
		<!-- 将此li标签注册成一个组件 -->
		<!-- <li v-for="(item,index) in list" :key="index">{{item}}</li> -->
		<!-- 使用自定义的组件替换原有的li标签 -->
		<!-- 
			todo-item: 自定义组件的组件名称
			:content自定义一个变量名称,给组件传值时使用
			:content="item" : 将组件的item这个值传递给下面component创建的组件
		 -->
		<todo-item 
			v-for="(item,index) in list"
			:key="index"
			:content="item"
			>
		</todo-item>
	</ul>
</div>
<script type="text/javascript">
	
	/* 自定义一个组件(全局组件),第一个参数组件名称 */
	/* 组件中接收上面传递过来的自定义参数时,需要使用props属性进行设置,否则接收不到 */
	Vue.component("todo-item",{
		props:['content'],
		template:"<li>{{content}}</li>"
	});
	
	
	/* 定义局部组件 */
	/* var todoItem = {
		template:"<li>item</li>"
	}; */
	
	new Vue({
		el:"#app",
		/* components:{ //当定义局部组件的时候需要在当前Vue实例里面进行注册
			'todo-item':todoItem
		}, */
		data:{
			inputValue:"",
			list:[]
		},
		methods:{
			inputClick:function(){
				this.list.push(this.inputValue);
				//清空输入框
				this.inputValue="";
			}
		}
	});
</script>
```
---
### 第3节 删除功能
```
<div id="app">
	<input v-model="inputValue" />
	<button @click="inputClick" type="button">添加</button>
	<ul>
		<todo-item 
			v-for="(item,index) in list"
			:key="index"
			:content="item"
			:index="index"
			@delete="handlerDelete"
			>
		</todo-item>
	</ul>
</div>
<script type="text/javascript">
	
	Vue.component("todo-item",{
		props:['content','index'],
		template:"<li v-on:click='handlerClick'>{{content}}</li>",
		methods:{
			handlerClick:function(){
				/* 当前组件使用$emit发布一个自定义的delete监听方法,监听delete事件是否被触发 */
				this.$emit('delete',this.index)
			}
		}
	});
	
	new Vue({
		el:"#app",
		data:{
			inputValue:"",
			list:[]
		},
		methods:{
			inputClick:function(){
				this.list.push(this.inputValue);
				//清空输入框
				this.inputValue="";
			},
			handlerDelete:function(index){
				//删除list里面对应下标的值
				this.list.splice(index,1);
			}
		}
	});
</script>
```
---
## 第四章 axios

* axios是什么?
```
axios是通过promise实现对ajax技术的一种封装，就像jQuery实现ajax封装一样
```
* 官网地址
```
http://www.axios-js.com/zh-cn/
```
* axios和vue实现分页
```
<div class="card" id="customerIds">
  <div class="card-toolbar clearfix">
	<form class="pull-right search-bar" method="get" action="#!" role="form">
	  <div class="input-group">
		<input type="text" class="form-control" value="" name="keyword" placeholder="请输入客户名称">
		<div class="input-group-btn">
		  <button class="btn btn-dark" type="button">搜索</button>
		</div>
	  </div>
	</form>
	<div class="toolbar-btn-action">
	  <a class="btn btn-primary m-r-5" href="customer_add.html"><i class="mdi mdi-plus"></i> 新增</a>
	  <a class="btn btn-info m-r-5" href="#!"><i class="mdi mdi-check"></i> 导出</a>
	  <a id="btnDel" class="btn btn-danger" href="#!"><i class="mdi mdi-window-close"></i> 删除</a>
	</div>
  </div>
  <div class="card-body">
	<div class="table-responsive">
	  <table class="table table-hover">
		<thead>
		  <tr>
			<th>
			  <label class="lyear-checkbox checkbox-primary">
				<input type="checkbox" id="check-all"><span></span>
			  </label>
			</th>
			<th>客户名称</th>
			<th>手机</th>
			<th>客户状态</th>
			<th>创建时间</th>
			<th>更新时间</th>
			<th>下次联系时间</th>
			<th>操作</th>
		  </tr>
		</thead>
		<tbody id="tbody">
			<tr v-for="(item,index) in list" :key="index">
				<td>
				  <label class="lyear-checkbox checkbox-primary">
					<input type="checkbox" name="ids[]" value="1001"><span></span>
				  </label>
				</td>
				<td>{{item.customerName}}</td>
				<td>{{item.phone}}</td>
				<td><span class="label label-primary">正常</span></td>
				<td>{{item.createTime}}</td>
				<td>{{item.updateTime}}</td>
				<td>{{item.nextTime}}</td>
				<td>
				  <div class="btn-group">
					<a class="btn btn-xs btn-default" href="customer_update.html" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>
					<a onclick="if(confirm('是否删除?')){return true;}else{return false;}" class="btn btn-xs btn-default" :href="item.customerId" title="删除" data-toggle="tooltip"><i class="mdi mdi-window-close"></i></a>
				  </div>
				</td>
			</tr>
		</tbody>
	  </table>
	</div>
	<ul class="pagination">
	  <li v-on:click="switchPage(pageNo-1)"><span style="cursor: pointer;">«</span></li>
	  <!-- <li class="active"><span>1</span></li>-->
	  <li v-for="item in pages" v-bind:class="{active:item==pageNo}" v-on:click="switchPage(item)"><span v-text="item" style="cursor: pointer;"></span></li>
	  <li><a v-on:click="switchPage(pageNo+1)" style="cursor: pointer;">»</a></li>
	</ul>
  </div>
</div>
```
```
<script type="text/javascript">
	
	new Vue({
		el:"#customerIds",
		data:{
			pageNo:1,
			pageSize:10,
			pageCount:5,
			list:[]
		},
		<!--钩子函数-->
		mounted() {
			//调用分页
			this.switchPage(1);
		},
		methods:{
		    <!--获取分页数据-->
			switchPage(num){
				this.pageNo=num;
				//console.log(num);
				if(this.pageNo<=0){
					alert("已经是第一页");
				}else{
					this.pageNo = this.pageNo-1;
				}
				if(this.pageNo>=this.pageCount){
					alert("已经是最后一页");
				}else{
					this.pageNo = this.pageNo+1;
				}
				//获取数据
				var that=this;
				axios.get('http://localhost:8080/getCustomerListJson',{
					params:{
						pageNo:that.pageNo,
						pageSize:that.pageSize
					}
				}).then(function(response){
					var r = response.data;
					//console.log("response=",r);
					that.pageNo=r.pageNo;
					that.pageSize=r.pageSize;
					that.pageCount=r.pageCount;
					that.list=r.page;
				}).catch(function(error){
					console.log("error=",error)
				});
			}
		},
		computed:{
		    <!--构建中间页-->
			pages:function(){
				let arr=[];
				if(this.pageCount<=5){
					for(var i=1;i<=this.pageCount;i++){
						arr.push(i); //将中间要显示的页码存进数组中
					}
				}else{
					//如果大于5
					if(this.pageNo<=5){
						for(var i=1;i<=5;i++){
							arr.push(i);
						}
					}else{
						for(var i=this.pageNo-4;i<=this.pageNo;i++){ 
							arr.push(i);
						}
					}
					
				}
				return arr;
			}
			
		}
	})
	
</script>
```
* 简说钩子函数
```
https://cn.vuejs.org/v2/api/#%E9%80%89%E9%A1%B9-%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E9%92%A9%E5%AD%90
```


