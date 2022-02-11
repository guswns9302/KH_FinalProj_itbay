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
							<input class="form-control" type="password" name="pw" value="${accountVO.password}" required>
						</div>
					</div>
					<div class="row mb-3">
						<label class="col-sm-2 col-form-label" for="pw">비밀번호 확인</label>
						<div class="col-sm-10">
							<input class="form-control" type="password" name="pw_check" value="${accountVO.password}" required>
						</div>
					</div>
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
					<div class="row mb-3">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary acttive">
								<input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
							</label>
							<label class="btn btn-primary ">
								<input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
							</label>
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