<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
<link rel="stylesheet" href="css/menu.css"><link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Single+Day&display=swap" rel="stylesheet">
<style type="text/css">
.write-div {
	width: 80%;
	min-height: 300px;
	height:auto;
	background-color: #c0c0c0;
	margin: 0 auto;
	padding: 10px;
}
#title {
 width: calc(100% - 10px);
 height: 30px;
 color: green;
 padding-left: 10px;
 border: 0px;
 /* text-align: center; */
 font-size: xx-large;
 font-family: "Single Day";
}

#content {
	width: 100%;
	height: 300px;
	border: 0;
	margin: 10px 0;
	resize: none;
	overflow: auto;
}

#write-btn {
	width: 100%;
	height: 30px;
}

</style>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<h1>글 수정하기</h1>
	
	<div class="write-div">
		<form action="./write1" method="post">
			<input name="board_title" id="title" class="" placeholder="제목을 입력하세요." required="required" value="${update.board_title} }">
			<textarea name="board_content" id="content" class="" placeholder="내용을 입력하세요">${update.board_content}</textarea>
			<input type="hidden" name="board_no" value="${update.board_no }">
			<button type="submit" id="write-btn">수정하기</button>
		</form>
	</div>
</body>
</html>
