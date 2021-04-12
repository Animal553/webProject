# Spring Cloud  Open Feign



## 前奏

​     使用SpringCloud开发微服务应用时，各个服务提供者都是以HTTP接口的形式对外提供服务，因此在服务消费者调用服务提供者时，底层通过HTTP Client的方式访问。

可以使用JDK原生的**URLConnection**, Apache的**HTTP Client**，Spring的**RestTemplate**去实现服务间的调用。

**Spring cloud 有两种服务调用方式，一种是 ribbon + restTemplate，另一种是 feign。**

## 一、Feign

### 1 什么是Feign

　Feign是一个声明似的web服务客户端。

> Feign是Netﬂix开发的声明式，模板化的HTTP客户端，它使得编写web服务客户端变得更加容易
>
> Feign可帮助我们更加便捷，优雅的调用HTTP API。 
>
> 在SpringCloud中，使用Feign非常简单——创建一个接口，并在接口上添加一些注解，代码就完 成了。 Feign支持多种注解，例如Feign自带的注解或者JAX-RS注解等。
>
>  SpringCloud对Feign进行了增强，使Feign支持了SpringMVC注解，并整合了Ribbon和Eureka， 从而让Feign的使用更加方便。 



​    Spring Cloud 集成 Ribbon 和 Eureka 提供的负载均衡的HTTP客户端Feign。**(Feign默认集成了Ribbon，并和Eureka结合)**。默认实现了负载均衡的效果。

**特点：**

Feign是一种负载均衡的HTTP客户端, 使用Feign调用API就像调用本地方法一样

支持Hystrix和它的Fallback

**支持Ribbon的负载均衡**

支持HTTP请求和响应的压缩。



### 2 会议微服务Feign

####  创建springboot dudu-meeting-fegin

**功能：springboot+ssm 根据会议编号完成会议信息查询**

![](images-ribbon-feign\4.png)

#### pom.xml（导入Feign和Eureka）

```xml
  <!-- 导入Spring Cloud 组件 -->
<!-- 客户端 Spring Cloud  eureka client Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<!-- 客户端 Spring Cloud eureka client End -->
<!-- openfeign 包含ribbon  Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<!--  openfeign End-->
```

#### 完整的pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.qfjy</groupId>
        <artifactId>dudu</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

   <groupId>com.qfjy.dudu</groupId>
    <artifactId>dudu-meeting-feign</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dudu-meeting-feign</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

        <!-- 导入Spring Cloud 组件 -->
        <!-- 客户端 Spring Cloud  eureka client Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 客户端 Spring Cloud eureka client End -->
        <!-- openfeign 包含ribbon  Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--  openfeign End-->


        <!-- 公共的 common-->
        <dependency>
            <groupId>com.qfjy</groupId>
            <artifactId>dudu-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- 公共的api服务模型、接口-->
        <dependency>
            <groupId>com.qfjy</groupId>
            <artifactId>dudu-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>


        <!-- spring web mvc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- spring mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis-spring-boot-starter.version}</version>
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
            <version>${druid-spring-boot.version}</version>
        </dependency>

        <!-- 热部署 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

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
            <version>${springfox-swagger2.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${springfox-swagger2.version}</version>
        </dependency>
        <!-- Swagger2 End -->


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



#### application.yml

```yml
#server config info
server:
  port: 8080
  servlet:
    context-path: /dudu-meeting-feign
spring:
  application:
    name: dudu-meeting-feign
    .................
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
  instance:  #指定Eureka 注册的实例名称
    instance-id: dudu-meeting-8080
    prefer-ip-address: true #访问Eureka IP地址
```

#### 访问URL

**测试会议微服务是否正常访问**



### 3 Feign方式调用

```
服务与服务间完成调用Feign
```

#### 支持Ribbon的负载均衡

```java
package com.qfjy.dudu.config;
/**
 * @ClassName RestTemplateConfig
 * @Description TODO
 * @Author guoweixin
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced // Feign支持Ribbon的负载均衡
    public org.springframework.web.client.RestTemplate restTemplate(){
        return  new org.springframework.web.client.RestTemplate();
    }
}
```

#### **FeignClient接口**

**创建接口（像本地中调用）当前消费者需要调用服务提供者中的信息**

![](images-ribbon-feign\5.png)

```java
package com.qfjy.dudu.client;
/**
    用于通知Feign组件对该接口进行代理(不需要编写接口实现)，
    name属性指定我们要调用哪个服务。使用者可直接通过@Autowired注入。
     DUDU-USER是Eureka中Application
    path 定义当前FeignClient统一前缀（用户微服务上下文路径是：dudu-user
 */
@FeignClient(name = "DUDU-USER",path = "dudu-user")
public interface UserServiceFeignClient {

    /**
        此处调用方法要和服务提供者
        UserController一致
     */
    @RequestMapping(value = "/user/{id}")
    public User selectById(@PathVariable("id") Integer id);

}
```



#### MeetingService中调用



![](images-ribbon-feign\6.png)

#### 应用程序入口

@EnableDiscoveryClient  //Eureka注册中心发现
**@EnableFeignClients**  //扫描所有带@FeignClient注解的类 进行容器Bean管理

```java
package com.qfjy.dudu;

@SpringBootApplication
@MapperScan(basePackages = {"com.qfjy.dudu.mapper"})
@EnableDiscoveryClient  //Eureka注册中心发现
@EnableFeignClients  //扫描所有带@FeignClient注解的类 进行容器Bean管理
public class DuduMeetingFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuduMeetingFeignApplication.class, args);
    }

}
```



### 4 注解描述总结

#### **@FeignClien**t

用于通知Feign组件对该接口进行代理(不需要编写接口实现)，

**name属性**  指定FeignClient名称。指定我们要调用哪个服务。name属性会作为微服务的名称，用于服务发现。使用者可直接通过@Autowired注入。

**path属性** 定义当前FeignClient的统一前缀

**fallback属性** 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑。fallback指定的类必须实现@FeignClient标记的接口



### 5、负载均衡

Feign中本身已经集成了Ribbon依赖和自动配置，固负载均衡同样适用。因此我们不需要额外引入依赖，也不需要再注册 RestTemplate 对象。





## 二、Feign GZIP压缩

### 开启压缩功能

**Spring Cloud Feign 支持对请求和响应进行GZIP压缩，以减少通信过程中的性能损耗。**（如果不经过压缩就发送请求、获取响应，那么会因为流量过大导致浪费流量，这时就需要使用数据压缩，将大流量压缩成小流量。）

**Spring Cloud Feign 支持对请求和响应进行 GZIP 压缩，以调高通信效率。**

通过下面的参数配置 即可开启请求与响应的压缩功能。

```bat
# feign gzip
feign:
  compression:
    request:
      enabled: true  #开启请求压缩
    response:
      enabled: true  #开启响应压缩
```

**注意:由于使用 gzip 压缩，压缩后的数据是二进制，那么在获取 Response 的时候，需要使用 ResponseEntity<byte[]> 接收**



### 验证是否压缩

开启 FeignClient 日志

> 在开发或者运行阶段往往希望看到Feign请求过程的日志记录，默认情况下Feign的日志是没有开启的。 
>
> 要想用属性配置方式来达到日志效果，只需在 application.yml 中添加如下内容即可：

```bat
# feign gzip
feign:
  compression:
    request:
      enabled: true
    response:
      enabled: true
  client:
    config:
      DUDU-USER:  #微服务的名称 FeignClient 的name
        loggerLevel: FULL
logging:
  level:
    com.qfjy.dudu.client.UserFeignClient: debug  # Feign日志只会对日志级别为debug的做出响应
```



**feign.client.config.XXX.loggerLevel :**   Feign日志只会对日志级别为debug的做出响应

**配置Feign的日志Feign有四种 日志级别：** 

> ​	NONE【性能佳，适用于生产】：不记录任何日志（默认值）
>
> ​	 BASIC【适用于生产环境追踪问题】：仅记录请求方法、URL、响应状态代码以及执行时间 
>
> ​	HEADERS：记录BASIC级别的基础上，记录请求和响应的header。 
>
> ​	FULL【比较适用于开发及测试环境定位问题】：记录请求和响应的header、body和元数据



### 总结

1、在日志中查看，如果开启了压缩，可看到 Accept-Encoding: gzip

2、在request请求中，开启 gzip 之后，request 是：

```bat
[UserFeignClient#selectById] ---> GET http://DUDU-USER/dudu-user/user/34 HTTP/1.1
2020-02-15 23:03:37.324 DEBUG 15672 --- [nio-8080-exec-1] com.qfjy.dudu.client.UserFeignClient : [UserFeignClient#selectById] Accept-Encoding: gzip
2020-02-15 23:03:37.325 DEBUG 15672 --- [nio-8080-exec-1] 
2020-02-15 23:03:37.325 DEBUG 15672 --- [nio-8080-exec-1] com.qfjy.dudu.client.UserFeignClient  : [UserFeignClient#selectById] ---> END HTTP (0-byte body)
```

可以看到，request 中增加了 `Accept-Encoding: gzip`，证明 request 开启了 gzip 压缩。





## 三、Feign 超时配置

Feign调用服务的默认时长是1秒钟，也就是如果超过1秒没连接上或者超过1秒没响应，那么会相应的报错。而实际情况是因为业务的不同可能出现超出1秒的情况，这时我们需要调整超时时间。

最重要的两个超时就是**连接超时ConnectTimeout**和**读超时ReadTimeout**，

### 问题描述

服务的提供者方法中让其休眠1秒。以看下调用效果。

```
Thread.sleep(1000);
```

```bat
2020-02-15 23:19:44.154 DEBUG 15484 --- [nio-8080-exec-1] com.qfjy.dudu.client.UserFeignClient     : [UserFeignClient#selectById] <--- END ERROR
2020-02-15 23:19:44.171 ERROR 15484 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/dudu-meeting-feign] threw exception [Request processing failed; nested exception is feign.RetryableException: Read timed out executing GET http://DUDU-USER/dudu-user/user/34] with root cause

java.net.SocketTimeoutException: Read timed out
```

### 解决方案

Feign 的负载均衡底层用的就是 **Ribbon**
在application.yml中添加如下配置，超过5秒没连接上报连接超时，如果超过5秒没有响应，报请求超时

```bat
feign:
  client:
    config:
      default:
        connectTimeout: 5000  
        readTimeout: 5000  
```

完整的Feign 配置

```bat
# feign gzip
feign:
  compression:
    request:
      enabled: true   #开启请求压缩
    response:
      enabled: true    #开响响应压缩
  client:
    config:
      DUDU-USER:
        loggerLevel: FULL
      default:
        connectTimeout: 5000  #连接超时
        readTimeout: 5000  #读超时
logging:
  level:
    com.qfjy.dudu.client.UserFeignClient: debug
```



## 四、Feign 多参数传递

### Get多参数传递

#### 示例1：（Get传递多参数)

我们知道Spring Cloud为Feign添加了Spring MVC的注解支持，那么我们不妨按照Spring MVC的写法尝试一下：

```java
  /** 在 dudu-user 服务提供者中 编写一个方法。
      服务消费者dudu-meeting 通过feign方式进行调用，看能否传递？
    * 传递一个对象 进行打印
  */
@RequestMapping(value ="selectUser",method = RequestMethod.GET)
@ResponseBody
public String  selectUser(User user){
    return "打印的结果如下："+user.toString();
}
```

然而我们通过swagger 测试时会发现该写法不正确，我们将会收到类似以下的异常：

```bat
{
  "timestamp": "2020-02-15T16:33:18.163+0000",
  "status": 500,
  "error": "Internal Server Error",
  "message": "status 405 reading UserFeignClient#selectUser(User)",
  "path": "/dudu-meeting-feign/meetingpub/selectUser"
}
```

#### 解决方案

##### 方法一  @RequestParam

```java
  /**
     * 多个通数 可通过 @RequestParam 方式来完成  GET多个参数传递
     */
    @ResponseBody
    @RequestMapping(value = "selectByIdAndName",method =RequestMethod.GET)
    public User selectByIdAndName(@RequestParam("id")Integer id, @RequestParam("name")String name){
        User u=new User();
        u.setId(id);
        u.setName(name);
        return u;
    }
```

##### 方法二  Map类型

```java
@RequestMapping(value = "hello2", method = RequestMethod.GET)
public String  user2(@RequestParam Map<String, Object> map) {
    return userService.addUser(map);
}
```

##### 方法三

spring cloud项目使用feign的时候都会发现一个问题，就是get方式无法解析对象参数。其实feign是支持对象传递的，但是得是Map形式，而且不能为空，与spring在机制上不兼容，因此无法使用。

spring cloud在springboot 2.1.x版本中提供了**@SpringQueryMap**注解，可以传递对象参数，框架自动解析，



**服务消费者 dudu-meeting-feign  FeignClient**

```java
@FeignClient(name = "DUDU-USER",path ="dudu-user/user/" )
public interface UserFeignClient {

    /**
     * 传递一个对象 进行打印
     */
    @GetMapping(path = "user3")
    String user3(@SpringQueryMap  User user);

}
```

**服务消费者 dudu-meeting-feign  controller**

```java
@GetMapping(path = "user3")
public  String user3( User user){
    return  userFeignClient.user3(user);
}
```

**服务提供者dudu-user controller**

```java
    /**
     * 传递一个对象 进行打印
     */
@RequestMapping(value ="user3",method = RequestMethod.GET)
@ResponseBody
public String  user3(User user){
    return "打印的结果如下："+user.toString();
}
```













