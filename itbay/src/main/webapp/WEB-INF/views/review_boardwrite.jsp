<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<title>후기 작성</title>
</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
ITBAY
   <form action='<c:url value='/review_boardinsert'/>' method="post">
      <label >작성자 이름</label>
	 	<input type="text" name="name" value="${loginMember.getNickname() }" readonly>
              <label >제목</label>
            <input type="text" name="subject" placeholder="제목을 작성해주세요.">
			
	
            <label >내용</label>
            <textarea  name="contents" rows="10"></textarea>

  <input type="number" name="score"  min="0" max="10">

        <button type="submit">등록하기</button>
	<td>첨부파일</td>
                	<td>
                		<input type="file" name="img_name" multiple="multiple"><%-- 파일 업로드 --%>
                	</td>
    </form>
</body>
</html>