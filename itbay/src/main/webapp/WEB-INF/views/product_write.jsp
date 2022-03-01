<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>상품 등록</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<link type="text/css" rel="stylesheet" href="/static/css/write.css">
	<meta charset="UTF-8">
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#cancel").click(function(){
				location.href = "/product";
			})
			
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
<!-- 	<div class="logo">
        <a href="/board">
        	<img src = "/static/icon/logo.jpg" alt="No Image" width = "150" height = "150">
        </a>
    </div> -->
 	<br>
 	<br>
 	<br>
 	<div class="container">

	 	<form action="/productSave" method="post" enctype="multipart/form-data">
			<div class="change">
				<button type="submit" class="submit-btn btn btn-primary">등록</button>
				<button type="button" id="cancel" class="cancle-btn btn btn-danger">취소</button>
			</div>
		<br>
	 	  <div class="form-group">
		    <label for="subject" style="font-weight: bold;">제품명</label>
		    <input type="text" class="form-control" id="subject" name="subject" placeholder="제품명을 입력하세요." required>
		  </div>
		  
		  <br>
		  
	 	  <div class="form-group">
		    <label for="price" style="font-weight: bold;">가격</label>
		    <input type="number" class="form-control" id="price" name="price" placeholder="제품 가격을 입력하세요." required>
		  </div>		  
	
		  <br>
		  
	 	  <div class="form-group">
		    <label for="contents" style="font-weight: bold;">제품 상세 설명</label>
		    <textarea class="form-control" id="contents" name="contents" placeholder="제품상세 설명을 입력하세요." required style="height: 25.25em; resize: none;"></textarea>
		  </div>		  
	
		  <br>
		  
	 	  <div class="form-group">
		    <label for="file" style="font-weight: bold;">이미지 첨부</label>
		    <input class="form-control" type="file" name="file" id="file" multiple="multiple">
		  </div>			  
	    </form> 	
 	</div>
 	

</body>
</html>