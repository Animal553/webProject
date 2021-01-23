

## 一. WEB服务器

> 我们在本地完成了 html 页面之后, 如果让其他人通过网络来访问这些页面呢 ?
>
> 首先, 我们肯定是要将页面放到公共的,任何人都可以访问到的一台机器上去.
>
> 客户端可以通过 ip地址找到这台机器
>
> 这台机器上应该有一段程序, 这段程序可以接收客户端发来的请求, 解析请求内容, 根据客户端段的要求, 找到对应的文件, 然后读取这个文件的内容, 通过网络传输给客户端.
>
> 这样的硬件和软件的组合体, 我们称之为 web 服务器

#### 服务器分两种 : 

* 硬件服务器 : 一台可以联网的机器, 里面装有操作系统
* 软件服务器 : 一款可以接收网络请求, 读写本地文件, 运行代码, 返回请求的软件

#### 常见的 web 服务器

> Tomcat 服务器 目前最为流行的Tomcat服务器是Apache-Jarkarta开源项目中的一个子项目，是一个小型、轻量级的支持JSP和Servlet 技术的Web服务器，也是初学者学习开发JSP应用的首选
>

> Resin 服务器 Resin是Caucho公司的产品，是一个非常流行的支持Servlet和JSP的服务器，速度非常快。Resin本身包含了
>
> 一个支持HTML的Web服务器，这使它不仅可以显示动态内容，而且显示静态内容的能力也毫不逊色，因此许多网站都 是使用Resin服务器构建

> JBoss服务器 JBoss是一个种遵从JavaEE规范的、开放源代码的、纯Java的EJB服务器，对于J2EE有很好的支持。JBoss采
>
> 用JML API实现软件模块的集成与管理，其核心服务又是提供EJB服务器，不包含Servlet和JSP的Web容器，不过 它可以和Tomcat完美结合

> WebSphere 服务器
>  WebSphere是IBM公司的产品，可进一步细分为 WebSphere Performance Pack、Cache Manager 和
>
> WebSphere Application Server等系列，其中WebSphere Application Server 是基于Java 的应用环 境，可以运行于 Sun Solaris、Windows NT 等多种操作系统平台，用于建立、部署和管理Internet和 Intranet Web应用程序。

> WebLogic 服务器
>  WebLogic 是BEA公司的产品(现在已经被Oracle收购)，可进一步细分为 WebLogic Server、WebLogic
>
> Enterprise 和 WebLogic Portal 等系列，其中 WebLogic Server 的功能特别强大。WebLogic 支持企业 级的、多层次的和完全分布式的Web应用，并且服务器的配置简单、界面友好。对于那些正在寻求能够提供Java平台 所拥有的一切应用服务器的用户来说，WebLogic是一个十分理想的选择

## 二. Tomcat

> 俗称 Tom 猫 
>
> Tomcat 是Apache 软件基金会（Apache Software Foundation）的Jakarta 项目中的一个核心项目，由Apache、Sun 和其他一些公司及个人共同开发而成。由于有了Sun 的参与和支持，最新的Servlet 和JSP 规范总是能在Tomcat 中得到体现。因为Tomcat 技术先进、性能稳定，而且免费，因而深受Java 爱好者的喜爱并得到了部分软件开发商的认可，成为目前比较流行的Web 应用服务器

#### 下载安装启动

1. 官网下载( http://tomcat.apache.org/ )，Tomcat8.0|8.5解压缩版本 

2. 解压到一个没有特殊符号的目录中(一般纯英文即可)
3. 进入到解压的目录下找到bin\startup.bat双击启动即可

#### 测试启动

> 默认情况下, 在本地浏览器输入 http://localhost:8080 

![startpage](images/startpage.png)

#### Tomat 目录结构

![startpage](images/tree.png)

##### bin

> bin目录主要是用来存放tomcat的命令，主要有两大类，一类是以.sh结尾的（linux命令），另一类是以.bat结尾的（windows命令）。
>
> 很多环境变量的设置都在此处，例如可以设置JDK路径、tomcat路径
>
> startup 用来启动tomcat
>
> shutdown 用来关闭tomcat
>
> 例如 catalina可以设置tomcat的启动参数

##### conf

> conf目录主要是用来存放tomcat的一些配置文件。
>
> server.xml可以设置端口号、设置域名或IP、默认加载的项目、请求编码
>
> web.xml 项目设置, 每个项目都有一个 web.xml , 当前文件夹下的 web.xml中的配置会被所有的项目共享
>
> context.xml 可以用来配置数据源之类的, 这个文件中的内容如果被改变,项目将会重新加载
>
> tomcat-users.xml用来配置管理tomcat的用户与权限
>
> 在Catalina目录下可以设置默认加载的项目

##### lib

> lib目录主要用来存放tomcat运行需要加载的jar包
>
> 例如，像连接数据库的jdbc的包我们可以加入到lib目录中来 , 当然,正常情况下, 项目的 jar 包还是最好放到项目下

##### logs 

> logs目录用来存放tomcat在运行过程中产生的日志文件，非常重要的是在控制台输出的日志。（清空不会对tomcat运行带来影响）
>
> 日志分成5类 : catalina、localhost、manager、admin、host-manager
>
> catalina.out 
>
> > catalina.out即标准输出和标准出错，所有输出到这两个位置的都会进入catalina.out，这里包含tomcat运行自己输出的日志以及应用里向console输出的日志。默认这个日志文件是不会进行自动切割的，我们需要借助其他工具进行切割（注意：catalina.out文件如果过大会影响）
>
> catalina.YYYY-MM-DD.log
>
> > catalina.{yyyy-MM-dd}.log是tomcat自己运行的一些日志，这些日志还会输出到catalina.out，但是应用向console输出的日志不会输出到catalina.{yyyy-MM-dd}.log,它是tomcat的启动和暂停时的运行日志，**注意，它和catalina.out是里面的内容是不一样的**
>
> localhost.YYYY-MM-DD.log
>
> > localhost.{yyyy-MM-dd}.log主要是应用初始化(listener, filter, servlet)未处理的异常最后被tomcat捕获而输出的日志,它也是包含tomcat的启动和暂停时的运行日志,但它没有catalina.YYYY-MM-DD.log 日志全。它只是记录了部分日志
>
> localhost_access_log**.**YYYY-MM-DD.txt
>
> > localhost_access_log.2018-09-19.txt：这个是访问tomcat的日志，请求时间和资源，状态码都有记录
>
> host-manager.YYYY-MM-DD.log
>
> > 这个估计是放tomcat的自带的manager项目的日志信息的，未看到有什么重要的日志信息
>
> manager.YYYY-MM-DD.log
>
> > 这个是tomcat manager项目专有的日志文件

##### temp

> temp目录用户存放tomcat在运行过程中产生的临时文件。（清空不会对tomcat运行带来影响）

##### webapps

> webapps目录用来存放应用程序，当tomcat启动时会去加载webapps目录下的应用程序。可以以文件夹、war包、jar包的形式发布应用。
>
> 当然，你也可以把应用程序放置在磁盘的任意位置，在配置文件中映射好就行

##### work

> work目录用来存放tomcat在运行时的编译后文件，例如JSP编译后的文件。
>
> 清空work目录，然后重启tomcat，可以达到清除缓存的作用

#### Tomcat Server.xml 配置文件

> server.xml 是 tomcat 的主要配置文件 里面的内容非常多, 这里只对重要内容做出解释
>
> <Server port="8005" shutdown="SHUTDOWN"> : 关闭 tomcat 服务器的连接端口号
>
>  <Connector port="8080" protocol="HTTP/1.1"
>
> ​               connectionTimeout="20000"
>
> ​               redirectPort="8443" />
>
> port 协议访问端口号
>
> redirectPort : 如果资源强制要求 https 访问, 转发的端口号

#### 自定义项目

> 在 webapps 文件夹下创建文件夹myproject, 然后在文件夹中创建html文件
>
> 重新启动tomcat 服务器, 浏览器中输入 http://localohost:8080/myproject/xxx.html即可访问

![startpage](images/myproject.png)

![startpage](images/myprojecthtml.png)

## 三. idea 集成 Tomcat

#### 新建 web 项目

![startpage](images/WeChat1aab87eb0453b03221557562d703bb39.png)

#### 添加tomcat 服务器

![startpage](images/WeChatf0028a08a39a97468ffb3492c5c72780.png)

![startpage](images/WeChat1726f145837652b2fe0412d19feafd9a.png)

#### 添加项目到tomcat

![startpage](images/WeChat598fa247134db4da3ba0762296aa4140.png)

#### 确认已经完成添加

![startpage](images/WeChat86f3e899cbda81ac5390ebe2dfa5cdfb.png)

#### 启动服务器

![startpage](images/WeChat0da1a060be16235b32189e8893e5a6b6.png)

#### 浏览器中测试访问

> 访问地址 : http://localhost:8080/

## 四. Web 项目结构

![startpage](images/WeChat91be707525a853113df4ae5dc9e72f45.png)

> idea 中的工程机构并不是 tomcat 可以直接使用的, 需要在部署时进行转换, 
>
> 部署时, idea 会建立一个和项目名相同的文件夹供 tomcat 使用

#### src 

> JAVA 代码的书写位置, 编译之后会放到 WEB-INf/classes 文件夹下

#### web

> 存放静态文件和配置文件的地方
>
> 这个文件夹下的内容会在部署的时候会直接复制到项目文件夹下

 #### WEB-INF

> 这个文件夹下的内容不能直接被客户端访问, 必须由 java 代码转发才行

 #### web.xml

> 项目的配置信息文件
>
> 例如, 配置欢迎页面,servlet之类

## 五. HTTP协议

> 在网络请求, 我必须要有ip 地址, 端口号, 资源路径 才能访问到服务器
>
> ip : 用于查找网络中的硬件服务器
>
> port : 用于查找硬件服务器中的软件服务器
>
> 资源路径 : 用户查找软件服务器中的资源文件

> http 协议是为了适应现代网络, 对 tcp 协议的再次封装
>
> 现在网络请求环境复杂, 不能只考虑请求地址, 还有周边的配套设置, 比如服务器返回的数据, 浏览器是否需要解压才能展示, 这时候需要更加复杂的通信. 所以, http 协议中除了请求地址之外,还有其他设置信息
>
> http 协议一段固定格式的字符串 分为请求和响应两种格式

#### 请求协议

请求分为三部分 : 请求行 请求头 请求体

请求行 : 包含请求方法, 地址, 协议版本

请求头 : 有意义的设置字段和值

请求体 : 由于存放请求时的数据

![](https://upload-images.jianshu.io/upload_images/1843940-d3214aa6ebf47292.png)



```http
GET /mix/76.html?name=kelvin&password=123456 HTTP/1.1
Host: www.fishbay.cn
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6 
username:hanchun
```

请求方法

> GET         请求指定的页面信息，并返回实体主体。
> HEAD        类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头
> POST        向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改。
> PUT         从客户端向服务器传送的数据取代指定的文档的内容。
> DELETE      请求服务器删除指定的页面。
> CONNECT     HTTP/1.1协议中预留给能够将连接改为管道方式的代理服务器。
> OPTIONS     允许客户端查看服务器的性能。
> TRACE       回显服务器收到的请求，主要用于测试或诊断。

#### 响应协议

响应分为 : 状态行 响应头 响应正文

状态行 : 协议版本 状态码 描述信息

响应头 : 有意义的设置字段和值

响应正文 : 返回给客户端的数据

![](https://upload-images.jianshu.io/upload_images/1843940-9161c0c67fb3bad1.jpg)

```http
HTTP/1.1 200 OK
Server: nginx
Date: Mon, 20 Feb 2017 09:13:59 GMT
Content-Type: text/plain;charset=UTF-8
Vary: Accept-Encoding
Cache-Control: no-store
Pragrma: no-cache
Expires: Thu, 01 Jan 1970 00:00:00 GMT
Cache-Control: no-cache
Content-Encoding: gzip
Transfer-Encoding: chunked
Proxy-Connection: Keep-alive
{"code":200,"notice":0,"follow":0,"forward":0,"msg":0,"comment":0,"pushMsg":null,"friend":{"snsCount":0,"count":0,"celebrityCount":0},"lastPrivateMsg":null,"event":0,"newProgramCount":0,"createDJRadioCount":0,"newTheme":true}
```

响应状态码

1.`1xx`: 指示信息--表示请求已接收，继续处理

2.`2xx`: 成功--表示请求已被成功接收、理解、接受

3.`3xx`: 重定向--要完成请求必须进行更进一步的操作

4.`4xx`: 客户端错误--请求有语法错误或请求无法实现

5.`5xx`: 服务器端错误--服务器未能实现合法的请求

#### 特点

> 1.支持客户/服务器模式
>
> 2.简单快速：客户向服务器请求服务时，只需传送请求方法和路径。请求方法常用的有GET、HEAD、POST。每种方法规定了客户与服务器联系的类型不同。由于HTTP协议简单，使得HTTP服务器的程序规模小，因而通信速度很快
>
> 3.灵活：HTTP允许传输任意类型的数据对象。正在传输的类型由Content-Type加以标记
>
> 4.无连接：无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间
>
> 5.无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快

**重点解释 :**

无连接 : http 协议的无连接并不是 udp协议中的面向无连接, 这里是指, 客户端向服务器发起一次请求, 服务器接收请求返回数据之后, 这次连接就中断, 不会一直和服务器保持连接. 但每次连接都 tcp 协议

无状态 : 服务器无法得知这次的请求是从哪个客户端过来的

#### GET 请求和 POST请求的区别(重点)

> 1. get 请求请求一次 , post 请求请求两次
>
>    get 请求是将数据放在 url 的后面, 直接一次性发送给服务器
>
>    post 请求是将数据放在请求体中, 第一次发送请求行,请求头, 第二次发送请求体
>
> 2. get 请求效率搞, post请求效率低
> 3. get 请求将请求参数存在 url 的后面(如果是中文, 会先进行 base64编码), post 将数据存放在请求体中
> 4. get 请求不安全, post 请求安全
> 5. get 请求无法的参数体积有限, post 请求的参数无体积限制

## 六. Servlet

> 客户端向服务器发起请求, 服务器接收请求并解析请求, 接下来, 客户端如果只是请求一个静态资源, 比如 html 页面的话, tomcat 可以自动读取文件并返回
>
> 但是如果客户端是要求从数据库获取数据的话, 这时候就需要调用 java 代码了
>
> java 代码肯定是开发着来写, 那么, 我们写的代码如何才能被 tomcat 调用呢? 这就要求我们必须要符合 tomcat 运行的规则, java 程序和 tomcat 的约定规则就是servlet 
>
> 当我们从数据库获取到数据之后 , 如果将数据返回给我客户端呢? 我们并没有跟客户端直接联系, 所以还是将数据给 tomcat, 由 tomcat 返回给客户端
>
> 所以, servlet 并不是具体的代码实现, 而是一套接收请求,返回数据的规则

#### 1. 定义Servlet

```java
package com.qianfeng.part01;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/test2")
public class TestDemo implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }
    
    @Override
    public ServletConfig getServletConfig() {

        return null;
    }
    
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        
    }
    @Override
    public String getServletInfo() {

        return "TestDemo类型的Servlet";
    }
    @Override
    public void destroy() {
        
    }
}

```

#### 2. Servlet方法解释

* init :  初始化
* destroy : 销毁方法
* getServletInfo : 返回一个代表当前servlet对象的字符串
* getServletConfig : 返回一个当前servlet对象的配置对象
* service :  执行代码的类

## 七. HttpServlet

> 是Servlet的一个实现类 , 针对于Http协议

```java
package com.qianfeng.part02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//用户注册
@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        

    }
}

```

#### 1. 给servlet配置路径

* web.xml重配置

  ```xml
  <servlet>
          <servlet-name>tt</servlet-name>
          <servlet-class>com.qianfeng.part01.TestDemo</servlet-class>
          <init-param>
              <param-name>name</param-name>
              <param-value>李四</param-value>
          </init-param>
      </servlet>
      <servlet-mapping>
          <servlet-name>tt</servlet-name>
          <url-pattern>/test</url-pattern>
      </servlet-mapping>
  ```

* 使用注解

  ```java
  @WebServlet("/register")
  public class Register extends HttpServlet 
  ```

#### 2. 注意事项

* servlet的生命周期 :  第一次请求的时候创建对象       服务存在servlet对象就存在     服务器关闭servlet对象消亡
* servlet是单例的 : 一个路径对应一个实例
* 如果有多个路径对应一个servlet , 那么会创建多个servlet对象

#### 3. 常见问题

* servlet的url必须 / 开始
* 多个servlet绝对不能重名
* 404只有一种情况, 路径错了

## 八. 页面路径问题(重要)

#### 1. 分类

* 绝对路径 : 相对于一个普通认同的固定位置开始
  * 优点 : 准确
  * 缺点 : 书写麻烦
* 相对路径 : 相对于当前一个位置
  * 优点 : 书写简单
  * 缺点 : 不容易理解

#### 2. 浏览器请求时的路径问题

* 在浏览器中的操作, 无论是请求servlet还是请求一个页面, 都是浏览器在工作

* 点击 a 标签的执行流程

  * 浏览器获取a标签中href属性中的地址
  * 根据地址向服务器发起请求
  * 服务器根据请求找到对应的资源, 返回资源
  * 浏览器接收服务器返回的资源, 展示信息

* 浏览器向服务器发起请求的地址的要素

  * 协议 + ip地址 + 端口号 + 项目名 + 文件地址
  * 如果是简写的, 那么, 浏览器会帮助我们补全

* 浏览器补全地址

  * 相对路径

    ```html
    <a href="login.html">请登陆</a>
    ```

    * 要看这个地址所在的页面 
    * 浏览器会拿到当前页面的路径 ,然后去掉最后一节( / )
    * 然后将我们写的相对路径拿过来补到最后

    ```html
    http://localhost:8080/day32/pages/ + login.html
    ```

  * 绝对路径 (最前面有 / )

    ```html
    <a href="/day32/pages/login.html">请登陆</a>
    ```

    * 要看这个地址所在的页面
    * 浏览器会拿到当前页面的路径, 截取 : 协议 +  IP地址 + 端口号 
    * 然后将我们写的路径补充到上面截取的那个路径的后面

    ```html
    http://localhost:8080/ + day32/pages/login.html
    ```

## 总结

1. web服务器的概念
   * 什么时web服务器
   * web服务器能帮我们做什么
     * 管理静态资源, 调用执行java代码
2. tomcat
   * 下载, 安装,运行
   * 和eclipse结合使用
3. http协议
   * 特点: 长连接, 固定格式的数据
   * 请求协议: 请求行, 请求头 , 请求体
   * 响应协议: 状态行 ,响应头, 响应正文
4. HttpServlet
   * 定义HttpServlet
   * 接收请求参数
   * 返回数据
5. 页面路径问题
   * 相对路径
   * 绝对路径