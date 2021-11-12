<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<a href="addBoard">게시글 추가</a>
	</div>
	<table border="1">
		<tr>
			<td>boardNo</td>
			<td>boardCategory</td>
			<td>boardTitle</td>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.boardNo}</td>
				<td>${board.boardCategory}</td>
				<td>
					<a href="/boardOne?boardNo=${board.boardNo}">${board.boardTitle}</a>
				</td>
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
</html>