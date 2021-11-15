<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>categoryList</title>
</head>
<body>
	<h1>카테고리 목록</h1>
	<div><a href="/admin/addCategory">카테고리 추가</a></div>
	<table border="1">
		<tr>
			<td>카테고리명</td>
			<td>생성일</td>
			<td>최종수정일</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${categoryList}" var="c">
			<tr>
				<td>${c.categoryName}</td>
				<td>${c.categoryDate}</td>
				<td>${c.updateDate}</td>
				<td><a href="/admin/updateCategory?categoryName=${c.categoryName}">수정</a></td>
				<td><a href="/admin/deleteCategory?categoryName=${c.categoryName}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>