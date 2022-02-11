<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고 상품</title>
</head>
<body>
		<c:forEach var="img" items="${imgList}" varStatus="status">
			<c:out value="${img.img_name}" />
			<br><br>
			
		</c:forEach>
		${productDto.subject}
		${productDto.id}
</body>
</html>