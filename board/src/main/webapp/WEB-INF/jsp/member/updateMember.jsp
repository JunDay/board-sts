<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
</head>
<body>
	<h1>회원정보 변경</h1>
	
	<form method="post" action="updateMember">
		<table border="1">
			<tr>
				<td>회원번호</td>
				<td>회원ID</td>
			</tr>
			<tr>
				<td><input id="memberNo" name="memberNo" type="text" value="${member.memberNo}" readonly></td>
				<td><input id="memberId" name="memberId" type="text" value="${member.memberId}"readonly></td>
			</tr>
			<tr>
				<td colspan="2">회원이메일</td>
			</tr>
			<tr>
				<td colspan="2"><input id="memberEmail" name="memberEmail" type="text" value="${member.memberEmail}"></td>
			</tr>
			<tr>
				<td>회원가입일</td>
				<td>수정일</td>
			</tr>
			
			<tr>
				<td><input id="memberDate" name="memberDate" type="text" value="${member.memberDate}" readonly></td>
				<td><input id="updateDate" name="updateDate" type="text" value="${member.updateDate}" readonly></td>
			</tr>
		</table>
		<button type="submit">완료</button>
	</form>
</body>
</html>