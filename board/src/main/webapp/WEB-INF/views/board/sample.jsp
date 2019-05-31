<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
$(()=>{
	$("#btn").click(()=>{
		$.ajax({
			url: 'proxy',
			success: (data, status, xhr) => {
				console.log(data);
				console.log(status);
				console.log(xhr);
				
				$(data).find("item").each(function() {
					$("body").append("<h2>" + $(this).find("title").text());
				});
			},
			error: (xhr, status, errThrown) => {
				console.log(data);
				console.log(status);
				console.log(xhr);
			}
		})
	})
});
</script>
<title>RSS</title>
</head>
<body>
<h1>RSS 연습하기</h1>
<button id="btn">RSS Go!</button>
</body>
</html>