<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID찾기</title>
<jsp:include page="/WEB-INF/views/module/default_js_css.jsp"
	flush="false" />

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp"
			flush="false">
			<jsp:param name="logined"
				value="${empty sessionScope.logined ? false : true }" />
			<jsp:param name="logined" value="${sessionScope.logined}" />
		</jsp:include>
	</header>
	<section class="container">
		<h2>ID 찾기</h2>
		<div class="card border-dark">
			<div class="card-body text-dark">

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label" for="username">이름</label>
					<div class="col-sm-10">
						<input id="username" class="form-control" type="text"
							name="username" required>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label" for="phone">전화번호</label>
					<div class="col-sm-10">
						<input id="phone" class="form-control" type="tel" name="phone"
							required>
					</div>
				</div>
				<div style="text-align: center;">
					<button id="findBtn" class="btn btn-secondary text-uppercase"
						style="width: 183px;">ID 찾기</button>
				</div>
			</div>
		</div>
	</section>
	<section class="container">
		<h2></h2>
		<div class="card border-dark">
			<div class="card-body text-dark">

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label" for="username">ID</label>
					<div class="col-sm-10">
						<div id="foundid"></div>
					</div>
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
	$(function(){
		$("#findBtn").on("click", function(){
			$.ajax({
				url:'/findid',
				type:'POST',
				data:{
					'username' : $("#username").val(),
					'phone' : $("#phone").val()
				}
			}).done(function(response){
				$("#foundid").html(response.nickname)
			})
		})
	})
</script>
</html>