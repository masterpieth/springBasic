<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ol, ul {
		list-style: none;
	}
	.d-flex {
		display: flex;
		-ms-display: flexbox;
	}
	.title {
		cursor: pointer;
		color: blue;
	}
	.pagination {
		display: flex;
		-ms-display: flexbox;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.js"></script>
<script>
	$(function() {
		setDataList();
	});
	
	function setDataList() {
		$.ajax({
			method : 'post',
			data : $('form[name=searchForm]').serialize(),
			url : '/basic/intro/boardList',
			async : false,
			success : function(data) {
 				if(data.dataList.length > 0) {
					setDataListUl(data.dataList);
					pagination(data);
				}
			}
		});
	}
	
	function setDataListUl(list) {
		var content = '';
		
		$.each(list, function(index, item) {
			content += '<li class="d-flex">';
			content += 		'<div>' + (index + 1) + '</div>';
			content += 		'<div class="title">' + item.USERNAME + '</div>';
			content += 		'<div>' + item.AUTHORITY + '</div>';
			content += '</li>';
		});
		
		$('#dataList').html(content);
	}
	function pagination(data) {
		
		$('#pagination').empty();
		$('#pagination').removeData("twbs-pagination");
		
		var startPage = $('#startPage').val();
		var visiblePages = 10;
		var perPageNum = $('select[name=perPageNum]').val();
		var totalPages = Math.ceil(data.totalCount / perPageNum);
		
		console.log(startPage + ' , ' + totalPages + ' , ' + visiblePages);
		
		$('#pagination').twbsPagination({
	        visiblePages : visiblePages,
	        totalPages: totalPages,
	        startPage: startPage,
	        first : "<<",
		    prev : 'prev',
			next : 'next',
			last : ">>",
	        initiateStartPageClick : false,
	        onPageClick: function (event, page) {
	        	$('#startPage').val(page);
	        	setDataList();
	        }
		});
	}
	
</script>
</head>
<body>
	<form name="searchForm" method="post">
		<input type="text" name="username" placeholder="검색 ID 입력">
		
		<c:choose>
			<c:when test="${search.startPage != '' && search.startPage != null}">
				<input type="hidden" id="startPage" name="startPage" value="${search.startPage }">
			</c:when>
			<c:otherwise>
				<input type="hidden" id="startPage" name="startPage" value="1">
			</c:otherwise>
		</c:choose>
		
		<select name="perPageNum">
			<option value="10" <c:if test="${search.perPageNum eq '10'}">selected</c:if>>10개씩</option>
			<option value="20" <c:if test="${search.perPageNum eq '20'}">selected</c:if>>20개씩</option>
			<option value="30" <c:if test="${search.perPageNum eq '30'}">selected</c:if>>30개씩</option>
		</select>
		<input type="button" value="검색" onclick="setDataList()">
		
		<div>
			<ul class="d-flex">
				<li>순번</li>
				<li>아이디</li>
				<li>권한</li>
			</ul>
			<ul id="dataList"></ul>
		</div>
		
		<div class="d-flex" id="pagination"></div>
	</form>
</body>
</html>