<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
<jsp:include page="${head_url }" flush="false" />
<title>cart</title>

<script type="text/javascript">

	function detail(id){

		location.href="/productDetail?product_id="+id;

		
	}
	
	function deleteCart(id, members_id) {
		
		var loginId = document.getElementById("members_id").value;
		if(loginId == null || loginId == ""){
			alert("로그인후 이용가능합니다.");
			location.href="/login";
		} else {
			location.href="/deleteCart?id="+id+"&members_id="+members_id;
		}
	}

	
	$(document).ready(function(){

		//이벤트 #-html태그의 Id, .-html태그의 class
		$("#searchBtn").click(function(){
			var subject = $("#searchText").val();
			var sold_out = $("#sold_out").val();
			location.href = "/product?subject="+subject+"&sold_out="+sold_out;
		});
		
		
		$("#allPur").click(function(){
			
			
			var allList = new Array();

			
			<c:forEach items="${allPur}" var="item">
				allList.push("${item}");
			</c:forEach>
			
			
			console.log(JSON.stringify(allList))
			var params = JSON.stringify(allList);
			
			$.ajax({
				contentType: 'application/json',
				url: "/cart/all/pur",
				type : "post",
				dataType : "json",
				data: allList,
				success: function(data){
	
				} 
			});					
			
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
		<input type="hidden" id="members_id" value="${loginMember.getId()}">
		
		<div class="container" style="margin-top:20px;">
			<div>
				<span style="font-weight:bold">내 장바구니</span>
			</div>
			<c:set var = "sum" value = "0" />
			<c:forEach var="cart" items="${list}" varStatus="status">
				<c:set var= "sum" value="${sum + cart.price}"/>
			</c:forEach>
			<div style="font-weight: bold; float: right;">
				총 금액 : <fmt:formatNumber value="${sum}" pattern="#,###"/>원
			</div>
			<table class="table" style="text-align:center;">
				<thead class="thead-dark">
					<tr>
						<th scope="col">IMG</th>
						<th scope="col">NUN</th>
						<th scope="col">Subjcet</th>
						<th scope="col">Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${list}" varStatus="status">
					<tr style="vertical-align: middle;">
						<td scope="row" class="col-2">
							<img width="150" height="100" alt="" src="/resources/img/${cart.img_name}" onerror="this.style.display='none'">
						</td>
						<td>${cart.product_id}</td>
						<td  onclick="detail(${cart.product_id})">${cart.subject}</td>
						<td>${cart.price}</td>
						<td>
							<c:choose>
						  		<c:when test="${cart.order_status eq 'N'}">
								  	<a href="/purchase_product?product_id=${cart.product_id}" class="btn btn-secondary text-uppercase">Buy</a>
						  		</c:when>
						  		<c:otherwise>
						  			<a class="btn btn-secondary text-uppercase">Sold Out</a>
						  		</c:otherwise>
						  	</c:choose>
							<button class="btn btn-secondary text-uppercase" onclick="deleteCart(${cart.id}, ${cart.members_id})">Delete</button>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>