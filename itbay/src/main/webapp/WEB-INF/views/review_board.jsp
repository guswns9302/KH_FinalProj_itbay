<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>후기 게시판</title>
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
	<thead>
		<th>번호</th>
		<th>상품 사진</th>
		<th>제품번호</th>		
		<th>제목</th>
		<th>내용</th>
		<th>날짜</th>
		
	</thead>
	<tbody>
	<c:forEach var="data" items="${list}">
		<tr>
			<td>${data.id}</td>
			<td>${data.img_name}</td>
			<td>${data.product_id}</td>
			<td>${data.subject}</td>
			<td>${data.contents}</td>
			<td>${data.create_date}</td>
			<td>${data.img_name}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>