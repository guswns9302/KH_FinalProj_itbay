<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>공지사항</title>
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
	<c:if test="${sessionScope.loginMember.username eq '마스터' }">
		<button type="button" onclick="location.href='/noticeWrite'">공지 등록</button>
	</c:if>
<table>
	<thead>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
	</thead>
	<tbody>
	<c:forEach var="data" items="${list}">
		<tr>
			<td align="center">${data.id}</td>
			<td align="center"><a href="/noticeContents?noticeId=${data.id}">${data.subject}</a></td>
			<td align="center">
				<c:forEach var="admin" items="${admin}">
					<c:if test="${data.members_id eq admin.id}">
						${admin.username}
					</c:if>
				</c:forEach>
			</td>
			<td align="center"><fmt:formatDate value="${data.create_date}" pattern="yyyy년 MM월 dd일"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>