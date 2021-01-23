

## 第一章 Quartz简介

### 第一节 Quartz是什么?
```
Quartz [kwɔːts]是一个完全由Java编写的开源的作业调度框架
```
### 第二节 Quartz可以用来做什么?
* 比如说买火车票下单之30分钟之后,查看是否付款
* 付款完成之后,在乘车日期的时候是否乘车
* 或者每个月1号扣房贷
* 每个月20号自动还信用卡
* 想定时在某一个时间,在到了那个时间的时候去执行某个任务

## 第二章 快速入门

### 第一节 常用API介绍

* Job接口
```
首先我们需要定义实现一个定时功能的接口,我们可以称之为Task(或Job)如定时发送邮件的task(Job)重启机器的task(Job)优惠券到期发送短信提醒的task(Job)
```
<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEBdd6c0c98606d3c2981e4f46b2aba4b34?method=download&shareKey=6f8b3f562867bb3d86d622920cdf9569">
</center>

* Trigger [ˈtrɪɡə(r)] 触发器
```
有了任务之后,还需要一个能够实现触发任务去执行的触发器,触发器Trigger最基本的功能是指定Job的执行时间,执行间隔,运行次数等
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEBe2a3822807a8b772bc81c2f639595860?method=download&shareKey=6668920cb8f168264deb51a4eb441cb5">
</center>

* Scheduler [ˈʃɛdjuːlə]调度器
```
有了Job和Trigger后，怎么样将两者结合起来呢?即怎样指定Trigger去执行指定的Job呢?这时需要一个Schedule,来负责这个功能的实现
```
<center>
<img src="https://note.youdao.com/yws/api/personal/file/WEB3f157721a41f8c6cc37182880e194fc4?method=download&shareKey=221ff38729e0064e2ae488542730ddd6">
</center>

```
上面三个是Quartz的基本组成部分
1. 调度器: Scheduler
2. 任务:   Job,以及JobDetail
3. 触发器: Trigger,包括SimpleTrigger和CronTrigger
```
### 第二节 简单使用（API）

#### 2.1 添加依赖
```
<!-- https://mvnrepository.com/artifact/org.quartz-scheduler/quartz -->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.0</version>
</dependency>
```
#### 2.2 简单代码

* 自定义一个任务
```
/**
 * @Author 枫桥夜泊1990 
 * @BLOG   https://hd1611756908.github.io/
 * @BSITE  https://space.bilibili.com/514155929/
 * 创建一个被执行的任务
 */
public class HelloJob implements Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("任务被执行...."+sdf.format(new Date()));
	}
}
```
* 启动任务,并设置每5秒执行一次
```
public static void main(String[] args) throws SchedulerException{
	//1.创建自定义的任务(job)对象
	JobDetail job = JobBuilder.newJob(HelloJob.class).build();
	//2.创建触发器(Trigger)对象,并设置触发规则,每格五秒触发一次Job
	Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
	//3.创建任务调度(Scheduler)对象
	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	//4.将任务和触发器联系到一起
	scheduler.scheduleJob(job, trigger);
	//5.启动任务
	scheduler.start();
}
```
#### 2.3 核心对象介绍

* JobDetail
```
JobDetail内部指定了Job的更详细的属性,比如说任务名称,任务群组等
```
* Trigger
```
一个类，用于描述触发Job执行的时间规则
```
* * SimpleTrigger
```
一次或者固定时间间隔周期的触发规则
```
* * CronTrigger
```
通过cron表达式描述出更为复杂的触发规则
```
* Scheduler
```
1. 代表一个Quartz的独立运行容器
2. Trigger和JobDetail被注册到Scheduler中
3. 二者在Scheduler中拥有各自的名称（name）和组名（group）
4. Trigger和JobDetail的名称和组名的组合必须唯一，但是Trigger的名称和组名的组合可以和JobDetail的相同，因为它们类型不同
5. 一个Job可以绑定多个Trigger
```
* Calendar
```
Quartz提供的日历类。一个Trigger可以和多个Calendar关联，以此来排除一些特殊的日期
```
---
### 第三节 触发器API的使用

#### 3.1 SimpleTrigger对象
```
public static void main(String[] args) throws SchedulerException {
		
	//1.创建自定义的任务(job)对象
	JobDetail job = JobBuilder.newJob(HelloJob.class).build();
	//2.创建触发器 设置任务启动5秒后开始执行job,一秒执行一次，执行5秒
	Date startDate = new Date();//开始时间
	startDate.setTime(startDate.getTime() + 2000);//设置5秒之后在执行
	Date endDate = new Date();//结束时间
	endDate.setTime(startDate.getTime() + 5000);//结束时间推迟5秒
	/**
	 * withIdentity               : 给任务添加名称和群组方便统一管理,比如可以设置多个触发器名字不同但是群组相同,因为一个任务可以绑定多个触发器
	 *  - name                    : 设置任务名称
	 *  - group                   : 设置群组名称
	 * startNow                   : 立即生效
	 * startAt                    : 触发器开始时间
	 * endAt                      : 触发器结束时间
	 * withSchedule               : 设置规则
	 * withIntervalInMilliseconds : 每隔1000毫秒执行一次Job(HelloJob);还可以使用withIntervalInSeconds方法单位是秒
	 * repeatForever              : 循环执行
	 */
	Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1").
	startNow().
	startAt(startDate).
	endAt(endDate).
	withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).repeatForever()).
	build();
	//3.创建任务调度(Scheduler)对象
	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	//4.将任务和触发器联系到一起
	scheduler.scheduleJob(job, trigger);
	//5.启动任务
	scheduler.start();
}
```
#### 3.2 CronTrigger对象

##### 3.2.1 CronTrigger对象介绍
```
1. CronTrigger功能非常强大
2. 是基于日历的作业调度,而SimpleTrigger是精准指定间隔,所以相比SimpleTrigger,CroTrigger更加常用
3. CroTrigger是基于Cron表达式的
```
##### 3.2.2 CronTrigger对象使用
```
public static void main(String[] args) throws SchedulerException {
		
	//1.创建自定义的任务(job)对象
	JobDetail job = JobBuilder.newJob(HelloJob.class).build();
	//2.创建触发器,创建一个每天下午16时54分开始执行的触发器
	CronTrigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("job01","group01")
			.startNow()
			.withSchedule(CronScheduleBuilder.cronSchedule("0 54 16 * * ?"))
			.build();
	//3.创建任务调度(Scheduler)对象
	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	//4.将任务和触发器联系到一起
	scheduler.scheduleJob(job, trigger);
	//5.启动任务
	scheduler.start();
}
```
##### 3.2.3 Cron表达式
* Cron表达式的语法格式
```
Cron表达式是由7个子表达式组成的字符串,其中"年" 是可选的，可以不指定,格式如下:
[秒] [分] [小时] [日] [月] [周] [年]

例如1: 
表达式:  * 30 10 ? * 1/5 *    --> (从后向前解释) --> [指定年份]的[周一到周五][指定月][不指定日][上午10时][30分][指定秒]
```
* 表达式的每个位置涉及到的符号的取值范围

位置 | 时间域名 | 允许值 | 允许的特殊字符
---|---|---|---
1 | 秒 | 0-59 | , - * /
2 | 分钟  | 0-59 | , - * /
3 | 小时 | 0-23 | , - * /
4 | 日期 | 1-31 | , - * ? / L W C
5 | 月份 | 1-12 | , - * /
6 | 星期 | 1-7 | , - * ? / L C #
7 | 年(可选) | 空值1970-2099 | , - * /

---
> 其中特殊字符的含义如下: 


符号名称 | 备注
---|---
星号（*） | 可用在所有字段中，表示对应时间域的每一个时刻。例如，*在分钟字段时，表示“每分钟”
问号（?） | 该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于点位符
减号（-） | 表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
逗号（,） | 表示一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五
斜杠（/） | x/y表达一个等步长序列，x为起始值，y为增量步长值。如在分钟字段中使用0/15，则表示为0,15,30和45秒，而5/15在分钟字段中表示5,20,35,50，你也可以使用*/y，它等同于0/y
L | 该字符只在日期和星期字段中使用，代表“Last”的意思，但它在两个字段中意思不同。L在日期字段中，表示这个月份的最后一天，如一月的31号，非闰年二月的28号；如果L用在星期中，则表示一个星期的最后一天（也就是星期六，国外星期第一天为周天）。但是，如果L出现在星期字段里，而且在前面有一个数值X，则表示“这个月的最后一个星期X-1”，例如，6L表示该月的最后一个星期五，5L表示该月的最后一个星期四，以此类推
W | 该字符只能出现在日期字段里，是对前导日期的修饰，表示离该日期最近的工作日。例如15W表示离该月15号最近的工作日，如果该月15号是星期六，则匹配14号星期五；如果15日是星期日，则匹配16号星期一；如果15号是星期二，那结果就是15号星期二。但必须注意关联的匹配日期不能够跨月，如你指定1W，如果1号是星期六，结果匹配的是3号星期一，而非上个月最后的那天
LW组合 | 在日期字段可以组合使用LW，它的意思是当月的最后一个工作日
井号（#） | 该字符只能在星期字段中使用，表示当月某个工作日。如6#3表示当月的第三个星期五（6表示星期五，#3表示当前的第三个），而4#5表示当月的第五个星期三，假设当月没有第五个星期三，忽略不触发
C | 该字符只在日期和星期字段中使用，代表“Calendar”的意思。它的意思是计划所关联的日期，如果日期没有被关联，则相当于日历中所有日期。例如5C在日期字段中就相当于日历5日以后的第一天。1C在星期字段中相当于星期日后的第一天

---
* 常见的Cron表达式(大小写不敏感)

表示式 | 说明
---|---
"0 0 12 * * ?"    | 每天12点运行
"0 15 10 ? * *"   | 每天10:15运行
"0 15 10 * * ?"   | 每天10:15运行
"0 15 10 * * ? *" | 每天10:15运行
"0 15 10 * * ? 2008" | 在2008年的每天10:15运行
"0 * 14 * * ?" | 每天14点到15点之间每分钟运行一次，开始于14:00，结束于14:59
"0 0/5 14 * * ?" | 每天14点到15点每5分钟运行一次，开始于14:00，结束于14:55
"0 0/5 14,18 * * ?" | 每天14点到15点每5分钟运行一次，此外每天18点到19点每5钟也运行一次
"0 0-5 14 * * ?" | 每天14:00点到14:05，每分钟运行一次
"0 10,44 14 ? 3 WED" | 3月每周三的14:10分到14:44，每分钟运行一次
"0 15 10 ? * MON-FRI" | 每周一，二，三，四，五的10:15分运行
"0 15 10 15 * ?" | 每月15日10:15分运行
"0 15 10 L * ?" | 每月最后一天10:15分运行
"0 15 10 ? * 6L" | 每月最后一个星期五10:15分运行
"0 15 10 ? * 6L 2014-2016" | 在2014,2015,2016年每个月的最后一个星期五的10:15分运行
"0 15 10 ? * 6#3" | 每月第三个星期五的10:15分运行

* 在线生成Cron表达式的工具地址
```
旧版地址 : https://cron.qqe2.com/
新版地址 : https://qqe2.com/cron
```

---
### 第四节 Spring整合Quartz 

#### Spring整合Quartz依赖
```
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context-support</artifactId>
	<version>4.3.26.RELEASE</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-tx</artifactId>
	<version>4.3.26.RELEASE</version>
</dependency>
<dependency>
	<groupId>org.quartz-scheduler</groupId>
	<artifactId>quartz</artifactId>
	<version>>2.2.3</version>
</dependency>
```
#### 4.1 方式一(JobDetailFactoryBean)
* 创建任务类(实现Job接口)
```
/**
 * @Author 枫桥夜泊1990 
 * @BLOG   https://hd1611756908.github.io/
 * @BSITE  https://space.bilibili.com/514155929/
 * 创建自定义任务类
 */
public class MyJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("我的任务被执行了..."+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
```
* 创建Spring配置文件(spring.xml)
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- 创建任务 -->
	<bean id="job" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
		<!-- job名称 -->
		<property name="name" value="job1"></property>
		<!-- job分组 -->
		<property name="group" value="group1"></property>
		<!-- 指定具体的Job类 -->
		<property name="jobClass" value="com.qianfeng.qs.MyJob"></property>
	</bean>
	<!-- 创建触发器 -->
	<bean id="trigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<!-- 设置触发器名称 -->
		<property name="name" value="trigger1"></property>
		<!-- 设置触发器群组 -->
		<property name="group" value="trigger_group1"></property>
		<!-- 设置触发的任务 -->
		<property name="jobDetail" ref="job"></property>
		<!-- 设置Cron表达式,每隔5s运行一次 -->
		<property name="cronExpression" value="*/5 * * * * ?"></property>
	</bean>
	<!-- 创建任务调度器 -->
	<bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="triggers">
			<list>
				<ref bean="trigger"/>
			</list>
		</property>
	</bean>
</beans>
```
* 测试
```
public static void main(String[] args) throws SchedulerException, InterruptedException {
    //如果只是启动任务,那么只需要创建IOC容器即可
	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
	// 获取调度器
	StdScheduler scheduler = (StdScheduler) ioc.getBean("scheduler");
	// 获取任务
	JobKey jobKey = JobKey.jobKey("job1", "group1");
	// 任务的暂停(3秒之后暂停3秒)
	Thread.sleep(3000);
	scheduler.pauseJob(jobKey);
	// 任务的恢复(暂停3秒后恢复)
	Thread.sleep(3000);
	scheduler.resumeJob(jobKey);
	// 任务的删除(3秒之后删除)
	Thread.sleep(3000);
	scheduler.deleteJob(jobKey);
}
```

#### 4.2 方式二(MethodInvokingJobDetailFactoryBean)

* 创建任务类
```
/**
 * @Author 枫桥夜泊1990 
 * @BLOG   https://hd1611756908.github.io/
 * @BSITE  https://space.bilibili.com/514155929/
 * 创建自定义任务类
 */
public class MyJob{
	/**
	 * 任务方法1(此方法用于测试SimpleTrigger触发器)
	 */
	public void task1() {
		System.out.println("SimpleTrigger生效了..."+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	/**
	 * 任务方法2(此方法用于测试CronTrigger触发器)
	 */
	public void task2() {
		System.out.println("CronTrigger生效了..."+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
```
* 创建Spring配置文件(spring.xml)
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- 将自定义任务类加入到IOC容器中 -->
	<bean id="myJob" class="com.qianfeng.qs.MyJob"></bean>
	
	<!-- 配置固定时长的任务类,并注入被任务调度类和任务方法 -->
	<bean id="jobDetail1" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<!-- 注册被调度类 -->
		<property name="targetObject" ref="myJob"></property>
		<!-- 注册被调度方法 -->
		<property name="targetMethod" value="task1"></property>
		<!-- 设置任务名称 -->
		<property name="name" value="job1"></property>
		<!-- 设置任务群组名称 -->
		<property name="group" value="group1"></property>
	</bean>
	
	<!-- 配置cron表达式的任务类,并注入被任务调度类和任务方法 -->
	<bean id="jobDetail2" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<!-- 注册被调度类 -->
		<property name="targetObject" ref="myJob"></property>
		<!-- 注册被调度方法 -->
		<property name="targetMethod" value="task2"></property>
		<!-- 设置任务名称 -->
		<property name="name" value="job2"></property>
		<!-- 设置任务群组名称 -->
		<property name="group" value="group1"></property>
	</bean>
	
	<!-- 配置固定时长的触发器并配置任务的时间 -->
	<bean id="simpleTrigger" class="org.springframework.scheduling.quartz.SimpleTriggerFactoryBean">
		<!-- 配置此触发器作用的任务类 -->
		<property name="jobDetail" ref="jobDetail1"></property>
		<!-- 指定触发器执行的时间间隔 -->
		<property name="repeatInterval" value="1000"></property>
	</bean>
	
	<!-- 配置cron表达式的触发器并配置任务的规则 -->
	<bean id="cronTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<!-- 配置此触发器作用的任务类 -->
		<property name="jobDetail" ref="jobDetail2"></property>
		<!-- 指定触发器执行的时间间隔 -->
		<property name="cronExpression" value="0/1 * * * * ?"></property>
	</bean>
	
	<!-- 配置调度 -->
	<bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="triggers">
			<list>
				<ref bean="simpleTrigger"/>
				<ref bean="cronTrigger"/>
			</list>
		</property>
	</bean>
	
</beans>

```
* 测试
```
public static void main(String[] args) throws InterruptedException, SchedulerException{
	// 如果只是启动任务,那么只需要创建IOC容器即可
	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
	// 获取调度器
	StdScheduler scheduler = (StdScheduler) ioc.getBean("scheduler");
	// 获取某一个组的所有任务
	GroupMatcher<JobKey> group1 = GroupMatcher.groupEquals("group1");
	// 3秒之后暂停当前组的所有任务
	Thread.sleep(3000);
	scheduler.pauseJobs(group1);
	// 5秒之后恢复当前组的任务
	Thread.sleep(5000);
	scheduler.resumeJobs(group1);
}
```
---
### 第五节  Quartz默认配置文件
* 简介
```
设置quartz的初始化配置
quartz.properties 是Quartz的默认配置,主要使用配置和调度工厂类(StdSchedulerFactory)实例化调度对象(Scheduler)
默认存在于 quartz依赖的org.quartz.Scheduler路径下,如果想修改默认值可以在当前classpath下创建同名文件修改默认值
或者直接配置到spring的xml文件中
```
* xml配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- 创建任务 -->
	<bean id="job" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
		<!-- job名称 -->
		<property name="name" value="job1"></property>
		<!-- job分组 -->
		<property name="group" value="group1"></property>
		<!-- 指定具体的Job类 -->
		<property name="jobClass" value="com.qianfeng.qs.MyJob"></property>
	</bean>
	<!-- 创建触发器 -->
	<bean id="trigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<!-- 设置触发器名称 -->
		<property name="name" value="trigger1"></property>
		<!-- 设置触发器群组 -->
		<property name="group" value="trigger_group1"></property>
		<!-- 设置触发的任务 -->
		<property name="jobDetail" ref="job"></property>
		<!-- 设置Cron表达式,每隔5s运行一次 -->
		<property name="cronExpression" value="*/1 * * * * ?"></property>
	</bean>
	<!-- 创建任务调度器 -->
	<bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="triggers">
			<list>
				<ref bean="trigger"/>
			</list>
		</property>
		<!-- 
			设置quartz的初始化配置
			quartz.properties 是Quartz的默认配置,主要使用配置和调度工厂类(StdSchedulerFactory)实例化调度对象(Scheduler)
			默认存在于 quartz依赖的org.quartz.Scheduler路径下,如果想修改默认值可以在当前classpath下创建同名文件修改默认值
			或者直接配置到spring的xml文件中
		 -->
		<!-- <property name="configLocation" value="classpath:quartz.properties"></property> -->
		<!-- <property name="quartzProperties">
			<props>
				<prop key="org.quartz.scheduler.instanceName">DefaultQuartzScheduler</prop>
			</props>
		</property> -->
	</bean>
</beans>
```
---
### 第六节 Quartz和Spring框架整合解决在任务中不能注入的问题
#### 6.1 Quartz不能注入service或者dao(mapper)层原因
```
这个Job是由quartz实例化出来的,不受Spring的管理,所以就导致注入失败
```
#### 6.2 解决方案
* 如何将非Spring的IOC容器创建的bean加入到IOC容器中?
```
我们的任务是由Spring框架的spring-context-support依赖中SpringBeanJobFactory类的createJobInstance方法创建
但是只是创建了对象却没有加入到IOC容器中,所以要想将任务加入到IOC容器中首先就是需要继承SpringBeanJobFactory类并重写createJobInstance方法
将创建的任务对象手动加入到IOC容器中,那么问题来了,我们创建好了任务之后怎么加入到Spring的IOC容器中呢???

↓↓↓↓↓↓↓↓

AutowireCapableBeanFactory可以实现对已存在实例进行管理,捆绑并填充并不由Spring管理生命周期并已存在的对象.
```
* 重写用于生成job实例的SpringBeanJobFactory
```
/**
 * 重写SpringBeanJobFactory类,用于生成job实例,并且使用AutowireCapableBeanFactory类
 * 的autowireBean方法将新生成的job实例加入到IOC中
 */
public class CustomerJobFactory extends SpringBeanJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //创建job实例
        Object jobInstance = super.createJobInstance(bundle);
        //将job实例通过AutowireCapableBeanFactory的autowireBean方法加入到IOC中进行管理
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
```
* 将重写了的SpringBeanJobFactory加入到IOC中进行实例化
```
<!--设置id方便被引用-->
<bean id="customJobFactory" class="com.qianfeng.task.CustomJobFactory"></bean>
```
* Quartz在Spring中的配置
```
<!--将重写了的SpringBeanJobFactory加入到IOC中进行实例化-->
<bean id="customJobFactory" class="com.qianfeng.task.CustomJobFactory"></bean>

<!--配置quartz-->
<bean id="job" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
    <!-- job名称 -->
    <property name="name" value="job1"></property>
    <!-- job分组 -->
    <property name="group" value="group1"></property>
    <!-- 指定具体的Job类 -->
    <property name="jobClass" value="com.qianfeng.job.HelloJob"></property>
</bean>
<!-- 创建触发器 -->
<bean id="trigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
    <!-- 设置触发器名称 -->
    <property name="name" value="trigger1"></property>
    <!-- 设置触发器群组 -->
    <property name="group" value="trigger_group1"></property>
    <!-- 设置触发的任务 -->
    <property name="jobDetail" ref="job"></property>
    <!-- 设置Cron表达式,每隔5s运行一次 -->
    <property name="cronExpression" value="*/5 * * * * ?"></property>
</bean>
<!-- 创建任务调度器 -->
<bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
    <!--在调度器中重新引入自定义的SpringBeanJobFactory,这时的任务已经被加入到了IOC中-->
    <property name="jobFactory" ref="customJobFactory"></property>
    <property name="triggers">
        <list>
            <ref bean="trigger"/>
        </list>
    </property>
</bean>
```