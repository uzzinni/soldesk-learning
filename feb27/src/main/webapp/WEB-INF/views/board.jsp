<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>board</h1>
	서버에서 온 값 : ${name1 }<br>
	map : ${title.name }<br>
	list : ${list[9] }
	<hr>
	${board[0].board_title }
	<hr>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>좋아요</th>
		</tr>
		<c:forEach items="${board }" var="row">
		<tr>
			<td>${row.board_no }</td>
			<td>${row.board_title }</td>
			<td>${row.user_name }</td>
			<td>${row.board_date }</td>
			<td>${row.board_like }</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:forEach items="${board }" var="row"> 
		${row.board_no } /
		${row.board_title } /
		${row.board_date } /
		${row.board_like } /
		${row.board_no } /
		${row.board_name }		<br>
	</c:forEach>
	
	<c:if test="${board != null }">참일 때 출력</c:if>
	<c:if test="${list[9] eq 900 }">900입니다</c:if>
	<c:if test="${list[9] gt 800 }">800보다 큽니다</c:if>
	<c:if test="${list[9] lt 910 }">910보다 작습니다</c:if>
	<c:if test="${list[9] le 910 }">910보다 작거나 같습니다</c:if>
	<c:if test="${list[9] ge 800 }">800보다 크거나 같습니다</c:if>
	<c:if test="${empty list}">리스트가 비어있습니다</c:if>
	<c:if test="${not empty board}">보드가 값이 있습니다</c:if>
	
	if(board != null) {
		// 출력하세요
	} else {
		// 데이터가 없습니다.
	}
	
	<c:choose>
		<c:when test="${not empty board }">
			출력하세요
		</c:when>
		<c:otherwise>
			데이터가 없습니다.
		</c:otherwise>
	</c:choose>
	
	// list[9]
	
	if(list[9] > 10) {
		// 10보다 큽니다.
	} else {
		// 10보다 작습니다.
	}
	
	<c:choose>
		<c:when test="${list[9] gt 10 }">
			10보다 큽니다.
		</c:when>
		<c:otherwise>
			10보다 작습니다.
		</c:otherwise>
	</c:choose>
	
	<c:set var="tt" value="티티"></c:set>
	${tt }
	<c:out value="${tt }"></c:out>
	
	<%--
	<c:redirect url="./">
		<c:param name="board_no" value="15"></c:param>
	</c:redirect>
	--%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>