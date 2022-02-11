<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>중고 상품</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<script type="text/javascript">
		function soldOutChange(){
			
			var sold = document.getElementById("sold_out").value;

			location.href="product?subject=&sold_out="+sold;
		}
		
		$(document).ready(function(){

			$("#searchBtn").click(function(){
				var subject = $("#searchText").val();
				var sold_out = $("#sold_out").val();
				location.href = "/product?subject="+subject+"&sold_out="+sold_out;
			});
			
		})
		
	</script>
</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
	<h1>중고 상품</h1>
		<div style="float: right;" class=>
			<select name="sold_out" id="sold_out" onChange="soldOutChange()">
				<option value="" >전체 상품 보기</option>
				<option value="N" <c:if test="${sold_out == 'N'}">selected</c:if>>판매완료 상품 제외</option>
			</select>
		</div>
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