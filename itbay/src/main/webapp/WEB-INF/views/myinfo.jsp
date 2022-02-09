<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>myinfo</title>
</head>
<body>
    <form action="" method="post">
        <div>
            <img src="" alt="프로필사진">
        </div>
        <div>
            <input type="image" name="" accept=".image/png,.jpg,.jpeg" >
        </div>
    </form>

    <form action="" method="post">
        <div>
            <li>
                <label for="">아이디</label>
                <input type="text" name="nickname" value="">
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
                <label for="">메일주소</label>
                <input type="email" name="email_address" value="">
            </li>
            <li>
                <label for="">전화번호</label>
                <input type="tel" name="phone" value="">
            </li>
            <li>
                <label for="">주소</label>
                <input type="text" name="address" value="">
            </li>
            <button type="submit"> 정보 변경</button>
        </div>
    </form>
</body>
</html>