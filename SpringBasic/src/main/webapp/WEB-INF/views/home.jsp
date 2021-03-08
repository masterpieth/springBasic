<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	
	<h1>Home</h1>
	<sec:authorize access="isAnonymous()">
		<p>
			<a href="<c:url value="/login/loginForm"/>">로그인</a>
		</p>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="로그아웃">
		</form>
	</sec:authorize>
	
	<ul>
		<li>
			<a href="<c:url value="/intro/introduction"/>">소개 페이지</a>
		</li>
		<li>
			<a href="<c:url value="/admin/adminHome"/>">관리자 홈</a>
		</li>
	</ul>
</body>
</html>