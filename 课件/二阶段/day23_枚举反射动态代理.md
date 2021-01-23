## 一. 枚举

1. **定义**

   * 枚举是一个概念,列举的意思,将所有的情况都列举出来那么取值的时候只能是这几种情况的一种，不能是别的
   * 在Java中枚举可以理解为有限制的多例,在当前类中定义多个实例供别人使用

2. **应用场景**

   * 一个星期只有七天, 我们就可以把这些字符串装在一个数组或者集合中供我们使用(这也是枚举), 但是, 扩展性非常差, 比如, 我们想显示英文的就必须重写一次, 使用枚举类来实现, 就是将变量放到一个类中,然后有限制的定义几个对象使用

3. **格式**

   * 空参构造的枚举类(没有意义)

     ```java
     public  class Week {
     	
     	public static final Week MON = new Week();
     	public static final Week TUE = new Week();
     	public static final Week WED = new Week();
     	public static final Week THU = new Week();
     	public static final Week FRI = new Week();
     	public static final Week SAT = new Week();
     	public static final Week SUN = new Week();
     }
     ```

   * 有参构造的枚举类

     ```java
     public  class Week {
     	
     	public static final Week MON = new Week("星期一");
     	public static final Week TUE = new Week("星期二");
     	public static final Week WED = new Week("星期三");
     	public static final Week THU = new Week("星期四");
     	public static final Week FRI = new Week("星期五");
     	public static final Week SAT = new Week("星期六");
     	public static final Week SUN = new Week("星期日");
     	private String name;
     	//私有化构造
     	private Week(String name){
     		this.name = name;
     	}
     	//提供getter方法,便于别人使用
     	public String getName() {
     		return name;
     	}

     }
     ```

     

   * 有方法的枚举类

     ```java
     public  class Week {
     	
     	public static final Week MON = new Week("星期一");
     	public static final Week TUE = new Week("星期二");
     	public static final Week WED = new Week("星期三");
     	public static final Week THU = new Week("星期四");
     	public static final Week FRI = new Week("星期五");
     	public static final Week SAT = new Week("星期六");
     	public static final Week SUN = new Week("星期日");
     	public String name;
     	//私有化构造
     	private Week(String name){
     		this.name = name;
     	}
     	//提供getter方法,便于别人使用
     	public String getName() {
     		return name;
     	}
     	//提供特殊方法
     	public  void show(){
     		System.out.println("今天是"+name);
     	}
     }
     ```

     

   * 包含抽象方法的枚举类

     ```java
     public abstract class Week {
     	
     	private static final Week MON = new Week("星期一"){
     		public  void show(){
     			System.out.println("星期一好难受");
     		}
     		
     	};
     	private static final Week TUE = new Week("星期二"){
     		public  void show(){
     			System.out.println("星期二晕晕乎乎");
     		}
     		
     	};
     	private static final Week WED = new Week("星期三"){
     		public  void show(){
     			System.out.println("咦,星期三了...");
     		}
     		
     	};
     	private static final Week THU = new Week("星期四"){
     		public  void show(){
     			System.out.println("赶紧,赶紧,今天快点结束");
     		}
     		
     	};
     	private static final Week FRI = new Week("星期五"){
     		public  void show(){
     			System.out.println("哇,好棒,星期五了");
     		}
     		
     	};
     	private static final Week SAT = new Week("星期六"){
     		public  void show(){
     			System.out.println("睡的真舒服");
     		}
     		
     	};
     	private static final Week SUN = new Week("星期日"){
     		public  void show(){
     			System.out.println("好开心哦, 时间慢慢走哈");
     		}
     		
     	};
     	private String name;
     	//私有化构造
     	private Week(String name){
     		this.name = name;
     	}
     	//提供getter方法,便于别人使用
     	public String getName() {
     		return name;
     	}
     	
     	public abstract void show();
     }
     ```

4. **注意事项**

   * 枚举多用于将一组信息装载到一个对象中

5. **测试题**

   * 使用枚举模拟扑克牌的制作牌的过程

## 二. enum关键字的枚举(jdk1.5)

1. **定义**

   * jdk1.5推出了enum关键字来帮助我们简化格式
   * 省略了static final 关键字和创建对象
   * enum关键字还能对格式进行检查

2. **演示**

   ```java
   public enum Week2 {
   	//定义变量,指向对象
   	MON("星期一") ,TUE("星期二") ,WED("星期三") ,THU("星期四") ,FRI("星期五") ,SAT("星期六") ,SUN("星期日") ;
   	
   	String name ;
   	//私有化构造
   	private Week2(String name){
   		this.name = name;
   	}

   	public String getName() {
   		return name;
   	}
   }
   ```

3. **注意事项**

   - 定义枚举类要用关键字enum
   - 所有枚举类都是Enum的子类
   - 枚举类的第一行上必须是枚举项，最后一个枚举项后的分号是可以省略的，但是如果枚举类有其他的东西，这个分号就不能省略。建议不要省略
   - 枚举类可以有构造器，但必须是private的，它默认的也是private的。
   - 枚举类也可以有抽象方法，但是枚举项必须重写该方法
   - swicth语句可以使用枚举

4. **案例**

   ```java
   public static void main(String[] args) {
   	Week2 week2 = Week2.MON;
   	switch (week2) {
   	case FRI:
   			System.out.println("好高兴哦");
   		break;
   	case MON:
   		System.out.println("过了星期三,越过越心宽");
   		break;
   	default:
   		System.out.println("没有了");
   		break;
   	}
   }
   ```

5. **常用方法**

   - int ordinal() 获取枚举项的序号
   - int compareTo(E o)  比较两个枚举项
   - String name() 获取枚举枚举项的名称
   - String toString() 获取枚举项的字符串表现形式
   - <T> T valueOf(Class<T> type,String name) 使用字节码和名称获取枚举项
   - values() 
     - 此方法虽然在JDK文档中查找不到，但每个枚举类都具有该方法，它遍历枚举类的所有枚举值非常方便

## 三. 类加载 

1. **定义**
   * 当程序要使用某个类时，如果该类还未被加载到内存中，则系统会通过加载，连接，初始化三步来实现对这个类进行初始化
   * 加载 
     - 就是指将.class文件读入内存，并为之创建一个Class对象。任何类被使用时系统都会建立一个Class对象。
   * 连接
     - 验证 是否有正确的内部结构，并和其他类协调一致
     - 准备 负责为类的静态成员分配内存
     - 解析 将类的二进制数据中的符号引用替换为直接引用
   * 初始化 就是我们以前讲过的初始化步骤
2. **加载的时机**
   * 创建类的实例
   * 访问类的静态变量，或者为静态变量赋值
   * 调用类的静态方法
   * 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象
   * 加载某个类的子类
   * 直接使用java.exe命令来运行某个主类  java TestDemo

## 四. 类加载器的概述和分类

1. **定义**

   * 负责将.class文件加载到内存中，并为之生成对应的Class对象。虽然我们不需要关心类加载机制，但是了解这个机制我们就能更好的理解程序的运行

2. **类加载器的分类**

   - Bootstrap ClassLoader 根类加载器
   - Extension ClassLoader 扩展类加载器
   - Sysetm ClassLoader 系统类加载器
   - AppClassLoader 应用类加载器

3. **类加载器的作用**

   - BootstrapClassLoader 根类加载器
     - 也被称为引导类加载器，负责Java核心类的加载
     - 比如System,String等。在JDK中JRE的lib目录下rt.jar文件中
   - ExtensionClassLoader 扩展类加载器
     - 负责JRE的扩展目录中jar包的加载。
     - 在JDK中JRE的lib目录下ext目录
   - SysetmClassLoader 系统类加载器
     - 负责在JVM启动时加载来自java命令的class文件，以及classpath环境变量所指定的jar包和类路径
   - AppClassLoader 加载其他类
     * 负载一些非核心类和程序猿自己写的类

4. 演示

   ```java
   public static void main(String[] args) {
   	//获取TestDemo类的类加载器
   	System.out.println(TestDemo.class.getClassLoader());
   	
   }
   ```

## 五. 自定义类加载器

 1.  **双亲委派模型**

     * 当前类加载器从自己已经加载的类中查询是否此类已经加载，如果已经加载则直接返回原来已经加载的类。
     * 如果没有找到，就去委托父类加载器去加载（如代码c = parent.loadClass(name, false)所示）。父类加载器也会采用同样的策略，查看自己已经加载过的类中是否包含这个类，有就返回，没有就委托父类的父类去加载，一直到根类加载器。因为如果父加载器为空了，就代表使用根类类加载器作为父加载器去加载
     * 如果根类类加载器加载失败（例如在$JAVA_HOME/jre/lib里未查找到该class），会使用拓展类加载器来尝试加载，继续失败则会使用AppClassLoader来加载，继续失败则会抛出一个异常ClassNotFoundException，然后再调用当前加载器的findClass()方法进行加载

     2. **好处**
        * 主要是为了安全性，避免用户自己编写的类动态替换 Java的一些核心类，比如 String。
        * 同时也避免了类的重复加载，因为 JVM中区分不同类，不仅仅是根据类名，相同的 class文件被不同的 ClassLoader加载就是不同的两个类
     3. **案例演示**

```java
public class MyClassLoader extends ClassLoader{
	private String path;
	public MyClassLoader(String path) {
		super();
		this.path = path;
	}
    	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		//读取本地文件
		byte[] bs = getBytes(path);
		//将字节数组装载成Class对象
		Class<?> clazz = this.defineClass(name, bs, 0, bs.length);
		return clazz;
	}
	
	private byte[] getBytes(String path){
		try(
			FileInputStream fis = new FileInputStream(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
		) {
			byte[] bs = new byte[1024];
			int len ;
			while((len=fis.read(bs))!=-1){
				bos.write(bs, 0, len);
			}
			return bos.toByteArray();
		} catch (Exception e) {
			
		}
		return null;
	}
}
```

```java
public static void main(String[] args) throws Exception {
	MyClassLoader classLoader = new MyClassLoader("D:\\Student.class");
	Class<?> clazz = classLoader.findClass("com.qianfeng.Student");
	//Class<?> class1 = Class.forName("com.qianfeng.Student", true, classLoader);
	
	Object obj = clazz.newInstance();
	System.out.println(obj.getClass().getClassLoader());
}
```

## **六. 反射**

1. **定义**

   - JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法
   - 对于任意一个对象，都能够调用它的任意一个方法和属性
   - 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制
   - 要想解剖一个类,必须先要获取到该类的字节码文件对象
   - 而解剖使用的就是Class类中的方法，所以先要获取到每一个字节码文件对应的Class类型的对象
   - 说白了就是获取一个类的骨架

2. **获取字节码的三种方式**

   - 对象.getClass()
   - 类名.class
   - Class类中静态方法forName("类名")

3. **演示**

   ```java
   public static void main(String[] args) throws Exception {
   	Student student = new Student();
   	Class<?> clazz1 = student.getClass();
   	Class<?> clazz2 = Student.class;
   	Class<?> clazz3 = Class.forName("com.qianfeng.Student");
   }
   ```

## 七. 反射获取构造函数

1. **定义**

   * Class类的newInstance()方法是使用该类无参的构造函数创建对象
   * 如果一个类没有无参的构造函数, 就不能这样创建了,可以调用Class类的getConstructor(String.class,int.class)方法获取一个指定的构造函数然后再调用Constructor类的newInstance("张三",20)方法创建对象

2. **演示**

   ```java
   public static void main(String[] args) throws Exception {
   	Class<?> clazz =  Class.forName("com.qianfeng.Student");
   	Student object = (Student)clazz.newInstance();
   	object.method();
   	
   }
   ```

3. **榨汁机案例**

   * 分别有水果(Fruit)苹果(Apple)香蕉(Banana)桔子(Orange)榨汁(squeeze)
   * 根据客户的需求, 随时更换果汁

   ```java
   interface Fruit {
   	public void squeeze();
   }

   class Apple implements Fruit {
   	public void squeeze() {
   		System.out.println("榨出一杯苹果汁儿");
   	}
   }

   class Orange implements Fruit {
   	public void squeeze() {
   		System.out.println("榨出一杯桔子汁儿");
   	}
   }

   class Juicer {
   	public void run(Fruit f) {
   		f.squeeze();
   	}

   }
   ```

   ```java
   public static void main(String[] args) throws Exception {
   	
   	//从本地读取配置文件
   	BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        //创建输入流对象,关联配置文件	
   	Class<?> clazz = Class.forName(br.readLine());	//读取配置文件一行内容,获取该类的字节码对象
   	Fruit f = (Fruit) clazz.newInstance();		//通过字节码对象创建实例对象
   	Juicer j = new Juicer();
   	j.run(f);
   }
   ```

## 八. 反射获取成员变量

1. **定义**

   * Class.getField(String)方法可以获取类中的指定字段(可见的)
   * 如果是私有的可以用getDeclaedField("name")方法获取
   * 通过get(obj) 和set(obj, "李四")方法可以获取和设置指定对象上该字段的值, obj指的是这个类的对象
   * 如果是私有的需要先调用setAccessible(true)设置访问权限放开

2. **演示**

   ```java
   public static void main(String[] args) throws Exception {
   	Student student = new Student();
   	Class<?> clazz = Class.forName("com.qianfeng.Student");
     	//获取共有的属性
   	Field field = clazz.getField("name");
     	//获取所有的属性
   	Field field2 = clazz.getDeclaredField("name");
   	//取消语言检查
   	field2.setAccessible(true);
   	//给一个对象的属性设置值
   	field2.set(student, "333");
   	//获取这个对象的属性的值
   	String str= (String) field2.get(student);
   	System.out.println(str);
   	
   }
   ```

3. 测试题

   * 需求: 写一个方法, 通过此方法可以给任意对象的任意属性设置值

## 九. 反射获取成员方法

1. **定义**

   * Class.getMethod(String, Class...) 和 Class.getDeclaredMethod(String, Class...)方法可以获取类中的指定方法
   * 调用invoke(Object, Object...)可以调用对象的这个方法

2. **演示**

   ```java
   public static void main(String[] args) throws Exception {
   	Student student = new Student();
   	Class<?> clazz = Class.forName("com.qianfeng.Student");
   	Method method = clazz.getMethod("method");
   	method.invoke(student);
   	//获取私有方法
   	Method method2= clazz.getDeclaredMethod("method2");
   	//取消语言检查
   	method2.setAccessible(true);
   	//调用对象的方法
   	method2.invoke(student);
   	
   }
   ```

3. 测试题

   * 需求: 往一个ArrayList\<Integer> 的对象中添加String类型的值

## 十. 动态代理

1. 定义

   * 代理: 本来应该自己做的事情，请了别人来做，被请的人就是代理对象
   * 动态代理：在程序运行过程中产生的这个对象,而程序运行过程中产生对象其实就是我们刚才反射讲解的内容，所以，动态代理其实就是通过反射来生成一个代理对象
   * 代理可以使我们在不破坏源代码的情况下增加新的功能

2. java中动态代理的使用

   * 在Java中java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过使用这个类和接口就可以生成动态代理对象
   * public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
   * 最终会调用InvocationHandler的方法
   * InvocationHandler Object invoke(Object proxy,Method method,Object[] args)

3. 演示

   ```java
   public static void main(String[] args) throws Exception {
   	
   	Student student = new Student();
   	//创建Student的代理对象
   	MyInterface p =  (MyInterface) Proxy.newProxyInstance(Student.class.getClassLoader(), Student.class.getInterfaces(), new InvocationHandler() {
   		
   		@Override
   		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
   			System.out.println("你好");
   			method.invoke(student);
   			return null;
   		}
   	});
   	p.method();
   }
   ```

4. 原理

   * 利用对象的类的字节码接口,写出一个新的类到本地区,通过编译器直接编译成.class文件,再通过类加载器加载进来
   * 弊端: 代理的对象必须实现接口

## 十一. cglib

1. 定义

   * 非java原生的动态代理, 效率更高,限制更小
   * 可以代理没有接口的类

2. 使用

   * 导包

   * 演示

     ```java
     public static void main(String[] args) {
     	//导入包	cglib-core  asm		ant		ant-launcher
     	//创建运行器
     	MethodInterceptor mi = new MethodInterceptor() {
     		
     		@Override
     		public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
     			System.out.println("运行前");
     			arg3.invokeSuper(arg0, arg2);
     			System.out.println("运行后");
     			return null;
     		}
     	};
     	//获取代理类
     	Enhancer enhancer = new Enhancer();
     	//设置父类
     	enhancer.setSuperclass(Demo.class);
     	//运行任务
     	enhancer.setCallback(mi);
     	//创建代理对象
     	Demo d = (Demo)enhancer.create();
     	d.method();
     }
     ```

## 作业

1. 把今天的案例都敲一遍