## 一. MVC和三层模型

#### MVC模型

> M : model  : 数据库 javaBean 
>
> V : view : html jsp
>
> C : control : servlet  jdbc 

> 建立一个项目, 项目中应该有表示数据结构和项目功能地方 , 应该给用户操作和展示结果的地方, 还应该有控制逻辑运算的地方

#### 三层模型

> controoler  : 和客户单进行交互
>
> service : 主要进行逻辑运算
>
> dao : 和数据库进行交互

## 二. 数据库连接池

#### 动态代理实现数据库连接池

```java
package com.qianfeng.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.sun.jndi.cosnaming.CNCtx;

public class JDBCUtils2 {
	//注册驱动 应该在项目启动的时候执行, 而且, 全局只需要执行一次即可
	/*
	 * DriverManager.registerDriver(new Driver()); new Driver();
	 */
	//加载驱动只需要执行一次 所以放到JDBCUtils这个类的静态代码块中
	//当JDBCUtils被加载的时候, 这段代码就会执行,而且只执行一次
	private static LinkedList<Connection> list = new LinkedList<>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			for (int i = 0; i < 10; i++) {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hanchun123");
				//改造connection -> connection2   代理 : 静态代理,  动态代理
				list.add(connection);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {

		Connection connection = list.poll();
		
		Class<?> clazz = connection.getClass();
		Connection cn = (Connection) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						 
						if("close".equals(method.getName())) {
							System.out.println(".......................");
							list.add(connection);
						}else {
							return method.invoke(connection, args);
						}
						return null;
					}
				});	
		//获取数据库连接
		return cn;
	}

```

#### druid

```java
private static DruidDataSource dataSource;
	static {
		dataSource = new DruidDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("root");
		dataSource.setPassword("hanchun123");
		dataSource.setMaxActive(100); //设置最大的连接数
		dataSource.setInitialSize(10); //设置初始化连接数
	}
	
	public static Connection getConnection() {

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
```

## 三.动态代理 

#### 原生的动态代理

```java
public static void main(String[] args) throws Exception {
	
	Student student = new Student();
	//创建Student的代理对象
	MyInterface p =  (MyInterface) Proxy.newProxyInstance(Student.class.getClassLoader(), Student.class.getInterfaces(), new InvocationHandler() {
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("你好");
			method.invoke(student);
			return null;
		}
	});
	p.method();
}
```

#### cglib

```java
public static void main(String[] args) {
	//导入包	cglib-core  asm		ant		ant-launcher
	//创建运行器
	MethodInterceptor mi = new MethodInterceptor() {
		
		@Override
		public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
			System.out.println("运行前");
			arg3.invokeSuper(arg0, arg2);
			System.out.println("运行后");
			return null;
		}
	};
	//获取代理类
	Enhancer enhancer = new Enhancer();
	//设置父类
	enhancer.setSuperclass(Demo.class);
	//运行任务
	enhancer.setCallback(mi);
	//创建代理对象
	Demo d = (Demo)enhancer.create();
	d.method();
}
```

## 四. 批处理

#### Statement

```java
Connection cn = JDBCUtils.getConnection();
		
Statement sm = cn.createStatement();

//sm.execute("insert into users values(default,'xiaohong','password')");
//将sql语句添加到缓存区
sm.addBatch("insert into users values(default,'xiaohong','password')");
sm.addBatch("insert into users values(default,'xiaoming','password')");
//一次性提交sql语句
sm.executeBatch();

JDBCUtils.close(sm, cn);
```

#### PreparedStatement

```java
Connection cn = JDBCUtils.getConnection();

PreparedStatement ps = cn.prepareStatement("insert into users values(default,?,?)");

ps.setString(1, "xiaogang");
ps.setString(2, "password");
ps.addBatch(); //将参数以组的形式存入缓存区

ps.setString(1, "xiaozhang");
ps.setString(2, "password");
ps.addBatch(); //将参数以组的形式存入缓存区

int[] i = ps.executeBatch();

JDBCUtils.close(ps, cn);
```

## 总结

1. mvc 和三层模型

   > model view control
   >
   > controller service dao : 将功能分开, 提高代码的复用性, 维护性

2. 动态代理

   > 在不破坏原始代码的情况下, 改写方法
   >
   > 代理 : 静态代理 动态代理
   >
   > 动态代理 : java原生的  cglib

3. 数据库连接池

   > 在初始化的时候创建多个数据库连接存储到容器中, 使用时直接从连接池中获取, 用完了再还回连接池中
   >
   > 节省了获取数据库连接的时间,提高执行效率

   > 市面上的数据库连接池有很多, 我们使用国产的druid

4. 批处理

   > 一次性发送多条数据, 节省了时间
   >
   > Statement
   >
   > PreparedStatement