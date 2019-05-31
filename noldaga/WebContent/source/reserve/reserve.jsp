<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../header.jsp" />
<style>
.reserver-info {
	width: 60%
}

.reserve-pension-info {
	width: 38%;
}

.check-aria input[type="checkbox"] { padding: 10px; transform: scale(1.5); margin-right: 10px; }
.modal-backdrop { z-index: 5; }
.modal { z-index: 10; }
.modal table tb, .modal table th{}
</style>
<div class="modal" id="modal1">
	<div class="modal-header">
		<strong>이용규칙 및 취소/환불규정</strong>
	</div>
	<div class="modal-body small">
		<ul class="list-group ">
			<li>최대 인원 초과 시 입실 불가합니다.</li>
			<li>정원 기준 요금 외 인원 추가 요금은 현장결제입니다.</li>
			<li>제공 이미지는 배정된 객실과 다를 수 있습니다.</li>
			<li>제공 정보는 숙소의 사정에 따라 변경될 수 있습니다.</li>
			<li>미성년자는 보호자 동반 시 투숙이 가능합니다.</li>
			<li>반려동물은 숙소 규정에 따라 출입이 제한되오니 숙소별 상세정보를 꼭 확인해주세요.</li>
			<li>시즌 별 객실 가격 상이하오니 확인바랍니다.</li>
			<li>업체 현장에서 객실 컨디션 및 서비스로 인해 발생된 분쟁은 여기어때에서 책임지지 않습니다.</li>
			<li><mark>비회원으로 결제 시 취소 및 환불이 불가합니다.</mark></li>
			<li>숙소 사정에 의해 취소 발생 시 100% 환불이 가능합니다.</li>
			<li>단! 숙소의 객실 정보가 수시로 변경될 수 있으며 이로 인한 불이익은 여기어때가 책임지지 않습니다.</li>
		</ul>
	</div>
</div>
<div class="modal" id="modal2">
	<div class="modal-header">
		<strong>개인정보 수집 및 이용 동의</strong>
	</div>
	<div class="modal-body small">
		<div class="scroller" style="transform: translate(0px, 0px) translateZ(0px);">
			<div class="txt">
				<p>“놀다가 Noldaga” 개인정보 수집 이용 목적 및 항목, 보유기간은 아래와 같습니다.<br>“회원” 은 정보 수집/이용 약관에 동의하지 않을 수 있으며, 동의하지 않는 경우 예약 서비스 이용에 제한이 있을 수 있습니다.</p>
				<table cellpadding="0" cellspacing="0" class="pop_table">
				<colgroup><col width="33%"><col width="33%"><col width="34%"></colgroup>
				<tbody><tr>
					<th>수집/이용 목적</th>
					<th>위탁 업무 내용</th>
					<th>개인 정보의 보유 및 이용 기간</th>
				</tr>
				<tr>
					<td>예약 서비스 이용</td>
					<td>예약자명, 휴대폰 번호, 결제정보</td>
					<td>전자상거래 상 소비자 보호에 관한 법률에 따라 5년 간 보관</td>
				</tr>
				<tr>
					<td>결제 서비스 이용</td>
					<td>- 신용카드 결제: 카드사명, 카드번호, 유효기간, 이메일 등 - 휴대폰 결제: 휴대폰 번호, 통신사, 결제 승인번호 등 - 계좌 이체 시: 은행명, 계좌번호, 예금주</td>
					<td>전자상거래 상 소비자 보호에 관한 법률에 따라 5년 간 보관</td>
				</tr>
				<tr>
					<td>서비스 이용 및 부정거래 기록 확인</td>
					<td>서비스 이용 시간/이용 기록, 접속 로그, 이용 콘텐츠, 쿠키, 접속IP 정보, 주소, 사용된 신용카드 정보, 기기 모델명, 브라우저 정보</td>
					<td>통신비밀보호법에 따라 3개월간 보관</td>
				</tr>
				</tbody></table>
			</div>
        </div>
	</div>
</div>
<div class="modal" id="modal3">
	<div class="modal-header">
		<strong>개인정보 제 3자 제공 동의</strong>
	</div>
	<div class="modal-body small">
		<div class="txt">
			<p>회원 정보를 제공받는 자, 제공목적, 제공하는 정보, 보유 · 이용기간은 아래와 같습니다.<br>"회원"은 정보 수집/이용 약관에 동의하지 않을 수 있으며, 동의하지 않는 경우 예약 서비스 이용에 제한이 있을 수 있습니다.</p>			
			<table cellpadding="0" cellspacing="0" class="pop_table">
			<colgroup><col width="30%"><col width="70%"></colgroup>
			<tbody><tr>
				<th>제공받는 자</th>
				<td>${vo.pstitle }</td>
			</tr>
			<tr>
				<th>제공 목적</th>
				<td>숙박예약서비스 이용계약 이행(서비스 제공, 확인, 이용자 정보 확인)</td>
			</tr>
			<tr>
				<th>제공하는 정보</th>
				<td>예약한 숙박서비스의 이용자 정보(예약자 이름, 휴대폰번호, 예약번호, 예약한 업체명, 예약한 객실명, 결제금액)</td>
			</tr>
			<tr>
				<th>제공받는 자의 개인정보 보유 및 이용기간</th>
				<td>예약서비스 제공 완료 후 6개월</td>
			</tr>
			</tbody></table>
		</div>
	</div>
</div>
<form method="post" id="reserveForm" name="reserveForm">

<input type="hidden" name="psidx" value="${vo.psidx }">
<input type="hidden" name="rmidx" value="${vo.rmidx }">
<input type="hidden" name="email" value="${member.email }">
<input type="hidden" name="nameHidden" value="${member.name }">
<input type="hidden" name="telHidden" value="${member.tel }">
<input type="hidden" name="startdate" value="${vo.startdate }">
<input type="hidden" name="enddate" value="${vo.enddate }">
<input type="hidden" name="money" value="${vo.money }">
<input type="hidden" name="moneyunit" value="${vo.moneyunit }">
<input type="hidden" name="pstitle" value="${vo.pstitle }">
<input type="hidden" name="rmtitle" value="${vo.rmtitle }">
<input type="hidden" name="days" value="${vo.days }">

<div class="wrap content clearfix">
	<div class="reserver-info float-left p-3">
		<h3>예약자 정보</h3>
		<div class="form-group">
			<h5 class="mt-3">예약자 이름</h5>
			<input class="form-control" type="text" name="name" placeholder="${member.name }">
			<p class="small bg-light text-secondary pl-2">예약자 이름 미입력시 회원님의
				이름으로 등록됩니다.</p>
		</div>
		<div class="form-group">
			<h5 class="mt-3">휴대폰 번호</h5>
			<input class="form-control" type="text" name="tel" placeholder="${member.tel }">
			<p class="small bg-light text-secondary pl-2">휴대폰 번호 미입력시 회원님의
				번호로 등록됩니다.</p>
		</div>
		<div class="form-group">
			<h5 class="mt-3">결제수단</h5>
		</div>
		<select>
			<option>무통장입금</option>
		</select>
		<div class="h5 mt-5 check-aria">
			<p>
				<label class="text-primary"><input type="checkbox" id="allAgreeChk">전체동의</label>
			</p>
			<p>
				<label><input type="checkbox"> 숙소이용규칙 및 취소 / 환불 규정 <a href="" class="text-danger modalLink" data-link="modal1">(필수)</a></label>
			</p>
			<p>
				<label><input type="checkbox"> 개인정보 수집 및 이용 동의 <a href="" class="text-danger modalLink" data-link="modal2">(필수)</a></label>
			</p>
			<p>
				<label><input type="checkbox"> 개인정보 제 3자 제공 동의 <a href="" class="text-danger modalLink" data-link="modal3">(필수)</a></label>
			</p>
			<p>
				<label><input type="checkbox"> 만 14세 이상입니다. <span class="text-danger modalLink">(필수)</span></label>
			</p>
		</div>

	</div>
	<div class="reserve-pension-info float-right p-3 bg-light mr-3">
		<h5 class="text-secondary mt-3">숙소이름</h5>
		<p class="h4 text-dark">${vo.pstitle }</p>

		<h5 class="text-secondary mt-4">객실타입/기간</h5>
		<p class="h4 text-dark">${vo.rmtitle}/${vo.days}박</p>

		<h5 class="text-secondary mt-4">체크인</h5>
		<p class="h4 text-dark">${vo.startdate } / 14:00</p>

		<h5 class="text-secondary mt-4">체크아웃</h5>
		<p class="h4 text-dark">${vo.enddate } / 11:00</p>

		<h5 class="text-secondary mt-4">총결제금액(VAT)포함</h5>
		<p class="h3 text-danger">
			<fmt:formatNumber type="number" value="${vo.money }"></fmt:formatNumber>
			<span class="text-dark">원</span>
		</p>

		<ul class="list-group">
			<li class="list-group-item small">해당 객실가는 세금, 봉사료가 포함된 가격입니다.</li>
			<li class="list-group-item small">결제완료 후 예약자 이름으로 바로 체크인 하시면
				됩니다.</li>
		</ul>
		<button class="mt-5 form-control btn-outline-danger">결제하기</button>
	</div>
</div>
</form>
<script type="text/javascript">
$(".modalLink").click(function(e){
	e.preventDefault();
	$("#"+$(this).data("link")).modal("show");
})
$("#allAgreeChk").change(function () {
	$(this).closest("div").find("input:checkbox").prop("checked", $(this).prop("checked"));
})
$("#reserveForm button").click(function(e) {
	e.preventDefault();
	var submitFlag = true;
	$("#reserveForm .check-aria [type='checkbox']:not(#allAgreeChk)").each(function() {
		if(!$(this).prop("checked")){
			alert("필수약관에 동의해주세요.")
			submitFlag = false
			return false;
		}
	})
	console.log("end of click event")
	
	if(submitFlag){
		if($(reserveForm.name).val() == ''){
			$(reserveForm.name).val($(reserveForm.nameHidden).val())
		}
		if($(reserveForm.tel).val() == ''){
			$(reserveForm.tel).val($(reserveForm.telHidden).val())
		}
		$("#reserveForm").submit();
		
	}
})
</script>
<jsp:include page="../footer.jsp"></jsp:include>
