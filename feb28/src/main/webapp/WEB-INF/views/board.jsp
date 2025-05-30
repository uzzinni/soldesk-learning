<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드</title>
<link rel="stylesheet" href="css/menu.css">
<style type="text/css">
table {
	width: 800px;
	margin: 0 auto;
	height: 500px;
	border-spacing: 0px;
	margin-bottom: 10px;
	background-color: gray;
}

tr:hover {
	background-color: #c0c0c0;
}

td{
	text-align: center;
	border-bottom: 1px solid black;
}
.row1{

}
.row3{
	width: 50%;
	text-align: left;
}

</style>
<script type="text/javascript">
// 자바스크립트 영역
function detail(no){
	//console.log(no + 'detail을 클릭했습니다.');
	location.href="./detail?board_no=" + no;
}
</script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>board</h1>
	서버에서 준 이름 : ${name1 }
	
	<c:choose>
		<c:when test="${sessionScope.user_name eq null }">
			<h2>로그인 해주세요.</h2>
		</c:when>
		<c:otherwise>
			<h2>${sessionScope.user_name }님 반값습니다.</h2>	
		</c:otherwise>
	</c:choose>
	
	
	
	<!-- EL -->
	<hr>
	<!-- JSTL로 찍겠습니다 -->

	for(BoardDTO dto : list){ syso(dto); } fn length사용하기 ${fn:length(list) }개
	글이 있습니다.
	<!-- 
	> gt
	< lt
	>= ge
	<= le
	== eq
	!= ne
	empty
	not empty
	 -->
	<c:choose>
		<c:when test="${fn:length(list) gt 0 }">
			<table>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>글쓴이</td>
					<td>날짜</td>
					<td>좋아요</td>
				</tr>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td class="row1">${dto.board_no }</td>
						<td class="row3" onclick="detail(${dto.board_no })">${dto.board_title }</td>
						<td class="row1">${dto.user_name }</td>
						<td class="row1">${dto.board_date }</td>
						<td class="row1">${dto.board_like }</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			데이터가 없습니다
		</c:otherwise>
	</c:choose>
	<button onclick="location.href='./write'">글쓰기</button>



	<hr>
	<c:forEach begin="2" end="9" var="row">
		<c:forEach begin="1" end="9" var="in">
			${row } X ${in } = ${row * in }<br>
		</c:forEach>
		<br>
	</c:forEach>
</body>
</html>