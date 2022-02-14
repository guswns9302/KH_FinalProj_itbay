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
	<style>
        table{
            text-align: center;
        }
    </style>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="login" value="${sessionScope.login }" />
		</jsp:include>
	</header>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
                <td>구매내역</td>
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
            </tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${listNp}">
				<tr>
					<td><img src="resources/img/${list.img_name}".png width="50" height="50"></td>
					<td>${list.product_id}</td>
					<td>${list.subject}</td>
					<td>${list.price}</td>
					<td>${list.purchase_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>