# Spring Cloud  Ribbon



## 前奏

​     使用SpringCloud开发微服务应用时，各个服务提供者都是以HTTP接口的形式对外提供服务，因此在服务消费者调用服务提供者时，底层通过HTTP Client的方式访问。

可以使用JDK原生的**URLConnection**, Apache的**HTTP Client**，Spring的**RestTemplate**去实现服务间的调用。

**Spring cloud 有两种服务调用方式，一种是 ribbon + restTemplate，另一种是 feign。**



## 一、Ribbon

### 1 什么是Ribbon

​       Ribbon是Netflix开发的一个负载均衡组件，它在云服务体系中起着至关重要的作用。诞生于2013年1月。

​          它有助于控制HTTP和TCP的客户端的行为。为Ribbon配置服务提供者地址后，Ribbon就可基于某种负载均衡算法，自动地帮助服务消费者去请求。

​           **Ribbon默认为我们提供了很多负载均衡算法**，例如轮询、随机等。当然，我们也可为Ribbon实现自定义的负载均衡算法。
在Spring Cloud中，当Ribbon与Eureka配合使用时，Ribbon可自动从Eureka Server获取服务提供者地址列表，并基于负载均衡算法，请求其中一个服务提供者实例。

**总结：Spring Cloud Ribbon是基于Netfix Ribbon实现的一套 客户端 负载均衡工具（Ribbon对服务实例节点的增减也能动态感知）**



### 2 LoadBalance描述

​           **Load Balance负载均衡**是用于解决一台机器(一个进程)无法解决所有请求而产生的一种算法。像nginx可以使用负载均衡分配流量，ribbon为客户端提供负载均衡，dubbo服务调用里的负载均衡等等，很多地方都使用到了负载均衡。



#### **使用负载均衡好处：**

当集群里的1台或者多台服务器down的时候，剩余的没有down的服务器可以保证服务的继续使用
使用了更多的机器保证了机器的良性使用，不会由于某一高峰时刻导致系统cpu急剧上升。



#### **LB方案可分成两类：**

**一种是集中式LB**, 即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；

**一种是进程内LB**，将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon就属于后者，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址

![](images-ribbon-feign\1.png)



### 3 示例1：Ribbon+Rest会议微服务发现到Eureka(消费者)

#### dudu-meeting pom.xml

**追加SpringCloud Eureka 客户端支持**

```xml
<!-- 导入Spring Cloud 组件  新版本中已集成Ribbon-->
<!-- 客户端 Spring Cloud  eureka client Start -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<!-- 客户端 Spring Cloud eureka client End -->

<!-- ribbon Begin (此处不需要，新版本中包含了ribbon)-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
<!--ribbon End -->

```

##### 错误解决

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

##### 完整的pom

```xml
   <parent>
        <groupId>com.qfjy</groupId>
        <artifactId>dudu</artifactId>
        <version>1.0-SNAPSHOT</version>
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

        <!-- 导入Spring Cloud 组件 -->
        <!-- 客户端 Spring Cloud  eureka client Start -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 客户端 Spring Cloud eureka client End -->

      


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



####  dudu-meeting application.yml

**追加application.yml中，添加Eureka客户端支持。进行服务注册**

**注意：** 需要指明 **spring.application.name**，这个很重要，这在以后的服务与服务之间相互调用一般都是根据这个 name。 

```yml
#server config info
server:
  port: 8080
  servlet:
    context-path: /dudu-meeting
spring:
  application:
    name: dudu-meeting  #应用程序名称

# eureka 服务端配置
eureka:
  client:
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
  instance:  #指定Eureka 注册的实例名称
    instance-id: dudu-meeting-8080
    prefer-ip-address: true #访问Eureka IP地址
```

#### RestTemplate代码

```java
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced //Ribbon负载均衡
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

}
```



#### MeetingpubService(服务间调用)



```java
@Service
public class MeetingpubServiceImpl implements MeetingpubService {
    @Autowired
    private MeetingpubMapper meetingpubMapper;
    @Autowired
    private RestTemplate restTemplate;  //模版的注入
    /**
     * 根据会议ID 查询会议详情
     */
    @Override
    public Meetingpub selectByPrimaryKey(String id) {
        Meetingpub meetingpub=meetingpubMapper.selectByPrimaryKey(id);
        //String url="http://localhost:8081/dudu-user/user/{id}";
        /** 采用Ribbon+Rest方式
        1：不要使用ip+port的方式访问，取而代之的是应用名
		2：这种方式发送的请求都会被ribbon拦截，ribbon从eureka注册中心获取服务列表，然后采用均衡策略进行访问
        */
        String url="http://DUDU-USER/dudu-user/user/{id}";
        ResponseEntity<User> responseEntity=restTemplate.getForEntity(url, User.class,meetingpub.getUid());
        User user=responseEntity.getBody();
        meetingpub.setUser(user);

        return  meetingpub;
    }
}
```



####  应用程序入口Application

**DuduMeetingApplication(如下两种都可)**

@EnableDiscoveryClient  //启动主类声明该服务被注册中心发现
@EnableEurekaClient //表明自己是一个Eureka Client

```java
@SpringBootApplication
@MapperScan(basePackages = {"com.qfjy.dudu.mapper"})
@EnableDiscoveryClient //启动主类声明该服务被注册中心发现
public class DuduMeetingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuduMeetingApplication.class, args);
    }
}
```

### 4 示例2：Ribbon负载均衡示例

![](images-ribbon-feign\7.png)

![](images-ribbon-feign\8.png)

需要启动多个 服务提供者（dudu-user)。开启两个，端口号分别为：8081/8082/8083

需要启动多个 服务提供者（dudu-video)。开启两个，端口号分别为：8083/8084/8085

#### dudu-user 代码注入端口号

以示区分调用哪个服务

```java
    @Value("${server.port}")
    private String port;   //获取当前微服务的端口号信息application.yml中

    @RequestMapping(value = "{id}")   // user/id
    @ResponseBody
    public User selectById(@PathVariable("id") Integer id) {

       User user= userService.selectByPrimaryKey(id);
        user.setCity(port); //负载均衡显示端口号
        return user;
    }
```

#### dudu-user application.yml

**修改如下两处：并依次启动两个微服务**

```bat
server.port:8081/8082  
eureka.instance.instance-id: dudu-user-${server.port}  //必须要变更（不能相同
```

```yml
#server config info
server:
  port: 8082  
  servlet:
    context-path: /dudu-user
spring:
  application:
    name: dudu-user
# eureka 服务端配置
eureka:
  client:
    register-with-eureka: true  # 是否将当前注册到Eureka服务中。(客户端）
    fetch-registry: true #是否从Eureka中获取注册信息(客户端）
    service-url:  #Eureka客户端与Eureka服务端进行交互的地址
      defaultZone: http://localhost:9001/eureka/
  instance:  #指定Eureka 注册的实例名称
    instance-id: dudu-user-${server.port}
#    prefer-ip-address: true #访问Eureka IP地址
```

#### eureka如下：

![](images-ribbon-feign\2.png)

#### 运行

服务消费者运行，即可看到负载均衡场景

**发现结果，默认是Ribbon的轮询。**

### 5 Ribbon负载均衡策略

#### 负载均衡

负载均衡器是从EurekaClient（EurekaClient的实现类为DiscoveryClient）获取服务信息，根据IRule去路由，并且根据IPing判断服务的可用性。

![](images-ribbon-feign\3.png)

#### 全局修改

使用Ribbon的时候想要全局更改负载均衡策略，需要加一个配置类

```java
/**
 * @ClassName RestTemplateConfig
 * @Description TODO
 * @Author guoweixin
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //添加注解，用于声明Rest访问时，使用负载均衡(必须加）
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Bean //负载均衡规则改为 RandomRule
    public IRule ribbonRule(){
        return  new RandomRule(); //改为随机策略
    }
}
```

如上配置好后，7种自带的负载均衡。所有凡是通过Ribbon的请求都会按照配置规则来进行。



