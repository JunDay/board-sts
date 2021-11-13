<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardOne.jsp</title>
</head>
<body>
	<h1>게시글</h1>
	<div>
		<a href="/boardList">뒤로가기</a>
		<a href="/deleteBoard?boardNo=${board.boardNo}">게시글 삭제</a>
	</div>
	<div></div>
	<table border="1">
		<tr>
			<td>boardTitle</td>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.memberId}</td>
		</tr>
		<tr>
			<td>boardContent</td>
			<td>${board.boardContent}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.boardDate}</td>
		</tr>
	</table>
	
	<h2>comments</h2>
	
	<table border="1">
		<tr>
			<td>작성자</td>
			<td>댓글</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${board.comments}" var="c">
			<tr>
				<td>${c.commentWriter}</td>
				<td>${c.commentContent}</td>
				<td><a href="/updateComment?boardNo=${board.boardNo}&commentNo=${c.commentNo}">수정</a></td>
				<td><a href="/deleteComment?boardNo=${board.boardNo}&commentNo=${c.commentNo}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="/addComment?boardNo=${board.boardNo}" method="post">
		<table border="1">
			<tr>
				<td>작성자</td>
				<td>댓글</td>
				<td>비밀번호</td>
			</tr>
			<tr>
				<c:if test="${loginMember != null}">
					<td>
						<input id="commentWriter" name="commentWriter" type="text" value="${loginMember.memberId}" readonly>
					</td>
				</c:if>
				<c:if test="${loginMember == null}">
					<td><input id="commentWriter" name="commentWriter" type="text"></td>
				</c:if>
				
				<td><textarea id="commentContent" name="commentContent" rows="5" cols="50"></textarea></td>
				<td><input id="commentPw" name="commentPw" type="password"></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>