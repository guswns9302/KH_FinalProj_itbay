


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<th> 글 번호</th>
		<th>상품 사진</th>
		<th>제품번호</th>		
		<th>제목</th>
		<th>내용</th>
		<th>날짜</th>
		<th>주문날짜</th>
		<th>구매한 가격</th>
		<th>구매자 이름</th>
		
		
	</thead>
	<tbody>
	<c:forEach var="data" items="${list}">
		<tr>
			<td>${data.id}</td>
			<td> <img src="resources/img/${data.img_name}".png width="50" height="50"></td>
			<td>${data.product_id}</td>
			<td>${data.subject}</td>
			<td>${data.contents}</td>
			<td>${data.create_date}</td>
			<td>${data.order_date}</td>
			<td>${data.price}원</td>
			<td>${data.usernickname}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
            	
<c:if test="${sessionScope.login !=null}">
<a href='<c:url value='/review_boardwrite'/>' role="button">글쓰기</a>
</c:if>
</body>
</html>
