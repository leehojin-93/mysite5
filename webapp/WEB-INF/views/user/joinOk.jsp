<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<link href="${ pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<!-- //header -->
		<!-- //nav -->

		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/includes/asideUser.jsp" />
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinOK">
					
						<p class="text-large bold">
							${ param.name }(${ param.id })님 회원가입을 축하합니다.<br>
							<br>
							<a href="${ pageContext.request.contextPath }/user/loginForm" >[로그인하기]</a>
						</p>
							
					</div>
					<!-- //joinOK -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>