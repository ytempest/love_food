<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
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
            <li class="active">个人资料</li>
        </ul>
        <!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="modal-open">
            <div class="modal-dialog">
                <div class="modal-content form-horizontal">

                    <div class="modal-header center blue">
                        <h2 class="modal-title">个人信息</h2>
                    </div>

                    <div class="modal-body text-center">
                        <div class="form-group">
                            <label class="col-sm-3 text-right">账号：</label>
                            <div class="col-sm-9 ">
                                <font size="3">${ adminInfo.adminAccount }</font>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 text-right">姓名：</label>
                            <div class="col-sm-9">
                                <font size="3">${ adminInfo.adminName }</font>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">性别：</label>
                            <div class="col-sm-9">
                                <font size="3">${ adminInfo.adminSex }</font>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">电话：</label>
                            <div class="col-sm-9">
                                <font size="3">${ adminInfo.adminPhone  }</font>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 text-right">邮箱：</label>
                            <div class="col-sm-9">
                                <font size="3">${ adminInfo.adminEmail  }</font>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <!-- /.page-content -->
</div>
<!-- /.main-content -->
</body>
</html>