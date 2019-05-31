<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>/security/admin page</h1>
<a href="../customLogout">logout</a>
<h2>principal:<security:authentication property="principal"/></h2>
<h2>principal.vo.member:<security:authentication property="principal.vo"/></h2>
<h2>principal.vo.member.userName:<security:authentication property="principal.vo.userName"/></h2>
<h2>principal.username:<security:authentication property="principal.username"/></h2>
<h2>principal.vo.member.authList:<security:authentication property="principal.vo.authList"/></h2>
</body>
</html>