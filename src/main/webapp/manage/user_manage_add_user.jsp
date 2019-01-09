<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	var canRegister = true;

	function confirmAddUser() {

		if (!check()) {
			return;
		}

		alert(canRegister);

		if (!canRegister) {
			return;
		}

		var array = new Array("userHeadUrl", "userAccount", "userPwd",
				"userBirth", "userPhone", "userQQ", "userEmail");
		var key = "";
		for (var i = 0; i < array.length; i++) {
			var value = document.getElementById(array[i]).value;
			key += array[i] + "=" + value + "&";
		}

		key += "userSex=" + getRadioValue("userSex");

		var request = getXmlHttp();
		alert(key);

		request.open("POST", "user/add.s", true);
		// 如果使用的是post方式带数据，那么要添加头，说明提交的数据类型是一个经过url编码的form表单数据
		request.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		request.onreadystatechange = function() {
			if (request.readyState == 4 && request.status == 200) {
				var parent = document.getElementById("content_container");
				var childs = parent.childNodes;
				for (var i = 0; i < childs.length; i++) {
					parent.removeChild(childs[i]);
				}
				document.getElementById("content_container").innerHTML = request.responseText;
				document.getElementById("errorMsg").innerHTML = "添加成功";
				$('#myModal').modal("show");
			}
		}

		request.send(key);
	}

	function check() {
		var prePwd = document.getElementById("userPwd").value;
		var nextPwd = document.getElementById("userPwdConfirm").value;
		if (prePwd != nextPwd) {
			alert("前后输入的密码不一致");
			return false;
		}

		return true;

	}

	function checkUserExist() {
		var userAccount = document.getElementById("userAccount").value;
		var pageUrl = "user/check.s?userAccount=" + userAccount;

		var request = getXmlHttp();
		request.open("GET", pageUrl, true);
		request.onreadystatechange = function() {
			if (request.readyState == 4 && request.status == 200) {
				document.getElementById("checkTip").innerHTML = request.responseText;
				canRegister = request.responseText == "";
			}
		}

		request.send();
	}
</script>
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
				<li class="active">添加用户</li>
			</ul>
			<!-- .breadcrumb -->
		</div>

		<div class="page-content">

			<div class="modal-open">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="form-horizontal">

							<div class="modal-header center blue">
								<h2 class="modal-title">添加用户</h2>
							</div>

							<div class="modal-body">

								<div class="form-group">
									<label class="col-sm-3 control-label">用户头像地址：</label>
									<div class="col-sm-9">
										<input type="text" required="required" class="form-control"
											name="userHeadUrl" id="userHeadUrl" placeholder="请输入用户头像地址" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">用户名：</label>
									<div class="col-sm-9">
										<input type="text" required="required"
											onblur="checkUserExist()" pattern="^[a-zA-Z]\w{2,14}$"
											class="form-control" name="userAccount" id="userAccount"
											placeholder="请输入3-15位的字符" /> <font id="checkTip"
											color="#F53D3D" size="2"></font>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">密码：</label>
									<div class="col-sm-9">
										<input type="password" required="required" pattern="^.{3,10}$"
											class="form-control" name="userPwd" id="userPwd"
											placeholder="请输入3-10位的密码" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">密码确认：</label>
									<div class="col-sm-9">
										<input type="password" required="required" pattern="^.{3,10}$"
											class="form-control" name="userPwdConfirm"
											id="userPwdConfirm" placeholder="请输入3-10位的密码" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">姓名：</label>
									<div class="col-sm-9">
										<input type="text" required="required"
											pattern="^[\u4e00-\u9fa5]{2,4}$" class="form-control"
											id="userRealName" name="userRealName"
											placeholder="请输入2-4位的中文姓名" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期：</label>
									<div class="col-sm-9">
										<input type="text" required="required"
											pattern="^\d{4}-\d{1,2}-\d{1,2}$" class="form-control"
											id="userBirth" name="userBirth"
											placeholder="请输入yyyy-MM-dd格式的日期" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别：</label>
									<div class="col-sm-9">
										<label class="checkbox-inline"> <input type="radio"
											value="1" name="userSex" checked="checked" /> 男
										</label> <label class="checkbox-inline"> <input type="radio"
											value="0" name="userSex" /> 女
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id=userPhone
											name="userPhone" placeholder="请输入电话" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">qq：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userQQ"
											name="userQQ" placeholder="请输入qq号码" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="userEmail"
											name="userEmail" placeholder="请输入邮箱" />
									</div>
								</div>


							</div>
							<div class="modal-footer">
								<button onclick="confirmAddUser()" class="btn btn-primary">添加用户</button>
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