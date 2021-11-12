<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoard.jsp</title>
</head>
<body>
	<h1>addBoard</h1>
	
	<form action="/addBoard" method="post">
		<div>
			<select></select>
		</div>
		<div>boardCategory : <input type="text"  id="boardCategory" name="boardCategory"></div>
		<div>boardTitle : <input type="text"  id="boardTitle" name="boardTitle"></div>
		<div>boardContent : <textarea id="boardContent" name="boardContent" rows="5" cols="50"></textarea></div>
		<button type="submit">입력</button>
	</form>
</body>
</html>