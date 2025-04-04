<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미디어쿼리</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		
		<h1>board</h1>
	${sessionScope.lname }님 반갑습니다. / ${sessionScope.lid }
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>읽음수</td>
			<td>날짜</td>
		</tr><c:forEach items="${board }" var="b">
		<tr onclick="location.href='./detail.do?bno=${b.bno}'">
			<td>${b.bno }</td>
			<td>${b.btitle } <small>${b.commentcount }</small></td>
			<td>${b.name }</td>
			<td>1</td>
			<td>${b.boarddate }</td>
		</tr></c:forEach>
	</table>
	</div>
</body>
</html>