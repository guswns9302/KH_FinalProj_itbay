<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>후기 게시판</title>
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
<section class="container p-5 my-5" style="text-align:center;">
 	<h1 class="h3 mb-3 fw-normal">Review Notice</h1>
 	</section>
<div class="container">
 <form action='<c:url value='/Rereview_board'/>' method="post">
 
 <div class="form-group">
    <label  style="font-weight: bold;">회원 아이디</label>
	 	<input type="text" class="form-control"  name="members_id" value="${loginMember.getId()}" readonly>
	 	 <input type="hidden" name="id" value="${dto.getId()}" readonly>
	 	 </div>
	 	 
	 	   <div class="form-group">
	 	 <label  style="font-weight: bold;">제품 번호</label>
	 	 <input type="text" class="form-control" name="product_id" value="${dto.getProduct_id()}" readonly>
	 	    </div>
	 	     <div class="form-group"> 
	 	    <label  style="font-weight: bold;">제목</label>            
            <input type="text" class="form-control"  name="subject" value="${dto.getSubject()}" placeholder="제목을 작성해주세요.">
           </div>
           
           <div class="form-group">
            <label class="form-group">내용</label>
            <textarea  class="form-control" name="contents" rows="10">${dto.getContents()}</textarea>
  </div>
  
     <div class="form-group">
     <label class="form-group">평점</label>
  <input type="number"  class="form-control" name="score" value="${dto.getScore()}" min="0" max="10">
</div>
   <div class="change">
        <button  class="submit-btn btn btn-primary" type="submit">수정하기</button>
        </div>
    </form>
    </div>
</body>
</html>