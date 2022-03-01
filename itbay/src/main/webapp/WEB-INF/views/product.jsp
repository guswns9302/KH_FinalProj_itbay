<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<title>중고 상품</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<meta charset="UTF-8">
	<script type="text/javascript">
		function soldOutChange(){
			
			var sold = document.getElementById("sold_out").value;

			location.href="product?subject=&sold_out="+sold;
		}
		
		function go_page(currentIndex) {
			
			var sold = document.getElementById("sold_out").value;
			location.href="product?subject=&sold_out="+sold+"&page="+currentIndex;
		}
		
		//제이쿼리 기초 틀
		$(document).ready(function(){

			//이벤트 #-html태그의 Id, .-html태그의 class
			$("#searchBtn").click(function(){
				var subject = $("#searchText").val();
				var sold_out = $("#sold_out").val();
				location.href = "/product?subject="+subject+"&sold_out="+sold_out;
			});
			
			$("#productSaveBtn").click(function(){
				location.href = "/productWrite";
			});
			
			
		});
		
	</script>
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
	


		
		<br><br>
		
	<div class="album py-5 bg-light">
    	<div class="container">
    	
    		<div>
				<c:if test="${loginMember.getNickname() eq 'master@master.com'}">
					<button class="btn btn-primary" type="button" id="productSaveBtn">제품등록</button>
				</c:if>
			</div>		
			<br>
			<select name="sold_out" class="form-control" id="sold_out" onChange="soldOutChange()" style="float: right; ">
				<option value="" >전체 상품 보기</option>
				<option value="N" <c:if test="${sold_out == 'N'}">selected</c:if>>판매완료 상품 제외</option>
			</select>
			<br>
			<br>
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<c:forEach var="product" items="${list}" varStatus="status">
				        <div class="col">
				          <div class="card shadow-sm">
				            <img class="bd-placeholder-img card-img-top" onerror="this.src='resources/icon/itbay_logo.png'" width="100%" height="225" alt="" src="/resources/img/${product.img_name}">
				            <div class="card-body">
				              <div class="clearfix">
					              <h5 class="card-text float-start"><c:out value="${product.subject}" /></h5>
					              <c:choose>
								    <c:when test="${product.sold_out eq 'Y'}">
								        <h5 class="card-text float-end text-muted">Sold Out</h5>
								    </c:when>
								  </c:choose>	
							  </div>
				              <div class="d-flex justify-content-between align-items-center">
				              	<table class="table table-striped" style="text-align: center;">
									<thead>
										<tr>
											<th>
												<small class="text-muted"><fmt:formatNumber value="${product.price}" pattern="#,###"/> \</small>
											</th>
											<th>조회수 : ${product.view_CNT }</th>
											<th><a class="btn btn-secondary" href="/productDetail?product_id=${product.id}">See Detail</a></th>
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
			${paging.mkPageing()}		
		</div>
	</div>

</body>
</html>