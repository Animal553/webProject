




#### 第1节 验证码作用
```
防止恶意破解密码、刷票、论坛灌水、刷页
```
#### 第2节 Kaptcha是什么 
```
Kaptcha 是一个可高度配置的实用验证码生成工具
```
#### 第3节 与Spring MVC 整合
```
<dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.2</version>
</dependency>

<!--kaptcha 验证码配置-->
<bean id="defaultKaptcha" class="com.google.code.kaptcha.impl.DefaultKaptcha">
    <!--图片边框-->
    <property name="config">
        <bean class="com.google.code.kaptcha.util.Config">
            <constructor-arg>
                <props>
                    <!--生成的验证码是否有边框-->
                    <prop key="kaptcha.border">yes</prop>
                    <!--验证码背景色-->
                    <prop key="kaptcha.border.color">105,179,90</prop>
                    <!--验证码文本字符颜色-->
                    <prop key="kaptcha.textproducer.font.color">blue</prop>
                    <!--验证码宽度-->
                    <prop key="kaptcha.image.width">164</prop>
                    <!--验证码高度-->
                    <prop key="kaptcha.image.height">47</prop>
                    <!--验证码文本字符大小-->
                    <prop key="kaptcha.textproducer.font.size">45</prop>
                    <!--字体个数-->
                    <prop key="kaptcha.textproducer.char.length">4</prop>
                    <!--字体-->
                    <prop key="kaptcha.textproducer.font.names">宋体,楷体,微软雅黑</prop>
                </props>
            </constructor-arg>
        </bean>
    </property>
</bean>

```
#### 第4节 Kaptcha详细配置

```
<properties>
    <comment>Temporary Properties</comment>
    <!-- 图片边框，合法值yes，no，默认值yes -->
    <entry key="kaptcha.border">no</entry>
    <!-- 边框颜色，合法值rgb(and optional alpha)或者 white,black,blue，默认值black -->
    <entry key="kaptcha.border.color">blue</entry>
    <!-- 边框厚度，合法值>0,默认值为1 -->
    <entry key="kaptcha.border.thickness">2</entry>
    <!-- 图片宽度，默认值200 -->
    <entry key="kaptcha.image.width">200</entry>
    <!-- 图片高度，默认值50 -->
    <entry key="kaptcha.image.height">50</entry>
    <!-- 图片实现类，默认值priv.kerlomz.kaptcha.impl.DefaultKaptcha -->
    <entry key="kaptcha.producer.impl">priv.kerlomz.kaptcha.impl.DefaultKaptcha</entry>
    <!-- 文本实现类,默认值priv.kerlomz.kaptcha.impl.DefaultTextCreator -->
    <entry key="kaptcha.textproducer.impl">priv.kerlomz.kaptcha.text.impl.DefaultTextCreator</entry>
    <!-- 文本集合，验证码值从此集合中获取,默认值abcde2345678gfynmnpwx -->
    <entry key="kaptcha.textproducer.char.string">abcde2345678gfynmnpwx</entry>
    <!-- 验证码长度,默认值为5 -->
    <entry key="kaptcha.textproducer.char.length">5</entry>
    <!-- 字体,默认值Arial, Courier(如果使用中文验证码，则必须使用中文的字体，否则出现乱码) -->
    <entry key="kaptcha.textproducer.font.names">Arial</entry>
    <!-- 字体大小，默认值为40px -->
    <entry key="kaptcha.textproducer.font.size">40</entry>
    <!-- 字体颜色，合法值： r,g,b 或者 white,black,blue，默认值black -->
    <entry key="kaptcha.textproducer.font.color">black</entry>
    <!-- 文字间隔，默认值为2 -->
    <entry key="kaptcha.textproducer.char.space">2</entry>
    <!-- 干扰实现类，默认值priv.kerlomz.kaptcha.impl.DefaultNoise -->
    <entry key="kaptcha.noise.impl">priv.kerlomz.kaptcha.impl.DefaultNoise</entry>
    <!-- 干扰 颜色，合法值： r,g,b 或者 white,black,blue，默认值black -->
    <entry key="kaptcha.noise.color">black</entry>
    <!-- 图片样式： 
         水纹 priv.kerlomz.kaptcha.impl.WaterRipple 
         鱼眼 priv.kerlomz.kaptcha.impl.FishEyeGimpy
         阴影 priv.kerlomz.kaptcha.impl.ShadowGimpy, 默认值水纹    
    -->
    <entry key="kaptcha.obscurificator.impl">priv.kerlomz.kaptcha.impl.WaterRipple</entry>
    <!-- 背景实现类，默认值priv.kerlomz.kaptcha.impl.DefaultBackground -->
    <entry key="kaptcha.background.impl">priv.kerlomz.kaptcha.impl.DefaultBackground</entry>
    <!-- 背景颜色渐变，开始颜色，默认值lightGray/192,193,193 -->
    <entry key="kaptcha.background.clear.from">255,255,255</entry>
    <!-- 背景颜色渐变， 结束颜色，默认值white -->
    <entry key="kaptcha.background.clear.to">white</entry>
    <!-- 文字渲染器，默认值priv.kerlomz.kaptcha.text.impl.DefaultWordRenderer -->
    <entry key="kaptcha.word.impl">priv.kerlomz.kaptcha.text.impl.DefaultWordRenderer</entry>
</properties>
```

#### 第5节 Java代码实现
```java

/*导入Kaptcha defaultKaptcha对应配置文件中bean的ID,用于创建验证码*/
@Autowired
private Producer defaultKaptcha;


1. 获取验证码

/**
 * 获取验证码
 */
@RequestMapping(value = "/getVerifyCode",method = RequestMethod.GET)
public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println("获取验证码成功...........");
    System.out.println(defaultKaptcha);
    //获取session
    HttpSession session = request.getSession();
    /*设置浏览器缓存页面的时限 0:表示不进行缓存 */
    response.setDateHeader("Expires", 0);
    /*JSP页面禁用缓存*/
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    /*IE5 cache更新说明,已弃用*/
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    /*兼容HTTP/1.0 它的行为与 Cache-Control: no-cache 一致*/
    response.setHeader("Pragma", "no-cache");
    /*内容*/
    response.setContentType("image/jpeg");
    /*生成验证码*/
    String kaptchaText = defaultKaptcha.createText();
    /*将生成的二维码放到session域中*/
    session.setAttribute(Constants.KAPTCHA_SESSION_KEY,kaptchaText);
    /*将生成的二维码封装进图片中然后打印到前端*/
    BufferedImage image = defaultKaptcha.createImage(kaptchaText);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image,"jpg",out);
    out.flush();
    out.close();
}

2. 验证码校验
/**
 * 验证码校验
 * @param request
 * @param kaptcha
 * @return
 */
public boolean checkVerifyCode(HttpServletRequest request,String kaptcha){
    //获取我们自己生成保存到session域中的验证码
    String kpSession = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    /*如果数据都不为空开始校验*/
    if(kaptcha!=null && kaptcha!="" && kpSession!=null && kpSession!=""){
        return kaptcha.equals(kpSession);
    }else{
        return false;
    }
}

3. 登录实现
@RequestMapping(value = "/login",method = RequestMethod.POST)
public String login(HttpServletRequest request,String username,String password,String kaptcha){
    System.out.println("....................登录开始....................");
    System.out.println(username);
    System.out.println(password);
    System.out.println(kaptcha);
    boolean code = checkVerifyCode(request, kaptcha);
    System.out.println(code);
    return "01_dashboard";
}

```
#### 第6节 前端页面实现
```
<div class="form-group">
    <div class="row">
        <div class="col-lg-6">
            <input type="text" class="form-control" name="kaptcha" placeholder="验证码">
        </div>
        <div class="col-lg-6">
            <%--获取验证码--%>
            <img id="verifyCodeRefresh" src="${pageContext.request.contextPath}/getVerifyCode" alt="验证码不存在">
        </div>
    </div>
</div>

/*更新图片的src属性*/
$("#verifyCodeRefresh").click(function () {
    this.src="${pageContext.request.contextPath}/getVerifyCode?"+Math.random();
});
```