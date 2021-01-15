<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:26
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
                        <span class="navbar-page-title"> 线索 - 线索列表 </span>
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
                                <form class="pull-right search-bar" role="form">
                                    <div class="input-group">
                                        <input type="text" id="K" class="form-control" name="keyword" onchange="getName()" placeholder="请输入线索">
                                        <div class="input-group-btn">
                                            <button class="btn btn-dark" type="button" onclick="select()">搜索</button>
                                        </div>
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="${pageContext.request.contextPath}/ThreadToAddServlet"><i class="mdi mdi-plus"></i> 新增</a>
                                    <a class="btn btn-info m-r-5" href="#!"><i class="mdi mdi-check"></i> 导出</a>
                                    <a id="btnDel" class="btn btn-danger" ><i class="mdi mdi-window-close"></i> 删除</a>
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
                                            <th>线索名称</th>
                                            <th>手机</th>
                                            <th>负责人</th>
                                            <th>创建人</th>
                                            <th>线索来源</th>
                                            <th>客户行业</th>
                                            <th>客户级别</th>
                                            <th>创建时间</th>
                                            <th>更新时间</th>
                                            <th>下次联系时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tbody">

                                        </tbody>
                                    </table>
                                </div>
                                <ul class="pagination" id="page">
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
<script>
    var pageNo = 1;
    var pageSize = 5;
    var pageCount = 10;

    $.getJSON("ThreadJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
        var list = d.pageMessage;
        pageCount =  d.pageCount;
        var str = "";
        for (var i=0;i<list.length;i++){
            str+="<tr>\r\n" +
                "<td>"+
                "<label class=\"lyear-checkbox checkbox-primary\">"+
                "<input  type=\"checkbox\" name=\"ids\" value="+ list[i].customerId +"> <span></span>"+
                "</label>\r\n" +
                "</td>\r\n" +
                "<td>"+list[i].customerName+"</td>\r\n" +
                "<td>"+list[i].phone +"</td>\r\n" +
                "<td>"+list[i].chargeEmpName+"</td>\r\n" +
                "<td>"+list[i].createEmpName+"</td>\r\n" +
                "<td>"+list[i].sourceName+"</td>\r\n" +
                "<td>"+list[i].industryName+"</td>\r\n" +
                "<td>"+list[i].levelName+"</td>\r\n" +
                "<td>"+list[i].createTime+"</td>\r\n" +
                "<td>"+list[i].updateTime+"</td>\r\n" +
                "<td>"+list[i].nextTime+"</td>\r\n" +
                "<td>\r\n" +
                " <div class=\"btn-group\">"+
                "	<a class=\"btn btn-xs btn-default\"  title=\"编辑\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/ThreadToUpdateServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-pencil\"></i></a>\r\n" +
                "	<a class=\"btn btn-xs btn-default\"  title=\"删除\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/CustomerDeleteServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-window-close\"></i></a>\r\n" +
                " </div>"+
                "</td>\r\n" +
                "</tr>"
        }
        $("#tbody").html(str);
        joinPage(pageCount, pageNo);
        showPageCss(pageNo,".pm");
        bindEven(pageNo,pageCount);
    })

    //将分页的数字变红
    function showPageCss(pageNo,pm) {
        var pms = $(pm);
        for (var i = 0; i < pm.length; i++) {
            var pi = $(pms[i]).html();
            console.log("页码:",pi);
            if (pageNo==pi){
                $(pms[i]).css({
                    "color":"red"
                });
            }
        }
    }



    /* 事件绑定 */
    function bindEven(){
        //绑定中间页
        var pms = $(".pm");

        for(var i=0;i<pms.length;i++){
            $(pms[i]).click(function(){
                //获取当前点击的页码
                var cussentPage=$(this).html();
                //将获取到的字符串类型的页码进行转换,转成number

                pageNo = parseInt(cussentPage);
                //AJAX
                $.getJSON("ThreadJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input  type=\"checkbox\" name=\"ids\" value="+ list[i].customerId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].phone +"</td>\r\n" +
                            "<td>"+list[i].chargeEmpName+"</td>\r\n" +
                            "<td>"+list[i].createEmpName+"</td>\r\n" +
                            "<td>"+list[i].sourceName+"</td>\r\n" +
                            "<td>"+list[i].industryName+"</td>\r\n" +
                            "<td>"+list[i].levelName+"</td>\r\n" +
                            "<td>"+list[i].createTime+"</td>\r\n" +
                            "<td>"+list[i].updateTime+"</td>\r\n" +
                            "<td>"+list[i].nextTime+"</td>\r\n" +
                            "<td>\r\n" +
                            " <div class=\"btn-group\">"+
                            "	<a class=\"btn btn-xs btn-default\"  title=\"编辑\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/ThreadToUpdateServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-pencil\"></i></a>\r\n" +
                            "	<a class=\"btn btn-xs btn-default\"  title=\"删除\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/CustomerDeleteServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-window-close\"></i></a>\r\n" +
                            " </div>"+
                            "</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    joinPage(pageCount, pageNo);
                    showPageCss(pageNo,".pm");
                    bindEven(pageNo,pageCount);
                })
            });
        }


        //绑定下一页
        $("#next").click(function(){

            if(pageNo>=pageCount){
                alert("已经是最后一页");
            }else{
                pageNo=pageNo+1;

                $.getJSON("ThreadJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input  type=\"checkbox\" name=\"ids\" value="+ list[i].customerId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].phone +"</td>\r\n" +
                            "<td>"+list[i].chargeEmpName+"</td>\r\n" +
                            "<td>"+list[i].createEmpName+"</td>\r\n" +
                            "<td>"+list[i].sourceName+"</td>\r\n" +
                            "<td>"+list[i].industryName+"</td>\r\n" +
                            "<td>"+list[i].levelName+"</td>\r\n" +
                            "<td>"+list[i].createTime+"</td>\r\n" +
                            "<td>"+list[i].updateTime+"</td>\r\n" +
                            "<td>"+list[i].nextTime+"</td>\r\n" +
                            "<td>\r\n" +
                            " <div class=\"btn-group\">"+
                            "	<a class=\"btn btn-xs btn-default\"  title=\"编辑\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/ThreadToUpdateServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-pencil\"></i></a>\r\n" +
                            "	<a class=\"btn btn-xs btn-default\"  title=\"删除\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/CustomerDeleteServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-window-close\"></i></a>\r\n" +
                            " </div>"+
                            "</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    joinPage(pageCount, pageNo);
                    showPageCss(pageNo,".pm");
                    bindEven(pageNo,pageCount);
                })
            }
        });



        //绑定上一页事件
        $("#pre").click(function(){

            if(pageNo<=1){
                alert("已经是第一页");
            }else{
                pageNo = pageNo-1;

                //通过AJAX请求调用后台

                $.getJSON("ThreadJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input  type=\"checkbox\" name=\"ids\" value="+ list[i].customerId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].phone +"</td>\r\n" +
                            "<td>"+list[i].chargeEmpName+"</td>\r\n" +
                            "<td>"+list[i].createEmpName+"</td>\r\n" +
                            "<td>"+list[i].sourceName+"</td>\r\n" +
                            "<td>"+list[i].industryName+"</td>\r\n" +
                            "<td>"+list[i].levelName+"</td>\r\n" +
                            "<td>"+list[i].createTime+"</td>\r\n" +
                            "<td>"+list[i].updateTime+"</td>\r\n" +
                            "<td>"+list[i].nextTime+"</td>\r\n" +
                            "<td>\r\n" +
                            " <div class=\"btn-group\">"+
                            "	<a class=\"btn btn-xs btn-default\"  title=\"编辑\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/ThreadToUpdateServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-pencil\"></i></a>\r\n" +
                            "	<a class=\"btn btn-xs btn-default\"  title=\"删除\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/CustomerDeleteServlet?customerId="+list[i].customerId+" \"> <i class=\"mdi mdi-window-close\"></i></a>\r\n" +
                            " </div>"+
                            "</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    joinPage(pageCount, pageNo);
                    showPageCss(pageNo,".pm");
                    bindEven(pageNo,pageCount);
                })
            }
        });
    }

    function joinPage(pageCount, pageNo) {
        var str="<li><a id='pre' href=\"#\"><<</a></li>";
        if (pageCount<=3){
            for(var i=1;i<=pageCount;i++){
                str+="<li><a class='pm' href=\"#\">"+i+"</a></li>";
            }
        }else{

            if(pageNo<=3){
                for(var i=1;i<=3;i++){
                    str+="<li><a class='pm' href=\"#\">"+i+"</a></li>";
                }
            }else{
                for(var i=pageNo-2;i<=pageNo;i++){
                    str+="<li><a class='pm' href=\"#\">"+i+"</a></li>";
                }
            }
        }
        str+="<li><a id='next' href=\"#\">>></a></li>";
        //将拼接好的参数设置到指定位置
        $("#page").html(str);
    }

    $(function(){
        /* 删除提示框生成 */
        var array = new Array();
        $('#btnDel').on('click', function () {
            //获取被选中的多选框
            var cks = $("input[type='checkbox']:checked").not($("#check-all"));
            for(var i=0;i<cks.length;i++){
                console.log(cks[i].value);
                array.push(cks[i].value);
            }

            $.confirm({
                title: '删除',
                content: '是否确认删除?',
                type: 'red',
                typeAnimated: true,
                buttons: {
                    tryAgain: {
                        text: '确认',
                        btnClass: 'btn-red',
                        action: function(){
                            //当点击确认删除时被执行
                            console.log('确认删除....');
                            $.getJSON("CustomerDeleteListServlet",{array:array},function(da) {
                                console.log(da);
                                if (da!=null){
                                    location.href="thread_list.jsp";
                                }
                            });
                        }
                    },
                    close: {
                        text: '关闭'
                    }
                }
            });
        });
    });
    var kname;
    function getName() {
        kname = $("#K").val();
    }
    function select() {
        $("#tbody").empty();
        $("#page").empty();
        $.getJSON("ThreadJsonListServlet",{kname:kname},function (date) {
            console.log(date);
            var list = date
            var str = ""
            for (var i=0;i<list.length;i++){
                str+="<tr>\r\n" +
                    "<td>"+
                    "<label class=\"lyear-checkbox checkbox-primary\">"+
                    "<input  type=\"checkbox\" name=\"ids\" value="+ date[i].customerId +"> <span></span>"+
                    "</label>\r\n" +
                    "</td>\r\n" +
                    "<td>"+date[i].customerName+"</td>\r\n" +
                    "<td>"+date[i].phone +"</td>\r\n" +
                    "<td>"+date[i].chargeEmpName+"</td>\r\n" +
                    "<td>"+date[i].createEmpName+"</td>\r\n" +
                    "<td>"+date[i].sourceName+"</td>\r\n" +
                    "<td>"+date[i].industryName+"</td>\r\n" +
                    "<td>"+date[i].levelName+"</td>\r\n" +
                    "<td>"+date[i].createTime+"</td>\r\n" +
                    "<td>"+date[i].updateTime+"</td>\r\n" +
                    "<td>"+date[i].nextTime+"</td>\r\n" +
                    "<td>\r\n" +
                    " <div class=\"btn-group\">"+
                    "	<a class=\"btn btn-xs btn-default\"  title=\"编辑\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/ThreadToUpdateServlet?customerId="+date[i].customerId+" \"> <i class=\"mdi mdi-pencil\"></i></a>\r\n" +
                    "	<a class=\"btn btn-xs btn-default\"  title=\"删除\" data-toggle=\"tooltip\" href=\"${pageContext.request.contextPath }/CustomerDeleteServlet?customerId="+date[i].customerId+" \"> <i class=\"mdi mdi-window-close\"></i></a>\r\n" +
                    " </div>"+
                    "</td>\r\n" +
                    "</tr>"
            }
            $("#tbody").html(str);
        })
    }
</script>
</body>
</html>