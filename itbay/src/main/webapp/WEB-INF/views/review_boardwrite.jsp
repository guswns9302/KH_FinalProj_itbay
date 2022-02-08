<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ITBAY

<a href="/review_board"><button>취소</button></a>
            	<p><%=session.getAttribute("login") %>님</p>
            	
            	<ul>
            		<li><a href="/members">회원정보</a></li>
            		<li><a href="/cart">장바구니</a></li>
                	<li><a href="/purchase_history">구매내역</a></li>
                	<li><a href="/reply">채팅문의</a></li>
            	</ul>
        	</div>
    	</div>
</body>
</html>