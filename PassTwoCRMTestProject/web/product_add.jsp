<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:23
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
                        <span class="navbar-page-title"> 产品 - 添加产品 </span>
                    </div>
                    <jsp:include page="baseRihtTop.jsp"></jsp:include>
                </div>
            </nav>

        </header>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <!-- 表单 元素 start-->
                <div class="card">
                    <div class="card-body">

                        <form class="form-horizontal" action="${pageContext.request.contextPath}/ProductAddServlet" method="post">
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-thread">产品名称</label>
                                <div class="col-md-4">
                                    <input class="form-control" type="text" id="example-hf-thread" name="productName" placeholder="请输入产品名称..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone">价格</label>
                                <div class="col-md-4">
                                    <input class="form-control" type="text" id="example-hf-phone" name="price" placeholder="请输入价格..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">创建人</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="createId">
                                        <c:forEach items="${createPeople}" var="item">
                                            <option value="${item.empId}">
                                                    ${item.empName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">分类</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="categoryId">
                                        <c:forEach items="${categoryList}" var="item">
                                            <option value="${item.categoryId}">
                                                    ${item.categoryName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">创建时间</label>
                                <div class="col-md-4">
                                    <input class="form-control js-datepicker m-b-10" type="text" id="example-datepicker" name="createTime" value="2010-12-12" placeholder="yyyy-mm-dd" value="" data-date-format="yyyy-mm-dd" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">是否上架</label>
                                <div class="col-md-4">
                                    <!-- 是否上下架-->
                                    <label class="lyear-radio radio-inline radio-primary">
                                        <input type="radio" name="status" checked value="1"><span>是</span>
                                    </label>
                                    <label class="lyear-radio radio-inline radio-primary">
                                        <input type="radio" name="status" value="0"><span>否</span>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-3">
                                    <button class="btn btn-info btn-w-md" type="submit">保存</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- 表单 元素 end-->

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/perfect-scrollbar.min.js"></script>
<!--时间选择插件-->
<script src="js/bootstrap-datetimepicker/moment.min.js"></script>
<script src="js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="js/bootstrap-datetimepicker/locale/zh-cn.js"></script>
<!--日期选择插件-->
<script src="js/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="js/main.min.js"></script>
<!--图表插件-->
<script type="text/javascript" src="js/Chart.js"></script>
<script src="js/jconfirm/jquery-confirm.min.js"></script>

</body>
</html>