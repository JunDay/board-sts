<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoard.jsp</title>
</head>
<body>
	<h1>addBoard</h1>
	
	<form action="/addBoard" method="post">
		<input type="hidden"  id="memberId" name="memberId" value="${loginMember.memberId}">
		<div>
			boardCategory : 
			<select name="boardCategory">
				<c:forEach items="${categoryList}" var="c">
					<option value="${c.categoryName}">${c.categoryName}</option>
				</c:forEach>
			</select>
		</div>
		<div>boardTitle : <input type="text"  id="boardTitle" name="boardTitle"></div>
		<div>boardContent : <textarea id="boardContent" name="boardContent" rows="5" cols="50"></textarea></div>
		<button type="submit">입력</button>
	</form>
</body>
</html>