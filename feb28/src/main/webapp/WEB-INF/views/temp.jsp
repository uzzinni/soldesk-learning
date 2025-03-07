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
.detail-user img{
	vertical-align: middle;
	height: 30px;
}
.detail-date{
	text-align: right;
}
.detail-content{
	margin: 10px;
}
</style>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="detail-div">
	<div class="detail-title">
		${detail.title }
	</div>
	<div class="detail-info">
		<div class="detail-user">
			${detail.u_name }님이 씀
			<c:if test="${detail.u_id eq sessionScope.user_id }">
			
			<img onclick="del()" alt="삭제하기" src="./img/delete.png">		
			<img id="edit" alt="수정하기" src="./img/edit.png">		
			</c:if>
			
		</div>
		<div class="detail-date">
			${detail.date }
		</div>
	</div>
	<div class="detail-content">
		${detail.content }
	</div>
</div> <!-- detail-div 끝 -->

${detail.like }<br>
</body>
</html>