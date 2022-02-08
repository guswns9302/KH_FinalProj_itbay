<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<table>
	<thead>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
	</thead>
	<tbody>
	<c:forEach var="data" items="${list}">
		<tr>
			<td>${data.id}</td>
			<td>${data.subject}</td>
			<td>${data.members_id}</td>
			<td>${data.create_date}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>