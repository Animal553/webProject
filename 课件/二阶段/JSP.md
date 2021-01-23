# JSP

### 一. HTML作为展示页的缺点

1. 缺点
   * 缓存问题
   * 回显问题
     * 除非使用异步

2. 解决办法
   * MIME 类型
   * 如果浏览器认为后台返回的是一个静态文件,就会走缓存的流程
   * 如果浏览器认为返回的是数据,就会走正常请求解析数据流程(Servlet流程)
   * servlet流程 :  
     * 浏览器请求服务器使用的http协议会携带一个最后修改时间
     * tomcat拿到http协议中的数据后,会查找这个时间, 然后再拿到请求地址去找servlet
     * 调用servlet的getLastModified方法回去一个时间
     * 如果这个时间和http协议携带的时候相同, 那么tomcat认为结果没有改变, 就直接返回了
     * 如果这个时间大于http协议携带的时间, 那么tomca认为结果有变化, 需要重新执行doGet或doPost方法
   * 通过动态拼接html代码并写回到页面的方式,可以解决回显问题

### 二. 介绍

* **定义**
  * Java Server Pages,java服务器页面。和Servlet一样，是sun公司定义的一种动态网页开发技术。
  * 特点：基于html模版，可以在html模版嵌入java代码和jsp中的标签。
  * 备注：html静态页面。
  * CSS:修饰美化html页面。
  * JavaScript:动态修改html页面和css样式。
  * Servlet：运行在服务器上的java程序。适合编写java代码，写网页困难
  * jsp:适合编写动态内容，不适合写java代码，主要用于显示页面
  * 开发者在jsp文件中写html代码  --->  tomcat会将jsp翻译成一个servlet文件 --- 编译servlet -- > 执行代码返回给浏览器 
* **作用**
  * JSP不缓存的原因是：jsp是servlet ,不是静态页面
  * 动态拼接页面
  * jsp可以在html页面中动态嵌入元素
  * 服务器调用的是已经编译好的JSP文件
  * JSP基于Java Servlet Api,有很多强大企业的支持
  * JSP可以与处理业务逻辑的Servlet一起使用，该模式被Java Servlet模版引擎所支持
* **优势**
  * 与纯 Servlet 相比：JSP可以很方便的编写或者修改HTML网页而不用去面对大量的println语句
  * 与JavaScript相比：虽然JavaScript可以在客户端动态生成HTML，但是很难与服务器交互，因此不能提供复杂的服务，比如访问数据库和图像处理等等
  * 与静态HTML相比：静态HTML不包含动态信息
* **缺点**
  * 传输量大 : 使用异步,尽量不刷新页面
  * 每次都要拼接大量的字符串 : 页面静态化技术

### 三. 基础语法

* **基础语法(不准使用)**

  ```jsp
  <% java代码 %>
  <%  int i=10;%> : 定义局部变量
  <%! int i=10;%> : 定义全局变量
  <%=2+3%> 输出2+3的结果
  ```

* **注释**

  ```jsp
  <%-- 网页注释 --%>：安全，省流量
  ```

* **演示**

  ```jsp
  <%--指令，页面的设置--%>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8" %>
      <%--1、模板元素 --%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>第一个JSP页面</title>
  </head>
  <body>
  <%--我是jsp特有的注释 --%>
  <!--我是HTML的注释  -->
  <a href="http://www.baidu.com">百度一下</a>
  <%--jsp脚本 --%>
  <%! int c=10; %>
  <!-- 4、jsp标签 -->
  <jsp:include page=""></jsp:include>
  </body>
  </html>
  ```

### 四. JSP指令

* **page指令**
  * import和java代码的含义一样
  * session:是否会自动创建session对象，默认值为true;
  * buffer:JSP中有java.servlet.jsp.JspWriter输出字符流。设置输出数据的缓存大小。默认8kb.
  * errorPage:如果页面中有错误，跳转到指定的资源 errorPage="/uri" 如果写“/”则代表当前应用的目录 下，绝对路径。  如果不写“/”则代表相对路径
  * isErrorPage:是否创建throwable对象。默认是false
  * contentType:等同于response.setContentType(“text/html”；charset=utf-8);服务器发送客户端的内容编码
  * pageEncoding: Jsp文件本身的编码
  * isELIgnored: 是否屏蔽EL表达式。 默认是false,支持表达式, 是true,不支持表达式，${1+1};false输出结果2   true按照原样输出 
* **inclue指令**
  * 静态包含: <%@ include file="header.jsp" %> 
  * 动态包含: <jsp:include page=“header.jsp">\</jsp:include\>
  * 区别：翻译的时间段不同(可在tomcat工作目录下查看)
    静态包含：在翻译时就把两个文件进行合并
    动态包含：不会合并文件，当代码执行到include时，才包含另一个文件的内容
* **taglib指令**
  * 引入其他标签库
  * <%@ taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>

### 五. JSP的六个动作(尽量不用)

* jsp:include 动态包含
* jsp:forward 请求转发
* jsp:param 请求设置参数
* jsp:useBean 创建一个对象
* jsp:setProperty 给指定的对象属性赋值
* jsp:getProperty 取出指定的对象属值

### 六. JSP9大内置对象(不准使用)

* | 对象名      | 类型                                   | 说明                        |
  | ----------- | -------------------------------------- | --------------------------- |
  | request     | javax.servlet.http.HttpServletRequest  |                             |
  | response    | javax.servlet.http.HttpServletResponse |                             |
  | session     | javax.servlet.http.HttpSession         | 由session=“true”开关        |
  | application | javax.servlet.ServletContext           |                             |
  | exception   | java.lang.Throwable                    | 由isErrorPage=“false”开关   |
  | page        | java.lang.Object当前对象this           | 当前servlet实例             |
  | config      | javax.servlet.ServletConfig            |                             |
  | pageContext | javax.servlet.jsp.PageContext          |                             |
  | out         | javax.servlet.jsp.JspWriter            | javax.servlet.jsp.JspWriter |

* **演示**

  ```jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8" isErrorPage="true"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP内置对象</title>
  </head>
  <body>
  <%
  //页面上下文对象
  pageContext.setAttribute("msg", "我是PageContext对象");
  System.out.println(pageContext.getAttribute("msg"));
  //请求对象
  System.out.println("请求对象："+request);
  //响应对象
  response.getWriter().print("OK");
  //当前页面对象
  System.out.println(page.getClass());//输出到控制台
  //输出对象
  out.print(48);//输出到浏览器：48
  out.write(48);//输出到浏览器：0
  int no=101;
  //会话对象
  System.out.println(session);
  //全局对象
  System.out.println(application.getRealPath("/"));
  //配置对象--初始化参数
  System.out.println(config.getServletName());
  //异常对象
  System.out.println(exception);
  System.out.println(application);
  application.setAttribute("author", "1711");
  %>
  <h1>jsp脚本：<%=no %></h1>
  <h1>jsp脚本：<% out.print(no); %></h1>
  </body>
  </html>
  ```

### 七. JSP的四大域对象

* **pageContext : 表示当前jsp页面**

  * 获取其他域对象
  * 获取其他隐式对象
  * 提供了跳转和包含方法

  ```jsp
  void setAttribute(String name,Object o);
  Object getAttribute(String name);
  void removeAttribute(String name);
  
  操作其它域对象的方法
      void setAttribute(String name,Objecto,int Scope);
      Object getAttribute(String name,intScope);
      void removeAttribute(String name,intScope);
  Scope作用域，值如下:
      PageContext.PAGE_SCOPE
      PageContext.REQUEST_SCOPE
      PageContext.SESSION_SCOPE
      PageContext.APPLICATION_SCOPE
  
  findAttribute(Stringname)自动从page,request ，session ，application依次查找，找到了就取值，结束查找  （作用域的范围由小到大）
  
  获取其他隐式对象
      getException方法返回exception隐式对象 
      getPage方法返回page隐式对象
      getRequest方法返回request隐式对象 
      getResponse方法返回response隐式对象 
      getServletConfig方法返回config隐式对象
      getServletContext方法返回application隐式对象
      getSession方法返回session隐式对象 
      getOut方法返回out隐式对象
  转发和包含
  		pageContext.forward(“2.jsp”);
  		pageContext.include(“2.jsp”);
  ```

* **request域**

  * 一个请求内,如果有转发操作, 当前请求走过的所有servlet和jsp都属于同一个request域
* **session域**

  * 同一个浏览器发出的多个请求共享一个域
* **servletContext域**

  * servlet最大的一个域, 被所有请求共享

### 八. EL表达式

* **定义**

  * expression language表达式语言
  * 简化了JSP中代码开发
  * 这是不是一种语言,是jsp中获取数据的一种规范

* **格式**

  * ${参数的名称}

*  **案例1：获取实体类中的属性值**

  ```jsp
  <%
  User user= new User();
  user.setName("gggg");
  
  Address  address = new Address();
  address.setAddr("北京市海淀区");
  user.setAddress(address);
  
  session.setAttribute("user", user);
  /* request.getRequestDispatcher("testEl.jsp").forward(request, response); */
  response.sendRedirect("testEl.jsp");
  %>
  <!--  EL表达式中的.表示调用该属性的get方法 -->
  <%-- 我是：$住在{user.name }  , 住在  ${user.address.addr } --%>
  ```

* **案例2 : 使用list和map**

  ```jsp
  <%
  List<String> list =new ArrayList<String>();
  list.add("aa");
  list.add("bb");
  list.add("cc");
  pageContext.setAttribute("list", list);
  
  Map<String,String> map =new HashMap<String,String>();
  map.put("aa", "11");
  map.put("bb", "22");
  map.put("cc", "33");
  
  pageContext.setAttribute("map", map);
  
  %>
  
  <h1>1.以数组的下标形式获取list值</h1>
  ${list[0]}<br>
  ${list[1]}<br>
  ${list[2]}<br>
  <h1>2.以提供的方法get(index)</h1>
  ${list.get(0)}
  
  <h1>3.使用El表达式获取map中的值(以key的形式获取)</h1>
  ${map.aa}<br>
  ${map.bb}<br>
  ${map.cc}<br>
  <h1>4.使用El表达式获取map中的值</h1>
  ${map['aa']}
  ```

* **案例3: 使用表达式的empty关键字**

  ```jsp
  <% String s1="";
  pageContext.setAttribute("s1", s1);
  String s2=null;
  pageContext.setAttribute("s2", s2);
  String s3="122222";
  pageContext.setAttribute("s3", s3);
  List list1 =new ArrayList();
  pageContext.setAttribute("list1", list1);
  %>
  
  <!-- s1 s2 list1 都为true -->
  ${empty s1}<br>
  ${empty s2}<br>
  ${empty  s3}<br>
  ${empty  list1}<br>
  ```

### 九. JSTL

* **定义**

  JSTL是一款强大的逻辑标签库, 她可以使我们像写Java代码一样在页面中进行判断和循环

* **if chose标签**

  ```jsp
  <!-- 条件标签：if  choose -->
  	<!-- test属性中是条件，但是条件需要使用EL表达式来书写 -->
  	<h3>条件标签：if</h3>
  	<c:if test="${8>2 }">
      8大于2是成立的
       </c:if>
  	<c:if test="${8<2 }">
      8小于2是成立的
      </c:if>
  	<br>
  	<%--  如果只是一个基本数据类型直接书写不需要${} --%>
  	<c:set var="m" value="${5}"></c:set>
  	<c:if test="${m>3}">
       5大于3是成立的
    </c:if>
  ```

  ```jsp
  <h3>条件标签：choose(等价于java中switch)</h3>
  <%-- 测试成绩等级 >90 优秀   >80  良好    >70 中等   >60及格--%>
  <c:set var="score" value="${80}"></c:set>
  <c:choose>
  	<c:when test="${score>=90 }">优秀</c:when>
  	<c:when test="${score>=80 }">良好</c:when>
  	<c:when test="${score>=70 }">中等</c:when>
  	<c:when test="${score>=60 }">及格</c:when>
  	<c:otherwise>不及格</c:otherwise>
  </c:choose>
  ```

* **foreach标签**

  ```jsp
  <!-- 遍历for:输出1到10 的值 -->
  	<!--var: 变量，把遍历的每一个值都存储在变量中进行输出
      begin：开始   如果是变量使用EL表达式表示
      end:结束    如果是变量使用EL表达式表示
      step:间隔的长度
      
      for( int i=0;i<10;i++){
       System.out.println(i);
      }
       -->
  示例代码：
  <c:forEach var="i" begin="1" end="10" step="2">
   ${i}<br>
  </c:forEach>
  ```

  ```jsp
  <h3>测试list集合遍历获取学生列表</h3>
        <table border="1" width="80%" bordercolor="red" cellspacing="0"
            align="center">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>成绩</th>
                <th>班级</th>
                <th>是否是第一个</th>
                <th>是否是最后一个</th>
                <th>计数count</th>
                <th>索引index</th>
            </tr>
     <!-- varStatus:变量状态：遍历出的每一项内容的状态：
        isFirst()      first
        isLast()       last
        getCount()     count  计数  重要的
        getIndex()     index
         -->
         <!-- var :遍历出的每一项使用变量先存储
              items：集合(使用El表达式)
              -->
            <c:forEach var="stu" items="${students}" varStatus="vs">
                <tr>
                    <td>${stu.id}</td>
                    <td>${stu.name}</td>
                    <td>${stu.score}</td>
                    <td>${stu.classes}</td>
                    <td>${vs.first}</td>
                    <td>${vs.last}</td>
                    <td>${vs.count}</td>
                    <td>${vs.index}</td>
                </tr>
            </c:forEach>
     </table>
  ```

总结

1. jsp的注释

   <%-- 注释 --%>

   当将页面写回到浏览器时, 注释式不会写回去的,减少了流量

2. jsp就是一个servlet
3. taglib
   * 包含 : 静态和动态包含
     静态拼接《%@include%》
     动态拼接《jsp.include》
   * 使用动态包含 : <jsp:include page="">
4. 四大域
   * pageContext : 基本不用
   * request
   * session
   * servletContext
5. EL表达式
   * ${} 
   * \${参数名} \${参数名.属性} \${list[index]} \${map.key} empty
6. JSTL
   * \<c:if> \<c:choose> \<c:when>
   * \<c:foreach>

### 作业

* 实现具有回显功能的登录注册



