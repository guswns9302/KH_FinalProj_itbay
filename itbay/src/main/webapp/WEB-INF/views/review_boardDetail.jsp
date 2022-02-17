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
<body>
<table>
	<div>
		<span>제목:</span>
		<span name="subject">${dto.subject}</span>
	</div>
	<div>
		<span>제품번호:</span>
		<span name="product_id">${dto.product_id}</span>
	</div>
	<div>
		<span>사진: -> ${dto.img_name}</span>
		<img src="resources/img/${dto.img_name}".png width="50" height="50">
	</div>
	<div>
		<span>후기 평점:</span>
		<span  name="score">${dto.score}</span>
	</div>
		<span>작성자:</span>	
			<span name="name">${dto.name}</span>
	</div>
	<div>
	<span>내용:</span>
		<span name="contents">${dto.contents}</span>
	</div>
	
	<form action="/review_comment" method="post">
	<c:choose>
	<c:when test="${empty sessionScope.loginMember.id}">
	<textarea name="contents" disabled></textarea>
	<input type="hidden" name="review_id" value="${dto.id}" disabled>	
	<input type="hidden" name="members_id">
	<button type="button">댓글 작성</button>
	</c:when>
	<c:otherwise>
	<textarea name="contents"></textarea>
	<input type="hidden" name="review_id" value="${dto.id}">	
	<input type="hidden" name="members_id" value="${dto.members_id}">
	<button type="submit">댓글 작성</button>
	</c:otherwise>
	</c:choose>
	</form>
	
	<c:forEach var="comment" items="${comments}">
	<div>
	<span>${comment.nickname}</span>
	<span>${comment.contents}</span>
	<span>${comment.create_date}</span>
	</div>
	</c:forEach>
	<div>
	<c:if test="${sessionScope.loginMember.nickname eq 'master' }">
		<button type="button" onclick="location.href='/Rereview?reviewid=${dto.id}'">수정</button>
		<button type="button" onclick="location.href='/review_boardDelete?reviewid=${dto.id}'">삭제</button>
		</c:if>
	<div>
		<button type="button" onclick="location.href='/review_board'">목록으로</button>
	</div>
	</div>
</table>
</body>
</html>