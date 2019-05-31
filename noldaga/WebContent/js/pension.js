$(function() {
	$(".img_wrap").click(function() {
		var rmidx = $(this).data("rmidx");
		$.ajax("imgCnt",{
			data : "rmidx=" + rmidx,
			success : function(data) {
				//alert(rmidx+" : " + data.trim());
				
				
				var testStr = '<ul class="slider">';
				
				for (var i = 1; i <= data; i++) {
					var mainPath = web_path + "/" + oridx + "/" + rmidx + "/main/" + i + ".jpg";
					//console.log(mainPath);
					testStr += "<li><img src='"+ mainPath + "'></li>";
				}
				testStr += '</ul><ul class="sliderPager">';
				
				for (var j = 1; j <= data; j++) {
					var thumbPath = web_path + "/" + oridx + "/" + rmidx + "/thumb/" + j + ".jpg";
					testStr += "<li><a href='#' data-slide-index='"+ (j-1) +"'>";
					testStr += "<img src='"+ thumbPath + "'></a></li>";
				}
				testStr += "</ul>";
				//console.log(testStr);
				
				$("#ex1").html(testStr);
				$("#open").click();
			}
		});
	});
	
	$("#open").click(function() {
		$("#ex1").show();
		$(".slider").bxSlider({
			preloadImages: 'all',
			pagerCustom : '.sliderPager',
			mode:'fade'
		});
	
		$(".sliderPager").bxSlider({
			touchEnabled : false,
			pager : false,
			slideWidth : 180,
			slideHeight : 162,
			minSlides : 5,
			maxSlides : 5
		});
	})
	
	$(window).scroll(function() {
		if($(this).scrollTop() >= 456) {
			$(".cont_tab").addClass("cont_tab_scrolled");
		} else {
			$(".cont_tab").removeClass("cont_tab_scrolled");
		}
	});
	

	
	
	//modal ex2 별점클릭이벤트
	$(".input-star i").click(function() {
		var idx = $(this).index();
		
		$(".input-star i").each(function(i) {
			if (idx >= i) {
				$(this).removeClass("fa-star-o").addClass("fa-star")
			}else{
				$(this).removeClass("fa-star").addClass("fa-star-o")
			}
			
		})
		
		$("#score").val(idx+1);
	})
	
	//후기작성버튼
	$("#scoreRegBtn").click(function() {
		$(".modal-footer button").hide()
			.eq(0).show()
		.end()
			.eq(3).show();
		
		$("#ex2").modal("show");
	})
	
	$(".modal-footer button").eq(0).click(function(e) {
		e.preventDefault();
		//alert('click')
		var frm = $(document.scoreFrm).get(0);
		var formData = new FormData(frm);
		console.log(frm)
		console.log(formData)
		
		$.ajax({
			url : "/writeReview",
			data : formData,
			type : "post",
			dataType : 'json',
			contentType : false,
			processData : false
		}).done(function(data) {
			console.log(data);
			frm.reset();
			if (data.result =='success') {
				console.log(data);
				showList();
			}else if(data.result == 'not-login'){
				alert("로그인 이후에 사용해 주세요.")
			}
			$(".blocker").click();
		})
	})
	
})

function showList(){
	console.log("is showList..............psidx : "+psidx)
	$.ajax({
		url : '/reviewOverall?psidx=' + psidx,
		success : function(data){
			var str = "<h4>";
			for(var i = 0; i < 5; i++){
				str += "<i class='fa fa-lg ";
				var j = data.avg - i;
				if (j >= 1) {
					str += "fa-star";
				}else if (j > 0){
					str += "fa-star-half-o";
				}else{
					str += "fa-star-o";
				}
				str += "'></i>";
			}
			str += "<br>" + (data.avg || "평가없음") + "</h4>";
			console.log(data);
			console.log(str);
			$("#overall").html(str);
				
			if(data.avg){
				str = "";
				var arr = ["5","4","3","2","1"];
				for (var i = 0; i < 5; i++) {
					str += '<div class="clearfix"><div class="float-left">';
					for (var j = 0; j < 5; j++) {
						if (i+j <= 4) {
							str += '<i class="fa fa-lg fa-star" style="color:#fc0"></i>';
						}else{
							str += '<i class="fa fa-lg fa-star" style="color:#ccc"></i>';
						}
					}
					str += '</div><div class="progress float-right"><div class="progress-bar" style="width :'+ (data[arr[i]]/data.cnt)*100 +'%">'+
						(data[arr[i]] == 0?'':data[arr[i]]);
					str += '</div></div></div>';
				}
				$("#overall").html(function(i, html) {
					return html + str;
				});
			}
		}
		
	}).done(function(){
		console.log("is showList done.................")
		$.ajax({
			url : '/listReview?psidx=' + psidx,
			beforeSend : function () {
				$(".review-detail").html("<div class='center'><img src='images/icon/cat2.gif'></div>")
			},
			success : function(data){
				var str = "<ul>";
				for(var i in data){
					str += "<li>";
					str += "<div class='reviewDetailContentWrapper'><h5>" + data[i].title + "</h5>";
					str += "<p>" + data[i].cont + "</p>";
					str += "<p class='small'>" + data[i].email + " | " + data[i].regdate + " | " ;

					str += makeStarHtml(data[i].score);
					
					str += "</p></div>";
					str += "<div class='reviewDetailImgWrapper'>";
					
					if (data[i].imgList.length != 0) {
						str += "<img src='upload/"+data[i].imgList[0].realName+"' alt='"+data[i].imgList[0].originName+"' onerror='this.src=\"images/member/userimg.png\"'>";
					}
					
					str += "</div>"
					str += "</li>";
					}
					str += "</ul>"
					$('.review-detail').html(str);
			}
		})
	})
}


function makeStarHtml(number){
	var span =$("<span>");
	for(var i = 0; i < 5; i++){
		span.append($("<i>").addClass("fa fa-lg fa-star").css("color",number >= i+1 ? "#fc0" : "#ccc"));
	}
	return span.html();
}