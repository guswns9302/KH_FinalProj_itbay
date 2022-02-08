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
	<a href="/">메인 돌아가기</a>
    <form action="" method="post">
        <div>
            <img src="${kakaoMember.get('profile_image') }" alt="프로필사진">
        </div>
        <button type="submit"> 프로필 사진 변경</button>
    </form>

    <form action="" method="post">
        <div>
        <ul>
            <li>
                <label for="">아이디</label>
                <input type="text" name="nickname" value="${kakaoMember.get('email') }">
            </li>
            <li>
                <label for="">패스워드</label>
                <input type="password" name="pw" value="">
            </li>
            <li>
                <label for="">패스워드확인</label>
                <input type="password" name="pwchk" value="">
            </li>
             <li>
                <label for="">이름</label>
                <input type="text" name="username" value="${kakaoMember.get('kakaoNickName') }">
            </li>
            <li>
                <label for="">메일주소</label>
                <input type="email" name="email_address" value="${kakaoMember.get('email') }">
            </li>
            <li>
                <label for="">전화번호</label>
                <input type="tel" name="phone" value="">
            </li>
            <li>
                <label for="">주소</label>
                <input type="text" name="address" value="">
            </li>
        </ul>
            <button type="submit"> 정보 변경</button>
        </div>
    </form>
</body>
</html>