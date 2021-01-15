<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <span class="navbar-page-title"> 回款 - 回款列表 </span>
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
                            <div class="card-toolbar clearfix">
                                <form class="pull-right search-bar" method="get" action="#!" role="form">
                                    <div class="input-group">
                                        <input type="text" class="form-control" value="" name="keyword" placeholder="请输入合同">
                                        <div class="input-group-btn">
                                            <button class="btn btn-dark" type="button">搜索</button>
                                        </div>
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="contract_add.html"><i class="mdi mdi-plus"></i> 新增</a>
                                    <a id="btnDel" class="btn btn-danger" href="#!"><i class="mdi mdi-window-close"></i> 删除</a>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" id="check-all"><span></span>
                                                </label>
                                            </th>
                                            <th>回款编号</th>
                                            <th>客户名称</th>
                                            <th>合同编号</th>
                                            <th>回款日期</th>
                                            <th>回款金额</th>
                                            <th>创建人</th>
                                            <th>创建时间</th>
                                            <th>回款方式</th>
                                            <th>状态</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" name="ids[]" value="1001"><span></span>
                                                </label>
                                            </td>
                                            <td>1001</td>
                                            <td>李雷</td>
                                            <td>3001</td>
                                            <td>2012-12-13</td>
                                            <td>10002</td>
                                            <td>韩梅梅</td>
                                            <td>2012-12-13</td>
                                            <td>支付宝</td>
                                            <td>已提交</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" name="ids[]" value="1001"><span></span>
                                                </label>
                                            </td>
                                            <td>1001</td>
                                            <td>李雷</td>
                                            <td>3001</td>
                                            <td>2012-12-13</td>
                                            <td>10002</td>
                                            <td>韩梅梅</td>
                                            <td>2012-12-13</td>
                                            <td>支付宝</td>
                                            <td>已提交</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <ul class="pagination">
                                    <li class="disabled"><span>«</span></li>
                                    <li class="active"><span>1</span></li>
                                    <li><a href="#1">2</a></li>
                                    <li><a href="#1">3</a></li>
                                    <li><a href="#1">4</a></li>
                                    <li><a href="#!">»</a></li>
                                </ul>

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
<!--图表插件-->
<script type="text/javascript" src="js/Chart.js"></script>
<script src="js/jconfirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="js/pdfobject.min.js"></script>
<script type="text/javascript">

    /* 查看pdf文件 */
    $(".contractClass").click(function(){
        PDFObject.embed(this.href);
    });


</script>
</body>
</html>