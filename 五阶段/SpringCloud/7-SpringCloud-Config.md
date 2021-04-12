# Spring Cloud Config



# 一、Cloud Config概述

## 问题

在分布式、微服务架构中当前所存在的问题：

- 配置文件分散在各个项目里，不方便维护
- 配置内容安全与权限，实际开发中，开发人员是不知道线上环境的配置的
- 更新配置后，项目需要重启

## 简介

​		Spring Cloud Config 是Spring Cloud微服务体系中的配置中心，是微服务中不可或缺的一部分。其能够很好的将程序中日益增多的配置的各种功能的开关、参数的配置、服务器的地址，配置修改后实时生效、灰度发布，分环境、分集群管理配置等进行全面的集中化管理，有利于系统的配置管理和维护。

   如下主要讲解 SpringCloud 构建微服务中如何使用SpringCloud Config配置中心、扩展配置中心以及三方配置中心Apollo的接入。



## 什么是Config 

​		Spring Cloud Config 项目是一个解决分布式系统的配置管理方案。它包含了Client和Server两个部分，server提供配置文件的存储、以接口的形式将配置文件的内容提供出去，client通过接口获取数据、并依据此数据初始化自己的应用。

​		Spring Cloud Config 它不依赖于注册 中心，是一个独立的配置中心。Config支持多种存储配置信息的形式，目前主要有JDBC、Native、SVN、GIT。其中默认为GIT。

![](images-config\1.png)







# 二、Spring Cloud Config入门案例

## 1 Config-Server创建和配置

### 1.1 远程GIT仓库的创建

在远程的git上新建一个存放配置文件的仓库，我这里使用的是码云：

![](images-config\2.png)

创建好后，新建一个文件，然后把 dudu-user 用户微服务的配置文件内容粘贴进来：

dudu-user.yml

```yml
server:
  port: 8081
  servlet:
    context-path: /dudu-user
spring:
  info: dudu-user-info-dev/test/prod #此处内容根据不同环境来编写测试即可
  application:
    name: dudu-user
  #datasource connect info
  datasource:
    name: test
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      url: jdbc:mysql://localhost:3306/qfdnj?serverTimezone=GMT%2B8
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
# mybatis config info
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.qfjy.dudu.po


# eureka
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://Wilson:123456@localhost:9001/eureka/,http://Wilson:123456@localhost:9002/eureka/,http://Wilson:123456@localhost:9003/eureka/
  instance:
    prefer-ip-address: true
    instance-id: dudu-user-${server.port}
```



### 1.2 创建微服务 dudu-config-server

#### pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!-- 依赖父工程 -->
    <parent>
        <groupId>com.qfjy</groupId>
        <artifactId>dudu</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <groupId>com.qfjy.dudu</groupId>
    <artifactId>dudu-config-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dudu-config-server</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--导入cloud config-server依赖 Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
         <!--导入cloud config-server依赖 End -->
        
        <!-- 客户端 Spring Cloud  eureka client Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 客户端 Spring Cloud eureka client End -->
        
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

**@EnableConfigServer**   //开启Spring cloud  Config服务功能

```java

@SpringBootApplication
@EnableConfigServer   //开启Spring cloud  Config服务功能
public class DuduConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduConfigServerApplication.class, args);
    }

}
```

#### application.yml

```yml
server:
  port: 9005
spring:
  cloud:
    config:
      server:
        git:
          uri: https://gitee.com/guoweixin/spring-cloud-config-repo-1.git
          #username:
          #password:
  application:
    name: dudu-config-server
    
# eureka
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://Wilson:123456@localhost:9001/eureka/,http://Wilson:123456@localhost:9002/eureka/,http://Wilson:123456@localhost:9003/eureka/
  instance:
    prefer-ip-address: true
    instance-id: dudu-config-server-${server.port}
```

> uri:git服务器的地址，
>
> username和password是git访问的用户名和密码。
>
> search-paths：可以搜索指定目录下所有满足条件的配置文件。（DUDU-CONFIG),可以根据需求添加多个目录，目录之间用逗号隔开。



### 1.3 启动运行

**启动项目，访问如下地址，可以看到能够访问到配置文件的内容：**

> 如果访问.properties格式的，还会自动进行转换： ...dudu-user.properties
>
> .json格式的也能够进行转换：  dudu-user.json
>
> 注：如果配置文件的内容格式有问题的话，访问会报500错误。我们可以利用这个特性，来检查我们的配置文件是否正确

<http://localhost:9005/dudu-user.yml>

![](images-config\3.png)



#### 本地GIT

此时观察 控制台的打印信息，config-server会在本地的临时目录下面克隆远程仓库中的配置信息。临时目录如下：

```yml
2020-02-26 15:50:41.481  INFO 3028 --- [nio-9005-exec-6] o.s.c.c.s.e.NativeEnvironmentRepository  : Adding property source: file:/C:/Users/GUOWEI~1/AppData/Local/Temp/config-repo-6622443480039113975/DUDU-CONFIG/config-info-dev.yml
```









#### 默认规则

可再次访问：<http://localhost:9005/dudu-user-aaa.yml>

> 发现访问的配置文件名后面还有一个-aaa，也能成功。
>
> 这其实是config的访问规则。后面必须要跟个-xxx，所以在创建文件的时候，最好是按这种命名规则来创建。

配置文件的访问规则如下：

```yml
/{name}-{profiles}.yml
/{label}/{name}-{profiles}.yml

name : 文件名，一般以服务名来命名
profiles : 一般作为环境标识
lable : 分支（branch），指定访问某分支下的配置文件  默认是 master
```





### **1.4 SpringBoot多环境配置**

​		随着后期微服务数量的增加，配置文件的数量自然也会随着增加，而且实际的企业项目中都会在不同的部署环境使用不同的配置文件，例如开发环境（dev）、测试环境（test）、生产环境（product）等。所以一个服务至少会有三个以上的配置文件。



springboot多环境（dev、test、prod）配置

> 在Spring Boot中多环境配置文件名需要满足application-{profile}.yml的格式，其中{profile}对应你的环境标识，比如：
>
> application-dev.yml：开发环境
> application-test.yml：测试环境
> application-prod.yml：生产环境
>
> 如：spring.profiles.active=dev就会加载application-dev.yml配置文件内容

![](images-config\4.png)



**这时我们很自然的会想到将不同服务的配置文件放到以服务名命名的目录下**

![](images-config\5.png)





**完成如上文件夹分类后，此刻会发现这些配置文件全都加载不到了。**



因为config server默认情况下只会搜索git仓库根路径下的配置文件，

所以我们还需要加上一个配置项：**search-paths**，该配置项用于指定config server搜索哪些路径下的配置文件，需要注意的是这个路径是相对于git仓库的，并非是项目的路径。

#### dudu-config-server  pom.xml

```yml
server:
  port: 9005
spring:
  cloud:
    config:
      server:
        git:
          uri: https://gitee.com/guoweixin/spring-cloud-config-repo-1.git
          #username:
          #password:
          search-paths: /**  # 指定搜索根路径下的所有目录，若有多个路径使用逗号隔开
  application:
    name: dudu-config-server
```



## 2 Config Client配置

在上小节中，我们介绍了config-server的使用以及配置文件的访问规则，

本小节将介绍config-client端的使用，我们以用户微服务  dudu-user 为例。

### dudu-user pom.xml

在用户微服务工程的pom.xml文件中，增加如下依赖配置：

```xml
  <!--Spring-Cloud Config Client依赖  Start-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-client</artifactId>
        </dependency>
 <!--Spring-Cloud Config Client依赖  End-->
```

### bootstrap.yml

此时，git仓库已存在原有的dudu-user 的配置文件，

并依次三份文件：dudu-user-dev.yml dudu-user-test.yml dudu-user-prod.yml

**然后将application.yml重命名为bootstrap.yml，并修改内容如下：**

```yml
spring:
  application:
    name: dudu-user  #当前应用名称，当前值和下面的profile组合起来即是访问路径，此处：dudu-user-dev.yml
  cloud:
    config:  #配置config 地址
      enabled: true  # 启动config配置 默认即为true 
      discovery:
        enabled: true   #启动服务发现
        service-id: dudu-config-server  #注册中心的config-server 名字
      profile: dev  #开发环境
      label: master #分支
      
# eureka
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://Wilson:123456@localhost:9001/eureka/,http://Wilson:123456@localhost:9002/eureka/,http://Wilson:123456@localhost:9003/eureka/
  instance:
    prefer-ip-address: true
    instance-id: dudu-user-${server.port}      
```

> 注意1：之所以要用bootstrap.yml，是因为启动SpringBoot项目时，会优先读取bootstrap.yml里的配置，然后才会读取application.yml。如果不通过bootstrap.yml里的配置，先从配置中心拉下相应的配置文件，就会报错                                                      
>
> 注意2：服务注册中心地址端口的问题，我们都知道eureka-server的默认端口是8761，如果我们现在将eureka-server的端口改成9001，那么微服务就会启动不了。因为在bootstrap.yml配置文件中，并没有配置eureka-server的地址。
>
> 所以项目启动的时候会优先读取bootstrap.yml，按照配置的内容去配置中心拉取配置文件，但是在此之前微服务需要先去注册中心上找配置中心的调用地址。所以需要在bootstrap.yml中进行配置。



### Controller测试

```java
/**
 * @ClassName TestController
 * @Description TODO
 * @Author guoweixin
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {
    @Value("${spring.info}")
    private String info;

    @GetMapping("test")
    public String test(){
        log.info(" git 远程文件内容：------>");
        return  info;
    }
}
```

<http://localhost:8081/dudu-user/test>

```
如果： spring.cloud.config.profile=test   打印：dudu-user-info-test 
如果： spring.cloud.config.profile=dev    打印：dudu-user-info-dev 
```

证明 config 配置成功。



# 三、高可用

统一配置中心和服务注册中心一样，都是需要高可用的

config-server也属于是一个微服务，所以让其高可用很简单，只需要启动多个服务实例即可。

![](images-config\6.png)

服务通过负载均衡策略，就能够调用这几个config-server实例，轻松实现高可用。



# 四、Spring Cloud Bus



## 1 问题

例如我在git上更改了配置文件，还需要重启微服务（用户微服务）才能够读取到最新的配置。

此时还不能做到自动刷新配置文件，

所以接下来将介绍一下如何使用Spring Cloud Bus实现热刷新配置，Bus在这里是总线的意思。

## 2 流程图

![](images-config\7.png)

Spring Cloud Bus会向外提供一个http接口，即图中的**/actuator/bus-refresh**。我们将这个接口配置到远程的git上，当git上的文件内容发生变动时，就会自动调用/bus-refresh接口。Bus就会通知config-server，config-server会发布更新消息到消息队列中，其他服务订阅到该消息就会信息刷新，从而实现整个微服务进行自动刷新。



## 3 RabbitMQ

详细描述  在前面已介绍。

此处直接来使用即可。

使用浏览器访问`http://192.168.20.135:15672` ，用户名/密码:admin 进入登录界面：

## 4 实现刷新配置

安装好RabbitMQ后，我们就可以着手实现配置的刷新了。

首先我们需要在config-server 项目中，增加Spring Cloud Bus依赖：

**注意：其它微服务也需要加入相应依赖(用户微服务、会议微服务、视频微服务等)**

```xml
        <!-- spring-boot-starter-actuator（健康监控）配置和使用
   在生产环境中，需要实时或定期监控服务的可用性。Spring Boot的actuator（健康监控）功能提供了很多监控所需的接口，
   可以对应用系统进行配置查看、相关功能统计等
    --> 
	<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency> 
<!--Spring Cloud Bus amqp 消息队列（默认是rabbitmq) -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```



### config-server配置文件application.yml

```yml
  # rabbitmq相关配置
  rabbitmq:
    host: 192.168.20.135
    port: 5672
    username: admin
    password: admin

# 允许/actuator/bus-refresh接口被外部调用
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

**完整的 config-server application.yml**

```yml
server:
  port: 9007
spring:
  cloud:
    config:
      server:
        git:
          uri: https://gitee.com/guoweixin/spring-cloud-config-repo-1.git
          #username:
          #password:
          search-paths: /**
  application:
    name: dudu-config-server
  # rabbitmq相关配置
  rabbitmq:
    host: 192.168.20.135
    port: 5672
    username: admin
    password: admin

# 允许/actuator/bus-refresh接口被外部调用
management:
  endpoints:
    web:
      exposure:
        include: "*"


# eureka
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://Wilson:123456@localhost:9001/eureka/,http://Wilson:123456@localhost:9002/eureka/,http://Wilson:123456@localhost:9003/eureka/
  instance:
    prefer-ip-address: true
    instance-id: dudu-config-server-${server.port}
```



### 用户微服务

用户微服务、会议微服务、视频微服务都依次加入该功能配置

**导入包 Cloud bus**

```xml
  <!--Spring Cloud Bus amqp 消息队列（默认是rabbitmq) -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```

**修改bootstrap.yml加入配置**

```yml
spring:
# rabbitmq相关配置
  rabbitmq:
    host: 192.168.20.135
    port: 5672
    username: admin
    password: admin
```



### 重启项目

配置好后，将以上项目都重启，dudu-eureka/dudu-config-server /dudu-user等。

然后到RabbitMQ上，可以看到注册上来的队列：

![](images-config\8.png)

确认都能够正常注册到rabbitmq后，我们到码云上，修改spring-info的值查看。

### 测试刷新

确认都能够正常注册到rabbitmq后，我们到码云上，修改spring-info的值。

**通过访问bus用于刷新配置的接口：**

`http://localhost:9007/actuator/bus-refresh`

![](images-config\9.png)