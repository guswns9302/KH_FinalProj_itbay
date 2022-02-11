<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Purchase_History</title>
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
    <div>
    	<c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
	    <select name="pageofnum" onChange="location.href='/purchase_history?pageofnum=' + this.value">
            <c:forEach var="n" begin="5" end="10" step="5">
            	<opetion value="${n}" ${pn eq n ? "selected" : ""}>${n}</opetion>
            </c:forEach>
            <option value="">전체</option>
            <option value="">10개씩 정렬</option>
            <option value="">20개씩 정렬</option>
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
		<c:set var="page" value="${empty param.page ? 1 : param.page}"/>
		<tbody>
			<c:forEach var="num" begin="${(page - 1) * pn}" end="${page * pn -1}">
				<tr>
					<td> <img src="resources/img/${purchaseList[num].img_name}".png width="50" height="50"></td>
					<td>${purchaseList[num].product_id}</td>
					<td>${purchaseList[num].subject}</td>
					<td>${purchaseList[num].price}</td>
					<td>${purchaseList[num].purchase_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	${purchaseList.size()}
	<div>
		<c:forEach var="page" begin="0" end="${purchaseList.size() / pn}" varStatus="loop">
			<c:if test="${not loop.last}">
				<span><a href="/purchase_history?page=${page +1}&pageofnum${pn}"/>${page + 1 }</span>
			</c:if>
			<c:if test="${loop.last}">
				<c:if test="${purchase_history.size() mod pn ne 0}">
					<span><a href="/purchase_history?page=${page +1}&pageofnum${pn}"/>${page + 1 }</span>
				</c:if>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>