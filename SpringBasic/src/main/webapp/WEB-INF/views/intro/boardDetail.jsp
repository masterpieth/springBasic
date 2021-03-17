<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>상세페이지입니다.</h1>
	<h2>화면 값은 모델의 값들로 설정합니다.</h2>
	<h3>${detail.USERNAME}</h3>
	<h3>${detail.AUTHORITY}</h3>
	
	<form name="searchForm" action="<c:url value="/intro/board"/>" method="post">
		<input type="hidden" name="curPage" value="${search.curPage }">
		<input type="hidden" name="perPageNum" value="${search.perPageNum }">
		<input type="hidden" name="username" value="${search.username }">
		<input type="submit" value="리스트">
	</form>
</body>
</html>