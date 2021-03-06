<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Purchase_History</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
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
	   	<c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
		<span class="float-end">
			<select name="pageofnum" onChange="location.href='/purchase_history' + this.value" class="btn btn-secondary text-uppercase">
				<option value="_np">전체 보기</option>
		        <option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
		      	<option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
			</select>
		</span>
		<table class="table table-striped table-hover align-middle" style="text-align: center;">
			<thead>
	            <tr>
	                <th>상품사진</th>
	                <th>제품번호</th>
	                <th>제품명</th>		
	                <th>가격</th>
	                <th>구매날짜</th>
	                <td></td>
	            </tr>
			</thead>
			<c:set var="page" value="${empty param.page ? 1 : param.page}"/>
			<tbody>
				<c:choose>
					<c:when test="${page * pn -1 > purchaseList.size()-1}">
						<c:set var="endNum" value="${purchaseList.size()-1 }"/>
					</c:when>
					<c:otherwise>
						<c:set var="endNum" value="${page * pn -1}"/>
					</c:otherwise>
				</c:choose>
				<c:if test="${endNum > 0 }">
					<c:forEach var="num" begin="${(page - 1) * pn}" end="${endNum }">
						<tr>
							<td> <img src="resources/img/${purchaseList[num].img_name}" onerror="this.style.display='none'" width="50" height="50"></td>
							<td>${purchaseList[num].product_id}</td>
							<td>${purchaseList[num].subject}</td>
							<td>${purchaseList[num].price}원</td>
							<td>${purchaseList[num].purchase_date}</td>
							<td>
								<c:if test="${purchaseList[num].review_yn == '후기 없음'}">
									<a href="/review_boardWrite?etc=${purchaseList[num].product_id}" id="review" class="btn btn-secondary text-uppercase">Write Review</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${vpage > 1 }">
				<li class="page-item">
					<a class="page-link" href="/purchase_history?page=${vpage-1}&pageofnum=${pn}">Previous</a>
				</li>						
			</c:if>
			<c:forEach var="page" begin="0" end="${purchaseList.size() / pn}" varStatus="loop">
				<c:choose>
					<c:when test="${not loop.last}">
						<c:choose>
							<c:when test="${(page+1) == vpage }">
								<li class="page-item active"><a class="page-link" href="/purchase_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="/purchase_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${loop.last}">
						<c:if test="${purchaseList.size() mod pn ne 0}">
							<c:choose>
								<c:when test="${(page+1) == vpage }">
									<li class="page-item active"><a class="page-link" href="/purchase_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/purchase_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${vpage < purchaseList.size() / pn }">
				<li class="page-item">
					<a class="page-link" href="/purchase_history?page=${vpage+1}&pageofnum=${pn}">Next</a>
				</li>						
			</c:if>
		</ul>
	</section>
</body>
</html>