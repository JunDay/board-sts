<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pro Board 로그인</title>
</head>
<body>
	<div><a href="/boardList">뒤로가기</a></div>
	<form method="post" action="/login">
		<div>ID : <input id="memberId" name="memberId" type="text"></div>
		<div>PW : <input id="memberPw" name="memberPw" type="password"></div>
		<button id="loginBtn" type="submit">login</button>
	</form>
	<div><a href="/joinMember">회원가입</a></div>
</body>
</html>