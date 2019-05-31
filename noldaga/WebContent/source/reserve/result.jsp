<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/myutil.tld" prefix="my" %>
<jsp:include page="../header.jsp" />
<div class="wrap">
<%-- ${my:toDate('2019-05-04')} --%>

	<div class="w-75 p-3 m-auto">
	<c:if test="${not empty member and not empty vo and member.email == vo.email }">
		<div class="p-4">
			<p><span class="text-info">${vo.name }</span>님, <span class="text-info">${my:getJosa(vo.pstitle,'을','를')}</span> 이용해주셔서 감사합니다.</p>
			<p>예약이 완료되었습니다. 영수증 및 상세정보를 <span class="text-info">${vo.email }</span>로 곧 전송해 드리겠습니다</p>
		</div>
			
		<div class="clearfix shadow p-4">
			<div class="float-left">
				<h4>${vo.pstitle }</h4>
				<h5>${vo.rmtitle } / <span class="text-info">${vo.days }</span>박</h5>
			</div>
			<div class="float-right">
				<p>총 비용 <span class="h3 text-danger"><fmt:formatNumber type="number" value="${vo.money }" /></span> 원</p>
			</div>
		</div>
		<div>
			<a href="mypage" class="btn-primary btn-block p-3 center"> 마이 페이지 이동 </a>
		</div>
	</c:if>
	<c:if test="${!(not empty member and not empty vo and member.email == vo.email )}">
		<h4 class="text-center">잘못된 접근입니다.</h4>
		<div>
			<a href="index" class="btn-primary btn-block p-3 center"> 메인 페이지 이동 </a>
		</div>
	</c:if>
	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
