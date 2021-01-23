

## 第一章 Maven介绍
```
maven是一个采用纯Java编写的开源项目管理工具。采用了一种被称之为POM(project object model)概念来管理项目，所有的项目配置信息都被定义在一个叫做pom.xml的文件中，通过该文件，maven可以管理项目的整个生命周期，包括编译，测试，打包，发布，运行等等。目前Apache下绝大多数项目都已经采用maven进行管理。而maven本身还支持多种插件，可以方便,灵活的控制项目。一句话：maven是一个项目管理和构建工具，主要对项目做编译，测试，打包，发布，运行等操作。
```
---
## 第二章 Maven安装

> 下载
```
http://maven.apache.org/
```
> 环境配置
```
和JDK的环境配置类似
MAVEN_HOME=maven安装路径
path=%MAVEN_HOME%\bin
```
---
## 第三章 Maven项目创建

### 3.1 命令创建
> 普通Java工程

```
mvn archetype:generate -DgroupId=com.qianfeng -DartifactId=myapp01 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn: 指定使用maven构建工具
archetype:generate ：原型生成器
-D ： pom.xml文件中的一个属性,如果不存在就生成，如果存在就覆盖
groupId： 项目属于哪个组
artifactId：项目名称
archetypeArtifactId: 指定插件
interactiveMode: 是否开启交互模式
```
> JavaWeb工程

```
mvn archetype:generate -DgroupId=com.qianfeng -DartifactId=mywebapp01 -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```
### 3.2 集成开发工具
```
演示集成Eclipse和IDEA
```
---
## 第四章 阿里云私服配置

### 4.1 阿里云Maven私服配置
```
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>central</mirrorOf>        
</mirror>
```
### 4.2 修改本地(.m2)仓库
```
Maven的第一次运行命令的时候会从云端下载很多的文件,这些文件都保存到了我们本机的.m2这个maven的默认仓库里,这个仓库默认在windows电脑的C盘用户目录下,随着添加的依赖越来越多,这时候这个文件夹会越来越大,所以可以重新指定默认本地仓库的位置.

设置方式：
1. 找到我们Maven的安装位置
2. 里面有一个conf文件夹
3. conf里面有一个settings.xml文件
   <localRepository>D:\soft\maven_repository</localRepository>
```
---
## 第五章 Maven依赖特性

### 5.1 依赖范围介绍
```
maven 项目不同的阶段引入到classpath中的依赖是不同的，例如，编译时，maven 会将与编译相关的依赖引入classpath中，测试时，maven会将测试相关的的依赖引入到classpath中，运行时，maven会将与运行相关的依赖引入classpath中，而依赖范围就是用来控制依赖于这三种classpath的关系.

例子:

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
</dependency>

其scope标签就是依赖范围的配置，默认是compile,可选配置有test、provided、runtime、system、import
```
### 5.2 依赖范围分类

* 编译依赖范围（compile）
```
该范围就是默认依赖范围，此依赖范围对于编译、测试、运行三种classpath都有效，举个简单的例子，假如项目中有spring-core的依赖，那么spring-core不管是在编译，测试，还是运行都会被用到，因此spring-core必须是编译范围（构件默认的是编译范围，所以依赖范围是编译范围的无须显示指定）
```
* 测试依赖范围(test)
```
使用此依赖范围的依赖，只对测试classpath有效，在编译主代码和项目运行时，都将无法使用该依赖，最典型的例子就是 Junit, 构件在测试时才需要，所以它的依赖范围是测试，因此它的依赖范围需要显示指定为<scope>test</scope> ,当然不显示指定依赖范围也不会报错，但是该依赖会被加入到编译和运行的classpath中,造成不必要的浪费

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
</dependency>
```
* 已提供依赖范围(provided)
```
使用该依赖范围的maven依赖，只对编译和测试的classpath有效，对运行的classpath无效，典型的例子就是servlet-api， 编译和测试该项目的时候需要该依赖，但是在运行时，web容器已经提供的该依赖，所以运行时就不再需要此依赖，如果不显示指定该依赖范围，并且容器依赖的版本和maven依赖的版本不一致的话，可能会引起版本冲突，造成不良影响

<dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-api</artifactId>
    <version>7.0</version>
    <scope>provided</scope>
</dependency>
```
* 运行时依赖范围(runtime)
```
使用该依赖范围的maven依赖，只对测试和运行的classpath有效，对编译的classpath无效，典型例子就是JDBC的驱动实现，项目主代码编译的时候只需要JDK提供的JDBC接口，只有在测试和运行的时候才需要实现上述接口的具体JDBC驱动

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
    <scope>runtime</scope>
</dependency>
```
* 导入依赖范围(import)
```
该依赖范围不会对三种classpath产生影响，该依赖范围只能与dependencyManagement元素配合使用，其功能为将目标pom文件中dependencyManagement的配置导入合并到当前pom的dependencyManagement中
```
* 系统依赖范围（system）
```
该依赖与classpath的关系与 provided依赖范围完全一致，但是系统依赖范围必须通过配置systemPath元素来显示指定依赖文件的路径，此类依赖不是由maven仓库解析的，而且往往与本机系统绑定，可能造成构件的不可移植，因此谨慎使用，systemPath元素可以引用环境变量
```

### 5.3 依赖传递
```
假如有Maven项目A，项目B依赖A，项目C依赖B。那么我们可以说 C依赖A。也就是说，依赖的关系为：C—>B—>A。那么我们执行项目C时，会自动把B、A都下载导入到C项目的jar包文件夹中，这就是依赖的传递性
```

### 5.4 依赖传递的排除
```
如上，C—>B—>A。加入现在不想执行C时把A下载进来，那么我们可以用<exclusions>标签

<dependencies>
    <dependency>
        <groupId>com.sc.</groupId>
        <artifactId>B</artifactId>
        <version>1.0</version>
         <exclusions>
            <exclusion>
              <!--被排除的依赖包坐标-->
              <groupId>com.sc.</groupId>
              <artifactId>A</artifactId>
              <version>1.0</version>
            </exclusion>
         </exclusions>
    </dependency>
</dependencies>
```
### 5.5 依赖冲突与解决
```
依赖冲突：一个项目A，通过不同依赖传递路径依赖于X，若在不同路径下传递过来的X版本不同，那么A应该导入哪个版本的X包呢？

冲突解决方案:

1：如果依赖路径的长度不同，则“短路优先”：

     A—>B—>C—>D—>E—>X(version 0.0.1)

     A—>F—>X(version 0.0.2)

     则A依赖于X(version 0.0.2)。

2：依赖路径长度相同情况下，则“先声明优先”：

     A—>E—>X(version 0.0.1)

     A—>F—>X(version 0.0.2)

     则在项目A的<dependencies></dependencies>中，E、F哪个在前则A依赖哪条路径的X

```
### 5.6 继承
```
继承，和java中的继承相当，作用就是复用
```
* 父类是dependencies标签
```
当父类使用dependencies管理依赖时,子类可以直接继承父类的所有依赖

<dependencies>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.47</version>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>

```
* 父类是dependencyManagement标签
```
当父类使用dependencyManagement管理依赖时，父类的依赖不会直接继承到子类,子类还需要添加依赖，但是版本不需要添加,父类会统一管理版本，这样可以灵活控制依赖,避免没有必要的依赖也被子类继承过去.


父类:
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
</dependencyManagement>

子类:
<dependencies>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
	
```

### 5.7 import使用
```
import只在dependencyManagement元素下才有效果，作用是将目标POM中的dependencyManagement配置导入并合并到当前POM的dependencyManagement元素中

例如:

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-framework-bom</artifactId>
	<version>4.3.10.RELEASE</version>
	<scope>import</scope>
	<type>pom</type>
</dependency>
```
### 5.8 properties属性
```
通过<properties>元素用户可以自定义一个或多个maven属性，然后在POM的其他地方使用${属性名}的方式引用该属性，这种做法的最大意义在于消除重复和统一管理.


<properties>
	<!-- 定义 spring版本号 -->
	<spring.version>4.3.10.RELEASE</spring.version>
</properties>

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-framework-bom</artifactId>
	<!-- 使用${}引入 -->
	<version>${spring.version}</version>
	<scope>import</scope>
	<type>pom</type>
</dependency>
```
---
## 第六章 Maven的常见命令
```
1、mvn compile 编译,将Java 源程序编译成 class 字节码文件。
2、mvn test 测试，并生成测试报告
3、mvn clean 将以前编译得到的旧的 class 字节码文件删除
4、mvn pakage 打包,动态 web工程打 war包，Java工程打 jar 包。
5、mvn install 将项目生成 jar 包放在仓库中，以便别的模块调用

命令还可以连着一起使用
mvn clean install -Dmaven.test.skip=true
```
---
## 第七章 Maven的插件介绍
```
jetty插件，功能和tomcat类似，一个web服务器插件

<plugins>
    <plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>9.2.2.v20140723</version>
    </plugin>
</plugins>

修改启动端口号命令: mvn -Djetty.port=9999 jetty:run 对应此版本
```
---
## 第八章 Maven私服安装和配置
* windows系统下进行安装
```
windows下安装版本 nexus-2.14.14-01-bundle.zip,如果需要安装包,登陆官网获取,或者加群联系群主

1. 解压之后的nexus目录结构
|--nexus-2.5.0-04
	|--bin
		|--jsw(这里面是 不同系统版本下的脚本)
		|--nexus（lunix支持 install uninstall start stop 四个命令）
		|--nexus.bat(windows 同上)
	|--conf
		|--examples
		|--jetty.xml
		|--logback.xml
		|--nexus.properties（只用修改这里面的项目及主要是jetty bind的IP和端口）
	|--lib（nexus web工程需要的Jar包）
	|--logs（日志输出目录）
	|--nexus（nexus war包解压后）
	|--tmp（tmp）
	|--LICENSE.txt
	|--NOTICE.txt
|--sonatype-work

2. 注册服务,将解压之后的nexus注册到windows服务中
    在bin目录下执行
    nexus install 注册为服务
    nexus uninstall 删除服务

3. 修改端口号
在nexus-2.14.14-01-bundle\nexus-2.14.14-01\conf\nexus.properties修改端口号为: application-port=8081

4. 启动nexus
    在bin目录下执行 nexus start 命令启动服务

错误解决:

出现错误
Started as Service
Launching a JVM...
Unable to execute Java command.  系统找不到指定的文件。 (0x2)

说明JDK安装路径和nexus指定的jdk执行路径不相同导致

解决方式: 
在Nexus安装目录下，找到【\~\bin\jsw\conf】文件夹，打开【wrapper.conf】文件
找到 # Set the JVM executable
设置 wrapper.java.command=jdk的java命令的地址
比如: wrapper.java.command=C:\soft\Java\jdk1.8.0_171\bin\java

修改完成重新启动:

访问：
http://127.0.0.1:8081/nexus
用户名: admin  密码: admin123
```
* 其他操作系统(略)
```
其他操作系统安装方式,请参考官网
```
---
## 第九章 Maven私服相关的常见配置

### 9.1 从私服获取数据
```
如果只是从私服获取依赖那么只需要在maven的setting.xml配置文件中配置镜像地址，镜像地址设置public群组,nexus有一个群组属性将多个库聚合到一起,所以直接引用public库即可

<mirror>
    <id>public</id> 
    <name>public repositroy</name>
    <url>http://192.168.43.211:8081/nexus/content/groups/public/</url>
    <mirrorOf>*</mirrorOf>        
</mirror>

    id: 起一个标记
    name: 介绍
    url: 组仓库的地址
    mirrorOf: 引组里面哪些仓库,“*”为全部
```

### 9.2 将自己的项目上传到私服
```
1. 需要在setting.xml配置文件中添加私服的用户名和密码
<server>
  <id>public</id>
  <username>admin</username>
  <password>admin123</password>
</server>
<server>
	<id>releases</id>
    <username>admin</username>
    <password>admin123</password>
</server>
<server>
	<id>snapshots</id>
    <username>admin</username>
    <password>admin123</password>
</server>

2. 在项目的pom.xml中配置将项目上传到Maven私服的哪个仓库中.
<distributionManagement>
    <!-- 上传稳定版或者发行版 -->
    <repository>
        <id>releases</id>  <!-- id对应server密码的id -->
        <url>http://192.168.43.211:8081/nexus/content/repositories/releases/</url>
    </repository>
    <!-- 上传开发板,或者不稳定版 -->
    <snapshotRepository>
        <id>snapshots</id>  <!-- id对应server密码的id -->
        <url>http://192.168.43.211:8081/nexus/content/repositories/snapshots/</url>
    </snapshotRepository>
</distributionManagement>
```
### 9.3 引入其他开发者上传到私服的项目依赖
```
1. 在Maven的setting.xml中配置mirror(上面已经介绍)
2. 在Maven的setting.xml中配置profile
<profile>
  <id>dev</id> 
  <repositories>
    <repository>
      <id>public</id>  <!-- 自定义 url为组的地址,也就是public即可,因为组内包含多个仓库 -->
      <url>http://192.168.43.211:8081/nexus/content/groups/public/</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
  </repositories>
</profile>

3. 在maven的setting.xml中配activefile
<activeProfiles>
    <!-- 指定profile -->
    <activeProfile>dev</activeProfile>
</activeProfiles>
```