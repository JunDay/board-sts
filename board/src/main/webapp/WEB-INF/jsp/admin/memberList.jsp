<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList</title>
</head>
<body>
	<h1>회원 목록</h1>
<table border="1">
		<tr>
			<td>회원번호</td>
			<td>회원ID</td>
			<td>회원레벨</td>
			<td>회원상태</td>
		</tr>
		<c:forEach items="${memberList}" var="member">
			<tr>
				<td><a href="memberOne?memberNo=${member.memberNo}">${member.memberNo}</a></td>
				<td>${member.memberId}</td>
				<td>${member.memberLevel}</td>
				<td>${member.memberState}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${memberId != null}">
			<c:if test="${currentPage > 1}" >
				<a href="memberList?currentPage=${currentPage-1}&memberId=${memberName}">이전</a>
			</c:if>
			<c:if test="${currentPage < lastPage}" >
				<a href="memberList?currentPage=${currentPage+1}&memberId=${memberName}">다음</a>
			</c:if>
		</c:if>
		<c:if test="${memberId == null}">
			<c:if test="${currentPage > 1}" >
				<a href="memberList?currentPage=${currentPage-1}">이전</a>
			</c:if>
			<c:if test="${currentPage < lastPage}" >
				<a href="memberList?currentPage=${currentPage+1}">다음</a>
			</c:if>
		</c:if>
	</div>
</body>
</html>