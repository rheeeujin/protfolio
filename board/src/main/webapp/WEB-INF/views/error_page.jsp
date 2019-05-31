<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/resources/img/favicon.ico">
<title>Insert title here</title>
</head>
<body>
	<h4>${exception.getMessage()}</h4>
	<ul>
	<c:forEach items="${exception.getStackTrace()}" var="stack">
		<li>${stack}</li>
	</c:forEach>
	</ul>
</body>
</html>