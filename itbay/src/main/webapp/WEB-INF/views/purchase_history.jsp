<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>purchase_history</title>
</head>
<body>
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