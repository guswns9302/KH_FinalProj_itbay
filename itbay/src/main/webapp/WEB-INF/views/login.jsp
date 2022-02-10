<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
</head>
	<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="login" value="${sessionScope.login }" />
		</jsp:include>
	</header>
	
	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style = "padding-top: 20px;">
				<form action="/login" method="post">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control" id="nickname" placeholder="Enter email" name="nickname" maxlength="20">
						<label for="email">Email</label>
					</div>
					<div class="form-floating mt-3 mb-3">
						<input type="text" class="form-control" id="pw" placeholder="Enter password" name="pw" maxlength="20">
						<label for="pwd">Password</label>
					</div>
					<div class="form-floating mt-3 mb-3">
					<button type="submit" class="btn btn-primary">로그인</button>
					</div>
										
					<a href="https://kauth.kakao.com/oauth/authorize?
					client_id=ea71971929c14c8aef7a4bef54e9b082&
					redirect_uri=http://localhost/kakao&
					response_type=code">
						<img src="resources/icon/kakao_login_medium_narrow.png">
					</a>
				</form>
				<form action="/join" method="get">
					<div class="form-floating mt-3 mb-3">
						<button type="submit" class="btn btn-primary" >회원가입</button>
					</div>	
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
		</div>
	</body>
</html>
