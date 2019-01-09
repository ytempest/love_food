<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>404错误页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!-- fonts -->

<link rel="stylesheet"
	href="http//fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

</head>

<body>
	<div class="main-container" id="main-container">
		<div style="padding: 2%">
			<h1 class="grey lighter smaller">
				<span class="blue bigger-125"> <i class="icon-sitemap"></i>
					404
				</span> Page Not Found
			</h1>

			<hr />
			<h3 class="lighter smaller">We looked everywhere but we couldn't
				find it!</h3>

			<div>
				<form class="form-search">
					<span class="input-icon align-middle"> <i
						class="icon-search"></i> <input type="text" class="search-query"
						placeholder="Give it a search..." />
					</span>
					<button class="btn btn-sm" onclick="return false;">Go!</button>
				</form>

				<div class="space"></div>
				<h4 class="smaller">Try one of the following:</h4>

				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="icon-hand-right blue"></i> Re-check the url for
						typos</li>

					<li><i class="icon-hand-right blue"></i> Read the faq</li>

					<li><i class="icon-hand-right blue"></i> Tell us about it</li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>
