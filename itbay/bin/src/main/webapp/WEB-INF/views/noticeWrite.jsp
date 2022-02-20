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
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
	
	<form action="./noticeSave" method="post">
		<div>
			<button type="submit">저장</button>	
		</div>
		<div>
			<button type="button" onclick="location.href='./notice_board'">취소</button>	
		</div>
		<div>
			<input type="text" name="subject" placeholder="공지 제목">
		</div>
		<div>
			<textarea name="contents" placeholder="공지 내용"></textarea>
		</div>
	</form>
	
</header>
</body>
</html>