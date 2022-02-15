<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/"><img src="" width="120" height="70"></a>
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
				<li>
					<form class="d-flex">
						<input class="form-control me-2" type="text" id="searchText" placeholder="Search">
						<button class="btn btn-primary" type="button" id="searchBtn">Search</button>
					</form>
				</li>
			</ul>
			<div class="d-flex">
				<ul class="navbar-nav">
					<c:choose>
						<c:when test="${param.login }">
							<img src="resources/img/${loginMember.getImg_name()}" width="50" height="50" >
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="/account/info" role="button" data-bs-toggle="dropdown">마이페이지</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="/myinfo">Profile</a></li>
									<li><a class="dropdown-item" href="/cart">Cart</a></li>
									<li><a class="dropdown-item" href="/purchase_history">Purchase History</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
						</c:when>
						<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</nav>
