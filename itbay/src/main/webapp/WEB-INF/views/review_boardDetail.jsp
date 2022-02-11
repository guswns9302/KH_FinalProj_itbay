


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
<ul>
            	<c:if test="${sessionScope.login !=null}">     
            		
				<ul>
				<img src="resources/img/${loginMember.getImg_name()}".png" width="50" height="50">
            		<li><a href="/myinfo">회원정보</a></li>
            		<li><a href="/cart">장바구니</a></li>
                	<li><a href="/purchase_history">구매내역</a></li>
                	<li><a href="/reply">채팅문의</a></li>
            	</ul>
            	</c:if>
            	</ul>
</body>
</html>