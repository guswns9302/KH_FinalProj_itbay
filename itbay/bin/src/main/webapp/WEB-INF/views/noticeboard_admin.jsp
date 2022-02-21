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
<div>
	<div>
		<select onchange="location.href='/notice_board_admin?cnt=' + this.value">
			<option value="5"  ${pageListCnt eq '5' ? 'selected' : '' }> 5개</option>
			<option value="10" ${pageListCnt eq '10' ? 'selected' : '' }>10개</option>
			<option value="20" ${pageListCnt eq '20' ? 'selected' : '' }>20개</option>
			<option value="30" ${pageListCnt eq '30' ? 'selected' : '' }>30개</option>
		</select>
	</div>
</div>	

<table>
	<thead>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
	</thead>
	<tbody>
	<c:forEach var="data" items="${datas}">
		<tr>
			<td align="center">${data.id}</td>
			<td align="center"><a href="/noticeContents_admin?noticeId=${data.id}">${data.subject}</a></td>
			<td align="center">	${data.username}</td>
			<td align="center"><fmt:formatDate value="${data.create_date}" pattern="yyyy년 MM월 dd일"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
	<div>
		<ul>
			<li><a href="?page='1'">처음</a></li>
			<c:forEach var="num" items="${pageList.nums}">
				<c:choose>
					<c:when test="${pageList.curNum eq num }">
						<li class="active">
					</c:when>
					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
					<a href="?page=${num}">${num}</a>
					</li>					
			</c:forEach>
			<li><a href="?page=${pageList.maxNum}">마지막</a></li>
		</ul>
	</div>

</body>
</html>