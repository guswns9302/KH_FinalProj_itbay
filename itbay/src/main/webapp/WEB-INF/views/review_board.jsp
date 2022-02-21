


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>후기게시판</title>
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
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
	<section class="container p-5 my-5">
    <c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
	<span class="float-end">
		<select name="pageofnum" onChange="location.href='/review_board' + this.value">
			<option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
			<option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
	 	</select>
	 </span>
	<table class="table table-striped table-hover align-middle" style="text-align: center;">
		<thead>
			<tr>
				<th>Img</th>
				<th colspan="2">Subject</th>
				<th>Buyer</th>
				<th>Price</th>
				<th>Score</th>
				<th>Viewcnt</th>
				<th>Date</th>
			</tr>	
		</thead>
		<c:set var="page" value="${empty param.page ? 1 : param.page}"/>
		<tbody>
			<c:choose>
				<c:when test="${page * pn -1 > list.size()-1}">
					<c:set var="endNum" value="${list.size()-1 }"/>
				</c:when>
				<c:otherwise>
					<c:set var="endNum" value="${page * pn -1}"/>
				</c:otherwise>
			</c:choose>
			<c:if test="${endNum > 0 }">
				<c:forEach var="num" begin="${(page - 1) * pn}" end="${endNum }">
					<tr>
						<td><img src="resources/img/${list[num].img_name}" width="50" height="50"></td>
						<td colspan="2"><a href="/review_boardDetail?reviewid=${list[num].id}">${list[num].subject}</a></td>
						<td>${list[num].name}</td>
						<td>${list[num].price}원</td>
						<td>${list[num].score}</td>
						<td>${list[num].view_cnt}</td>
						<td>${list[num].create_date}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<ul class="pagination" style="justify-content: center;">
			<c:if test="${vpage > 1 }">
				<li class="page-item">
					<a class="page-link" href="/review_board?page=${vpage-1}&pageofnum=${pn}">Previous</a>
				</li>						
			</c:if>
			<c:forEach var="page" begin="0" end="${list.size() / pn}" varStatus="loop">
				<c:choose>
					<c:when test="${not loop.last}">
						<c:choose>
							<c:when test="${(page+1) == vpage }">
								<li class="page-item active"><a class="page-link" href="/review_board?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="/review_board?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${loop.last}">
						<c:if test="${list.size() mod pn ne 0}">
							<c:choose>
								<c:when test="${(page+1) == vpage }">
									<li class="page-item active"><a class="page-link" href="/review_board?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/review_board?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${vpage < list.size() / pn }">
				<li class="page-item">
					<a class="page-link" href="/review_board?page=${vpage+1}&pageofnum=${pn}">Next</a>
				</li>						
			</c:if>
		</ul>
	</section>

</body>
</html>
