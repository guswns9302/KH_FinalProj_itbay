<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <form action='<c:url value='/Rereview_board'/>' method="post">
    <label >회원 아이디</label>
	 	<input type="text" name="members_id" value="${loginMember.getId()}" readonly>
	 	 <input type="hidden" name="id" value="${dto.getId()}" readonly>
	 	 <label >제품 번호</label>
	 	 <input type="text" name="product_id" value="${dto.getProduct_id()}" readonly>
	 	    <label >제목</label>            
            <input type="text" name="subject" value="${dto.getSubject()}" placeholder="제목을 작성해주세요.">
            <label >내용</label>
            <textarea  name="contents" rows="10">${dto.getContents()}</textarea>
  <input type="number" name="score" value="${dto.getScore()}" min="0" max="10">
        <button type="submit">수정하기</button>
    </form>
</body>
</html>