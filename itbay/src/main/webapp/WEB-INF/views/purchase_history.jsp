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
	<style>
        table{
            text-align: center;
        }
        #paging{
        	text-align: center;
        	background-color:whitesmoke;
            font-size:16px;
        }
        #review{
        	background-color:lightgray;
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
                <td></td>
                <td></td>
                <td>
                	<c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
	    			<select name="pageofnum" onChange="location.href='/purchase_history' + this.value">
         				<option value="_np">전체 보기</option>
         		        <option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
        		        <option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
     			    </select>
        		</td>
            </tr>
            <tr>
                <th>상품사진</th>
                <th>제품번호</th>
                <th>제품명</th>		
                <th>가격</th>
                <th>구매날짜</th>
                <th>후기</th>
                <td></td>
            </tr>
		</thead>
		<c:set var="page" value="${empty param.page ? 1 : param.page}"/>
		<tbody>
			<c:forEach var="num" begin="${(page - 1) * pn}" end="${page * pn - 1}">
				<tr>
					<td> <img src="resources/img/${purchaseList[num].img_name}".png width="50" height="50"></td>
					<td>${purchaseList[num].product_id}</td>
					<td>${purchaseList[num].subject}</td>
					<td>${purchaseList[num].price}원</td>
					<td>${purchaseList[num].purchase_date}</td>
					<td>${purchaseList[num].review_yn}</td>
					<td>
						<br>
						<c:if test="${purchaseList[num].review_yn == '후기 없음'}">
							<a href="/review_boardWrite?${purchaseList[num].product_id}" id="review">후기 작성</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="paging">
		<c:forEach var="page" begin="0" end="${purchaseList.size() / pn}" varStatus="loop">
			<c:if test="${not loop.last}">
				<span><a href="/purchase_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></span>
			</c:if>
			<c:if test="${loop.last}">
				<c:if test="${purchaseList.size() mod pn ne 0}">
					<span><a href="/purchase_history?page=${page +1}&pageofnum=${pn}">${page + 1}</a></span>
				</c:if>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>