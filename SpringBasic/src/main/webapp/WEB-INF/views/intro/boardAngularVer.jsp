<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.table {
		width: 50%;
		border: 1px solid black;
		border-collapse: collapse;
	}
	.table th,td {
		text-align: center;
		border: 1px solid black;
	}
	.d-flex {
		display: flex;
		-ms-display: flexbox;
	}
	ol, ul {
		list-style: none;
	}
	.curPage {
		color: red;
	}
	.title {
		cursor: pointer;
		color: blue;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	
	app.controller('searchController', function($scope, $http, $window) {
		$scope.perPageOptions = [10,20,30];
		
		$scope.search = function() {
			$http({
				method: 'POST',
				url: '/basic/intro/AngBoardList',
				params: {
					'perPageNum' : $scope.perPageNum === undefined ? 10 : $scope.perPageNum,
					'curPage' : $scope.curPage,
					'username' : $scope.username
				}
			}).then(function successCallback(response) {
				
				//데이터 리스트
				$scope.dataList = response.data.userList;
				
				//페이징 관련 시작
				var pageMaker = response.data.pageMaker;
				
				$scope.prev = pageMaker.prev;
				$scope.next = pageMaker.next;
				
				$scope.curPage = pageMaker.cri.curPage;
				
				$scope.pages = new Array();
				
				for(var i = pageMaker.startPage; i <= pageMaker.endPage; i++) {
					$scope.pages.push(i);
				}
				
				$scope.goPrev = function() {
					$scope.curPage = pageMaker.startPage - 1;
					$scope.search();
				};
				
				$scope.goNext = function() {
					$scope.curPage = pageMaker.endPage + 1;
					$scope.search();
				}
				$scope.movePage = function(page) {
					$scope.curPage = page;
					$scope.search();
				}
				//페이징 관련 끝
			}, function errorCallback(response) {
			});
		}
		
		$window.onload = $scope.search();
	});
</script>
</head>
<body ng-app="myApp">
	
	<div ng-controller="searchController">
		<form>
			<input type="text" ng-model="username" placeholder="검색 ID 입력">
			<select ng-model="perPageNum" ng-options="x for x in perPageOptions"></select>
			<input type="hidden" ng-model="curPage">
			<input type="button" value="검색" ng-click="search()">
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>아이디</th>
					<th>권한</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="x in dataList">
					<td class="title" ng-click="">{{x.USERNAME}}</td>
					<td>{{x.AUTHORITY}}</td>
				</tr>
			</tbody>
		</table>
		<div class="pagination">
			<ul class="d-flex">
				<li ng-show="prev" >
					<input type="button" class="prev" ng-click="goPrev()" value="<<">
				</li>
				<li ng-model="pages" ng-repeat="x in pages">
					<input type="button" value="{{x}}" ng-click="movePage(x)" ng-class="{'curPage': x == curPage}">
				</li>
				<li ng-show="next">
					<input type="button" class="next" ng-click="goNext()" value=">>">
				</li>
			</ul>
		</div>
	</div>
</body>
</html>