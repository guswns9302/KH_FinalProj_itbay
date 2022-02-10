<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
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
    <div><span>구매내역</span></div>
    <div><select name="" id="">
            <option value="" selected>전체보기</option>
            <option value="">5개씩 정렬</option>
            <option value="">10개씩 정렬</option>
        </select>
    </div>
	<table>
		<thead>
			<th>상품 사진</th>
			<th>제품번호</th>
			<th>제품명</th>		
			<th>가격</th>
			<th>구매 날짜</th>
		</thead>
		<tbody>
			<c:forEach var="purchase_history" items="${purchaseList}">
				<tr>
					<td>${purchase_history.img_name}</td>
					<td>${purchase_history.product_id}</td>
					<td>${purchase_history.subject}</td>
					<td>${purchase_history.price}</td>
					<td>${purchase_history.purchase_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:set var="page" value="${(param.p==null)?1:param.p}"/>
	<c:set var="startNum" value="${page-(page-1)%5}"/>
	
	<ul class="-List- center">
	<c:forEach var="n" begin="0" end="4">
	<li><a class="-test- orange bold" href="?p=${startNum+n}&t=&q=">${startNum+n}</a></li>
	</c:forEach>
	</ul>
	
	<c:if test="${startNum+5<LastNum}"><a href="?p=${startNum+5}&t=&q=" class btn-next"">다음</a></c:if>
	<c:if test="${startNum+5>=LastNum}"><span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.')">다음</span></c:if>
	<c:if test="${startNum>1}"><a href="?p=${startNum-1}&t=&q=" class="btn btn-prev"">이전</a></c:if>
	<c:if test="${startNum<=1}"><span class="btn btn-prev" onclick="alert('이전페이지가 없습니다')">이전</span></c:if>
</body>
</html>