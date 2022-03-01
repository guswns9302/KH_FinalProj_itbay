<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sales_History_Np</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<style>
        table{
            text-align: center;
        }
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
		<c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
		<span class="float-end">
   			<select name="pageofnum" onChange="location.href='/sales_history' + this.value" class="btn btn-secondary text-uppercase">
         		<option value="_np">전체 보기</option>
		        <option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
		        <option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
      		</select>
      	</span>
		<table class="table table-striped table-hover">
			<thead>
	            <tr>
	                <th>상품사진</th>
	                <th>제품번호</th>
	                <th>제품명</th>		
	                <th>가격</th>
	                <th>판매날짜</th>
	            </tr>
			</thead>
			<tbody>
				<c:forEach var="slist" items="${sListNp}">
					<tr>
						<td><img src="resources/img/${slist.img_name}".png onerror="this.style.display='none'" width="50" height="50"></td>
						<td>${slist.product_id}</td>
						<td>${slist.subject}</td>
						<td>${slist.price}</td>
						<td>${slist.sales_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>