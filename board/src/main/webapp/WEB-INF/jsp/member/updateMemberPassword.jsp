<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMemberPassword</title>
</head>
<body>
	<h1>비밀번호 변경</h1>
	
	<form method="post" action="updateMemberPassword">
		<input id="memberNo" name="memberNo" type="hidden" value="${member.memberNo}">
		<table border="1">
			<tr>
				<td colspan="2">회원ID</td>
			</tr>
			<tr>
				<td colspan="2"><input id="memberId" name="memberId" type="text" value="${member.memberId}"readonly></td>
			</tr>
			<tr>
				<td colspan="2">기존 비밀번호</td>
			</tr>
			<tr>
				<td colspan="2"><input id="memberPw" name="memberPw" type="password"></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td>비밀번호 확인</td>
			</tr>
			
			<tr>
				<td><input id="newMemberPw" name="newMemberPw" type="password"></td>
				<td><input id="newMemberPwCheck" name="newMemberPwCheck" type="password"></td>
			</tr>
		</table>
		<button type="submit">완료</button>
	</form>
</body>
</html>