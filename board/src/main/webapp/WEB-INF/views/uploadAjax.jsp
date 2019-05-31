<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
var regex = /(.*?)\.(exe|sh|js)$/i;
var maxSize = 1024 * 1024 * 5;

function checkExtension(fileName, fileSize){
	if(fileSize >= maxSize){
		alert("파일 사이즈 초과");
		return false;
		}
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 없습니다.")
		return false;
	}
	return true;
}

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex");
	$(".bigPicture")
	.html("<img src='display?fileName=" + fileCallPath + "'>")
	.animate({height:"100%"},1000);
	
}

	$(function() {
		var uploadResult = $('.uploadResult ul');
		
		/* 파일 삭제 버튼 이벤트 */
		$(".uploadResult").on("click","span",function(){
			var target = $(this).data("file");
			var type = $(this).data("type");
			
			console.log(target,type);
			
			$.ajax({
				url : "deleteFile",
				data : {fileName : target,type:type},
				type : "post",
				success : function(result){
					alert(result);
				}
			})
		});
		
		
		/* 업로드 이후 파일 보여주기 */
		function showUploadFile(uploadResultArr){
			var str = "";
			for(var i in uploadResultArr){
				str += "<li>" 
				var thumbPath = uploadResultArr[i].uploadPath + "/s_" 
						+ uploadResultArr[i].uuid + "_" 
						+ uploadResultArr[i].fileName;
				var fileCallPath = encodeURIComponent(thumbPath.replace("/s_","/"));
				thumbPath = encodeURIComponent(thumbPath);
				if(uploadResultArr[i].image){
					str += "<a href ='javascript:showImage(\"" + fileCallPath + "\")'>";
					str += "<img src='display?fileName="
							+ thumbPath + "'>";
					str += "</a>";
					str += " <span data-file='" + thumbPath + "' data-type='image'>X</span>"
				}else{
					str += "<a href ='download?fileName=" + fileCallPath + "'>";
					str += "<img src='resources/img/attach.png' class='file-attach'>" + uploadResultArr[i].fileName;	
					str += "</a>";
					str += " <span data-file='" + fileCallPath + "' data-type='file'>X</span>"
				}
				str += "</li>";
			}
			uploadResult.append(str);
		}
		
		
		/* 팝업이미지 닫기 */
		$(".bigPictureWrapper").click(function(){
			$(".bigPicture").animate({height:'0%'},1000);
			setTimeout(function() {
				$(".bigPictureWrapper").hide();
			}, 1000);
		})
		
		/* input : file의 label로 변경 */
		$('.uploadDiv').on("change", "input:file", function(){
			var files = $("#files").get(0).files;
			if(files.length < 1){
				$('.uploadDiv span').text("파일첨부");	
			}
			else if(files.length == 1){
				$('.uploadDiv span').text(files[0].name);
			}
			else{
				$('.uploadDiv span').text(files.length + "files");
			}
		});
		
		/* 파일 업로드 후 초기화 */
		var uploadDivCloneObj = $('.uploadDiv').clone(true);
		$("#btn").click(function(e) {
			var formData = new FormData();
			var files = $("#files").get(0).files;
			
			for (var i in files) {
				if(!checkExtension(files[i].name, files[i].size))return false;
				formData.append("uploadFile",files[i]);
			}
			
			$.ajax({
				url : 'uploadAjax',
				type : 'post',
				data : formData,
				dataType : 'json',
				processData : false,
				contentType : false,
				success : function(result){
					console.log(result);
					
					$('.uploadDiv').html(uploadDivCloneObj.html());
					showUploadFile(result);
				}
			});
		})
	})
</script>

</head>
<body>
<div class="uploadDiv">
	<label>
	<input type="file" name="uploadFile" multiple="multiple" id="files">
		<span>파일 첨부</span>
	</label>
</div>
<button id="btn">파일전송</button>
<div class="uploadResult">
	<ul></ul>
</div>
<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>
</body>
</html>