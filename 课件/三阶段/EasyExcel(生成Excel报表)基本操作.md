


## 第一章 EasyExcel简介
```
EasyExcel是一个基于Java的简单、省内存的读写Excel的开源项目。在尽可能节约内存的情况下支持读写百M的Excel

github地址:https://github.com/alibaba/easyexcel
```

* 当前案例采用easyexcel版本 2.2.3

---
## 第二章 EasyExcel使用

### 第1节 EasyExcel相关依赖

#### 1.1 如果添加maven依赖
```
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>easyexcel</artifactId>
	<version>2.2.3</version>
</dependency>
```
#### 1.2 如果添加jar包
```
asm-4.2.jar
cglib-3.1.jar
commons-codec-1.10.jar
commons-collections4-4.1.jar
curvesapi-1.04.jar
easyexcel-2.2.3.jar
ehcache-3.4.0.jar
poi-3.17.jar
poi-ooxml-3.17.jar
poi-ooxml-schemas-3.17.jar
slf4j-api-1.7.26.jar
stax-api-1.0.1.jar
xmlbeans-2.6.0.jar
```

### 第2节 写Excel

```
/**
 * 创建User类,用于构建向Excel表格中写数据的类型;
 * @ExcelProperty:这个注解是EasyExcel提供,用于生成Excel表格头
 * @Author 夜泊 
 * @BLOG   https://hd1611756908.github.io/
 */
public class User {
	@ExcelProperty("用户编号")
	private Integer userId;
	@ExcelProperty("姓名")
	private String userName;
	@ExcelProperty("性别")
	private String gender;
	@ExcelProperty("工资")
	private Double salary;
	@ExcelProperty("入职时间")
	private Date hireDate;
	
	//setter/getter方法
}
```
#### 2.1 最简单的写(方式一)

```
/**
 * 向Excel文档中写数据(方式一)
 * 最简单的写
 */
@Test
public void writeExcel1() {
	
	//创建文件保存的位置,以及文件名
	String fileName="user1.xlsx";
	
	/**
	 * 构建要写入的数据
	 * User类是一个自定义的特殊类,专门用来构建向Excel中写数据的类型类
	 * @ExcelProperty是easyexcel提供的注解,用来定义表格的头部
	 */
	List<User> data = new ArrayList<>();
	User user = new User(2001,"李雷","男",1000.123,new Date());
	data.add(user);
	//将数据写到Excel的第一个sheet标签中,并且给sheet标签起名字
	EasyExcel.write(fileName,User.class).sheet("用户信息").doWrite(data);
	//文件流会自动关闭
}
```

#### 2.2 最简单的写(方式二)

```
/**
 * 向Excel文档中写数据(方式二)
 * 最简单的写
 */
@Test
public void writeExcel2() {
	//创建文件保存的位置,以及文件名
	String fileName="user2.xlsx";
	/**
	 * 构建要写入的数据
	 * User类是一个自定义的特殊类,专门用来构建向Excel中写数据的类型类
	 * @ExcelProperty是easyexcel提供的注解,用来定义表格的头部
	 */
	List<User> data = new ArrayList<>();
	User user = new User(2001,"李雷","男",1000.123,new Date());
	data.add(user);
	//创建Excel写对象
	ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
	//创建sheet对象
	WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();
	//将数据写到sheet标签中
	excelWriter.write(data, writeSheet);
	//关闭流,文件流手动关闭
	excelWriter.finish();
}
```
#### 2.3 将类中某几个字段排除掉,不写入Excel中

```
/**
 * 向Excel文档中写数据
 * 将User类中的某几个属性字段排除
 */
@Test
public void writeExcel3() {
	//创建文件保存的位置,以及文件名
	String fileName="user3.xlsx";
	/**
	 * 构建要写入的数据
	 * User类是一个自定义的特殊类,专门用来构建向Excel中写数据的类型类
	 * @ExcelProperty是easyexcel提供的注解,用来定义表格的头部
	 */
	List<User> data = new ArrayList<>();
	User user = new User(2001,"李雷","男",1000.123,new Date());
	data.add(user);
	//构建要排除掉的列
	Set<String> excludeColumnFiledNames = new HashSet<>();
	//排除掉hireDate和userName字段
	excludeColumnFiledNames.add("hireDate");
	excludeColumnFiledNames.add("userName");
	EasyExcel.write(fileName, User.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("abc").doWrite(data);
}
```

#### 2.4 只向表格中导出指定某几个字段

```
/**
 * 向Excel文档中写数据
 * 指定将User类中的某几个字段导出
 */
@Test
public void writeExcel4() {
	//创建文件保存的位置,以及文件名
	String fileName="user4.xlsx";
	/**
	 * 构建要写入的数据
	 * User类是一个自定义的特殊类,专门用来构建向Excel中写数据的类型类
	 * @ExcelProperty是easyexcel提供的注解,用来定义表格的头部
	 */
	List<User> data = new ArrayList<>();
	User user = new User(2001,"李雷","男",1000.123,new Date());
	data.add(user);
	//构建要导出的列
	Set<String> includeColumnFiledNames = new HashSet<>();
	//要导出hireDate和userName字段
	includeColumnFiledNames.add("hireDate");
	includeColumnFiledNames.add("userName");
	EasyExcel.write(fileName, User.class).includeColumnFiledNames(includeColumnFiledNames).sheet("abc").doWrite(data);
}
```

#### 2.5 插入指定的列
```
将Java对象中指定的属性,插入到Excel表格中的指定的列(在Excel中进行列排序)
```
* 写Excel的Java对象类型
```
public class IndexUser {
	/**
	 * value: 设置Excel中列头的名字 index: 设置Excel中将数据插入指定的列(列排序) eg: value="姓名" index=2 :
	 * 将姓名的列头设置在Excel表格的第三列
	 */
	@ExcelProperty(value = "用户编号", index = 0)
	private Integer userId;
	@ExcelProperty(value = "姓名", index = 2)
	private String userName;
	@ExcelProperty(value = "入职时间", index = 1)
	private Date hireDate;
}
```
* 向Excel中写数据
```
/**
 * 插入指定列
 * Java类中有三个属性
 * 我现在要将这三个属性的数据保存到Excel表格中
 * 并且自定义在Excel中的列顺序
 */
@Test
public void writeExcel5() {
	//创建文件保存的位置,以及文件名
	String fileName="user5.xlsx";
	/**
	 * 构建要写入的数据
	 */
	List<IndexUser> data = new ArrayList<>();
	IndexUser user = new IndexUser(1001,"枫桥夜泊",new Date());
	data.add(user);
	EasyExcel.write(fileName, IndexUser.class).sheet("abc").doWrite(data);
}
```
#### 2.6 复杂头数据写入

* 写Excel的Java对象类型
```
/**
 * 构建复杂头信息数据的类
 * @Author 夜泊 
 * @BLOG   https://hd1611756908.github.io/
 */
public class ComplexHeadUser {
	/**
	 * {"用户主题1","用户编号"}这种遇到格式设置多级主题
	 * 第一个参数为一级主题(第一行)
	 * 第二个参数为二级主题(第二行)
	 * 如果一级主题名称相同,那么他们会合并单元格
	 */
	@ExcelProperty({"用户主题1","用户编号"})
	private Integer userId;
	@ExcelProperty({"用户主题2","用户名称"})
	private String userName;
	@ExcelProperty({"用户主题3","入职时间"})
	private Date hireDate;
}
```
* 向Excel中写数据
```
/**
 * 写入复杂头信息到Excel
 */
@Test
public void writeExcel6() {
	//创建文件保存的位置,以及文件名
	String fileName="user6.xlsx";
	/**
	 * 构建要写入的数据
	 */
	List<ComplexHeadUser> data = new ArrayList<>();
	ComplexHeadUser user = new ComplexHeadUser(1001,"枫桥夜泊",new Date());
	data.add(user);
	EasyExcel.write(fileName, ComplexHeadUser.class).sheet("abc").doWrite(data);
}
```

#### 2.7 重复写到Excel的同一个sheet中
```
/**
 * 重复写到同一个sheet中
 */
@Test
public void writeExcel7() {
	//创建文件保存的位置,以及文件名
	String fileName="user7.xlsx";
	//构建数据
	List<User> data = new ArrayList<>();
	User user1 = new User(2001,"李雷1","男",1000.123,new Date());
	User user2 = new User(2002,"李雷2","男",1000.123,new Date());
	User user3 = new User(2003,"李雷3","男",1000.123,new Date());
	User user4 = new User(2004,"李雷4","男",1000.123,new Date());
	data.add(user1);
	data.add(user2);
	data.add(user3);
	data.add(user4);
	//创建ExcelWriter对对象并设置用哪个Class(数据类型)去写
	ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
	//创建sheet
	WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();
	//模拟重复写入,模拟重复写入5次
	for (int i = 0; i < 5; i++) {
		excelWriter.write(data, writeSheet);
	}
	//关闭流
	excelWriter.finish();
}
```
#### 2.8 重复的写到Excel的不同sheet中
```
/**
 * 重复写到不同sheet中
 */
@Test
public void writeExcel8() {
	//创建文件保存的位置,以及文件名
	String fileName="user8.xlsx";
	//构建数据
	List<User> data = new ArrayList<>();
	User user1 = new User(2001,"李雷1","男",1000.123,new Date());
	User user2 = new User(2002,"李雷2","男",1000.123,new Date());
	User user3 = new User(2003,"李雷3","男",1000.123,new Date());
	User user4 = new User(2004,"李雷4","男",1000.123,new Date());
	data.add(user1);
	data.add(user2);
	data.add(user3);
	data.add(user4);
	//创建ExcelWriter对对象并设置用哪个Class(数据类型)去写
	ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
	
	//模拟重复写入,模拟重复写入5次
	for (int i = 0; i < 5; i++) {
		//创建sheet(因为写入到不同的sheet,所以需要多次创建sheet)
		//WriteSheet writeSheet = EasyExcel.writerSheet(i,"用户信息"+i).build(); //用下面的格式也可以
		WriteSheet writeSheet = EasyExcel.writerSheet("用户信息"+i).build();
		excelWriter.write(data, writeSheet);
	}
	//关闭流
	excelWriter.finish();
}
```

#### 2.9 日期、数字类型格式化
```
对于日期和数字有时候我们需要对其展示的样式进行格式化,easyexcel提供了一下注解
@DateTimeFormat : 日期格式化
@NumberFormat   : 数字格式化(小数或者百分数)

```
* 写Excel的Java对象类型
```
/**
 * 创建User类,用于构建向Excel表格中写数据的类型;
 * @ExcelProperty:这个注解是EasyExcel提供,用于生成Excel表格头
 * @Author 夜泊 
 * @BLOG   https://hd1611756908.github.io/
 */
public class User {
	@ExcelProperty("用户编号")
	private Integer userId;
	@ExcelProperty("姓名")
	private String userName;
	@ExcelProperty("性别")
	private String gender;
	//格式化小数类型,如果是百分数那么定义为  #.##% 比如:9.12%
	@NumberFormat("#.##")
	@ExcelProperty("工资")
	private Double salary;
	//格式化时间
	@DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
	@ExcelProperty("入职时间")
	private Date hireDate;
}
```
* 向Excel中写数据
```
/**
 * 日期、数字格式化
 */
@Test
public void writeExcel9() {
	//创建文件保存的位置,以及文件名
	String fileName="user9.xlsx";
	//构建数据
	List<User> data = new ArrayList<>();
	User user1 = new User(2001,"李雷1","男",1000.12223,new Date());
	User user2 = new User(2002,"李雷2","男",1000.12223,new Date());
	User user3 = new User(2003,"李雷3","男",1000.12223,new Date());
	User user4 = new User(2004,"李雷4","男",1000.13323,new Date());
	data.add(user1);
	data.add(user2);
	data.add(user3);
	data.add(user4);
	
	EasyExcel.write(fileName, User.class).sheet("用戶信息").doWrite(data);
}
```
#### 2.10 写入图片到Excel

* 写Excel的Java对象类型
```
/**
 * 构建向Excel中写图片的类 支持5种数据类型的图片类型 
 * 1. 图片可以是一个File格式 
 * 2. 图片可以是一个InputStream输入流的方式 
 * 3. 图片可以是一个byte[]数组的方式 
 * 4. 图片可以是 一个网络的java.net.URL对象方式 
 * 5. 图片也可以是一个String类型方式(当是String类型是，需要StringImageConverter类型转换器)
 * 
 * @Author 夜泊
 * @BLOG https://hd1611756908.github.io/
 */
@ContentRowHeight(100)  // 内容高度
@ColumnWidth(100 / 8)   // 列宽
public class ImageData {
	// 使用抽象文件表示一个图片
	private File file;
	// 使用输入流保存一个图片
	private InputStream inputStream;
	/**
	 * 当使用String类型保存一个图片的时候需要使用StringImageConverter转换器
	 */
	@ExcelProperty(converter = StringImageConverter.class)
	private String string;
	// 使用二进制数据保存为一个图片
	private byte[] byteArray;
	// 使用网络链接保存一个图片
	private URL url;
}
```
* 向Excel中写数据
```
/**
 * 将图片写入到Excel表格中
 * EasyExcel提供了5种数据格式进行图片写入
 * @throws IOException 
 */
@Test
public void writeExcel10() throws IOException {
	//创建文件保存的位置,以及文件名
	String fileName="user10.xlsx";
	
	List<ImageData> list = new ArrayList<ImageData>();
	ImageData imageData = new ImageData();
	//根据File写入
	imageData.setFile(new File("imgs/img.png"));
	//根据InputStream写入
	imageData.setInputStream(new FileInputStream("imgs/img.png"));
	//根据String类型写入
	imageData.setString("imgs/img.png");
	/*
	 * 使用二进制方式写入步骤:
	 * 1. 获取文件大小
	 * 2. 读成二进制流
	 */
	File file = new File("imgs/img.png");
	//创建一个指定大小的二进制数组
	byte[] byteArray = new byte[(int)file.length()];
	FileInputStream in = new FileInputStream(file);
	//将文件流写入到二进制数组中
	in.read(byteArray, 0, (int)file.length());
	imageData.setByteArray(byteArray);
	//只用网络链接
	imageData.setUrl(new URL("https://pics5.baidu.com/feed/e850352ac65c10384dcf6805a7540815b17e890b.jpeg?token=46fd105a95d8754d3daf93de7e8a7f6f"));
	list.add(imageData);
	EasyExcel.write(fileName, ImageData.class).sheet().doWrite(list);
	//关闭输入流
	in.close();
}
```


#### 2.11 设置写入Excel的列宽行高设置

* 写Excel的Java对象类型
```
@ContentRowHeight(30) //设置内容高度
@HeadRowHeight(40)    //设置标题高度
@ColumnWidth(25)      //设置列宽
public class WidthAndHeightData {

	@ExcelProperty("字符串标题")
	private String string;
	@ExcelProperty("日期标题")
	private Date date;
	@ColumnWidth(50)   //设置列宽(可以修饰类,也可以修饰具体属性)
	@ExcelProperty("数字标题")
	private Double doubleData;
}
```
* 向Excel中写数据
```
/**
 * 设置Excel的列宽行高
 */
@Test
public void writeExcel11(){
	//创建文件保存的位置,以及文件名
	String fileName="user11.xlsx";
	//构建数据
	List<WidthAndHeightData> list = new ArrayList<WidthAndHeightData>();
	WidthAndHeightData wahd = new WidthAndHeightData();
	wahd.setString("小明");
	wahd.setDoubleData(200.12);
	wahd.setDate(new Date());
	list.add(wahd);
	//写数据
	EasyExcel.write(fileName, WidthAndHeightData.class).sheet("信息").doWrite(list);
}
```

#### 2.12 通过注解形式设置写入Excel的样式

* 写Excel的Java对象类型
```
//头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 10)
//头字体设置成20
@HeadFontStyle(fontHeightInPoints = 20)
//内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
@ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 17)
//内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 20)
public class DemoStyleData {

	// 字符串的头背景设置成粉红 IndexedColors.PINK.getIndex()
	@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 14)
	// 字符串的头字体设置成20
	@HeadFontStyle(fontHeightInPoints = 30)
	// 字符串的内容的背景设置成天蓝 IndexedColors.SKY_BLUE.getIndex()
	@ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 40)
	// 字符串的内容字体设置成20
	@ContentFontStyle(fontHeightInPoints = 30)
	@ExcelProperty("字符串标题")
	private String string;
	@ExcelProperty("日期标题")
	private Date date;
	@ExcelProperty("数字标题")
	private Double doubleData;
}
```
* 向Excel中写数据
```
/**
 * 注解形式设置Excel样式
 */
@Test
public void writeExcel12(){
	//创建文件保存的位置,以及文件名
	String fileName="user12.xlsx";
	//构建数据
	List<DemoStyleData> list = new ArrayList<>();
	DemoStyleData dsld = new DemoStyleData();
	dsld.setDate(new Date());
	dsld.setDoubleData(200.12);
	dsld.setString("韓梅梅");
	list.add(dsld);
	EasyExcel.write(fileName, DemoStyleData.class).sheet("信息").doWrite(list);
}
```

#### 2.13 合并单元格
* 写Excel的Java对象类型
```
/*
 * @OnceAbsoluteMerge: 指定从哪一行开始，哪一行结束，哪一列开始，哪一列结束，进行合并单元格
 * firstRowIndex   : 起始行索引 从0开始
 * lastRowIndex    : 结束行索引
 * firstColumnIndex: 起始列索引 从0开始
 * lastColumnIndex : 结束列索引
 * 例如: 第2-3行,2-3列进行合并
 */
@OnceAbsoluteMerge(firstRowIndex = 1,lastRowIndex = 2,firstColumnIndex = 1,lastColumnIndex = 2)
public class DemoMergeData {
	//每隔两行合并一次(竖着合并单元格)
	//@ContentLoopMerge(eachRow = 2)
	@ExcelProperty("字符串标题")
	private String string;
	@ExcelProperty("日期标题")
	private Date date;
	@ExcelProperty("数字标题")
	private Double doubleData;
}
```
* 向Excel中写数据
```
/**
 * 合并单元格
 */
@Test
public void writeExcel13(){
	//创建文件保存的位置,以及文件名
	String fileName="user13.xlsx";
	//构建数据
	List<DemoMergeData> list = new ArrayList<>();
	DemoMergeData dmd = new DemoMergeData();
	dmd.setDate(new Date());
	dmd.setDoubleData(200.12);
	dmd.setString("韓梅梅");
	DemoMergeData dmd2 = new DemoMergeData();
	dmd2.setDate(new Date());
	dmd2.setDoubleData(200.12);
	dmd2.setString("韓梅梅");
	DemoMergeData dmd3 = new DemoMergeData();
	dmd3.setDate(new Date());
	dmd3.setDoubleData(200.12);
	dmd3.setString("韓梅梅");
	DemoMergeData dmd4 = new DemoMergeData();
	dmd4.setDate(new Date());
	dmd4.setDoubleData(200.12);
	dmd4.setString("韓梅梅");
	DemoMergeData dmd5 = new DemoMergeData();
	dmd5.setDate(new Date());
	dmd5.setDoubleData(200.12);
	dmd5.setString("韓梅梅");
	list.add(dmd);
	list.add(dmd2);
	list.add(dmd3);
	list.add(dmd4);
	list.add(dmd5);
	EasyExcel.write(fileName, DemoMergeData.class).sheet("信息").doWrite(list);
}
```

### 第3节 读Excel

#### 3.1 最简单的读取Excel

* Excel表格数据类型

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEBd0b986a78d3129445d7cb5c5d8ae148d?method=download&shareKey=d499346d2c26e9eef3c36b363302f099">

</center>

* 盛装数据的Java对象
```
/**
 * 承装Excel表格数据的类
 * 注意点: Java类中的属性的顺序和Excel中的列头的顺序是相同的
 * @Author 夜泊 
 * @BLOG   https://hd1611756908.github.io/
 */
public class DemoData {
    
	private String name;
	private Date hireDate;
	private Double salary;
}
```
* 读取Excel数据
```
/*
 * 最简单的读取Excel内容(方式一)
 * 默认读取Excel文件中的第一个sheet
 */
@Test
public void testRead1() {
	//读取的文件路径
	String fileName="user11.xlsx";
	Class<DemoData> head = DemoData.class; //创建一个数据格式来承装读取到数据
	//读取数据
	EasyExcel.read(fileName, head, new AnalysisEventListener<DemoData>() {

		/**
		 * 解析每一条数据的时候被调用
		 */
		@Override
		public void invoke(DemoData data, AnalysisContext context) {
			//在这里操作，将解析的每一条数据保存到数据库中,在这里可以调用数据库
			System.out.println("解析的数据为: "+data);
		}

		/**
		 * 解析完所有数据的时候被调用
		 */
		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			System.out.println("数据解析完成..........");
		}
	}).sheet().doRead();
	
}



/*
 * 最简单的读取Excel内容(方式二)
 * 默认读取Excel文件中的第一个sheet
 */
@Test
public void testRead2() {
	//读取的文件路径
	String fileName="user11.xlsx";
	Class<DemoData> head = DemoData.class; //创建一个数据格式来承装读取到数据
	//创建ExcelReader对象
	ExcelReader excelReader = EasyExcel.read(fileName, head, new AnalysisEventListener<DemoData>() {

		@Override
		public void invoke(DemoData data, AnalysisContext context) {
			System.out.println("读取到的数据为:"+data);
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			System.out.println("数据解析已完成");
		}
	}).build();
	//创建sheet对象,并读取Excel的第1个sheet(下标从0开始)
	ReadSheet readSheet = EasyExcel.readSheet(0).build();
	excelReader.read(readSheet);
	//关闭流操作，在读取文件时会创建临时文件,如果不关闭,磁盘爆掉
	excelReader.finish();
}
```

#### 3.2 根据Excel中指定的列名或者列的下标读取指定列的数据

* 盛装数据的Java对象
```
public class IndexOrNameData {
	/**
	 * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
	 */
	@ExcelProperty(index = 2)
	private Double doubleData;
	/**
	 * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
	 */
	@ExcelProperty("字符串标题")
	private String string;
	@ExcelProperty("日期标题")
	private Date date;
}
```
* 读取Excel数据操作
```
/*
 * 根据名称或者下标获取指定的Excel表格数据
 */
@Test
public void testRead3() {
	//读取的文件路径
	String fileName="user11.xlsx";
	Class<IndexOrNameData> head = IndexOrNameData.class; //创建一个数据格式来承装读取到数据
	//读取数据
	EasyExcel.read(fileName, head, new AnalysisEventListener<IndexOrNameData>() {

		/**
		 * 解析每一条数据的时候被调用
		 */
		@Override
		public void invoke(IndexOrNameData data, AnalysisContext context) {
			//在这里操作，将解析的每一条数据保存到数据库中,在这里可以调用数据库
			System.out.println("解析的数据为: "+data);
		}

		/**
		 * 解析完所有数据的时候被调用
		 */
		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			System.out.println("数据解析完成..........");
		}
	}).sheet().doRead();
	
}
```

#### 3.3 格式化Excel中的数据格式(例如时间)

* 盛装数据的Java对象
```
/**
 * 定义需要被格式化的字段
 * 如果使用@NumberFormat("#.##")注解，建议数据类型采用String,如果使用double可能不能被格式化
 * @Author 夜泊
 * @BLOG https://hd1611756908.github.io/
 */
public class ConverterData {

	@NumberFormat("#.#")
	@ExcelProperty("数字标题")
	private String salary;
	@DateTimeFormat("yyyy-MM-dd HH:mm:ss")
	@ExcelProperty("日期标题")
	private Date hireDate;
}
```
* 读取Excel数据操作
```
/*
 * 格式化Excel中的数据格式(例如时间)
 */
@Test
public void testRead4() {
	//读取的文件路径
	String fileName="user11.xlsx";
	Class<ConverterData> head = ConverterData.class; //创建一个数据格式来承装读取到数据
	//读取数据
	EasyExcel.read(fileName, head, new AnalysisEventListener<ConverterData>() {

		/**
		 * 解析每一条数据的时候被调用
		 */
		@Override
		public void invoke(ConverterData data, AnalysisContext context) {
			//在这里操作，将解析的每一条数据保存到数据库中,在这里可以调用数据库
			System.out.println("解析的数据为: "+data);
		}

		/**
		 * 解析完所有数据的时候被调用
		 */
		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			System.out.println("数据解析完成..........");
		}
	}).sheet().doRead();
}
```

#### 3.4 读取多个或者全部的Sheet

* 盛装数据的Java对象
```
/**
 * 承装Excel表格数据的类
 * 注意点: Java类中的属性的顺序和Excel中的列头的顺序是相同的
 * @Author 夜泊 
 * @BLOG   https://hd1611756908.github.io/
 */
public class DemoData {

	private String name;
	private Date hireDate;
	private Double salary;
}
```
* 读取Excel数据操作
```
/*
 * 读取Excel中的多个或者全部sheet
 */
@Test
public void testRead5() {
	//读取的文件路径
	String fileName="user11.xlsx";
	Class<DemoData> head = DemoData.class; //创建一个数据格式来承装读取到数据
	/**
	 * 读取全部sheet
	 */
	EasyExcel.read(fileName, head, new AnalysisEventListener<DemoData>() {

		@Override
		public void invoke(DemoData data, AnalysisContext context) {
			// 每解析一条数据被调用一次
			System.out.println("解析的数据为: "+data);
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			// 数据解析完成之后被调用
			System.out.println("数据解析完成......");
		}
	}).doReadAll();
	
	System.out.println("============================================================");
	
	/**
	 * 读取其中的某几个sheet
	 */
	//构建ExcelReader对象
	ExcelReader excelReader = EasyExcel.read(fileName).build();
	//创建想要获取的sheet对象
	ReadSheet sheet1 = EasyExcel.readSheet(0).head(DemoData.class).registerReadListener(new AnalysisEventListener<DemoData>() {

		@Override
		public void invoke(DemoData data, AnalysisContext context) {
			// 每解析一条数据被调用一次
			System.out.println("解析的数据为: "+data);				
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			// 数据解析完成之后被调用
			System.out.println("数据解析完成......");				
		}
	}).build();
	
	ReadSheet sheet2 = EasyExcel.readSheet(2).head(DemoData.class).registerReadListener(new AnalysisEventListener<DemoData>() {

		@Override
		public void invoke(DemoData data, AnalysisContext context) {
			// 每解析一条数据被调用一次
			System.out.println("解析的数据为: "+data);				
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			// 数据解析完成之后被调用
			System.out.println("数据解析完成......");				
		}
	}).build();
	//读取sheet
	excelReader.read(sheet1,sheet2);
	//关闭
	excelReader.finish();
}

```

### 第4节 填充Excel

#### 4.1 简单填充

* 创建Excel模板格式
```
填充单个属性使用{}作为占位符,在大括号里面定义属性名称,如果{}想不作为占位符展示出来,可以使用反斜杠进行转义.
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB69924e488f22f85f277b6742d945a479?method=download&shareKey=272a78e39f2565b413d5ac6744e602b8">

</center>

* 填充数据的Java类
```
public class FillData {
	private String name;
    private double number;
}
```
* 填充的代码
```
/**
 * 简单的单个对象填充
 */
@Test
public void excelFull() {
	//读取模板文件
	String templateFileName="templateFileName.xlsx";
	//通过模板文件生成的填充之后的文件
	String fullFile="fullFile.xlsx";
	//构建要填充的数据
	FillData data = new FillData();
	data.setName("张三");
	data.setNumber(5.2);
	//填充操作
	EasyExcel.write(fullFile).withTemplate(templateFileName).sheet().doFill(data);
}
```
* 填充之后的效果


<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB92a74953f77024100d786804cc770dcc?method=download&shareKey=9a3c73044c0c269c3f944e35373e1f8e">

</center>


#### 4.2 列表填充

* 创建Excel模板格式
```
当占位符是一个列表时,{}里面的属性前面要加一个点代表多个
```

<center>

<img src="https://note.youdao.com/yws/api/personal/file/WEB52736389f77c9328af30c3cb04c91297?method=download&shareKey=88e7cf711726d1c856e29a056b9b715b">

</center>

* 填充数据的Java类
```
public class FillData {
	private String name;
    private double number;
}
```
* 填充的代码
```
/**
 * 简单的列表对象填充
 */
@Test
public void excelFull2() {
	//读取模板文件
	String templateFileName="templateFileName2.xlsx";
	//通过模板文件生成的填充之后的文件
	String fullFile="fullFile2.xlsx";
	//构建要填充的数据列表
	List<FillData> list = new ArrayList<FillData>();
	FillData data1 = new FillData();
	data1.setName("张三");
	data1.setNumber(5.2);
	FillData data2 = new FillData();
	data2.setName("张三");
	data2.setNumber(5.2);
	list.add(data1);
	list.add(data2);
	//填充操作
	EasyExcel.write(fullFile).withTemplate(templateFileName).sheet().doFill(list);
}
```

* 填充之后的效果

<center>
<img src="https://note.youdao.com/yws/api/personal/file/WEB8f8db5fae2863c9590428ea967f822de?method=download&shareKey=296324d30db3647daf6c4b2285c89e6f">
</center>

### 第5节 Web操作(Excel上传/下载)

#### 5.1 Excel下载

* HTML
```
<a href="${pageContext.request.contextPath }/DownLoadExcel">下载</a>
```
* 封装数据的Java类
```
public class User {

	@ExcelProperty("用户编号")
	private Integer userId;
	@ExcelProperty("用户名称")
	private String userName;
	@ExcelProperty("用户性别")
	private String gender;
	@ExcelProperty("创建时间")
	private Date createTime;
}
```
* 下载操作
```
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/**
	 * EasyExcel下载步骤
	 */
	//设置响应头
	response.setContentType("application/vnd.ms-excel");
	response.setCharacterEncoding("utf-8");
	//设置防止文件名中文乱码
	String fileName = URLEncoder.encode("中文文件名","utf-8");
	response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
	//构建写入到Excel中的数据(此数据可以从数据库中获取)
	List<User> users = new ArrayList<>();
	User user1 = new User(1001, "李雷1", "男", new Date());
	User user2 = new User(1002, "李雷2", "男", new Date());
	User user3 = new User(1003, "李雷3", "男", new Date());
	users.add(user1);
	users.add(user2);
	users.add(user3);
	EasyExcel.write(response.getOutputStream(),User.class).sheet("用户信息").doWrite(users);
}
```

#### 5.2 Excel上传

* 前端页面
```
<form action="${pageContext.request.contextPath }/UploadExcelServlet" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
	<input type="submit" value="上传">
</form>
```
* 接收从Excel中获取到的数据
```
public class User {

	@ExcelProperty("用户编号")
	private Integer userId;
	@ExcelProperty("用户名称")
	private String userName;
	@ExcelProperty("用户性别")
	private String gender;
	@ExcelProperty("创建时间")
	private Date createTime;
}
```
* 上传操作
```
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload fileUpload = new ServletFileUpload(factory);
	//设置单个文件为3M
	fileUpload.setFileSizeMax(1024*1024*3);
	//总文件大小为30M
	fileUpload.setSizeMax(1024*1024*3*10);
	
	try {
		List<FileItem> list = fileUpload.parseRequest(req);
		
		for (FileItem fileItem : list) {
			
			//判断是否为附件
			if(!fileItem.isFormField()) {
				//是附件
				InputStream inputStream = fileItem.getInputStream();
				EasyExcel.read(inputStream,User.class,new AnalysisEventListener<User>() {

					@Override
					public void doAfterAllAnalysed(AnalysisContext arg0) {
						System.out.println("Excel全部读完被执行......");
					}

					@Override
					public void invoke(User data, AnalysisContext arg1) {
						//读完一行就执行一次(调用数据库进行插入操作)
						System.out.println("解析一行: "+data);
					}
				}).sheet().doRead();
			}else {
				//普通表单
			}
		}
	} catch (FileUploadException e) {
		e.printStackTrace();
	}
	//上传完成进行转发或者重定向
}
```

---
```
报表相关资料

链接：https://pan.baidu.com/s/1yJe7rSXy8zKHHRuv8wAi3w 
提取码：esdo
```