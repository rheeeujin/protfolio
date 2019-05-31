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
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper" class="fade-in">
		<!-- Intro -->
		<div id="intro">
			<h1>Traveler</h1>
			<p>
				Traveler is tv program name in jtbc, The design was provided free on
				the template site. <br>I hate CSS, html, etc. <br>Why does
				br not apply?
			</p>
			<p>
				<a href="https://html5up.net/massively">Site from which the
					template was imported.</a>
			</p>
			<ul class="actions">
				<li><a href="#header" class="button icon solo fa-arrow-down scrolly">Continue</a></li>
			</ul>
		</div>

		<!-- Header -->
		<jsp:include page="include/hearderNav.jsp" />
		<!-- Main -->
		<div id="main">

			<!-- Posts -->
			<section class="posts">
			<c:forEach items="${board}" var="board">
			<article>
				<header>
					<span class="date">${board.userid} :: ${board.bdate}</span>
					<h2>
						<a href="view?bidx=${board.bidx}">${board.title}</a>
					</h2>
				</header> 
				<c:if test="${!empty board.preview}">
				<a href="view?bidx=${board.bidx}" class="image fit"><img src="upload/${board.preview}" alt="${board.title}" class=" set-height"/></a>
				</c:if>
				<c:if test="${empty board.preview}">
				<a href="view?bidx=${board.bidx}" class="image fit"><img src="upload/noimg.png" alt="${board.title}" class=" set-height"/></a>
				</c:if>
				<a href="view?bidx=${board.bidx}" class="button actions special">Full Story</a>
				<a href="view?bidx=${board.bidx}#reply" class="button actions special">comment</a>
			</article>
			</c:forEach>
			</section>
			<!-- Footer -->
			<footer>
			<ul class="actions fit small">
			<c:if test="${!empty member}">
				<li><a href="write" class="button fit">write</a></li>
			</c:if>
				<li><a href="#header" class="button fit">top</a></li>
			</ul>
			<jsp:include page="include/page.jsp" />
			</footer>

		</div>

		<!-- Footer -->
		<footer id="footer">
			<jsp:include page="include/footer.jsp" />
		</footer>

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