<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에어코리아</title>
<body>
	<h1>Air Korea</h1>
	<c:forEach items="${list }" var="row">
		${row }<br>
	</c:forEach>
	<hr>
	${map.informGrade }
	<hr>
	<img alt="" src="${map.imageUrl4 }">
	
</body>
</html>