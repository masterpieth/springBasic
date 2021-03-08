<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

	<h3>아이디와 비밀번호를 입력하세요.</h3>
	
	<form name="loginForm" action="<c:url value="/login"/>" method="POST">
		<c:if test="${param.error != null }">
			<p>아이디와 비밀번호가 잘못되었습니다.</p>
		</c:if>
		<c:if test="${param.logout != null }">
			<p>로그아웃 하였습니다.</p>
		</c:if>
		<p>
			<label for="username">아이디</label>
			<input type="text" id="id" name="id"/>
		</p>
		<p>
			<label for="password">비밀번호</label>
			<input type="text" id="password" name="password"/>
		</p>
		<input type="submit" class="btn" value="로그인">
	</form>
</body>
</html>