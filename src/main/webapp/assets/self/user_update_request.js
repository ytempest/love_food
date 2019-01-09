function confirmUpdateClick() {
	var url = "user/confirm-update.s?";
	var key = new Array("userAccount", "userHeadUrl", "userPwd",
			"userRegisterTime", "userBirth", "userPhone", "userQQ", "userEmail");
	for (var i = 0; i < key.length; i++) {
		var value = document.getElementById(key[i]).value;
		url += key[i] + "=" + value + "&";
	}

	url += "userStatus=" + getRadioValue("userStatus") + "&";

	url += "userSex=" + getRadioValue("userSex");
	
	ajaxGetRequest(url);
}


function getRadioValue(name) {
	var userSex = document.getElementsByName(name);
	for (var i = 0; i < userSex.length; i++) {
		if (userSex[i].checked == true) {
			return userSex[i].value;
		}
	}
	
}
