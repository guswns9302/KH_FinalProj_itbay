<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>공지사항</title>
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
	<section class="container p-5 my-5">
		<button type="submit" class="btn btn-secondary text-uppercase" style="width: 183px;" form="formhidden">수정</button>
		<button type="button" class="btn btn-secondary text-uppercase" style="width: 183px;" onclick="location.href='/noticeDelete?noticeId=${dto.id}'">삭제</button>
		<button type="button" class="btn btn-secondary text-uppercase float-end" style="width: 183px;" onclick="location.href='/notice_board'">목록</button>
		<div class="row" style="margin-bottom:-20px">
			<div class="col">
				<div class="form-floating mb-3 mt-3">
					<input type="text" class="form-control"  id="writer" name="writer" value="${dto.username}" readonly>
					<label for="writer">Writer</label>
				</div>
			</div>
			<div class="col">
				<div class="form-floating mb-3 mt-3">
					<input type="text" class="form-control"  id="date" name="date" value="${dto.create_date} " readonly>
					<label for="date">Date</label>
				</div>
			</div>
			<div class="col">
				<div class="form-floating mb-3 mt-3">
					<input type="text" class="form-control"  id="count" name="count" value="${dto.view_CNT}" readonly>
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
		<form action="/noticeModify" id="formhidden">
			<input type="hidden" name="noticeId" value="${dto.id}">
			<input type="hidden" name="subject" value="${dto.subject}">
			<input type="hidden" name="contents" value="${dto.contents}">
		</form>
	</section>
</body>
</html>