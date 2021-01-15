<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/18
  Time: 17:13
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
                        <span class="navbar-page-title"> 合同 - 合同列表 </span>
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
                                        <input type="text" id="K" class="form-control" name="keyword" onchange="getName()" placeholder="请输入合同">
                                        <div class="input-group-btn">
                                            <button class="btn btn-dark" type="button" onclick="select()">搜索</button>
                                        </div>
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="${pageContext.request.contextPath}/ContractToAddServlet"><i class="mdi mdi-plus"></i> 新增</a>
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
                                            <th>合同编号</th>
                                            <th>合同名称</th>
                                            <th>客户名称</th>
                                            <th>合同金额</th>
                                            <th>生效时间</th>
                                            <th>结束时间</th>
                                            <th>客户签约人</th>
                                            <th>公司签约人</th>
                                            <th>负责人</th>
                                            <th>审批人1</th>
                                            <th>审批人2</th>
                                            <th>状态</th>
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
<div id="detailId"></div>


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

    $.getJSON("ContractJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
        console.log(d);
        var list = d.pageMessage;
        pageCount =  d.pageCount;
        var str = "";
        for (var i=0;i<list.length;i++){
            str+="<tr>\r\n" +
                "<td>"+
                "<label class=\"lyear-checkbox checkbox-primary\">"+
                "<input type=\"checkbox\" name=\"ids\" value="+ list[i].contractId +"> <span></span>"+
                "</label>\r\n" +
                "</td>\r\n" +
                " <td>\r\n"+
                "<a class=\"contractBtn\" data-toggle=\"modal\" data-target=\"#gridSystemModal\">"+list[i].contractId+"</a>\r\n"+
                "</td>\r\n"+
                "<td>\r\n"+
                "<a class=\"contractClass\" href="+list[i].filePath+">"+list[i].contractName+"</a>\r\n"+
                "</td>\r\n"+
                "<td>"+list[i].customerName+"</td>\r\n" +
                "<td>"+list[i].contractPrice +"</td>\r\n" +
                "<td>"+list[i].startTime+"</td>\r\n" +
                "<td>"+list[i].endTime+"</td>\r\n" +
                "<td>"+list[i].customerSign+"</td>\r\n" +
                "<td>"+list[i].companySignName+"</td>\r\n" +
                "<td>"+list[i].chargeName+"</td>\r\n" +
                "<td>"+list[i].approval1Name+"</td>\r\n" +
                "<td>"+list[i].approval2Name+"</td>\r\n" +
                "<td>"+list[i].statusName+"</td>\r\n" +
                "</tr>"
        }
        $("#tbody").html(str);
        getCPDetail();
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
                $.getJSON("ContractJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input type=\"checkbox\" name=\"ids\" value="+ list[i].contractId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            " <td>\r\n"+
                            "<a href=\"#\" data-toggle=\"modal\" data-target=\"#gridSystemModal\">"+list[i].contractId+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>\r\n"+
                            "<a class=\"contractClass\" href="+list[i].filePath+">"+list[i].contractName+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].contractPrice +"</td>\r\n" +
                            "<td>"+list[i].startTime+"</td>\r\n" +
                            "<td>"+list[i].endTime+"</td>\r\n" +
                            "<td>"+list[i].customerSign+"</td>\r\n" +
                            "<td>"+list[i].companySignName+"</td>\r\n" +
                            "<td>"+list[i].chargeName+"</td>\r\n" +
                            "<td>"+list[i].approval1Name+"</td>\r\n" +
                            "<td>"+list[i].approval2Name+"</td>\r\n" +
                            "<td>"+list[i].statusName+"</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    getCPDetail();
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
                $.getJSON("ContractJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input type=\"checkbox\" name=\"ids\" value="+ list[i].contractId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            " <td>\r\n"+
                            "<a href=\"#\" data-toggle=\"modal\" data-target=\"#gridSystemModal\">"+list[i].contractId+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>\r\n"+
                            "<a class=\"contractClass\" href="+list[i].filePath+">"+list[i].contractName+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].contractPrice +"</td>\r\n" +
                            "<td>"+list[i].startTime+"</td>\r\n" +
                            "<td>"+list[i].endTime+"</td>\r\n" +
                            "<td>"+list[i].customerSign+"</td>\r\n" +
                            "<td>"+list[i].companySignName+"</td>\r\n" +
                            "<td>"+list[i].chargeName+"</td>\r\n" +
                            "<td>"+list[i].approval1Name+"</td>\r\n" +
                            "<td>"+list[i].approval2Name+"</td>\r\n" +
                            "<td>"+list[i].statusName+"</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    getCPDetail();
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

                $.getJSON("ContractJsonListServlet",{pageNo:pageNo,pageSize:pageSize},function (d) {
                    var list = d.pageMessage;
                    pageCount =  d.pageCount;
                    var str = "";
                    for (var i=0;i<list.length;i++){
                        str+="<tr>\r\n" +
                            "<td>"+
                            "<label class=\"lyear-checkbox checkbox-primary\">"+
                            "<input type=\"checkbox\" name=\"ids\" value="+ list[i].contractId +"> <span></span>"+
                            "</label>\r\n" +
                            "</td>\r\n" +
                            " <td>\r\n"+
                            "<a href=\"#\" data-toggle=\"modal\" data-target=\"#gridSystemModal\">"+list[i].contractId+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>\r\n"+
                            "<a class=\"contractClass\" href="+list[i].filePath+">"+list[i].contractName+"</a>\r\n"+
                            "</td>\r\n"+
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].customerName+"</td>\r\n" +
                            "<td>"+list[i].contractPrice +"</td>\r\n" +
                            "<td>"+list[i].startTime+"</td>\r\n" +
                            "<td>"+list[i].endTime+"</td>\r\n" +
                            "<td>"+list[i].customerSign+"</td>\r\n" +
                            "<td>"+list[i].companySignName+"</td>\r\n" +
                            "<td>"+list[i].chargeName+"</td>\r\n" +
                            "<td>"+list[i].approval1Name+"</td>\r\n" +
                            "<td>"+list[i].approval2Name+"</td>\r\n" +
                            "<td>"+list[i].statusName+"</td>\r\n" +
                            "</tr>"
                    }
                    $("#tbody").html(str);
                    getCPDetail();
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
                            $.getJSON("ContractDeleteListServlet",{array:array},function(da) {
                                console.log(da);
                                if (da!=null){
                                    location.href="contract_list.jsp";
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
        $.getJSON("ContractLikeSelectServlet",{kname:kname},function (date) {
            console.log(date);
            var list = date
            var str = ""
            for (var i=0;i<list.length;i++){
                str+="<tr>\r\n" +
                    "<td>"+
                    "<label class=\"lyear-checkbox checkbox-primary\">"+
                    "<input type=\"checkbox\" name=\"ids\" value="+ date[i].contractId +"> <span></span>"+
                    "</label>\r\n" +
                    "</td>\r\n" +
                    " <td>\r\n"+
                    "<a href=\"#\" data-toggle=\"modal\" data-target=\"#gridSystemModal\">"+date[i].contractId+"</a>\r\n"+
                    "</td>\r\n"+
                    "<td>\r\n"+
                    "<a class=\"contractClass\" href="+data[i].filePath+">"+data[i].contractName+"</a>\r\n"+
                    "</td>\r\n"+
                    "<td>"+date[i].customerName+"</td>\r\n" +
                    "<td>"+date[i].contractPrice +"</td>\r\n" +
                    "<td>"+date[i].startTime+"</td>\r\n" +
                    "<td>"+date[i].endTime+"</td>\r\n" +
                    "<td>"+date[i].customerSign+"</td>\r\n" +
                    "<td>"+date[i].companySignName+"</td>\r\n" +
                    "<td>"+date[i].chargeName+"</td>\r\n" +
                    "<td>"+date[i].approval1Name+"</td>\r\n" +
                    "<td>"+date[i].approval2Name+"</td>\r\n" +
                    "<td>"+date[i].statusName+"</td>\r\n" +
                    "</tr>"
            }
            getCPDetail();
            $("#tbody").html(str);
        })
    }

    /* 查看pdf文件 */
    $(".contractClass").click(function(){
        PDFObject.embed(this.href);
    });


    function getCPDetail(){
        var cps = $(".contractBtn");
        console.log(cps);
        for(var i=0;i<cps.length;i++){
            $(cps[i]).click(function(){
                var cId = this.innerHTML;
                // ajax获取合同详情(合同/关联产品)
                $.ajax({
                    url:"ContractJsonDetailServlet",
                    method:"GET",
                    data:{contractId:cId},
                    async:false,
                    dataType:"json",
                    success:function(d){
                        var str="<div class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridSystemModalLabel\" id=\"gridSystemModal\">\r\n" +
                            "<div class=\"modal-dialog\" role=\"document\">\r\n" +
                            "<div class=\"modal-content\">\r\n" +
                            "<div class=\"modal-header\">\r\n" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\r\n" +
                            "<h4 class=\"modal-title\" id=\"gridSystemModalLabel\">合同信息</h4>\r\n" +
                            "</div>\r\n" +
                            "<div class=\"modal-body\">\r\n" +
                            "<div class=\"row\">\r\n" +
                            "<div class=\"col-md-12\">\r\n" +
                            "<ul class=\"nav-step step-anchor\">\r\n" +
                            "<li class=\"nav-step-item\">\r\n" +
                            "<a href=\"#step-1\">\r\n" +
                            "<h6>"+d.chargeName+"</h6>\r\n" +
                            "<p class=\"m-0\">已提交</p>\r\n" +
                            "</a>\r\n" +
                            "</li>\r\n" +
                            "<li class=\"nav-step-item\">\r\n" +
                            "<a href=\"#step-2\">\r\n" +
                            "<h6>"+d.approval1Name+"</h6>\r\n" +
                            "<p class=\"m-0\">"+(d.status==0?'未审批':d.status==2?'未通过':'已审批')+"</p>\r\n" +
                            "</a>\r\n" +
                            "</li>\r\n" +
                            "<li class=\"nav-step-item\">\r\n" +
                            "<a href=\"#step-3\">\r\n" +
                            "<h6>"+d.approval2Name+"</h6>\r\n" +
                            "<p class=\"m-0\">"+(d.status==0 || d.status==1 || d.status==2 ? '未审批':d.status==3?'通过':'未通过')+"</p>\r\n" +
                            "</a>\r\n" +
                            "</li>\r\n" +
                            "</ul>\r\n" +
                            "</div>\r\n" +
                            "</div>\r\n" +
                            "<div class=\"modal-header\">\r\n" +
                            "<h4 class=\"modal-title\" id=\"gridSystemModalLabel\">产品列表</h4>\r\n" +
                            "</div>\r\n" +
                            "<div class=\"row\">\r\n" +
                            "<div class=\"col-md-12\">\r\n" +
                            "<table class=\"table\">\r\n" +
                            "<thead>\r\n" +
                            "<tr>\r\n" +
                            "<th>产品名称</th>\r\n" +
                            "<th>产品编号</th>\r\n" +
                            "<th>价格</th>\r\n" +
                            "<th>数量</th>\r\n" +
                            "<th>合计</th>\r\n" +
                            "</tr>\r\n" +
                            "</thead>\r\n" +
                            "<tbody>\r\n";

                        var ps="";
                        var ll = d.contractProduct;
                        for(var i=0;i<ll.length;i++){
                            ps+="<tr>\r\n" +
                                "<th>"+ll[i].productName+"</th>\r\n" +
                                "<td>"+ll[i].productId+"</td>\r\n" +
                                "<td>"+ll[i].price+"</td>\r\n" +
                                "<td>"+ll[i].count+"</td>\r\n" +
                                "<td>"+((ll[i].price)*(ll[i].count)) +"</td>\r\n" +
                                "</tr>\r\n";
                        }

                        var s = "</tbody>\r\n" +
                            "</table>\r\n" +
                            "</div>\r\n" +
                            "</div>\r\n" +
                            "</div>\r\n" +
                            "</div>\r\n" +
                            "</div>\r\n" +
                            "</div>";
                        str=str+ps+s;
                        $("#detailId").html(str);
                    }
                });
            });
        }
    }
</script>
</body>
</html>