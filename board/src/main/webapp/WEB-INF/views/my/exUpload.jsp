<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form enctype="multipart/form-data" method="post">
		<input type="file" multiple="multiple" name="files">
		<input type="file" name="files">
		<button>전송</button>
	</form>
</body>
</html>