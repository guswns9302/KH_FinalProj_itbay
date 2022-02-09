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
		<c:forEach var="product" items="${list}" varStatus="status">
			
			<c:out value="${product.img_name}" />
			
			<a href="/productDetail?product_id=${product.id}"><c:out value="${product.subject}" /></a>
			
			<c:out value="${product.price}" />
			
			<c:choose>
			    <c:when test="${product.sold_out eq 'N'}">
			        
			    </c:when>
			    <c:otherwise>
			        판매완료
			    </c:otherwise>
			</c:choose>


			<br><br>
			
		</c:forEach>
</body>
</html>