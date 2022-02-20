<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/module/default_js_css.jsp" flush="false" />
</head>
<script type="text/javascript">
	var ws;
	function connectChat(){
		ws = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chat");
		
		ws.onopen = function(){
			console.log("서버 접속");
		}
	}
	function enterkey() {
		if (window.event.keyCode == 13) {
	    	// 엔터키가 눌렸을 때
	    	sendMSG(message.value);
	    }
	}
	function sendMSG(message){
		ws.send(message);
		ws.onmessage = function(event){
			// 서버로부터 응답을 받을 때 사용
			console.log(event.data);
			var msg_data = event.data;
			$("#messageArea").append(msg_data + "\n");
			$("#message").val("");
		}
	}
	function closeChat(){
		ws.onclose = function(event){
			console.log("연결 해제");
		}
	}
</script>
<body>

	<div>
		<button type="button" onclick="connectChat();">채팅연결</button>
	</div>
	<div>
			<textarea rows="20" cols="60" id="messageArea" readonly></textarea>
		<div>
			<input type="text" id="message" onkeyup="enterkey()">
			<button type="button" onclick="sendMSG(message.value);">보내기</button>
		</div>
	</div>
</body>
</html>