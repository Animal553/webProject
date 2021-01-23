#### 一. AJAX

1. **定义**

   * 可以让浏览器在不刷新页面的情况下,利用js代码和后台进行交互, 这种交互方式称之为异步,

2. **原生js的ajax**

   ```javascript
   //获取异步对象
   var ajax = new XMLHttpRequest();
   //设置数据, method: 请求方式   url: 地址 arsc: 是否异步
   ajax.open(method,url,async);
   //data: 数据 {userName:"hanchun",password:"hanchun123"}
   ajax.send(data);
   //定义个请求过程中每个状态变化时的回调函数
   ajax.onreadystatechange = function () {
       //当请求进行到第五阶段,也就是有数据返回时,和返回的状态值是200时
       if(ajax.readyState==4&&ajax.status==200){
           //获取返回的数据
           var resp = ajax.responseText;
       }
   }
   ```

3. jquery的ajax

   ```javascript
   $.ajax({
       url:"请求地址",
       type:"请求模式",
       data:{"数据名":"数据值"},//js对象    ?key=value&key=value
       contentType:"数据格式",//默认"application/x-www-form-urlencoded"
       dataType:"json",//默认是 text  , js 会按照预制的数据格式解析数据
       cache:boolean,//为了防止ie8以下的浏览器缓存请求
       processData:boolean,//是否将数据转换成字符串
       success:function(data){ //请求成功返回之后调用的方法 data:返回的数据
           
       }
   })
   ```

   发送和接收json

   ```javascript
   $.ajax({
       url:"请求地址",
       type:"请求模式",
       data:{"数据名":"数据值"},//js对象
       contentType:"application/json",//默认"application/x-www-form-urlencoded"
       dataType:"json",//默认是 text  , js 会按照预制的数据格式解析数据
       success:function(data){ //请求成功返回之后调用的方法 data:返回的数据
           
       }
   })
   ```

4. 利用jquery的ajax上传文件

   ```javascript
   //将文件添加到FromData中
   var formData = new FormData();
   var files = $("#upload")[0].files;
   for (var i = 0; i < files.length; i++) {
       formData.append("file",files[i]);
   }
   
   $.ajax({
       url:"/nj_1901/login",
       type:"post",
       data:formData,//js对象
       contentType:false,//默认"application/x-www-form-urlencoded"
       cache:false,//为了防止ie8一下的浏览器缓存请求
       processData:false,//是否将数据转换成字符串
       success:function(data){ //请求成功返回之后调用的方法
           alert(data)
       }
   })
   ```

   ```java
   DiskFileItemFactory factory = new DiskFileItemFactory();
   
   ServletFileUpload upload = new ServletFileUpload(factory);
   
   ServletRequestContext requestContext = new ServletRequestContext(req);
   
   try {
       List<FileItem> fileItems = upload.parseRequest(requestContext);
       for (FileItem fileItem : fileItems) {
           InputStream is = fileItem.getInputStream();
           String newName = System.currentTimeMillis() + fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
           File file = new File("/Users/spring/Desktop/" + newName);
           System.out.println(file.getAbsolutePath());
           FileOutputStream fos = new FileOutputStream("/Users/spring/Desktop/" + newName);
   
          byte[] bs = new byte[1024];
           int len;
           while ((len=is.read(bs))!=-1){
               fos.write(bs, 0, len);
   
           }
           fos.close();
       }
   
   } catch (FileUploadException e) {
       e.printStackTrace();
   } catch (IOException e) {
       e.printStackTrace();
   }
   ```

#### 二. JSON

> 一个固定格式的字符串 
>
> FastJson :  将 java对象和json字符串相互转化
>
> jquery ajax 中有 dataType属性 : 告诉jquery ,返回的数据按照什么格式解析

#### 三. 文件上传

1. 文件上传三要素

   * 提供form表单,method必须是post!
   * form表单的enctype必须是multipart/form-data
     * application/x-www-form-urlencoded : 将form表单中的可以可以传递的参数,拼接成key=value&key=value的字符串
   * multipart/form-data : 数据不要变成字符串, 以流的形式往服务端传递
   * 提供 input type="file" 类型长传输入

   ```java
   <%--
     Created by IntelliJ IDEA.
     User: Administrator
     Date: 2020/8/10
     Time: 14:39
     To change this template use File | Settings | File Templates.
   --%>
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Title</title>
   </head>
   <body>
   
   <form action="/day41/upload" method="post" enctype="multipart/form-data">
   
       <input type="file" name="file" multiple>   <br>
   
       <input type="submit">
   
   </form>
   
   </body>
   </html>
   
   ```

2. 接收

   >------WebKitFormBoundary4pQcotmZzJJHSAX3 : 边界符  用于分割多个文件内容
   >
   >name : 字段名
   >
   >filename : 文件名
   >
   >我们发现, 在服务端使用流读取的时候, 除了读取到文件内容之外, 还读取一些辅助信息

3.  后台接收

   * 导包 : fileupload.jar commons-io.jar

   ```java
   package com.qianfeng.user;
   
   
   
   import org.apache.commons.fileupload.FileItem;
   import org.apache.commons.fileupload.FileUpload;
   import org.apache.commons.fileupload.FileUploadException;
   import org.apache.commons.fileupload.disk.DiskFileItemFactory;
   
   import javax.servlet.ServletException;
   import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.FileOutputStream;
   import java.io.IOException;
   import java.io.InputStream;
   import java.util.List;
   
   @WebServlet("/upload")
   public class Upload extends HttpServlet {
   
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doPost(req, resp);
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
           //上传的设置
           DiskFileItemFactory factory = new DiskFileItemFactory();
           FileUpload fileUpload = new FileUpload(factory); //创建解析器
           try {
               List<FileItem> fileItems = fileUpload.parseRequest(req); //解析请求
               for (int i = 0; i < fileItems.size(); i++) {
                   FileItem fileItem = fileItems.get(i); //拿到一个文件
                   String fileName = fileItem.getName(); //获取文件名
                   FileOutputStream fos = new FileOutputStream("d:\\"+fileName);
                   InputStream is = fileItem.getInputStream(); //获取当前文件纯内容的流
                   byte[] bs = new byte[1024];
                   int len;
                   while ((len = is.read(bs))!=-1) {
                       fos.write(bs, 0, len);
                   }
                   is.close();
                   fos.close();
               }
   
           } catch (FileUploadException e) {
               e.printStackTrace();
           }
   
           /*DiskFileItemFactory factory = new DiskFileItemFactory();
           FileUpload fileUpload = new FileUpload();
           try {
               List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(req));
   
   
           } catch (FileUploadException e) {
               e.printStackTrace();
           }*/
   
       }
   }
   
   ```

4. 文件上传细节注意

   上述的代码虽然可以成功将文件上传到服务器上面的指定目录当中，但是文件上传功能有许多需要注意的小细节问题，以下列出的几点需要特别注意的

   * 为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如放于WEB-INF目录下
   * 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
   * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
   * 要限制上传文件的最大值
   * 要限制上传文件的类型，在收到上传文件名时，判断后缀名是否合法


#### 四 . AJAX文件上传

* js代码

  ```html
  <%--
    Created by IntelliJ IDEA.
    User: Administrator
    Date: 2020/8/10
    Time: 15:38
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>个人中心</title>
  </head>
  <body>
      用户名 : ${user.user_Name};
      头像 : <img id="inco" src="/day41/getImg?fileName=${user.inco}" onclick="openFile()">
  
      <input id="file"  type="file" style="display: none" onchange="subFile()">
  
  
  </body>
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  <script>
      function openFile(){
          $("#file").click();
      }
      function subFile(){
          //创建存储文件数据的容器
          var formData = new FormData();
          //获取input框的files属性, 文件的内容都在里面存着,
          var file = $("#file")[0].files[0];
          //第一个参数是字段名
          formData.append("file", file);
  
          $.ajax({
              url: "/day41/uploadInco2",
              type: "post",
              data:formData,
              contentType:false, //禁止将数据组装成任何格式
              processData:false, //禁止将数据转成字符串
              cache:false,
              success: function (data) {
                  $("#inco").attr("src", "/day41/getImg?fileName=" + data);
              }
          })
      }
  
  </script>
  </html>
  
  ```

* java代码

  ```java
  package com.qianfeng.lasted;
  
  
  import com.qianfeng.user.User;
  import com.qianfeng.user.UserDao;
  import org.apache.commons.fileupload.FileItem;
  import org.apache.commons.fileupload.FileUpload;
  import org.apache.commons.fileupload.FileUploadException;
  import org.apache.commons.fileupload.disk.DiskFileItemFactory;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.FileOutputStream;
  import java.io.IOException;
  import java.io.InputStream;
  import java.util.List;
  import java.util.UUID;
  
  @WebServlet("/uploadInco2")
  public class UploadInco extends HttpServlet {
  
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          doPost(req, resp);
      }
  
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
          DiskFileItemFactory factory = new DiskFileItemFactory();
          FileUpload fileUpload = new FileUpload(factory);
  
          try {
              List<FileItem> fileItems = fileUpload.parseRequest(req);
              FileItem fileItem = fileItems.get(0);
              //获取源文件的后缀名
              String prefix = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
              String str = UUID.randomUUID().toString(); //生成一个随机的文件名
              String newName = str + prefix;
  
              InputStream is = fileItem.getInputStream();
              FileOutputStream fos = new FileOutputStream("d:\\imgs\\"+newName);
              byte[] bs = new byte[1024];
              int len;
  
              while ((len = is.read(bs)) != -1) {
                  fos.write(bs, 0, len);
              }
  
              fos.close();
  
              resp.getWriter().write(newName);
  
          } catch (FileUploadException e) {
              e.printStackTrace();
          }
  
  
      }
  }
  
  ```

#### 五. 文件下载

> 我们要将Web应用系统中的文件资源提供给用户进行下载，首先我们要有一个页面列出上传文件目录下的所有文件，当用户点击文件下载超链接时就进行下载操作，编写一个ListFileServlet，用于列出Web应用系统中所有下载文件

##### 2.1 获取文件列表

```java
package me.gacl.web.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ListFileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取上传文件的目录
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //存储要下载的文件名
        Map<String,String> fileNameMap = new HashMap<String,String>();
        //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
        //将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/listfile.jsp").forward(request, response);
    }
    public void listfile(File file,Map<String,String> map){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            //遍历files[]数组
            for(File f : files){
                //递归
                listfile(f,map);
            }
        }else{
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
                file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
                那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
```

　这里简单说一下ListFileServlet中listfile方法，listfile方法是用来列出目录下的所有文件的，listfile方法内部用到了递归，在实际开发当中，我们肯定会在数据库创建一张表，里面会存储上传的文件名以及文件的具体存放目录，我们通过查询表就可以知道文件的具体存放目录，是不需要用到递归操作的，这个例子是因为没有使用数据库存储上传的文件名和文件的具体存放位置，而上传文件的存放位置又使用了散列算法打散存放，所以需要用到递归，在递归时，将获取到的文件名存放到从外面传递到listfile方法里面的Map集合当中，这样就可以保证所有的文件都存放在同一个Map集合当中。

##### 2.2 配置

在Web.xml文件中配置ListFileServlet

```xml
<servlet>
     <servlet-name>ListFileServlet</servlet-name>
     <servlet-class>me.gacl.web.controller.ListFileServlet</servlet-class>
</servlet>
 
<servlet-mapping>
     <servlet-name>ListFileServlet</servlet-name>
    <url-pattern>/servlet/ListFileServlet</url-pattern>
</servlet-mapping>
```

##### 2.3 下载页面

展示下载文件的listfile.jsp页面如下：

```jsp
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>下载文件显示页面</title>
  </head>
  <body>
      <!-- 遍历Map集合 -->
    <c:forEach var="me" items="${fileNameMap}">
        <c:url value="/servlet/DownLoadServlet" var="downurl">
            <c:param name="filename" value="${me.key}"></c:param>
        </c:url>
        ${me.value}<a href="${downurl}">下载</a>
        <br/>
    </c:forEach>
  </body>
</html>
```

##### 2.4 实现文件下载

编写一个用于处理文件下载的Servlet，DownLoadServlet的代码如下：

```java
public class DownLoadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }
    
    public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
```

##### 2.5 配置

```xml
<servlet>
      <servlet-name>DownLoadServlet</servlet-name>
      <servlet-class>me.gacl.web.controller.DownLoadServlet</servlet-class>
</servlet>
 
<servlet-mapping>
      <servlet-name>DownLoadServlet</servlet-name>
      <url-pattern>/servlet/DownLoadServlet</url-pattern>
</servlet-mapping>
```

#### 总结

1. AJAX

   > 一种js和服务器通信的功能
   >
   > 是js自带的功能, 但是比较难用 , 所以我们使用jquery封装之后的方法, 便于使用
   >
   > load() get() post()  ajax()

2. json

   > 一个固定格式的字符串 被所有人认可的格式
   >
   > java中有个 java对象和json字符串相互转换的工具
   >
   > js中也有js对象和json字符串相互转换的工具
   >
   > 因为这个原因,我们可以使用json作为js和java通信的数据格式

3. 文件上传

   > 三个要素 :  type类型为file的input框   请求方式必须是post    enctype必须是 multipart/form-data 
   >
   > 因为上传的时候, 浏览器传输到服务器的数据不光只有文件的内容,还有边界符和文件属性, 所以我们需要一个款能够解析内容的工具 fileupload

4. ajax文件上传

   > 原来的文件上传使用的是form表单, form表单会跳转页面, 
   >
   > 通过input框获取文件, 将文件存到FormData对象中
   >
   > contentType: false   
   >
   > processData:false

5. 文件的下载

   >浏览器能展示就展示,不能展示就下载
   >
   >resp.setHeader("content-disposition","attachment;filename=文件名")
   >
   >中文名称必须要转码 URLEncoder.encoder(中文,编码); 





jdbc六部曲：1加载驱动

​					2获取连接对象

​					3获取sql语句对象

​					4执行sql语句

​					5查询结果集

​					6关闭连接