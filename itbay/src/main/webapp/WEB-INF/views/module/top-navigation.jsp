<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/"><img src="resources/icon/itbay_logo.png" width="120" height="70"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="navbar-nav me-auto">
				<li class="nav-item">
					<a class="nav-link" href="/">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/notice_board">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/product">Market</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/review_board">Review</a>
				</li>
			</ul>
			<div class="d-flex">
				<ul class="navbar-nav">
					<c:choose>
						<c:when test="${param.login }">
							<!-- Button to Open the Modal -->
							<a href="#" data-bs-toggle="modal" data-bs-target="#myModal" style="margin-right:50px;"><img src="resources/icon/chat_logo.png" width="100" height="50"></a>
							<c:choose>
								<c:when test="${loginMember.getSocial_login() eq 'Y'.charAt(0) }">
									<img src="${loginMember_img}" width="50" height="50" >
								</c:when>
								<c:when test ="${loginMember.getSocial_login() eq 'N'.charAt(0) }">
									<c:choose>
										<c:when test="${empty loginMember.getImg_name()}">
											<img src="resources/img/Basic.jpg" width="50" height="50" >
										</c:when>
										<c:otherwise>
											<img src="resources/img/${loginMember.getImg_name()}" width="50" height="50" >
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>
							
							<c:choose>
								<c:when test="${loginMember.getUsername() eq '마스터' }">
								
									<li class="nav-item dropdown">
										<a class="nav-link dropdown-toggle" href="/myinfo" role="button" data-bs-toggle="dropdown">Admin</a>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="/member_list">Member List</a></li>
											<li><a class="dropdown-item" href="/sales_history">Sales History</a></li>
										</ul>
									</li>
								
								</c:when>
								<c:otherwise>
		
									<li class="nav-item dropdown">
										<a class="nav-link dropdown-toggle" href="/myinfo" role="button" data-bs-toggle="dropdown">Mypage</a>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="/myinfo">Profile</a></li>
											<li><a class="dropdown-item" href="/mileage">Mileage</a></li>
											<li><a class="dropdown-item" href="/cart?members_id=${loginMember.getId()}">Cart</a></li>
											<li><a class="dropdown-item" href="/purchase_history">Purchase History</a></li>
										</ul>
									</li>
		
								</c:otherwise>
							</c:choose>
							
							<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
						</c:when>
						<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="/cart">Cart</a></li>
								<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</nav>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
		<div>
			<button type="button" onclick="connectChat();">채팅연결</button>
		</div>
		<div>
				<textarea rows="10" cols="60" id="messageArea" readonly></textarea>
			<div>
				<input type="text" id="message" onkeyup="enterkey()">
				<button type="button" onclick="sendMSG(message.value);">보내기</button>
			</div>
		</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
<script type="text/javascript">
	var ws;
	function connectChat(){
		ws = new WebSocket("ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chat");
		
		ws.onopen = function(){
			console.log("서버 접속");
			$("#messageArea").append("${loginMember.getNickname()} 접속!\n");
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
