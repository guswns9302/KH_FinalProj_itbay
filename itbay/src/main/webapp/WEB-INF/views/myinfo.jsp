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
    <form action="" method="post">
        <div>
        	<c:choose>
        		<c:when test="${loginMember.getSocial_login() eq 'Y'.charAt(0) }">
            		<img src="${loginMember.getImg_name() }.jpg" />
        		</c:when>
        		<c:otherwise>
            		<img src="resources/img/${loginMember.getImg_name() }.png" alt="프로필사진">
        		</c:otherwise>
        	</c:choose>
        </div>
        <button type="submit"> 프로필 사진 변경</button>
    </form>

    <form action="" method="post">
        <div>
        <ul>
            <li>
                <label for="">아이디</label>
                <input type="text" name="nickname" value="${loginMember.getNickname() }">
            </li>
            <li>
                <label for="">패스워드</label>
                <input type="password" name="pw" value="${loginMember.getPw() }">
            </li>
            <li>
                <label for="">패스워드확인</label>
                <input type="password" name="pwchk" value="">
            </li>
             <li>
                <label for="">이름</label>
                <input type="text" name="username" value="${loginMember.getUsername() }">
            </li>
            <li>
                <label for="">메일주소</label>
                <input type="email" name="email_address" value="${loginMember.getEmail_address() }">
            </li>
            <li>
                <label for="">전화번호</label>
                <input type="tel" name="phone" value="${loginMember.getPhone() }">
            </li>
            <li>
                <label for="">주소</label>
                <input type="text" name="address" value="${loginMember.getAddress() }">
            </li>
        </ul>
            <button type="submit"> 정보 변경</button>
        </div>
    </form>
</body>
</html>