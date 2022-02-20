<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>중고 상품</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<link type="text/css" rel="stylesheet" href="/static/css/write.css">	
	<style type="text/css">
		.navbar-nav me-auto{
		    margin-right: auto!important;
		}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			var master = $("#masterYn").val();
			if(master == "master"){
				$(".masterInput").removeAttr("readonly");
			}
			
			$("#golist").click(function(){
				location.href = "/product";
			});
			
			$("#reload").click(function(){
				location.reload();
			});
			
			$("#delete").click(function(){
				location.href = "/productDelete?id="+$("#boardId").val();
			});
			
			
			
			$("#gocart").click(function(){
				
				var boardId= $("#boardId").val();
				
				$.ajax({
					url: "/add/cart",
					type: "POST",
					dataType: "json",
					data: {
						id : boardId
					},
					success: function(data){
						if(data){
							alert("장바구니에 저장되었습니다.");
						}
					} 
				});				
				
			});
		
		});
		
	</script>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
			<jsp:param name="login" value="${sessionScope.login }" />
		</jsp:include>
	</header>

	
	
	
	
	<input type="hidden" id="masterYn" value="${loginMember.getNickname()}">

	<br><br><br>
				
	<div class="wrapper" style="height: 400px;">
		<div class="container" style="min-width: 640px; max-width: 640px;">
			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="width: 640px;">
			  <div class="carousel-inner" style="width: 640px;">
				<c:forEach var="img" items="${imgList}" varStatus="status">
					<c:if test="${status.index == 0}">
						<div class="carousel-item active">
							<img class="d-block w-100" src="/resources/img/${img.img_name}" width="640">
						</div>
					</c:if>
					<c:if test="${status.index != 0}">
					<div class="carousel-item">
						<img class="d-block w-100" src="/resources/img/${img.img_name}" width="640">
					</div>
					</c:if>
				</c:forEach>	
			  </div>
			  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>				
		</div>

	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> 
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<br><br>
	<br><br>
	<form action="/productUpdate" method="post">
 	<div class="container">
 	  <div class="form-group">
	    <label for="boardId" style="font-weight: bold;">제품번호</label>
	    <input type="text" class="form-control" id="boardId" name="id" value="${productDto.id}" readonly="readonly">
	  </div>
	  <br>
 	  <div class="form-group">
	    <label for="subject" style="font-weight: bold;">제품명</label>
	    <input type="text" class="form-control masterInput" name="subject" id="subject" value="${productDto.subject}" readonly="readonly">
	  </div>
	  <br>
 	  <div class="form-group">
	    <label for="price" style="font-weight: bold;">제품 가격</label>
    	<input type="text" class="form-control masterInput" name="price" id="price" value="${productDto.price}" readonly="readonly">
	  </div>
	  <div style="float:right">
	  	<c:choose>
	  		<c:when test="${login && productDto.sold_out eq 'N'}">
			  	<a href="/purchase_product?product_id=${productDto.id}" class="btn btn-secondary text-uppercase">Buy</a>
			  	<button type="button" id="gocart" class="submit-btn btn btn-secondary">장바구니</button>
	  		</c:when>
	  		<c:when test="${login && productDto.sold_out eq 'Y' }">
				<a href="/product" class="btn btn-secondary text-uppercase">Sold Out</a>
	  		</c:when>
	  		<c:otherwise>
	  			<a href="/login" class="btn btn-secondary text-uppercase">Buy</a>
	  			<button type="button" id="gocart" class="submit-btn btn btn-secondary">장바구니</button>
	  		</c:otherwise>
	  	</c:choose>
	  </div>
	  <br>
	  <br>
	  <br>
 	  <div class="form-group">
	    <label for="contents" style="font-weight: bold;">제품 상세 설명</label>
	    <textarea class="form-control masterInput" name="contents" id="contents" style="height: 25.25em; resize: none; text-align: left;" readonly="readonly">
	    	${productDto.contents}
	    </textarea>
	  </div>		  
	  <div>
		<c:if test="${loginMember.getNickname() eq 'master'}">
			<button class="btn btn-primary" type="submit">수정</button>
			<button class="btn btn-danger" type="button" id="delete">삭제</button>
		</c:if>	  
	  	<button type="button" id="golist" class="submit-btn btn btn-secondary" style="float:right">목록으로</button>
	  </div>
 	</div>
 	</form>


		
		
</body>
</html>