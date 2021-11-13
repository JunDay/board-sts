<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>게시글 목록</h1>
	<div>
		<c:if test="${loginMember == null}">
			<a href="/login">로그인</a>
			<a href="/joinMember">회원가입</a>
		</c:if>
		<c:if test="${loginMember != null}">
			<a href="/logout">로그아웃</a>
		</c:if>
	</div>
	<div>
		<a href="/addBoard">게시글 추가</a>
	</div>
	<div>
		카테고리 : 
		<select id="boardCategory" name="boardCategory" onchange="categorySelect()">
			<option value="">카테고리 선택</option>
			<c:forEach items="${categoryList}" var="c">
				<option value="${c.categoryName}">${c.categoryName}</option>
			</c:forEach>
		</select>
		<a id="serchCategory" href="/boardList">검색</a>
	</div>
	<table border="1">
		<tr>
			<td>글 번호</td>
			<td>카테고리</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.boardNo}</td>
				<td>${board.boardCategory}</td>
				<td>
					<a href="/boardOne?boardNo=${board.boardNo}">${board.boardTitle}</a>
				</td>
				<td>${board.memberId}</td>
				<td>${board.boardDate}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${currentPage > 1}" >
			<a href="boardList?currentPage=${currentPage-1}">이전</a>
		</c:if>
		<c:if test="${currentPage < lastPage}" >
			<a href="boardList?currentPage=${currentPage+1}">다음</a>
		</c:if>
	</div>
</body>
<script>
function categorySelect(){  
	var boardCategory = document.getElementById("boardCategory");
	// select element에서 선택된 option의 value가 저장된다.
	var categoryValue = boardCategory.options[boardCategory.selectedIndex].value;
	$('#serchCategory').attr('href', '/boardList?boardCategory='+categoryValue);
}
</script>
</html>