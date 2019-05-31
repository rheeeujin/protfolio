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
						<input name="userid" value="${member.id }"/>
					</div>
					<div class="col-12">
						<input name="pw" value="${member.id }"/>
					</div>
					<div class="col-12">
						<input name="name" type="text" placeholder="name을 입력해 주세요(선택)"/>
					</div>
					<div class="col-12">
						<input name="email" type="email" placeholder="email을 입력해 주세요(선택)"/>
					</div>
					<div class="col-12">
						<ul class="actions fit small">
							<li><input type="submit" value="signup"/></li>
							<li><input type="reset" value="reset"/></li>
							<li><a class="button primary fit" href="index">BACK</a></li>
						</ul>
					</div>
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