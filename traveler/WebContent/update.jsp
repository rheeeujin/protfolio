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
			<form method="post" enctype="multipart/form-data" name="frm">
			<h1>WRITE</h1>
				<div class="row gtr-uniform">
					<input type="hidden" value="${board.bidx}" name="bidx">
					<input type="hidden" value="${member.userid}" name="userid">
					<div class="col-12">
						<input name="title" type="text" value="${board.title }"/>
					</div>
					<div class="col-12">
						<textarea name="content">${board.bcontent }</textarea>
					</div>
					
					<c:forEach begin="0" end="2" var="i">
					<div class="col-12">
						<label>
						<input type="file" name="file${i+1 }" value="">
						<c:if test="${!empty file[i]}">
						<span class="button small fit icon fa-download inputfile">${file[i].origin}</span>
						</c:if>
						<c:if test="${empty file[i]}">
						<span class="button small fit icon fa-download inputfile">add file</span>
						</c:if>
						</label>
					</div>
					</c:forEach>
					
					<div class="col-12">
						<ul class="actions fit small">
							<li><input type="submit" value="update" class="primary"/></li>
							<li><input type="reset" value="reset"/></li>
							<li><a class="button primary" href="index">BACK</a></li>
						</ul>
					</div>
				</div>
			</form>

		</div>

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