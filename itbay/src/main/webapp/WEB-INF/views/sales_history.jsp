<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>Sales_Hitory</title>
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
   			<select name="pageofnum" onChange="location.href='/sales_history' + this.value" class="btn btn-secondary text-uppercase">
         		<option value="_np">전체 보기</option>
		        <option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
		        <option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
      		</select>
      	</span>
		<table class="table table-striped table-hover">
			<thead>
<<<<<<< HEAD
=======
				<tr>
	                <td>판매내역</td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td>
	                	<c:set var="pn" value="${empty param.pageofnum ? pageofnum : param.pageofnum}" />
		    			<select name="pageofnum" onChange="location.href='/review_board' + this.value">
	           				<option value="_np">전체 보기</option>
					        <option value="?pageofnum=5" ${pn eq 5 ? "selected" : ""}>5개 씩 보기</option>
					        <option value="?pageofnum=10" ${pn eq 10 ? "selected" : ""}>10개 씩 보기</option> 
	        			</select>
	        		</td>
	            </tr>
>>>>>>> refs/remotes/origin/0220황원준
	            <tr>
	                <th>상품사진</th>
	                <th>제품번호</th>
	                <th>제품명</th>		
	                <th>가격</th>
	                <th>판매날짜</th>
	            </tr>
			</thead>
			<c:set var="page" value="${empty param.page ? 1 : param.page}"/>
			<tbody>
				<c:choose>
					<c:when test="${page * pn -1 > salesList.size()-1}">
						<c:set var="endNum" value="${salesList.size()-1 }"/>
					</c:when>
					<c:otherwise>
						<c:set var="endNum" value="${page * pn -1}"/>
					</c:otherwise>
				</c:choose>
				<c:forEach var="num" begin="${(page - 1) * pn}" end="${endNum }">
					<tr>
						<td> <img src="resources/img/${salesList[num].img_name}".png width="50" height="50"></td>
						<td>${salesList[num].product_id}</td>
						<td>${salesList[num].subject}</td>
						<td>${salesList[num].price}</td>
						<td>${salesList[num].sales_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${vpage > 1 }">
				<li class="page-item">
					<a class="page-link" href="/sales_history?page=${vpage-1}&pageofnum=${pn}">Previous</a>
				</li>						
			</c:if>
			<c:forEach var="page" begin="0" end="${salesList.size() / pn}" varStatus="loop">
				<c:choose>
					<c:when test="${not loop.last}">
						<c:choose>
							<c:when test="${(page+1) == vpage }">
								<li class="page-item active"><a class="page-link" href="/sales_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="/sales_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${loop.last}">
						<c:if test="${salesList.size() mod pn ne 0}">
							<c:choose>
								<c:when test="${(page+1) == vpage }">
									<li class="page-item active"><a class="page-link" href="/sales_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/sales_history?page=${page + 1}&pageofnum=${pn}">${page + 1}</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${vpage < salesList.size() / pn }">
				<li class="page-item">
					<a class="page-link" href="/sales_history?page=${vpage+1}&pageofnum=${pn}">Next</a>
				</li>						
			</c:if>
		</ul>
	</section>
</body>
</html>