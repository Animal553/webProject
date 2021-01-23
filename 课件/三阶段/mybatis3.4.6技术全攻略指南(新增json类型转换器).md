

## 第一章 mybatis简介

### 1.1 mybatis的历史
```
1. mybatis在2010年前叫ibatis是apache内部的一个项目，名字来源于internet+ibatis 是一个基于Java的持久层框架
2. 2010年以后从apache内部迁移到google code上，并且改名为mybatis
3. 2013年迁移到github上
```
### 1.2 mybatis是什么
```
1. 是一款优秀的持久层框架
2. 支持定制化sql，存储过程以及高级映射
3. 可以避免几乎所有的JDBC代码手动设置参数以及获取结果集
4. 可以使用简单的 XML 或注解来配置和映射原生信息
5. 用接口将Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录
```
### 1.3 mybatis的优势
```
1. 相比于JDBC没有侵入性可以达到很好的解耦效果
2. 相比于hibernate他有着高性能，可以满足当下绝大多数移动互联网时代的需求
3. sql代码分类放置在不同的sql map中易维护
```
### 1.4 mybatis的核心文件编写
```
1. POJO映射数据库表的Java对象
2. data access objects(DAOS)映射器接口类，用于操作数据库
3. sql maps : SQL映射配置文件,用于注册DAOs接口,使其接口有操作数据库的能力
4. mybatis配置文件,初始化一些基本的配置信息,用于生成SqlSessionFactory
```
---
## 第二章 入门HelloWorld

* 数据库表创建
```
//创建数据库
CREATE DATABASE mybatis DEFAULT CHARACTER SET UTF8;
//创建数据库表
CREATE TABLE user(
    `user_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_name` VARCHAR(100) NOT NULL COMMENT '用户名',
    `create_time` DATETIME DEFAULT NULL comment '创建时间'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="用户表";
```
* 入门HelloWorld代码的编写
```
①创建项目,添加jar包
    mybatis-3.4.6.jar
    mysql-connector-java-5.1.7-bin.jar
②根据官网的步骤一步步进行环境搭建.
③使用junit测试进行接口的测试.
```
* * 步骤

> 第1步 创建映射数据库表的实体类

```
/**
 * 映射数据库表的接口类
 * @author hu shuang
 * @email hd1611756908@163.com
 */
import java.util.Date;

public class User {
	//用户ID
	private Integer userId;
	//用户名
	private String userName;
	//创建时间
	private Date createTime;

	public User() {
	}
	public User(Integer userId, String userName, Date createTime) {
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
	}
}
```
> 第2步 创建操作数据库表的接口类

```
/**
 * 操作数据库表的接口
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public interface UserMapper {
	/*
	 * 添加用户
	 */
	void addUser(User user);
	/*
	 * 删除用户
	 */
	void deleteUser(Integer userId);
	/*
	 * 更新用户
	 */
	void updateUser(User user);
	/*
	 * 通过用户ID获取用户信息
	 */
	User getUser(Integer userId);
	/*
	 * 获取用户列表
	 */
	List<User> getUsers();
	
	/*
	 * 根据名字模糊查询获取用户列表
	 */
	List<User> getUsersLikeByUserName(@Param("userName") String userName);
}
```
> 第3步 创建注册操作数据库接口类的映射文件

```
操作数据库只能用SQL语句,但是mybatis提供了使用接口操作数据库,一般情况接口是没有操作数据库的能力的,但是 mybatis可以通过这个配置文件来注册接口，让接口有操作数据库的能力
```
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.im.mapper.UserMapper">
	<!-- 
		此配置文件用来注册UserMapper这个接口以及此接口中定义的方法
		namespace: 被注册接口的全类名
		insert: 插入数据的标签,还有其他三个标签 查询select、更新update、删除delete
		id: 接口里面定义的方法名字
		parameterType: 方法的入参类型(全类名)
		#{xxx}: 表达式  :  取方法的参数值
				如果方法的入参是基本数据类型那么xxx为形参名
				如果方法的入参为对象类型那么xxx为对象类型里面的属性名
		resultType:方法的返回值类型
		useGeneratedKeys: 让 myabtis 框架使用JDBC 通过 getGeneratedKeys 获取数据库主键.
		keyProperty: 映射通过getGeneratedKeys获取的键值
	 -->
	 <insert id="addUser" parameterType="com.im.entity.User" useGeneratedKeys="true" keyProperty="userId">
	 	INSERT INTO user(user_name,create_time) VALUES(#{userName},#{createTime})
	 </insert>
	 
	 <delete id="deleteUser" parameterType="Integer">
	 	DELETE FROM user WHERE user_id=#{userId}
	 </delete>
	 
	 <update id="updateUser" parameterType="com.im.entity.User">
	 	UPDATE user SET user_name=#{userName},create_time=#{createTime} WHERE user_id=#{userId}
	 </update>
	 
	 <select id="getUser" parameterType="Integer" resultType="com.im.entity.User">
	 	SELECT user_id as userId,user_name as userName,create_time as createTime FROM user WHERE user_id=#{userId}
	 </select>
	 
	 <select id="getUsers" resultType="com.im.entity.User">
	 	SELECT user_id as userId,user_name as userName,create_time as createTime FROM user
	 </select>
	 <!-- 
	  	构建LIKE参数
	  	方式一: CONCAT('参数1','参数2','参数3') 
	  	方式二: 在传参数时构建成 %李雷%
	  	方式三: 使用bind标签创建元素,放到上下文中
	  -->
	 <select id="getUsersLikeByUserName" resultType="com.sc.entity.User">
	  	SELECT user_id as userId,user_name as userName,create_time as createTime FROM user WHERE user_name like CONCAT('%',#{userName},'%')
	 </select>
	 <select id="getUsersLikeByUserName" resultType="com.sc.entity.User">
	  	SELECT user_id as userId,user_name as userName,create_time as createTime FROM user WHERE user_name like #{userName}
	 </select>
	 
	 <select id="getUsersLikeByUserName" resultType="com.sc.entity.User">
	  	<bind name="pattern" value="'%' + _parameter.userName + '%'" />
	  	SELECT user_id as userId,user_name as userName,create_time as createTime FROM user WHERE user_name like #{pattern}
	 </select>
</mapper>
```

> 第4步 创建mybatis核心配置文件 mybatis-config.xml,用于生成mybatis核心对象

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
      	<!-- 数据库驱动 -->
        <property name="driver" value="com.mysql.jdbc.Driver"/>
        <!-- mysql数据库地址 -->
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8"/>
        <!-- 数据库用户名 -->
        <property name="username" value="root"/>
        <!-- 数据库密码 -->
        <property name="password" value="root"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
  	<!-- 将注册接口的配置文件注册到mybatis的核心配置文件中,这是jar包版本,如果是maven项目，要放在resources文件夹下 -->
    <mapper resource="com/im/mapper/UserMapper.xml"/>
  </mappers>
</configuration>
```
> 第5步 通过单元测试类调用接口测试(mybatis原生API版调用)

```
public class UserMapperTest1 {

	/*
	 * 添加用户
	 */
	@Test
	public void testAddUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//创建statement
		String statement = "com.im.mapper.UserMapper.addUser";
		//构建入参
		User user = new User();
		user.setUserName("Tom");
		user.setCreateTime(new Date());
		int row = session.insert(statement, user);
		System.out.println("row:"+row);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 删除用户
	 */
	@Test
	public void testDeleteUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//创建statement
		String statement = "com.im.mapper.UserMapper.deleteUser";
		//调用mybatis的删除数据的API 
		int row = session.delete(statement, 1);
		System.out.println("row:"+row);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 更新用户
	 */
	@Test
	public void testUpdateUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//创建statement
		String statement = "com.im.mapper.UserMapper.updateUser";
		//调用mybatis的更新数据的API 
		//构建更新入参
		User user = new User(2, "polly", new Date());
		int row = session.update(statement, user);
		System.out.println("row:"+row);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 根据ID获取用户
	 */
	@Test
	public void testGetUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//创建statement
		String statement = "com.im.mapper.UserMapper.getUser";
		//调用mybatis的查询单个数据的API 
		User user = session.selectOne(statement, 2);
		System.out.println("user:"+user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 获取用户列表
	 */
	@Test
	public void testGetUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//创建statement
		String statement = "com.im.mapper.UserMapper.getUsers";
		//调用mybatis的查询列表数据的API 
		List<User> list = session.selectList(statement);
		System.out.println("users:"+list);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}
```
> 第6步 通过单元测试类调用接口测试(mybatis映射器版调用)

```
public class UserMapperTest2 {
	/*
	 * 添加用户
	 */
	@Test
	public void testAddUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建入参
		User user = new User();
		user.setUserName("张三");
		user.setCreateTime(new Date());
		//调用添加用户方法
		userMapper.addUser(user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 删除用户
	 */
	@Test
	public void testDeleteUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//调用删除方法
		userMapper.deleteUser(2);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 更新用户
	 */
	@Test
	public void testUpdateUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建更新入参
		User user = new User(2, "polly", new Date());
		//调用更新方法
		userMapper.updateUser(user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 根据ID获取用户
	 */
	@Test
	public void testGetUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//调用查询方法
		User user = userMapper.getUser(3);
		System.out.println("user:"+user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 获取用户列表
	 */
	@Test
	public void testGetUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//调用查询全部方法
		List<User> users = userMapper.getUsers();
		System.out.println("users:"+users);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}
```
---
## 第三章 mybatis常用属性配置

* properties:导入外部属性资源文件，解决数据库配置信息写到Java代码中的问题
```java
<properties resource="db.properties"></properties>
<environments default="development">
	<environment id="development">
		<transactionManager type="JDBC" />
		<dataSource type="POOLED">
			<!-- 数据库驱动 -->
			<property name="driver" value="${jdbc_driver}" />
			<!-- mysql数据库地址 -->
			<property name="url" value="${jdbc_url}" />
			<!-- 数据库用户名 -->
			<property name="username" value="${user}" />
			<!-- 数据库密码 -->
			<property name="password" value="${password}" />
		</dataSource>
	</environment>
</environments>
```
* typeAliases: 解决别名的问题
```java
在mybatis-config.xml配置文件中配置别名

<!-- 别名设置 -->
<typeAliases>
	<!-- 将全类名 com.im.entity.User 映射成别名 user -->
	<!-- 单个设置 -->
	<!-- <typeAlias type="com.im.entity.User" alias="user"/> -->
	<!-- 多个设置，将放在com.im.entity包下的所有Javabean都设置别名 -->
	<package name="com.im.entity"/>
</typeAliases>
```
<center>
<img src="https://note.youdao.com/yws/api/personal/file/WEB558b8aae7b33719d29689ea0648dca2e?method=download&shareKey=233b9c7c99b2612638c88c52061f043e">

</center>

* setting: mybatis的一些常用配置
```
在mybatis-config.xml配置文件中配置一些mybatis的基本属性

<settings>
  <!-- 二级缓存开启 默认开启 -->
  <setting name="cacheEnabled" value="true"/>
  <!-- 懒加载 默认值 false -->
  <setting name="lazyLoadingEnabled" value="true"/>
  <!-- 即时加载 默认是false (true in ≤3.4.1)在3.4.1版本以前默认值为true -->
  <setting name="aggressiveLazyLoading" value="true"/>
  <!-- 数据库经典列名和Java经典列名的映射（默认值为false） -->
  <setting name="mapUnderscoreToCamelCase" value="false"/>
</settings>




mapUnderscoreToCamelCase:简单介绍一下此属性的使用,其他的属性会在后面一一介绍。

在日常Java开发中我们的命名规则驼峰状比如: aaaBbbCcc
而我们的数据库命名规则为: AAA_BBB_CCC，因为命名规则
的不同，在进行Java POJO和数据库表关系映射时，通过反射
就会出现名称对应不上出现属性找不到数据为空的问题,解决方式一般有两种
- ①通过SQL的设置别名
- ②设置mapUnderscoreToCamelCase属性为true 
    
第一种方式我们上面已经用过，接下来我们使用mybatis提供的配置方式解决这个问题,但是一定要注意数据库的命名和Java的命名，一定要遵循自己的命名规范

```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB7d9d44efa80e645e97bbd41d9105bc41?method=download&shareKey=9f119b142aabb8b438dd325416ea6936">

</center>

* mappers: 映射文件的配置
```
在mybatis-config.xml配置文件中配置

<mappers>
	<!-- 将注册接口的配置文件注册到mybatis的核心配置文件中，
	     如果有多个映射文件,可以配置多个,还有其他方式会在后面介
	     绍 -->
	<mapper resource="com/im/mapper/UserMapper1.xml" />
	<mapper resource="com/im/mapper/UserMapper2.xml" />
	<mapper resource="com/im/mapper/UserMapper3.xml" />
</mappers>
```
---
## 第四章 映射枚举类型
```
映射枚举的时候比较特殊,mybatis官方给我们提供了将枚举映射成字符串或者整数类型两种方式.看下面的实现方式.
```
* 表设计
```
数据库表的SQL语句

CREATE TABLE student(
    `stu_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `stu_name` VARCHAR(100) NOT NULL COMMENT '学生名',
    `gender1` INT NOT NULL COMMENT '性别整数类型',
    `gender2` VARCHAR(10) NOT NULL COMMENT '性别字符类型'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="学生表";
```
* 映射数据库表的实体类
```
/**
 * 性别的枚举类
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public enum Gender {
	MAN("男"), 
	WOMAN("女"),
	NEUTRAL("中性");
	private String sex;
	private Gender(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}

<------------------分隔线----------------------->

/**
 * 	映射数据库student表的类
 *  @author hu shuang
 *  @email hd1611756908@163.com
 */
public class Student {
	private Integer stuId;
	private String stuName;
	//性别映射数据库整数类型
	private Gender gender1;
	//性别映射数据库字符类型
	private Gender gender2;
	public Student() {
	}
}
```
* 操作学生表的映射器接口
```
/**
 *  操作学生表的映射器接口
 *	@author hu shuang
 *  @email hd1611756908@163.com
 */
public interface StudentMapper {
	/*
	 * 添加一个学生
	 */
	void addStudent(Student student);
	/*
	 * 更新一个学生
	 */
	void updateStudent(Student student);
	/*
	 * 获取学生列表
	 */
	List<Student> getStudents();
	
}
```
* 注册接口的xml配置文件
```
注册接口的xml配置文件

<mapper namespace="com.im.mapper.StudentMapper">
	<!-- 
		1、Java枚举值映射到数据库表里面的整型字段时，映射的是枚举的数组下标值
		   使用typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler"
		2、Java枚举值映射到数据库表里面的varchar类型字段时,使用typeHandler="org.apache.ibatis.type.EnumTypeHandler"
	-->
	<insert id="addStudent" parameterType="student" useGeneratedKeys="true" keyProperty="stuId">
		INSERT INTO student(stu_name,gender1,gender2) VALUES(#{stuName},
		#{gender1,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},
		#{gender2,typeHandler=org.apache.ibatis.type.EnumTypeHandler})
	</insert>
	
	<!-- 查询带有枚举类型的数据时，返回值结果类型不能使用resultType属性设置，需要使用resultMap属性重新进行映射 -->
	<resultMap type="student" id="studentMap">
		<id property="stuId" column="stu_id"/>
		<result property="stuName" column="stu_name"/>
		<!-- 映射下标 -->
		<result property="gender1" column="gender1" typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler"/>
		<!-- 映射字符串 -->
		<result property="gender2" column="gender2" typeHandler="org.apache.ibatis.type.EnumTypeHandler"/>
	</resultMap>
	<select id="getStudents" resultMap="studentMap">
		SELECT * FROM student
	</select>
	
	<!-- 更新枚举类型 -->
	<update id="updateStudent" parameterType="student">
		UPDATE student SET stu_name=#{stuName},gender1=#{gender1,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},gender2=#{gender2,typeHandler=org.apache.ibatis.type.EnumTypeHandler} WHERE stu_id=#{stuId}
	</update>

</mapper>
```
* 测试枚举类型的增改查
```
/**
 * 测试枚举类型的增删改
 * @author hu shuang
 * @email  hd1611756908@163.com
 */
public class StudentMapperTest {
	/*
	 * 添加学生
	 */
	@Test
	public void testAddStudent() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setStuName("韩梅梅");
		student.setGender1(Gender.MAN);
		student.setGender2(Gender.MAN);
		mapper.addStudent(student);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	/*
	 * 获取学生列表
	 */
	@Test
	public void testGetStudents() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		List<Student> students = mapper.getStudents();
		System.out.println(students);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}

	
	/*
	 * 更新学生信息
	 */
	@Test
	public void testUpdateStudent() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setStuId(1001);
		student.setStuName("polly");
		student.setGender1(Gender.WOMAN);
		student.setGender2(Gender.WOMAN);
		mapper.updateStudent(student);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}

```
---
## 第五章 SQL map(注解) 方式进行数据库操作
```
注解方式操作数据库:

1. 不需要注册接口的xml配置文件
2. 直接将SQL写到操作数据库的接口里的每一个方法上
3. 核心配置文件 mapper 直接配置类
<mappers>
	<!-- 将注册接口的配置文件注册到mybatis的核心配置文件中 -->
	<mapper class="com.im.mapper.UserMapper"/>
</mappers>
```
* 操作数据库的接口
```
/**
 * 操作数据库表的接口
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public interface UserMapper {
	/*
	 * 添加用户
	 */
	@Insert("INSERT INTO user(user_name,create_time) VALUES(#{userName},#{createTime})")
	public void addUser(User user);
	/*
	 * 删除用户
	 */
	@Delete("DELETE FROM user WHERE user_id=#{userId}")
	void deleteUser(Integer userId);
	/*
	 * 更新用户
	 */
	@Update("UPDATE user SET user_name=#{userName},create_time=#{createTime} WHERE user_id=#{userId}")
	void updateUser(User user);
	/*
	 * 通过用户ID获取用户信息
	 */
	@Select("SELECT * FROM user WHERE user_id=#{userId}")
	User getUser(Integer userId);
	/*
	 * 获取用户列表
	 */
	@Select("SELECT * FROM user")
	List<User> getUsers();
}
```
* 单元测试类
```
public class UserMapperTest {

	@Test
	public void testAddUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//构建入参
		User user = new User();
		user.setUserName("张三");
		user.setCreateTime(new Date());
		mapper.addUser(user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	@Test
	public void testDeleteUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.deleteUser(2);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	@Test
	public void testUpdateUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		//构建入参
		User user = new User();
		user.setUserId(5);
		user.setUserName("JIM");
		user.setCreateTime(new Date());
		mapper.updateUser(user);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	@Test
	public void testGetUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> users = mapper.getUsers();
		System.out.println(users);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}
```
---
## 第六章 mybatis的日志功能

### 6.1 日志的介绍
```
mybatis内置日志工厂提供日志功能，内置日志工厂将日志交给以下其中一种工具做代理:

1. SLF4J
2. Apache Commons Logging
3. Log4j 2
4. Log4j
5. JDK logging

mybatis 内置日志工厂基于运行时自省机制选择合适的日志工具。它会使用第一个查找得到的工具（按上文列举的顺序查找）。如果一个都未找到，日志功能就会被禁用。

```
### 6.2 日志配置
```
不少应用服务器（如 Tomcat 和 WebShpere）的类路径中已经包含 Commons Logging，所以在这种配置环境下的MyBatis会把它作为日志工具，记住这点非常重要。这将意味着，在诸如 WebSphere 的环境中，它提供了 Commons Logging的私有实现，你的Log4J配置将被忽略。MyBatis将你的Log4J配置忽略掉是相当令人郁闷的（事实上，正是因为在这种配置环境下，MyBatis 才会选择使用 Commons Logging而不是Log4J）。如果你的应用部署在一个类路径已经包含 Commons Logging 的环境中，而你又想使用其它日志工具，你可以通过在 MyBatis 配置文件mybatis-config.xml里面添加一项setting 来选择别的日志工具。


<configuration>
  <settings>
    ...
    <setting name="logImpl" value="LOG4J"/>
    ...
  </settings>
</configuration>


logImpl 可选的值有：SLF4J、LOG4J、LOG4J2、JDK_LOGGING、COMMONS_LOGGING、STDOUT_LOGGING、NO_LOGGING，或者是实现了接口org.apache.ibatis.logging.Log的，且构造方法是以字符串为参数的类的完全限定名。
(译者注：可以参考org.apache.ibatis.logging.slf4j.Slf4jImpl.java的实现)
```
### 6.3 日志的使用
```
第1步: 添加jar包

log4j-1.2.17.jar
slf4j-api-1.7.25.jar
slf4j-log4j12-1.7.25.jar

第2步: 添加日志配置文件log4j.xml

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration
	xmlns:log4j="http://jakarta.apache.org/log4j/">
	<appender name="STDOUT"
		class="org.apache.log4j.ConsoleAppender">
		<param name="Encoding" value="UTF-8" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern"
				value="%-5p %d{MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n" />
		</layout>
	</appender>
	<logger name="java.sql">
		<level value="debug" />
	</logger>
	<logger name="org.apache.ibatis">
		<level value="debug" />
	</logger>
	<root>
		<level value="debug" />
		<appender-ref ref="STDOUT" />
	</root>
</log4j:configuration>
```

---
## 第七章 动态SQL

### 7.1 动态SQL简介（and的问题用where标签解决，逗号的问题用set标签解决）
* 动态SQL优势
```
mybatis的强⼤特性之一就是动态sql,有过根据不同条件拼接SQL经历的同学应该知道当条件多的时候拼接SQL是一件很痛苦的事情，但是mybatis却很好的帮我们解决了拼接SQL的烦恼
```
* 动态SQL所涉及到的标签
```
1. if
2. choose(when,otherwise)(类似于我们Java中的switch语句)
3. trim(where,set)
4. foreach(多用于批量操作)
```
### 7.2 动态SQL使用

#### 7.2.1 if语句(用于where子句之后作为条件判断)
* 使用常量(1=1)解决由于部分条件匹配而产生的多AND造成SQL语法错误的问题
```
<!-- if可以作为条件判断 并且使用 1=1 来解决由于条件不匹配而产生的多AND造成SQL语法错误的问题 -->
<select id="getBooks" parameterType="book" resultType="book">
	SELECT * FROM book WHERE 1=1
	<if test="bookName!=null">AND book_name=#{bookName}</if>
	<if test="author!=null and author.name!=null">AND author_name=#{author.name}</if>
	<if test="price!=null">AND price=#{price}</if> 
</select>
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB061a19c5a3b7c0baf087a7f31875166b?method=download&shareKey=7abb9dc3097d16ffccb6aa0933aa3366">

</center>


* 使用<where>标签解决由于部分条件匹配而产生的多AND造成SQL语法错误的问题
```
<!-- if可以作为条件判断 并且使用<where>标签来解决由于条件不匹配而产生的多AND造成SQL语法错误的问题 -->
<select id="getBooks" parameterType="book" resultType="book">
	SELECT * FROM book
	<where>
		<if test="bookName!=null">AND book_name=#{bookName}</if>
		<if test="author!=null and author.name!=null">AND author_name=#{author.name}</if>
		<if test="price!=null">AND price=#{price}</if> 
	</where>
</select>
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB97fa1dc4f8a1dcc94a08daae823d9e75?method=download&shareKey=9a4f41817d4838c0c28ee393539cb357">

</center>

#### 7.2.2 if语句(用于更新语句作为入参判断)

* 解决在增量更新时，因为不是所有字段都更新,所以有些字段没有赋值，造成的更新数据库某些数据被置空的为题
```
<update id="updateBook" parameterType="book">
	UPDATE book SET 
	<if test="bookNam!=null">book_name=#{bookName},</if>
	<if test="author!=null and author.name!=null">author_name=#{author.name},</if>
	<if test="price!=null">price=#{price}</if>
	WHERE book_id=#{bookId}
</update>
```
* 注意事项
```
在更新时，由于有些字段不需要更新而造成的SQL语句多逗号(,)问题
```
* 解决方式(<set>标签)
```
<update id="updateBook" parameterType="book">
	UPDATE book 
	<set>
		<if test="bookNam!=null">book_name=#{bookName},</if>
		<if test="author!=null and author.name!=null">author_name=#{author.name},</if>
		<if test="price!=null">price=#{price}</if>
	</set>
	WHERE book_id=#{bookId}
</update>
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB0da056304be34fd2b0a5e355f7e1ff55?method=download&shareKey=a6a05b0000575db0118d831edcd955bb">

</center>

#### 7.2.3 choose (when, otherwise)
```
用于匹配多个条件中的一个(不常用)

<select id="getBooks" parameterType="book" resultType="book">
	SELECT * FROM book WHERE
	<choose>
		<when test="bookName!=null">book_name=#{bookName}</when>
		<when test="author!=null and author.name!=null">author_name=#{author.name}</when>
		<when test="price!=null">price=#{price}</when>
		<otherwise>
			book_id=#{bookId}
		</otherwise>
	</choose>
</select>
```
#### 7.2.4 foreach(多用于批量操作)

* 批量操作Mapper层接口
```
/**
 * 操作数据库表的接口
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public interface UserMapper {
	/*
	 * 批量添加
	 */
	//void batchAddUser(List<User> users); //根据类型匹配
	void batchAddUser(@Param("users") List<User> users);//根据名称匹配
	
	/*
	 * 批量查询
	 */
	void batchQueryUsers(List<Integer> ids);
	/*
	 * 批量删除
	 */
	void batchDeleteUsers(List<Integer> ids);
	/*
	 * 批量更新
	 */
	void batchUpdateUsers(List<User> users);
	
}
```
* 批量添加语法
* * 方式一
```
方式一:
 <!-- 批量添加 -->
 <!-- 根据类型匹配 -->
 <insert id="batchAddUser" parameterType="user">
 	INSERT INTO user(user_name,create_time) VALUES 
 	<foreach collection="list" item="item"  separator="," >
 		(#{item.userName},#{item.createTime})
 	</foreach>
 </insert>
```
* * 方式二
```
<!-- 根据名称匹配 -->
 <insert id="batchAddUser" parameterType="user">
 	INSERT INTO user(user_name,create_time) VALUES 
 	<foreach collection="users" item="item"  separator="," >
 		(#{item.userName},#{item.createTime})
 	</foreach>
 </insert>
```
* 批量查询语法
```
<!-- 批量查询 -->
 <select id="batchQueryUsers" resultType="user">
 	SELECT * FROM user WHERE user_id IN 
 	<foreach collection="list" item="item" open="(" close=")" separator=",">
 		#{item}
 	</foreach>
 </select>
```
* 批量删除语法
```
 <!-- 批量删除 -->
 <delete id="batchDeleteUsers">
 	DELETE FROM user WHERE user_id IN 
 	<foreach collection="list" item="id" open="(" separator="," close=")">
 		#{id}
 	</foreach>
 </delete>
```
* 批量更新语法
* * 批量更新配置
```
使用mybatis进行批量更新数据库数据时，需要在url地址后面加上allowMultiQueries参数等于true
例如: jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8&allowMultiQueries=true
注意:在properties文件中使用&号;在xml配置文件中使用&amp;连接参数
```
* * 语法格式
```
 <!-- 批量更新 -->
 <update id="batchUpdateUsers" parameterType="user">
 	<foreach collection="list" item="item"  open="" separator=";" close="">
 		UPDATE user 
 		<set>
 			<if test="item.userName!=null">user_name=#{item.userName},</if>
 			<if test="item.createTime!=null">create_time=#{item.createTime}</if>
 		</set>
 		WHERE user_id=#{item.userId}
 	</foreach>
 </update>
```
* 单元测试代码
```
public class UserMapperTest {
	/*
	 * 批量添加
	 */
	@Test
	public void testBatchAddUser() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建入参
		List<User> users = new ArrayList<>();
		User user1 = new User(null, "EEE", new Date());
		User user2 = new User(null, "FFF", new Date());
		User user3 = new User(null, "GGG", new Date());
		User user4 = new User(null, "HHH", new Date());
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		//调用添加用户方法
		userMapper.batchAddUser(users);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	/*
	 * 批量查询
	 */
	@Test
	public void testBatchQueryUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建入参
		List<Integer> ids = new ArrayList<>();
		
		ids.add(3);
		ids.add(5);
		ids.add(7);
		
		//调用批量查询用户方法
		List<User> users = userMapper.batchQueryUsers(ids);
		System.out.println(users);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	/*
	 * 批量删除
	 */
	@Test
	public void testBatchDeleteUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建入参
		List<Integer> ids = new ArrayList<>();
		
		ids.add(3);
		ids.add(5);
		ids.add(7);
		
		//调用批量删除方法
		userMapper.batchDeleteUsers(ids);
		//提交
		session.commit();
		//关闭会话
		session.close();
	} 
	/*
	 * 批量更新
	 */
	@Test
	public void testBatchUpdateUsers() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		//获取映射器类的对象实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//构建入参
		List<User> users = new ArrayList<>();
		
		User user1 = new User(4,"MMM", new Date());
		User user2 = new User(6,"VVV", new Date());
		User user3 = new User(8,"WWW", new Date());
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		//调用批量更新方法
		userMapper.batchUpdateUsers(users);
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}
```
---
## 第八章 高级查询

### 8.1 数据库表创建(部门表/员工表)
```
CREATE TABLE department(
    `dept_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `dept_name` VARCHAR(20) NOT NULL COMMENT '部门名称'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="部门表";

CREATE TABLE employee(
    `emp_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `emp_name` VARCHAR(20) NOT NULL COMMENT '员工名称',
    `salary` DOUBLE(10,2) DEFAULT 0.00 COMMENT '工资',
    `dept_id` INT NOT NULL COMMENT '部门ID，关联部门表的主键'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="员工表";

//关联
ALTER TABLE employee ADD FOREIGN KEY(dept_id) REFERENCES department(dept_id);

INSERT INTO department(dept_name) VALUES('行政部'),('人事部'),('IT部');

INSERT INTO employee(emp_name,salary,dept_id) VALUES('孙悟空',100.12,1000),('猪八戒',100.12,1001),('沙悟净',100.12,1002);
```
### 8.2 创建映射数据库表的实体类
```
/**
 * 映射数据库部门表
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public class Department {
	private Integer deptId;
	private String deptName;
	// 一个部门下有多个员工(一对多关联)
	private List<Employee> employees;

	public Department() {
	}
}
```
```
/**
 * 映射数据库员工表
 * @author hu shuang
 * @email hd1611756908@163.com
 */
public class Employee {

	private Integer empId;
	private String empName;
	private Double salary;
	// 一个员工对应一个部门(一对一关联)
	private Department department;

	public Employee() {
	}
}
```
### 8.3 创建操作数据库的映射器接口
```
1. (一对一)查询员工信息,将员工的部门信息同时查出(一个员工属于一个部门)
2. (一对多)查询部门信息,将此部门下的所有员工信息都查询出来(一个部门下有多个员工)
```
```
public interface DepartmentMapper {

	/*
	 * 根据部门ID查询部门详情,不需要将当前部门下的员工列表查出
	 */
	Department getDepartmentByDeptId(Integer deptId);
	
	/*
	 * 根据部门ID查询部门详情,需要将当前部门下的员工列表查询出来(方式一 一对多关联查询 单条SQL)
	 */
	Department getDepartmentByDeptId_(Integer deptId);
	
	/*
	 * 根据部门ID查询部门详情,需要将当前部门下的员工列表查询出来(方式二 一对多关联查询 多条SQL)
	 */
	Department getDepartmentByDeptId$(Integer deptId);
	
}
```
```
public interface EmployeeMapper {

	/*
	 * 通过员工ID查询员工信息,同时查询出员工的部门信息( 方式一(单条SQL 关联查询) )
	 */
	Employee getEmployeeByEmpId(Integer empId);
	
	/*
	 * 通过员工ID查询员工信息,同时查询出员工的部门信息( 方式二(多条SQL查询) )
	 */
	Employee getEmployeeByEmpId_(Integer empId);
	
	/*
	 * 通过部门ID查询员工列表
	 */
	List<Employee> getEmployeesByDeptId(Integer deptId);
	
}
```

### 8.4 创建注册接口的XML配置文件

* 一对一(方式一)单条SQL关联查询
```
EmployeeMapper.xml

<!-- 一对一 方式一(单条SQL 关联查询) -->
<resultMap type="employee" id="employeeMap">
	<!-- 映射主键 -->
	<id property="empId" column="emp_id"/>
	<!-- 其他映射 -->
	<result property="empName" column="emp_name"/>
	<result property="salary" column="salary"/>
	<!-- 使用 association 进行一对一关联查询 -->
	<association property="department" javaType="department">
		<id property="deptId" column="dept_id"/>
		<result property="deptName" column="dept_name"/>
	</association>
</resultMap>
<select id="getEmployeeByEmpId" parameterType="Integer" resultMap="employeeMap">
	SELECT * FROM employee e,department d WHERE e.dept_id=d.dept_id AND e.emp_id=#{empId}
</select>

```
* 一对一(方式二)多条SQL分别查询
```
EmployeeMapper.xml

<!-- 一对一 方式二(多条SQL查询) -->
<resultMap type="employee" id="employeeMap_">
	<id property="empId" column="emp_id"/>
	<result property="empName" column="emp_name"/>
	<result property="salary" column="salary"/>
	<!-- 
		 当使用方式二时,association标签内有一个select属性,需要在此调用DepartmentMapper xml
		 配置文件中的statement进行二次查询
		 com.im.mapper.DepartmentMapper.getDepartmentByDeptId:DepartmentMapper.xml中的namespace(接口全类名)+id属性(方法名字)
	-->
	<association property="department" javaType="department" column="dept_id" select="com.im.mapper.DepartmentMapper.getDepartmentByDeptId"/>
</resultMap>
<select id="getEmployeeByEmpId_" parameterType="Integer" resultMap="employeeMap_">
	SELECT * FROM employee WHERE emp_id=#{empId}
</select>
```
```
DepartmentMapper.xml

<select id="getDepartmentByDeptId" parameterType="Integer" resultType="department">
	SELECT * FROM department WHERE dept_id=#{deptId}
</select>
```
* 一对多(方式一)单条SQL关联查询
```
DepartmentMapper.xml

<!-- 根据部门ID查询部门详情,需要将当前部门下的员工列表查询出来(方式一 一对多关联查询 单条SQL) -->
<resultMap type="department" id="departmentMap_">
	<id property="deptId" column="dept_id"/>
	<result property="deptName" column="dept_name"/>
	<!-- 一对多关联查询多的一方使用collection标签 ofType:集合中的泛型类型;property:多的一方属性名 -->
	<collection property="employees" ofType="employee">
		<id property="empId" column="emp_id"/>
		<result property="empName" column="emp_name"/>
		<result property="salary" column="salary"/>
	</collection>
</resultMap>
<select id="getDepartmentByDeptId_" parameterType="Integer" resultMap="departmentMap_">
	SELECT * FROM department d,employee e WHERE d.dept_id=e.dept_id AND d.dept_id=#{deptId}
</select>
```
* 一对多(方式二)多条SQL分别查询
```
DepartmentMapper.xml

<!-- 根据部门ID查询部门详情,需要将当前部门下的员工列表查询出来(方式二 一对多关联查询 多条SQL) -->
<resultMap type="department" id="departmentMap$">
	<id property="deptId" column="dept_id"/>
	<result property="deptName" column="dept_name"/>
	<!-- 一对多关联查询多的一方使用collection标签 ofType:集合中的泛型类型;property:多的一方属性名 column:关联关系的键 -->
	<collection property="employees" ofType="employee" column="dept_id" select="com.im.mapper.EmployeeMapper.getEmployeesByDeptId"/>
</resultMap>
<select id="getDepartmentByDeptId$" parameterType="Integer" resultMap="departmentMap$">
	SELECT * FROM department WHERE dept_id=#{deptId}
</select>
```
```
EmployeeMapper.xml

<select id="getEmployeesByDeptId" parameterType="Integer" resultType="employee">
	SELECT * FROM employee WHERE dept_id=#{deptId}
</select>
```
### 8.5 单元测试类
* EmployeeMapperTest.java
```
public class EmployeeMapperTest {

	/*
	 * 一对一 方式一(使用一条SQL语句关联查询)
	 */
	@Test
	public void testGetEmployeeByEmpId() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee employee = employeeMapper.getEmployeeByEmpId(1000);
		System.out.println(employee);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	/*
	 * 一对一 方式二(多条SQL分别查询)
	 */
	@Test
	public void testGetEmployeeByEmpId_() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee employee = employeeMapper.getEmployeeByEmpId_(1000);
		System.out.println(employee);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
}
```
* DepartmentMapperTest.java
```
public class DepartmentMapperTest {

	/*
	 * 一对多 方式一(使用一条SQL语句关联查询)
	 */
	@Test
	public void testGetDepartmentByDeptId_() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		Department department = mapper.getDepartmentByDeptId_(1000);
		System.out.println(department);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
	
	/*
	 * 一对多 方式二(多条SQL单独查询)
	 */
	@Test
	public void testGetDepartmentByDeptId$() throws IOException {
		//加载外部属性资源文件
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//创建SqlSession
		SqlSession session = sessionFactory.openSession();
		
		DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
		Department department = mapper.getDepartmentByDeptId$(1000);
		System.out.println(department);
		
		//提交
		session.commit();
		//关闭会话
		session.close();
	}
}
```
### 8.6 关联查询bug扩展
```
由于带有关联关系的多个POJO之间因为属性名相同问题而引起的映射数据出错的BUG处理以及解决方式
```
* 创建数据库表
```
CREATE TABLE tb_key(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(20) NOT NULL COMMENT '钥匙名称',
    `lock_id` INT NOT NULL COMMENT '锁ID关联锁表的主键'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="钥匙表";

CREATE TABLE tb_lock(
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(20) NOT NULL COMMENT '锁名称'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="锁表";
//关联
ALTER TABLE tb_key ADD FOREIGN KEY(lock_id) REFERENCES tb_lock(id);

INSERT INTO tb_key(name,lock_id) VALUES("铁牛钥匙",1000);
INSERT INTO tb_lock(name) VALUES('铁牛锁');
```
* 创建映射数据库表的实体类
```
/*
 * 锁类
 */
public class TbLock {

	private Integer id;
	private String name;

	public TbLock() {
	}
}
```
```
/*
 * 钥匙类
 */
public class TbKey {

	private Integer id;
	private String name;
	//一把钥匙只能开一把锁 一对一关联
	private TbLock tbLock;

	public TbKey() {
	}
}
```
* 创建操作数据库表的映射器接口
```
public interface TbKeyMapper {
	/*
	 * 通过钥匙ID钥匙信息以及锁的信息
	 */
	TbKey getKeyInfo(Integer keyId);
}
```
* 创建注册操作数据库表接口的xml配置文件
```
<mapper namespace="com.im.mapper.TbKeyMapper">
	<resultMap type="tbKey" id="tbKeyMap">
		<id property="id" column="id"/>
		<result property="name" column="name"/>
		<association property="tbLock" javaType="tbLock">
			<id property="id" column="id"/>
			<result property="name" column="name"/>
		</association>
	</resultMap>
	<select id="getKeyInfo" parameterType="Integer" resultMap="tbKeyMap">
		SELECT * FROM tb_key tk,tb_lock tl WHERE tk.lock_id=tl.id AND tk.id=#{keyId}
	</select>

</mapper>
```
* 编写测试类
```
@Test
public void testGetKeyInfo() throws IOException {
	//加载外部属性资源文件
	InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
	//创建SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
	//创建SqlSession
	SqlSession session = sessionFactory.openSession();
	
	TbKeyMapper keyMapper = session.getMapper(TbKeyMapper.class);
	TbKey keyInfo = keyMapper.getKeyInfo(1000);
	System.out.println(keyInfo);
	
	//提交
	session.commit();
	//关闭会话
	session.close();
}
```
* 结果分析
```
得出的结果,会发现映射出错,钥匙和锁信息都是相同的

TbKey [id=1000, name=铁牛钥匙, tbLock=TbLock [id=1000, name=铁牛钥匙]]

造成以上问题的原因是因为TbKey和TbLock两个JavaBean类中的属性名字相同造成的,由此可以看出 association 标签映射Java对象时采用的是通过名称映射的,在两个JavaBean属性名字相同时，在映射时候就会出现映射不清的问题。
```
* 解决方案: 采用多SQL联查的方式
```
<resultMap type="tbKey" id="tbKeyMap">
	<id property="id" column="id"/>
	<result property="name" column="name"/>
	<association property="tbLock" javaType="tbLock" column="lock_id" select="com.im.mapper.TbLockMapper.getLockInfo"/>
</resultMap>
<select id="getKeyInfo" parameterType="Integer" resultMap="tbKeyMap">
	SELECT * FROM tb_key WHERE id=#{keyId}
</select>
```
---
## 第九章 mybatis的懒加载

### 9.1 什么是懒加载
```
懒加载又称延时加载，就是在我们获取数据时，有些数据是不需要实时获取的，当需要时在获取，这样的加载数据的方式被称为懒加载
```
### 9.2 懒加载的使用
* 添加jar包如果是maven依赖,可以不加
```
cglib-2.2.2.jar
asm-3.3.1.jar
```
* 在mybatis全局配置文件中开启懒加载
```
关闭及时加载 aggressiveLazyLoading
开启懒加载 lazyLoadingEnabled
```
```
<settings>
	<!-- 设置经典Java命名规则和经典数据库命名规则的自动映射 -->
	<setting name="mapUnderscoreToCamelCase" value="true"/>
	<!-- 开启懒加载 -->
	<setting name="lazyLoadingEnabled" value="true"/>
	<!-- 关闭延时加载 -->
	<setting name="aggressiveLazyLoading" value="false"/>
</settings>
```
* 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态
```
1. 特定关联关系是指使用关联映射标签
<collection>或者<association>中的select
属性，而不是使用多表联查的方式.
2. 可以通过<collection>或者<association>的fetchType属性设置成lazy
```
---
## 第十章 mybatis的缓存

### 10.1 缓存的优缺点
```
优点: 减少读数据库的读操作,降低数据库压力,加快响应速度
缺点:可能造成数据不一致问题,增加成本
```
### 10.2 mybatis的一级缓存

* 一级缓存特点
```
1. 一级缓存默认是开启的
2. 一级缓存默认是SqlSession级别的
```
* 验证一级缓存
```
1. 在一个SqlSession下根据相同条件多次查询,发现日志只打印一条SQL,说明有缓存存在
2. 在多个SqlSession下,根据相同条件查询,打印多条SQL说明一级缓存是SqlSession级别的,创新创建SqlSession对象之后缓存失效
```
* 一级缓存失效方式
```
1. 使用sqlSession.clearCache()刷新缓存
2. 关闭SqlSession之后重新打开
3. SqlSession执行增删改之后会清空缓存
```
### 10.3 mybatis的二级缓存

* 二级缓存特点
```
1. 二级缓存需要手动配置
2. 二级缓存是SqlSessionFactory级别的
```
* 二级缓存的使用
```
①在myabtis-config.xml全局的开启二级缓存
<settings>
    <!-- 开启二级缓存 -->
    <setting name="cacheEnabled" value="true"/>
</settings>

②SQL map映射文件中添加<cache/>
<cache/>标签中属性的介绍:
<cache
    eviction="FIFO" 
    flushInterval="60000" 
    size="512" 
    readOnly="true"/>
    
eviction: 缓存的回收策略
—>LRU  – 最近最少使用的:移除最长时间不被使用的对象
—>FIFO – 先进先出: 按对象进入缓存的顺序来移除它们
—>SOFT – 软引用: 移除基于垃圾回收器状态和软引用规则的对象 
—>WEAK – 弱引用: 更积极地移除基于垃圾收集器状态和弱引用规则的对象

flushInterval: 刷新间隔,默认不刷新，可以设置任意的正整数,毫秒数

size: 引用数目->可以被设置为任意正整数,要记住你缓存的对象数目和你运行环境的可用内存资源数目,默认值是1024。

readOnly: 是否只读默认是false->只读的缓存会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。可读写的缓存会返回缓存对象的拷贝(映射数据库的实体类必须实现序列化) 。这会慢一些,但是安全,因此默认是 false
```
* 二级缓存设置成功的标志
```
日志中会出现缓存命中率，并且在每一次查询结束之后都要提交事务,否则二级缓存不会起作用,缓存命中率不会发生变化.

Cache Hit Ratio [com.im.mapper.UserMapper]: 0.0
```
### 10.4 集成第三方缓存ehcache
```
使用二级缓存产品ehcache步骤
```
* 添加jar包或者依赖
```
ehcache-2.10.4.jar
mybatis-ehcache-1.0.0.jar
```
* 添加ehcache产品配置文件ehcache.xml
* 配置Ehcache
```
在SQL map 文件中配置<cache/>标签

 <cache type="org.mybatis.caches.ehcache.EhcacheCache">
 	<!-- 缓存创建以后，最后一次访问缓存，间隔多久缓存失效(单位s) -->
 	<property name="timeToIdleSeconds" value="3600"/>
 	<!-- 缓存创建日期起到失效时的时间间隔(单位s) -->
 	<property name="timeToLiveSeconds" value="3600"/>
 	<!-- 堆内存中缓存对象数(0:没有限制) -->
 	<property name="maxEntriesLocalHeap" value="1000"/>
 	<!-- 磁盘中的对象数,默认为0不限制 -->
 	<property name="maxEntriesLocalDisk" value="1000000"/>
 	<!-- 内存存储与释放策略(FIFO:先进先出,LFU:一直以来最少被使用的,LRU:最近最少使用的) -->
 	<property name="memoryStoreEvictionPolicy" value="LRU"/>
 </cache>
```
## 第十一章 MYSQL分页

### 11.1 分页准备
* 操作数据库的接口代码
```
/**
 * 操作数据库表的接口
 */
public interface UserMapper {
	/*
	 * 原生SQL分页
	 * @Param:方法入参为多个时要使用此注解指定入参,否则mybatis表达式(#{xxx})会找不到方法入参
	 */
	List<User> getUserPage(@Param("offset")int offset,@Param("pageSize")int pageSize);
	/*
	 * 分页插件分页
	 */
	List<User> getUserPage_();
}
```
* 注册接口的xml配置文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.im.mapper.UserMapper">
	<!-- 原生分页 -->
	<select id="getUserPage" resultType="user">
		SELECT * FROM user LIMIT #{offset},#{pageSize}
	</select>
	<!-- 插件分页,pageHelper插件分页时,方法不需要传任何入参 -->
	<select id="getUserPage_" resultType="user">
		SELECT * FROM user
	</select>
</mapper>
```
### 11.2 原生分页
```
/*
 * 原生分页
 */
@Test
public void testGetUserPage() throws IOException {
	//加载外部属性资源文件
	InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
	//创建SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
	//创建SqlSession
	SqlSession session = sessionFactory.openSession();
	
	UserMapper mapper = session.getMapper(UserMapper.class);
	int pageNo=3;//当前页
	int pageSize=2;//每页显示的条数
	//mysql分页的第一个参数,转换公式
	int offset=(pageNo-1)*pageSize;
	List<User> page = mapper.getUserPage(offset, pageSize);
	System.out.println(page);
	//提交
	session.commit();
	//关闭会话
	session.close();
}
```

### 11.3 分页插件(PageHelper)
* 添加jar包或者依赖
```
jsqlparser-0.9.5.jar
pagehelper-5.1.2.jar
```
* 在mybatis-config.xml中配置PageHelper
```
<!-- 配置分页插件pageHelper -->
<plugins>
	<plugin interceptor="com.github.pagehelper.PageInterceptor">
		<!--
			分页合理化参数，默认值为false。当该参数设置为 true 时， 
			pageNum<=0 时会查询第一页， pageNum>pages(超过总数时)， 
			会查询最后一页。默认false 时，直接根据参数进行查询
		-->
		<property name="reasonable" value="true"/>
		<!-- 支持合法的参数化,设置这个属性以及下面的属性,可以设置方法的入参 -->
		<property name="supportMethodsArguments" value="true"/>
		<!-- 参数化的key值 -->
		<property name="params" value="pageNum=pageNumKey;pageSize=pageSizeKey;"/>
	</plugin>
</plugins>
```
```
    public void getTbKeyList(){
        PageHelper.startPage(2,2);
        List<TbKey> tbKeyList = tbKeyMapper.getTbKeyList();
        for (TbKey tbKey : tbKeyList) {
            System.out.println(tbKey);
        }

        PageInfo pageInfo = new PageInfo(tbKeyList);
        //pageInfo.getPages();等等get方法,能获取我们想要的前端想要的所有数据
    }
```

* Junit单元测试
```
/*
 * pageHelper分页
 */
@Test
public void testGetUserPage_() throws IOException {
	//加载外部属性资源文件
	InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
	//创建SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
	//创建SqlSession
	SqlSession session = sessionFactory.openSession();
	UserMapper mapper = session.getMapper(UserMapper.class);
	//在查询全部数据之前直接调用PageHelper.startPage(2, 2);方法进行分页
	//注意不能再两个方法之间插入其他的代码方法入参为第一个:pageNo；第二个:pageSize
	PageHelper.startPage(2, 2);
	List<User> page_ = mapper.getUserPage_();
	for (User user : page_) {
		System.out.println(user);
	}
	
	//提交
	session.commit();
	//关闭会话
	session.close();
}
```
---
## 第十二章 mybatis和spring框架的整合
```
spring + mybatis 框架整合
整合步骤:
```
* 创建Java普通项目(因为没有页面所以不需要web项目)
* 添加jar包目或者maven依赖
```
commons-logging-1.2.jar
druid-1.1.6.jar
jsqlparser-0.9.5.jar
log4j-1.2.17.jar
mybatis-3.4.6.jar
mybatis-spring-1.3.1.jar
mysql-connector-java-5.1.7-bin.jar
pagehelper-5.1.2.jar
slf4j-api-1.7.25.jar
slf4j-log4j12-1.7.25.jar
spring-aop-4.3.10.RELEASE.jar
spring-aspects-4.3.10.RELEASE.jar
spring-beans-4.3.10.RELEASE.jar
spring-context-4.3.10.RELEASE.jar
spring-context-support-4.3.10.RELEASE.jar
spring-core-4.3.10.RELEASE.jar
spring-expression-4.3.10.RELEASE.jar
spring-jdbc-4.3.10.RELEASE.jar
spring-orm-4.3.10.RELEASE.jar
spring-oxm-4.3.10.RELEASE.jar
spring-test-4.3.10.RELEASE.jar
spring-tx-4.3.10.RELEASE.jar
```
* 创建jdbc.properties配置文件
```
jdbc_user=root
jdbc_password=root
jdbc_url=jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8
```
* 创建mybatis-config.xml配置⽂件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<settings>
		<!-- 开启二级缓存 -->
		<setting name="cacheEnabled" value="true"/>
		<!-- 开启驼峰命名 -->
		<setting name="mapUnderscoreToCamelCase" value="true"/>
	</settings>
	<!-- 配置分页插件pageHelper -->
	<plugins>
		<plugin interceptor="com.github.pagehelper.PageInterceptor">
			<!--
				分页合理化参数，默认值为false。当该参数设置为 true 时， 
				pageNum<=0 时会查询第一页， pageNum>pages(超过总数时)， 
				会查询最后一页。默认false 时，直接根据参数进行查询
			-->
			<property name="reasonable" value="true"/>
		</plugin>
	</plugins>
</configuration>
```
* 创建applicationContext.xml配置文件
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">

	<!-- 1.导入外部属性资源文件 -->
	<context:property-placeholder location="classpath:jdbc.properties" />
	<!-- 2.扫描需要扫描的包 -->
	<context:component-scan base-package="com.sc" />
	<!-- 3.配置数据库连接池 -->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
		<!-- 基本属性 url、user、password -->
		<property name="url" value="${jdbc_url}" />
		<property name="username" value="${jdbc_user}" />
		<property name="password" value="${jdbc_password}" />
		<!-- 配置初始化大小、最小、最大 -->
		<property name="initialSize" value="1" />
		<property name="minIdle" value="1" />
		<property name="maxActive" value="20" />
		<!-- 配置获取连接等待超时的时间 -->
		<property name="maxWait" value="60000" />
		<!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
		<property name="timeBetweenEvictionRunsMillis" value="60000" />
		<!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
		<property name="minEvictableIdleTimeMillis" value="300000" />
		<property name="validationQuery" value="SELECT 'x'" />
		<property name="testWhileIdle" value="true" />
		<property name="testOnBorrow" value="false" />
		<property name="testOnReturn" value="false" />
		<!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
		<property name="poolPreparedStatements" value="false" />
		<property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
		<!-- 配置监控统计拦截的filters -->
		<property name="filters" value="stat" />
	</bean>
	<!-- 4.配置SqlSessionFactory -->
	<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="typeAliasesPackage" value="com.sc.entity" />
		<property name="configLocation" value="classpath:mybatis-config.xml" />
	</bean>
	<!-- 5.配置平台事务管理理器器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
	<!-- 6.开启基于注解的声明式事务 -->
	<tx:annotation-driven transaction-manager="transactionManager" />
		<!-- 7.注册mybatis接⼝口加⼊入到Spring IOC容器器中 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
		<property name="basePackage" value="com.sc.mapper" />
		<property name="annotationClass" value="org.springframework.stereotype.Repository" />
	</bean>
</beans>
```
* 添加log4j.xml配置文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration
	xmlns:log4j="http://jakarta.apache.org/log4j/">
	<appender name="STDOUT"
		class="org.apache.log4j.ConsoleAppender">
		<param name="Encoding" value="UTF-8" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern"
				value="%-5p %d{MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n" />
		</layout>
	</appender>
	<logger name="java.sql">
		<level value="debug" />
	</logger>
	<logger name="org.apache.ibatis">
		<level value="debug" />
	</logger>
	<root>
		<level value="debug" />
		<appender-ref ref="STDOUT" />
	</root>
</log4j:configuration>
```
---
## 第十三章 spring框架的单元测试
```
spring和mybatis框架整合完成之后，需要测试整合是否成功,接下来需要进行测试,测试采用spring框架的spring-tx-4.3.10.RELEASE.jar 依赖进行单元测试.
```
* 使用mybatis上面的例子进行测试
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test01() {
		List<User> users = userMapper.getUsers();
		System.out.println(users);
	}
}
```
---
## 第十四章 mybatis映射MYSQL5.7新增的JSON数据类型

### 14.1 mybatis 5.7 json数据类型介绍
```
MYSQL5.7的GA版本开始支持json格式的数据
json格式有两种一种单个对象{};另一种就是集合格式[]
```
### 14.2 Java对象和json数据的互通
```
看看mybatis是怎么实现将Java的对象保存到数据库中的json数据格式,又怎么将数据库中的json数据格式查询出来保存成Java对象
```
* 创建数据库表
```
CREATE TABLE tb_order(
    `order_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `users` json NOT NULL,
    `user` json NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8;
```
* 创建映射数据库表的Java实体类
```
//测试数据库两种数据类型
public class Order {

	private Integer orderId;
    //[{"id":1001,"name":"李雷"},{"id":1001,"name":"李雷"}]
	private List<User> users;
    //{"id":1001,"name":"李雷"}
	private User user;
    
}

public class User {
	// 用户ID
	private Integer userId;
	// 用户名
	private String userName;
    
}
```
* 创建自定义类型转换器进行JSON和POJO的转换
* * 集合类型处理器(处理集合数据) JsonArrayTypeHandler.java
```
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.im.entity.User;
/*
 * 数组类型的相互转换
 * JSON转换使用的是阿里巴巴的fastjson
 */
public class JsonArrayTypeHandler extends BaseTypeHandler<List<User>> {

	/*
	 * 使用列名获取数据库中保存的json数据
	 * 并将其数据类型转换成List集合
	 */
	@Override
	public List<User> getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		String string = arg0.getString(arg1);
		return JSON.parseObject(string, List.class);
	}
	/**
	 * 使用索引获取数据库中保存的json数据
	 * 并将其数据类型转换成List集合
	 */
	@Override
	public List<User> getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		String string = arg0.getString(arg1);
		return JSON.parseObject(string, List.class);
	}

	/**
	 * 使用索引获取数据库中保存的json数据
	 * 并将其数据类型转换成List集合
	 */
	@Override
	public List<User> getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		String string = arg0.getString(arg1);
		return JSON.parseObject(string,List.class);
	}

	/**
	 * 将集合的实体对象转换成JSON保存到数据库中
	 */
	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, List<User> arg2, JdbcType arg3)
			throws SQLException {
		arg0.setString(arg1, JSON.toJSONString(arg2));
	}
}
```
* * 单个对象处理器(处理单个对象) JsonTypeHandler.java
```
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.im.entity.User;
/**
 * 自定义类型处理器
 * @author hushuang
 * json转换使用阿里巴巴的fastjson
 */
public class JsonTypeHandler extends BaseTypeHandler<User> {

	@Override
	public User getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		return JSON.parseObject(arg0.getString(arg1), User.class);
	}

	@Override
	public User getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		return JSON.parseObject(arg0.getString(arg1), User.class);
	}

	@Override
	public User getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		return JSON.parseObject(arg0.getString(arg1), User.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, User arg2, JdbcType arg3) throws SQLException {
		arg0.setString(arg1, JSON.toJSONString(arg2));
	}

}
```
* * 注册自定义类型转换器
```
在mybatis-config.xml配置文件中注册

<typeHandlers>
	<typeHandler handler="com.im.handler.JsonTypeHandler" />
	<typeHandler handler="com.im.handler.JsonArrayTypeHandler" />
</typeHandlers>
```
* 创建注解操作接口的xml配置文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.im.mapper.OrderMapper">
	<!-- 测试插入 -->
	<insert id="addOrder" parameterType="order">
		INSERT INTO tb_order(users,user) 
		VALUES(#{users,typeHandler=com.im.handler.JsonArrayTypeHandler},#{user,typeHandler=com.im.handler.JsonTypeHandler})
	</insert>
	<!-- 测试查询 -->
	<resultMap type="order" id="orderMap">
		<id property="orderId" column="order_id"/>
		<result property="users" column="users" typeHandler="com.im.handler.JsonArrayTypeHandler"/>
		<result property="user" column="user" typeHandler="com.im.handler.JsonTypeHandler"/>
	</resultMap>
	<select id="getOrder" resultMap="orderMap">
		SELECT * FROM tb_order WHERE order_id=#{id}
	</select>
</mapper>
```
* 单元测试(略)

---
## 第十五章 相关文档
```
mybatis中文官网地址:https://mybatis.org/mybatis-3/zh/index.html
Druid中文官网地址:https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
pageHelper官网地址:https://pagehelper.github.io/docs/howtouse/
ehcache 官网地址:http://mybatis.org/ehcache-cache/
```
---