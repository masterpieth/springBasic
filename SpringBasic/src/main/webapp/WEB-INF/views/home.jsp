<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style type="text/css">
	.customClass_ja {
		background-color: red;
		padding: 20px;
	}
	.customClass_ko {
		background-color: blue;
		padding: 20px;
	}
	.customClass_en {
		background-color: grey;
		padding: 20px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	
	app.controller('mainController', function($scope, $http, $window) {
		//language 옵션
		$scope.langOptions= ['ko','ja','en'];
		
		$scope.responseLang = 'customClass_' + '${pageContext.response.locale.language}';
		
		$scope.changeLang = function () {
			$http({
				method: 'GET',
				url: '<c:url value="/changeLang"/>',
				params : {'lang' : $scope.lang}
			}).then(function successCallback(response) {
				$window.location.reload();
			})
		}
	});
</script>
</head>
<body ng-app="myApp">
	
	<div ng-controller="mainController">
		<select ng-model="lang" ng-options="x for x in langOptions" ng-change="changeLang()"></select>
		<div ng-model="responseLang" ng-class="'{{responseLang}}'"></div>
	</div>
	<spring:message code="main.title"/>
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
			<a href="<c:url value="/intro/board"/>">페이징 복기</a>
			<a href="<c:url value="/intro/boardJsVer"/>">페이징 복기(twbs)</a>
			<a href="<c:url value="/intro/boardAngularVer"/>">페이징 복기(Angular)</a>
		</li>
		<li>
			<a href="<c:url value="/admin/adminHome"/>">관리자 홈</a>
		</li>
	</ul>
</body>
</html>