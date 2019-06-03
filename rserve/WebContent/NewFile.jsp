<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<!-- <h1><a href="?type=2">1.api사용 차트</a></h1> -->
<h1><a href="?type=3">군집분석 iGraph</a></h1>
<%-- <c:if test="${param.type == 2}">
	<figure>
		<figcaption>서울시 한국여행중 서울지역 방문지 (거주국별) 통계</figcaption>
		<img alt="2.api사용 차트" src="apiChart">
	</figure>
</c:if> --%>
<c:if test="${param.type == 3}">
	<figure>
		<figcaption>employees</figcaption>
		<img alt="3.군집분석 iGraph" src="igraph">
	</figure>
</c:if>
<a href="index">돌아가기</a>
</body>
</html>