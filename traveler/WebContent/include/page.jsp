<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"	prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"	prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="pagination">
				<c:if test="${page > 1}">
					<a href="index?page=${page-1 }" class="previous">Prev</a>
					<c:if test="${page-1>2 }"></c:if>
					<a href="index" class="page">1</a>
					<c:if test="${page-2>1 }">
					<span class="extra">&hellip;</span>
					</c:if>
				</c:if>
				<c:if test="${page-1>1}">
					<a href="index?page=${page-1 }" class="page">${page-1}</a>
				</c:if>
				<a href="index?page=${page}" class="page active">${page}</a>
				<c:if test="${page<maxPage}">
					<a href="index?page=${page+1}" class="page">${page+1}</a>
				</c:if>
				<c:if test="${maxPage > page+2}">
					<span class="extra">&hellip;</span>
				</c:if>
				<c:if test="${page+1<maxPage}">
					<a href="index?page=${maxPage }" class="page" name="maxPage">${maxPage }</a>
				</c:if>
				<c:if test="${page < maxPage}">
					<a href="index?page=${page+1 }" class="next">Next</a>
				</c:if>
				</div>
</body>
</html>