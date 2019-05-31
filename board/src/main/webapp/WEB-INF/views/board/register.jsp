<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Custom fonts for this template -->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<!-- Custom styles for this page -->
<link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<link rel="shortcut icon" href="/resources/img/favicon.ico">
<style>
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
									<h1 class="text-gray-900 mb-4">Board Register</h1>
								</div>
								<form class="user" method="post" id="register">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
									<div class="form-group">
									Title <input type="text" class="form-control"placeholder="제목을 입력하세요" name="title">
									</div>
									<div class="form-group">Textarea
										<textarea rows="5" class="form-control"	placeholder="내용을 입력하세요" name="content"></textarea>
									</div>
									<div class="form-group">
										Writer <input type="text" class="form-control" name="writer" readonly="readonly"
										value= "<security:authentication property="principal.username"/>">
									</div>
									<div class="form-group uploadDiv">File Attach
										<label class="form-control hojae">
											<span>파일첨부<input type="file" name="uploadFile" multiple="multiple"></span>
										</label>
									</div>
									<div class="form-group clearfix">
										<div class="float-right">
											<button class="btn btn-outline-primary" type="submit">글등록</button>
											<button class="btn btn-outline-danger" type="reset">초기화</button>
										</div>
									</div>
									<div class="form-group uploadResult">
										<ul></ul>
									</div>
								</form>
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
	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<!-- Page level plugins -->
	<script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- Page level custom scripts -->
	<script src="/resources/js/demo/datatables-demo.js"></script>
	<input type="hidden" name="attachList[0].fileName" value="">
	<script>
		var frm = $("form");
		var csrfHeader = "${_csrf.headerName}";
		var csrfToken = "${_csrf.token}";
		
		$("#register button:submit").click(
				function(e) {
					e.preventDefault();
					var str = "";
					$(".uploadResult ul li").each(
							function(i) {
								str += '<input type="hidden" name="attachList[' + i + '].fileName" value="' + $(this).data("filename") + '">'
								str += '<input type="hidden" name="attachList['	+ i + '].uuid" value="'	+ $(this).data("uuid") + '">'
								str += '<input type="hidden" name="attachList['	+ i + '].uploadPath" value="'+ $(this).data("path") + '">'
								str += '<input type="hidden" name="attachList['	+ i + '].fileType" value="'	+ $(this).data("type") + '">'
							});
					console.log(str);
					frm.append(str).submit();
					//console.log($(this).html());
					//console.log($(frm).serialize())
				});

		var regex = /(.*?)\.(exe|sh|js)$/i;
		var maxSize = 1024 * 1024 * 5;

		function checkExtension(fileName, fileSize) {
			if (fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if (regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드 할 수 없습니다.")
				return false;
			}
			return true;
		}

		function showImage(fileCallPath) {
			$(".bigPictureWrapper").css("display", "flex");
			$(".bigPicture").html(
					"<img src='display?fileName=" + fileCallPath + "'>")
					.animate({height : "100%"}, 1000);

		}
		var uploadDivCloneObj = $('.uploadDiv').clone(true);

		$('.uploadDiv').on("change", "input:file", function() {
			var files = $(this).get(0).files;
			/* input : file의 label로 변경 */
			if (files.length < 1) {
				$('.uploadDiv span').text("파일첨부");
			} else if (files.length == 1) {
				$('.uploadDiv span').text(files[0].name);
			} else {
				$('.uploadDiv span').text(files.length + "files");
			}
			console.log(files.length);

			/* 파일의 확장자 유효성 검증 후 form binding */
			var formData = new FormData();
			for ( var i in files) {
				if (!checkExtension(files[i].name, files[i].size))
					return false;
				formData.append("uploadFile", files[i]);
			}

			/* ajax로 파일 전송  */
			$.ajax({
				url : '../uploadAjax',
				type : 'post',
				data : formData,
				dataType : 'json',
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeader, csrfToken)
				},
				processData : false,
				contentType : false,
				success : function(result) {
					console.log(result);
					/* 파일 업로드 후 초기화 */
					$('.uploadDiv').html(uploadDivCloneObj.html());
					showUploadFile(result);
				}
			});
		});
		/* 업로드 이후 파일 보여주기 */
		var uploadResult = $('.uploadResult ul');
		function showUploadFile(uploadResultArr) {
			var str = "";
			for ( var i in uploadResultArr) {
				str += "<li"
			str += " data-filename='" + uploadResultArr[i].fileName + "'";
			str += " data-uuid='" + uploadResultArr[i].uuid + "'";
			str += " data-path='" + uploadResultArr[i].uploadPath + "'";
			str += " data-type='" + uploadResultArr[i].image + "'";
			str += ">";
				var thumbPath = uploadResultArr[i].uploadPath + "/s_"+ uploadResultArr[i].uuid + "_"+ uploadResultArr[i].fileName;
				var fileCallPath = encodeURIComponent(thumbPath.replace("/s_","/"));
				thumbPath = encodeURIComponent(thumbPath);
				if (uploadResultArr[i].image) {
					str += "<a href ='javascript:showImage(\"" + fileCallPath + "\")'>";
					str += "<img src='../display?fileName=" + thumbPath + "'>";
					str += "</a>";
					str += " <span data-file='" + thumbPath + "' data-type='image'><i class='fa fa-times'></i></span>"
				} else {
					str += "<a href ='../download?fileName=" + fileCallPath	+ "'>";
					str += "<img src='../resources/img/attach.png' class='file-attach'>"+ uploadResultArr[i].fileName;
					str += "</a>";
					str += " <span data-file='" + fileCallPath + "' data-type='file'><i class='fa fa-times'></i></span>"
				}
				str += "</li>";
			}
			uploadResult.append(str);
		}

		/* 파일 삭제 버튼 이벤트 */
		$(".uploadResult").on("click", "span", function() {
			var target = $(this).data("file");
			var type = $(this).data("type");
			var li = $(this).closest("li");
			console.log(target, type);

			$.ajax({
				url : "../deleteFile", 
				data : {fileName : target, type : type},
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeader, csrfToken)
				},
				type : "post",
				success : function(result) {alert(result);li.remove();}
			})
		});
	</script>
</body>
</html>