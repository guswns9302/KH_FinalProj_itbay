<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<jsp:include page="/WEB-INF/views/module/default_js_css.jsp"
	flush="false" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
	<header>
		<c:choose>
			<c:when test="${loginMember.getUsername() eq '마스터' }">
				<jsp:include page="/WEB-INF/views/module/top-navigation_master.jsp" flush="false" >
					<jsp:param name="login" value="${sessionScope.login }" />
				</jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
					<jsp:param name="login" value="${sessionScope.login }" />
				</jsp:include>
			</c:otherwise>
		</c:choose>
	</header>
	<section class="container">
		<div style="margin: 10px;">
			<h2>회원가입</h2>
			<div class="card border-dark" style="margin: 50px;">
				<div class="card-body text-dark">

					<form action="/join" method="post">

						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="nickname">아이디</label>
							<div class="col-sm-8">
								<input class="form-control" id="nickname" type="email"
									name="nickname" placeholder="ID (E-mail)" required>
								<div class="check_font" id="id_check"></div>
							</div>
						</div>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="pw">비밀번호</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" id="user_pw" name="pw"
									placeholder="Password" required>
							</div>
						</div>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="pw">비밀번호 확인</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" id="user_pw_chk"
									name="pw_check" placeholder="Confirm Password" required>
							</div>
							<button type="button" onclick="test()" value="확인"
								class="btn btn-secondary text-uppercase" style="width: 100px;">확인</button>


						</div>
						<script type="text/javascript">
							function test() {
								var p1 = document.getElementById('user_pw').value;
								var p2 = document.getElementById('user_pw_chk').value;
								var pattern1 = /[0-9]/;
								var pattern2 = /[a-zA-Z]/;
								var pattern3 = /[~!@\#$%<>^&*]/; // 원하는 특수문자 추가 제거

								if (!pattern1.test(p1) || !pattern2.test(p1)
										|| !pattern3.test(p1)
										|| p1.length<8||p1.length>50) {
									alert("영문+숫자+특수기호 8자리 이상으로 구성하여야 합니다.");
									return false;
								}
								if (p1 != p2) {
									alert("비밀번호가 일치 하지 않습니다");
									return false;
								} else {
									alert("비밀번호가 일치합니다");
									return true;
								}
							}
						</script>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="birth">생년월일</label>
							<div class="col-sm-8">
								<input class="form-control" type="date" name="birth" required>
							</div>
						</div>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="email_address">Email</label>
							<div class="col-sm-8">
								<input class="form-control" type="email" name="email_address"
									placeholder="E-mail" required>
							</div>
						</div>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="username">이름</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" name="username"
									placeholder="Name" required>
							</div>
						</div>

						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="phone">전화번호</label>
							<div class="col-sm-8">
								<input class="form-control" type="tel" name="phone"
									placeholder="'-' 없이 번호만 입력해주세요" required>
							</div>
						</div>
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="address">주소</label>
							<div class="col-sm-8">
								<input class="form-control" id="address" type="text"
									name="address" placeholder="Address" required>
							</div>
						</div>
						<div class="form-group" style="text-align: center;">
							<button type="submit" class="btn btn-secondary text-uppercase"
								style="width: 183px;">가입 완료</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
	$("#nickname")
			.blur(
					function() {
						var user_id = $('#nickname').val();
						$
								.ajax({
									url : '${pageContext.request.contextPath}/user/idCheck?nickname='
											+ user_id,
									type : 'get',
									data : user_id,
									success : function(data) {
										console.log("1 = 중복o / 0 = 중복x : "
												+ data);

										if (data >= 1) {
											$("#id_check").text("사용중인 아이디입니다.");
											$("#id_check").css("color", "red");
											$("#reg_submit").attr("disabled",
													true);

										} else {
											if (user_id == "") {
												$('#id_check').text(
														'아이디를 입력해주세요.');
												$('#id_check').css('color',
														'red');
												$("#reg_submit").attr(
														"disabled", true);

											}
										}
									},
									error : function() {
										console.log("실패");
									}
								});
					});
</script>
</html>