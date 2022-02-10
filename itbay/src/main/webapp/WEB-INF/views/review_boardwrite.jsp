<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<title>profile</title>
</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
ITBAY
<form method="get">

<label>제목</label>
<input type="text" name="subject" /><br />

<label>내용</label>
<textarea name="contents"></textarea><br />
<input type="submit" id="submiBtn"  class="btn btn-outline-primary mr-2" value="작성">



</form>

<a href="/review_board"><button>취소</button></a>
				
            	
            	<ul>
            	<img src="resources/img/${loginMember.getImg_name()}".png" width="50" height="50">
            		<li><a href="/myinfo">회원정보</a></li>
            		<li><a href="/cart">장바구니</a></li>
                	<li><a href="/purchase_history">구매내역</a></li>
                	<li><a href="/reply">채팅문의</a></li>
            	</ul>
</body>
</html>