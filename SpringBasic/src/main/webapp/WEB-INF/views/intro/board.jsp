<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
<script>
	$(function() {
		if('${msg}' != '') alert('${msg}');
	});
	function prev() {
		$(':hidden[name=curPage]').val(${pageMaker.startPage} - 1);
		goSearch();
	}
	function next() {
		$(':hidden[name=curPage]').val(${pageMaker.endPage} + 1);
		goSearch();
	}
	function movePage(page) {
		$(':hidden[name=curPage]').val(page);
		goSearch();
	}
	function search() {
		$(':hidden[name=curPage]').val(1);
		goSearch();
	}
	function goSearch() {
		$('form[name=searchForm]').attr('action', '<c:url value="/intro/board"/>');
		$('form[name=searchForm]').submit();
	}
	
	function goDetail(detail) {
		$(':hidden[name=detail]').val(detail);
		$('form[name=searchForm]').attr('action', '<c:url value="/intro/boardDetail"/>');
		$('form[name=searchForm]').submit();
	}
	
</script>
</head>
<body>
	<form name="searchForm" method="post">
		<input type="text" name="username" placeholder="검색 ID 입력">
		<input type="hidden" name="detail">
		<input type="hidden" name="curPage" value="${pageMaker.cri.curPage}">
		<select name="perPageNum">
			<option value="10">10개씩</option>
			<option value="20">20개씩</option>
			<option value="30">30개씩</option>
		</select>
		<input type="button" value="검색" onclick="search()">
		
		<table class="table">
			<thead>
				<tr>
					<th>아이디</th>
					<th>권한</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(userList) gt 0}">
					<c:forEach var="user" items="${userList}">
						<tr>
							<td class="title" onclick="goDetail('${user.USERNAME}')">${user.USERNAME}</td>
							<td>${user.AUTHORITY}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="pagination">
			<ul class="d-flex">
				<c:if test="${pageMaker.prev eq true}">
					<li>
						<input type="button" value="prev" class="prev" onclick="prev()">
					</li>
				</c:if>
				<c:forEach var="page" begin="${pageMaker.startPage }" end="${pageMaker.endPage}">
					<li>
						<c:choose>
							<c:when test="${page == pageMaker.cri.curPage}">
								<input type="button" value="${page}" class="curPage page">
							</c:when>
							<c:otherwise>
								<input type="button" value="${page}" class="page" onclick="movePage(${page})">
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next eq true}">
					<li>
						<input type="button" value="next" class="next" onclick="next()">
					</li>
				</c:if>
			</ul>
		</div>
	</form>
</body>
</html>