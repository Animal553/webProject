<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/21
  Time: 9:16
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
                        <span class="navbar-page-title"> 菜单 </span>
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
                    <div class="col-sm-6 col-lg-3">
                        <div class="card bg-primary">
                            <div class="card-body clearfix">
                                <div class="pull-right">
                                    <p class="h6 text-white m-t-0">当日收入</p>
                                    <p class="h3 text-white m-b-0">${homePageInformation.income}</p>
                                </div>
                                <div class="pull-left"> <span class="img-avatar img-avatar-48 bg-translucent"><i class="mdi mdi-currency-cny fa-1-5x"></i></span> </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-3">
                        <div class="card bg-danger">
                            <div class="card-body clearfix">
                                <div class="pull-right">
                                    <p class="h6 text-white m-t-0">本月收入</p>
                                    <p class="h3 text-white m-b-0">${homePageInformation.mothIncome}</p>
                                </div>
                                <div class="pull-left"> <span class="img-avatar img-avatar-48 bg-translucent"><i class="mdi mdi-account fa-1-5x"></i></span> </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-3">
                        <div class="card bg-success">
                            <div class="card-body clearfix">
                                <div class="pull-right">
                                    <p class="h6 text-white m-t-0">当日用户新增</p>
                                    <p class="h3 text-white m-b-0">${homePageInformation.userIncrease}</p>
                                </div>
                                <div class="pull-left"> <span class="img-avatar img-avatar-48 bg-translucent"><i class="mdi mdi-arrow-down-bold fa-1-5x"></i></span> </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-3">
                        <div class="card bg-purple">
                            <div class="card-body clearfix">
                                <div class="pull-right">
                                    <p class="h6 text-white m-t-0">当月用户新增</p>
                                    <p class="h3 text-white m-b-0">${homePageInformation.nowUserIncrease}</p>
                                </div>
                                <div class="pull-left"> <span class="img-avatar img-avatar-48 bg-translucent"><i class="mdi mdi-comment-outline fa-1-5x"></i></span> </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header">
                                <h4>客户增长</h4>
                            </div>
                            <div class="card-body">
                                <canvas class="js-chartjs-bars"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header">
                                <h4>近年交易</h4>
                            </div>
                            <div class="card-body">
                                <canvas class="js-chartjs-lines"></canvas>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h4>任务列表</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>任务名称</th>
                                            <th>开始日期</th>
                                            <th>状态</th>
                                            <th>进度</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${contractList1 }" var="item">
                                            <tr>
                                                <td>${item.contractId }</td>
                                                <td>${item.contractName}</td>
                                                <td>
                                                    <f:formatDate value="${item.startTime }" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <c:if test="${item.status==0 }">
                                                        <span class="label label-info">审核中</span>
                                                    </c:if>
                                                    <c:if test="${item.status==1 }">
                                                        <span class="label label-success">审批人1通过</span>
                                                    </c:if>
                                                    <c:if test="${item.status==2 }">
                                                        <span class="label label-danger">审批人1未通过(审批结束)</span>
                                                    </c:if>
                                                    <c:if test="${item.status==3 }">
                                                        <span class="label label-success">审批人2通过(审批完成)</span>
                                                    </c:if>
                                                    <c:if test="${item.status==4 }">
                                                        <span class="label label-danger">审批人2未通过(审批完成)</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${userInfo.empId eq item.approval1Id}">

                                                        <c:if test="${item.status==0 }">
                                                            <a href="ContractUpdateStatusServlet?contractId=${item.contractId }&status=1&empId=${userInfo.empId}" class="btn btn-xs btn-primary">通过</a>
                                                            <a href="ContractUpdateStatusServlet?contractId=${item.contractId }&status=2&&empId=${userInfo.empId}" class="btn btn-xs btn-danger">不通过</a>
                                                        </c:if>
                                                        <c:if test="${item.status==1 }">
                                                            <span class="label label-success">通过</span>
                                                        </c:if>
                                                        <c:if test="${item.status==2 }">
                                                            <span class="label label-danger">未通过</span>
                                                        </c:if>
                                                        <c:if test="${item.status==3 }">
                                                            <span class="label label-success">完成</span>
                                                        </c:if>
                                                        <c:if test="${item.status==4 }">
                                                            <span class="label label-danger">未通过</span>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${userInfo.empId eq item.approval2Id}">

                                                        <c:if test="${item.status==0 }">
                                                            <span class="label label-info">待审核</span>
                                                        </c:if>

                                                        <c:if test="${item.status==1 }">
                                                            <a href="ContractUpdateStatusServlet?contractId=${item.contractId }&status=3&empId=${userInfo.empId}" class="btn btn-xs btn-primary">通过</a>
                                                            <a href="ContractUpdateStatusServlet?contractId=${item.contractId }&status=4&empId=${userInfo.empId}" class="btn btn-xs btn-danger">不通过</a>
                                                        </c:if>
                                                        <c:if test="${item.status==2 }">
                                                            <span class="label label-danger">未通过</span>
                                                        </c:if>
                                                        <c:if test="${item.status==3 }">
                                                            <span class="label label-success">已完成</span>
                                                        </c:if>
                                                        <c:if test="${item.status==4 }">
                                                            <span class="label label-danger">未通过</span>
                                                        </c:if>
                                                    </c:if>


                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!--End 页面主要内容-->
    </div>
</div>


<!-- 导入线索模态框 start -->
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="gridSystemModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">线索导入</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <a class="btn btn-info btn-w-lg" href="#">线索模板下载</a>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-6 	col-md-offset-3">
                        <form class="form-horizontal" action="#" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="example-hf-thread">文件</label>
                                <div class="col-md-8">
                                    <input class="form-control" type="file" id="example-hf-thread">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4 col-md-offset-2">
                                    <button class="btn btn-info btn-w-lg" type="submit">上传</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 导入线索模态框 end -->
<input type="hidden" id="di" value="${userInfo.empId}">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="js/main.min.js"></script>

<!--图表插件-->
<script type="text/javascript" src="js/Chart.js"></script>
<script type="text/javascript">
    $(document).ready(function(e) {

        var $dashChartBarsCnt  = jQuery( '.js-chartjs-bars' )[0].getContext( '2d' ),
            $dashChartLinesCnt = jQuery( '.js-chartjs-lines' )[0].getContext( '2d' );

        var $dashChartBarsData = {
            labels: ['周日', '周六', '周五', '周四', '周三', '周二', '周一'],
            datasets: [
                {
                    label: '注册用户',
                    borderWidth: 1,
                    borderColor: 'rgba(0,0,0,0)',
                    backgroundColor: 'rgba(51,202,185,0.5)',
                    hoverBackgroundColor: "rgba(51,202,185,0.7)",
                    hoverBorderColor: "rgba(0,0,0,0)",
                    data: ${homePageInformation.register}
                }
            ]
        };
        var $dashChartLinesData = {
            labels: [ '2008', '2009', '2010', '2011', '2012', '2013', '2014'],
            datasets: [
                {
                    label: '交易资金',
                    data: ${homePageInformation.capital},
                    borderColor: '#358ed7',
                    backgroundColor: 'rgba(53, 142, 215, 0.175)',
                    borderWidth: 1,
                    fill: false,
                    lineTension: 0.5
                }
            ]
        };

        new Chart($dashChartBarsCnt, {
            type: 'bar',
            data: $dashChartBarsData
        });

        var myLineChart = new Chart($dashChartLinesCnt, {
            type: 'line',
            data: $dashChartLinesData,
        });
    });

</script>
</body>
</html>