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

<c:url var="ajax_url" value="/purchase_myinfo" />
<script type="text/javascript">
	function modify_profile(){
		document.getElementById("phone").readOnly = false;
		document.getElementById("phone").value = "";
		document.getElementById("address").readOnly = false;
		document.getElementById("address").value = "";
		
		// 새로운 버튼 생성
		var submit_btn = document.createElement("button");
		var node = document.createTextNode("수정완료");
		// 버튼에 텍스트 추가하기
		submit_btn.appendChild(node);
		// 버튼에 속성 추가
		submit_btn.setAttribute("type","submit");
		submit_btn.setAttribute("class","btn btn-secondary text-uppercase");
		submit_btn.setAttribute("style","width: 183px;");
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
					location.href = "/purchase_product?product_id=" + ${productInfo.getId()};
				}
				else{
					alert(data.message);
					location.href = "/purchase_product?product_id=" + ${productInfo.getId()};
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
						  	<div style="text-align:center;">
								<img src="/resources/img/${productInfo.img_name}" class="img-thumbnail" onerror="this.src='resources/icon/itbay_logo.png'" alt="Cinque Terre">
							</div>
							<div class="card-body">
								<div class="container">
									<div class="form-floating mb-3 mt-3">
										<input type="text" class="form-control" name="subject" value="${productInfo.getSubject() }" readonly>
										<label for="subject">Product Subject</label>
									</div>
									<div class="form-floating mb-3 mt-3">
										<input type="text" class="form-control" name="subject" value="${productInfo.getPrice() }" readonly>
										<label for="price">Price</label>
									</div>
								</div>
						  	</div>
						</div>
					</div>
				</section>
			</div>
			
			<div class="col-sm-6">
				<section class="container p-5 my-5" style="text-align:center;">
				 	<h1 class="h3 mb-3 fw-normal">구매자 정보 입력</h1>
				 	<div id="button_modify" style="text-align:right;">
						<button type="button" class="btn btn-secondary text-uppercase" style="width: 183px;" id="modify" onclick="modify_profile();">Modify</button>
					</div>
					<form action="/purchase_product_info?product_id=${productInfo.getId() }&price=${productInfo.getPrice() } " method="post">
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="nickname" name="nickname" value="${loginMember.getNickname() }" readonly>
							<label for="UserID">UserID</label>
						</div>
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
						<div style="text-align:right;">
							<button type="submit" class="btn btn-secondary text-uppercase" style="width: 183px;">Buy</button>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>