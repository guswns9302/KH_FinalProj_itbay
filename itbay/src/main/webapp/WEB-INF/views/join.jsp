<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입</title>
	<jsp:include page="/WEB-INF/views/module/default_js_css.jsp" flush="false" />
</head>

<body>

<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="logined" value="${empty sessionScope.logined ? false : true }" />
			<jsp:param name="logined" value="${sessionScope.logined}" />
		</jsp:include>
</header>
<section class="container">
		<h2>회원가입</h2>
		<div class="card border-dark">
			<div class="card-body text-dark">

				<form action="/join" method="post">
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="nickname">아이디</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="nickname" value="${accountVO.nickname}" required>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="pw">비밀번호</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" id="password1" name="pw" value="${accountVO.pw1}" required>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="pw">비밀번호 확인</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" id="password2" name="pw_check" value="${accountVO.pw2}" required>
						</div>
						<div class="form-group" style="text-align: center;">
						    <input type="button" onclick="test()" value="확인">
						</div>
					
					</div>
					    <script type="text/javascript">
					    function test() {
					      var p1 = document.getElementById('password1').value;
					      var p2 = document.getElementById('password2').value;
					      var pattern1 = /[0-9]/;
					      var pattern2 = /[a-zA-Z]/;
					      var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거


					      if(!pattern1.test(p1)||!pattern2.test(p1)||!pattern3.test(p1)||p1.length<8||p1.length>50){
					            alert("영문+숫자+특수기호 8자리 이상으로 구성하여야 합니다.");
					            return false;
					        }
					      if( p1 != p2 ) {
					        alert("비밀번호가 일치 하지 않습니다");
					        return false;
					      } else{
					        alert("비밀번호가 일치합니다");
					        return true;
					      }
					    }
					  </script>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="email_address">Email</label>
						<div class="col-sm-10">
							<input class="form-control" type="email" name="email_address" value="${accountVO.email}" required>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="username">이름</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" name="username" value="${accountVO.username}" required>
						</div>
					</div>
					<%-- <div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="birth">생년월일</label>
						<div class="col-sm-10">
							<input class="form-control" type="date" name="birth" value="${accountVO.birth}" required>
						</div>
					</div> --%>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="phone">전화번호</label>
						<div class="col-sm-10">
							<input class="form-control" type="tel" name="phone" value="${accountVO.phone}" required>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="address">주소</label>
						<div class="col-sm-10">
							<input class="form-control"  id="address" type="text" name="address" value="${accountVO.address}" required>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button type="submit" style="text-align: center;" class="btn btn-primary">가입 완료</button>
					</div>
				</form>
			</div>
		</div>
</section>

</body>
</html>