# Spring Cloud

**微服务解决方案(服务治理中间件）：**

基于Dubbo的微服务解决方案

基于SpringCloud的微服务解决方案

# 1.   SpringCloud简介

尽管Spring Cloud带有“Cloud”的字样，但它并不是云计算解决方案，而**是在Spring Boot基础上构建的，用于快速构建分布式系统的通用模式的工具集**。

> 使用Spring Cloud开发的应用程序非常适合在Docker或者PaaS（例如Cloud Foundry）上部署，所以又叫做云原生应用（Cloud Native Application）。云原生（Cloud Native）可简单理解为面向云环境的软件架构。

Spring Cloud 是一个相对比较新的微服务框架，2016 才推出 1.0 的 Release 版本. 但是其更新特别快，几乎每 1-2 个月就有一次更新，虽然 Spring Cloud 时间最短, 但是相比 Dubbo 等 RPC 框架, **Spring Cloud** **提供的全套的分布式系统解决方案。**

> **Spring Cloud 为开发者提供了在分布式系统（****配置管理，服务发现，熔断，路由，微代理，控制总线，一次性 Token，全居琐，Leader 选举，分布式 Session，集群状态）中快速构建的工具，使用 Spring Cloud 的开发者可以快速的启动服务或构建应用、同时能够快速和云平台资源进行对接。**

**参考网址：**<https://www.processon.com/mindmap/5ef48c4f6376891e81e5d269>



**Spring Cloud集成相关优质项目推荐**

参考网址：<https://springcloud.cc/>



![](https://springcloud.cc/images/spring-cloud-config.png)

Spring Cloud Config
Spring
配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git以及Subversion。

![](https://springcloud.cc/images/spring-cloud-bus.png)

Spring Cloud Bus
Spring
事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。

![](https://springcloud.cc/images/netflix-eureka.jpg)

Eureka
Netflix
云端服务发现，一个基于 REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。

![](https://springcloud.cc/images/netflix-hystrix.png)

Hystrix
Netflix
熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。

![](https://springcloud.cc/images/netflix-zuul.png)

Zuul
Netflix
Zuul 是在云平台上提供动态路由,监控,弹性,安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。

![](https://springcloud.cc/images/netflix-archaius.png)

Archaius
Netflix
配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。

![](https://springcloud.cc/images/hashicorp-consul.png)

Consul
HashiCorp
封装了Consul操作，consul是一个服务发现与配置工具，与Docker容器可以无缝集成。

![](https://springcloud.cc/images/pivotal-cloud-foundry.png)

Spring Cloud for Cloud Foundry
Pivotal
通过Oauth2协议绑定服务到CloudFoundry，CloudFoundry是VMware推出的开源PaaS云平台。

![](https://springcloud.cc/images/spring-cloud-sleuth.png)

Spring Cloud Sleuth
Spring
日志收集工具包，封装了Dapper和log-based追踪以及Zipkin和HTrace操作，为SpringCloud应用实现了一种分布式追踪解决方案。

![](https://springcloud.cc/images/spring-cloud-data-flow.png)

Spring Cloud Data Flow
Pivotal
大数据操作工具，作为Spring XD的替代产品，它是一个混合计算模型，结合了流数据与批量数据的处理方式。

![](https://springcloud.cc/images/spring-cloud-security.png)

Spring Cloud Security
Spring
基于spring security的安全工具包，为你的应用程序添加安全控制。

![](https://springcloud.cc/images/spring-cloud-zookeeper.png)

Spring Cloud Zookeeper
Spring
操作Zookeeper的工具包，用于使用zookeeper方式的服务发现和配置管理。

![](https://springcloud.cc/images/spring-cloud-stream.png)

Spring Cloud Stream
Spring
数据流操作开发包，封装了与Redis,Rabbit、Kafka等发送接收消息。

![](https://springcloud.cc/images/spring-cloud-cli.png)

Spring Cloud CLI
Spring
基于 Spring Boot CLI，可以让你以命令行方式快速建立云组件。

![](https://springcloud.cc/images/netflix-ribbon.png)

Ribbon
Netflix
提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。

![](https://springcloud.cc/images/netflix-turbine.png)

Turbine
Netflix
Turbine是聚合服务器发送事件流数据的一个工具，用来监控集群下hystrix的metrics情况。

![](https://springcloud.cc/images/netflix-feign.png)

Feign
OpenFeign
Feign是一种声明式、模板化的HTTP客户端。

![](https://springcloud.cc/images/spring-cloud-task.png)

Spring Cloud Task
Spring
提供云端计划任务管理、任务调度。

![](https://springcloud.cc/images/spring-cloud-connectors.png)

Spring Cloud Connectors
Spring
便于云端应用程序在各种PaaS平台连接到后端，如：数据库和消息代理服务。

![](https://springcloud.cc/images/spring-cloud-cluster.png)

Spring Cloud Cluster
Spring
提供Leadership选举，如：Zookeeper, Redis, Hazelcast, Consul等常见状态模式的抽象和实现。

![](https://springcloud.cc/images/spring-cloud-starters.png)

Spring Cloud Starters
Pivotal

**总结：SpringCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的几何体，俗称微服务全家桶。**

## 1.1.  Spring Cloud 现状

目前，国内主要使用微服务治理解决方案更多是 Spring Cloud和Dubbo。

但是，微服务架构是一个趋势，而 Spring Cloud 是微服务解决方案的佼佼者， 

```bat
微服务是一种架构模式或者一种架构风格
SpringCloud是一种解决方案（中间件）。
```

## 1.2.  Spring Cloud 优缺点

**优点：**

1. 集大成者，Spring Cloud 包含了微服务架构的方方面面。
2. 轻量级组件，Spring Cloud 整合的组件大多比较轻量级，且都是各自领域的佼佼者。
3. 约定优于配置，基于注解，没有配置文件。
4. 开发简便，Spring Cloud 对各个组件进行了大量的封装，从而简化了开发。

5. 开发灵活，Spring Cloud 的组件都是解耦的，开发人员可以灵活按需选择组件。

**缺点：**

1. 项目结构复杂，每一个组件或者每一个服务都需要创建一个项目。
2. 部署门槛高，项目部署需要配合 Docker 等容器技术进行集群部署。
3. 仅支持JAVA语言。
4. 并不是所有的项目都需要SpringCloud。


## 1.3.  Spring Cloud 和 Dubbo 对比

Dubbo 只是实现了服务治理，而 Spring Cloud 实现了微服务架构的方方面面，服务治理只是其中的一个方面。下面通过一张图对其进行比较

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/wMONw3VJPr9fLqrCBD*MEZ75DlEmfaqGok8J8lBycrQ!/b/dAcBAAAAAAAA&bo=gAIuAQAAAAARB50!&rf=viewer_4)

可以看出，Spring Cloud 比较全面，而 Dubbo 由于只实现了服务治理，需要集成其他模块，需要单独引入，增加了学习成本和集成成本。

## 1.4.  SpringCloud和其它比较

 ![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/PCsn5yehOk3UqBZ5ujXuZH5nHVP69cFj54YkA8CHZRQ!/b/dL4AAAAAAAAA&bo=QwM8AgAAAAADF0w!&rf=viewer_4)

## 1.5.  分布式服务框架未来猜想

**阿里巴巴开源 Spring Cloud Alibaba，加快微服务生态建设**

```
2018年10月31日的凌晨，这个伟大的日子里，Spring Cloud Alibaba正式入驻了Spring Cloud官方孵化器，并在maven中央库发布了第一个版本

Spring Cloud for Alibaba，它是由一些阿里巴巴的开源组件和云产品组成的。这个项目的目的是为了让大家所熟知的 Spring 框架，其优秀的设计模式和抽象理念，以给使用阿里巴巴产品的 Java 开发者带来使用 Spring Boot 和 Spring Cloud 的更多便利。

摘自cloud中国社区：
<https://www.oschina.net/news/101426/spring-cloud-alibaba-open-source>
```

**通知: Spring Cloud Alibaba** **仓库迁移alibaba github oss repository 。 2019-7-8**

<http://www.sohu.com/a/325505636_612370>

```bat
	Dubbo在国内有着非常大的用户群体，但是其周边设施与组件相对来说并不那么完善。很多开发者用户又很希望享受Spring Cloud的生态，因此也会有一些Spring Cloud与Dubbo一起使用的案例与方法出现，但是一直以来大部分Spring Cloud整合Dubbo的使用方案都不完善。直到Spring Cloud Alibaba的出现，才得以解决这样的问题。
	SpringCloud Alibaba是阿里巴巴公司基于Spring Cloud所开发的一套微服务框架集
SpringCloud Alibaba应用于生产实践的一种架构方案，此方案即可享有Dubbo RPC服务通信的优势，也可使用Spring Cloud RESTful接口的通用性。
```



# 2.   版本号

## 1.1. 版本介绍

Spring Cloud 项目目前仍然是快速迭代期，版本变化很快。

<http://spring.io/projects/spring-cloud#learn>

因为Spring Cloud不同其他独立项目，它拥有很多子项目的大项目。所以它是的版本是 版本名+版本号 （如**Hoxton .SR2**）。 
 **版本名：是伦敦的地铁名** 
 **版本号：SR（Service Releases）**是固定的 ,大概意思是**稳定版本**。后面会有一个递增的数字。 

**GA：(Generally Available),表示常用版本**
 所以 Hoxton .SR2就是Finchley的第2个Release版本。

**SNAPSHOT：** 快照版本，随时可能修改

**M： MileStone**，M1表示第1个里程碑版本，一般同时标注PRE，表示预览版版。

**SR：Service Release，SR1表示第1个正式版本，一般同时标注GA,表示常用稳定版本。**



## 1.2.  命名原因：

> ​		从下Angel到上Finchley可以看出，版本的第一个字母是按照A－Z顺序编排的。这些单词是什么含义呢，大概的搜一下可以得出基本都是地名，官方说明是这些版本号的单词来自于英国伦敦的地铁站站名。 
>
> ​		那么为什么要用单词而不是数字类型的版本号呢？  因为Spring Cloud包含了一系列的子系统，Spring Cloud Config，Spring Cloud Netflix，Spring Cloud Bus等，为了防止与这些子系统的版本号混淆，Spring Cloud的版本号全部使用英文单词。 
>
> ​		版本号后面的SRX，X代表一个数字，这个是小版本号，就是在特定的版本中，修复一些致命问题，做的升级版本号。

## 1.3. 选择版本

目前SpringCloud最新版本是：

| SpringCloud | SpringBoot        |
| ----------- | ----------------- |
| **Hoxton ** | **2.2.4.RELEASE** |





Spring Cloud与Spring Boot版本匹配关系

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/QeTCCVVYsc.nTrdl1k8N*hayRltYJZqzlUSnyZScnS8!/b/dLYAAAAAAAAA&bo=LQKlAQAAAAADF7k!&rf=viewer_4)



# 架构原型

```bat
dudu
 ├── dudu-common -- 公共工具类核心包
     ├── dudu-common-log -- 日志服务
     └── dudu-common-security -- 安全工具类
├── dudu-api --  公共服务接口（模型、异常）     
├──  dudu-meeting -- 会议管理微服务[8080]
     ├── dudu-meeting-weixin -- 微信端
	 ├── dudu-meeting-admin -- 会议管理后台管理微服务[8443]
	 ├── dudu-meeting-ui -- 前端VUE
     ├── dudu-meeting-codegen -- 图形化代码生成服务		 
├──  dudu-user -- 用户管理微服务[8081]	 
	 dudu-user-auth -- 授权服务提供(oauth2.0等)
	 dudu-user-rdbms-- 认证授权微服务	 
├── dudu-video -- 嘟嘟视频微服务[8085]
├── dudu-fastdfs --文件上传服务器（建议现在用阿里云OSS）
├── dudu-kafka --  中间件服务器
├── dudu-redis -- redis服务器
├── hystrix-dashBoard -- 熔断器[9080]
├── hystrix-trubine -- 集群熔断器[9088]
├── dudu-config -- 配置中心[8888]
├── dudu-eureka -- 服务注册与发现[9001 9002 9003]
├── dudu-gateway -- Spring Cloud Gateway网关[9999]
├── dudu-zuul -- Spring Cloud Zuul网关[8000]


```



# 3.   需求场景

**某大型分布式微服务系统，（会议发布微服务、视频管理微服务）**

**会议（查询）发布时，需要调用用户服务获取某个用户的联系方式。**

**(例如：通过会议编号查询会议详细信息、同时还要查询是哪位用户发布的会议)**

如下创建两个服务模块进行测试：

| 模块名称               | 功能                                      |
| ---------------------- | ----------------------------------------- |
| **会议发布（微服务）** | 会议查询（根据会议编号查询会议信息）      |
| **视频端 (微服务)**    | 视频召开信息（根据 召开编号查询会议信息） |

**需求实现：**

两个项目。其中会议发布管理模块在服务器A地址上。视频端服务在服务器B地址上。

A服务器模块项目—》调用B服务器模块项目。



# 4.   统一依赖管理

**（Spring Cloud** **项目都是基于 Spring Boot** **进行开发），并且都是使用 Maven** **做项目管理工具。在实际开发中，我们一般都会创建一个依赖管理项目作为 Maven** **的 Parent** **项目使用，这样做可以极大的方便我们对 Jar** **包版本的统一管理。**

| SpringBoot      | 2.3.0.RELEASE  |
| --------------- | -------------- |
| **SpringCloud** | **Hoxton.SR5** |



## 1.1.  创建依赖管理项目

### 1.1.1.   创建父工程pom

![](F:/%E8%AF%BE%E4%BB%B6%E6%A1%88%E4%BE%8B/java%E7%AC%AC%E4%B8%89%E5%92%8C%E5%9B%9B%E9%98%B6%E6%AE%B5/%E5%9B%9B%E9%98%B6%E6%AE%B5%E8%AF%BE%E4%BB%B6/SpringCloud/%E6%9C%80%E6%96%B0%E6%9C%80%E5%85%A8Cloud/md2%20-%20%E6%9C%80%E6%96%B0/images/3.png)

**dubbo** 对jar版本的统一管理 

![](F:/%E8%AF%BE%E4%BB%B6%E6%A1%88%E4%BE%8B/java%E7%AC%AC%E4%B8%89%E5%92%8C%E5%9B%9B%E9%98%B6%E6%AE%B5/%E5%9B%9B%E9%98%B6%E6%AE%B5%E8%AF%BE%E4%BB%B6/SpringCloud/%E6%9C%80%E6%96%B0%E6%9C%80%E5%85%A8Cloud/md2%20-%20%E6%9C%80%E6%96%B0/images/4.png)

### 1.1.2.   导入通用 公共 依赖pom.xml 

#### dudu pom

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.qfjy</groupId>
  <artifactId>dudu</artifactId>
  <packaging>pom</packaging>
  <version>1.0-SNAPSHOT</version>

  <name>dudu</name>

  <!-- 统一依赖父工程 springboot -->
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.4.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>


  <!-- 统一依赖的jar版本 -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    <!--spring-cloud 统一版本 -->
    <spring-cloud.version>Hoxton.RELEASE</spring-cloud.version>

    <mybatis-spring-boot-starter.version>2.1.0</mybatis-spring-boot-starter.version>

    <druid-spring-boot.version>1.1.10</druid-spring-boot.version>

    <springfox-swagger2.version>2.8.0</springfox-swagger2.version>
  </properties>



  <!-- 公共的 依赖jar-->
  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
    </dependency>
  </dependencies>


</project>
```



# 5.   服务创建

## 1.1.  会议微服务

**实现功能：根据会议编号ID，查询得到到会议详情**

### 1.1.1.   创建基于spring boot ssm

**dudu-meeting**

![](images\1.png)

### 1.1.2.   完整pom.xml

```xml
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.qfjy.dudu</groupId>
    <artifactId>dudu-meeting</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dudu-meeting</name>
    <description>Demo project for Spring Boot</description>


    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

        <!-- spring web mvc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- spring mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>
        <!-- mysql 驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- druid 数据源连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>

        <!-- 热部署
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
 		-->
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <!-- thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- 支持非严格语法的neko -->
        <dependency>
            <groupId>net.sourceforge.nekohtml</groupId>
            <artifactId>nekohtml</artifactId>
            <version>1.9.22</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>


        <!-- Swagger2 Begin -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.8.0</version>
        </dependency>
        <!-- Swagger2 End -->

    </dependencies>
```

### 1.1.3.   application.yml

```yml
#server config info
server:
  port: 8080
  servlet:
    context-path: /dudu-meeting
spring:
  #application name
  application:
    name: dudu-meeting
  #thymeleaf info
  thymeleaf:
    encoding: UTF-8
    cache: false
    prefix: classpath:/templates/
    suffix: .html
    mode: LEGACYHTML5
    servlet:
      content-type: text/html
  #datasource connect info
  datasource:
    name: test
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      url: jdbc:mysql://localhost:3306/qfdnj?serverTimezone=GMT%2B8
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
      filters: stat
      initial-size: 1
      min-idle: 1
      max-active: 20
      max-wait: 60000
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 'x'
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false

      pool-prepared-statements: false
      max-pool-prepared-statement-per-connection-size: 20

# mybatis config info
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.qfjy.dudu.po
  
# log config
logging:
  config: classpath:logback-spring.xml
  file:
    path: D:/dudu/log
```

### 1.1.4.   功能实现：Controller

![](images\2.png)



**会议微服务访问：**

<http://localhost:8080/dudu-meeting/meetingpub/20180201>



## 1.2.  视频微服务

**dudu-video 8085**

**实现功能：根据会议编号，查询得到视频召开详情**

功能同上

<http://localhost:8085/dudu-video/videoMeeting/pcode>

## 1.3微服务日志管理

为更好的管理所有的微服务。此处还需要分别为每个微服务添加日志。

### application.yml

> ```yml
> logging:
>   config: classpath:logback-spring.xml
>   file:
>     path: D:/dudu/log
> ```

### logback-spring.xml

> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <!-- 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出 -->
> <!-- scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true -->
> <!-- scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。 -->
> <!-- debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->
> <configuration  scan="true" scanPeriod="10 seconds">
> 
>     <!--<include resource="org/springframework/boot/logging/logback/base.xml" />-->
> 
>     <contextName>logback</contextName>
>     <!-- name的值是变量的名称，value的值时变量定义的值。通过定义的值会被插入到logger上下文中。定义变量后，可以使“${}”来使用变量。 -->
>     <property name="log.path" value="D:/dudu/log" />
> 
>     <!-- 彩色日志 -->
>     <!-- 彩色日志依赖的渲染类 -->
>     <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter" />
>     <conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter" />
>     <conversionRule conversionWord="wEx" converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter" />
>     <!-- 彩色日志格式 -->
>     <property name="CONSOLE_LOG_PATTERN" value="${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>
> 
> 
>     <!--输出到控制台-->
>     <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
>         <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->
>         <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
>             <level>info</level>
>         </filter>
>         <encoder>
>             <Pattern>${CONSOLE_LOG_PATTERN}</Pattern>
>             <!-- 设置字符集 -->
>             <charset>UTF-8</charset>
>         </encoder>
>     </appender>
> 
> 
>     <!--输出到文件-->
> 
>     <!-- 时间滚动输出 level为 DEBUG 日志 -->
>     <appender name="DEBUG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
>         <!-- 正在记录的日志文件的路径及文件名 -->
>         <file>${log.path}/log_debug.log</file>
>         <!--日志文件输出格式-->
>         <encoder>
>             <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
>             <charset>UTF-8</charset> <!-- 设置字符集 -->
>         </encoder>
>         <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
>         <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
>             <!-- 日志归档 -->
>             <fileNamePattern>${log.path}/debug/log-debug-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
>             <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
>                 <maxFileSize>100MB</maxFileSize>
>             </timeBasedFileNamingAndTriggeringPolicy>
>             <!--日志文件保留天数-->
>             <maxHistory>15</maxHistory>
>         </rollingPolicy>
>         <!-- 此日志文件只记录debug级别的 -->
>         <filter class="ch.qos.logback.classic.filter.LevelFilter">
>             <level>debug</level>
>             <onMatch>ACCEPT</onMatch>
>             <onMismatch>DENY</onMismatch>
>         </filter>
>     </appender>
> 
>     <!-- 时间滚动输出 level为 INFO 日志 -->
>     <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
>         <!-- 正在记录的日志文件的路径及文件名 -->
>         <file>${log.path}/log_info.log</file>
>         <!--日志文件输出格式-->
>         <encoder>
>             <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
>             <charset>UTF-8</charset>
>         </encoder>
>         <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
>         <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
>             <!-- 每天日志归档路径以及格式 -->
>             <fileNamePattern>${log.path}/info/log-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
>             <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
>                 <maxFileSize>100MB</maxFileSize>
>             </timeBasedFileNamingAndTriggeringPolicy>
>             <!--日志文件保留天数-->
>             <maxHistory>15</maxHistory>
>         </rollingPolicy>
>         <!-- 此日志文件只记录info级别的 -->
>         <filter class="ch.qos.logback.classic.filter.LevelFilter">
>             <level>info</level>
>             <onMatch>ACCEPT</onMatch>
>             <onMismatch>DENY</onMismatch>
>         </filter>
>     </appender>
> 
>     <!-- 时间滚动输出 level为 WARN 日志 -->
>     <appender name="WARN_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
>         <!-- 正在记录的日志文件的路径及文件名 -->
>         <file>${log.path}/log_warn.log</file>
>         <!--日志文件输出格式-->
>         <encoder>
>             <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
>             <charset>UTF-8</charset> <!-- 此处设置字符集 -->
>         </encoder>
>         <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
>         <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
>             <fileNamePattern>${log.path}/warn/log-warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
>             <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
>                 <maxFileSize>100MB</maxFileSize>
>             </timeBasedFileNamingAndTriggeringPolicy>
>             <!--日志文件保留天数-->
>             <maxHistory>15</maxHistory>
>         </rollingPolicy>
>         <!-- 此日志文件只记录warn级别的 -->
>         <filter class="ch.qos.logback.classic.filter.LevelFilter">
>             <level>warn</level>
>             <onMatch>ACCEPT</onMatch>
>             <onMismatch>DENY</onMismatch>
>         </filter>
>     </appender>
> 
> 
>     <!-- 时间滚动输出 level为 ERROR 日志 -->
>     <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
>         <!-- 正在记录的日志文件的路径及文件名 -->
>         <file>${log.path}/log_error.log</file>
>         <!--日志文件输出格式-->
>         <encoder>
>             <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
>             <charset>UTF-8</charset> <!-- 此处设置字符集 -->
>         </encoder>
>         <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
>         <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
>             <fileNamePattern>${log.path}/error/log-error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
>             <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
>                 <maxFileSize>100MB</maxFileSize>
>             </timeBasedFileNamingAndTriggeringPolicy>
>             <!--日志文件保留天数-->
>             <maxHistory>15</maxHistory>
>         </rollingPolicy>
>         <!-- 此日志文件只记录ERROR级别的 -->
>         <filter class="ch.qos.logback.classic.filter.LevelFilter">
>             <level>ERROR</level>
>             <onMatch>ACCEPT</onMatch>
>             <onMismatch>DENY</onMismatch>
>         </filter>
>     </appender>
> 
>     <!--
>         <logger>用来设置某一个包或者具体的某一个类的日志打印级别、
>         以及指定<appender>。<logger>仅有一个name属性，
>         一个可选的level和一个可选的addtivity属性。
>         name:用来指定受此logger约束的某一个包或者具体的某一个类。
>         level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
>               还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。
>               如果未设置此属性，那么当前logger将会继承上级的级别。
>         addtivity:是否向上级logger传递打印信息。默认是true。
>     -->
>     <!--<logger name="org.springframework.web" level="info"/>-->
>     <!--<logger name="org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor" level="INFO"/>-->
>     <!--
>         使用mybatis的时候，sql语句是debug下才会打印，而这里我们只配置了info，所以想要查看sql语句的话，有以下两种操作：
>         第一种把<root level="info">改成<root level="DEBUG">这样就会打印sql，不过这样日志那边会出现很多其他消息
>         第二种就是单独给dao下目录配置debug模式，代码如下，这样配置sql语句会打印，其他还是正常info级别：
>      -->
> 
> 
>     <!--
>         root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性
>         level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
>         不能设置为INHERITED或者同义词NULL。默认是DEBUG
>         可以包含零个或多个元素，标识这个appender将会添加到这个logger。
>     -->
> 
>     <!--开发环境:打印控制台-->
>     <springProfile name="dev">
>         <logger name="com.qfjy" level="error"/>
>     </springProfile>
> 
>     <root level="info">
>         <appender-ref ref="CONSOLE" />
>         <appender-ref ref="DEBUG_FILE" />
>         <appender-ref ref="INFO_FILE" />
>         <appender-ref ref="WARN_FILE" />
>         <appender-ref ref="ERROR_FILE" />
>     </root>
> 
>     <!--生产环境:输出到文件-->
>     <!--<springProfile name="pro">-->
>     <!--<root level="info">-->
>     <!--<appender-ref ref="CONSOLE" />-->
>     <!--<appender-ref ref="DEBUG_FILE" />-->
>     <!--<appender-ref ref="INFO_FILE" />-->
>     <!--<appender-ref ref="ERROR_FILE" />-->
>     <!--<appender-ref ref="WARN_FILE" />-->
>     <!--</root>-->
>     <!--</springProfile>-->
> 
> </configuration>
> ```



# 6.   公共模块搭建(服务化最佳实践）

<http://dubbo.apache.org/zh-cn/docs/user/best-practice.html>                               

**公共模块的搭建，dudu-common，主要是放一些其他项目公用的东西，比如日志服务，工具类等等；**

**建议：如果实体类、工具类都比较多的情况下，可以再进行分包管理。**

**例如：**

```bat
dudu
 ├── dudu-common -- 公共工具类核心包（jar)
     ├── dudu-common-log -- 日志服务
     └── dudu-common-security -- 安全工具类
├── dudu-api --  公共服务(service接口、po模型、exception异常) (jar)
```

### dudu-api模块

```
将公共的接口、模型po 统一jar管理
```

### dudu-common模块

```
将公共的工具类、包括统一的返回ResultJson
(参考： Swagger中的类：ResultJson)
```

#### ResultCode响应码

> ```java
> package com.qfjy.util.result;
> 
> /**
>  * @ClassName ResultCode
>  * @Description TODO 公共响应码
>  * @Author guoweixin
>  * @Version 1.0
>  */
> 
> public enum  ResultCode {
>     //##########TODO 请求成功 2**
>     /** 成功 */
>     SUCCESS("2000", "成功"),
> 
>     /** 操作失败 */
>     FAIL("2001", "操作失败"),
> 
>     /** 数据已存在 */
>     SUCCESS_IS_HAVE("2002", "数据已存在"),
>     /** 没有结果 */
>     NOT_DATA("2003", "没有结果"),
> 
>     //##########TODO 客户端错误 4**
>     /** 没有登录 */
>     NOT_LOGIN("4000", "没有登录"),
> 
>     /** 发生异常 */
>     EXCEPTION("4001", "发生异常"),
> 
>     /** 系统错误 */
>     SYS_ERROR("4002", "系统错误"),
> 
>     /** 参数错误 */
>     PARAMS_ERROR("4003", "参数错误 "),
> 
>     /** 不支持或已经废弃 */
>     NOT_SUPPORTED("4004", "不支持或已经废弃"),
> 
>     /** AuthCode错误 */
>     INVALID_AUTHCODE("4005", "无效的AuthCode"),
> 
>     /** 太频繁的调用 */
>     TOO_FREQUENT("4006", "太频繁的调用"),
> 
>     /** 未知的错误 */
>     UNKNOWN_ERROR("4007", "未知错误"),
> 
>     /** 未设置方法 */
>     NOT_METHOD("4008", "未设置方法");
> 
>     //##########TODO 服务器错误 5**
> 
>     private ResultCode(String code, String msg) {
>         this.code = code;
>         this.msg = msg;
>     }
>     /**对应状态码*/
>     private String code;
>     /**返回内容*/
>     private String msg;
> 
>     public String getCode() {
>         return code;
>     }
>     public void setCode(String code) {
>         this.code = code;
>     }
>     public String getMsg() {
>         return msg;
>     }
>     public void setMsg(String msg) {
>         this.msg = msg;
>     }
> }
> ```

#### ResultJson<T>

> ```java
> package com.qfjy.util.result;
> 
> import lombok.Data;
> 
> /**
>  * @ClassName ResultJson
>  * @Description TODO
>  * @Author guoweixin
>  * @Version 1.0
>  */
> @Data
> public class ResultJson<T> {
> 
>     /*响应码*/
>     private String code;
>     /*消息提示内容文件*/
>     private String msg;
>     /*返回指定对象*/
>     private T data;
> 
>     /** 成功的方法*/
>     public ResultJson(T t) {
>        this.setCode(ResultCode.SUCCESS.getCode());
>        this.setMsg(ResultCode.SUCCESS.getMsg());
>        this.setData(t);
>     }
>     /**已有的ResultCode 进行返回*/
>     public ResultJson(T t,ResultCode code){
>         this.setCode(code.getCode());
>         this.setMsg(code.getMsg());
>         this.setData(t);
>     }
>     /** 完全自定义返回 */
>     public ResultJson(T t,String code,String message){
>         this.setCode(code);
>         this.setMsg(message);
>         this.setData(t);
>     }
> }
> ```



# 7.   当前整体架构图如下：

![](images\5.png)



```bat
dudu  父工程pom(统一的依赖jar管理)
├── dudu-api --  公共服务接口（模型、异常）     
├── dudu-common -- 公共工具类核心包
├── dudu-meeting -- 会议管理微服务[8080]	 
├── dudu-video -- 视频微服务[8085]	 
```



# 8.   微服务间（通信）调用：

**功能实现：**

 根据会议编号ID，查询会议信息，同时查询 得到用户信息。

![](images\7.png)

## 1.1.  分析

**dudu-video服务的提供者**

<<http://localhost:8084/dudu-video/videoMeeting/pcode?pcode=20200102001>>

 //根据会议编号得到视频相关数据详情



**dudu-meeting服务的消费者**

<<http://localhost:8080/dudu-meeting/meetingPub/20200102001> 

//根据会议编号得到会议信息



## 1.2.  功能实现(服务间通信)RestTemplate

**目前针对分布式最主流的是RPC和 Http 两种。**

```
Dubbo以RPC
SpringCloud是Http Rest
```



### 1.2.1.   什么是RestTemplate？

RestTemplate是Spring提供的用于访问Rest服务的客户端模版工具集。

是一种简单、便捷访问的restful服务模版类。

RestTemplate提供了多种访问远程Http服务的方法，能够大大提高客户端的编写效率。

借助 RestTemplate，Spring应用能够方便地使用REST资源 

### 1.2.2.   使用示例

REST提供了一个更简单的可选方案。另外，很多的现代化应用都会有移动或富JavaScript客户端，它们都会使用运行在服务器上REST API。

使用restTemplate访问restful接口非常简单。

#### **创建RestTemplate模版对象**

**通过注解的方式创建RestTemplate对象**

**创建RestTemplateConfig类：**

![](images\61.png)

**在controller中进行自动注入RestTemplate**

```java
@RestController
@RequestMapping("meetingpub")
public class MeetingpubController {
    @Autowired
    private MeetingpubService meetingpubService;
    @Autowired
    private RestTemplate restTemplate; //模版的注入

    @RequestMapping(value = "{id}",method = RequestMethod.GET)  // meetingpub/id
    public ResultJson selectMeetingpubById(@PathVariable("id") final String id){
		....................
    }
```

### 1.2.3 GET请求

在RestTemplate中，发送一个GET请求，我们可以通过如下两种方式：

##### 第一种：getForEntity

getForEntity方法的返回值是一个`ResponseEntity<T>`，

  `ResponseEntity<T>`是Spring对HTTP请求响应的封装，包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等。

```java
 //调用 用户微服务--》通过RestTemplate模版实现  得到用户信息
        /**
         * param1 url 请求的URL地址
         * param2 responseType 希望返回的类型.class
         * param3 占位符 最后一个参数是map,map的key为前面的占位符。map value为参数值
         * 可变长度的参数，一一来替换前面的占位符 name={name}形式，
         */
        String url="http://localhost:8081/dudu-user/user/{id}";
        ResponseEntity<User> responseEntity=restTemplate.getForEntity(url, User.class,meetingpub.getUid());
        User user=responseEntity.getBody();
```

**完整代码：(Controller或Service层都可)**

```java
    @Autowired
    private RestTemplate restTemplate;  //模版的注入

    @RequestMapping(value = "{id}",method = RequestMethod.GET)  // meetingpub/id
    public ResultJson selectMeetingpubById(@PathVariable("id") final String id){
        Meetingpub meetingpub=meetingpubService.selectByPrimaryKey(id);
        if (meetingpub==null){
            return ResultJson.failed("用户ID不存在");
        }
        //得到用户信息
        /**
         * param1 url 请求的URL地址
         * param2 responseType 希望返回的类型.class
         * param3 占位符 最后一个参数是map,map的key为前面的占位符。map value为参数值
         * 可变长度的参数，一一来替换前面的占位符 name={name}形式，
         */
        String url="http://localhost:8081/dudu-user/user/{id}";
        ResponseEntity<User> responseEntity=restTemplate.getForEntity(url, User.class,meetingpub.getUid());
        User user=responseEntity.getBody();

        meetingpub.setUser(user);

        return ResultJson.ok(meetingpub);
    }
```

##### 第二种：getForObject

getForObject函数实际上是对getForEntity函数的进一步封装，如果你只关注返回的消息体的内容，对其他信息都不关注，此时可以使用getForObject，

```java
  //调用 用户微服务--》通过RestTemplate模版实现
  //User user= restTemplate.getForObject(url,User.class,meetingPub.getUid());
```



### 1.2.4 POST请求

**示例：**

**服务提供者（dudu-user）中有一个用户添加的方法。**

UserController:

```java
//POST请求
@ResponseBody
@RequestMapping(value = "add",method = RequestMethod.POST) // user/add
public String addUser( @RequestBody User user){
    System.out.println("user:"+user);
    return "success";
}
```

   `@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。`



**服务消费者（dudu-meeting）调用用户添加方法：**

MeetingPubController:

```java
 //模拟 服务间 调用 用户微服务  POST请求
    @ResponseBody
    @RequestMapping(value = "user/add")
    public User addUserMeeting(){

        User u=new User();
        u.setId(123456);
        u.setName("guoweixin");

        /**
         * param1 请求的url
         * param2 表示传递的参数数据
         * param3 返回的消息体的数据类型
         */
        String url="http://localhost:8081/dudu-user/user/add";
         String flag=restTemplate.postForObject(url,u,String.class);

        System.out.println(flag);
        return u;
    }
```

### 1.2.5PUT请求

在RestTemplate中，PUT请求可以通过put方法调用，put方法的参数和前面介绍的postForEntity方法的参数基本一致，只是put方法没有返回值而已。

```java
      restTemplate.put(url,param2,param3);
```

### 1.2.6 DELETE请求

delete请求我们可以通过delete方法调用来实现

```java
   restTemplate.delete("http://localhost:8081/dudu-user/user/del/{id}","123");
```



# 9.  问题

**服务与服务间通信通过restTemplate模版实现。 **

1、  如果服务的提供者IP地址或端口号发生改变怎么办？

2、  如果服务的提供者有多个。在消费者端调用哪一个？



# 10.解决

答：服务的注册与发现（注册中心）

```bat
为什么使用服务发现？

答：如果你在写代码调用一个有REST API或Thrift API的服务，你的代码需要知道一个服务实例的网络地址（IP地址和端口）。运行在物理硬件上的传统应用中，服务实例的网络地址是相对静态的，你的代码可以从一个很少更新的配置文件中读取网络地址。
在一个现代的，基于云的微服务应用中，
服务实例的网络地址是动态分配的。而且，由于自动扩展，失败和更新，服务实例的配置也经常变化。
```

