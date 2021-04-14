<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>我的商城 | 用户管理</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>


    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${tbUser.id==null?"新增": "编辑"}用户
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>



        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbUser.id==null?"新增": "编辑"}用户</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->


                        <form:form id="inputForm" cssClass="flip-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-2 control-label">邮箱</label>

                                        <div class="col-sm-10">
                                            <form:input path="email" cssClass="form-control required email" placeholder="请输入邮箱地址"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 control-label">密码</label>

                                        <div class="col-sm-10">
                                            <form:password path="password" cssClass="form-control required" placeholder="请输入登录密码"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="col-sm-2 control-label">姓名</label>

                                        <div class="col-sm-10">
                                            <form:input path="username" cssClass="form-control required" placeholder="请输入用户的姓名"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-2 control-label">手机</label>

                                        <div class="col-sm-10">
                                            <form:input path="phone" cssClass="form-control required mobile" placeholder="请输入用户的手机号"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                    <button type="submit" class="btn btn-info pull-right">提交</button>
                                </div>
                                <!-- /.box-footer -->
                        </form:form>

                   </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>


    </div>
    <jsp:include page="../includes/copy_right.jsp"/>

</div>

<jsp:include page="../includes/footer.jsp"/>

</body>
</html>