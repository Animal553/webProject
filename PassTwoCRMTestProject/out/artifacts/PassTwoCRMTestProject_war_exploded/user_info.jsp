<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:27
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
    <link rel="stylesheet" href="js/jconfirm/jquery-confirm.min.css">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <jsp:include page="baseLeft.jsp"></jsp:include>
        <!--头部信息-->
        <header class="lyear-layout-header">

            <nav class="navbar navbar-default">
                <div class="topbar">

                    <div class="topbar-left">
                        <div class="lyear-aside-toggler">
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                        </div>
                        <span class="navbar-page-title"> 用户 - 个人信息 </span>
                    </div>
                    <jsp:include page="baseRihtTop.jsp"></jsp:include>
                </div>
            </nav>

        </header>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <div class="edit-avatar">
                                    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/EmployeeChangeHead" method="post">
                                    <img src="${employee.portrait}" alt="..." class="img-avatar">
                                    <div class="avatar-divider"></div>
                                    <div class="edit-avatar-content" >
                                            <input type="hidden" name="empId" value="${employee.empId}">
                                            <input type="file"  id="file" name="H">
                                            <button type="submit" >修改头像</button>
                                        <p class="m-0">选择一张你喜欢的图片，裁剪后会自动生成264x264大小，上传图片大小不能超过2M。</p>
                                    </div>
                                    </form>
                                </div>
                                <hr>
                                <form method="post" action="${pageContext.request.contextPath}/EmployeeUserToUpdateServlet" class="site-form">
                                    <input type="hidden" name="empId" value="${employee.empId}">
                                    <div class="form-group">
                                        <label for="username">用户名</label>
                                        <input type="text" class="form-control" name="username" id="username" value="${employee.empName}" disabled="disabled" />
                                    </div>
                                    <div class="form-group">
                                        <label for="nickname">昵称</label>
                                        <input type="text" class="form-control" name="nickname" id="nickname" placeholder="输入您的昵称" value="${employee.empName}">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="请输入正确的邮箱地址" value="${employee.email}">
                                        <small id="emailHelp" class="form-text text-muted">请保证您填写的邮箱地址是正确的。</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">简介</label>
                                        <textarea class="form-control" name="remark" id="remark" rows="3">
                                            ${employee.info}
                                        </textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary">保存</button>
                                </form>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="js/main.min.js"></script>
</body>
</html>