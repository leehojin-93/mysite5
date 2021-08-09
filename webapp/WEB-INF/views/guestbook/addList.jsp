<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>

<link href="${ pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<!-- //header -->
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/includes/asideGuestbook.jsp" />
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${ pageContext.request.contextPath }/guestbook/add" method="post">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<c:choose>
										<c:when test="${ !empty sessionScope.authUser }">
											<td><input id="input-uname" type="text" name="name" value="${ sessionScope.authUser.name }"></td>
										</c:when>
										<c:otherwise>
											<td><input id="input-uname" type="text" name="name" value=""></td>
										</c:otherwise>
									</c:choose>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5" style="resize: none;"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						
					</form>
					<!-- //guestWrite -->	
					
					<c:forEach items="${ requestScope.gList }" var="guestVo" varStatus="status">
					
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${ guestVo.no }</td>
							<td>${ guestVo.name }</td>
							<td>${ guestVo.date }</td>
							<td><a href="${ pageContext.request.contextPath }/guestbook/deleteForm?no=${ guestVo.no }">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${ guestVo.content }</td>
						</tr>
					</table>
					
					</c:forEach>
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->
			
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