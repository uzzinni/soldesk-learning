<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index입니다</h1>
	index<br>
	서버에서 오는 name : ${name }<br>
	서버에서 오는 toSize : ${toSize }<br>
	jstl
	<c:forEach begin="1" end="10" var="i">
		${i }<br>
	</c:forEach>
</body>
</html>