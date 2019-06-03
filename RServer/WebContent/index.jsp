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
<title>Rserver</title>
<style type="text/css">
a{cursor: pointer;}
</style>
</head>
<body>
<h1> Rserver 예제용 페이지 입니다. </h1>
<p>
	키워드 입력시 키워드로 검색한 결과를 워드클라우드로 출력
</p>

<form action="" method="post" id="frm">
	<select name="type">
		<option value="Nblog">네이버 블로그에서 검색</option>
		<!-- <option value="Nnews">네이버 뉴스에서 검색</option>
		<option value="Dblog">다음 블로그에서 검색</option>
		<option value="Dnews">다음 뉴스에서 검색</option>
		<option value="google">구글에서 검색</option> -->
	</select>
	<input type="text" name="keyword">
	<button>검색</button>
</form>

<div class="imgdiv">
</div>

<a href="view">다른 과제 보기</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("form").submit(function (e) {
			e.preventDefault();
			var formData = $("#frm").serialize();
			console.log(formData)
			
			$.ajax({
				url:"index"
				,cache : false
				,type:"post"
				,data:formData
				,crossDomain:true
				,beforeSend : function () {
					$(".imgdiv").html("<img src='img/loading.gif'>");
				}
				,success:function(str){	
					if (str == "error") {
						$(".imgdiv").html("<h1>오류가 생겼어요...</h1><p>${error}</p>");
					}else{
						$(".imgdiv").html("").delay(1500).html("<h1>wordcloud가 완성되었습니다.</h1><img src='data/rcrawl.png'>");
						//$('img').attr('src', 'data:image/png;base64,'+src);
						console.log("end")
					}
				}
			});
		})
		$("button").keyup(function(event) {
		  // Enter 처리
		  if (event.keyCode == '13') {
		    $("button").trigger("click");
		  };
		});
		/* 
		$("a").click(function(e) {
			alert("click")
			e.preventDefault();
			$.ajax({
				url:"downloadfile",
				type:"get",
				data:{"name" : $("a").data("name")},
				success:function(data){
					
				}
			})
		}) 
		*/
	})
</script>
</body>
</html>