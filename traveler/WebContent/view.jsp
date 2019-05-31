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
	<title>Traveler</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<noscript>
		<link rel="stylesheet" href="assets/css/noscript.css" />
	</noscript>
	<script type="text/javascript">
	$(function () {
		$("input:file").change(function () {
			var src = $(this).val();
			$(this).next().text(src.slice(src.lastIndexOf("\\")+1));
			
		})
	})
	</script>
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<jsp:include page="include/hearderNav.jsp" />

		<!-- Main -->
		<div id="main">
			<!-- Post -->
			<form method="post" name="frm">
			<input type="hidden" value="${member.userid}" name="userid">
			<input type="hidden" value="${board.bidx}" name="bidx">
			<h1>view</h1>
				<div class="row gtr-uniform">
					
					<div class="col-12">
						<h2>${board.title}</h2>
					</div>
					<div class="col-12">
						<p>${board.bcontent}</p>
					</div>
					<c:if test="${!empty file}">
					<c:forEach items="${file}" var="file">
					<div class="col-12">
						<img alt="${file.origin}" src="upload/${file.realname}" class="image fit">
					</div>
					</c:forEach>
					</c:if>
					<div class="col-12">
						<ul class="actions fit small">
							<c:if test="${member.userid == board.userid}">
							<%-- <li><a class="actions fit small button primary" href="update?bidx=${board.bidx}">update</a></li> --%>
							<li><a class="actions fit small button primary" href="javascript:alert('준비중입니다.');">update</a></li>
							<li><a class="actions fit small button primary" href="delete?bidx=${board.bidx}" onclick="return confirm('정말삭제하시겠습니까?')">delete</a></li>
							</c:if>
							<li><a class="actions fit small button primary" href="index">BACK</a></li>
						</ul>
					</div>
					<section class="col-12" id="reply">
						<div class="table-wrapper">
						<table>
							<thead>
							<tr>
								<th>userid</th>
								<th>reply</th>
								<th>date</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${reply}" var="i">
							<tr>
								<td>${i.userid}</td>
								<td>${i.reply}</td>
								<td>${i.reply_date}
								<c:if test="${member.userid == i.userid}">
								<a href="delreply?ridx=${i.ridx}&userid=${i.userid}&bidx=${board.bidx}" class="warring">x</a>
								</c:if>
								</td>
							</tr>
							</c:forEach>
							</tbody>
							<c:if test="${!empty member}">
							<tfoot>
							<tr>
								<td>${member.userid}</td>
								<td><input type="text" placeholder="댓글을 입력해주세요" name="reply"></td>
								<td><button class="fit small">댓글등록</button></td>
							</tr>
							</tfoot>
							</c:if>
						</table>
						</div>
					</section>
				</div>
			</form>

		</div>

		<!-- Footer -->
		<%-- <footer id="footer">
			<jsp:include page="include/footer.jsp" />
		</footer> --%>

		<!-- Copyright -->
		<div id="copyright">
			<jsp:include page="include/copyright.jsp" />
		</div>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>

</html>