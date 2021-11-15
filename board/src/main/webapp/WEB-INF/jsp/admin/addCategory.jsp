<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCategory</title>
</head>
<body>
	<h1>카테고리 추가</h1>
	
	<form method="post" action="/admin/addCategory">
		<div>카테고리명 : <input id="categoryName" name="categoryName" type="text"></div>
		<button type="submit">카테고리 추가</button>
	</form>
</body>
</html>