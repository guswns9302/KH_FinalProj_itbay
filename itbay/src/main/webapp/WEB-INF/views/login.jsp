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
	<form action="/login" method="post">
	 	<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		<div class="form-floating mb-3 mt-3">
			<input type="text" class="form-control" id="nickname" placeholder="Enter email" name="nickname">
			<label for="email">Email</label>
		</div>
		<div class="form-floating mt-3 mb-3">
			<input type="text" class="form-control" id="pw" placeholder="Enter password" name="pw">
			<label for="pwd">Password</label>
		</div>
		<button type="submit" class="btn btn-primary">login</button>
		<a href="https://kauth.kakao.com/oauth/authorize?
			client_id=ea71971929c14c8aef7a4bef54e9b082&
			redirect_uri=http://localhost/kakao&
			response_type=code">
		<img src="resources/icon/kakao_login_medium_narrow.png">
	</a>
	</form>
</div>

</body>
</html>
