<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteMember</title>
</head>
<body>
	<h1>회원 탈퇴</h1>
	<div>관리자 : ${loginMember.memberId}</div>
	<form method="post" action="/admin/deleteMember">
		<div>선택된 회원 : <input id="memberNo" name="memberNo" value="${memberNo}" readonly></div>
		<div>회원 ID : <input id="memberId" name="memberId" type="text"></div>
		<div>회원 PW : <input id="memberPw" name="memberPw" type="password"></div>
		<button type="submit">강제탈퇴</button>
	</form>
</body>
</html>