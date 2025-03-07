<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
<style type="text/css">
.detail-div{
	width: 90%;
	height: 500px;
	margin: 0 auto;
	background-color: #c0c0c0;
	border-radius: 10px;
	margin-top: 10px;
}
.detail-title{
	width: 100%;
	height: 50px;
	font-size: xx-large;
	line-height: 50px;
}
.detail-info{
	width: 100%;
	height: 50px;
	background-color: white;
}
.detail-user, .detail-date{
	width: 50%;
	float: left;
	height: 50px;
	line-height: 50px;
}
.detail-date{
	text-align: right;
}
</style>
<script type="text/javascript">
window.onload = function() {
	//document.getElementById('edit').addEventListener('click', edit);
	document.getElementById('edit').onclick = function() {edit();};
	//alert(document.getElementById('edit').src);	//이미지 경로 보기
}

function edit() {
	if(confirm('수정하시겠습니까?')) {
		location.href="./update?board_no=${detail.board_no }";
	}
}

function del() {
	//alert('삭제를 눌렀습니다.');
	if(confirm('이 글을 삭제하시겠습니까?')) {
		//alert('${detail.board_no }번 글을 삭제합니다.');
		//location.href="";
		//스크립트가 post 전송하기
		let form = document.createElement('form');	//form 생성
		form.setAttribute('action', './del');
		form.setAttribute('method', 'post');
		
		//input 구성요소 만들기
		let input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', 'board_no');
		input.setAttribute('value', ${detail.board_no});
		
		//form 속에 input 붙이기
		form.appendChild(input);
		
		//html 속 body에 form 붙이기
		document.body.appendChild(form);
		
		form.submit();
		
	}
}
</script>
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="detail-div">
	<div class="detail-title">
		${detail.board_title }
	</div>
	<div class="detail-info">
		<div class="detail-user">
			${detail.user_name }님이 씀
			<c:if test="${detail.user_id eq sessionScope.user_id }">
			
			<img onclick="del()" alt="삭제하기" src="./img/delete.png">
			<img onclick="edit()" alt="수정하기" src="./img/edit.png">
			</c:if>
			
		</div>
		<div class="detail-date">
			${detail.board_date }
		</div>
		
		<div class="detail-content">
			${detail.board_content }
		</div>
	</div>
</div> <!-- detail-div 끝 -->

${detail.board_like }<br>


</body>
</html>