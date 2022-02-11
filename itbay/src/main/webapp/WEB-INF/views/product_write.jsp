<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>상품 등록</title>
	<c:url var="head_url" value="/WEB-INF/views/module/default_js_css.jsp"></c:url>
	<jsp:include page="${head_url }" flush="false" />
	<link type="text/css" rel="stylesheet" href="/static/css/write.css">
	<meta charset="UTF-8">
</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/module/top-navigation.jsp" flush="false" >
		<jsp:param name="login" value="${sessionScope.login }" />
	</jsp:include>
</header>
	<div class="logo">
        <a href="/board">
        	<img src = "/static/icon/logo.jpg" alt="No Image" width = "150" height = "150">
        </a>
    </div>
 	<h2>게시글 작성</h2>
 	<form action="/productSave" method="post" enctype="multipart/form-data">
 		<div class="write">
        	<table>
            	<tr>
                	<td>제목</td>
                	<td><input type="text" name="subject" required></td>
            	</tr>
            	<tr>
                	<td>가격</td>
                	<td><input type="text" name="price" required></td>
            	</tr>            	
           	 	<tr>
               		<td>내용</td>
               		<td><textarea name="contents"></textarea></td>
           		</tr>
           		<tr>
                	<td>첨부파일</td>
                	<td>
                		<input type="file" name="file" multiple="multiple"><%-- 파일 업로드 --%>
                	</td>
            	</tr>
        	</table>
        	
        	<div class="profile">
            <%--	<p class="p1"><%=session.getAttribute("login") %> 님</p>     --%>
            	<p class="p2">환영합니다.</p>
            	<ul>
                	<li><a href="/logout">로그아웃</a></li>
                	<li><a href="/mypage">마이페이지</a></li>
            	</ul>
        	</div>
    	</div>
     	<div class="change">
        	<button type="submit" class="submit-btn">등록</button>
            <button type="reset" class="cancle-btn">취소</button>
    	</div>
    </form>
</body>
</html>