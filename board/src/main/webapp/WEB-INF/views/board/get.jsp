<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<!-- Custom fonts for this template -->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<!-- Custom styles for this page -->
<link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<link rel="shortcut icon" href="/resources/img/favicon.ico">
<style>
button{cursor: pointer;}
.chat li {cursor: pointer;}
.uploadDiv span {cursor:pointer;}
.uploadDiv input[type='file']{display : none;}
.uploadResult ul {display: flex; flex-flow: row; justify-content: center; align-items: center; background: white; border-radius: .35rem;}
.uploadResult ul li{list-style: none; padding: 10px;}
.uploadResult ul li .file-attach {width: 20px;}
.uploadResult span{cursor: pointer; color: red;}
.bigPictureWrapper{display:none; position: absolute; width:100%;  height: 100%; justify-content: center; align-items: center; top:0; z-index: 100; background: gray; background: rgba(255,255,255,.5);}
.bigPicture{position: relative; display: flex; justify-content: center; align-items: center;}
.bigPicture img {width: 600px;}
.hojae{width: 7%; text-align: center;}
</style>
</head>
<body id="page-top">
<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<jsp:include page="../includes/header.jsp" />
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12">
							<div class="p-5">
								<div class="text-center">
									<h1 class="text-gray-900 mb-4">Board Read</h1>
								</div>
								<form class="user" method="post">
									<div class="form-group">
										Title 
										<input type="text" value="${board.title}" class="form-control" placeholder="제목을 입력하세요" name="title" readonly="readonly">
									</div>
									<div class="form-group">
										Textarea
										<textarea rows="5" class="form-control" placeholder="내용을 입력하세요" name="content" readonly="readonly">${board.content}</textarea>
									</div>
									<div class="form-group">
										Writer 
										<input type="text" value="${board.writer}" class="form-control" placeholder="작성자를 입력하세요" name="writer" readonly="readonly">
									</div>
									<div class="form-group float-right">
										<security:authorize access="isAuthenticated()">
										<security:authentication property="principal" var="pinfo"/>
										<security:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')" var="isAdmin" />
										<c:if test="${isAdmin or pinfo.username eq board.writer }">
										<%-- <c:if test=
											"${fn:contains(pinfo,'ROLE_MANAGER') 
											or fn:contains(pinfo,'ROLE_ADMIN') 
											or pinfo.username eq board.writer}"> --%>
										<a href="modify${cri.listLink}&bno=${board.bno}" class="btn btn-outline-warning">글수정</a>
										</c:if>
										</security:authorize>
										<a href="list${cri.listLink}" class="btn btn-outline-primary">글목록</a>
									</div>
									<!-- <a href="index.html" class="btn btn-primary btn-user btn-block">
                    					Login
                    				</a> -->
								</form>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="p-5">
								<div class="m-2 clearfix">
									<i class="fa fa-file fa-fw"></i> Files
								</div>
								<div class="uploadResult">
									<ul class="list-group">
									</ul>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-12">
							<div class="p-5">
								<div class="m-2 clearfix">
									<i class="fa fa-comments fa-fw"></i> Reply 
									<security:authorize access="isAuthenticated()">
									<button id="addReplyBtn" class="btn btn-outline-success btn-sm float-right" >New Reply</button>
									</security:authorize>
								</div>
								<ul class="list-group chat">
									<!-- <li class="list-group-item">
										<div class="header">
											<strong class="primary-font">user00</strong>
											<small class="float-right text-muted">2019-05-01 13:00</small>
										</div>
										<p class="m-0">내용내용</p>
									</li>
									<li class="list-group-item">
										<div class="header">
											<strong class="primary-font">user00</strong>
											<small class="float-right text-muted">2019-05-01 13:00</small>
										</div>
										<p class="m-0">내용내용</p>
									</li> -->
								</ul>
								<br>
								<ul class="pagination float-right">
									<!-- <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
									<li class="page-item active"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#">2</a></li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">&raquo;</a></li> -->
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
			<!-- Footer -->
			<jsp:include page="../includes/footer.jsp" />
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>
	<!-- Logout Modal-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Reply</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="reply">Reply</label><input class="form-control" name="reply" id="reply">
					</div>
					<div class="form-group">
						<label for="replyer">Replyer</label><input class="form-control" name="replyer" id="replyer" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="replyDate">ReplyDate</label><input class="form-control" name="replyDate" id="replyDate">
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-outline-warning btn-sm" type="button" data-dismiss="modal" id="modalModBtn">Modify</button>
					<button class="btn btn-outline-danger btn-sm" type="button" data-dismiss="modal" id="modalRmBtn">Remove</button>
					<button class="btn btn-outline-primary btn-sm" type="button" data-dismiss="modal" id="modalRegBtn">Register</button>
					<button class="btn btn-outline-secondary btn-sm" type="button" data-dismiss="modal" id="modalCloseBtn">Close</button>
					<!-- <a class="btn btn-primary" href="login.html">Logout</a> -->
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<!-- Page level plugins -->
	<!-- <script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script> -->
	<!-- Page level custom scripts -->
	<!-- <script src="/resources/js/demo/datatables-demo.js"></script> -->
	<script src="/resources/js/reply.js"></script>
	
	<script>
	var bno = '${board.bno}';
	var csrfHeader = "${_csrf.headerName}";
	var csrfToken = "${_csrf.token}";
	var replyer = null;
	<security:authorize access="isAuthenticated()">
	replyer = '<security:authentication property="principal.username"/>';
	</security:authorize>
	
	$(document).ajaxSend(function(e,xhr){
		xhr.setRequestHeader(csrfHeader, csrfToken);
	})
	
	var pageNum = 1;
	showList();
	function showList(page) {
		replyService.getList({bno : bno, page : page || 1}, function(cnt ,list) {
			var str = "";
			console.log(cnt);
			
			if (page == -1) {
				pageNum = Math.ceil(cnt/10);
				showList(pageNum);
				return;
			}
			
			if (list == null || list.length == 0) {
				$(".chat").html("");
				return;
			}
			
			for(var i in list) {
				str += "<li class='list-group-item' data-rno='" + list[i].rno + "'> " ;
				str += "<div class='header'> " ;
				str += "<strong class='primary-font'>" + list[i].replyer + "</strong> ";
				str += "<small class='float-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small> ";
				str += "</div> ";
				str += "<p class='m-0'>" + list[i].reply + "</p> ";
				str += "</li>";
			}
			$(".chat").html(str);
			showReplyPage(cnt);
			
			function showReplyPage(cnt) {
				var endNum = Math.ceil(pageNum / 10) * 10;
				var startNum = endNum-9;
				
				var prev = startNum != 1;
				var next = false;
				
				if (endNum * 10 >= cnt) {
					endNum = Math.ceil(cnt/10);
				}
				if (endNum * 10 < cnt) {
					next = true;
				}
				
				var str = "";
				if (prev) {
					str += '<li class="page-item"><a class="page-link" href="' + startNum + '">&laquo;</a></li>';
				}
				for (var i = startNum; i <= endNum; i++) {
					var active = i == pageNum ? 'active' : '';
					str += '<li class="page-item ' + active + '"><a class="page-link" href="' + i + '">' + i + '</a></li>';
				}
				
				if (next) {
					str += '<li class="page-item"><a class="page-link" href="' + endNum + '">&raquo;</a></li>';
				}
				$(".pagination").html(str);
			}
		});
	}
	
	$(".pagination").on("click", "a", function(e) {
		e.preventDefault();
		var target = $(this).attr("href");
		pageNum = target;
		showList(pageNum);
	})
	
	// modal
	var modal = $("#myModal");
	var modalInputReply = modal.find("[name='reply']");
	var modalInputReplyer = modal.find("[name='replyer']");
	var modalInputReplyDate = modal.find("[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRmBtn = $("#modalRmBtn");
	var modalRegBtn = $("#modalRegBtn");
	
	$("#addReplyBtn").click(function() {
		modal.find("input").val("");
		modalInputReplyDate.parent().hide();
		modalInputReplyer.val(replyer);
		//modalInputReplyer.prop("readonly", false);
		
		modal.find("button[id != 'modalCloseBtn']").hide();
		modalRegBtn.show();
		modal.modal("show");
	})
	
	modalRegBtn.click(function() {
		replyService.add({
			reply : modalInputReply.val(),
			replyer : modalInputReplyer.val(),
			bno : bno
		}, function(result) {
			alert(result);
			modal.find("input").val("");
			modal.modal("hide");
			showList(-1);
		})
	})
	
	modalModBtn.click(function() {
		var rno = modal.data('rno');
		if(!replyer){
			alert("로그인 후 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		if(replyer != modalInputReplyer.val()){
			alert("자신이 작성한 댓글만 삭제 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		
		replyService.modify({
			reply : modalInputReply.val(),
			replyer : replyer,
			rno : rno
		}, function(result) {
			alert(result);
			modal.modal("hide");
			showList();
		})
	})
	
	modalRmBtn.on("click",function(e) {
		var rno = modal.data('rno');
		if(!replyer){
			alert("로그인 후 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		if(replyer != modalInputReplyer.val()){
			alert("자신이 작성한 댓글만 삭제 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		replyService.remove(rno, replyer, function(result) {
			alert(result);
			modal.modal("hide");
			showList();
		})
	})
	
$.getJSON("getAttachList", {bno:bno},function(uploadResultArr){
		console.log(uploadResultArr);
		var str = "";
		for(var i in uploadResultArr){
			str += "<li>" 
			var thumbPath = uploadResultArr[i].uploadPath + "/s_" 
					+ uploadResultArr[i].uuid + "_" 
					+ uploadResultArr[i].fileName;
			var fileCallPath = encodeURIComponent(thumbPath.replace("/s_","/"));
			thumbPath = encodeURIComponent(thumbPath);
			if(uploadResultArr[i].fileType){
				str += "<a href ='javascript:showImage(\"" + fileCallPath + "\")'>";
				str += "<img src='../display?fileName="
						+ thumbPath + "'>";
				str += "</a>";
				//str += " <span data-file='" + thumbPath + "' data-type='image'>X</span>"
			}else{
				str += "<a href ='../download?fileName=" + fileCallPath + "'>";
				str += "<img src='../resources/img/attach.png' class='file-attach'>" + uploadResultArr[i].fileName;	
				str += "</a>";
				//str += " <span data-file='" + fileCallPath + "' data-type='file'>X</span>"
			}
			str += "</li>";
		}
		$(".uploadResult ul").append(str);
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex");
	$(".bigPicture")
	.html("<img src='../display?fileName=" + fileCallPath + "'>")
	.animate({height:"100%"},1000);
	
}
	
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({height:'0%'},1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
	
	$(".chat").on("click", "li", function() {
		var rno = $(this).data("rno"); // this = li
		
		replyService.get(rno, function(result) {
			modalInputReply.val(result.reply);
			modalInputReplyer.val(result.replyer).prop("readonly",true);
			modalInputReplyDate.val(replyService.displayTime(result.replyDate)).prop("readonly", true).parent().show();
			modal.data("rno", rno);
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRmBtn.show();
			modal.modal("show");
		})
	})
	
	/* replyService.add({
		reply : 'js Reply',
		replyer : 'rester',
		bno : bno
	}, function(result) {
		alert(result);
	}) */
	/* replyService.get(21, function(result) {
		alert(result)
	}) */
	</script>
</body>
</html>