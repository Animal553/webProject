# Spring Cloud Hystrix

![](images-hystrix\1.png)

## 前言

本篇主要介绍的是SpringCloud中的断路器(Hystrix)和断路器指标看板(Dashboard)的相关使用知识。



## 一 Hystrix是什么

Hystrix是由Netflix开源的一个针对分布式系统容错处理的开源组件。能够提供断路，降级，监控等多种服务。

```bat
Hystrix的本意是指 豪猪 的动物，它身上长满了很长的较硬的空心尖刺,当受到攻击时，通过后退的方式使其尖刺刺入敌方的身体。作为这种特征的引申，Netflix公司在分布式微服务架构的践行下，将其保护服务的稳定性而设计的客户端熔断和断路器的解决方案，称之为Hystrix。
```

在分布式环境中，许多服务依赖项中的一些必然会失败,或者我们的服务被突发性的高并发访问导致出现 问题。Hystrix是一个库，通过添加延迟容忍和容错逻辑，帮助你控制这些分布式服务之间的交互。 Hystrix通过隔离服务之间的访问点、停止级联失败和提供回退选项来实现这一点，所有这些都可以提高 系统的整体弹性。

Hystrix官方代码拖管在： https://github.com/Netflix/Hystrix/wiki

## 二 解决了什么问题

**官方给出的解释如下：**

 **在应用拆分成多个服务的情况下**，当用户需要请求 A P H I 四个服务获取数据时，

### 正常流量

在平时正常流量情况下，系统运行正常：

![](images-hystrix\2.png)

```java
在复杂分布式体系结构中的应用程序有许多依赖项，每个依赖项在某些时候都不可避免地会失败。
如果主 机应用程序没有与这些外部故障隔离，那么它有可能被他们拖垮。

例如，对于一个依赖于30个服务的应用程序，每个服务都有99.99%的正常运行时间，你可以期望如下：
99.99*30  =  99.7% 可用
也就是说一亿个请求的0.03% = 3000000 会失败
如果一切正常，那么每个月有2个小时服务是不可用的。现实通常是更糟糕 。
```



### 高并发流量

在高流量的情况下，使得平时几倍数量的用户涌入进来，对其中的服务I并发超过50+，这时对服务I出现一定程序的影响（逐渐导致CPU、内存占用过高等）。导致服务I延迟响应过慢等。随着压力的增加，服务I承受不住压力或发生内部错误导致机器内部资源耗尽。 更糟糕的是其它服务对I有依赖，导致其它服务也同样出现请求堆积、资源占用等问题，此时会导致整个系统大面积延迟或瘫痪。直到整个系统不可用。

![](images-hystrix\3.png)





### 服务容错的核心知识 

####  雪崩效应 

​	在微服务架构中，一个请求需要调用多个服务是非常常见的。如客户端访问A服务，而A服务需要调用B 服务，B服务需要调用C服务，由于网络原因或者自身的原因，如果B服务或者C服务不能及时响应，A服 务将处于阻塞状态，直到B服务C服务响应。此时若有大量的请求涌入，容器的线程资源会被消耗完毕， 导致服务瘫痪。服务与服务之间的依赖性，故障会传播，造成连锁反应，会对整个微服务系统造成灾难 性的严重后果，这就是服务故障的“雪崩”效应。（**服务熔断**和**服务降级**就可以视为解决服务雪崩的手段之一）

#### 服务隔离 

​	顾名思义，它是指将系统按照一定的原则划分为若干个服务模块，各个模块之间相对独立，无强依赖。 当有故障发生时，能将问题和影响隔离在某个模块内部，而不扩散风险，不波及其它模块，不影响整体 的系统服务。



#### 服务熔断

​	**熔断这一概念来源于电子工程中的断路器（Circuit Breaker）**。

在互联网系统中，当下游服务因访问压力过大而响应变慢或失败，上游服务为了保护系统整体的可用性，可以暂时切断对下游服务的调用。这种牺牲局部，保全整体的措施就叫做**熔断**。



#### 服务降级 

​	**降级**，就是当某个服务熔断之后，服务器将不再被调用，此时客户端可以自己准备一个本地的 fallback回调，返回一个缺省值。 



#### 服务限流 

​	限流可以认为服务降级的一种，限流就是限制系统的输入和输出流量已达到保护系统的目的。一般来说 系统的吞吐量是可以被测算的，为了保证系统的稳固运行，一旦达到的需要限制的阈值，就需要限制流 量并采取少量措施以完成限制流量的目的。比方：推迟解决，拒绝解决，或者者部分拒绝解决等等。



### 解决

**在这种多系统和微服务的情况下，需要一种机制来处理延迟和故障，并保护整个系统处于可用稳定的状态。**

**这就是Hystrix。**

Hystrix是由Netﬂix开源的一个延迟和容错库，用于隔离访问远程系统、服务或者第三方库，防止级联失败，从而提升系统的可用性与容错性。

**Hystrix主要通过以下几点实现延迟和容错:**

>  **包裹请求：**使用HystrixCommand包裹对依赖的调用逻辑，每个命令在独立线程中执行。这使用 了设计模式中的“命令模式”。 
>
> **跳闸机制：**当某服务的错误率超过一定的阈值时，Hystrix可以自动或手动跳闸，停止请求该服务 一段时间。 
>
> **资源隔离：**Hystrix为每个依赖都维护了一个小型的线程池（或者信号量）。如果该线程池已满， 发往该依赖的请求就被立即拒绝，而不是排队等待，从而加速失败判定。 
>
> **监控：**Hystrix可以近乎实时地监控运行指标和配置的变化，例如成功、失败、超时、以及被拒绝 的请求等。 
>
> **回退机制：**当请求失败、超时、被拒绝，或当断路器打开时，执行回退逻辑。回退逻辑由开发人员 自行提供，例如返回一个缺省值。 
>
> **自我修复：**断路器打开一段时间后，会自动进入“半开”状态。

### Hystrix设计原则

1. 通过客户端库对延迟和故障进行保护机制
2. 在一个复杂的分布式系统中停止级联故障
3. 快速失败和迅速恢复
4. 在合理的情况下回退和优雅的降级
5. 开启近实时监控、告警和操作控制



## 导入Hystrix

在Feign中，默认是自带Hystrix的功能。但是新版本 默认情况下是禁用的,需要我们手动开启

### 导入相关Hystrix包

```xml
<!--引入hystrix Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
<!--引入hystrix End  -->
```

### 开启Hystrix application.yml

```bat
feign:
  hystrix:
    enabled: true  #默认情况下 feign屏蔽了hystrix 需要将其开启改为true
```

```bat
#server config info
server:
  port: 8080
  servlet:
    context-path: /dudu-meeting-feign
spring:
  application:
    name: dudu-meeting

# eureka 服务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
  instance:  #指定Eureka 注册的实例名称
    instance-id: dudu-meeting-${server.port}
    prefer-ip-address: true #访问Eureka IP地址
feign:
  hystrix:
    enabled: true  #默认情况下 feign屏蔽了hystrix 需要将其开启改为true
```

### 主程序入口

**@EnableHystrix**  //开启断路器 Hystrix注解

```java
@SpringBootApplication
@MapperScan(basePackages = {"com.qfjy.dudu.mapper"})
@EnableDiscoveryClient  //Eureka注册中心发现
@EnableFeignClients  //扫描所有带@FeignClient注解的类 进行容器Bean管理
@EnableHystrix  //开启断路器 Hystrix注解
// #### TODO  @SpringCloudApplication是 组合注解。 
public class DuduMeetingFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduMeetingFeignApplication.class, args);
    }
}
```



## 三Hystrix熔断和降级

>  	服务熔断一般是指软件系统中，由于某些原因使得服务出现了过载现象，为防止造成整个系统故障，从而采用的一种保护措施，所以很多地方把熔断亦称为过载保护。
>
> 服务熔断 类似现实生活中的“保险丝“，当某个异常条件被触发，直接熔断保险丝来起到保护电路的作用，

`服务降级：当服务内部出现异常情况，将触发降级，这个降级是每次请求都会去触发，走默认处理方法defaultFallback`

`服务熔断：在一定周期内，服务异常次数达到设定的阈值或百分比，则触发熔断，熔断后，后面的请求将都走默认处理方法`

@HystrixCommand 注解

```bat
1设置 fallbackMethod 指定的 返回值方法类型要跟目标方法一致，否则将报错。
2如果方法内部有明显的异常,将不走目标方法，直接返回 fallback 方法的返回值。
```

### 代码示例

> ```java
> @GetMapping("info")
> @ResponseBody
> @HystrixCommand(fallbackMethod = "hystrixInfo")
> public String info(@RequestParam("id") Integer id){
>     if(id!=123){
>         throw new RuntimeException(("该ID没有对应数据信息"));
>     }
>     return "正常情况下的访问情况"+id;
> }
> 
> public String hystrixInfo(@RequestParam("id") Integer id){
>     return "启动熔断处理程序"+id;
> }
> ```

### 全局示例

**上面存在的问题：**

```bat
每个业务方法对应一个fallbackMethod方法，代码变的很臃肿
```

**1、在上方代码类 上方 声明注解： 指定当前类全部默认方法**

```bat
@DefaultProperties(defaultFallback = "defaultFallBack")
```

**2、全局默认方法如下：**

> ```java
> public String defaultFallBack(){
>     return "服务器开小差了....";
> }
> ```

**3、对需要触发降级的方法添加注解@HystrixCommand。不写指定降级，默认走全局回调方法**

> ```java
> @GetMapping("info1")
> @ResponseBody
> @HystrixCommand 
> public String info1(@RequestParam("id") Integer id){
>     if(id!=123){
>         throw new RuntimeException(("该ID没有对应数据信息"));
>     }
>     return "正常情况下的访问情况"+id;
> }
> ```

**4、高级熔断配置**

```bat
circuitBreaker.requestVolumeThreshold //请求规模的阀值，默认为20
circuitBreaker.sleepWindowInMilliseconds //过多长时间，熔断器再次检测是否开启，默认为5000，即5s钟
circuitBreaker.errorThresholdPercentage //错误率，默认50%
每当20个请求中，有50%失败时，熔断器就会打开，此时再调用此服务，将会直接返回失败，不再调远程服务。直到5s钟之后，重新检测该触发条件，判断是否把熔断器关闭，或者继续打开。
```

`注意：熔断是否开启熔断器主要由依赖调用的错误比率决定的，依赖调用的错误比率=请求失败数/请求总数。Hystrix中断路器打开的默认请求错误比率为50%（这里暂时称为请求错误率），还有一个参数，用于设置在一个滚动窗口中，打开断路器的最少请求数（这里暂时称为滚动窗口最小请求数），这里举个具体的例子：如果滚动窗口最小请求数为默认20，在一个窗口内（默认10秒，统计滚动窗口的时间可以设置），收到19个请求，即使这19个请求都失败了，此时请求错误率高达95%，但是断路器也不会打开。对于被熔断的请求，并不是永久被切断，而是被暂停一段时间（默认是5000ms）之后，允许部分请求通过，若请求都是健康的（ResponseTime<250ms）则对请求健康恢复（取消熔断），如果不是健康的，则继续熔断。（这里很容易出现一种错觉：多个请求失败但是没有触发熔断。这是因为在一个滚动窗口内的失败请求数没有达到打开断路器的最少请求数）`

**完整代码类如下**

> ```java
> **
>  * @ClassName MeetingPubController
>  * @Description TODO
>  * @Author guoweixin
>  * @Version 1.0
> */
> @RequestMapping("meetingPub")
> @Controller
> @Slf4j
> @DefaultProperties(defaultFallback = "defaultFallBack")
> public class MeetingPubController {
> @Autowired
>  private MeetingPubService meetingPubService;
> 
> @GetMapping("info")
>  @ResponseBody
>  @HystrixCommand(fallbackMethod = "hystrixInfo")
>  public String info(@RequestParam("id") Integer id){
>      if(id!=123){
>          throw new RuntimeException(("该ID没有对应数据信息"));
>      }
>      return "正常情况下的访问情况"+id;
>  }
>  public String hystrixInfo(@RequestParam("id") Integer id){
>      return "启动熔断处理程序"+id;
>  }
> 
> @GetMapping("info1")
>  @ResponseBody
>  @HystrixCommand(commandProperties = {
>          @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
>          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "6"),
>          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
>          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
>  })
>  public String info1(@RequestParam("id") Integer id){
>      if(id!=123){
>           System.out.println("走这个方法啦...");
>          throw new RuntimeException(("该ID没有对应数据信息"));
>      }
>      return "正常情况下的访问情况"+id;
>  }
>  public String defaultFallBack(){
>      return "服务器开小差了....";
>  }
> 
> }
> ```

## 四 Feign使用Hystrix 降级

### 创建降级fallback类

**创建impl包，在该包下创建 FeignClient接口 的实现类**

![](images-hystrix\5.png)



### 修改FeignClient接口

**@FeignClient注解：fallback 如果接口服务不能访问或延迟，则运行对应其接口实现类。**

```
当配置fallback的时候,如果接口中的方法出现问题,就会去执行指定类里面的方法,因为类里面的方法可以随便 写,所以这个类有特殊要求,必须实现当前feign接口 
```

![](images-hystrix\6.png)

### 运行测试

1. 当不启动dudu-user(提供者时)，并feign.hystrix.enabled=false时。该问请求，发生500错误。
2. 当不启动dudu-user(提供者时)，并feign.hystrix.enabled=true时。该问请求，(显示友好信息）结果得到默认User对象数据。

**此时，Hystrix已经产生了作用。正确运行**



### 总结

> 服务降级：
>
>  	1、程序运行异常
>
> ​	2、超时连接
>
> ​	3、服务熔断导致服务降级

`服务降级：当服务内部出现异常情况，将触发降级，这个降级是每次请求都会去触发，走默认处理方法defaultFallback`

`服务熔断：在一定周期内，服务异常次数达到设定的阈值或百分比，则触发熔断，熔断后，后面的请求将都走默认处理方法`

## 五、Hystrix超时配置

`Hystrix`默认超时时间是1秒，所以这里需要自己重新设置

### 全局配置

如果只是想全局的配置，可以配置默认的超时时间：

```bat
hystrix:
  command:
    default:  #default全局有效，service id指定应用有效
      execution:
        timeout:
          #是否开启超时熔断
          enabled: true
        isolation:
          thread:
            timeoutInMilliseconds: 4000 #断路器超时时间，默认1000ms
```

当规定的时间内，微服务无法提供服务时。Hystrix会自动触发熔断和降级功能。



## 六、Hystrix熔断高级配置

我们知道，当请求失败，被拒绝，超时的时候，都会进入到降级方法中。

但进入降级方法并不意味着断路器已经被打开。那么如何才能了解断路器中的状态呢



### 6.1 Hystrix的监控平台

除了实现容错功能，Hystrix还提供了近乎实时的监控，在执行时，会生成执行结果和运行指标。

比如每秒的请求数量，成功数 量等。**这些状态会暴露在Actuator提供的/health端点中。**

只需为项目添加 spring-boot-actuator 依 赖，重启项目，

访问http://localhost:8080/actuator/hystrix.stream ,即可看到实时的监控数据

#### 导入Pom

在dudu-meeting-feign：

```bat
       <!--监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

#### DuduMeeting入口类

```bat
@MapperScan(basePackages = {"com.qfjy.dudu.mapper"})
@EnableFeignClients
/* 此处可以用组合注解
 * @EnableHystrix/@EnableCircuitBreaker
 * @SpringBootApplication
 * @EnableDiscoveryClient
 */
@SpringCloudApplication
public class DuduMeetingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduMeetingApplication.class, args);
    }
}
```



#### applicaiton.yml

配置 include '*' :暴露所有端点信息

```
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

访问：

<http://localhost:8080//dudu-meeting-feign/actuator/hystrix.stream>

测试：发送请求 可看到监控平台信息数据。



### 6.2 Hystrix DashBoard

Hystrix Dashboard仪表盘是根据系统一段时间内发生的请求情况来展示的可视化面板，这些信息是每个HystrixCommand 执行过程中的信息，这些信息是一个指标集合和具体的系统运行情况。

##### 作用：

- 实时监控各个Hystrix commandKey的各种指标值
- 通过 Dashboards 的实时监控来动态修改各种配置(图形化)



#### 新建HystrixDashBoard工程

创建SpringBoot微服务项目

![](images-hystrix\7.png)



#### 导入Pom

```xml
 <parent>
        <groupId>com.qfjy</groupId>
        <artifactId>dudu</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>


    <groupId>com.qfjy.dudu</groupId>
    <artifactId>hystrix-dashboard</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>hystrix-dashboard</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
       	<!--SpringWeb 依赖包 开始--> 
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
		<!--SpringWeb 依赖包 结束-> 
		<!--SpringCloud  Hystrix DashBoard依赖包 开始-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
            <version>LATEST</version>
        </dependency>
		<!--SpringCloud  Hystrix DashBoard依赖包 结束 -->
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

#### 主程序入口

添加 @EnableHystrixDashboard 注解

```java
@SpringBootApplication
@EnableHystrixDashboard  // 启动 HystrixDashboard注解
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
```

#### application.yml

```bat
server:
  port: 9080
```

#### 运行应用程序

浏览器访问 <http://localhost:9080/hystrix>  即可看到如下页面：

![](images-hystrix\8.png)



### 6.3 实践应用

已知我们对 dudu-meeting-feign 微服务 进行了  actuator 监控。

![](images-hystrix\9.png)



如下显示：

![](images-hystrix\10.png)

**关于监控图：**
圆圈：它代表流量的大小和流量的健康。有绿色、黄色、橙色、红色这几个颜色。通过这些颜色的标训，可以快速发现故障，具体的实例、请求压力等。
曲线：它代表2分钟内流量的变化。可以根据它发现流程的浮动趋势。

**圆圈右边的数字：**  
左侧第1开开始：

**成功数量、  熔断数、错误的请求**

**超时的请求、线程池拒绝数、失败的请求。和最近100秒内错误的比率。**





## 七、Turbine聚合Hystrix

​	在上节讲了单个实例的Hystrix Dashboard，但在整个系统和集群的情况下不是特别有用。所以需要一种方式来聚合整个集群下的监控状态。Turbine就是来聚合所有相关的Hystrix Stream流的方案。然后在Hystrix Dashboard中显示。



### 搭建微服务工程

turbine-hystrix



### 导入Pom

完整的POM

```bat
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
    <artifactId>hystrix-trubine</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>hystrix-trubine</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

		<!-- 导入netflix-turbine  start   内包含Eureka和Web -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
        </dependency>
        <!--导入 netflix-hystrix 相关即可-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>


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

### application.yml

```yml
server:
  port: 9088

spring:
  application:
    name: hystrix-trubine

# eureka
eureka:
  client:
    service-url:
      defaultZone: http://Wilson:123456@localhost:9001/eureka/
  instance:
    prefer-ip-address: true

trubine:
  appConfig: dudu-meeting-feign   # eureka服务中的名称，多个用 , 分隔
  clusterNameExpression: "'default'"

```





### 启动类

**HystrixTrubineApplication** 

依次导入注解：

@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient

```java
@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
public class HystrixTrubineApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTrubineApplication.class, args);
    }

}
```



### 运行应用程序

<http://localhost:9088/hystrix>

这里就是聚合了appConfig配置的多个微服务的接口日志信息
把这个地址 根路径+/turbine.stream 进行监控即可

http://localhost:9088/turbine.stream?cluster=default