<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Topbar -->
<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
				<div class="modal-footer">
				<form action="../customLogout" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<button class="btn btn-primary" href="login.html">Logout</button>
				</form>
				</div>
			</div>
		</div>
	</div>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

  <!-- Sidebar Toggle (Topbar) -->
  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
    <i class="fa fa-bars"></i>
  </button>

  <!-- Topbar Search -->
  <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" id="searchForm">
    <div class="input-group">
      <select name="type" class="form-control small border-0 bg-light">
      	<option value="">--</option>
      	<option value="T">제목</option>
      	<option value="C">내용</option>
      	<option value="W">작성자</option>
      	<option value="TC">제목 or 내용</option>
      	<option value="TW">제목 or 작성자</option>
      	<option value="TMC">제목 or 내용 or 작성자</option>
      </select>
      <input type="text" class="form-control bg-light border-0 small" name="keyword" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" value="${param.keyword}">
      <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <div class="input-group-append">
        <button class="btn btn-primary" type="submit">
          <i class="fas fa-search fa-sm"></i>
        </button>
      </div>
    </div>
  </form>

  <!-- Topbar Navbar -->
 <ul class="navbar-nav ml-auto">

	<!-- <Nav Item - User Information> -->

    <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      	<security:authorize access="isAuthenticated()">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
        	<security:authentication property="principal.username"/>
        	(<security:authentication property="principal.vo.userName"/>)
        </span>
        <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
        </security:authorize>
        
        <security:authorize access="!isAuthenticated()">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">NOT LOGIN</span>
        </security:authorize>
      </a>
      <!-- Dropdown - User Information -->
      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
      <security:authorize access="isAuthenticated()">
        <a class="dropdown-item" href="#">
          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
          Profile
        </a>
        <a class="dropdown-item" href="#">
          <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
          Settings
        </a>
        <a class="dropdown-item" href="#">
          <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
          Activity Log
        </a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/customLogout" data-toggle="modal" data-target="#logoutModal">
          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
          Logout
        </a>
        </security:authorize>
        <security:authorize access="!isAuthenticated()">
         <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/customLogin">
          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
          Login
        </a>
        </security:authorize>
      </div>
    </li>

  </ul>

</nav>
<!-- End of Topbar -->