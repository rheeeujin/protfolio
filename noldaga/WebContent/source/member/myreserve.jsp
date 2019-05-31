<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../header.jsp"%>
<c:if test="${empty member}">
	<script>
		alert("세션정보가 만료되었습니다 다시 로그인 진행해주세요");
		location.href = "login";
	</script>
</c:if>
<style>
ul.list-group{list-style: none;}
#accordion li{padding: 0}
#accordion .ui-accordion-content{padding: 8px 20px}
</style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#accordion" ).accordion();
  } );
  </script>
<div class="wrap clearfix p-5">
	<div class="float-left w-25 p-2">
		<h1 class="text-center text-dark">MY PAGE</h1>
		<div class="user_img">
			<img src="images/member/userimg.png" alt="회원이미지" class="w-100 border">
		</div>
		<ul class="list-group">
			<li><a href="mypage" class="btn btn-block list-group-item mt-2 btn-outline-danger">정보수정 &gt; </a></li>
			<li><a href="myreserve" class="btn btn-block list-group-item mt-2 text-white bg-danger">예약정보 &gt; </a></li>
		</ul>
	</div>
	<div class="float-right w-75 p-2 pl-5">
		<h4 class="text-center">예약 정보</h4>
		<ul id="accordion" class="p-0">
		<c:forEach items="${list }" var="reserve">
			<li data-rsidx="${reserve.rsidx }" class="list-group-item">
				<div class="clearfix btn-block btn-light btn">
					<p class="float-left mb-0"><span class="text-danger">${reserve.pstitle }</span> / ${reserve.rmtitle }</p>
					<p class="float-right mb-0"><span class="text-danger">${reserve.days }</span>박</p>
				</div>
				<div>
					<div class="clearfix my-2">
						<p class="float-left mb-0">예약자 이름 : <span class="text-info">${reserve.name}</span></p>
						<p class="float-right mb-0">예약자 연락처 : <span class="text-info">${reserve.tel}</span></p>
					</div>
					<div class="clearfix small my-2">
						<p class="float-left mb-0">기간 : ${reserve.startdate } ~ ${reserve.enddate }</p>
						<p class="float-right mb-0">가격 : <span class="h4 text-danger"><fmt:formatNumber type="number" value="${reserve.money }"/></span>원(1박 당 <fmt:formatNumber type="number" value="${reserve.moneyunit }"/>원)</p>
					</div>
					<div class="clearfix">
						<a href="detail?psidx=${reserve.psidx }&startdate=${reserve.startdate}&enddate=${reserve.enddate}" class="float-right w-50 btn text-danger border rounded">팬션 보러가기</a>
						<a href="reserveCancel?rsidx=${reserve.rsidx }" class="float-left btn btn-danger rounded" onclick="return confirm('정말 취소 하시겠습니까?')">예약 취소</a>
					</div>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
