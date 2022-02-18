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
</header>
<table>
	<div>
		<span>제 목:</span>
		<span name="subject">${dto.subject}</span>
	</div>
	<div>
		<span>작성자:</span>
		<span name="contents">${dto.username}</span>
	</div>
	<div>
		<div>${dto.contents}</div>
	</div>
	<div>
		<button type="button" onclick="location.href='/notice_board'">목록</button>
	</div>
</table>
</body>
</html>