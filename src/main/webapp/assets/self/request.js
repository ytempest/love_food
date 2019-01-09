//1. 捕捉到键盘弹起的事件 
//在文档准备就绪的时候，对某一个元素进行onkeyup事件监听
function getXmlHttp() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	return xmlhttp;
}

/**
 * GET请求处理
 */
function ajaxGetRequest(pageUrl) {

	// 1、创建ajax对象
	var request = getXmlHttp();

	// 2、构架请求
	request.open("GET", pageUrl, true);

	// 3、设置监听
	request.onreadystatechange = function() {

		// 前半段表示 已经能够正常处理。 再判断状态码是否是200
		if (request.readyState == 4 && request.status == 200) {
			var parent = document.getElementById("content_container");
			var childs = parent.childNodes;
			for (var i = 0; i < childs.length; i++) {
				parent.removeChild(childs[i]);
			}

			document.getElementById("content_container").innerHTML = request.responseText;

		}
	}

	// 4、发送请求
	request.send();
}

/**
 * POST请求处理
 */
/*
 * function postRequest(pageUrl) { var request = getXmlHttp();
 * 
 * request.open("POST", pageUrl, true); //
 * 如果使用的是post方式带数据，那么要添加头，说明提交的数据类型是一个经过url编码的form表单数据
 * request.setRequestHeader("Content-type",
 * "application/x-www-form-urlencoded");
 * 
 * request.onreadystatechange = function() { if (request.readyState == 4 &&
 * request.status == 200) { document.getElementById("errorMsg").innerHTML =
 * request.responseText; $('#myModal').modal("show"); } }
 * 
 * var admin = document.getElementById("admin").value; var pwd =
 * document.getElementById("pwd").value;
 * 
 * request.send("admin=" + admin + "&pwd=" + pwd);
 *  }
 */