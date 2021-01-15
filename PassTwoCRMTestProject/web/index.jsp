<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
  <title>客户资源管理系统</title>
  <!-- 标签图标设置 -->
  <link rel="icon" href="images/favicon.ico" type="image/ico">
  <!-- SEO（搜索引擎所有关键件） -->
  <meta name="keywords" content="crm,qf">
  <meta name="description" content="这是一个解决中小企业客户资源管理的网站">
  <meta name="author" content="枫桥夜泊1990">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/materialdesignicons.min.css" rel="stylesheet">
  <link href="css/style.min.css" rel="stylesheet">
  <style>
    .lyear-wrapper {
      position: relative;
    }
    .lyear-login {
      display: flex !important;
      min-height: 100vh;
      align-items: center !important;
      justify-content: center !important;
    }
    .login-center {
      /* background: #fff; */
      background: rgba(255,255,255,0.5);
      min-width: 38.25rem;
      padding: 2.14286em 3.57143em;
      border-radius: 5px;
      margin: 2.85714em 0;
    }
    .login-header {
      margin-bottom: 1.5rem !important;
    }
    .login-center .has-feedback.feedback-left .form-control {
      padding-left: 38px;
      padding-right: 12px;
    }
    .login-center .has-feedback.feedback-left .form-control-feedback {
      left: 0;
      right: auto;
      width: 38px;
      height: 38px;
      line-height: 38px;
      z-index: 4;
      color: #dcdcdc;
    }
    .login-center .has-feedback.feedback-left.row .form-control-feedback {
      left: 15px;
    }
  </style>
</head>

<body style="background: url(images/bg.jpg);">
<div class="row lyear-wrapper">
  <div class="lyear-login">
    <div class="login-center">
      <div class="login-header text-center">
        <a href="index.html"> <img alt="light year admin" src="images/logo-sidebar.png"> </a>
      </div>
      <form action="${pageContext.request.contextPath}/EmployeeLoginServlet" method="post">
        <div class="form-group has-feedback feedback-left">
          <input type="text" placeholder="请输入邮箱" class="form-control" name="email" id="username" />
          <span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left">
          <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" />
          <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left">
          <label class="lyear-checkbox checkbox-info m-t-10">
            <input type="checkbox" name="rem" checked value="1"><span>记住我</span>
          </label>
        </div>
        <div class="form-group">
          <button class="btn btn-block btn-primary" type="submit">立即登录</button>
        </div>
      </form>
      <hr>
      <footer class="col-sm-12 text-center">
        <p class="m-b-0">Copyright © 2019 <a href="https://ukoko.cn">枫桥夜泊</a>. All right reserved</p>
      </footer>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">;</script>
</body>
<script>

  var name = $("#username").val();
  var password = $("#password").val();
  var login = ${login};
  if (login==true){
      location.href="home.jsp";
  }

</script>
</html>