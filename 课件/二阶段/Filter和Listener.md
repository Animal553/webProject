## Filter和Listener

### 一. 过滤器

* **定义**

  * 过滤器会在请求到达实际指向目标的时候先截获到请求, 并且在请求离开目标后再次截获请求
  * 在截获请求之后,我们可以对请求中的内容作出修改,甚至拒绝请求
  * Filter是Servlet技术的核心内容, 我们可以利用其完成权限控制,过滤请求, 压缩数据等等

* **编写过滤器**

  * 定义类实现Filter接口

  ```java
  package com.qianfeng.user;
  
  import java.io.IOException;
  import javax.servlet.Filter;
  import javax.servlet.FilterChain;
  import javax.servlet.ServletException;
  import javax.servlet.ServletRequest;
  import javax.servlet.ServletResponse;
  
  public class MyFilter implements Filter{
  
  	@Override
  	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
  			throws IOException, ServletException {
  		//拦截前
  		
  		chain.doFilter(request, response);
  		
  		//拦截后
  	}
  }
  ```

  * 指定Filter的拦截范围

    * 配置式

    ```XML
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
      <display-name>shop</display-name>
      <filter>
      	<filter-name>myfilter</filter-name>
      	<filter-class>com.qianfeng.user.MyFilter</filter-class>
      </filter>
      <filter-mapping>
      	<filter-name>myfilter</filter-name>
      	<url-pattern>/*</url-pattern>
      </filter-mapping>
    </web-app>
    ```

    * 注解式

    ```JAVA
    @WebFilter("/*")
    public class MyFilter implements Filter{}
    ```

* **注意事项**

  * /* 表示拦截所有
  * 生命周期 : 项目启动时 - 项目运行 - 项目销毁

* **过滤器链**

  * 通常客户端对服务器请求之后，服务器调用Servlet之前会执行一组过滤器（多个过滤器）,那么这组过滤器就称为一条过滤器链。
  * 每个过滤器实现某个特定的功能，一个过滤器检测多个Servlet。（匹配几个，检测几个）。
  * 一组过滤器中的执行顺序与<filter-mapping>的配置顺序呢有关。
    当第一个Filter的doFilter方法被调用时，web服务器会创建一个代表Filter链的FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源

* **多过滤器下的优先级**

  * 在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。web服务器根据Filter在web.xml文件中的注册顺序，决定先调用哪个Filter。当第一个Filter的doFilter方法被调用时，web服务器会创建一个代表Filter链
  * FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源
  * 如果为注解的话，是按照类名的字符串顺序进行起作用的

* **初始化过滤器时设置参数**

  * 基于注解的

    ```java
    @WebFilter(value="/*",initParams= {@WebInitParam(name = "version", value = "1.0")})
    ```

  * 基于配置的

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
      <display-name>Web_Day</display-name>
      
      <!--过滤器的xml配置  -->
      <filter>
        <filter-name>myfilter</filter-name>
        <filter-class>com.qf.web.filter.SecondFilter</filter-class>
         <!--过滤器的初始化参数  -->
        <init-param>
          <param-name>version</param-name>
          <param-value>1.0</param-value>
        </init-param>
      </filter>
      <filter-mapping>
        <filter-name>myfilter</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>
      <welcome-file-list>
        <welcome-file>index.html</welcome-file>
      </welcome-file-list>
    </web-app>
    ```

  * 案例

    * 自动登录
    * 全站压缩

    ```java
    package com.qianfeng.filter;
    
    import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
    
    import javax.servlet.*;
    import javax.servlet.annotation.WebFilter;
    
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpServletResponseWrapper;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.zip.GZIPOutputStream;
    
    @WebFilter("/*")
    public class GzipFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            //1. 想要压缩服务器返回到浏览器的数据, 那么要先拿到数据
            //      问题是, 现在没有任何方法支持我们拿到返回给浏览器的数据
            //      servletResponse 是服务器写回数据到浏览器的必备工具, 可以我们无法从这个工具中截取数据
            //      分析 : 无论什么情况下, 想要写回数据到浏览器,都必须要使用response,使用response中的两个流
            //      所以, 我们给servlet一个假的response, 并在这个假的response中提供两个假的流
            //       这样,我们就可以从这两个假的流中截取数据了
    
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            //servlet收到这两个参数是要使用 , 收到Response用什么?
            //实际使用从Response中获取流,用来写出数据
            //所以,我们要重写两个获取流的方法, 返回两个假的流给servlet
            MyResponse myResponse = new MyResponse(httpServletResponse);
            filterChain.doFilter(servletRequest,myResponse);
    
            //拿到数据之后要进行压缩
            //因为没问要看压缩前后的效果对比,所以, 压缩之后的数据不能直接写回到浏览器
            ByteOutputStream bos = new ByteOutputStream(); //用来存放压缩之后的数据
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
    
            //拿到原始数据
            byte[] bs = myResponse.getBytes();
            System.out.println("压缩前:"+bs.length);
    
            //开始压缩
            gzip.write(bs);
            gzip.close();
    
            bs = bos.toByteArray();
            System.out.println("压缩后:"+bs.length);
    
            //写回压缩数据到浏览器之前, 要告诉浏览器我们这次传输的数据是gzip压缩的, 先解压在展示
            ((HttpServletResponse) servletResponse).setHeader("Content-Encoding","gzip");
            //设置完压缩格式之后, 就可以真的往外写数据了
            servletResponse.getOutputStream().write(bs);
    
        }
    
        private class MyResponse extends HttpServletResponseWrapper{
            private PrintWriter pw ;
            private MyOutputStream myOutputStream;
            private ByteOutputStream bos = new ByteOutputStream();
            //当我们继承了HttpServletResponseWrapper , 必须要有一个有参构造, 需要一个真的HttpServletResponse对象
            public MyResponse(HttpServletResponse response) {
                super(response);
            }
    
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                if(myOutputStream==null){
                    myOutputStream = new MyOutputStream(bos);
                }
                return myOutputStream;
            }
    
            @Override
            public PrintWriter getWriter() throws IOException {
                if(pw==null){
                    //我们给PrintWriter一个内存流, 当servlet使用字符流往外写数据时,
                    //字符流接收到的数据就会全部存放到内存流中
                    pw = new PrintWriter(bos);
                }
    
                return pw;
            }
            //调用这个方法,就是为了获取内存流中存储的数据
            public byte[] getBytes(){
                if(pw!=null){ //说明servlet调用getWriter方法
                    pw.flush();
                }
    
                return bos.toByteArray(); //返回内存流中的有效数据
            }
        }
        
        public class MyOutputStream extends ServletOutputStream {
            private ByteOutputStream bos = new ByteOutputStream();
            public MyOutputStream(ByteOutputStream bos){
                this.bos = bos;
            }
    
            @Override
            public boolean isReady() {
                return false;
            }
    
            @Override
            public void setWriteListener(WriteListener writeListener) {
    
            }
    
            //serlvet获取字节流之后, 是要调用这个write方法写回数据,
            // //所以,我们就在这个方法内使用容器存放serlvet写出数据
            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        }
    
    
        @Override
        public void destroy() {
    
        }
    }
    
    ```

### 二. 监听器

* **定义**

  监听器用于监听web应用中某些对象、信息的创建、销毁、增加，修改，删除等动作的发生，然后作出相应的响应处理。当范围对象的状态发生变化的时候，服务器自动调用监听器对象中的方法。常用于统计在线人数和在线用户，系统加载时进行信息初始化，统计网站的访问量等等

* **编写监听器**

  * 定义类实现对应的监听器接口

  * 重写方法

  * 配置

    * 注解式 : @WebListener

    * 配置式

      ```xml
      <listener>
      <!--直接写出自定义的监听器的类名即可-->
          <listener-class>com.qf.web.listener.RequestLeftListener</listener-class>
        </listener>
      ```

* **常用的监听器**
  * ServletContextListener : 监听ServletContext生命周期
    * 初始化：contextInitialized
    * 销毁：contextDestroyed
  * ServletContextAttributeListener : 监听ServletContext属性内容变化
    * attributeAdded：监听属性的添加
    * attributeRemoved：监听属性的移除
    * attributeReplaced：监听属性的修改
  * HttpSessionListener : 监听Session生命周期
    * sessionCreated：监听Session对象的创建
    * sessionDestroyed：监听Session对象的销毁
  * HttpSessionAttributeListener : 监听Session属性内容变化
    * attributeAdded：监听属性的添加
    * attributeRemoved：监听属性的移除
    * attributeReplaced：监听属性的修改
  * HttpSessionActivationListener : 监听服务器的Session的钝化和活化
    * sessionWillPassivate：监听Session内部存储对象的钝化-存储
    * sessionDidActivate：监听Session内部存储对象的活化---读取
    * 对应类需要实现序列化接口Serializable
  * HttpSessionBindingListener : 监听Session中对象的添加和移除
    * valueBound：监听对象的绑定
    * valueUnbound：监听对象的解除绑定
  * HttpSessionIdListener : 监听HttpSession的id的变化
    * sessionIdChanged：监听HttpSession的id的变化
    * req.changeSessionId() 改变request所属session的id的方法
  * ServletRequestListener : 监听request对象的初始化和销毁
    * requestInitialized：监听request对象的初始化
    * requestDestroyed：监听request对象的销毁
  * ServletRequestAttributeListener
    * attributeAdded：监听属性的添加
    * attributeRemoved：监听属性的移除
    * attributeReplaced：监听属性的修改
  * AsyncListener : 监听异步请求
    * onStartAsync：监听异步开始
    * onTimeout：监听超时
    * onError：监听异步的错误信息
    * onComplete：监听异步的完成
* **案例**

  * 使用监听器实现当前登录用户人数和请求数量的监测

### 总结: 

1. 过滤器

   * 格式 

     ```java
     public MyFilter implements Filter{
         init(){
             
         }
         distory(){
             
         }
         doFilter(ServletRequest request, ServletResponse response, FilterChain filterChian){
             
         }
     }
     ```

   * filterChian.doFilter(request,response);  放行

   * 配置

     * web.xml  (可以设置启动参数)
     * @WebFilter("/*")

2. 过滤的器的注意事项

   * 多个过滤器 都能生效, 是串联状态
   * 请求会先经过前面的过滤器, 返回时最后经过前面的过滤器
   * 如果是web.xml中配置的, 谁写在上面谁就优先
   * 如果是注解式, 按照类名的自然顺序
   * 生命周期 :  服务器启动   ----  服务器运行   -----  服务器关闭

3. 监听器

   > 监听serlvet 这些技术中的 对象的创建 ,销毁, 存储 

   * 定义类, 实现特定的接口, 打上@WebListener注解, 重写对应的方法即可

   ## 作业

   自动登录

   全局压缩

   统计在线人数

​	