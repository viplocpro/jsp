
	var objDiv = document.getElementsByClassName("chat-content")[0];
	objDiv.scrollTop = objDiv.scrollHeight;

	function Scroll(){
		objDiv.scrollTop = objDiv.scrollHeight;
		var newmsg = document.getElementsByClassName("new-message")[0];
		newmsg.style.zIndex=-5;
		newmsg.style.display="none";
	}
	
	function DisplayNewMsg(){
		var newmsg = document.getElementsByClassName("new-message")[0];
		newmsg.style.zIndex=5;
		newmsg.style.display="block";
	}
	