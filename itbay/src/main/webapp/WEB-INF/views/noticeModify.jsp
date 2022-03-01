<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>공지사항</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
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
	<section class="container p-5 my-5" style="text-align:center;">
		<form action="./noticeChange" method="post">
		 	<h1 class="h3 mb-3 fw-normal">Change Notice</h1>
		 	<input type="hidden" name="noticeId" value="${dto.id}">
			<div class="form-floating mb-3 mt-3">
				<input type="text" class="form-control" placeholder="Enter Subject" name="subject" value="${dto.subject}">
				<label for="subject">subject</label>
			</div>
			<div>
				<textarea class="form-control" rows="10" placeholder="Enter Contents" name="contents">${dto.contents}</textarea>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="float-end" style="margin : 10px;">
							<button type="submit" class="btn btn-secondary text-uppercase" style="width: 183px;">Submit</button>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="float-start" style="margin : 10px;">
							<button type="button" class="btn btn-secondary text-uppercase" style="width: 183px;" onclick="location.href='./notice_board'">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>