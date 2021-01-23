
## m第一章 Shiro简介

### 第1节 shiro介绍

```
官网地址: http://shiro.apache.org/
```
> Apache Shiro 是一个功能强大,易于使用的Java安全框架,他执行认证、授权、加密、会话管理等功能,使用Shiro易于理解的API,使你能够轻松的保护任何应用,如移动端应用,大型web应用以及企业级应用.

> Shiro可以非常容易的开发出足够好的应用,不仅可以用在JavaSE环境,也可以用在JavaEE环境


### 第2节 整体功能图

<center>

<img src="http://wiki.jikexueyuan.com/project/shiro/images/1.png">

</center>

* Authentication[ɔːˌθentɪˈkeɪʃn]:身份认证 / 登录,验证用户是不是拥有相应的身份
* Authorization[ˌɔːθəraɪˈzeɪʃn]:授权,即权限验证,验证某个已认证的用户是否拥有某个权限
* Session Manager:会话管理,即用户登录后就是一次会话,在没有退出之前,它的所有信息都在会话中
* Cryptography[krɪpˈtɒɡrəfi]:加密,保护数据的安全性,如密码加密存储到数据库,而不是明文存储
* Web Support:Web 支持,可以非常容易的集成到 Web 环境
* Caching:缓存,比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查数据库,这样可以提高效率
* Remember Me:记住我,这个是非常常见的功能,即一次登录后,下次再来的话不用登录了

### 第3节 核心API

<center>
<img src="http://wiki.jikexueyuan.com/project/shiro/images/2.png">

</center>

* Subject：主体,代表了当前 "用户",获取用户传递过来的数据,然后传递给SecurityManager: 安全管理器
* SecurityManager: 安全管理器(Shiro的核心),将用户传递过来的认证信息和数据库中保存的信息进行校验
* Realm:域,Shiro的Realm主要从数据库中获取安全数据(如用户、角色、权限)通过方法传递给SecurityManager安全管理器进行数据验证


### 第4节 内部架构图

<center>

<img src="http://wiki.jikexueyuan.com/project/shiro/images/3.png">

</center>

* Subject:当前用户主体(可以使任何与应用交互的用户)
* SecurityManager: 是Shiro的心脏;所有具体的交互都通过SecurityManager 进行控制;它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理
* Authenticator[ɔːˈθɛntɪkeɪtə]:认证器,负责主体认证的,这是一个扩展点,如果用户觉得 Shiro 默认的不好,可以自定义实现
* Authrizer:授权器,或者访问控制器
* Realm:可以有1个或多个Realm,是安全实体数据源,Shiro不知道你的用户 / 权限存储在哪及以何种格式存储,所以我们一般在应用中都需要实现自己的 Realm
* SessionManager: 会话管理器
* SessionDAO: session可以保存到数据库中或者是缓存中,或者是redis中,我们可以实现自己的SessionDAO对数据进行CRUD
* CacheManager:缓存控制器,来管理如用户、角色、权限等的缓存的;因为这些数据基本上很少去改变,放到缓存中后可以提高访问的性能
* Cryptography [krɪpˈtɒɡrəfi]:密码模块,Shiro提高了一些常见的加密组件用于如密码加密

### 第5节 核心名词介绍
```
身份验证就是我们通常说的登录,一般使用用户名/密码这样的常见组合,我们shiro中使用:
1. principals [ˈprɪnsəpəlz](用户名):身份,可以是任何东西,如用户名,邮箱等唯一即可。
2. credentials [krəˈdenʃlz]:证明 / 凭证，即只有主体知道的安全值,如密码 / 数字证书等
最常见的 principals 和 credentials 组合就是用户名 / 密码了
```
```
Subject: 主体
Realm  : 验证主体的数据源
```
### 第6节 Shiro核心对象介绍

* Md5Hash: MD5密码加密类
* DelegatingFilterProxy:代理类对象,存在spring-web包中,其作用就是一个filter的代理,用这个类的好处是可以通过spring容器来管理filter的生命周期
* ShiroFilterFactoryBean:ShiroFilter:权限控制的核心配置对象有Spring IOC容器创建，交给DelegatingFilterProxy代理
* DefaultWebSecurityManager: Shiro关于web的安全管理器对象
* AuthorizingRealm: 自定义Realm需要被继承的类,用于自定义Realm
* HashedCredentialsMatcher: 用于密码加密的类
* DefaultWebSessionManager: web的会话管理类
* UsernamePasswordToken: 封装用户名密码
* SimpleAuthenticationInfo:Realm认证方法的返回对象,封装从数据库查询出来认证的安全数据
* SimpleAuthorizationInfo:Realm授权方法的返回对象,封装从数据库查询出来授权的安全数据
```
以上是常见的认证/授权需要用到的类
```
---
## 第二章 Shiro快速入门

* Github源码下载
```
https://github.com/apache/shiro.git
```
* 在线源码下载
```
https://downloads.apache.org/shiro/1.2.6/shiro-root-1.2.6-source-release.zip
```
* 查看源码快速入门
* * shiro.ini 文件
```
[users]
# # 用户名=密码,角色   root: 用户名  secret:密码 admin: 角色
root = secret, admin
# 用户名=密码,角色
guest = guest, guest
# 用户名=密码,角色
presidentskroob = 12345, president
# 用户名=密码,角色,角色
darkhelmet = ludicrousspeed, darklord, schwartz
# 用户名=密码,角色,角色
lonestarr = vespa, goodguy, schwartz

# -----------------------------------------------------------------------------

[roles]
# 角色= *    *: 通配符代表所有权限
admin = *
# The 'schwartz' role can do anything (*) with any lightsaber:
schwartz = lightsaber:*
# The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
# license plate 'eagle5' (instance specific id)
goodguy = winnebago:drive:eagle5

```
* * 入门代码
```
public static void main(String[] args) {

    // 创建安全管理器 SecurityManager
    Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    SecurityManager securityManager = factory.getInstance();

    // 将安全管理器设置到SecurityUtils中
    SecurityUtils.setSecurityManager(securityManager);

    // 获取实体
    Subject currentUser = SecurityUtils.getSubject();

    // 获取会话
    Session session = currentUser.getSession();
    session.setAttribute("someKey", "aValue");
    String value = (String) session.getAttribute("someKey");
    if (value.equals("aValue")) {
        log.info("Retrieved the correct value! [" + value + "]");
    }

    // 判断是否认证
    if (!currentUser.isAuthenticated()) {
        UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        }
        // 其他异常
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
        }
    }

    // 
    log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

    // 判断当前用户有哪些角色
    if (currentUser.hasRole("schwartz")) {
        log.info("May the Schwartz be with you!");
    } else {
        log.info("Hello, mere mortal.");
    }

    // 判断当前用户是否有lightsaber角色的weild权限
    if (currentUser.isPermitted("lightsaber:weild")) {
        log.info("You may use a lightsaber ring.  Use it wisely.");
    } else {
        log.info("Sorry, lightsaber rings are for schwartz masters only.");
    }

    // 判断当前用户是否有winnebago角色的drive权限的eagle5操作
    if (currentUser.isPermitted("winnebago:drive:eagle5")) {
        log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                "Here are the keys - have fun!");
    } else {
        log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
    }

    // 登出
    currentUser.logout();
}
```
---
## 第三章 加密技术

### 第1节 数据加密

> 所谓数据加密（Data Encryption）技术是指将一个信息（或称明文,plain text）经过加密钥匙（Encryption key）及加密函数转换,变成无意义的密文（cipher text）,而接收方则将此密文经过解密函数、解密钥匙（Decryption key）还原成明文,加密技术是网络安全技术的基石

### 第2节 常见加密方式

#### 2.1 对称加密
> 对称加密算法是应用较早的加密算法,又称为共享密钥加密算法。在对称加密算法中,使用的密钥只有一个,发送和接收双方都使用这个密钥对数据进行加密和解密.这就要求加密和解密方事先都必须知道加密的密钥.

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEBfb86657ef464546adfc6a9a4af2dc60f?method=download&shareKey=505619aea2ba33a41e44658c44ad2dd8">

</center>

#### 2.2 非对称加密

> 非对称加密算法,又称为公开密钥加密算法。它需要两个密钥,一个称为公开密钥 (public key),即公钥,另一个称为私有密钥 (private key),即私钥。因为加密和解密使用的是两个不同的密钥,所以这种算法称为非对称加密算法.

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB477a71e1b5cf75b80cdc23ed72705622?method=download&shareKey=3f83efd617229a7cb3bd348f8104a86c">

</center>

### 第3节 常见的摘要算法

#### 3.1 MD5加密
```
MD5是一种摘要算法,它的典型应用是对一段信息产生信息摘要,以防止被篡改

public static final byte[] computeMD5(byte[] content) {
    try {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return md5.digest(content);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
```
#### 3.2 SHA-1加密
```
SHA-1 是和 MD5 一样流行的 消息摘要算法，然而 SHA-1 比 MD5 的 安全性更强。对于长度小于 2 ^ 64 位的消息

SHA-1 会产生一个 160 位的 消息摘要。基于 MD5、SHA-1 的信息摘要特性以及 不可逆，可以被应用在检查文件完整性以及数字签名等场景

public static byte[] computeSHA1(byte[] content) {
    try {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        return sha1.digest(content);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
```
---
## 第四章 Shiro在ssm框架(spring+springmvc+mybatis)中的使用

### 第1节 ssm框架整合
* pom.xml依赖
```
<properties>
    <spring.version>4.3.27.RELEASE</spring.version>
</properties>

<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>javax</groupId>
        <artifactId>javaee-api</artifactId>
        <version>7.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-all</artifactId>
        <version>1.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-ehcache</artifactId>
        <version>1.4.1</version>
    </dependency>
    <dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache-core</artifactId>
        <version>2.6.11</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aspects</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-expression</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-instrument</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-instrument-tomcat</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jms</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-messaging</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-oxm</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc-portlet</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-websocket</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <!-- mybatis核心包 -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.4.5</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.3.1</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.40</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.8.7</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.8.7</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.8.7</version>
    </dependency>
    <!--打印日志 -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.5</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.7.5</version>
    </dependency>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
    <dependency>
        <groupId>jstl</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.6</version>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>5.1.2</version>
    </dependency>
</dependencies>
```
* 整合(略)
* 数据库SQL语句
```
-- 1.sys_users用户表

CREATE TABLE sys_users (
  user_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  username VARCHAR (100) UNIQUE COMMENT '用户名',
  password VARCHAR(100) COMMENT '密码',
  salt VARCHAR(100) COMMENT '盐值'
) charset=utf8 ENGINE=InnoDB COMMENT="用户表";

-- 2.sys_roles角色表

CREATE TABLE sys_roles (
  role_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '角色编号',
  role_name VARCHAR(100) COMMENT '角色名称'
) charset=utf8 ENGINE=InnoDB COMMENT="角色表";

-- 3.sys_permissions权限表（或资源表）

CREATE TABLE sys_permissions (
  permission_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  permission_name VARCHAR(100) COMMENT '权限'
) charset=utf8 ENGINE=InnoDB COMMENT="权限表";

-- 4.sys_users_roles用户-角色关联表

CREATE TABLE sys_users_roles (
  ur_id  bigint PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  user_id bigint COMMENT '用户编号',
  role_id bigint COMMENT '角色编号'
) charset=utf8 ENGINE=InnoDB COMMENT="用户-角色关联表";


-- 5.sys_roles_permissions角色-权限关联表（或角色-资源关联表）
CREATE TABLE sys_roles_permissions (
  rp_id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
  role_id bigint COMMENT '角色编号',
  permission_id bigint COMMENT '权限编号'
) charset=utf8 ENGINE=InnoDB COMMENT="角色-权限关联表";
```
---
### 第2节 在WEB-INF/views文件夹下创建需要的页面
* 首页 index.jsp(首页创建在webapp下,非WEB-INF/views下)
```
<body>
    <h1>首页</h1>
    <a href="${pageContext.request.contextPath}/home">跳转Home页面</a>
</body>
```
* 主页 home.jsp
```
<body>
    <h1 style="text-align: center">Home页面</h1>
    <hr>
    <table border="1">
        <thead>
            <tr>
                <th>测试请求地址</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    /jumpLogin
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/jumpLogin">测试跳转登陆页面地址</a>
                </td>
            </tr>
            <tr>
                <td>
                    /jumpRegister
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/jumpRegister">测试跳转注册页面地址</a>
                </td>
            </tr>
            <tr>
                <td>
                    /getUserList
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/getUserList">测试获取用户列表页面地址</a>
                </td>
            </tr>
            <tr>
                <td>
                    /jumpEdit
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/jumpEdit">测试跳转更新页面地址</a>
                </td>
            </tr>
        </tbody>
    </table>
</body>
```
* 登陆页面 login.jsp
```
<body>
    <h1>登录</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">用户名:</label>
        <input id="username" type="text" name="username"><br/>
        <label for="password">用户名:</label>
        <input id="password" type="text" name="password"><br/>
        <input type="submit" value="登录">
    </form>
</body>
```
* 注册页面 register.jsp
```
<body>
    <h1>注册</h1>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">用户名:</label>
        <input id="username" type="text" name="username"><br/>
        <label for="password">用户名:</label>
        <input id="password" type="text" name="password"><br/>
        <input type="submit" value="注册">
    </form>
</body>
```
* 数据列表页面 user_list.jsp
```
<body>
    <h1>用户列表页面</h1>
</body>
```
* 为授权页面 unauthorized.jsp
```
<body>
    <h1>没有权限</h1>
</body>
```
* 更新页面 edit.jsp
```
<body>
    <h1 style="text-align: center">我是更新页面,我需要认证并且需要授权才能登陆</h1>
</body>
```
---
### 第3节 Shiro的基本配置

* 在web.xml配置shiro的代理filter
```
<filter>
    <filter-name>shiroFilter</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    <!--
        参数的意义:
        默认targetFilterLifecycle为false,当他为false的时候shiroFilter代理对象默认加入到IOC容器中
        并且在IOC容器中遵循IOC的生命周期管理,将其设置为true,让其受tomcat容器生命周期管理
    -->
    <init-param>
        <param-name>targetFilterLifecycle</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>shiroFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
* 在spring的核心配置文件(applicatioContext.xml)中配置其他Shiro其他配置
* * 配置被web.xml中Filter象代理的类
```
<!--被web.xml中配置的Filter代理对象代理的类-->
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    <!--配置安全管理器-->
    <property name="securityManager" ref="securityManager"></property>
    <!--如果访问的页面或者是请求地址需要认证才能访问,在没有认证的时候访问了,跳转此地址-->
    <property name="loginUrl" value="/jumpLogin"></property>
    <!--设置没有授权需要被跳转的地址-->
    <property name="unauthorizedUrl" value="/jumpUnauthorized"></property>
    <!--设置拦截规则-->
    <property name="filterChainDefinitions">
        <value>
            # anon: shiro的核心过滤器,表示/这个请求,可以匿名访问
            / = anon
            /home = anon
            /jumpLogin = anon
            /jumpRegister = anon
            # authc: shiro的核心过滤器,表示getUserList必须认证(登陆)才能访问
            /getUserList = authc
            # roles: shiro的核心过滤器,表示jumpEdit必须拥有admin角色才能访问
            /jumpEdit = authc,roles[admin]
            #设置角色的同时设置权限
            #/jumpEdit = authc,roles[admin],perms[delete]
            # ** :通配符,表示除了上面配置的拦截,其余的所有请求都需要认证
            /** = authc
        </value>
    </property>
</bean>
```
* * Shiro常见核心过滤器介绍

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB831385f01bad3de5616855f5e6781e0c?method=download&shareKey=a810f2c8c1cf83dc912f9408618bd070">

</center>

* * 配置Web安全管理器
```
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager"></bean>
```

* 编写控制器
```
@Controller
public class UserController {
    /**
     * 跳转Home页面
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        System.out.println("跳转home.jsp页面,此请求地址[home]设置为匿名访问...");
        return "home";
    }
    /**
     * 跳转登陆页面
     */
    @RequestMapping(value = "/jumpLogin")
    public String jumpLogin(){
        System.out.println("跳转login.jsp页面,此请求地址[jumpLogin]设置为匿名访问...");
        return "login";
    }
    /**
     * 跳转注册页面
     */
    @RequestMapping(value = "/jumpRegister")
    public String jumpRegister(){
        System.out.println("跳转register.jsp页面,此请求地址[jumpRegister]设置为匿名访问...");
        return "register";
    }
    /**
     * 跳转用户列表页面
     */
    @RequestMapping(value = "/getUserList")
    public String getUserList(){
        System.out.println("跳转user_list.jsp页面,此请求地址[getUserList]设置为认证访问...");
        return "user_list";
    }

    /**
     * 跳转更新页面
     */
    @RequestMapping(value = "/jumpEdit")
    public String jumpEdit(){
        System.out.println("跳转edit.jsp页面,此请求地址[jumpEdit]设置为认证,并且判断此用户是否是访问这个请求的角色...");
        return "edit";
    }
}
```
---
### 第4节 Shiro的其他配置

#### 4.1 注册功能实现
```
/**
 * Shiro用户注册
 * @param username: 用户名
 * @param password: 密码
 * @return
 */
@RequestMapping(value = "/register")
public String register(String username, String password, Model model){
    System.out.println("用户注册...入参为:"+username+"="+password);
    //判断前端发送过来的用户名和密码是否为空
    if((username!=null && username.length()>0) && (password!=null && password.length()>0)){
        //生成salt,我这里使用用户名作为盐,可以自己随意生成(比如UUDI或者随机数)
        String salt=username;
        /**
         * 在进行注册前要将密码进行盐值加密(我们采用MD5盐值加密方式),Shiro官方提供了Md5Hash类帮我们实现
         * 我们这里使用3个参数的构造方法
         * 第一个参数: 被加密的对象
         * 第二个参数： 加的盐
         * 第三个参数: 加密(迭代)次数
         */
        String source=password;//给谁加密
        Md5Hash md5Hash = new Md5Hash(source,salt,1024);
        //获取进过加盐和循环迭代多次的密码
        String targetPassword = md5Hash.toString();
        //调用业务逻辑成,调用mapper层,将新用户信息保存到数据库中
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(targetPassword);
        sysUser.setSalt(salt);
        sysUserService.addSysUser(sysUser);
        //注册成功跳转到登录页
        return "redirect:/jumpLogin";
    }else {
        //注册失败
        String msg="注册失败,用户名或者密码为空";
        model.addAttribute("msg",msg);
        return "register";
    }
}
```

#### 4.2 登陆实现

##### 4.2.1 控制器编写
```
/**
 * 用户登录
 * @param username: 用户名
 * @param password: 密码
 */
@RequestMapping(value = "/userLogin")
public String userLogin(String username,String password){
    System.out.println("用户登录...入参为:"+username+"="+password);

    /**
     * 用户登录采用Shiro帮助我们进行认证和授权
     */
    //获取Shiro实体
    Subject subject = SecurityUtils.getSubject();
    //使用Shiro提供的API对象封装前端传送过来的请求数据(用户名和密码)
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    //调用Shiro提供的方法进行验证
    try {
        subject.login(token);
    }catch (UnknownAccountException uae){
        System.out.println("用户名不存在:"+uae.getMessage());
    }catch (IncorrectCredentialsException ice){
        System.out.println("密码错误:"+ice.getMessage());
    }catch (LockedAccountException lae){
        System.out.println("用户被锁定:"+lae.getMessage());
    }catch (AuthenticationException ae){
        <!--由自定义Realm抛出,在此捕获-->
        System.out.println("其他异常:"+ae.getMessage());
    }
    //登陆成功跳转列表页(列表页需要认证才可以访问)
    return "redirect:/getUserList";
}
```
##### 4.2.2 自定义Realm实现
```java
/**
 * @Author 枫桥夜泊1990
 * @BLOG https://hd1611756908.github.io/
 * @BSITE https://space.bilibili.com/514155929/
 * @DATE 2020/8/9
 * realm:查询数据库获取用户认证和授权的数据,将其返回给安全管理器
 */
public class SysUserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUsersRolesService sysUsersRolesService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolesPermissionsService sysRolesPermissionsService;
    @Autowired
    private SysPermissionService sysPermissionService;
    /**
     * 授权操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户授权数据获取"+principalCollection);
        //获取用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
        //通过用户名查询用户ID
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        //获取当前用户下的所有角色
        List<SysUsersRoles> sysUsersRoles = sysUsersRolesService.getSysUsersRoles(sysUser.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将当前用户下的所有角色名称封装进SimpleAuthorizationInfo对象中
        Set<String> roleNames = new HashSet<>();
        for (SysUsersRoles usersRole : sysUsersRoles) {
            //通过角色ID查询角色名称
            SysRole sysRole = sysRoleService.getSysRole(usersRole.getRoleId());
            roleNames.add(sysRole.getRoleName());
            //通过角色查询当前用户的操作权限名称
            List<SimpleAuthenticationInfo> rolesPermissions = sysRolesPermissionsService.getSysRolesPermissionsList(usersRole.getRoleId());
            Set<String> permissionList = new HashSet<>();
            for (SysRolesPermissions rolesPermission : rolesPermissions) {
                //通过permissionId查询名称
                SysPermission permission = sysPermissionService.getSysPermission(rolesPermission.getPermissionId());
                permissionList.add(permission.getPermissionName());
            }
            info.addStringPermissions(permissionList);
        }
        //将角色名称设置进SimpleAuthorizationInfo对象中
        info.addRoles(roleNames);
        return info;
    }

    /**
     * 认证操作
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("用户认证数据获取"+authenticationToken);
        //获取用户名
        String username = authenticationToken.getPrincipal().toString();
        //使用用户名向数据库中查询数据
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        //如果用户为空抛出异常在控制器层进行捕获
        if(sysUser==null){
            throw new AuthenticationException("用户名或密码错误");
        }
        System.out.println("==========="+sysUser);
        //将查询出来的用户信息,通过SimpleAuthenticationInfo对象传递给安全管理器
        //将数据库中查询出来的盐进行转换
        ByteSource bytes = ByteSource.Util.bytes(sysUser.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser.getUsername(),sysUser.getPassword(),bytes,getName());
        return info;
    }
}
```
##### 4.2.3 密码加密技术/以及自定义Realm配置
```
<!--注册安全管理器-->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    <!--配置自定义realm,从数据库获取数据-->
    <property name="realm" ref="sysUserRealm"></property>
</bean>
<!--配置自定义realm-->
<bean id="sysUserRealm" class="com.qianfeng.shiro.SysUserRealm">
    <!--设置密码加密方式(MD5盐值加密)-->
    <property name="credentialsMatcher" ref="credentialsMatcher"></property>
</bean>
<!--配置加密-->
<bean id="credentialsMatcher" class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
    <!--加密类型-->
    <property name="hashAlgorithmName" value="MD5"></property>
    <!--迭代次数-->
    <property name="hashIterations" value="1024"></property>
</bean>
```
#### 4.3 多Realm实现

* 为什么要使用多Realm
```
如果当前应用多了很多外来用户(外来用户可能是公司兼并,或者是其他应用合并过来的等,总之认证数据不在同一个表里).
这时候一个Realm在实现起来比较不容易,可能两个表中,用户密码的加密方式不同,这时候一个Realm很难实现这样的策略,对于这种情况,Shiro提供了多Realm实现方式.
```
* 设置方式
```
Shiro中提供了一个多Realm的管理类ModularRealmAuthenticator帮助Shiro框架进行多个Realm管理


<!--注册安全管理器-->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    <!--在安全管理器中设置多Realm管理器,重点: 多Realm策略,authenticator必须配置在realms上面,否则认证不通过-->
    <property name="authenticator" ref="authenticator"></property>
    <!--配置多Realm-->
    <property name="realms">
        <list>
            <ref bean="userRealm01"></ref>
            <ref bean="userRealm02"></ref>
        </list>
    </property>
</bean>
<!--配置多Realm管理器-->
<bean id="authenticator" class="org.apache.shiro.authc.pam.ModularRealmAuthenticator">
    <!--设置多Realm管理器的策略-->
    <property name="authenticationStrategy">
        <!--默认策略-->
        <bean class="org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy"></bean>
    </property>
</bean>
<!--配置Realm01-->
<bean id="userRealm01" class="com.qianfeng.shiro.SysUserRealm">
    <!--使用内部bean设置加密方式-->
    <property name="credentialsMatcher">
        <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
            <property name="hashAlgorithmName" value="MD5"></property>
            <property name="hashIterations" value="1024"></property>
        </bean>
    </property>
</bean>
<!--配置Realm02-->
<bean id="userRealm02" class="com.qianfeng.shiro.SysUserRealm">
    <property name="credentialsMatcher">
        <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
            <property name="hashAlgorithmName" value="SHA-1"></property>
            <property name="hashIterations" value="1024"></property>
        </bean>
    </property>
</bean>
```
* 多Realm策略
* * FirstSuccessfulStrategy
```
当有一个Realm认证成功就为成功,只返回第一个Realm身份验证 成功的认证信息,其他的忽略
```
* * AtLeastOneSuccessfulStrategy(默认)
```
只要有一个Realm验证成功即可,和FirstSuccessfulStrategy不同,返回所有Realm身份验证成功的认证信息
```
* * AllSuccessfulStrategy
```
所有Realm验证成功才算成功,且返回所有Realm身份验证成功的认证信息,如果有一个失败就失败
```

#### 4.4 Shiro的缓存

* 开启缓存并配置缓存(在自定义Realm中定义)
```
<!--配置自定义realm-->
<bean id="sysUserRealm" class="com.qianfeng.shiro.SysUserRealm">
    <!--设置密码加密方式(MD5盐值加密)-->
    <property name="credentialsMatcher" ref="credentialsMatcher"></property>
    <!--开启shiro缓存-->
    <property name="cachingEnabled" value="true"></property>
    <!--启用身份验证缓存,默认false-->
    <property name="authenticationCachingEnabled" value="true"></property>
    <!--启用授权缓存,默认false-->
    <property name="authorizationCachingEnabled" value="true"></property>
    <!--缓存 AuthenticationInfo 信息的缓存名称-->
    <property name="authenticationCacheName" value="authenticationCache"></property>
    <!--缓存 AuthorizationInfo 信息的缓存名称-->
    <property name="authorizationCacheName" value="authorizationCache"></property>
    <!--配置ehcache缓存-->
    <property name="cacheManager">
        <bean class="org.apache.shiro.cache.ehcache.EhCacheManager"></bean>
    </property>
</bean>
```
#### 4.4 常见的JSP标签
```
Shiro为JSP页面提供了标签库类似于我们的JSTL,标签库地址为:
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
```
* guest 标签
```
<shiro:guest>
    欢迎游客访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</shiro:guest>

用户没有身份验证时显示相应信息，即游客访问信息
```
* authenticated 标签
```
<shiro:authenticated>
    用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>

用户已经身份验证通过，即 Subject.login 登录成功
```
* principal 标签
```
<shiro:principal/>

显示用户身份信息
```
* hasRole 标签
```
<shiro:hasRole name="admin">
    用户[<shiro:principal/>]拥有角色admin
</shiro:hasRole>

如果当前 Subject 有角色admin,显示内部内容
```
* hasAnyRoles 标签
```
<shiro:hasAnyRoles name="admin,user">
    用户[<shiro:principal/>]拥有角色admin或user<br/>
</shiro:hasAnyRoles>

如果当前 Subject 有任意一个角色（或的关系）将显示内部内容
```
* hasPermission 标签
```
<shiro:hasPermission name="user:create">
    用户[<shiro:principal/>]拥有权限user:create<br/>
</shiro:hasPermission>
```
#### 4.5 Shiro的会话管理
```
1、所谓会话，即用户访问应用时保持的连接关系,会话不结束,服务器可以一直识别当前用户
2、如果关闭浏览器,此次会话结束,下次在访问服务器,服务器会认为是全新的一次访问.需要重新认证
3、如果长时间保持连接,但是用户没有任何操作,那么会出现会话超时的问题,默认tomcat为30分钟
```
* Shiro多采用DefaultWebSessionManager进行会话管理
```
<!--会话管理-->
<bean id="webSessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
    <!--会话超时时间,单位毫秒-->
    <property name="globalSessionTimeout" value="10000"></property>
</bean>

<!--配置完会话之后将其设置到安全管理器中-->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    <!--配置自定义realm,从数据库获取数据-->
    <property name="realm" ref="sysUserRealm"></property>
    <!--会话管理-->
    <property name="sessionManager" ref="webSessionManager"></property>
</bean>
```
* 重启服务登陆,查看会话过期时间是否生效

#### 4.6 记住我功能实现

* 新增记住我测试页面 rememberme.jsp
```
<body>
    <h1>记住我页面测试</h1>
</body>
```
* 在控制器中添加方法(测试记住我)
```
/**
 *  记住我页面测试
 */
@RequestMapping(value = "/testRememberMe")
public String testRememberMe(){
    System.out.println("测试记住我...");
    //跳转记住我页面
    return "rememberme";
}
```
* 在配置文件中配置记住我功能(在安全管理器中)
```
<!--注册安全管理器-->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    <!--配置自定义realm,从数据库获取数据-->
    <property name="realm" ref="sysUserRealm"></property>
    <!--会话管理-->
    <property name="sessionManager" ref="webSessionManager"></property>
    <!--记住我-->
    <property name="rememberMeManager">
        <bean class="org.apache.shiro.web.mgt.CookieRememberMeManager">
            <property name="cookie">
                <bean class="org.apache.shiro.web.servlet.SimpleCookie">
                    <!--自定义cookie名称-->
                    <constructor-arg value="qianfeng"></constructor-arg>
                    <!--防止前端js使用document.cookie获取cookie-->
                    <property name="httpOnly" value="true"></property>
                    <!--过期时间,默认在浏览器关闭是过期 -1 有效期30天-->
                    <property name="maxAge" value="2592000"></property>
                </bean>
            </property>
        </bean>
    </property>
</bean>
```
* 修改ShiroFilterFactoryBean定义的规则(添加记住我拦截规则)
```
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    <!--配置安全管理器-->
    <property name="securityManager" ref="securityManager"></property>
    <!--如果访问的页面或者是请求地址需要认证才能访问,在没有访问的时候访问了,跳转此地址-->
    <property name="loginUrl" value="/jumpLogin"></property>
    <!--设置没有授权需要被跳转的地址-->
    <property name="unauthorizedUrl" value="/jumpUnauthorized"></property>
    <!--设置拦截规则-->
    <property name="filterChainDefinitions">
        <value>
            # anon: shiro的核心过滤器,表示/这个请求,可以匿名访问
            / = anon
            /home = anon
            /jumpLogin = anon
            /jumpRegister = anon
            /register = anon
            /userLogin = anon

            # authc: shiro的核心过滤器,表示getUserList必须认证(登陆)才能访问
            /getUserList = authc
            # roles: shiro的核心过滤器,表示jumpEdit必须拥有admin角色才能访问
            /jumpEdit = authc,roles[admin]
            # ** :通配符,表示除了上面配置的拦截,其余的所有请求都需要认证
            #/** = authc  #测试记住我,将通配拦截取消
            # user:shiro的核心过滤器,表示地址可以使用记住我登陆
            /testRememberMe = user
        </value>
    </property>
</bean>
```
* 控制器中修改登陆的规则(在登陆中调用Shiro记住我API方法)
```
/**
 * 用户登录
 * @param username: 用户名
 * @param password: 密码
 */
@RequestMapping(value = "/userLogin")
public String userLogin(String username,String password){
    System.out.println("用户登录...入参为:"+username+"="+password);

    /**
     * 用户登录采用Shiro帮助我们进行认证和授权
     */
    //获取Shiro实体
    Subject subject = SecurityUtils.getSubject();
    //使用Shiro提供的API对象封装前端传送过来的请求数据(用户名和密码)
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    token.setRememberMe(true);//记住我
    //调用Shiro提供的方法进行验证
    try {
        subject.login(token);
    }catch (UnknownAccountException uae){
        System.out.println("用户名不存在:"+uae.getMessage());
    }catch (IncorrectCredentialsException ice){
        System.out.println("密码错误:"+ice.getMessage());
    }catch (LockedAccountException lae){
        System.out.println("用户被锁定:"+lae.getMessage());
    }catch (AuthenticationException ae){
        System.out.println("其他异常:"+ae.getMessage());
    }
    //登陆成功跳转列表页(列表页需要认证才可以访问)
    return "redirect:/getUserList";
}
```
* 测试(打开浏览器调试模式)

---
