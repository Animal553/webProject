<%--
  Created by IntelliJ IDEA.
  User: Animal
  Date: 2020/12/22
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!--左侧导航-->
<aside class="lyear-layout-sidebar">
    <!-- logo -->
    <div id="logo" class="sidebar-header">
        <a href="home.jsp"><img src="images/logo-sidebar.png" title="LightYear" alt="LightYear" /></a>
    </div>
    <div class="lyear-layout-sidebar-scroll">
        <nav class="sidebar-main">
            <ul class="nav nav-drawer">
                <li class="nav-item active"> <a href="${pageContext.request.contextPath}/HomeJsonListServlet?empId=${userInfo.empId}"><i class="mdi mdi-home"></i> 后台首页</a> </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-all-inclusive"></i> 线索</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="thread_list.jsp">线索列表</a> </li>
                        <li> <a href="${pageContext.request.contextPath}/ThreadToAddServlet">添加线索</a> </li>
                        <li> <a href="#" data-toggle="modal" data-target="#gridSystemModal">线索导入</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-account"></i> 客户</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="customer_list.jsp">客户列表</a> </li>
                        <li> <a href="${pageContext.request.contextPath}/CustomerToAddServlet">添加客户</a> </li>
                        <li> <a href="lyear_pages_config.jsp">客户导入</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-dribbble"></i>公海</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="international_waters.jsp">公海列表</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi mdi-dropbox"></i>合同</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="contract_list.jsp">合同列表</a> </li>
                        <li> <a href="${pageContext.request.contextPath}/ContractToAddServlet">添加合同</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-chili-hot"></i>产品</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="product_list.jsp">产品列表</a> </li>
                        <li> <a href="${pageContext.request.contextPath}/ProductToAddServlet">添加产品</a> </li>
                        <li> <a href="#">产品导入</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-account-box"></i>员工</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="employee_list.jsp">员工列表</a> </li>
                        <li> <a href="${pageContext.request.contextPath}/EmployeeToAddServlet">添加员工</a> </li>
                    </ul>
                </li>
                <li class="nav-item nav-item-has-subnav">
                    <a href="javascript:void(0)"><i class="mdi mdi-codepen"></i>部门</a>
                    <ul class="nav nav-subnav">
                        <li> <a href="department_list.jsp">部门列表</a> </li>
                        <li> <a href="department_add.jsp">添加部门</a> </li>
                    </ul>
                </li>
            </ul>
        </nav>

        <div class="sidebar-footer">
            <p class="copyright">Copyright &copy; 2020. <img style="width: 25px;padding-bottom: 5px;" src="images/fy.png" /> <a target="_blank" href="https://hd1611756908.github.io/"> CRM</a>&nbsp; Powered By &nbsp;<a href="https://space.bilibili.com/514155929/" target="_blank" title="枫桥夜泊">枫桥夜泊</a></p>
        </div>
    </div>

</aside>
<!--End 左侧导航-->
</body>
</html>
