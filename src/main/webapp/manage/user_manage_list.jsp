<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet"
          href="http//fonts.googleapis.com/css?family=Open+Sans:400,300"/>

    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <style type="text/css">
        th, td {
            text-align: center;
        }
    </style>

    <script src="assets/js/ace-extra.min.js"></script>


</head>
<body>

<div>
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li><i class="icon-home home-icon"></i> <a href="index.jsp">首页</a></li>
            <li class="active">用户管理</li>
            <li class="active">用户列表</li>
        </ul>
        <!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="page-header">
            <h1>用户列表</h1>
        </div>
        <!-- /.page-header -->

        <div class="group">

            <div style="margin-bottom: 1%;" class="pull-left">
                <div class="form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" id="key" name="key"
                               placeholder="按账号模糊搜索">
                    </div>
                    <button type="button" class="btn btn-sm btn-primary" onclick="fuzzySearch()">
                        <i class="icon-search"></i>
                    </button>
                </div>
            </div>

            <div style="margin-bottom: 1%;" class="pull-right">
                <ul class="pagination no-margin pull-right blue">
                    <li id="recordCount" class="prev disabled">
                        一共${ pageInfo.pageCount }页，${ pageInfo.total }个记录，跳转到
                    </li>

                    <li><input id="pageInput" type="text" style="width: 30px;" required/></li>
                    <li class="prev disabled">页</li>
                    <li id="skipBtn">
                        <button class="btn btn-xs blue"
                                style="margin-bottom: 3px"
                                onclick="skipPage(1,${pageInfo.pageCount},null)">跳转
                        </button>
                    </li>

                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="sample-table-1"
                           class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>性别</th>
                            <th>生日</th>

                            <th>手机号</th>
                            <th>QQ</th>
                            <th>邮箱</th>
                            <th>注册时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody id="list_table">
                        <c:forEach var="vo" items="${ pageInfo.list }" begin="0"
                                   varStatus="status">

                            <tr>
                                <td>${ index + status.index+1 }</td>
                                <td>${ vo.userAccount }</td>
                                <td>${ vo.userSex!=null?vo.userSex:'暂无' }</td>
                                <fmt:formatDate value="${ vo.userBirth }" pattern="yyyy-MM-dd"
                                                var="userBirthFormat"/>
                                <td>${ userBirthFormat!=null?userBirthFormat:'暂无' }</td>
                                <td>${ vo.userPhone!=null?vo.userPhone:'暂无' }</td>
                                <td>${ vo.userQQ!=null?vo.userQQ:'暂无' }</td>
                                <td>${ vo.userEmail!=null?vo.userEmail:'暂无' }</td>
                                <fmt:formatDate value="${ vo.userRegisterTime }"
                                                pattern="yyyy-MM-dd HH:mm:ss"
                                                var="userRegisterTimeFormat"/>
                                <td>${ userRegisterTimeFormat }</td>
                                <td>${ vo.userStatus==1?'锁定':'正常' }</td>
                                <td>
                                    <div
                                            class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                        <a class="blue" href="javascript:void(0);"
                                           onclick="ajaxGetRequest('user/preview.s?account=${vo.userAccount}')">
                                            <i class="icon-zoom-in bigger-130"></i>
                                        </a> <a class="green" href="javascript:void(0);"
                                                onclick="ajaxGetRequest('user/update.s?account=${vo.userAccount}')">
                                        <i class="icon-pencil bigger-130"></i>
                                    </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <!-- /span -->
        </div>
        <!-- /row -->


        <div class="text-center">
            <nav aria-label="Page navigation ">
                <ul id="page_ul" class="pagination no-margin">

                    <c:set var="pageList" value="${ pageInfo.getPages(9) }"/>
                    <c:set var="pageListSize" value="${ fn:length(pageList) }"/>

                    <li class="prev"><a href="javascript:void(0);"
                                        onclick="prevPageClick(${pageList[0]})"> <i
                            class="icon-double-angle-left"></i>
                    </a></li>

                    <c:forEach var="i" items="${ pageList  }">
                        <li ${ i==pageInfo.getCurrentPage()? 'class="active"':'' }><a
                                href="javascript:void(0);" onclick="pageClick(${ i })">${ i }</a>
                        </li>
                    </c:forEach>

                    <li class="next"><a href="javascript:void(0);"
                                        onclick="nextPageClick(${pageList[pageListSize-1]==pageInfo.pageCount?pageInfo.pageCount:pageList[pageListSize-1]+1})">
                        <i class="icon-double-angle-right"></i>
                    </a></li>
                </ul>
            </nav>
        </div>


        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->

</body>
</html>