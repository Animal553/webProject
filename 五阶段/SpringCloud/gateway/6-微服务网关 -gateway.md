

## SpringCloud Getway

### 简介

  Spring Cloud Gateway构建于 Spring 5+，基于 Spring Boot 2.x 响应式的、非阻塞式的 API。同时，它支持 websockets，和 Spring 框架紧密集成。



### 核心概念

根据如图，查看Spring Cloud Gateway重要的概念：

![](images-zuul\6-gateway.jpg)



```bat
路由(route)。路由是网关最基础的部分,路由信息由一个 ID、一个目的 url、一组断言工厂和一组Filter组成。如果路由断言为真,则说明请求的url和配置的路由匹配

断言( Predicate)Java8中的断言函数, Spring Cloud Gateway中的断言函数输人类型是 Spring5.0框架中的 Server Web Exchange. Spring Cloud Gateway中的断言函数允许开发者去定义匹配来自于 Http Request中的任何信息,比如请求头和参数等。

过滤器(filter)。一个标准的 Spring webFilter. Spring Cloud Gateway中的Fler分为
两种类型的 Filter,分别是 Gateway Filter和 Global Filter过滤器 Filter将会对请求和响应进行修改处理。
```

### 工作原理

![](images-zuul\7-gateway.jpg)



网关最重要的功能就是协议适配和协议转发，协议转发也就是基本的路由信息转发。入门案例将演示Spring Cloud Gateway的基本路由转发功能，通过Gateway的Path路由断言工厂实现URL直接转发。

### 入门案例Getway

#### dudu-getway

![](images-zuul\4.jpg)





#### pom

```xml
<!--Spring Cloud gateway Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<!--Spring Cloud gateway End -->
```

#### 路由配置：

 浏览器访问 localhost:8000/java  跳转到 第三方网站

```yml
server:
  port: 8000 #服务器端口号
spring:
  application:
    name: dudu-gateway #应用程序的名字
  cloud:
    gateway:
      routes:  #当访问8000时，直接跳转到第三方网站
        - id: weixin_route #
          uri: https://www.javaqf.com/
          predicates:
           - Path=/java/**
```

在上面的配置中，配置了一个Path 的predict,将以 /java/** 开头的请求都会转发到uri地址上



```bat
路由(route)。路由是网关最基础的部分,路由信息由一个 ID、一个目的 url、一组断言工厂和一组Filter组成。如果路由断言为真,则说明请求的url和配置的路由匹配

断言( Predicate)Java8中的断言函数, Spring Cloud Gateway中的断言函数输人类型是 Spring5.0框架中的 Server Web Exchange. Spring Cloud Gateway中的断言函数允许开发者去定义匹配来自于 Http Request中的任何信息,比如请求头和参数等。

过滤器(filter)。一个标准的 Spring webFilter. Spring Cloud Gateway中的Fler分为
两种类型的 Filter,分别是 Gateway Filter和 Global Filter过滤器 Filter将会对请求和响应进行修改处理。
```

```
访问url: http://localhost:8000/java/..
```



#### 路由断言

​           SpringCloud Gateway的路由匹配的功能是以 Spring WebFlux中的 Handler Mapping为基础实现的 Spring Cloud Gateway也是由许多的路由断言工厂组成的。

​           当 Http Request请求进人 SpringCloud Gateway的时候,网关中的路由断言工厂会根据配置的路由规则,对 Http Request请求进行断言匹配。匹配成功则进行下一步处理,否则断言失败直接返回错误信息。

下面我们介绍下 Spring Cloud Gateway中经常使用的路由断言工厂 。



#####  After路由断言工厂 

After Route Predicate Factory中会取一个 UTC时间格式的时间参数，

**当请求进来的当前时间在配置的UTC时间之后**，则会成功匹配，否则不能成功匹配。

```yml
server:
  port: 8000
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: weixin_route #
          uri: https://www.baidu.com/
          predicates:
           - After=2019-6-11T14:06:39.289+08:00[Asia/Shanghai]
```

其中-After UTFC时间 代码生成：

```java
//获取当前时间的前一个小时
String minTime=ZonedDateTime.now().minusHours(1).
    format(DateTimeFormatter.ISO_DATE_TIME);
System.out.println(minTime);	
```



此时再访问：则请求失败

```java
//获取当前时间的后一个小时
String minTime=ZonedDateTime.now().plusHours(1).
    format(DateTimeFormatter.ISO_DATE_TIME);
System.out.println(minTime);
```

##### Before路由断言工厂

Before路由断言工厂会取一个 UTC时间格式的时间参数 ,当请求进来的当前时间在路由断言工厂之前会成功匹配 ,否则不能成功匹配。



##### Between路由断言工厂

Between路由断言工厂会取一个 UTC时间格式的时间参数 ,当请求进来的当前时间在配置的UTC时间工厂之间会成功匹配，否则不能成功匹配。

**application.yml:**

```yml
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: weixin_route #
          uri: http://www.javaqf.com/
          predicates:
          - name: Between
            args:
              datetime1: 2019-6-11T14:06:39.289+08:00[Asia/Shanghai]
              datetime2: 2019-6-11T16:06:39.289+08:00[Asia/Shanghai]
```

##### Cookie路由断言工厂

路由断言工厂会取两个参数一cookie名称对应的 key和 value。当请求中携带的cookie和 Cookied断言工厂中配置的 cookie一致 ,则路由匹配成功 ,否则匹配不成功。

##### Header路由断言工厂

Header路由断言工厂用于根据配置的路由 header信息进行断言匹配路由 ,匹配成功进行转发 ,否则不进行转发。

##### Host路由断言工厂

Host路由断言工厂根据配置的 Host,对请求中的 Host进行断言处理 ,断言成功则进行路由转发 ,否则不转发。

##### Method路由断言工厂

Method路由断言工厂会根据路由信息配置的 method对请求方法是Get或者Post等进行断言匹配,匹配成功则进行转发,否则处理失败。

```yml
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: weixin_route #
          uri: http://www.javaqf.com/
          predicates:
          - Method=GET
```



##### Query路由断言工厂

Query路由断言工厂会从请求中获取两个参数,将请求中参数和Query断言路由中的配置进行匹配,
例如：http://localhost:8000?name=123中的 name=123和路由配置一致则请求成功。否则转发失败。

```yml
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: weixin_route #
          uri: http://www.javaqf.com/
          predicates:
          - Query=name, 123
```

<http://127.0.0.1:8000/?name=123>

##### RemoteAddr路由断言工厂

Remoted路由断言工厂配置一个 IPv4或 IPv6网段的字符串或者IP。当请求IP地址在 网段之内或者和配置的IP相同 ,则表示匹配成功 ,成功转发 ,否则不能转发。

```yml
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: weixin_route #
          uri: http://www.javaqf.com/
          predicates:
          - RemoteAddr=127.0.0.1
```



## Gateway基于服务发现路由



前面学习了Spring Cloud Gateway的Predict（断言），其中在对服务路由转发的这一块，是采用硬编码的方式进行路由转发。接下来将学习Spring Cloud Gateway如何配合服务注册中心进行路由转发。

#### pom引入Eureka

在gateway工程中引入项目所需的依赖，包括eureka-client的起步依赖和gateway的起步依赖

```xml
      <!--Spring Cloud gateway Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <!--Spring Cloud gateway End -->
        <!--Spring Cloud Eureka Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--Spring Cloud Eureka End -->
```

#### application.yml

```yml
server:
  port: 8000
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      routes:
        - id: dudu-user
          uri: lb://DUDU-USER
          predicates:
            - Path=/dudu-user/**
#eureka config
eureka:
  client:
    service-url:
      defaultZone: http://localhost:9001/eureka/
```



在上面的配置中，配置了一个Path 的predict,将以/dudu-user/**开头的请求都会转发到uri为lb://DUDU-USER的地址上，lb://DUDU-USER即service-hi服务的负载均衡地址，并在转发之前将/dudu-user去掉。

```xml
正确访问路径：
http://localhost:8000/dudu-user/user/1
```



#### 高级配置

**spring.cloud.gateway.discovery.locator.enabled为true**

```
表明gateway开启服务注册和发现的功能，并且spring cloud gateway自动根据服务发现为每一个服务创建了一个router，这个router将以服务名开头的请求路径转发到对应的服务。
```

**spring.cloud.gateway.discovery.locator.lower-case-service-id为true**

```
是将请求路径上的服务名配置为小写（因为服务注册的时候，向注册中心注册时将服务名转成大写的了），比如以/service-hi/*的请求路径被路由转发到服务名为service-hi的服务上。
```

```yml
server:
  port: 8000
spring:
  application:
    name: dudu-gateway
  cloud:
    gateway:
      discovery:
        locator:
          lower-case-service-id: true
          enabled: true

eureka:
  client:
    service-url:
      defaultZone: http://localhost:9001/eureka/
```

访问成功：

```
http://localhost:8000/dudu-user/dudu-user/user/1
http://localhost:8000/dudu-meeting/dudu-meeting/meetingpub/02372a1b-5aa7-4333-abe0-55899b5c74b7
```

