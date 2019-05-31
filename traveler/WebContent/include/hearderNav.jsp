<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<header id="header"> <a href="index" class="logo">Traveler</a>
	</header>
	<!-- Nav -->
	<nav id="nav">
	<ul class="links">
		<li><a href="index">HOME</a></li>
		<!-- generic.html -->
		<c:if test="${empty member}">
		<li><a href="login">LOGIN</a></li>
		<li><a href="signup">SIGNUP</a></li>
		</c:if>
		<c:if test="${!empty member}">
		<li><a href="mypage">MYPAGE</a></li>
		<li><a href="logout">logout</a></li>
		<li><a href="resign">resign</a></li>
		</c:if>
	</ul>
	<ul class="icons">
		<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
		<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
		<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
		<li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
	</ul>
	</nav>

</body>
</html>