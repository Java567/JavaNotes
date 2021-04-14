<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>我的商城 | 控制面板</title>
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
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户管理</li>
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

                <div class="box box-info box-info-search" style="display: none">
                    <div class="box-header ">
                        <h3 class="box-title">高级搜索</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                    <form:form cssClass="form-horizontal" action="/user/search" method="post" modelAttribute="tbUser">
                        <div class="box-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <form:input path="username" cssClass="form-control " placeholder="姓名"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">邮箱</label>
                                        <div class="col-sm-8">
                                            <form:input path="email" cssClass="form-control " placeholder="邮箱"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-4 control-label">手机</label>
                                        <div class="col-sm-8">
                                            <form:input path="phone" cssClass="form-control pull-right" placeholder="手机号"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-info pull-right">搜索</button>
                        </div>
                        <!-- /.box-footer -->
                    </form:form>
                </div>


                <div class="box">
                    <div class="box-header">
                       <h3 class="box-title">用户列表</h3>
                       <div class="row" style="padding-top: 10px;">
                           <div class="col-xs-12">
                               <a href="/user/from" type="button" class="btn btn-default "><i class="fa fa-plus"> 新增</i></a>&nbsp;&nbsp;&nbsp;
                               <button type="button" class="btn btn-default " onclick="App.deleteMulti('/user/delete');"><i class="fa fa-trash-o">批量删除</i></button>&nbsp;&nbsp;&nbsp;
                               <a href="#" type="button" class="btn btn-default "><i class="fa fa-download"> 导入</i></a>&nbsp;&nbsp;&nbsp;
                               <a href="#" type="button" class="btn btn-default"><i class="fa fa-upload"> 导出</i></a>&nbsp;&nbsp;&nbsp;
                               <button type="button" class="btn btn-primary" onclick="$('.box-info-search').css('display')=='none' ? $('.box-info-search').show('fast'): $('.box-info-search').hide('fast')"><i class="fa fa-search"> 搜索</i></button>&nbsp;&nbsp;&nbsp;
                           </div>
                       </div>
                    </div>
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" class="minimal icheck_master"/>全选</th>
                                <th>ID</th>
                                <th>用户名</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>更新时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tbUsers}" var="tbUser">
                            <tr>
                                <td><input id="${tbUser.id}" type="checkbox" class="minimal"/></td>
                                <td>${tbUser.id}</td>
                                <td>${tbUser.username}</td>
                                <td>${tbUser.phone}</td>
                                <td>${tbUser.email}</td>
                                <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                                <td>
                                    <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-search"> 查看</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-primary btn-xs"><i class="fa fa-edit">编辑</i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-danger btn-xs"><i class="fa fa-trash-o">删除</i></a>&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>

                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            </div>
        </section>


    </div>
    <jsp:include page="../includes/copy_right.jsp"/>
</div>

<!--自定义模态框-->
<jsp:include page="../includes/footer.jsp"/>
<sys:modal />

<%--<script>--%>
<%--    /**--%>
<%--     * 批量删除--%>
<%--     */--%>

<%--    //定义一个存放ID的数组--%>
<%--    var idArray=new Array();--%>

<%--    function deleteMulti() {--%>
<%--        //将选中元素的ID放入数组中--%>
<%--        var _checkbox=App.getCheckbox();--%>
<%--        _checkbox.each(function(){--%>
<%--            var _id=$(this).attr("id")--%>
<%--            if(_id!=null && _id!=="undefine" &&$(this).is(":checked")){--%>
<%--                idArray.push(_id);--%>
<%--            }--%>
<%--        });--%>
<%--        if(idArray.length===0){--%>
<%--            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");--%>
<%--        }--%>

<%--        else {--%>
<%--            $("#modal-message").html("您确定删除数据项吗?");--%>
<%--        }--%>
<%--        $("#modal-default").modal("show");--%>
<%--    }--%>

<%--    $(function () {--%>
<%--        $("#btnModalOk").bind("click",function () {--%>
<%--            del(idArray,"/user/delete")--%>
<%--        })--%>
<%--        --%>
<%--        function del(idArray,url) {--%>
<%--            if (idArray.length===0){--%>
<%--                $("#modal-default").modal("hide");--%>
<%--            }--%>

<%--            else {--%>
<%--                $.ajax({--%>
<%--                    "url":url,--%>
<%--                    "type":"POST",--%>
<%--                    "data":{"ids":idArray.toString()},--%>
<%--                    "dataType":"JSON",--%>
<%--                    "success":function (data) {--%>
<%--                        console.log(data);--%>
<%--                    }--%>
<%--                })--%>
<%--            }--%>

<%--        }--%>
<%--    })--%>
<%--</script>--%>

</body>
</html>