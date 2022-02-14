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
	<title>mileage</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="login" value="${sessionScope.login }" />
		</jsp:include>
	</header>
	
	<p>${loginMember.getUsername() }님</p>
	<p>사용가능 마일리지</p>
	<p>${mileage.getMileage_amount() } point</p>
	<form action="/mileage/charge" method="post">
		<div class="row">
			<div class="col-sm-5">
				<div class="form-floating mb-3 mt-3">
					<input type="number" class="form-control" name="mileage_amount">
					<label for="email">충전 금액 입력</label>
				</div>
			</div>
			<div class="col-sm-6" id="button_modify">
				<button type="submit" class="btn btn-secondary text-uppercase">마일리지 충전</button>
			</div>
		</div>
	</form>
	<a href="/mileage_charging_history">마일리지 충전내역</a>
	<a href="/purchase_history">마일리지 사용내역</a>
	
	
</body>
</html>