<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>구매내역</title>
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
<div></div>
<div>
	<div>
		<select onchange="location.href='/member_purchase_history?cnt=' + this.value">
			<option value="5"  ${pageListCnt eq '5' ? 'selected' : '' }> 5개</option>
			<option value="10" ${pageListCnt eq '10' ? 'selected' : '' }>10개</option>
			<option value="20" ${pageListCnt eq '20' ? 'selected' : '' }>20개</option>
			<option value="30" ${pageListCnt eq '30' ? 'selected' : '' }>30개</option>
		</select>
	</div>
</div>
<table>
	<c:forEach var="history" items="${histories}">
		<tr>
			<td><img src="resources/img/${history.img_name }" width="50" height="50"></td>
			<td>${history.product_id }</td>
			<td>${history.subject }</td>
			<td>${history.price }</td>
			<td>${history.purchase_date }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>