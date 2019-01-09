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
				<li class="active">修改用户</li>
			</ul>

		</div>

		<div class="page-content">
			<div class="modal-open">
				<div class="modal-dialog">
					<div class="modal-content">
						<form action="user/confirm-update.s" class="form-horizontal"
							method="post">

							<div class="modal-header center blue">
								<h2 class="modal-title">修改用户</h2>
							</div>

							<div class="modal-body">

								<div class="form-group">
									<label class="col-sm-3 control-label">账号：</label>
									<div class="col-sm-9">
										<input type="text" readonly="readonly"
											pattern="^[a-zA-Z0-9]{6,15}$" class="form-control"
											id="userAccount" name="userAccount"
											placeholder="请输入6-15位的数字、大小写字母"
											value="${ udpateUserInfo.userAccount }" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 text-right">头像：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userHeadUrl"
											name="userHeadUrl" placeholder="请输入头像地址"
											value="${ udpateUserInfo.userHeadUrl }" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">重设密码：</label>
									<div class="col-sm-9">
										<input  type="password" pattern="^[a-zA-Z0-9]{6,15}$"
											class="form-control" id="userPwd" name="userPwd"
											placeholder="如果要修改密码请输入6-15位的数字、大小写字母组成的新密码" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">注册时间：</label>
									<div class="col-sm-9">
										<fmt:formatDate value="${ udpateUserInfo.userRegisterTime }"
											pattern="yyyy-MM-dd HH:mm:ss" var="userRegisterTimeFormat" />
										<input type="text" class="form-control" id="userRegisterTime"
											name="userRegisterTime" value="${ userRegisterTimeFormat }"
											placeholder="请输入注册时间  格式:yyyy-MM-dd HH:mm:ss" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">状态：</label>
									<div class="col-sm-9">
										<label class="checkbox-inline"> <input type="radio"
										 value="0" name="userStatus"
											${ udpateUserInfo.userStatus==0 ?'checked="checked"':''} />
											正常
										</label> <label class="checkbox-inline"> <input type="radio"
										 value="1" name="userStatus"
											${ udpateUserInfo.userStatus==1?'checked="checked"':''} />
											锁定
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">性别：</label>
									<div class="col-sm-9">
										<label class="checkbox-inline"> <input type="radio"
											value="男" name="userSex"
											${ udpateUserInfo.userSex=='男'?'checked="checked"':''} /> 男
										</label> <label class="checkbox-inline"> <input type="radio"
											value="女" name="userSex"
											${ udpateUserInfo.userSex=='女'?'checked="checked"':''} /> 女
										</label>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期：</label>
									<div class="col-sm-9">
										<fmt:formatDate value="${ udpateUserInfo.userBirth }"
											pattern="yyyy-MM-dd" var="userBirthFormat" />
										<input type="text" pattern="^\d{4}-\d{1,2}-\d{1,2}$"
											class="form-control" 	id="userBirth" name="userBirth" 
											placeholder="请输入yyyy-MM-dd格式的日期" value="${ userBirthFormat }" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">电话：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userPhone" name="userPhone"
											placeholder="请输入电话"  value="${ udpateUserInfo.userPhone }" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">qq：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userQQ" name="userQQ"
											placeholder="请输入qq号码" value="${ udpateUserInfo.userQQ }" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱：</label>
									<div class="col-sm-9">
										<input type="email" class="form-control" id="userEmail"
											name="userEmail" placeholder="请输入邮箱"
											value="${ udpateUserInfo.userEmail }" />
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									onclick="confirmUpdateClick()">确认修改</button>
							</div>
						</form>
					</div>
				</div>
			</div>




		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
</body>
</html>