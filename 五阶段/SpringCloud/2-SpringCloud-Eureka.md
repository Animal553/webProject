# **Spring Cloud**

#  SpringCloud版本选择

## 版本介绍

Spring Cloud 项目目前仍然是快速迭代期，版本变化很快。

<http://spring.io/projects/spring-cloud#learn>

![](images-eureka\4.png)





**目前SpringCloud最新版本是：**

| SpringCloud     | SpringBoot        |
| --------------- | ----------------- |
| **Hoxton.SR4 ** | **2.3.0.RELEASE** |



**springboot版本选择:2.3.0.RELEASE**

**spring-cloud版本选择：Hoxton.SR4** (如有变更，选择最新版本)



# 1.  服务的注册与发现

## 简介

> 在微服务架构中，服务发现（Service Discovery）是关键原则之⼀一。手动配置每个客户端或某种形式的约定是很难做的，并且很脆弱。Spring Cloud提供了了多种服务发现 的实现⽅方式，
>
> 例如：Eureka、Consul、Zookeeper、Nacos。

## 常见的注册中心

**Zookeeper**
zookeeper它是一个分布式服务框架，是Apache Hadoop 的一个子项目，它主要是用来解决分布式应 用中经常遇到的一些数据管理问题，如：统一命名服务、状态同步服务、集群管理、分布式应用配置项 的管理等。简单来说zookeeper=文件系统+监听通知机制。  

**Eureka**

Eureka是在Java语言上，基于Restful Api开发的服务注册与发现组件，Springcloud Netﬂix中的重要组件

**Consul** 

Consul是由HashiCorp基于Go语言开发的支持多数据中心分布式高可用的服务发布和注册服务软件， 采用Raft算法保证服务的一致性，且支持健康检查。

**Nacos** 

Nacos是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。简单来说 Nacos 就是 注册中心 + 配置中心的组合，提供简单易用的特性集，帮助我们解决微服务开发必会涉及到的服务注册 与发现，服务配置，服务管理等问题。Nacos 还是 Spring Cloud Alibaba 组件之一，负责服务注册与 发现。



我们通过一张表格大致了解Eureka、Consul、Zookeeper的异同点。选择什么类型的服务注册与 发现组件可以根据自身项目要求决定。

| 组件名     | 语言 | CAP  | 一致性算法 | 服务健康检查 | 对外暴露接口 |
| ---------- | ---- | ---- | ---------- | ------------ | ------------ |
| **Eureka** | Java | AP   | fq         | 可配支持     | HTTP         |
| **Consul** | Go   | CP   | Raft       | 支持         | HTTP/DNS     |
| Nacos      | Java | AP   | Raft       | 支持         | HTTP         |
| Zookeeper  | Java | CP   | Paxos      | 支持         | 客户端       |



# 2.   Eureka服务的注册与发现

## 1.1.  什么是EureKa

> Eureka 是 Netflix 出品的用于实现服务注册和发现的工具。 Spring Cloud 集成了 Eureka，并提供了开箱即用的支持。其中， Eureka 又可细分为 Eureka Server 和 Eureka Client。
>
> Eureka是Spring Cloud Netflix微服务套件中的一部分，可以与Springboot构建的微服务很容易的整合起来。
>
> EureKa在Spring Cloud全家桶中担任着服务的注册与发现的落地实现。Netflix在设计EureKa时，基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移，功能类似于Dubbo的注册中心Zookeeper。

 

### 1.1.1.  Eureka总结

　　　　**作用：**实现服务治理（服务注册与发现）

　　　　**总结：**

　　　　　　Spring Cloud Eureka是Spring Cloud Netflix项目下的服务治理模块。而Spring Cloud Netflix项目是Spring Cloud的子项目之一，主要内容是对Netflix公司一系列开源产品的包装，它为Spring Boot应用提供了自配置的Netflix OSS整合。通过一些简单的注解，开发者就可以快速的在应用中配置一下常用模块并构建庞大的分布式系统。它主要提供的模块包括：服务发现（Eureka），断路器（Hystrix），智能路由（Zuul），客户端负载均衡（Ribbon）等。

 

## 1.2.  EureKa实现原理

​              ![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/yxslfM3sZD4RtE.7RfSfRQYXtgigSTTUXiH*yFye4xQ!/b/dDUBAAAAAAAA&bo=jgM3AgAAAAADF4o!&rf=viewer_4)                                    

EureKa采用C-S的设计架构，即包括了Eureka Server（服务端），EureKa client（客户端）。 



1.EureKa Server 提供服务注册，各个节点启动后，在EureKa server中进行注册；

2 EureKa Client 是一个Java客户端，用于和服务端进行交互，同时客户端也是一个内置的默认使用轮询负载均衡算法的负载均衡器。在应用启动后，会向Eueka Server发送心跳（默认30秒）。如果EureKa Server在多个心跳周期内没有接受到某个节点的心跳，EureKa Server将会从服务注册表中将这个服务移出（默认90秒）。

 

## 1.3.  创建EureKa服务注册中心

### 1.1.1.  新建springboot项目

**1-dudu-eureka**

![](images-eureka\11.png)

![](images-eureka\5.png)

### 1.1.2.  Pom.xml

#### 1、修改dudu   parent pom.xml中：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.qfjy</groupId>
  <artifactId>dudu</artifactId>
  <packaging>pom</packaging>
  <version>1.0-SNAPSHOT</version>
  <modules>
    <module>dudu-api</module>
    <module>dudu-common</module>
  </modules>
  <name>dudu</name>

  <!-- 统一依赖父工程 springboot -->
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.0.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>


  <!-- 统一依赖的jar版本 -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--spring-cloud 统一版本 -->
    <spring-cloud.version>Hoxton.SR4</spring-cloud.version>
    <mybatis-spring-boot-starter.version>2.1.0</mybatis-spring-boot-starter.version>
    <druid-spring-boot.version>1.1.10</druid-spring-boot.version>
    <springfox-swagger2.version>2.8.0</springfox-swagger2.version>
  </properties>


  <dependencyManagement>
    <!--spring-cloud 统一版本 -->
    <dependencies>
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring-cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
    <!--spring-cloud 统一版本 -->

  </dependencyManagement>

  <!-- 公共的 依赖jar-->
  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
    </dependency>
  </dependencies>
</project>
```

```
建议放在dependencyManagement下，可能有些子工程不需要该依赖jar
```



#### 2、dudu-eureka  pom.xml中：

```xml
<!-- Spring Cloud  eureka Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<!-- Spring Cloud eureka End -->

```

#### 3、完整的POM.XML 

**dudu-eureka pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.qfjy</groupId>
        <artifactId>dudu</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <groupId>com.qfjy.dudu</groupId>
    <artifactId>1-dudu-eureka</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>1-dudu-eureka</name>

    <dependencies>
        <!-- Spring Boot  web Start -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- Spring Boot  web End -->

        <!-- Spring Cloud  eureka Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!-- Spring Cloud eureka End -->

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```



### 1.1.3.  配置Eureka服务端application.yml

默认情况下，该服务注册中心也会将自己作为客户端来尝试注册它自己，所以我们需要禁用它的客户端注册行为。

通过 **eureka.client.registerWithEureka:false 和 fetchRegistry:false** 来表明自己是一个 Eureka Server



**dudu-eureka   application.yml:**

```yml
server:
  port: 9001  #服务器端口号配置
spring:
  application:
    name: dudu-eureka  #应用程序名称
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: false  # 是否将当前注册到Eureka服务中。（本身服务端无需注册）
    fetch-registry: false #是否从Eureka中获取注册信息
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  instance:
    hostname: localhost
```

### 1.1.4.  应用程序入口

@EnableEurekaServer  //启动EurekaServer 表明自己是一个服务端

```java
@SpringBootApplication
@EnableEurekaServer  //启动EurekaServer 表明自己是一个服务端
public class DuduEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduEurekaApplication.class, args);
    }
}
```

### 1.1.5.  启动EureKa服务端

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/3DY2D*ujE0dwshOLoDpwswAb*egtT7iTIUnDnsQBgOU!/b/dFQBAAAAAAAA&bo=owOAAQAAAAADFxM!&rf=viewer_4)



# 3.用户/视频微服务注册到EureKa（提供者）

## 1.1.  dudu-user pom.xml

**追加SpringCloud Eureka 客户端支持**

```xml
 <!-- 客户端 Spring Cloud  eureka client Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<!-- 客户端 Spring Cloud eureka client End -->
```

## 1.2.  dudu-user application.yml

**追加application.yml中，添加Eureka客户端支持。进行服务注册**

**注意：** 需要指明 **spring.application.name**，这个很重要，这在以后的服务与服务之间相互调用一般都是根据这个 name。 

```yml
# server config
server:
  port: 8081
#spring config
spring:
  application:
    name: dudu-user #应用程序的名称
# eureka 客户端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
```

## 1.3.  应用程序入口Application

**DuduUserApplication(如下两种都可)**

@EnableDiscoveryClient  //启动主类声明该服务被注册中心发现
@EnableEurekaClient //表明自己是一个Eureka Client

```java
@SpringBootApplication
@MapperScan(basePackages = {"com.qfjy.dudu.mapper"})
@EnableDiscoveryClient  //启动主类声明该服务被注册中心发现
//@EnableEurekaClient //表明自己是一个Eureka Client
public class DuduUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduUserApplication.class, args);
    }
}
```

### 错误解决

引入spring-cloud-starter-netflix-eureka-client和spring-boot-starter-web两个依赖的时候，可能会出现冲突。由于代码里面，用了Spring MVC的Rest方式，而没有用spring-cloud-starter-netflix-eureka-client本身包含Jesery Rest方式。导致出现BUG。

```bat
<!--eureka客户端 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			<!-- 排除Jersey，用SpringMVC Rest方式-->
			<exclusions>
				<exclusion>
					<groupId>com.sun.jersey</groupId>
					<artifactId>jersey-client</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jersey</groupId>
					<artifactId>jersey-core</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jersey.contribs</groupId>
					<artifactId>jersey-apache-client4</artifactId>
				</exclusion>
			</exclusions>

```



# 4.   Eureka自我保护机制

## 1.1.  问题1(明确显示IP和ID名称）

**注册成功的服务，无法明确显示服务注册的名称和IP地址。**

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/5XdskzjPgoTqFUe.HWYaw0m6Xssm4LP90ED27*ye05w!/b/dE4BAAAAAAAA&bo=3AS*AAAAAAADF1U!&rf=viewer_4)

**解决指定服务注册的名称和让其显示IP地址：**(建议访问当前项目的时候使用ip 地址访问,默认是机器名的方式)

```bat
instance:  #指定Eureka 注册的实例名称 
    prefer-ip-address: true #访问Eureka IP地址
```

```bat
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
  instance:  #指定Eureka 注册的实例名称
    prefer-ip-address: true #访问Eureka IP地址
```



## 1.2.  问题2(Eureka自我保护机制）Eureka服务端



当我们进行SpringCloud微服务开发的时候，在<http://localhost:9001/>  Eureka主页上，

**问题1：为什么服务下线了，Eureka Server接口返回的信息还会存在？**

**问题2：为什么服务上线了，Eureka Client不能及时获取到？**

**问题3：为什么有可能会出现如下的一些红色提示信息？**

```bat
'EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEYRE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
```



> SELF PRESERVATION (自我保护 self preservation)
>
> ​	在分布式系统设计中，通常需要对应用实例的存活进行健康检查，这里比较关健的问题就是要处理好网络偶尔抖动或短暂不可用时造成的误判。另外Eureka Server端和Client出现网络分区等问题时，在极端情况下可能会使得Eureka Server清空部分服务的实例列表，这将严重影响到Eureka Server的 Avaliability属性。因为 Eureka Server引入了 SELF PRESERVATION机制。
>
> ​	Eureka Client端与Server端之间有个租约，Cient要定时发送心跳来维持这个租约，表示自己还存活着。Eureka通过当前注册的实例数，来计算每分钟应该从应用实例接收到的心跳数，如果最近一分钟接近续约次数小于等于指定阈值，则关闭租约失效剔除。禁止定时任务剔除失效的实例，从而保护注册信息。

> 参数调优：
>
>    Eureka Client端核心参数、 定时任务参数、通信（超时）相关参数等。
>
>    Eureka  Server端基本参数、response cache参数、peer参数、http参数等。

**默认情况下，如果Eureka Server在一定时间内（默认90秒）没有接收到某个微服务实例的心跳，Eureka Server将会移除该实例。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，而微服务本身是正常运行的，此时不应该移除这个微服务，所以引入了自我保护机制。**



答案1：Eureka Server并不是强一致性。因此registry中会存留过期的实例信息。

```bat
1、应用实例异常挂掉，没能在挂掉之前告诉Eureka Server要下线掉该服务实例信息，此时就需要依赖Eureka Server的EvictionTask去剔除。
2、Eureka Server的REST API有response cache。因此需要等待缓存过期才能更新。
3、Eureka Server由于开启并引入了SELF PRESERVATION模式，导致registry的信息不会因为过期而被剔除掉，直到退出SELF PRESERVATION模式。
```

答案2：

```bat
# 针对第二个问题，可以调整EvictionTask的调度频率，比如下面的配置，将调度间隔默认的60秒，调整为15秒：
eureka.server.eviction-interval-timer-in-ms=5000

# 针对response cache的问题，可以根据情况考虑关闭readOnlyCacheMap：
eureka.server.use-read-only-response-cache=false

#或者调整readWriteCacheMap的过期时间：
eureka.server.response-cache-auto-expiration-in-seconds=60

#针对SELF PRESERVATION的问题，在测试环境可以将enable-self-preservation设置为false：
eureka.server.enable-self-preservation=false
```



### 1.1.1.  自我保护机制定义

自我保护模式正是一种针对网络异常波动的安全保护措施，使用自我保护模式能使Eureka集群更加的健壮、稳定的运行。

自我保护机制的工作机制是如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，Eureka Server自动进入自我保护机制，此时会出现以下几种情况： 

```bat
eureka.server.renewal-percent-threshold=0.85  #默认0.85
```



```
1、Eureka Server不再从注册列表中移除因为长时间没收到心跳而应该过期的服务。

2、Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上，保证当前节点依然可用。

3、当网络稳定时，当前Eureka Server新的注册信息会被同步到其它节点中。

因此Eureka Server可以很好的应对因网络故障导致部分节点失联的情况，而不会像ZK那样如果有一半不可用的情况会导致整个集群不可用而变成瘫痪。
```

### 1.1.2.  自我保护机制设置(Eureka Server端)

Eureka自我保护机制，

通过配置 **eureka.server.enable-self-preservation ： true打开 / false 禁用**  自我保护机制，默认打开状态，建议生产环境打开此配置。

**eureka application.yml 下进行修改：**

 **禁用自我保护机制**：

```yml
  server:
    enable-self-preservation: false # 禁用自我保护机制
```

**微服务修改设置服务心跳的时间:**

lease-expiration-duration-in-seconds: 90   #默认心跳时间为90秒

```yml
  eureka:
   instance:
  		lease-expiration-duration-in-seconds: 90 #默认心跳即为90秒
```

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/4de3idGDuHWe4hJ1VkRDRwg71CIHdkJXMOfAvxegTDU!/b/dAUBAAAAAAAA&bo=NQTsAQAAAAADF.4!&rf=viewer_4)

```yml
server:
  port: 9001  #服务器端口号配置
spring:
  application:
    name: dudu-eureka  #应用程序名称
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: false  # 是否将当前注册到Eureka服务中。（本身服务端无需注册）
    fetch-registry: false #是否从Eureka中获取注册信息
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  instance:
    hostname: localhost
  server:
    enable-self-preservation: false #禁用自我保护机制

```

### 1.1.3.  自我保护机制关闭

在配置上，自我保护机制关闭了，但是一分钟内的续约数没有达到85% ，可能发生了网络分区，可能会有如下提示：

**eureka 主页 红色字符显示：**

```bat
'THE SELF PRESERVATION MODE IS TURNED OFF.THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.
```

## 1.3.  问题3(解决响应变成XML）

将dudu-user 注册到Eureka中，再访问原有连接请求：



![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/FPUoei6buTuPhm*VeFRrP8jnGDPQhMllvZPBlzeQROI!/b/dL8AAAAAAAAA&bo=4AL0AAAAAAADFyQ!&rf=viewer_4)

此处不再是原有JSON数据，而是变成了XML。

**原因**：

SpringBoot项目中集成了EurekaServer，又需要jackson-dataformat-xml这个依赖

该依赖jar 可以将实体转换为xml也可以转换为json，application/xml在最后匹配json的**/* *前面，优先级高过json，所以返回了XML。

由于我们引入了Eureka Server的依赖，导致将SpringMVC默认的配置由JSON变成了XML。

**解决：**

1、  在请求的方法Mapping上加上  produces = {"application/json;charset=UTF-8"}

```java
@ResponseBody
@RequestMapping(value = "{id}"
                ,method = RequestMethod.GET,
                produces = {"application/json;charset=UTF-8"})  
    public User selectById(@PathVariable("id")Integer id){
        return  userService.selectByPrimaryKey(id);
    }
```

2、同时也可以将其放在类体上面：

```java
@RequestMapping(value = "user",
                 produces = {"application/json;charset=UTF-8"})  // user/id
public class UserController {
    .......
}
```



# Eureka安全密码保护

SpringCloud通过Spring security来进⾏行行安全管理理,所以需要导⼊入这个依赖包

## Eureka Server pom.xml

```xml
<!--SpringBoot安全组件，用于设置密码来访问进入 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!--SpringBoot安全组件，用于设置密码来访问进入 -->
```

## 设置密码

导入security后如果不设置用户名密码会有默认的用户名密码,用户名是user,密码是每次启动的时候会在控制台打印一个随机密码,所以我们需要在application.yml中设置密码

![](images-eureka\6.png)

```bat
spring:
  application:
    name: dudu-eureka  #应用程序名称
  security:
    user:
      name: Wilson
      password: 123456
```

## 修改安全配置

> 当我们开启安全配置后,我们发现我们的服务会无法注册到eureka上面,即使是客户端 也指定用户名和密码仍旧不可以,原因是spring security 2开始,默认对csrf进行了拦 截,导致我们的服务注册也会被拦截,
>
> 而我们的springcloud H版本使用的就是 springboot2,那么导⼊入的security依赖也是2的版本,所以我们需要修改安全配置来允许访问

**config包下创建类 WebSecurityConfig**

```java
package com.qfjy.dudu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Author guoweixin
 * @Date 2020/2/15
 * @Version 1.0
 */
@Configuration //注意要加这个注解
@EnableWebSecurity//注意要加这个注解,开启安全配置
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().ignoringAntMatchers("/eureka/**");
        //我们的服务注册地址是 在/eureka下⾯面,所以我们可以通过/eureka/**进行忽略安全过滤来达到允许服务注册到⽬目的, 而且其他地址的访问会仍旧需要密码    }
    }

}
```



## EurekaServer访问

此刻输入密码即可

![](images-eureka\7.png)



## 服务提供者修改

dudu-user application.yml

```bat
# eureka 客务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://Wilson:123456@localhost:9001/eureka/  #curl风格 用户名Wilson，密码123456 访问主机和端口号
  instance:
    prefer-ip-address: true
```



# 5.   Eureka集群

## 1.1.  Eureka高可用：

前面我们介绍了如何搭建一个Eureka服务的架构，但是服务提供者我们只用了一个单例，完全不能体现高并发高可用。尝试在前面示例Eureka项目的基础上继续完善，让它可以做到一个集群的部署

![](http://m.qpic.cn/psb?/V13x1ZYF1dFQtq/liUdJvqsvJpshQHmiPMnclqcK.yMteejE1CTFum54YY!/b/dLgAAAAAAAAA&bo=hAMIAgAAAAADF78!&rf=viewer_4)

```
在我们的Eureka服务器里面会启动两个实例，这两个实例会相互注册。
然后服务提供者也会启动两个实例，这两个实例都会注册到我们服务器的两个实例，是的，像图中那样一个服务提供者实例分别向两个服务器实例注册。

服务调用者也会注册到两个服务器实例上面。
最后我们会编写一个Rest客户端，去调用服务调用者的站点端口，来测试服务调用的过程。
即完成高可用过程。
```

## 1.2.服务器的集群配置

**当前架构中，需要三个EurekaServer实例完成集群，**

**并且这三个Eureka服务器之间还相互注册了信息。所以我们需要修改一下上文中的Eureka服务器。**

> Eureka为了了保证高可用,需要搭建集群,Eureka的集群搭建很简单,只要eureka服务端之间相互注册就行了,每个Eureka作为其他的Eureka的客户端,配置方式和我们配置普通 服务没区别



##### 1、 EurekaServer-9001

```bat
server:
  port: 9001  #服务器端口号配置
spring:
  application:
    name: dudu-eureka  #应用程序名称
  security:
    user:
      name: Wilson
      password: 123456
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: false  # 是否将当前注册到Eureka服务中。（本身服务端无需注册）
    fetch-registry: false #是否从Eureka中获取注册信息
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
 #     defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #单机配置
      defaultZone: http://localhost:9002/eureka/,http://localhost:9003/eureka/  #集群配置
  instance:
    hostname: eureka-9001
```



##### 2、 EurekaServer-9002

```bat
server:
  port: 9002  #服务器端口号配置
spring:
  application:
    name: dudu-eureka  #应用程序名称
  security:
    user:
      name: Wilson
      password: 123456
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: false  # 是否将当前注册到Eureka服务中。（本身服务端无需注册）
    fetch-registry: false #是否从Eureka中获取注册信息
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
 #     defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #单机配置
      defaultZone: http://localhost:9001/eureka/,http://localhost:9003/eureka/  #集群配置
  instance:
    hostname: eureka-9002
```

##### 3、 EurekaServer-9003

```bat
server:
  port: 9003  #服务器端口号配置
spring:
  application:
    name: dudu-eureka  #应用程序名称
  security:
    user:
      name: Wilson
      password: 123456
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: false  # 是否将当前注册到Eureka服务中。（本身服务端无需注册）
    fetch-registry: false #是否从Eureka中获取注册信息
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
 #     defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #单机配置
      defaultZone: http://localhost:9001/eureka/,http://localhost:9002/eureka/  #集群配置
  instance:
    hostname: eureka-9003
```







**右键maven-install 打成jar包。单独运行**
**如果jar不能成功运行，**
**在pom.xml中加入该引用：（原因父工程引用的不是springboot,而是spring-boot-dependencies。导致该插件丢失。此处加上即可）**

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>2.1.1.RELEASE</version>
    <executions>
        <execution>
            <goals>
            	<goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```

# 6.   用户微服务注册到Eureka集群

#### 1服务提供者客户端应用配置

服务提供者dudu-user需要同时向两个eureka服务器发出注册信息，所以这里配置了三个eureka的地址信息，并且用逗号隔离开。

**修改 dudu-user application.yml:**

```bat
# eureka 客务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://Wilson:123456@localhost:9001/eureka/,http://Wilson:123456@localhost:9002/eureka/,http://Wilson:123456@localhost:9003/eureka/
  instance:
    prefer-ip-address: true
```





<http://eureka-9001.com:9001/eureka/>,

<http://eureka-9002.com:9002/eureka/>,

<http://eureka-9003.com:9003/eureka/>

 **启动dudu-user查看EureKa 服务即可看到注册成功**



# 7.   Eureka和Zookeeper区别

Eureka是SpringCloud中常用的注册中心，采用去中心  化的。不存在Leader,每个Eureka都可以认为是老大，在集群中是平级的。当服务提供者启动之后，会把服务的信息注册到Eureka中，即Eureka缓存服务列表。服务调用方调用服务时，先去Eureka中查询需要的服务注册列表，然后再完成一次远程的服务调用。

**总结：**

1.Eureka和Zookepper都可以作为注册中心

2.Eureka取CAP中的AP，注重可用性。Zookepper取CAP理论中的CP强调高的一致性

3.Cloud官网Eureka架构图指出，Eureka是去中心化的，当网络出现分区故障时，可以独立的为各自的子网下的机器提供服务。 Zookepper中，当出现分区故障，Zookepper集群进行各自服务的提出，然后根据规定在各自的集群下进行Leader选举， 但参选人数不过半时，此集群挂掉。(脑裂中就存在这种问题导致它不具有可用性)

4.Eureka常用于SpringCloud下产物的注册中心,如：微服务的注册中心

Zookepper是分布式下的注册中心，如：Dubbo、Hadoop类似的集群的服务注册中心

5.Eureka和Zookeeper的选取：看自己程序的需要，是需要AP还是CP,根据具体的场景来选取

 

CAP原则：

```
CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）。CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾
```

# 8、架构



![](images-eureka\3.png)

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
├──  dudu-user -- 用户管理微服务[8081]	 +6+
	 dudu-user-auth -- 授权服务提供(oauth2.0等)
	 dudu-user-rdbms-- 认证授权微服务	 
├── dudu-eureka -- 服务注册与发现[9001 9002 9003]
├── dudu-video -- 视频微服务[8085]


```



# 总结

<https://github.com/Netflix/eureka/wiki>

eureka官方已经正式宣布：自2.0起不再维护该项目，并在[github 项目wiki](https://github.com/Netflix/eureka/wiki)上放出了一段吓唬人的话:

大意就是：从2.x起，官方不会继续开发了，如果需要使用2.x，风险自负。

但其实我觉得问题并不大，eureka目前的功能已经非常稳定，就算不升级，服务注册/发现这些功能已经够用。

如果想寻找替代方案的话，建议采用功能更为丰富的**consul或阿里nacos**，除了服务注册、发现，consul还提供了k-v存储等其它功能，consul的官网针对其它同类软件也做了详细比较，详见 [consul vs other software](https://www.consul.io/intro/vs/index.html)，有兴趣的可以看看，特别是有一句话，翻译成大白话就是：我不是针对在座的各位，我想说除我之外其它的都是渣渣（足见其相当的自信！）

