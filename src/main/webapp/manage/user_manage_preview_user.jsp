<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="http//fonts.googleapis.com/css?family=Open+Sans:400,300" />

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

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
				<li><a href="javascript:void(0);"
					onclick="ajaxGetRequest('user/list.s')">用户列表</a></li>
				<li class="active">查看用户</li>
			</ul>
			<!-- .breadcrumb -->
		</div>

		<div class="page-content">
			<div class="modal-open">
				<div class="modal-dialog">
					<div class="modal-content form-horizontal">

						<div class="modal-header center blue">
							<h2 class="modal-title">用户信息</h2>
						</div>

						<div class="modal-body  text-center">

							<div class="form-group">
								<label class="col-sm-3 text-right">头像：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userHeadUrl }</font>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 text-right">账号：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userAccount }</font>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 text-right">状态：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userStatus==1?'锁定':'正常' }
									</font>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 text-right">性别：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userSex!=null?previewUserInfo.userSex:'暂无'  }</font>
								</div>
							</div>

							<div class="form-group">
								<fmt:formatDate value="${ previewUserInfo.userBirth }"
									pattern="yyyy-MM-dd" var="userBirthFormat" />
								<label class="col-sm-3 text-right">出生日期：</label>
								<div class="col-sm-9">
									<font size="3">${ userBirthFormat!=null?userBirthFormat:'暂无' }
									</font>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 text-right">电话：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userPhone!=null?previewUserInfo.userPhone:'暂无'  }</font>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 text-right">qq：</label>
								<div class="col-sm-9">
									<font size="3">${ previewUserInfo.userQQ!=null?previewUserInfo.userQQ:'暂无'  }</font>
								</div>
							</div>

							<div class="form-group">
								<fmt:formatDate value="${ previewUserInfo.userRegisterTime }"
									pattern="yyyy-MM-dd" var="userRegisterTimeFormat" />
								<label class="col-sm-3 text-right">注册时间：</label>
								<div class="col-sm-9">
									<font size="3">${ userRegisterTimeFormat!=null?userRegisterTimeFormat:'暂无' }
									</font>
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