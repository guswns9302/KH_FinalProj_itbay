<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원목록</title>
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
<div>
	<div>
		<select onchange="location.href='/member_list?cnt=' + this.value">
			<option value="5"  ${pageListCnt eq '5' ? 'selected' : '' }> 5개</option>
			<option value="10" ${pageListCnt eq '10' ? 'selected' : '' }>10개</option>
			<option value="20" ${pageListCnt eq '20' ? 'selected' : '' }>20개</option>
			<option value="30" ${pageListCnt eq '30' ? 'selected' : '' }>30개</option>
		</select>
	</div>
</div>	
<div>
	<!-- 회원목록 들어갈 공간-->
		<table class="table table-striped table-hover align-middle" style="text-align: center;">
			<thead>
				<tr>
					<th>Img</th>
					<th>ID</th>
					<th>Nickname</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Add</th>
					<th>JoinDate</th>
				</tr>	
			</thead>
			<c:forEach var="member" items="${datas}">
			<tr>
				<td>
					<c:choose>
						<c:when test="${member.getSocial_login() eq 'Y'.charAt(0) }">
							<div style="text-align:center;">
								<img src="${member_img}" class="img-thumbnail" alt="Cinque Terre"/>
							</div>
						</c:when>
						<c:otherwise>
						  	<div style="text-align:center;">
								<img src="resources/img/${member.getImg_name() }" class="img-thumbnail" alt="Cinque Terre">
							</div>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${member.getId() }</td>
				<td>${member.getNickname() }</td>
				<td>${member.getPhone() }</td>
				<td>${member.getEmail_address() }</td>
				<td>${member.getAddress() }</td>
				<td><a href="/member_purchase_history?membersId=${member.getId() }">구매 내역</a></td>
				<td>${member.getJoinDate() }</td>
			</c:forEach>
		</table>
</div>
<div>
	<ul>
		<li><a href="?page=1">처음</a></li>
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