<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax방명록</title>

<link href="${ pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>


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
					<h3>Ajax방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">Ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="post">
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
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="pass"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea id="input-content" name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnAdd" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->

					</form>

					<div id="listArea">
						<!-- Ajax List Area -->
					</div>
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
	
	
<!--         ---                    삭제 모달 창             ---                -->
<!-- 삭제 모달 창 -->
	<div id="delModal" class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">방명록 글 삭제</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- <p>One fine body&hellip;</p> -->
	      	
	      	<label for="modalPassword">비밀번호</label>&nbsp;
	      	<input id="modalPassword" type="password" name="password">
	      	
	      	<input id="modalNo" type="hidden" name="no" value="">
	        
	      </div>
	      <div class="modal-footer">
	        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


</body>

<script type="text/javascript">

	// $(document).ready(함수); == 화면이 Browser에 출력되기 전에 실행
	$(document).ready(function(){
		//Ajax 요청하기 - 함수
		fetchList();

	});
	
	// DB에서 리스트 가져오기
	function fetchList(){
		$.ajax({
			url: "${ pageContext.request.contextPath }/api/guestbook/list",		
			type: "post",
			/*
			contentType: "application/json",
			data: {name: "홍길동"},
	
			dataType: "json",
			*/
			success: function(guestbookList){ // url의 반환값을 json형식으로 대입
				/*성공시 처리해야될 코드 작성*/
				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");
				}
			},

			error: function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
	
	// 방명록 1개씩 렌더링
	// 반복되는 렌더링내부 HTML에 id값 작성X == id가 반복되기 때문에
	function render(guestbookVo, type) {
		var str = '<table id="t-' + guestbookVo.no + '" class="guestRead">'
				+ 		'<colgroup>'
				+ 			'<col style="width: 10%;">'
				+ 			'<col style="width: 40%;">'
				+ 			'<col style="width: 40%;">'
				+ 			'<col style="width: 10%;">'
				+ 		'</colgroup>'
				+ 		'<tr>'
				+ 			'<td>' + guestbookVo.no + '</td>'
				+ 			'<td>' + guestbookVo.name + '</td>'
				+ 			'<td>' + guestbookVo.date + '</td>'
				+ 			'<td class="spanBtnDel"><span class="btnDel" data-no="' + guestbookVo.no + '">[삭제]</span></td>'
				+ 		'</tr>'
				+ 		'<tr>'
				+ 			'<td colspan=4 class="text-left">' + guestbookVo.content + '</td>'
				+ 		'</tr>'
				+ '</table>';
		if (type === 'down') {
			$("#listArea").append(str);
		} else if (type === 'up') {
			$("#listArea").prepend(str);
		}
		
		// a링크가 아닌 이벤트(span [삭제])를 위한 마우스커서포인터 설정
		$('.btnDel').css('cursor', 'pointer');
		$('.spanBtnDel').css('text-align', 'center');
		
	}

	// .ready() 끝나고 등록버튼 클릭할때
	$('#btnAdd').on('click', function(){
		// 기존 form 이 작동 하지 않게 하기
		event.preventDefault();
		
		// input값 읽어오기
		/*
		var name = $('#input-uname').val();
		var password = $('#input-pass').val();
		var content = $('[name="content"]').val(); // $('#input-content').val();
		*/
		var guestbookVo = {
			name: $('#input-uname').val(),
			password: $('#input-pass').val(),
			content: $('[name="content"]').val() // $('#input-content').val();
		};
		
		$.ajax({
			// url: "${ pageContext.request.contextPath }/api/guestbook/add?name="+name+"&password="+password+"content="+content,
			url: "${ pageContext.request.contextPath }/api/guestbook/add",
			type: "post",
			// contentType: "application/json",
			/*
			data: { name: name,
					password: password,
					content: content },
			*/
			data: guestbookVo, // parameter 값
			// dataType: "json",

			success: function(guestInfo){
				/*성공시 처리해야될 코드 작성*/
				render(guestInfo, "up");
				
				// 등록 후 form-input 입력칸 빈칸으로 만들기
				$('#input-uname').val("");
				$('#input-pass').val("");
				$('[name="content"]').val("");
			},

			error: function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});

	// list 삭제 버튼 클릭 --> 부모를 지정해서 위임하기 (Browser 출력이 끝난 후 list 및 add가 진행되기 때문에)
	$('#listArea').on('click', '.btnDel', function(){
		// event.preventDefault(); --> form일때만 사용 가능

		
		// hidden no값 입력하기 == html 태그에 데이터 숨기기(click에 해당하는 태그)
		// data 값 설정 소문자로, guestNo == 오류
		var no = $(this).data("no");
		$('#modalNo').val(no);
		
		// input password창 초기화 --> 다른	테이블의 삭제창에서 입력된 input값이 그대로 남아있음.
		$('#modalPassword').val('');
		
		// 모달창(Modal Window)/bootstrap - link/bootstrap.css, script/bootstrap.js 추가
		$('#delModal').modal();
	});
	
	// list삭제 - modal창 삭제 버튼 클릭
	$('#modalBtnDel').on('click', function(){
		// DB 서버에 삭제 요청
		var guestVo = {
				no: $('#modalNo').val(),
				password: $('#modalPassword').val()
		};
		
		$.ajax({
			// url: "${ pageContext.request.contextPath }/api/guestbook/delete?password=" + password + "no=" + no,
			type: "post",
			url: "${ pageContext.request.contextPath }/api/guestbook/delete",
			// contentType: "application/json",
			
			data: guestVo, // parameter 값
			// dataType: "json",
			success: function(count){
				/*성공시 처리해야될 코드 작성*/
				
				if (count === 1) {
					// list에 해당 table 삭제
					$('#t-' + guestVo.no).remove();
					
					// mdalWindow close
					$('#delModal').modal('hide');
				} else {
					// mdalWindow close
					// $('#delModal').modal('hide');
					$('#modalPassword').val('');
					alert('password wrong');
				}
				
			},

			error: function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

</script>

</html>