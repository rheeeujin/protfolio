<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../header.jsp"%>
<c:if test="${empty member}">
	<script>
		alert("세션정보가 만료되었습니다 다시 로그인 진행해주세요");
		location.href = "login";
	</script>
</c:if>
<style>
ul.list-group{list-style: none;}
</style>
<div class="wrap clearfix p-5">
	<div class="float-left w-25 p-2">
		<h1 class="text-center text-dark">MY PAGE</h1>
		<div class="user_img">
			<img src="images/member/userimg.png" alt="회원이미지" class="w-100 border">
		</div>
		<ul class="list-group">
			<li><a href="mypage" class="btn btn-block list-group-item mt-2 text-white bg-danger">정보수정 &gt; </a></li>
			<li><a href="myreserve" class="btn btn-block list-group-item mt-2 btn-outline-danger">예약정보 &gt; </a></li>
		</ul>
	</div>
	<div class="float-right w-75 p-2 pl-5">
		<h4>회원정보 수정</h4>
		<form method="post" name="frm">
			<div class="mypage_input">
				<input type="hidden" name="email" value="${member.email}">
				<table class="w-100">
					<tr>
						<th class="w-25 p-3 test-center h4">성명</th>
						<td class="w75 p-3"><input class="form-control" type="text" name="name" value="${member.name}" placeholder="이름을 입력해주세요"></td>
					</tr>
					<tr>
						<th class="w-25 p-3 test-center h4">주소지</th>
						<td class="w75 p-3"><input class="form-control" type="text" name="address" value="${member.address}"></td>
					</tr>
					<tr>
						<th class="w-25 p-3 test-center h4">연락처</th>
						<td class="w75 p-3"><input class="form-control" type="text" name="tel" value="${member.tel}"></td>
					</tr>
					<tr>
						<td colspan="2" class="px-3 pt-4">
							<button class="btn btn-dark btn-block rounded"  onclick="return confirm('정말 수정 하시겠습니까?')">정보 수정</button>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="px-3 pt-1">
						<a href="resign"><button class="btn btn-danger btn-block rounded" type="button" onclick="return confirm('정말 탈퇴하시겠습니까?')">회원 탈퇴</button></a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
