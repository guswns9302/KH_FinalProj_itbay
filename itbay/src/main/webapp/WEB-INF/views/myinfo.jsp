<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<title>profile</title>
</head>

<c:url var="ajax_url" value="/myinfo" />
<script type="text/javascript">
	function profile_img_upload(){
		var img_input = document.getElementById("upload");
		img_input.click();
	}
	function img_modify(){
		// 새로운 버튼 생성
		var submit_btn = document.createElement("button");
		var node = document.createTextNode("Complete");
		var upload_btn = document.createElement("button");
		var upload_node = document.createTextNode("Select IMG");
		// 버튼에 텍스트 추가하기
		submit_btn.appendChild(node);
		upload_btn.appendChild(upload_node);
		// 버튼에 속성 추가
		submit_btn.setAttribute("type","submit");
		submit_btn.setAttribute("class","btn btn-secondary text-uppercase");
		upload_btn.setAttribute("type","button");
		upload_btn.setAttribute("style","margin-right:20px;");
		upload_btn.setAttribute("class","btn btn-secondary text-uppercase");
		upload_btn.setAttribute("onclick","profile_img_upload();");
		// div에 버튼 추가하기
		var div = document.getElementById("img_modify");
		div.appendChild(upload_btn);
		div.appendChild(submit_btn);
		// 기존 버튼 삭제
		var modify_btn = document.getElementById("imgmodify");
		modify_btn.remove();
	}
	
	function modify_profile(){
		document.getElementById("phone").readOnly = false;
		document.getElementById("phone").value = "";
		document.getElementById("address").readOnly = false;
		document.getElementById("address").value = "";
		
		// 새로운 버튼 생성
		var submit_btn = document.createElement("button");
		var node = document.createTextNode("Complete");
		// 버튼에 텍스트 추가하기
		submit_btn.appendChild(node);
		// 버튼에 속성 추가
		submit_btn.setAttribute("type","submit");
		submit_btn.setAttribute("class","btn btn-secondary text-uppercase");
		submit_btn.setAttribute("onclick","sendModifyProfile();");
		// div에 버튼 추가하기
		var div = document.getElementById("button_modify");
		div.appendChild(submit_btn);
		
		// 기존 버튼 삭제
		var modify_btn = document.getElementById("modify");
		modify_btn.remove();
	}
	
	function sendModifyProfile(){
		var phone = $("#phone").val();
		var address = $("#address").val();
		
		$.ajax({
			url: "${ajax_url}",
			type: "POST",
			dataType: "json",
			data: {
				phone : phone,
				address : address
			},
			success: function(data){
				if(data.status === "success"){
					alert(data.message);
					location.href = "/myinfo";
				}
				else{
					alert(data.message);
					location.href = "/myinfo";
				}
			} 
		});
	}
</script>

<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="login" value="${sessionScope.login }" />
		</jsp:include>
	</header>

	<div class="container" style="margin-top:-50px;">
		<div class="row">
			<div class="col-sm-6">
			<section class="container p-5 my-5">
				<div class="card">
						<div>
							<c:choose>
								<c:when test="${loginMember.getSocial_login() eq 'Y'.charAt(0) }">
									<div class="card-body">
									</div>
									<div style="text-align:center;">
										<p>안녕하세요 ${loginMember.getUsername() }님 </p>
										<img src="${loginMember_img}" class="img-thumbnail" alt="Cinque Terre" width="400" height="400"/>
									</div>
								</c:when>
								<c:otherwise>
								  	<div style="text-align:center;">
								  		<p>안녕하세요 ${loginMember.getUsername() }님 </p>
										<form action="/myinfo/profileImg" method="post" enctype="multipart/form-data">
											<div id="img_modify">
												<button type="button" id="imgmodify" class="btn btn-secondary text-uppercase" onclick="img_modify();">Modify</button>
											</div>
											<input  id="upload" type="file" name="file" multiple style="visibility:hidden;"/>
										</form>
										<img src="resources/img/${loginMember.getImg_name() }" class="img-thumbnail" alt="Cinque Terre" width="350" height="350">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</section>
			</div>
			
			<div class="col-sm-6">
				<section class="container p-5 my-5">
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control"  id="nickname" name="nickname" value="${loginMember.getNickname() }" readonly>
						<label for="email">Login Id</label>
					</div>
					<c:choose>
						<c:when test="${loginMember.getSocial_login() eq 'N'.charAt(0) }">
							<div class="form-floating mt-3 mb-3">
								<input type="password" class="form-control" id="pw" name="pw" value="${loginMember.getPw() }" readonly>
								<label for="pwd">Password</label>
							</div>
						</c:when>
					</c:choose>
					<div class="form-floating mt-3 mb-3">
						<input type="text" class="form-control" id="username" name="username" value="${loginMember.getUsername() }" readonly>
						<label for="text">Username</label>
					</div>
					<div class="form-floating mt-3 mb-3">
						<input type="text" class="form-control" id="phone" name="phone" value="${loginMember.getPhone() }" readonly>
						<label for="text">Phone</label>
					</div>
					<div class="form-floating mt-3 mb-3">
						<input type="text" class="form-control" id="email_address" name="email_address" value="${loginMember.getEmail_address() }" readonly>
						<label for="text">Email-Address</label>
					</div>
					<div class="form-floating mt-3 mb-3">
						<input type="text" class="form-control" id="address" name="address" value="${loginMember.getAddress() }" readonly>
						<label for="text">Address</label>
					</div>
					<div id="button_modify"style="display: inline-block">
						<button type="button" class="btn btn-secondary text-uppercase" id="modify" onclick="modify_profile();">Modify</button>
					</div>
					<div style="display: inline-block">
						<a href="/deleteMember"><button type="button" class="btn btn-secondary text-uppercase" id="deleteMember">회원 탈퇴</button></a>
					</div>
				</section>
			</div>
		</div>
	</div>
	
</body>
</html>