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
					<a class="nav-link" href="/">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/">Used Market</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/">Purchase Review</a>
				</li>
				<li>
					<form class="d-flex">
						<input class="form-control me-2" type="text" placeholder="Search">
						<button class="btn btn-primary" type="button">Search</button>
					</form>
				</li>
			</ul>
			<div class="d-flex">
				<ul class="navbar-nav">
					<c:choose>
						<c:when test="${param.login }">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="/account/info" role="button" data-bs-toggle="dropdown">마이페이지</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="/myinfo">내 정보</a></li>
									<li><a class="dropdown-item" href="/">비밀번호 변경</a></li>
									<li><a class="dropdown-item" href="/">회원 탈퇴</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="/kakaologout">카카오로그아웃</a></li>
						</c:when>
						<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</nav>
