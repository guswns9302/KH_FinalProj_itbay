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
	<form action="/purchase_history" method="get">
    <div>
	    	<span><img src="" alt="대표이미지"></span>
	        <span>번호</span>
	        <span>회원번호</span>
	        <span>제품번호</span>
	        <span>구매날짜</span>
	    
    </div>
	</form>
</body>
</html>