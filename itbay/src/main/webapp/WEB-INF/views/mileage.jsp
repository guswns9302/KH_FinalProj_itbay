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
	
	<section class="container p-5 my-5">
		<div class="row">
			<div class="col-sm-8">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link active" data-bs-toggle="tab" href="#home">Charge Mileage</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-bs-toggle="tab" href="#menu1">Charge History</a>
					</li>
				</ul>
				
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane container active" id="home">
						<form action="/mileage/charge" method="post">
							<div class="form-floating mb-3 mt-3">
								<input type="number" class="form-control" name="mileage_amount">
								<label for="email">Enter the recharge amount</label>
							</div>
							<div class="col-sm-6" id="button_modify">
								<button type="submit" class="btn btn-secondary text-uppercase">마일리지 충전</button>
							</div>
						</form>
					</div>
					<div class="tab-pane container fade" id="menu1">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Recharge Amount</th>
									<th>Recharge Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${history_mileage }">
									<tr>
										<td>${data.getRownum() }</td>
										<td>${data.getMileage_amount() } Point</td>
										<td>${data.getMileage_date() }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card">
				<div class="card-body">
					<h4 class="card-title">Hello ${loginMember.getUsername() }</h4>
					<p class="card-text">Amount of Miles Owned : ${mileage.getMileage_amount() } point</p>
					<a href="/myinfo" class="btn btn-secondary text-uppercase">See Profile</a>
				</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>