<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="css/menu.css">

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<h1>index</h1>
	
	메뉴 
	아이콘 | 보드 | 공지 | .... | 로그인
	css, img
	
	
	<a href="./board">board</a>
</body>
</html>

<!-- 

ctrl + shift + ?

jsp:include page 경우
	클라이언트단에서 합쳐진다.
	menu.jsp에 변수를 같이 사용할 수 없다.	

< % @ include file="menu.jsp" %>일 경우
	서버단에서 포함되고 읽어집니다.
	menu.jsp에 변수를 같이 사용할 수 있습니다.

 -->



