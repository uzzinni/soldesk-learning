<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2025-04-04 웹 서비스 구축</title>
<script type="text/javascript">
	function linkPage(pageNo){
		location.href = "./board.do?currentPageNo="+pageNo;
	}   
</script>
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
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
</body>
</html>