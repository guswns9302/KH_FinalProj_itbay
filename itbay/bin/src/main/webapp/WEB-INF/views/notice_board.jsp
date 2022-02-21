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
					<section class="container p-5 my-5">
						<div class="clearfix">
							<span class="float-end" style="margin-top:10px;">
								<select class="border border-1" onchange="location.href='/notice_board?cnt=' + this.value">
									<option value="5"  ${pageListCnt eq '5' ? 'selected' : '' }> 5개씩 보기</option>
									<option value="10" ${pageListCnt eq '10' ? 'selected' : '' }>10개씩 보기</option>
									<option value="20" ${pageListCnt eq '20' ? 'selected' : '' }>20개씩 보기</option>
									<option value="30" ${pageListCnt eq '30' ? 'selected' : '' }>30개씩 보기</option>
								</select>
							</span>
						</div>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Subject</th>
									<th>Writer</th>
									<th>Date</th>
								</tr>
							</thead>
							<c:forEach var="data" items="${datas}">
								<tr>
									<td>${data.id}</td>
									<td><a href="/noticeContents?noticeId=${data.id}">${data.subject}</a></td>
									<td>
										<c:forEach var="admin" items="${admin}">
											<c:if test="${data.members_id eq admin.id}">
												${admin.username}
											</c:if>
										</c:forEach>
									</td>
									<td align="center"><fmt:formatDate value="${data.create_date}" pattern="yyyy년 MM월 dd일"/></td>
								</tr>
							</c:forEach>
						</table>
						<ul class="pagination" style="justify-content: center;">
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
						</ul>	
				</section>
</body>
</html>