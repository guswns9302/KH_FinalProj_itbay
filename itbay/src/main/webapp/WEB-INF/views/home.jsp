<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
</head>
<header>
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
	<div class="album py-5 bg-light">
    	<div class="container">
		<h3>최근 등록된 상품</h3>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<c:forEach var="product" items="${recomend_list}" varStatus="status">
				        <div class="col">
				          <div class="card shadow-sm">
				            <img class="bd-placeholder-img card-img-top" width="100%" height="225" alt="" src="/resources/img/${product.img_name}">
				            <div class="card-body">
				              <h5 class="card-text"><c:out value="${product.subject}" /></h5>
				              <div class="d-flex justify-content-between align-items-center">
				              	<table class="table table-striped" style="text-align: center;">
									<thead>
										<tr>
											<th><small class="text-muted"><fmt:formatNumber value="${product.price}" pattern="#,###"/> \</small></th>
											<th>조회수 : ${product.view_CNT }</th>
											<th>
												<c:choose>
												    <c:when test="${product.sold_out eq 'N'}">
												        <a class="btn btn-secondary" href="/productDetail?product_id=${product.id}">See Detail</a>
												    </c:when>
												    <c:otherwise>
												         <a class="btn btn-secondary">Sold Out</a>
												    </c:otherwise>					
												</c:choose>	
											</th>
										</tr>
									</thead>
								</table>
				              </div>
				            </div>
				          </div>
				        </div>
				</c:forEach>
			</div>
		</div>
	</div> 
	<div class="album py-5 bg-light">
    	<div class="container">
		<h3>조회수 높은 상품</h3>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<c:forEach var="product" items="${viewCount_list}" varStatus="status">
				        <div class="col">
				          <div class="card shadow-sm">
				            <img class="bd-placeholder-img card-img-top" width="100%" height="225" alt="" src="/resources/img/${product.img_name}">
				            <div class="card-body">
				              <h5 class="card-text"><c:out value="${product.subject}" /></h5>
				              <div class="d-flex justify-content-between align-items-center">
				              	<table class="table table-striped" style="text-align: center;">
									<thead>
										<tr>
											<th><small class="text-muted"><fmt:formatNumber value="${product.price}" pattern="#,###"/> \</small></th>
											<th>조회수 : ${product.view_CNT }</th>
											<th>
												<c:choose>
												    <c:when test="${product.sold_out eq 'N'}">
												        <a class="btn btn-secondary" href="/productDetail?product_id=${product.id}">See Detail</a>
												    </c:when>
												    <c:otherwise>
												         <a class="btn btn-secondary">Sold Out</a>
												    </c:otherwise>					
												</c:choose>	
											</th>
										</tr>
									</thead>
								</table>
				              </div>
				            </div>
				          </div>
				        </div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
