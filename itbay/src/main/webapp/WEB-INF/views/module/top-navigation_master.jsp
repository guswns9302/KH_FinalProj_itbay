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
							<a href="#" onclick="getChatRoom();" data-bs-toggle="modal" data-bs-target="#myModal" style="margin-right:50px;"><img src="resources/icon/chat_logo.png" width="100" height="50"></a>
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
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="/myinfo" role="button" data-bs-toggle="dropdown">Admin</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="/member_list">Member List</a></li>
									<li><a class="dropdown-item" href="/sales_history">Sales History</a></li>
								</ul>
							</li>
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
				<h4 class="modal-title">Select Chat Room</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			<!-- Modal body -->
			<div class="modal-body" id="chatting">
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<c:url var="chatroom_ajax_url" value="/getChatroom" />
<c:url var="chat_ajax_url" value="/liveChat" />
<c:url var="send_ajax_url" value="/insertMsg" />
<script type="text/javascript">
	function getChatRoom(){
		$.ajax({
			url: "${chatroom_ajax_url}",
			type: "POST",
			dataType: "json",
			data:{
			},
			success: function(data){
				if(data.status === "success"){
					var chat_room = data.roomList;
					if($('#inner_div').length == 0){
						// ????????? div ??????
						var div_chatRoomList = document.createElement("div");
						// div??? ?????? ??????
						div_chatRoomList.setAttribute("id","chatRoomList");
						// div??? div ??????
						var div_chatting = document.getElementById("chatting");
						div_chatting.appendChild(div_chatRoomList);
						
						for(list in chat_room){
							// ????????? div ??????
							var inner_div = document.createElement("div");
							// div??? ?????? ??????
							inner_div.setAttribute("id","inner_div");
							// div??? div ??????
							var div = document.getElementById("chatRoomList");
							div.appendChild(inner_div);
							
							// ????????? ?????? ??????
							var btn = document.createElement("button");
							var node = document.createTextNode("????????? : " + chat_room[list].MEMBERS_NICKNAME);
							// ????????? ????????? ????????????
							btn.appendChild(node);
							// ????????? ?????? ??????
							btn.setAttribute("type","button");
							btn.setAttribute("value",chat_room[list]);
							btn.setAttribute("name","chatRoomNum");
							btn.setAttribute("class","btn btn-secondary text-uppercase");
							btn.setAttribute("onclick","connectChat("+chat_room[list].ROOMNUM+");");
							// inner_div??? ?????? ????????????
							inner_div.appendChild(btn);
						}
					}
				}
			}
		});
	}
	
	var ws;
	function connectChat(roomNum){
		ws = new WebSocket("ws://localhost:80/chat/"+roomNum);
		// console.log("${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}");
		
		// ?????? ?????? ??????
		var btn_roomlist = document.getElementById("chatRoomList");
		btn_roomlist.remove();
		
		// ????????? textarea ??????
		if($('#messageArea').length == 0){
			// div ??????
			var div_chatRoom = document.createElement("div");
			// div??? ?????? ??????
			div_chatRoom.setAttribute("id","chatRoom");
			// div??? div ??????
			var div_chatting = document.getElementById("chatting");
			div_chatting.appendChild(div_chatRoom);
			
			var area = document.createElement("textarea");
			// textarea??? ?????? ??????
			area.setAttribute("id","messageArea");
			area.setAttribute("cols","60");
			area.setAttribute("rows","10");
			area.setAttribute("readonly","true");
			// div??? ?????? ????????????
			var div = document.getElementById("chatRoom");
			div.appendChild(area);
			
			// ????????? input ??????
			var input = document.createElement("input");
			// input??? ?????? ??????
			input.setAttribute("type","text");
			input.setAttribute("id","message");
			input.setAttribute("onkeyup","enterkey()");
			// inner_div??? ?????? ????????????
			div.appendChild(input);
			
			// ?????? ?????? ??????
			var btn = document.createElement("button");
			var node = document.createTextNode("??????");
			// ????????? ????????? ????????????
			btn.appendChild(node);
			// ????????? ?????? ??????
			btn.setAttribute("type","button");
			btn.setAttribute("id","btn_send");
			btn.setAttribute("onclick","sendMSG(message.value);");
			// inner_div??? ?????? ????????????
			div.appendChild(btn);
			
			// ????????? ????????? ?????? ??????
			var btn_out = document.createElement("button");
			var node = document.createTextNode("?????? ?????????");
			// ????????? ????????? ????????????
			btn_out.appendChild(node);
			// ????????? ?????? ??????
			btn_out.setAttribute("type","button");
			btn_out.setAttribute("onclick","closeChat();");
			// inner_div??? ?????? ????????????
			div.appendChild(btn_out);
		}
		
		ws.onopen = function(){
			$.ajax({
				url: "${chat_ajax_url}",
				type: "POST",
				dataType: "json",
				data:{
					roomNum : roomNum
				},
				success: function(data){
					if(data.status === "success"){
						var msg_data = data.chatting_data;
						if($('#messageArea').val() === ""){
							for(list in msg_data){
								$("#messageArea").append(msg_data[list].MEMBERS_NICKNAME + " : " + msg_data[list].CHAT_CONTENTS + "\n");
								$("#message").val("");
							}
						}
					}
				}
			});
		}
	}
	function enterkey() {
		if (window.event.keyCode == 13) {
	    	// ???????????? ????????? ???
	    	sendMSG(message.value);
	    }
	}
	function sendMSG(message){
		ws.send(message);
		var roomNum = ws.url.substring(20);
		ws.onmessage = function(event){
			// ??????????????? ????????? ?????? ??? ??????
			var msg_data = event.data;
			$("#messageArea").append("${loginMember.getNickname()} : " + msg_data + "\n");
			$("#message").val("");
			
			$.ajax({
				url: "${send_ajax_url}",
				type: "POST",
				dataType: "json",
				data:{
					roomNum : roomNum,
					send_Msg : msg_data
				},
				success: function(data){
					if(data.status === "success"){
						console.log(data.message);
					}
				}
			});
		}
	}
	function closeChat(){
		ws.close();
		ws.onclose = function(event){
			console.log("?????? ??????");
			// ?????? ?????? ??????
			var btn_send = document.getElementById("chatRoom");
			btn_send.remove();
			
			// ????????? div ??????
			var div_chatRoomList = document.createElement("div");
			// div??? ?????? ??????
			div_chatRoomList.setAttribute("id","chatRoomList");
			// div??? div ??????
			var div_chatting = document.getElementById("chatting");
			div_chatting.appendChild(div_chatRoomList);
			
			$.ajax({
				url: "${chatroom_ajax_url}",
				type: "POST",
				dataType: "json",
				data:{
				},
				success: function(data){
					if(data.status === "success"){
						var chat_room = data.roomList;
						if($('#inner_div').length == 0){
							for(list in chat_room){
								// ????????? div ??????
								var inner_div = document.createElement("div");
								// div??? ?????? ??????
								inner_div.setAttribute("id","inner_div");
								// div??? div ??????
								var div = document.getElementById("chatRoomList");
								div.appendChild(inner_div);
								
								// ????????? ?????? ??????
								var btn = document.createElement("button");
								var node = document.createTextNode("????????? ?????? : " + chat_room[list].MEMBERS_NICKNAME);
								// ????????? ????????? ????????????
								btn.appendChild(node);
								// ????????? ?????? ??????
								btn.setAttribute("type","button");
								btn.setAttribute("value",chat_room[list]);
								btn.setAttribute("name","chatRoomNum");
								btn.setAttribute("class","btn btn-secondary text-uppercase");
								btn.setAttribute("onclick","connectChat("+chat_room[list].ROOMNUM+");");
								// inner_div??? ?????? ????????????
								inner_div.appendChild(btn);
							}
						}
					}
				}
			});
		}
	}
</script>
