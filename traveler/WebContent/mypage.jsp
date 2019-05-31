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
			<h1>mypage</h1>
				<div class="row gtr-uniform">
					<div class="col-12">
						<h3>userid :: ${member.userid }</h3>
					</div>
					<div class="col-12">
						<h3>name :: ${member.name}</h3>
					</div>
					<div class="col-12">
						<h3>email :: ${member.email}</h3>
					</div>
					<div class="col-12">
						<ul class="actions fit small">
							<li><a class="button fit" href="javascript:alert('준비중입니다.');">modify</a></li>
							<li><a class="button primary fit" href="index">home</a></li>
						</ul>
					</div>
					<c:if test="${!empty board}">
					<div class="col-12">
					<h1>myboard</h1>
					<table>
						<thead>
						<tr>
							<td>title</td>
							<td>bdate</td>
							<td>bcnt</td>
							<td>delet</td>
						</tr>
						</thead>
						<c:forEach items="${board}" var="i">
						<tbody>
						<tr>
							<td><a href="view?bidx=${i.bidx}">${i.title}</a></td>
							<td>${i.bdate}</td>
							<td>${i.bcnt}</td>
							<td><a href="delete?userid=${member.userid}&bidx=${i.bidx}" class="warring">x</a></td>
						</tr>
						</tbody>
						</c:forEach>
					</table>
					</div>
					<div class="col-12">
					<h1>myreply</h1>
					<table>
						<thead>
						<tr>
							<td>boardno</td>
							<td>reply</td>
							<td>regdate</td>
							<td>delet</td>
						</tr>
						</thead>
						<c:forEach items="${reply}" var="i">
						<tbody>
						<tr>
							<td>${i.bidx}</td>
							<td>${i.reply}</td>
							<td>${i.reply_date}</td>
							<td><a href="delete?userid=${member.userid}&bidx=${i.bidx}" class="warring">x</a></td>
						</tr>
						</tbody>
						</c:forEach>
					</table>
					</div>
					</c:if>
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