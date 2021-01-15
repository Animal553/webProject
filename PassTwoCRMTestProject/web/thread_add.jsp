<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:25
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
                        <span class="navbar-page-title"> 线索 - 添加线索 </span>
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

                        <form class="form-horizontal" action="${pageContext.request.contextPath}/ThreadAddServlet">
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-thread">线索名称</label>
                                <div class="col-md-4">
                                    <input class="form-control" type="text" id="example-hf-thread" name="customerName"  placeholder="请输入线索名称..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone">手机</label>
                                <div class="col-md-4">
                                    <input class="form-control" type="text" id="example-hf-phone" name="phone"  placeholder="请输入手机号..">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">负责人</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="empId1">
                                        <c:forEach items="${employeeList3}" var="item">
                                            <option   value="${item.empId}">
                                                    ${item.empName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">创建人</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="empId2">
                                        <c:forEach items="${employeeList3}" var="item">
                                            <option  value="${item.empId}">
                                                    ${item.empName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">线索来源</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="sourceId">
                                        <c:forEach items="${sourceList}" var="item">
                                            <option  value="${item.sourceId}">
                                                    ${item.sourceName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">客户行业</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="industryId">
                                        <c:forEach items="${industryList}" var="item">
                                            <option  value="${item.industryId}">
                                                    ${item.industryName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">客户级别</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="levelId">
                                        <c:forEach items="${levelList}" var="item">
                                            <option value="${item.levelId}">
                                                    ${item.levelName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label" for="example-hf-phone1">下次联系时间</label>
                                <div class="col-md-4">
                                    <input class="form-control js-datepicker" name="nextTime" type="text" data-date-format="yyyy-mm-dd" placeholder="年-月-日">
                                </div>
                            </div>
                            <!-- 单选框-->
<%--                            <div class="form-group">--%>
<%--                                <label class="col-md-3 control-label" for="example-hf-phone1">成交状态</label>--%>
<%--                                <div class="col-md-4">--%>
<%--                                    <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                        <input type="radio" checked="checked" name="e" value="1"><span>已成交</span>--%>
<%--                                    </label>--%>
<%--                                    <label class="lyear-radio radio-inline radio-primary">--%>
<%--                                        <input type="radio" name="e" value="0"><span>未成交</span>--%>
<%--                                    </label>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="form-group">
                                <div class="col-md-9 col-md-offset-3">
                                    <button class="btn btn-info btn-w-md" type="submit">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
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
