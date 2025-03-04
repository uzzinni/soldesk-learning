<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="menu">
		<ul>
			<li><a href="./"><img alt="" src="img/home.png"></a></li>
			<li><a href="./board">보드</a></li>
			<li><a href="./notice">공지</a></li>
			
			<c:choose>
				<c:when test="${sessionScope.user_name eq null }">
					<li><a href="./login">로그인</a></li>
				</c:when>
				<c:otherwise>	
					<li><a href="./myinfo">${sessionScope.user_name }님반갑습니다.</a></li>
					<li><a href="./logout">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			
		</ul>
	</div>