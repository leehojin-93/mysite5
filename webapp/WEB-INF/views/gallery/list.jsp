<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

						<c:if test="${ sessionScope.authUser != null }">
						<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<%-- data-name="${ sessionScope.authUser.name }" === Ajax 사용시 --%>
						<div class="clear"></div>
						


						<ul id="viewArea">
							<c:forEach items="${ requestScope.galleryList }" var="galleryVo" varStatus="status">
							<!-- 이미지반복영역 -->
							<li>
								<div class="view">
									<img class="imgItem" src="${ pageContext.request.contextPath }/upload/${ galleryVo.saveName }" data-no="${ galleryVo.no }" data-userno="${ sessionScope.authUser.no }">
									<div class="imgWriter">
										작성자: <strong>${ galleryVo.name }</strong>
									</div>
								</div>
							</li>
							<!-- 이미지반복영역 -->
							</c:forEach>

						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->









	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form action="${ pageContext.request.contextPath }/gallery/add" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text" for="addModalContent">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text" for="addFile">이미지선택</label> <input id="addFile" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<%-- <input type="hidden" name="userNo" value="${ sessionScope.authUser.no }">
						<input type="hidden" name="name" value="${ sessionScope.authUser.name }"> --%>
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div id="modalBody-view" class="modal-body">

				</div>
				<form method="post" action="">
					<div class="modal-footer">
						<button id="viewModalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						
					<%-- <c:if test="${ sessionScope.authUser.no eq ' + galleryVo.userNo + ' }"> --%>
						<button type="submit" class="btn btn-danger" id="btnDel">삭제</button>
					<%-- </c:if> --%>
					</div>

				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">

	$('#btnImgUpload').on('click', function(){
		// Ajax 사용시
		// var name = $(this).date("name");
		// $('#addModalName').val(name);
		
		$('#addModalContent').val('');
		$('#addFile').val('');
		
		$('#addModal').modal();
	});
	
	$('.imgItem').on('click', function(){ // $('#gallery').on('click', '.imgItem' function(){ == list를 Ajax로 출력시
		var viewNo = $(this).data("no");
		var userNo = $(this).data("userno");
		
		$('.viewModalImg').remove();
		$('#viewModalContent').remove();
		$('[name="view-userNo"]').remove();
	
		$.ajax({
			url: "${ pageContext.request.contextPath }/gallery/view",
			type: "post",
			
			data: { no: viewNo },
			success: function(galleryVo) {
				$('#viewModal').modal();
				
				render(galleryVo);
			},
			
			error: function(XHR, status, error) {
				console.error(status + ' : ' + error);
			}
		});
		if (userNo != galleryVo.userNo) {
			$('#btnDel').hide();
		} else {
			$('#btnDel').show();
		}
		
	});
	
	function render(galleryVo) {
		var str = '<img id="vI-' + galleryVo.no + '" class="viewModalImg" src="${ pageContext.request.contextPath }/upload/' + galleryVo.saveName + '">'
				+ '<div class="formgroup">'
				+ 		'<p id="viewModalContent">' + galleryVo.content + '</p>'
				+ '</div>'
//				+ '<input type="text" name="view-userNo" value="' + galleryVo.userNo + '">'
		
		$('#modalBody-view').append(str);
	};

	$('#viewModalCloseBtn').on('click', function(){
		$('#viewModal').modal('hide');
	})
	
	$('#btnDel').on('click', function(){
		event.preventDefault();
		
		var delNo = $(this).date("no");
		console.log(delNo);
		
		$.ajax({
			url: "${ pageContext.request.contextPath }/gallery/delete"
		});
	})
	
</script>

</html>

