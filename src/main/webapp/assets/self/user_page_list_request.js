
function loadDataToTable(pageInfo) {
	var data = pageInfo.list;
	
	var tbody = document.getElementById("list_table");
	var str = "";
	
	for(i in data){
		var index = (parseInt(pageInfo.currentPage) -1) *parseInt(pageInfo.pageSize) + parseInt(i) + 1;
		
        str += "<tr>" +
        	"<td>" + index + "</td>" +
        	"<td>" + data[i].userAccount + "</td>" + 
        	"<td>" + (data[i].userSex != null ? data[i].userSex : '暂无') + "</td>" + 
        	"<td>" + (data[i].userBirth == null ? '暂无' : formatDate(data[i].userBirth, 'yyyy-MM-dd')) + "</td>" + 
        	"<td>" + (data[i].userPhone != null ? data[i].userPhone : '暂无') + "</td>" + 
        	"<td>" + (data[i].userQQ != null ? data[i].userQQ : '暂无') + "</td>" + 
        	"<td>" + (data[i].userEmail != null ? data[i].userEmail : '暂无') + "</td>" + 
        	"<td>" + (data[i].userRegisterTime == null ? '暂无' : formatDate(data[i].userRegisterTime, 'yyyy-MM-dd hh:mm:ss')) + "</td>" + 
        	"<td>" + (data[i].userStatus == 0 ? '正常' : '锁定') + "</td>";
        
        str += "<td><div class=\"visible-md visible-lg hidden-sm hidden-xs action-buttons\">" +
		"<a class=\"blue\" href=\"javascript:void(0);\"	onclick=\"ajaxGetRequest('user/preview.s?account=" + data[i].userAccount + "')\">" +
		"<i class=\"icon-zoom-in bigger-130\"></i>" +
		"</a> <a class=\"green\" href=\"javascript:void(0);\" onclick=\"ajaxGetRequest('user/update.s?account=" + data[i].userAccount + "')\">" +
		"<i class=\"icon-pencil bigger-130\"></i></a></div></td>" +
		"</tr>";			
	}
	
	tbody.innerHTML = str;

}


function loadData(pageInfo,pageNo,key,type) {
	
	loadDataToTable(pageInfo);

	// 只要不是页码点击事件都执行下面的逻辑
	if(type != "singleClick"){
		
		var data = pageInfo.list;
		var ulTag = document.getElementById("page_ul");
		
		// 计算页码的开始位置
		var start = pageNo;		
		if(pageNo%10!=1){
			start = pageNo - pageNo%10+1;
		}else if(pageNo%10 ==0){
			start = pageNo -10 + 1; 
		}
		
		// 计算页码结束的位置
		var end = start + 9;
		if(end > pageInfo.pageCount){
			end = pageInfo.pageCount;
		}		
		
		
		// 创建所有页码的<li>标签并加载到页面
		var str = "";
		
		var prevClickStr = "prevPageClick("+(pageNo-10<1?-1:pageNo-10) + (key!=null?",'"+key + "'":"") + ")";
		
		str += "<li class=\"prev\"><a href=\"javascript:void(0);\" onclick=\"" + prevClickStr +"\"> <i class=\"icon-double-angle-left\"></i></a></li>";
		
		for(var i = start; i<=end; i++ ){
			var pageClickStr = "pageClick("+i+ (key!=null?",'"+key + "'":"") +")";
			str += "<li " + (i==pageNo?"class=\"active\"":"") +"><a href=\"javascript:void(0);\" onclick=\""+ pageClickStr +"\">"+i+"</a></li>";
		}
		var nextClickStr = "nextPageClick("+(end==pageInfo.pageCount?-1:end+1)+ (key!=null?",'"+key + "'":"") +")";
		str += "<li class=\"next\"><a href=\"javascript:void(0);\"onclick=\""+ nextClickStr+"\"><i class=\"icon-double-angle-right\"></i></a></li>";

		ulTag.innerHTML=str;
		
		// 打印页码和记录总量到页面中
		var recordCountTag = document.getElementById("recordCount");
		recordCountTag.innerText = "一共" + pageInfo.pageCount+"页，" + pageInfo.total+"个记录，跳转到";
		
		// 为跳转页面按钮添加点击事件
		var skipPageClickStr = "skipPage("+"1,"+ pageInfo.pageCount +(key!=null?",'"+key + "'":"") +")";
		document.getElementById("skipBtn").innerHTML = "<button class=\"btn btn-xs blue\" style=\"margin-bottom: 3px\" onclick=\"" + skipPageClickStr +"\">跳转</button>";
	}
}

/**
 * 前一页的点击按钮
 */
function prevPageClick(pageNo){
	nextPageClick(pageNo);
}

/**
 * 下一页的点击按钮
 */
function prevPageClick(pageNo,key){
	nextPageClick(pageNo,key);
}


function requestPage(pageNo,key,type) {
	var pageUrl = "user/pageList.s?page=" + pageNo;
	// 如果有模糊搜索关键词，则拼接url
	if(key!=null){
		pageUrl = pageUrl + "&key=" + key;
	}
	
	$.ajax({
		type: "get",
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		url: pageUrl ,
		success: function(pageInfo) {
			loadData(pageInfo,pageNo,key,type);
		},
		error: function() {
			alert("获取用户数据失败")
		}
	});
}

function nextPageClick(pageNo){
	nextPageClick(pageNo,null);
}

function nextPageClick(pageNo,key){
	
	if(pageNo<0){
		return;
	}
	
	requestPage(pageNo,key,"next");
}

function skipPage(start,end,key) {
	var pageNo = document.getElementById("pageInput").value;
	if(pageNo==null || pageNo==""){
		return;
	}
	
	if(end == 0){
		alert("没有记录");
		return;
	}
	
	if(pageNo<start || pageNo>end){
		alert("您输出的页码超出范围");
		return;
	}
	
	nextPageClick(pageNo,key);
}


function pageClick(pageNo) {
	pageClick(pageNo,null);	
}

function pageClick(pageNo,key) {
	
	if(pageNo<0){
		return;
	}

	// 将当前点击的li标签设为选中状态
	var ulTag = document.getElementById("page_ul");
	var liList = ulTag.getElementsByTagName("li");
	for(var i = 0; i<liList.length; i++ ){
		liList[i].classList.remove("active");
		var aTag = liList[i].getElementsByTagName("a")[0].innerText;
		if(aTag==pageNo){
			liList[i].classList.add("active");
		}
	}
	
	requestPage(pageNo,key,"singleClick");
}

function fuzzySearch() {
	// 获取进行模糊搜索的关键词
	var key = document.getElementById("key").value;

	// 如果没有关键词就默认加载第一页的所有数据
	if (key == "") {
		requestPage(1,null,"showList");
		return;
	}

	requestPage(1,key,"fuzzySearch");
}


