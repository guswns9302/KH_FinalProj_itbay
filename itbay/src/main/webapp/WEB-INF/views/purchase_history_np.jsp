<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase_History_Np</title>
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
	<section class="container p-5 my-5">
		<table class="table table-striped table-hover  align-middle" style="text-align: center;">
			<thead>
				<tr>
	                <td>구매내역</td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td><select name="pageofnum" onChange="location.href='/purchase_history' + this.value">
				           <option>전체 보기</option>
				           <option value="?pageofnum=5">5개 씩 보기</option>
				           <option value="?pageofnum=10">10개 씩 보기</option> 
				        </select></td>
	            </tr>
	            <tr>
	                <th>상품사진</th>
	                <th>제품번호</th>
	                <th>제품명</th>		
	                <th>가격</th>
	                <th>구매날짜</th>
	                <td></td>
	            </tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${listNp}">
					<tr>
						<td><img src="resources/img/${list.img_name}" width="50" height="50"></td>
						<td>${list.product_id}</td>
						<td>${list.subject}</td>
						<td>${list.price}원</td>
						<td>${list.purchase_date}</td>
						<td>
							<c:if test="${list.review_yn == '후기 없음'}">
								<a href="/review_boardWrite?${list.product_id}" id="review" class="btn btn-secondary text-uppercase">Write Review</a>
							</c:if>
						</td>
								
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>