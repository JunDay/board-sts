<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board 회원가입</title>
</head>
<body>
	<div><a href="/boardList">메인</a></div>
	
	<form method="post" action="/joinMember">
		<div>아이디 : <input id="memberId" name="memberId" type="text"></div>
		<div>비밀번호 : <input id="memberPw" name="memberPw" type="password"></div>
		<div>비밀번호 확인 : <input id="memberCheckPw" name="memberCheckPw" type="password"></div>
		<div>
			이메일 : 
			<input id="email" name="email" type="text">
			@
			<input id="emailDomain" name="emailDomain" type="text">
			<select id="emailChooseDomain" name="emailChooseDomain">
				<option value="default">사용자 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="daum.net">daum.net</option>
			</select>
		</div>
		<button id="addMemberBtn" type="submit">회원가입</button>
	</form>
</body>
</html>