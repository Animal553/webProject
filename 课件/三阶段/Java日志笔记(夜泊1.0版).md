
## 第一章 日志是什么
```
1、记录程序的运行轨迹
2、定位程序运行中出现的问题
3、查找程序运行过程中的关键信息等.
```
## 第二章 认识常见的Java日志

### 第1节 Logging(JUL)
```
Java自带的日志工具类,在 JDK 1.5 开始就已经有了,在 java.util.logging 包下

官网地址: https://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html
```

---
### 第2节 Log4j
```
Log4j 是 Apache 的一个开源日志框架,也是市场占有率最多的一个框架.

但是log4j在2015年8月宣布不在维护了.作者在此基础上有重新写了一个Log4j2

官网地址: https://logging.apache.org/log4j/2.x/
```
---
### 第3节 commons-logging(JCL)
```
它是apache提供的日志门面接口,有个这门面接口用户就可以选择不同的日志实现,而不需要改动日志的定义,符合面向接口编程思想

官网地址: http://commons.apache.org/proper/commons-logging/
```
---
### 第4节 Slf4j
```
全称:Simple Logging Facade for Java,即简单日志门面接口,和commons-logging是一样的,它们都不是具体的日志框架,只是门面接口,要想使用需要具体的日志框架(log4j,logback等)

官网地址: http://www.slf4j.org/
```
---
### 第5节 Logback
```
Logback是Slf4j的原生实现框架,同样也是出自Log4j一个人之手,拥有比log4j更多的优点、特性和更做强的性能,现在基本都用来代替log4j成为主流

官网地址: https://logback.qos.ch/
```

### 第6节 日志的关系

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEBc31918be417c7352c90006f492ebc139?method=download&shareKey=8b630bcd611a531b104a60622f6d7bf6">
</center>

---
## 第三章 Java日志的最佳实践

### 第1节 Log4j日志

#### 1.1 Log4j简介

* 依赖添加
```
<dependency>  
    <groupId>log4j</groupId>  
    <artifactId>log4j</artifactId>  
    <version>1.2.17</version>  
</dependency>
```
* 日志级别
```
Log4J在Level类中定义了OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、ALL八种日志级别

一般只使用4个级别,优先级从高到低为 ERROR > WARN > INFO > DEBUG
```
* 日志组成
```
Log4J 主要由 Loggers(日志记录器)、Appenders(输出端)和Layout(日志格式化器)组成

1、Loggers 控制日志的输出级别与日志是否输出
2、Appenders 指定日志的输出方式(输出到控制台、文件等)
3、Layout 控制日志信息的输出格式
```
#### 1.2 Log4j 常用API
* Appender(输出端)
```
Appender 用来指定日志输出到哪个地方,可以同时指定日志的输出目的地
```

输出端 类型 | 作用
---|---
ConsoleAppender | 将日志输出到控制台
FileAppender | 将日志输出到文件中
DailyRollingFileAppender | 将日志输出到一个日志文件,并且时间(每天或者每时,每分等)输出到一个新的文件
RollingFileAppender | 将日志信息输出到一个日志文件,并且指定文件的尺寸,当文件大 小达到指定尺寸时,会自动把文件改名,同时产生一个新的文件
JDBCAppender | 把日志信息保存到数据库中


* Layout(日志格式化器)

格式化器类型 | 作用
---|---
HTMLLayout | 格式化日志输出为HTML表格形式
SimpleLayout | 简单的日志输出格式化,打印的日志格式为(info - message)
PatternLayout | 最强大的格式化期,可以根据自定义格式输出日志,如果没有指定转换格式,就是用默认的转换格式


* 日志输出格式 
```
%m   输出代码中指定的日志信息
%p   输出优先级，及 DEBUG、INFO 等
%n   换行符（Windows平台的换行符为 "\n"，Unix 平台为 "\n"）
%r   输出自应用启动到输出该 log 信息耗费的毫秒数
%c   输出打印语句所属的类的全名
%t   输出产生该日志的线程全名

%d   输出服务器当前时间，默认格式为ISO8601，也可以在后面指定格式。如：%d{yyyy年MM月dd日 HH:mm:ss}

%l   输出日志时间发生的位置，包括类名、发生的线程，以及在代码中的行数，如：Test.main(Test.java:10)

%F   输出日志消息产生时所在的文件名称

%L   输出代码中的行号

%x   输出和当前线程相关的 NDC（嵌套诊断环境）

%%   输出一个 "%" 字符

可以在 % 与字符之间加上修饰符来控制最小宽度、最大宽度和文本的对其方式。如：
%5c   输出category名称，最小宽度是5，category<5，默认的情况下右对齐
%-5c  输出category名称，最小宽度是5，category<5，"-"号指定左对齐,会有空格
%.5c  输出category名称，最大宽度是5，category>5，就会将左边多出的字符截掉，<5不会有空格
%20.30c  category名称<20补空格，并且右对齐，>30字符，就从左边交远销出的字符截掉
```

---
#### 1.3 快速入门

* 采用默认的配置(不适用log4j的配置文件)
```
/**
 * 不使用log4j配置文件采用默认配置
 * 在单元测试中测试
 */
@Test
public void test01(){
    //实例化Logger对象
    Logger logger = Logger.getLogger(Log4jTest.class);
    //设置默认的log4j配置,不使用log4j的配置文件
    BasicConfigurator.configure();
    /**
     * 设置日志的的输出级别
     * 常见日志的级别 ERROR > WARN > INFO > DEBUG
     * 如果我设置 INFO级别,那么只有INFO和比INFO级别好的日志可以打印,DEBUG不能被输出
     */
    logger.setLevel(Level.INFO);
    //输出日志
    logger.debug("我是info信息");
    logger.info("我是info信息");
    logger.warn("我是info信息");
    logger.error("我是info信息");

    /**
     * 0 [main] INFO cn.ukoko.Log4jTest  - 我是info信息
     * 1 [main] WARN cn.ukoko.Log4jTest  - 我是info信息
     * 1 [main] ERROR cn.ukoko.Log4jTest  - 我是info信息
     * 以上为控制台输出数据,DEBUG级别的日志没有被打印出来,因为级别在INFO之下
     */
}
```
* 使用log4j.properties
* * log4j.properties配置文件
```
# 控制台输出配置
log4j.appender.Console=org.apache.log4j.ConsoleAppender
# 设置输出日志的格式,设置自定义格式
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
# 设置日志的输出格式
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n


# 文件输出配置(按照时间分割日志)
log4j.appender.A = org.apache.log4j.DailyRollingFileAppender
# 指定日志的输出路径,最后log不是文件路径是下面DatePattern的前缀
log4j.appender.A.File = D:\\pic\\log
# 设置日志的生成方式(每分钟生成一个)
log4j.appender.A.DatePattern = '_'yyyy-MM-dd-HH-mm'.log'
# 是否进行日志内容的追加,默认为true,为追加
log4j.appender.A.Append = true
# 设置输出日志的级别,设置成DEBUG那么就会将DEBUG以及以上的级别全部输出
log4j.appender.A.Threshold = DEBUG
# 使用自定义日志格式化器
log4j.appender.A.layout = org.apache.log4j.PatternLayout
# 指定日志的输出格式
log4j.appender.A.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
# 指定日志的文件编码
log4j.appender.A.encoding=UTF-8

# 文件输出配置(按照文件大小分割日志)
log4j.appender.B = org.apache.log4j.RollingFileAppender
# 指定日志的输出路径,最后log不是文件路径是下面DatePattern的前缀
log4j.appender.B.File = D:\\pic\\file_log.log
# 是否进行日志内容的追加,默认为true,为追加
log4j.appender.B.Append = true
# 设置输出日志的级别,设置成DEBUG那么就会将DEBUG以及以上的级别全部输出
log4j.appender.B.Threshold = DEBUG
# 使用自定义日志格式化器
log4j.appender.B.layout = org.apache.log4j.PatternLayout
# 指定日志的输出格式
log4j.appender.B.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
# 指定日志的文件编码
log4j.appender.B.encoding=UTF-8
# 设置日志的大小
log4j.appender.R.MaxFileSize=20MB
# 约束生成文件的个数(防止硬盘爆掉)
log4j.appender.R.MaxBackupIndex=10

# 指定日志的输出级别与输出端,第一个参数是日志级别,其余的是设置那个日志appender；Console和A为自定义
log4j.rootLogger=DEBUG,Console,A,B
```
* * Java测试代码
```
/**
 * 会在控制台数据以及在d盘生成一个日志文件
 */
@Test
public void test02(){
    //实例化Logger对象
    Logger logger = Logger.getLogger(Log4jTest.class);
    //输出日志
    logger.debug("我是info信息");
    logger.info("我是info信息");
    logger.warn("我是info信息");
    logger.error("我是info信息");
}
```
---
### 第2节 slf4j+Log4j

* 添加依赖
```
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.21</version>
</dependency>
```
* 配置文件(只需要修改依赖即可,配置文件不变)
* Java使用
```
@Test
public void test03(){
    Logger logger = LoggerFactory.getLogger(Log4jTest.class);
    //输出日志
    logger.debug("debug={}","我是info信息");
    logger.info("info={}","我是info信息");
    logger.warn("warn={}","我是info信息");
    logger.error("error={}","我是info信息");
}
```
---
### 第3节 slf4j+Logback

* 添加依赖
```
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.1.7</version>
</dependency>
```
* 配置文件 logback.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!--
    scan（非必须）属性：默认为false,true表示扫描配置文件的变化并自动重新配置,默认每隔1分钟扫描一次
    scanPeriod（非必须）属性：搭配scan属性使用,设置扫描的间隔时间
-->
<configuration scan="true" scanPeriod="1 seconds">

    <!--
        设置自定义参数,类似于pom.xml里面的properties标签,下面可以使用${名称}引用此变量
    -->
    <property name="FILE_PATH" value="D:/"></property>

    <!--
        ConsoleAppender: 控制台输出
        STDOUT: 给appender起一个名字
    -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!--
                pattern: 日志输出的格式化样式
            -->
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>


    <!--时间滚动日志生成-->
    <appender name="TIME_ROLLING_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{0} -%msg%n</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--
                设置生成的文件地址和文件名
             -->
            <fileNamePattern>${FILE_PATH}/test%d{yyyy-MM-dd_HH-mm}.log</fileNamePattern>
            <!--最多生成十个,查过十个开始删除-->
            <maxHistory>10</maxHistory>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <!-- 文件大小触发重写新文件 -->
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <!--
        debug: 指定日志的输出级别,大于debug的级别都会被输出
    -->
    <root level="debug">
        <!--
            appender-ref: 指定哪一个appender使用此日志输出级别
        -->
        <appender-ref ref="STDOUT" />
        <appender-ref ref="TIME_ROLLING_FILE" />
    </root>
</configuration>
```
* Java使用
```
@Test
public void test01(){
    Logger logger = LoggerFactory.getLogger(AppTest.class);
    logger.info("info={}","这是info级别日志");
    logger.debug("debug={}","这是debug级别日志");
    logger.warn("warn={}","这是warn级别日志");
    logger.error("error={}","这是error级别日志");
}
```

---

> 其他详细配置请查询官网