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
	<style type="text/css">
		a:link { color: black; text-decoration: none;}
		a:visited { color: black; text-decoration: none;}
		a:hover { color: navy; text-decoration: none;}
	</style>
</head>
<body>
<header>
	<c:choose>
		<c:when test="${loginMember.getUsername() eq '마스터' }">
			<jsp:include page="/WEB-INF/views/module/top-navigation_master.jsp" flush="false" >
				<jsp:param name="login" value="${sessionScope.login }" />
			</jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
				<jsp:param name="login" value="${sessionScope.login }" />
			</jsp:include>
		</c:otherwise>
	</c:choose>
</header>
<section class="container p-5 my-5">
	<div class="clearfix">
		<span class="float-end" style="margin-top:10px;">
			<select class="border border-1" onchange="location.href='/notice_board?cnt=' + this.value" class="btn btn-secondary text-uppercase">
				<option value="5"  ${pageListCnt eq '5' ? 'selected' : '' }> 5개씩 보기</option>
				<option value="10" ${pageListCnt eq '10' ? 'selected' : '' }>10개씩 보기</option>
				<option value="20" ${pageListCnt eq '20' ? 'selected' : '' }>20개씩 보기</option>
				<option value="30" ${pageListCnt eq '30' ? 'selected' : '' }>30개씩 보기</option>
			</select>
		</span>
	</div>

	<table class="table table-striped table-hover align-middle" style="text-align: center;">
		<thead>
			<tr>
				<th>Num</th>
				<th>Subject</th>
				<th>Writer</th>
				<th>Views</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="data" items="${datas}">
			<tr>
				<td>${data.id}</td>
				<td><a href="/noticeContents_user?noticeId=${data.id}">${data.subject}</a></td>
				<td>${data.username}</td>
				<td>${data.view_CNT}</td>
				<td><fmt:formatDate value="${data.create_date}" pattern="yyyy년 MM월 dd일"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<ul class="pagination" style="justify-content: center;">
		<li class="page-item"><a class="page-link" href="?page=1">처음</a></li>
		<c:if test="${vpage > 1 }">
			<li class="page-item">
				<a class="page-link" href="?page=${vpage -1 }">Previous</a>
			</li>
		</c:if>
		<c:forEach var="num" items="${pageList.nums}">
			<c:choose>
				<c:when test="${pageList.curNum eq num }">
					<li class="page-item active"><a class="page-link" href="?page=${num}">${num}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${num}">${num}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${vpage < total_page.maxPage }">
			<li class="page-item"><a class="page-link" href="?page=${vpage +1 }">Next</a></li>
		</c:if>
		<li class="page-item"><a class="page-link" href="?page=${pageList.maxNum}">마지막</a></li>
	</ul>
</section>
</body>
</html>