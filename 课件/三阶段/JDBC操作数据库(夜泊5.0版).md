

## 第一章 JDBC的简介

### 1.1 JDBC是什么
```
1、JDBC (Java DataBase Connectivity) 是一组用于操作数据库的Java API
2、JDBC 由一组Java类和接口组成
3、可以为多种关系型数据库提供统一访问
```
### 1.2 JDBC原理图

<center>

<img src='https://note.youdao.com/yws/api/personal/file/WEBb977189bf473acd2106e4411cde86ae1?method=download&shareKey=e0d91763520b4c25ab021011bc061d91'/>

</center>

### 1.3 JDBC提供的常用接口和类
```
1. DriverManager: 管理数据库驱动程序,用于建立数据库连接

2. Driver:        提供给各个数据库厂商的接口，每一个数据库厂商要想可以使用Java语言来与他们的数据库进行通信,必须实现此接口.(主要处理与数据库服务器之间的通信)
                   
3. Connection:    此接口具有用于联系数据库的所有方法

4. Statement:     从此接口创建的对象将SQL语句提交到数据库

5. ResultSet:     在使用Statement对象执行SQL查询后，这个对象保存从数据库检索的数据

6. SQLException: 处理数据库应用程序中发生的异常
```

---
## 第二章 JDBC入门

> 以一个用户表为例来使用JDBC进行增删该查的操作

* 数据库环境搭建
```
// 1)、创建数据库
CREATE DATABASE jdbc DEFAULT CHARACTER SET UTF8;
// 2)、切换数据库
USE jdbc;
// 3)、创建数据库表
CREATE TABLE user(
    `user_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `user_name` VARCHAR(20) NOT NULL COMMENT '用户名',
    `price` double(10,2) DEFAULT 0.0 COMMENT '价格',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT="用户表";
```
* 添加用户
```
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建添加SQL语句
String sql="INSERT INTO user(user_name,price,create_time) VALUES('admin',100.12,now())";
//⑤执行sql语句,返回影响行数,如果需要获取主键,要配置Statement.RETURN_GENERATED_KEYS参数
int row = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
//⑥打印影响行数
System.out.println("row:"+row);

//如果想获取插入用户的主键可以使用statement.getGeneratedKeys()获取

ResultSet resultSet=statement.getGeneratedKeys();
if(resultSet.next()){
    int id = resultSet.getInt(1);
}

```
* 更新用户
```
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建更新SQL语句
String sql="UPDATE user SET user_name='admin1',price=12.12,create_time=now() WHERE user_id=100";
//⑤执行sql语句,返回影响行数
int row = statement.executeUpdate(sql);
//⑥打印影响行数
System.out.println("row:"+row);
```
* 删除用户
```
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建更新SQL语句
String sql="DELETE FROM user WHERE user_id=100";
//⑤执行sql语句,返回影响行数
int row = statement.executeUpdate(sql);
//⑥打印影响行数
System.out.println("row:"+row);
```
* 查询用户列表
```
//创建一个集合对象,将从数据库查询出来的数据保存到集合中
List<User> users = new ArrayList<>();
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建SQL语句
String sql="SELECT * FROM user";
//⑤执行sql语句,返回结果集对象
ResultSet result = statement.executeQuery(sql);
while(result.next()){
	User user = new User();
	int userId = result.getInt("user_id");
	user.setUserId(userId);
	String userName = result.getString("user_name");
	user.setUserName(userName);
	double price = result.getDouble("price");
	user.setPrice(price);
	Date createTime = result.getDate("create_time");
	user.setCreateTime(createTime);
	users.add(user);
}
//打印从数据库查询出来的对象
System.out.println(users);
```
* 查询一个用户(因为JDBC没有提供一个查询单个对象的结果集,所以查询单个对象时也是使用ResultSet对象)
```
//创建一个集合对象,将从数据库查询出来的数据保存到集合中
List<User> users = new ArrayList<>();
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建SQL语句
String sql="SELECT * FROM user";
//⑤执行sql语句,返回结果集对象
ResultSet result = statement.executeQuery(sql);
if(result.next()){
	User user = new User();
	int userId = result.getInt("user_id");
	user.setUserId(userId);
	String userName = result.getString("user_name");
	user.setUserName(userName);
	double price = result.getDouble("price");
	user.setPrice(price);
	Date createTime = result.getDate("create_time");
	user.setCreateTime(createTime);
	users.add(user);
}
//打印从数据库查询出来的对象
System.out.println(users);
```
* 查询数据库总记录数
```
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③创建Statement对象
Statement statement = conn.createStatement();
//④创建SQL语句
String sql="SELECT COUNT(*) AS count FROM user";
//⑤执行sql语句,返回结果集对象
ResultSet result = statement.executeQuery(sql);
if(result.next()){
	int count = result.getInt("count");
	System.out.println(count);
}
```
* 分页查询
```
MYSQL数据库分页的语法
SELECT * FROM user LIMIT [offset],[max];
```
```
MYSQL的SQL语句的语法格式带有两个参数分别是offset和max,而我们前端或者是客户端给后台发送过来的数据为pageNo(当前页)和pageSize(每页显示多少条数),而SQL语句里面的max和我们前端传送过来的pageSize是相同的都是为每页显示的条数。但是offset和pageNo不是相同的所以需要转换。
```
```
转换的公式:
offset=(pageNo-1)*pageSize
```
* 中文乱码
```
url：jdbc:mysql:///jdbc?characterEncoding=utf-8&useUnicode=true
```
---
## 第三章 PreparedStatement接口
```
1. 它是扩展了Statement的接口(PreparedStatement本身也是接口)
2. 功能比Statement更强大
3. 可以动态地提供/接受参数
4. 创建PreparedStatement对象 --> conn.prepareStatement(通过Connection对象获取)
5. 可以使用占位符(?)来进行数据绑定
6. 通过stmt.setInt(1, 35);绑定参数
```
* 添加用户
```
//①加载数据库驱动
Class.forName("com.mysql.jdbc.Driver");
//②创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8","root","root");
//③准备sql，带有（?）占位符
String sql="INSERT INTO user(user_name,price,create_time) VALUES(?,?,?)";
//④预编译
PreparedStatement prepareStatement = conn.prepareStatement(sql);
//⑤绑定参数(参数的下标从1开始而不是像数组或者集合从0开始)
prepareStatement.setString(1, "admin");
prepareStatement.setDouble(2, 100.12);
prepareStatement.setDate(3, new Date(new java.util.Date().getTime()));
//⑥执行进行绑定参数之后的sql语句
int row = prepareStatement.executeUpdate();
System.out.println("row:"+row);
```
* 其余(删改查)操作自己完成

---
## 第四章 JDBC的事务处理

### 第1节 数据库事务简介
* 什么是事务?
```
事务是逻辑上的一组操作,这组操作要么全部成功，有一个失败就全部失败
```
* 事务的特性?
```
1. 原子性
2. 一致性
3. 隔离性
4. 永久性
```
* 事务的隔离级别?
```
1. 读已提交 -> READ-COMMITTED    -> 防止脏读
2. 读未提交 -> READ-UNCOMMITTED  -> 不能保证数据安全
3. 可重复读 -> REPEATABLE-READ   -> 可以防止脏读和不可重复读
4. 串行化   -> SERIALIZABLE      -> 防止脏读、幻读、不可重复读,但是性能较差
```
* 没有隔离级别产生的问题
* * 脏读
```
一个事物读取的数据是另一个事物还没有来得及提交的数据，会造成脏读的问题
```
* * 幻读
```
同一事务中，用同样的操作读取两次，得到的记录数不相同
```
* * 不可重复读
```
在同一事务中，两次读取同一数据，得到内容不同
```

* MYSQL的事务操作
```
1. mysql默认的隔离级别 : REPEATABLE-READ
2. oracle默认的隔离级别 : READ-COMMITTED
3. 查询当前mysql事务 : select @@tx_isolation;
4. 修改当前mysql事务 : set tx_isolation='REPEATABLE-READ';
```

### 第2节 JDBC对事务的操作

```
jdbc默认情况下每执行完一条SQL就会提交到数据库一次,因为jdbc默认提交事务的方式是自动提交,但是在正常的业务逻辑下,一个业务正常要执行多条sql语句,如果每执行完一条sql都进行事务提交就很容易出现前后数据不一致的问题,所以要保证这一组的sql语句操作都在一个事务管理中,所以我们要取消JDBC的默认事务提交
```
```
1. 使用Connection对象的setAutoCommit()方法关闭他的自动提交
2. 手动提交事务调用connection的commit()方法
3. 事务回滚调用connection的rollback()方法
```
#### 2.1 JDBC事务代码测试

* 业务介绍
```
转账 : z3给l4转500块钱
```
* 数据库表创建
```
-- 账户表
CREATE TABLE account(
	account_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT "主键",
	account_name VARCHAR(20) NOT NULL COMMENT "账户名",
	money double(10,2) DEFAULT 0.0 COMMENT "账户金额"
)ENGINE=InnoDB AUTO_INCREMENT=10000  DEFAULT CHARSET=UTF8;

-- 添加数据
INSERT INTO account(account_name,money) VALUES("z3",1000),("l4",1000);
```
* Java代码
```
@Test
public void test02()  {
	Connection connection = null;
	//执行
	int row1=0;
	int row2=0;
	try {
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取数据库连接
		connection = DriverManager.getConnection("jdbc:mysql:///jdbc?characterEncoding=utf-8&useUnicode=true", "root", "root");
		
		//取消默认提交
		connection.setAutoCommit(false);
		
		//创建Statement
		Statement statement = connection.createStatement();
		//准备sql语句
		String sql1="UPDATE account SET money=money-500 WHERE account_name='z3'";
		String sql2="UPDATE account SET money=money+500 WHERE account_name='l4'";
		
		row1 = statement.executeUpdate(sql1);
		//异常
		int i=1/0;
		row2 = statement.executeUpdate(sql2);
		System.out.println(row1);
		System.out.println(row2);
		//手动提交
		connection.commit();
		
	} catch (ClassNotFoundException | SQLException e) {
		//事务回滚
		if(null!=connection) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		e.printStackTrace();
	}
	
}
```

---
## 第五章 JDBC批量处理

> 使用JDBC进行批处理的对象有两个:
>> 1. Statement  
>> 2. PreparedStatement

* Statement
* * 介绍
```
①使用Statement对象批量添加要执行的SQL语句,事例如下:

  statement.addBatch(sql1);
  statement.addBatch(sql2);
  statement.addBatch(sql3);
  statement.addBatch(sql4);
②执行批处理SQL语句：statement.executeBatch();
③清除批处理命令：statement.clearBatch();
```
* * 代码
```
Statement statement = connection.createStatement();
//批量准备SQL
String sql1="INSERT INTO user(user_name,price,create_time) VALUES('AA',12.12,NOW())";
String sql2="INSERT INTO user(user_name,price,create_time) VALUES('BB',13.12,NOW())";
String sql3="INSERT INTO user(user_name,price,create_time) VALUES('CC',14.12,NOW())";
String sql4="DELETE FROM user WHERE user_id=100";
//批量添加SQL
statement.addBatch(sql1);
statement.addBatch(sql2);
statement.addBatch(sql3);
statement.addBatch(sql4);
//批量执行
statement.executeBatch();
```
* PreparedStatement
* * 代码
```
String sql="INSERT INTO user(user_name,price,create_time) VALUES(?,?,?)";
PreparedStatement prepareStatement = connection.prepareStatement(sql);
//绑定参数1
prepareStatement.setString(1, "AA");
prepareStatement.setDouble(2, 12.12);
prepareStatement.setDate(2, new Date(new java.util.Date().getTime()));
prepareStatement.addBatch();
//绑定参数2
prepareStatement.setString(1, "BB");
prepareStatement.setDouble(2, 12.12);
prepareStatement.setDate(2, new Date(new java.util.Date().getTime()));
prepareStatement.addBatch();
//执行
prepareStatement.executeBatch();
```
* Statement和PrepareStatement的优缺点:
```
1.Statement可以添加不同的SQL语句INSERT UPDATE DELETE可以同时进行批处理,但是效率相对较差

2.PrepareStatement执行同一条SQL不同参数的SQL语句，但是效率相对较高
```
---
## 第六章 JDBC存储大字段到MYSQL(了解)

### 6.1 大字段介绍
```
对于字段长度要求超过 255 个的情况下，MySQL 提供了 TEXT 和 BLOB 两种类型。根据存储数据的大小，它们都有不同的子类型。这些大型的数据用于存储文本块或图像、声音文件等二进制数据类型
```
### 6.2 大字段类型
```
4种文本: TINYTEXT(0-255字节)、TEXT(0-65535字节)、MEDIUMTEXT(0-16777215字节) 和 LONGTEXT(0-4294967295字节)
4种二进制: TINYBLOB(0-255字节)、BLOB(0-65535字节)、MEDIUMBLOB(0-16777215字节) 和 LONGBLOB(0-4294967295字节)
```
### 6.3 具体使用图示
<center>
<img src="https://note.youdao.com/yws/api/personal/file/WEBd10f2cc79dc0dccf7136dd84b3f0056c?method=download&shareKey=e4e9330886254cabda982c96082ea2c6">

</center>

### 6.4 大字段的操作

* 创建保存大字段的表
```
CREATE TABLE big_data(
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	`image` MEDIUMBLOB  COMMENT '图片',
	`article` TEXT NOT NULL COMMENT '大字段文本'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```
* 存储图片/文本的例子
```
/*
 * 保存大字段数据
 */
@Test
public void test01() throws ClassNotFoundException, SQLException, IOException {
	//加载数据库驱动
	Class.forName("com.mysql.jdbc.Driver");
	//获取数据库连接
	Connection conn = DriverManager.getConnection("jdbc:mysql:///bigdata?characterEncoding=utf8", "root", "root");
	//准备sql语句
	String sql="INSERT INTO user(image,article) VALUES(?,?)";
	//获取preparedStatement对象
	PreparedStatement ps = conn.prepareStatement(sql);
	//绑定参数
	//获取二进制流
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mysql.jpg");
	ps.setBinaryStream(1, inputStream, inputStream.available());
	//获取文本流
	File file = new File("mysql.sql");
	FileReader reader = new FileReader(file);
	ps.setCharacterStream(2, reader, file.length());
	ps.executeUpdate();
	ps.close();
	conn.close();
}
```
* 读取图片/文本的例子
```
/*
 * 查询大字段数据
 */
@Test
public void test02() throws SQLException, ClassNotFoundException, IOException {
	//加载数据库驱动
	Class.forName("com.mysql.jdbc.Driver");
	//获取数据库连接
	Connection conn = DriverManager.getConnection("jdbc:mysql:///bigdata?characterEncoding=utf8", "root", "root");
	//准备sql语句
	String sql="SELECT id,image,article FROM user WHERE id=?";
	//获取preparedStatement对象
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, 1);
	ResultSet resultSet = ps.executeQuery();
	if(resultSet.next()) {
		InputStream binaryStream = resultSet.getBinaryStream("image");
		Reader characterStream = resultSet.getCharacterStream("article");
		FileOutputStream outputStream = new FileOutputStream(new File("1.jpg"));
		byte[] b = new byte[1024];
		int len_=0;
		while((len_=binaryStream.read(b))!=-1) {
			outputStream.write(b, 0, len_);
		}
		System.out.println("=============");
		char[] c = new char[512];
		int len=0;
		while((len=characterStream.read(c))!=-1) {
			System.out.println(new String(c, 0,len));
		}
	}
}
```

---
## 第七章 封装JDBC工具类

* 封装获取和关闭数据库连接的工具类
```
①获取数据库连接
    --加载数据库驱动
    --获取数据库连接
②关闭数据库连接
    --关闭ResultSet
    --关闭PreparedStatement
    --关闭Connection
```
* * 代码(v1.0版本)
```
public class JdbcUtils {
	
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql:///oa";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	/**
	 * 获取数据库连接
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		//加载数据库驱动
		Class.forName(DRIVER_CLASS);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	/**
	 * 关闭数据库连接
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param ps
	 */
	public static void close(Connection conn, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn, Statement statement, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param ps
	 */
	public static void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		System.out.println(connection);
	}
}
```
* * 代码(v2.0版本)
```
db.properties

driverClass=com.mysql.jdbc.Driver
jdbcUrl=jdbc:mysql://localhost:3306/jdbc?characterEncoding=utf-8
jdbcUser=root
jdbcPassword=root
```
```
public class JdbcUtils {
	private static Properties properties = null;
	static {
		//通过类加载器
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
		properties = new Properties();
		try {
			properties.load(in);
			Class.forName(properties.getProperty("driverClass"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取数据库连接
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(properties.getProperty("jdbcUrl"), properties.getProperty("jdbcUser"), properties.getProperty("jdbcPassword"));
	}
	/**
	 * 关闭连接
	 */
	public static void close(Connection conn,Statement statement,ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection conn,Statement statement) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection conn,PreparedStatement ps) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
```
---
* 封装操作数据库Dao层的通用类
```
/**
 * 通用数据库操作类
 */
public class BaseDao<T> {
	/**
	 * 通用的增删改
	 */
	public void update(Connection conn,String sql,Object ...args) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		//赋值
		if(args!=null && args.length>0) {
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
		}
		ps.executeUpdate();
	}
	
	
	
	/**
	 * 查询列表
	 * 原生反射
	 */
	public List<T> getList(Connection connection,String sql,Class<T> clazz,Object ...args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<T> list = new ArrayList<>();
		T t = null;
		//获取PreparedStatement
		PreparedStatement ps = connection.prepareStatement(sql);
		//赋值
		if(args!=null && args.length>0) {
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
		}
		//执行
		ResultSet rs = ps.executeQuery();
		//获取数据库表的元数据信息
		ResultSetMetaData rsmd = rs.getMetaData();
		//获取数据库表的列总数
		int columnCount = rsmd.getColumnCount();
		while(rs.next()) {
			//实例化对象
			t=clazz.newInstance();
			//循环获取列名
			for(int i=0;i<columnCount;i++) {
				//通过列的下标获取列名
				String columnLabel = rsmd.getColumnLabel(i+1);
				//通过列标签获取列值
				Object columnValue = rs.getObject(columnLabel);
				//反射通过名称获取java对象中描述属性的对象
				Field field = clazz.getDeclaredField(columnLabel);//dept_id as deptId 
				//取消私有权限
				field.setAccessible(true);
				//给属性对象赋值
				field.set(t, columnValue);
			}
			list.add(t);
		}
		return list;
	}
	
	/**
	 * 通用查询(单个)
	 * 原生反射
	 */
	public T getT(Connection connection,String sql,Class<T> clazz,Object ...args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		T t = null;
		//获取PreparedStatement
		PreparedStatement ps = connection.prepareStatement(sql);
		//赋值
		if(args!=null && args.length>0) {
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
		}
		//执行
		ResultSet rs = ps.executeQuery();
		//获取数据库表的元数据信息
		ResultSetMetaData rsmd = rs.getMetaData();
		//获取数据库表的列总数
		int columnCount = rsmd.getColumnCount();
		if(rs.next()) {
			//实例化对象
			t=clazz.newInstance();
			//循环获取列名
			for(int i=0;i<columnCount;i++) {
				//通过列的下标获取列名
				String columnLabel = rsmd.getColumnLabel(i+1);
				//通过列标签获取列值
				Object columnValue = rs.getObject(columnLabel);
				//反射通过名称获取java对象中描述属性的对象
				Field field = clazz.getDeclaredField(columnLabel);//dept_id as deptId 
				//取消私有权限
				field.setAccessible(true);
				//给属性对象赋值
				field.set(t, columnValue);
			}
		}
		return t;
	}
	
	/**
	 * 通用查询(单个)
	 * 使用下面工具进行反射赋值
	 * commons-beanutils-1.8.0.jar
	 * commons-logging-1.1.1.jar
	 */
	public T getT_(Connection connection,String sql,Class<T> clazz,Object ...args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		T t = null;
		//获取PreparedStatement
		PreparedStatement ps = connection.prepareStatement(sql);
		//赋值
		if(args!=null && args.length>0) {
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
		}
		//执行
		ResultSet rs = ps.executeQuery();
		//获取数据库表的元数据信息
		ResultSetMetaData rsmd = rs.getMetaData();
		//获取数据库表的列总数
		int columnCount = rsmd.getColumnCount();
		if(rs.next()) {
			//实例化对象
			t=clazz.newInstance();
			//循环获取列名
			for(int i=0;i<columnCount;i++) {
				//通过列的下标获取列名
				String columnLabel = rsmd.getColumnLabel(i+1);
				//通过列标签获取列值
				Object columnValue = rs.getObject(columnLabel);
				//反射通过名称获取java对象中描述属性的对象
				//Field field = clazz.getDeclaredField(columnLabel);//dept_id as deptId 
				//取消私有权限
				//field.setAccessible(true);
				//给属性对象赋值
				//field.set(t, columnValue);
				PropertyUtils.setProperty(t, columnLabel, columnValue);
			}
		}
		return t;
	}
}

```

---
## 第八章 数据库连接池
### 8.1 连接池介绍
```
数据库连接池的概念：负责分配、管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个连接来操作数据库.
```
### 8.2 常见连接池
* C3P0:比较古老的数据库连接池
```
官网地址: https://www.mchange.com/projects/c3p0/

C3P0提供了多种创建数据库连接的方式,根据官网任选其一即可
```
* Druid:alibaba的基于Java的高效的数据库连接池
```
官网地址: https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
```
### 8.3 C3P0连接池使用(API调用)
```
ComboPooledDataSource pool = new ComboPooledDataSource();
pool.setDriverClass("com.mysql.jdbc.Driver");
pool.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc");
pool.setUser("root");
pool.setPassword("root");
```
### 8.4 Druid[ˈdruːɪd]连接池使用(API调用)
* Druid的API介绍
```
1. DruidDataSource : 创建数据库连接池的核心类(通过new对象创建Druid的数据库连接池)
2. getConnection   : 获取数据库连接池的方法(通过DruidDataSource对象获取数据库连接)
3. DruidDataSourceFactory : 通过工厂类获取DruidDataSource对象(是获取DruidDataSource对象的另一种方式)
```
* Druid的使用
* * 方式一(直接创建DruidDataSource对象方式)
```
//创建Druid的核心连接池对象DruidDataSource
DruidDataSource ds = new DruidDataSource();
//设置初始化参数
ds.setUrl(URL);
ds.setUsername(USER); 
ds.setPassword(PASSWORD);
ds.setDriverClassName(DRIVER_CLASS);
Connection conn = ds.getConnection();
```
* * 方式二(使用DruidDataSourceFactory工厂类获取DruidDataSource对象)
```
db.properties

url=jdbc:mysql:///oa
username=root
password=root
driverClassName=com.mysql.jdbc.Driver

注意: properties文件的key值必须是以上的key值(可以通过源码解析进行讲解)

---------------------------------------------------------------------

//听过加载外部配置文件的方式
Properties p = new Properties();
p.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
//采用Druid提供的工厂类创建DruidDataSource对象
DataSource dataSource = DruidDataSourceFactory.createDataSource(p);
Connection conn = dataSource.getConnection();
```

---
## 第九章 DBUtils工具类

### 9.1 DBUtils介绍
```
DBUtils是apache的一个组件,算是一个轻量级的JDBC框架,官网地址

https://commons.apache.org/proper/commons-dbutils/

DbUtils是一个非常小的类库，不需要花很长的时间去看他的API类或者是接口，只需要知道QueryRunner和ReulthSthand两个/接口类即可
```
### 9.2 DBUtils使用

* 通过BeanHandler查询一个ResultSet返回一个JavaBean对象(查询单个对象)
```
QueryRunner run = new QueryRunner(dataSource);

ResultSetHandler<Person> h = new BeanHandler<Person>(Person.class);

Person p = run.query("SELECT * FROM Person WHERE name=?", h, "John Doe");
```
* 通过BeanListHandler查询所有的ResultSet返回一组JavaBean列表
```
QueryRunner run = new QueryRunner(dataSource);

ResultSetHandler<List<Person>> h = new BeanListHandler<Person>(Person.class);

List<Person> persons = run.query("SELECT * FROM Person", h);
```
---
## 第十章 多线程安全问题

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB48ea58239b8b65384853a621e21598fb?method=download&shareKey=9abcc32dd1ad3c5f41d589896aa84fd6">

</center>

```
多线程安全问题产生的原因
1. 一个业务请求中执行的多条sql不能保证在同一个连接或者同一个事物管理中
2. 多用户操作时，可能发生当前业务中的一条sql语句已经出错，进行了回滚,而另一条sql语句却被别的连接提交.

解决方案: 主要是怎么保证一个线程内的所有被执行的SQL语句都是用的是同一个数据库连接(Connection),是解决问题的关键。
```
* 解决方案
* * ThreadLocal简介
```
1. 通过JDK的API文档了解ThreadLocal这个类
2. 通过文档可以得出此对象可以保存线程中产生的局部变量
3. 并且可以将这些局部变量与响应的线程相关联
4. 在线程消失之后，其线程局部实例的所有副本都会被垃圾回收
```
* * ThreadLocal的API

返回值类型 | 方法名 | 描述
---|---|---
T | get() | 返回此线程局部变量的当前线程副本中的值
void | remove() | 移除此线程局部变量当前线程的值
void | set(T value) | 将此线程局部变量的当前线程副本中的值设置为指定值

* * 实例代码
```
public class JdbcUtil {
	private static ThreadLocal<Connection> pool = new ThreadLocal<>();
	/**
	 * 获取当前请求线程上的Connection
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = pool.get();
		if(conn==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("", "", "");
				pool.set(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 关闭当前请求线程上的Connection
	 */
	public static void close(){
	    Connection conn = pool.get();
	    conn.close();//关闭连接
	    pool.remove();//要将关闭之后的连接移除
	}
}
```
