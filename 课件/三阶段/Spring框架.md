

## 第一章 Spring简介

### 第1节 Spring的介绍
* Spring官网地址
```
https://spring.io
```
* Spring的介绍
```
Spring是一个开放源代码的设计层面框架，他解决的是业务逻辑层和其他各层的松耦合问题，因此它将面向接口的编程思想贯穿整个系统应用。Spring是于2003年兴起的一个轻量级的Java 开发框架，由Rod Johnson创建。简单来说，Spring是一个分层的JavaSE/EE full-stack(一站式) 轻量级开源框架。
```
### 第2节 Spring的架构

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB725dfa3640aaa0f0b5779705b1e94e5e?method=download&shareKey=d6a6ef477b2d2e9168765d245d877a84"/>

</center>

### 第3节 Spring的核心依赖介绍

<center>
<img src="https://note.youdao.com/yws/api/personal/file/WEB6fec53306f8fecf3994a0e0f6c33f7ac?method=download&shareKey=cabdb3bd559fbe042758dc1cf7242b9c"/>

</center>

```
Spring框架由约20个模块组成,这些模块分为核心容器,数据库操作,web,AOP,设置,消息处理,测试等。
```
* Core Container
```
核心容器涉及到的模块
    spring-core
    spring-beans
    spring-context
    spring-context-support
    spring-expression
```
* AOP and Instrumentation
```
AOP(Aspect Oriented Programming)和设备

AOP涉及到的模块:
    spring-aop
    spring-aspects

设备涉及到的模块:
    spring-instrument
    spring-instrument-tomcat

```
* Messaging
```
消息涉及到的模块:
    spring-messaging
```
* Data Access/Integration
```
数据库操作涉及到的模块:
    spring-jdbc
    spring-tx
    spring-orm
    spring-oxm
    spring-jms
```
* Web
```
web涉及到的模块:
    spring-web
    spring-webmvc
    spring-websocket
    spring-webmvc-portlet
```
* Test
```
Test 涉及到的模块:
    spring-test
```
---
## 第二章 Spring的Core Container(核心容器)

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-framework-bom</artifactId>
    <version>4.3.27.RELEASE</version>
    <scope>import</scope>
    <type>pom</type>
</dependency>
```
### 第1节 核心容器(IOC)介绍

* IOC的概念
```
控制反转(Inversion of Control，缩写为IoC),又称为依赖注入,是面向对象编程中的一种设计思想，可以用来降低计算机代码之间的耦合度
```
* 依赖注入和控制反转的理解
```
依赖注入: 在应用程序中的组件需要获取资源时,传统的方式是组件主动去创建需要的资源;反转控制的思想完全颠覆了应用程序组件获取资源的方式,反转了资源的获取方向,改由容器创建,并主动的将资源推送给需要的组件，开发人员不需要知道容器是如何创建资源对象的,只需要提供接收资源的方式即可.

控制反转：IOC的另一种表述方式：即组件以一些预先定义好的方式(例如：setter方法)接受来自于容器的资源注入.
```
* IOC容器创建
```
Spring提供了创建IOC容器的接口(ApplicationContext),要想创建一个IOC容器只需要实例化此接口的实现类即可.

ApplicationContext接口的主要实现如下:
 - ClassPathXmlApplicationContext：创建IOC容器的典型实现类之一,基于对应类路径下的XML格式的配置文件进行IOC容器的初始化
 - ConfigurableApplicationContext:是ApplicationContext的子接口，里面包含了一些扩展方法,比如添加了启动,刷新,关闭IOC容器的能力


创建IOC实例的方式:
ApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
bean.xml： 类路径下的xml配置文件，里面是一些需要IOC容器进行初始化的类的配置
```
### 第2节 使用IOC容器进行对象实例化
```
我们有了IOC容器,IOC容器到底是干什么的,我们为什么要学习IOC容器,它能帮我们解决哪些问题?

我们下面会进行IOC容器的使用,你会发现IOC容器其实就是帮助我们创建对象和管理对象的.具体是怎么创建,怎么管理的,下面我们一个个说。
```
#### 2.1 bean标签方式
* bean的初始化(setter/getter)
```
<bean id="user1" class="com.sc.bean.User">
	<property name="id" value="1001"/>
	<property name="name" value="lilei"/>
</bean>
```
* bean的初始化(构造器方式)
```
<bean id="user2" class="com.sc.bean.User">
	<constructor-arg value="3001"></constructor-arg>
	<constructor-arg value="Jim"></constructor-arg>
</bean>

<bean id="user3" class="com.sc.bean.User">
	<constructor-arg index="0" value="1001"></constructor-arg>
	<constructor-arg index="1" value="hanmeimei"></constructor-arg>
</bean>

<bean id="user4" class="com.sc.bean.User">
	<constructor-arg name="id" value="2001"></constructor-arg>
	<constructor-arg name="name" value="Tom"></constructor-arg>
</bean>
```
* bean的初始化(p标签赋值)
```
<bean id="user1" class="com.sc.entity.User" p:id="100" p:name="lilei"></bean>
```
* bean的初始化(给属性赋null值)
```
不设置值或者是设置<null>标签

<bean id="user2" class="com.sc.entity.User">
    <property name="id" value="1000"></property>
    <property name="name">
    	<null></null>
    </property>
</bean>
```
* bean的初始化(对象引用ref赋值)
```
<bean id="user1" class="com.sc.entity.User" p:id="100" p:name="lilei"></bean>
<bean id="order1" class="com.sc.entity.Order">
	<property name="orderId" value="12334434"></property>
	<property name="orderName" value="dingdan"></property>
	<property name="user" ref="user1"></property>
</bean>
```
* bean的初始化(内部bean赋值)
```
<bean id="order2" class="com.sc.entity.Order">
	<property name="orderId" value="12334434"></property>
	<property name="orderName" value="dingdan"></property>
	<property name="user">
		<bean class="com.sc.entity.User">
			<property name="id" value="2000"></property>
			<property name="name" value="hanmeimei"></property>
		</bean>
	</property>
</bean>
```
* bean的初始化(list赋值)
```
<bean id="user3" class="com.sc.entity.User">
	<property name="id" value="66666"></property>
	<property name="name" value="666666"></property>
	<property name="orders">
		<list>
			<ref bean="order3"/>
			<ref bean="order4"/>
			<ref bean="order5"/>
		</list>
	</property>
</bean>
```
* bean的初始化(set赋值)
```
<bean id="user5" class="com.sc.entity.User">
	<property name="id" value="4000"></property>
	<property name="name" value="400000"></property>
	<property name="orderSet">
		<set>
			<ref bean="order3"/>
			<ref bean="order4"/>
			<ref bean="order5"/>
		</set>
	</property>
</bean>
```
* bean的初始化(map赋值)
```
<bean id="user4" class="com.sc.entity.User">
	<property name="id" value="4000"></property>
	<property name="name" value="400000"></property>
	<property name="map">
		<map>
			<entry key="key01" value="value1"></entry>
			<entry key="key02" value="value2"></entry>
			<entry key="key03" value="value3"></entry>
		</map>
	</property>
</bean>
```
* util名称创建对象list(了解)
```
<util:list list-class="java.util.ArrayList" id="utilList">
	<ref bean="order3"/>
	<ref bean="order4"/>
	<ref bean="order5"/>
	<value>23123</value>
</util:list>
```
* bean的初始化(Properties赋值)
```
<bean id="user6" class="com.sc.spring.User">
	<property name="id" value="1000"></property>
	<property name="name" value="lilei"></property>
	<property name="properties">
		<props>
			<prop key="username">root</prop>
			<prop key="password">root</prop>
			<prop key="url">jdbc:mysql://localhost:3306/spring_repository</prop>
			<prop key="driver">com.mysql/jdbc.Driver</prop>
		</props>
	</property>
</bean>
```
* 级联属性赋值
*  * Java类
```
public class UserDao {

	private String daoMsg;
}

public class UserService {

	private UserDao userDao;
	
}
```
* * XML配置
```
<bean id="userDao" class="com.sc.entity.UserDao"></bean>

<bean id="userService" class="com.sc.entity.UserService">
	<!-- 级联赋值首先是实例化赋值 -->
	<property name="userDao"  ref="userDao"></property>
	<!-- 然后在进行级联属性赋值 -->
	<property name="userDao.daoMsg" value="赋值成功"></property>
</bean
```

#### 2.2 注解的方式
```
相对于XML方式而言，通过注解的方式配置bean更加简洁和优雅，而且和MVC组件化开发的理念十分契合，是开发中常用的使用方式.
@Component
public class User{
    
}
```
* 使用注解标识要加入到IOC管理的类
```
①普通组件：@Component
标识一个受Spring IOC容器管理的组件
②持久化层组件：@Repository
标识一个受Spring IOC容器管理的持久化层组件
③业务逻辑层组件：@Service
标识一个受Spring IOC容器管理的业务逻辑层组件
④表述层控制器组件：@Controller
标识一个受Spring IOC容器管理的表述层控制器组件
⑤组件命名规则
[1]默认情况：使用组件的简单类名首字母小写后得到的字符串作为bean的id
[2]使用组件注解的value属性指定bean的id
```
* 组件扫描
```
组件被上述注解标识后还需要通过Spring进行扫描才能够侦测到

指定被扫描的package:
<context:component-scan base-package="com.sc.entity"/>
```
* 包含和排除
```
<context:component-scan base-package="com.sc.entity">
    <!-- 包含哪个注解 可以设置多个 -->
	<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
	<!-- 排除哪个注解 可以设置多个 -->
	<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```
### 第3节 静态工厂类/实例工厂类

#### 3.1 静态工厂
* Java代码
```
/**
 * 静态工厂类
 */
public class StaticFactory {
	private static Map<String, User> map;
	static {
		map = new HashMap<>();
		map.put("key01", new User(100, "user1"));
		map.put("key02", new User(102, "user2"));
		map.put("key03", new User(103, "user3"));
	}
	public static User getUser(String key){
		return map.get(key);
	}
}
```
* xml配置
```
<!-- 
	class:静态工厂类的全类名
	factory-method: 从静态工厂中获取的产品方法名
	id: 从IOC容器中取出从静态方法中生产出来的产品对象
	constructor-arg: 方法的入参
 -->
<bean id="user" class="com.sc.entity.StaticFactory" factory-method="getUser">
	<constructor-arg value="key01"></constructor-arg>
</bean>
```
#### 3.2 实例工厂
* Java代码
```
/**
 *  实例工厂类
 */
public class InstanceFactory {

	private Map<String, User> map;
	{
		map = new HashMap<>();
		map.put("key01", new User(100, "user1"));
		map.put("key02", new User(102, "user2"));
		map.put("key03", new User(103, "user3"));
	}
	public User getUser(String key){
		return map.get(key);
	}
}
```
* xml配置
```
<!-- 首先创建实例工厂 -->
<bean id="instanceFactory" class="com.sc.entity.InstanceFactory"></bean>

<bean id="user2" factory-bean="instanceFactory" factory-method="getUser">
	<constructor-arg value="key02"></constructor-arg>
</bean>
```
### 第4节 factoryBean接口
```
Spring中有两种类型的bean，一种是普通bean，另一种是工厂bean，即FactoryBean。工厂bean跟普通bean不同，其返回的对象不是指定类的一个实例，其返回的是该工厂bean的getObject方法所返回的对象
```
* Java代码
```
public class FactoryUser implements FactoryBean<User>{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public User getObject() throws Exception {
		return new User(1001,name);
	}
	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
	@Override
	public boolean isSingleton() {
		return true;
	}
}
```
* XML配置
```
<bean id="user" class="com.sc.spring.FactoryUser">
	<!-- 设置工厂类的参数 -->
	<property name="name" value="李雷"></property>
</bean>
```
### 第5节 bean配置的继承
```
Spring允许继承bean的配置，被继承的bean称为父bean。继承这个父bean的bean称为子bean。子bean从父bean中继承配置，包括bean的属性配置.子bean也可以覆盖从父bean继承过来的配置
```
* Java代码
```
public class User {
	private Integer id;
	private String name;
    
}

public class Student extends User {
	private String gender;
}
```
* XML配置
```
<bean id="user" class="com.sc.spring.User">
	<property name="id" value="1001"></property>
	<property name="name" value="lilei"></property>
</bean>

<bean id="student" parent="user" class="com.sc.spring.Student">
	<property name="gender" value="男"></property>
</bean>
```
### 第6节 bean之间的依赖
```
有的时候创建一个bean的时候需要保证另外一个bean也被创建，这时我们称前面的bean对后面的bean有依赖。例如：要求创建Employee对象的时候必须创建Department。这里需要注意的是依赖关系不等于引用关系，Employee即使依赖Department也可以不引用它
```
```
<bean id="emp" class="com.sc.spring.Employee" depends-on="dept">
	<property name="id" value="2001"></property>
	<property name="name" value="emp2"></property>
</bean>

<bean id="dept" class="com.sc.spring.Department">
	<property name="id" value="1001"></property>
	<property name="name" value="IT事业部"></property>
</bean>
```
### 第7节 bean的作用域
```
在Spring中，可以在bean元素的scope属性里设置bean的作用域，以决定这个bean是单实例的还是多实例的。默认情况下，Spring只为每个在IOC容器里声明的bean创建唯一一个实例，整个IOC容器范围内都能共享该实例：所有后续的getBean()调用和bean引用都将返回这个唯一的bean实例。该作用域被称为 singleton，它是所有bean的默认作用域
```

类别 | 说明
---|---
singleton | 在IOC容器中仅存在一个bean实例，bean以单实例的方式存在
prototype | 每次调用getBean()时都会返回新的实例

* xml配置
```
<bean id="user" class="com.sc.spring.User" scope="prototype">
	<property name="id" value="1001"></property>
	<property name="name" value="lilei"></property>
</bean>
```
* 注解配置
```
@Scope(value="prototype")
@Component
public class User{
    
}
```
* 测试代码
```
ApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
User bean = (User) ioc.getBean("user");
User bean2 = (User) ioc.getBean("user");
System.out.println(bean.hashCode());
System.out.println(bean2.hashCode());
```
### 第8节 bean的生命周期
```
Spring IOC容器可以管理bean的生命周期，Spring允许在bean生命周期内特定的时间点执行指定的任务

在配置bean时，通过init-method和destroy-method属性为bean指定初始化和销毁方法
```

* Java代码
```
public class User {
	public void init() {
		System.out.println("user被初始化了");
	}
	public void destroy() {
		System.out.println("user被销毁了");
	}

}
```
* XML配置
```
<bean id="user" class="com.sc.spring.User" init-method="init" destroy-method="destroy"></bean>
```
* 测试代码
```
@Test
public void test01() {
	//ConfigurableApplicationContext: 带有关闭IOC容器的方法,如果不关闭IOC容器不会调用销毁方法
	ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
	User bean = (User) ioc.getBean("user");
	System.out.println(bean);
	ioc.close();
}
```
### 第9节 bean的后置处理器
* 后置处理器介绍
```
bean后置处理器允许在调用初始化方法前后对bean进行额外的处理;bean后置处理器对IOC容器里的所有bean实例逐一处理，而非单一实例。其典型应用是：检查bean属性的正确性或根据特定的标准更改bean的属性;bean后置处理器需要实现接口：org.springframework.beans.factory.config.BeanPostProcessor

在初始化方法被调用前后，Spring将把每个bean实例分别传递给上述接口的以下两个方法：
 - postProcessBeforeInitialization(Object, String)
 - postProcessAfterInitialization(Object, String)
```
* Java代码
```
public class ABeanPostProcessor implements BeanPostProcessor {

	public ABeanPostProcessor() {
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization="+bean+"beanName:"+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization="+bean+"beanName:"+beanName);
		return bean;
	}

}
```
* XML配置
```
<bean class="com.sc.spring.ABeanPostProcessor"></bean>
```
### 第10节 外部资源导入配置
* 直接配置
```
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="root"/>
	<property name="password" value="root"/>
	<property name="jdbcUrl" value="jdbc:mysql:///test"/>
	<property name="driverClass" value="com.mysql.jdbc.Driver"/>
</bean>
```
* 外部资源配置方式
```
<!-- classpath:表示属性文件位于类路径下 -->
<context:property-placeholder location="classpath:db.properties"/>

<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="${user}"/>
	<property name="password" value="${password}"/>
	<property name="jdbcUrl" value="${url}"/>
	<property name="driverClass" value="${driver}"/>
</bean>

直接配置方式是将配置信息直接写在XML配置文件中,这样不好管理,所以我们将配置信息抽出放在一个properties文件中: db.properties

user=root
password=root
url=jdbc:mysql://localhost:3306/hello
driver=com.mysql.jdbc.Driver
```

### 第11节 自动装配

#### 11.1 基于xml的自动装配
```
通过设置 autowire 属性来进行自动装配

1. byType : 通过类型进行匹配
2. byName : 通过bean的id进行匹配
```
* Java代码
```
public class UserDao {

	private String msg="我是userDao";
}

public class UserService {

	private UserDao userDao;
}

public class UserController {

	private UserService userService;
}
```
* XML配置
```
<bean id="userDao" class="com.sc.spring.UserDao"></bean>
<!-- byName是通过id进行查找 -->
<bean id="userService" class="com.sc.spring.UserService" autowire="byName"></bean>
	
<bean class="com.sc.spring.UserController" autowire="byName"></bean>
```
#### 11.2 基于注解的自动装配
```
通过以下注解进行自动装配

1. @Autowired 注解 : 根据类型装配
2. @Resource  注解 : 根据名称装配
```
---
## 第三章 SpEL表达式

### 第1节 SpEL表达式简介
```
Spring Expression Language,Spring表达式语言,简称SpEL.和JSP页面上的EL表达式一样,SpEL根据JavaBean风格的getXxx()、setXxx()方法定义的属性访问对象图,完全符合我们熟悉的操作习惯
```
### 第2节 SpEL基本语法
```
SpEL语法格式#{…}，所有在大框号中的字符都将被认为是SpEL表达式
```
### 第3节 SpEL的使用

#### 3.1 基本数据类型
```
整数:<property name="count" value="#{5}"/>
小数:<property name="frequency" value="#{89.7}"/>
科学计数法:<property name="capacity" value="#{1e4}"/>
```
#### 3.2 String数据类型
```
String类型的字面量可以使用单引号或者双引号作为字符串的定界符号

<property name="name" value="#{'sc'}"/>
或
<property name='name' value='#{"sc"}'/>
```
#### 3.3 引用其他bean
* Java代码
```
public class Department {

	private Integer deptId;
	private String deptName;
	
	public String getDeptName(){
	    return deptName;
	}
	
	public static String getInfo(){
	    return deptName;
	}
}

public class Employee {

	private Integer empId;
	private String empName;

	private Department department;
	
	private String deptName;
	
}
```
* XML配置
```
bean id="dept" class="com.sc.entity.Department"></bean>
<bean id="emp1" class="com.sc.entity.Employee">
	<!-- 引用其他bean -->
	<property name="department" value="#{dept}"></property>
</bean>
```
#### 3.4 引用其他bean的属性
```
<bean id="emp2" class="com.sc.entity.Employee">
	<!-- 引用其他bean -->
	<property name="department" value="#{dept}"></property>
	<!-- 引入其他bean的属性值 -->
	<property name="deptName" value="#{dept.deptName}"></property>
</bean>
```
#### 3.5 调用非静态方法
```
bean id="dept" class="com.sc.entity.Department"></bean>

<bean id="emp3" class="com.sc.entity.Employee">
	<!-- 调用非静态方法的给属性赋值 -->
	<property name="deptName" value="#{dept.getDeptName(方法入参)}"></property>
</bean>
```
#### 3.6 调用静态方法
```
<bean id="emp4" class="com.sc.entity.Employee">
	<!-- 
		调用静态方法的给属性赋值
		T(): 表示 Department是外部类非Spring定义的bean所以使用表达式T()
		T表达式里面的类使用全类名进行表示
	-->
	<property name="deptName" value="#{T(com.sc.entity.Department).getInfo()}"></property>
</bean>
```
#### 3.7 运算符
```
① 算术运算符：+、-、*、/、%、^
② 字符串连接：+
③ 比较运算符：<、>、==、<=、>=、lt、gt、eq、le、ge
④ 逻辑运算符：and, or, not, |
⑤ 三目运算符：判断条件?判断结果为true时的取值:判断结果为false时的取值
```
---
## 第四章 Spring的AOP

#### 第1节 AOP简介

* AOP的概念
```
AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率
```
* AOP的本质
```
代码重用的一种解决方案

传统的解决代码重用的方式：

1. 将重复的代码抽取成一个方法
2. 将重复的代码抽取到一个基类中
3. 动态代理的方式
```
#### 第2节 动态代理
```
public class ProxyMathUtil {
	//目标代理对象
	private Object target;

	public ProxyMathUtil(Object target) {
		this.target = target;
	}
	
	public Object getProxy() {
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object object = method.invoke(target, args);
				return object;
			}
		});
	}
}
```
#### 第3节 AOP术语介绍
```
1. 横切关注点: 从每个方法中抽取出来的同一类非核心业务
2. 切面 Aspect: 封装横切关注点信息的类
3. 通知 Advice: 切面要完成的具体工作
4. 目标 Target: 被通知的对象
5. 代理 Proxy: 向目标对象应用通知之后创建的代理对象
6. 连接点 Joinpoint: 横切关注点在程序代码中的具体体现对应程序执行的某个特定位置
7. 切入点 pointcut： 查询具体连接点的表达式
8. 切入点的格式: execution([权限修饰符][返回值类型][简单类名/全类名]方法名)

切入点格式举例
精确匹配: execution(public int com.sc.aop.MathCaculator.add(int,int))
通配符匹配： execution(* *.*(..))
```
#### 第4节 AOP实现方式

##### 4.1 注解方式
* 注解方式实现步骤
```
1. 创建被代理的目标类,并加入到IOC容器中
2. 创建切面类,并加入到IOC容器中，同时给切面类添加@Aspect注解，表明当前类是一个切面类
3. 在切面类中声明通知方法
 - 前置通知: @Before
 - 返回通知: @AfterReturning
 - 后置通知: @After
 - 异常通知: @AfterThrowing
 - 环绕通知: @Around
```
* 代码实现
* * 创建目标接口
```
public interface MathCalculator {

	int add(int x,int y);
	
	int sub(int x,int y);
	
	int mul(int x,int y);
	
	int div(int x,int y);
}
```
* * 创建目标接口实现类
```
@Service
public class MathCalculatorImpl implements MathCalculator {

	@Override
	public int add(int x, int y) {
		return x+y;
	}

	@Override
	public int sub(int x, int y) {
		
		return x-y;
	}

	@Override
	public int mul(int x, int y) {
		
		return x*y;
	}

	@Override
	public int div(int x, int y) {
		
		return x/y;
	}

}
```
* * 创建切面类/在切面类中定义通知方法
```
@Aspect
@Component
public class AopAspect {
	
	//前置通知
	@Before("execution(public int com.sc.aop.MathCaculator.*(..))")
	public void methodStart(JoinPoint joinPoint) {
		System.out.println("前置通知");
	}
	
	//返回通知
	@AfterReturning(pointcut="execution(public int com.sc.aop.MathCaculator.*(..))",returning="abc")
	public void methodReturn(Object abc) {
		System.out.println("返回通知");
	}
	
	//异常通知
	@AfterThrowing(pointcut="execution(public int com.sc.aop.MathCaculator.*(..))",throwing="e")
	public void methodThrow(Exception e) {
		System.out.println("异常通知");
	}
	
	//后置通知
	@After("execution(public int com.sc.aop.MathCaculator.*(..))")
	public void methodAfter() {
		System.out.println("后置通知");
	}
	
	//环绕通知
	@Around("execution(public int com.sc.aop.MathCaculator.*(..))")
	public Object methodAround(ProceedingJoinPoint joinPoint) {
		Object r=0;
		System.out.println("环绕-->前置通知");
		try {
			//joinPoint入参就是要执行的方法
			//proceed就是执行入参也就是执行传进来的方法
			r = joinPoint.proceed();
			System.out.println("环绕-->返回通知");
		} catch (Throwable e) {
			System.out.println("环绕-->异常通知");
		}finally {
			System.out.println("环绕-->后置通知");
		}
		return r;
	}
}
```
* * xml中开启AOP的注解功能
```
<!-- 扫描需要被扫描的包,实例化对象 -->
<context:component-scan base-package="com.sc.aop"/>

<!-- 开启AOP注解功能 -->
<aop:aspectj-autoproxy proxy-target-class="true"/>
```
* * JoinPoint 方法入参获取方法信息
```
//获取方法名
String methodName = joinPoint.getSignature().getName();
```
* * 方法返回值获取
```
//returning：设置返回值参数名
@AfterReturning(pointcut="execution(public int com.sc.aop.MathCaculator.*(..))",returning="abc")
public void methodReturn(Object abc) {
	System.out.println("返回通知");
}
```
* * 接收捕获到的异常对象
```
//throwing：设置异常参数名
@AfterThrowing(pointcut="execution(public int com.sc.aop.MathCaculator.*(..))",throwing="e")
public void methodThrow(Exception e) {
	System.out.println("异常通知");
}
```
* * 统一声明切入点
```
@Pointcut("execution(public int com.sc.aop.MathCaculator.*(..))")
public void log(){
    
}

//前置通知
@Before("log()")
public void methodStart(JoinPoint joinPoint) {
	System.out.println("前置通知");
}
```
* * 优先级
```
给切面类设置优先级,当一个方法被多个切面切的时候，判断先走哪个切面。设置order注解。
@Order(10)
越小优先级越高
```

##### 4.2 XML配置方式
```
<!-- 将目标代理对象加入到IOC容器中 -->
<bean class="com.sc.aop.MathCaculatorImpl"></bean>

<!-- 注册切面类 -->
<bean id="myAspect" class="com.sc.aop.AopAspect"></bean>

<aop:config>
	<!-- 配置切入点 -->
	<aop:pointcut expression="execution(* *.*(..))" id="mypointcut"/>
	<!-- 配置切面 -->
	<aop:aspect ref="myAspect">
		<!-- 前置通知 -->
		<aop:before method="methodStart" pointcut-ref="mypointcut"/>
		<!-- 返回通知 -->
		<aop:after-returning method="methodReturn" pointcut-ref="mypointcut" returning="abc"/>
		<!-- 异常通知 -->
		<aop:after-throwing method="methodThrow" pointcut-ref="mypointcut" throwing="e"/>
		<!-- 后置通知 -->
		<aop:after method="methodAfter" pointcut-ref="mypointcut"/>
		<!-- 环绕通知 -->
		<aop:around method="methodAround" pointcut-ref="mypointcut"/>
	</aop:aspect>
</aop:config>
```
---
## 第五章 Spring的事务管理

### 第1节 事物介绍

* 什么是事务
```
事务是逻辑上的一组操作,这组操作要么全部成功，有一个失败就全部失败
```
* 事务的特性
```
1. 原子性
2. 一致性
3. 隔离性
4. 永久性

ACID
```
* 事务的隔离级别
```
1. 读已提交-> READ_COMMITTED -> 可避免脏读的发生
2. 读未提交-> READ_UNCOMMITTED -> 最低级别，任何情况都无法保证
3. 可重复读-> REPEATABLE_READ -> 可避免脏读、不可重复读的发生
4. 串行化-> SERIALIZABLE -> 可避免脏读、不可重复读、幻读的发生
```
* 没有隔离级别产生的问题
* * 脏读
```
一个事物读取的数据是另一个事物还没有来得及提交的数据，会造成脏读的问题
```
* * 幻读
```
幻读是指，如果我正在对一个数据库表进行全部的修改某几个属性的值，同时还有其他事物向数据库表中添加数据，当修改完查询的时候发现出现了有的字段属性值没有修改成功，这时候就产生幻读问题.
```
* * 不可重复读
```
不可重复读是指在一个事务当中通过相同条件多次查询相同的数据，返回的结果集不相同。造成的原因是有其他事物正在不断的修改这些数据.
```
* MYSQL的事务操作
```
1. mysql默认的隔离级别 : REPEATABLE_READ
2. oracle默认的隔离级别 : READ_COMMITTED
3. 查询当前mysql事务 : select @@tx_isolation;
4. 修改当前mysql事务 : set tx_isolation='REPEATABLE-READ';
```
### 第2节 Spring的事务API介绍

* PlatformTransactionManager : 平台事务管理器
```
对ORM框架的事物进行管理
1. JDBC/Mybatis : DataSourceTransactionManager
2. hibernate ：Hibernate3TransactionManager/Hibernate4TransactionManager/Hibernate5TransactionManager
```
* TransactionDefinition: 事务定义信息(隔离级别,传播行为,超时,只读)
* * 隔离级别
```
ISOLATION_DEFAULT -> 使用数据库的隔离级别
ISOLATION_READ_COMMITTED
ISOLATION_READ_UNCOMMITTED
ISOLATION_REPEATABLE_READ
ISOLATION_SERIALIZABLE
```
* * 传播行为
```
PROPAGATION_REQUIRED -> 支持当前事务如果不存在就重新创建一个
PROPAGATION_SUPPORTS -> 支持当前事务，如果不存在就不使用事务
PROPAGATION_MANDATORY -> 支持当前事务,如果不存在就抛出异常
PROPAGATION_REQUIRES_NEW -> 如果有事务存在,就挂起当前事务，创建一个新事务
PROPAGATION_NOT_SUPPORTED -> 以非事务方式运行,如果有事务存在就挂起当前事务
PROPAGATION_NEVER -> 以非事务方式运行,如果有事务就抛出异常
PROPAGATION_NESTED -> 如果当前事务存在就嵌套事务运行
```
* * 超时时间
```
TIMEOUT_DEFAULT
```
* TransactionStatus： 事务具体运行状态

### 第3节 Spring的事务实现

#### 3.1 环境准备
* 创建数据库
```
create database spring default character set utf8;
```
* 创建数据库表
```
CREATE TABLE account(
    `id` int  not null primary key auto_increment,
    `name` varchar(10) not null comment '账户',
    `money` double default 0.0
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT = '账户表';

INSERT INTO account(name,money) VALUES("li4",1000),("z3",1000);
```
* 创建Java工程添加jar包或者依赖
```
c3p0-0.9.1.2.jar
com.springsource.net.sf.cglib-2.2.0.jar
com.springsource.org.aopalliance-1.0.0.jar
com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
commons-logging-1.2.jar
mysql-connector-java-5.1.44.jar
spring-aop-4.3.10.RELEASE.jar
spring-aspects-4.3.10.RELEASE.jar
spring-beans-4.3.10.RELEASE.jar
spring-context-4.3.10.RELEASE.jar
spring-context-support-4.3.10.RELEASE.jar
spring-core-4.3.10.RELEASE.jar
spring-expression-4.3.10.RELEASE.jar
spring-jdbc-4.3.10.RELEASE.jar
spring-orm-4.3.10.RELEASE.jar
spring-oxm-4.3.10.RELEASE.jar
spring-test-4.3.10.RELEASE.jar
spring-tx-4.3.10.RELEASE.jar
```
* 创建外部属性资源文件(数据库配置信息)
```
//db.properties

jdbc.username=root
jdbc.password=root
jdbc.url=jdbc:mysql://127.0.0.1:3306/spring?useUnicode=true&characterEncoding=utf-8
jdbc.driverClass=com.mysql.jdbc.Driver
```
* 创建Dao层接口
```
public interface AccountDao {

	/**
	 * 转入金额
	 * @param in 
	 * @param money
	 */
	void inMoney(String in,double money);
	
	/**
	 * 转出金额
	 * @param out
	 * @param money
	 */
	void outMoney(String out,double money);
}
```
* 创建Dao层接口实现类
```
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	@Override
	public void inMoney(String in, double money) {
		
		String sql="UPDATE account set money=money-? WHERE name=?";
		
		this.getJdbcTemplate().update(sql, money, in);
	}

	@Override
	public void outMoney(String out, double money) {

		String sql="UPDATE account set money=money+? WHERE name=?";
		
		this.getJdbcTemplate().update(sql, money, out);
	}

}
```
* 创建service层接口
```
public interface AccountService {
	/*转账*/
	void transfer(String in,String out,double money);
}
```
* 创建service层接口实现类
```
public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String in, String out, double money) {
		//转出
		accountDao.outMoney(in, money);
		//int i = 1/0;
		//转入
		accountDao.inMoney(out, money);
		
	}

}
```
* 编写spring的核心配置文件applicationContext.xml
```
<!-- 导入外部属性资源文件 -->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="${jdbc.username}"/>
	<property name="password" value="${jdbc.password}"/>
	<property name="jdbcUrl" value="${jdbc.url}"/>
	<property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
<!-- 注册AccountDao -->
<bean id="accountDao" class="com.sc.dao.AccountDaoImpl">
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 注册AccountService -->
<bean id="accountService" class="com.sc.service.AccountServiceImpl">
	<property name="accountDao" ref="accountDao"></property>
</bean>
```
* 测试环境是否搭建成功
```
@Test
public void test() {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	AccountService accountService = (AccountService) ioc.getBean("accountService");
	//李四转账给张三500元
	accountService.transfer("zs", "ls", 500);
}
```
#### 3.2 编程式事务实现
```
1. Spring为了简化我们的开发,提供了TransactionTemplate这样的一个事务管理模板，所以编程式事务管理采用此模板进行管理。
2. 在AccountService中使用TransactionTemplate
3. TransactionTemplate依赖DataSourceTransactionManager
4. DataSourceTransactionManager依赖DataSource
```
* Java类
```
public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	@Override
	public void transfer(String in, String out, double money) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				//转出
				accountDao.outMoney(in, money);
				int i = 1/0;
				//转入
				accountDao.inMoney(out, money);
			}
		});
	}
}
```
* XML配置
```
<!-- 导入外部属性资源文件 -->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="${jdbc.username}"/>
	<property name="password" value="${jdbc.password}"/>
	<property name="jdbcUrl" value="${jdbc.url}"/>
	<property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
<!-- 注册AccountDao -->
<bean id="accountDao" class="com.sc.dao.AccountDaoImpl">
	<!-- 注入数据源,用于创建JdbcTemplate -->
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 注册AccountService -->
<bean id="accountService" class="com.sc.service.AccountServiceImpl">
	<property name="accountDao" ref="accountDao"></property>
	<!-- 设置事务管理模板 -->
	<property name="transactionTemplate" ref="transactionTemplate"></property>
</bean>

<!-- 配置平台事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 定义事务管理模板 -->
<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
	<property name="transactionManager" ref="transactionManager"></property>
</bean>
```
#### 3.3 声明式事物实现

* 方式一(AOP传统方式)
```
1. 配置平台事务管理器
2. 配置事务代理(代理service层)
    ① spring传统aop方式
    ② TransactionProxyFactoryBean
        * 配置目标对象
        * 注入事务管理器
        * 配置事务的一些属性:transactionAttributes（配置方式见源码）
```
```
XML配置

<!-- 导入外部属性资源文件 -->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="${jdbc.username}"/>
	<property name="password" value="${jdbc.password}"/>
	<property name="jdbcUrl" value="${jdbc.url}"/>
	<property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
<!-- 注册AccountDao -->
<bean id="accountDao" class="com.sc.dao.AccountDaoImpl">
	<!-- 注入数据源,用于创建JdbcTemplate -->
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 注册AccountService -->
<bean id="accountService" class="com.sc.service.AccountServiceImpl">
	<property name="accountDao" ref="accountDao"></property>
</bean>

<!-- 配置平台事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>

<!-- 配置业务层事务代理 -->
<bean id="accountServiceProxy" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
	<!-- 配置目标代理对象 -->
	<property name="target" ref="accountService"></property>
	<!-- 注入平台事务管理器 -->
	<property name="transactionManager" ref="transactionManager"></property>
	<!-- 配置事务的属性 -->
	<property name="transactionAttributes">
		<props>
			<!-- 
				key: 方法名 可以精确匹配,也可以使用*进行通配  save* 或者  *
				PROPAGATION : 事务的传播行为
				ISOLATION   : 事务的隔离级别
				readOnly    : 只读
				-Exception  : 发生哪些异常回滚事务
				+Exception  : 发生哪些异常不回滚
			 -->
			 <!-- <prop key="transfer">PROPAGATION_REQUIRED,readOnly,+Exception</prop> -->
			<prop key="transfer">PROPAGATION_REQUIRED</prop>
		</props>
	</property>
</bean>
```
```
Test测试


@Test
public void test() {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	//获取IOC容器中的代理增强类,不需要再获取AccountService类,因为他已经被accountServiceProxy代理。
	AccountService accountService = (AccountService) ioc.getBean("accountServiceProxy");
	//李四转账给张三500元
	accountService.transfer("zs", "ls", 500);
}
```
* 方式二(AspectJ)
```
1. 导入aspectj的包/以及spring整合aspectj的包
2. 配置平台事务管理器
3. 配置事务的通知 tx:advice
4. 配置AOP的切面
    ① 配置切入点
    ② 配置切面
```
```
XML配置

<!-- 导入外部属性资源文件 -->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="user" value="${jdbc.username}"/>
	<property name="password" value="${jdbc.password}"/>
	<property name="jdbcUrl" value="${jdbc.url}"/>
	<property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
<!-- 注册AccountDao -->
<bean id="accountDao" class="com.sc.dao.AccountDaoImpl">
	<!-- 注入数据源,用于创建JdbcTemplate -->
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 注册AccountService -->
<bean id="accountService" class="com.sc.service.AccountServiceImpl">
	<property name="accountDao" ref="accountDao"></property>
</bean>

<!-- 配置平台事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>
<!-- 配置事务的通知 -->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
	<tx:attributes>
		<tx:method name="transfer" propagation="REQUIRED"/>
	</tx:attributes>
</tx:advice>
<!-- 配置事务的切面 -->
<aop:config>
	<!-- 
		配置切点
		expression: 表达式  +: 代表及其子类
	 -->
	<aop:pointcut expression="execution(* com.sc.service.AccountService+.*(..))" id="mypointcut"/>
	<!-- 配置切面 -->
	<aop:advisor advice-ref="txAdvice" pointcut-ref="mypointcut"/>
</aop:config>
```
* 方式三(基于注解的事务管理)
```
配置平台管理器
开启基于注解的声明式事务管理
 - <tx:annotation-driven transaction-manager="transactionManager"/>
在类上或者方法上添加@Transactional注解
```
---
## 第六章 Spring的测试框架
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bean.xml"})
```
## 第七章 Spring的多配置文件管理
```
Spring允许通过<import>将多个配置文件引入到一个文件中，进行配置文件的集成
import元素的resource属性支持Spring的标准的路径资源
```