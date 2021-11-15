<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteCategory</title>
</head>
<body>
	<h1>카테고리 삭제</h1>
	<div>관리자 : ${loginMember.memberId}</div>
	<form method="post" action="/admin/deleteCategory">
		<div>선택된 카테고리명 : <input id="categoryName" name="categoryName" value="${categoryName}" readonly></div>
		<div>관리자 ID : <input id="memberId" name="memberId" type="text"></div>
		<div>관리자 PW : <input id="memberPw" name="memberPw" type="password"></div>
		<button type="submit">카테고리 삭제</button>
	</form>
	<div>총 게시글 개수 : ${totalBoardCount}</div>
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
</body>
</html>