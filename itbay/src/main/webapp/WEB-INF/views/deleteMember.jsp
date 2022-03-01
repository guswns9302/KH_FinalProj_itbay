<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/module/default_js_css.jsp"
	flush="false" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>회원 탈퇴</title>

</head>
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

<body>

	<section class="container">
		<div style="margin: 10px;">
			<h2>회원 탈퇴</h2>
			<div class="card border-dark" style="margin: 50px;">
				<div class="card-body text-dark">
					<form name="deleteForm" id="deleteForm" method="post"
						onsubmit="return false;">
						<input type="hidden" id="id" name="id" value="${loginData.id}" />
						<div class="row mb-3" style="margin: 10px;">
							<label class="col-sm-2 col-form-label" for="nickname">비밀번호</label>
							<div class="col-sm-8">
								<input class="form-control" type="password" name="pw" id="pw"
									maxlength="30" placeholder="비밀번호를 입력해주세요.">
							</div>
							<button type="submit" onclick="javascript:deleteMember()" value="회원 탈퇴"
								class="btn btn-secondary text-uppercase" style="width: 100px;">회원 탈퇴</button>
						</div>
						<div>
						<!--<input type="submit" value="회원 탈퇴" onclick="javascript:deleteMember()"> -->
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	function deleteMember() {
		const f = document.deleteForm;
		f.action = "/deleteMember.do";
		f.submit();
	}
</script>
</html>
