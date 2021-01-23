## 一. HttpServletRequest

>一个容器, 存放着请求时的数据

#### 1. getParameter("")

> 获取客户端传递参数
>
> 返回一个字符串

#### 2. getParameterMap

> 获取请求参数的集合
>
> 集合的 key是一个字符串, 请求时的参数名
>
> 集合的 value 是一个数组, 因为http 请求时会出现一个名称对应多个值的问题

#### 3. getHeader

> 获取请求头字段

#### 4. 获取请求地址

> getRequestURI : /webproject_war_exploded/ms
>
> getRequestURL : http://localhost:8080/webproject_war_exploded/ms
>
> getRemoteHost : 客户端的 ip 地址
>
> getRemotePort : 客户端的端口号

#### 5. 获取当前项目相关值

> getContextPath : 获取当前项目名
>
> getServletPath : 获取当前请求路径
>
> getServletContext().getRealPath("") : 获取当前项目的真实路径

## 二. HttpServletResponse

> 一个容器, 用tomcat提供的, 用来给我们存放返回的数据

#### 1. getWriter

> 获取返回的字符流

#### 2. getOutputStream

> 获取返回的字节流

#### 3. setHeader(key,value)

> 设置头字段

## 三. 转发和重定向

> 当前请求转到下个请求

#### 1. 转发

```java
req.getRequestDispatcher("/pages/register.html").forward(req,resp);
```

> 转发是服务器内部的动作, 这里的一个 / 表示当前项目
>
> 转发时浏览器是不知道,所以无论转发了多少次, 浏览器的地址栏还是第一次请求的地址
>
> 转发的时候数据不会丢失,会带到下一个目的地

#### 2. 重定向

```java
resp.sendRedirect("/day32/pages/login.html");//重新加载页面的时候不用再输入验证码
```

>告诉浏览器, 重启向这个地址发起请求
>
>这里的地址是给浏览器用的, 所有绝对路径要带着项目名
>
>重定向是浏览器向服务器发起了两次请求
>
>重定向只对哪些可以解析 http 协议的客户端生效

#### 3. 转发和重定向的区别

* 转发是服务器的动作, 重定向是浏览器的动作
* 转发请求一次 , 重定向请求两次
* 转发时浏览器的地址栏地址不变, 重定向地址发生改变
* 转发可以携带数据到目的地, 重定向无法携带数据重新发起请求

## 四. 乱码

> 乱码产生的原因是编码不统一
>
> 解决乱码的唯一办法 : 统一编码

#### 1. 接收数据乱码

* get请求乱码  tomcat --> conf --> server.xml 

  ```xml
  <Connector port="8080" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443" URIEncoding="utf-8"/>
  ```

* post请求乱码  在servlet中接收数据之前

  ```java
  req.setCharacterEncoding("utf-8");
  ```

#### 2. 返回数据乱码

> 必须要在返回数据之前设置

```java
//告诉服务器, 以utf-8的编码 变成字节
resp.setCharacterEncoding("utf-8");
//告诉浏览器, 以html的格式解析数据, 用utf-8编码转换
resp.setContentType("text/html;charset=utf-8");
```

## 五. BeanUtils

> 在接受参数时, 使用request 原生的方法, 需要一次次的调用 getParameter方法, 费时费力
>
> BeanUtils时一款可以直接将一个类中的数据直接映射到另外一个对象中的工具

#### 1. 使用

> 导入三个包 
>
> commons-beanutils-1.9.4.jar 
>
> commons-collections4-4.4.jar
>
> commons-logging-1.2.jar

**定义接收参数的 JavaBean**

```java
public class UserBean {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
```

**接受参数**

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    UserBean userBean = new UserBean();
    try {
      //将参数集合映射到 UserBean 对象中
      BeanUtils.populate(userBean,req.getParameterMap());
      System.out.println(userBean);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
}
```

#### 2. 封装工具

```java
public class WebUtils {
    
    public static <E> E toBean(Class<E> e, HttpServletRequest request){
        try {
            E bean = e.newInstance();
            BeanUtils.populate(bean,request.getParameterMap());
            return bean;
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
```

## 六. Cookie

> Cookie是浏览器的一片存储区域,可以以key-value的形式来存储一些值
>
> Cookie是在浏览器访问WEB服务器的某个资源时，由WEB服务器在HTTP响应消息头中附带传送给浏览器的一片数据，WEB服务器传送给各个客户端浏览器的数据是可以各不相同的
>
> 一旦WEB浏览器保存了某个Cookie，那么它在以后每次访问该WEB服务器时，都应在HTTP请求头中将这个Cookie回传给WEB服务器。
>
> WEB服务器通过在HTTP响应消息中增加Set-Cookie响应头字段将Cookie信息发送给浏览器，浏览器则通过在HTTP请求消息中增加Cookie请求头字段将Cookie回传给WEB服务器
>
> 一个Cookie只能标识一种信息，它至少含有一个标识该信息的名称（NAME）和设置值（VALUE）
>
> 一个WEB站点可以给一个WEB浏览器发送多个Cookie，一个WEB浏览器也可以存储多个WEB站点提供的Cookie
>
> 浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB

#### 1. 创建Cookie

```java
//创建Cookie
Cookie ck=new Cookie("code", code);
ck.setPath("/");//设置Cookie的路径
ck.setMaxAge(-1);//内存存储，取值有三种：>0有效期，单位秒；=0失效；<0内存存储
response.addCookie(ck);//让浏览器添加Cookie
```

#### 2. Cookie的生存时间

> Servlet中设置Cookie的生存时间单位是 秒
>
> ck.setMaxAge(-1)；设置生成时间
>
> \>0 指定生存时间
> =0 立即失效
> <0 内存存储 浏览器关闭则消失

#### 3. Cookie设置路径

> 通过Cookie的setPath方法设置路径
>
> cookie 一般都是由于用户访问页面而被创建的，可是并不是只有在创建 cookie 的页面才可以访问这个cookie。在默认情况下，出于安全方面的考虑，只有与创建 cookie 的页面处于同一个目录或在创建cookie页面的子目录下的网页才可以访问。那么此时如果希望其父级或者整个网页都能够使用cookie，就需要进行路径的设置
>
> 浏览器在发送请求之前，首先会根据请求url中的域名在cookie列表中找所有与当前域名一样的cookie，然后再根据指定的路径进行匹配，如果当前请求在域匹配的基础上还与路径匹配那么就会将所有匹配的cookie发送给服务器，这里要注意的是最大匹配和最小匹配问题，有些cookie服务器在发送之前会有意扩大当前页面cookie的匹配范围，此时这些被扩大范围的cookie也会一起发送给服务器

#### 4. Cookie的编码于解码

> 中文和英文字符不同，中文属于Unicode字符，在内存中占用4个字符，而英文属于ASCII字符，内存中只占2个字节。Cookie中使用Unicode字符时需要对Unicode字符进行编码，否则会出现乱码。编码可以使用java.net.URLEncoder类的encode(String str,String encoding)方法，解码使用java.net.URLDecoder类的decode(String str,String encoding)方法

```java
// 使用中文的 Cookie. name 与 value 都使用 UTF-8 编码. 
Cookie cookie = new Cookie(
URLEncoder.encode("姓名", "UTF-8"), 
URLEncoder.encode("老邢", "UTF-8"));
// 发送到客户端   
response.addCookie(cookie);
```

#### 5. Cookie的优缺点

- 优点:

  > 可配置到期规则 Cookie 可以在浏览器会话结束时到期，或者可以在客户端计算机上无限期存在，这取决于客户端的到期规则。
  > 不需要任何服务器资源 Cookie 存储在客户端并在发送后由服务器读取。
  > 简单性 Cookie 是一种基于文本的轻量结构，包含简单的键值对。
  > 数据持久性 虽然客户端计算机上 Cookie 的持续时间取决于客户端上的 Cookie 过期处理和用户干预，Cookie 通常是客户端上持续时间最长的数据保留形式

- 缺点:

  > 大小受到限制 大多数浏览器对 Cookie 的大小有 4096 字节的限制，尽管在当今新的浏览器和客户端设备版本中，支持 8192 字节的 Cookie 大小已愈发常见。
  > 用户配置为禁用 有些用户禁用了浏览器或客户端设备接收 Cookie 的能力，因此限制了这一功能。
  > 潜在的安全风险 Cookie 可能会被篡改。用户可能会操纵其计算机上的 Cookie，这意味着会对安全性造成潜在风险或者导致依赖于 Cookie 的应用程序失败。
  > 另外，虽然 Cookie 只能被将它们发送到客户端的域访问，历史上黑客已经发现从用户计算机上的其他域访问 Cookie 的方法。您可以手动加密和解密 Cookie，但这需要额外的编码，并且因为加密和解密需要耗费一定的时间而影响应用程序的性能

**自动登录案例**

## 七. 状态管理

> HTTP协议是无状态的，不能保存每次提交的信息，即当服务器返回与请求相对应的应答之后，这次事务的所有信息就丢掉了
>
> 如果用户发来一个新的请求，服务器无法知道它是否与上次的请求有联系。 对于那些需要多次提交数据才能完成的Web操作，比如登录来说，就成问题了
>
> WEB应用中的会话是指一个客户端浏览器与WEB服务器之间连续发生的一系列请求和响应过程
>
> WEB应用的会话状态是指WEB服务器与浏览器在会话过程中产生的状态信息，借助会话状态，WEB服务器能够把属于同一会话中的一系列的请求和响应过程关联起来

#### 状态管理常用的解决方案

> 客户端状态管理技术：将状态保存在客户端。代表性的是Cookie技术
>
> 服务器状态管理技术：将状态保存在服务器端。代表性的是session技术（服务器传递sessionID时需要使用Cookie的方式）

## 八. Session

> Session可以让服务器确认多个请求是否来自于同一个浏览器
>
> Session用于跟踪客户的状态
>
> Session指的是在一段时间内，单个客户与Web服务器的一连串相关的交互过程。
> 在一个Session中，客户可能会多次请求访问同一个网页，也有可能请求访问各种不同的服务器资源
>
> session被用于表示一个持续的连接状态，在网站访问中一般指代客户端浏览器的进程从开启到结束的过程。session其实就是网站分析的访问（visits）度量，表示一个访问的过程
>
> session的常见实现形式是会话cookie（session cookie），即未设置过期时间的cookie，这个cookie的默认生命周期为浏览器会话期间，只要关闭浏览器窗口，cookie就消失了。实现机制是当用户发起一个请求的时候，服务器会检查该请求中是否包含sessionid，如果未包含，则系统会创造一个名为JSESSIONID的输出 cookie返回给浏览器(只放入内存，并不存在硬盘中)，并将其以HashTable的形式写到服务器的内存里面；当已经包含sessionid是，服务端会检查找到与该session相匹配的信息，如果存在则直接使用该sessionid，若不存在则重新生成新的 session。这里需要注意的是session始终是有服务端创建的，并非浏览器自己生成的。　但是浏览器的cookie被禁止后session就需要用get方法的URL重写的机制或使用POST方法提交隐藏表单的形式来实现
>
> Session是一个域

#### 1. 获取Session

```java
//获取Session对象
HttpSession session=request.getSession();
System.out.println("Id："+session.getId());//唯一标记，
		System.out.println("getLastAccessedTime:"+session.getLastAccessedTime());//最后一次访问时间，毫秒
		System.out.println("getMaxInactiveInterval:"+session.getMaxInactiveInterval());//获取最大的空闲时间，单位秒
		System.out.println("getCreationTime:"+session.getCreationTime());//获取Session的创建，单位毫秒
```

#### 2. 存储和删除Session中的值

```JAVA
使用HttpSession的setAttrobute(属性名,Object)方法
获取HttpSession的getAttribute(属性名)
删除HttpSession的removeAttribute(属性名)
```

#### 3. 删除Session的值

```java
使用HttpSession的invalidate方法
```

#### 4. Session超时

> 我们不能无时间限制的存储session,这样做是不安全的
>
> HttpSession的最后一程访问时间和当前时间的差距大于了指定的最大空闲时间，这时服务器就会销毁Session对象。默认的空闲时间为30分钟
>
> 几种超时情况
>
> - 超过了设置的超时时间
> - 主动调用了invalidate方法
> - 服务器主动或异常关闭
>
> 注意：浏览器关闭并不会让Session失效

**保持登录状态案例**