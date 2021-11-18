<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
<body>
	<h1>회원 상세정보</h1>
	<a href="updateMemberPassword?memberNo=${member.memberNo}">비밀번호 변경</a>
	<a href="updateMember?memberNo=${member.memberNo}">정보변경</a>
	<a href="deleteMember?memberNo=${member.memberNo}">탈퇴</a>
	<table border="1">
		<tr>
			<td>회원번호</td>
			<td>회원ID</td>
		</tr>
		<tr>
			<td>${member.memberNo}</td>
			<td>${member.memberId}</td>
		</tr>
		<tr>
			<td colspan="2">회원이메일</td>
		</tr>
		<tr>
			<td colspan="2">${member.memberEmail}</td>
		</tr>
		<tr>
			<td>회원가입일</td>
			<td>수정일</td>
		</tr>
		
		<tr>
			<td>${member.memberDate}</td>
			<td>${member.updateDate}</td>
		</tr>
	</table>

</body>
</html>