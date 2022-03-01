<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	
	<div class="container" style="margin-top:-50px;">
		<div class="row">
			<div class="col-sm-6">
				<section class="container p-5 my-5">
					<div class="card">
						<div style="text-align:center;">
							<img src="resources/img/${dto.img_name}" class="img-thumbnail" alt="Cinque Terre" onerror="this.src='resources/icon/itbay_logo.png'" width="350" height="350">
						</div>
					</div>
				</section>
			</div>
			
			<div class="col-sm-6">
				<section class="container p-5 my-5">
					<div class="clearfix">
						<c:if test="${sessionScope.loginMember.nickname eq dto.name }">
							<button type="button" class="btn btn-secondary text-uppercase float-start" style="width: 100px; margin-right: 10px;" onclick="location.href='/Rereview?reviewid=${dto.id}'">수정</button>
							<button type="button" class="btn btn-secondary text-uppercase float-start" style="width: 100px;" onclick="location.href='/review_boardDelete?reviewid=${dto.id}'">삭제</button>
						</c:if>
						<button type="button" class="btn btn-secondary text-uppercase float-end" style="width: 100px;" onclick="location.href='/review_board'">목록</button>
					</div>
					<div class="row" style="margin-bottom: -20px;">
						<div class="col">
							<div class="form-floating mb-3 mt-3">
								<input type="text" class="form-control"  id="writer" name="writer" value="${dto.name}" readonly>
								<label for="writer">Writer</label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating mb-3 mt-3">
								<input type="text" class="form-control"  id="dates" name="dates" value="${dto.create_date}" readonly>
								<label for="dates">Date</label>
							</div>
								<div class="col">
							<div class="form-floating mb-3 mt-3">
								<input type="text" class="form-control"  id="viewcnt" name="viewcnt" value="${dto.view_cnt}" readonly>
								<label for="viewcnt">View_cnt</label>
							</div>
						</div>
						<div class="col">
							<div class="form-floating mb-3 mt-3">
								<input type="text" class="form-control"  id="count" name="count" value="${dto.score}" readonly>
								<label for="count">Count</label>
							</div>
						</div>
					</div>
					<div class="form-floating mb-3 mt-3">
						<input type="text" class="form-control"  id="subject" name="subject" value="${dto.subject}" readonly>
						<label for="subject">Subject</label>
					</div>
					<div>
						<textarea class="form-control" rows="10" name="contents" readonly>${dto.contents}</textarea>
					</div>
					
					
					<div class="rounded border border-dark" style="margin-top: 10px;">
						<c:forEach var="comment" items="${comments}">
								<div class="form-floating mb-3 mt-3" style="padding-right: 10px; padding-left: 10px;">
									<input type="text" class="form-control"  id="subject" name="subject" value="${comment.contents}" readonly>
									<label for="subject">${comment.nickname} / ${comment.create_date}</label>
								</div>
						</c:forEach>
					</div>
					<form action="/review_comment" method="post">
						<c:choose>
							<c:when test="${empty sessionScope.loginMember.id}">
							</c:when>
							<c:otherwise>
								<div class="form-floating mb-3 mt-3">
									<input type="text" class="form-control"  name="contents">
									<label for="contents">contents</label>
								</div>
								<input type="hidden" name="review_id" value="${dto.id}">	
								<input type="hidden" name="members_id" value="${sessionScope.loginMember.id}">
								<button type="submit" class="btn btn-secondary text-uppercase float-start" style="width: 100px;">댓글 작성</button>
							</c:otherwise>
						</c:choose>
					</form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>