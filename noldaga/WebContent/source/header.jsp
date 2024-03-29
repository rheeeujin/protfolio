<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>놀다가-noldaga</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="images/icon/favicon.ico" rel="icon">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="fonts/jua/css/jua.css">
<link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link rel="stylesheet" href="css/daterangepicker.min.css">
<!-- Theme Style -->
<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/member.css">
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="css/pension.css">


<script src="js/jquery-1.11.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.my.js"></script>
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/jquery.stellar.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script src="js/jquery.daterangepicker.min.js"></script>

<script src="js/jquery.my.js"></script>
<script src="js/main.js"></script>
<script src="js/pension.js"></script>
<script src="js/member.js"></script>
<script>
	var web_path = "${web_path}";
	var oridx = "${dao.oridx}";
</script>
<script>
$(function() {
    $('.header').on('click', '.search-toggle', function(e) {
      var selector = $(this).data('selector');
      $(selector).toggleClass('show').find('.search-input').focus();
      $(this).toggleClass('active');
      e.preventDefault();
    });
  })
</script>
</head>
<header role="banner">
        <div class="top-bar">
          <div class="container">
            <div class="row">
              <div class="col-9 social">
                <a href="index"><img src="images/logo.png" class="logo"></a>
              </div>
              <span id="header-3" class="header">
                  <nav class="header-nav">
                    <div class="search-button">
                      <a href="#" class="search-toggle" data-selector="#header-3"></a>
                    </div>
                    <ul class="menu social">
                      <li><a href="boardList">고객센터</a></li>
                      <c:if test="${member.email ==null}">
                      <li><a href="signup1">회원가입</a></li>
                      <li><a href="login">로그인</a></li>
                      </c:if>
                      <c:if test="${member.email !=null}">
                      <li><a href="mypage">내정보</a></li>
                      <li><a href="logout">로그아웃</a></li>
                      </c:if>
                    </ul>
                    <form action="list" method="get" class="search-box">
                      <input type="text" class="text search-input" name="search" placeholder="지역을 입력하세요." />
                      <input type="hidden" name="type" value="5">
                    </form>
                  </nav>
                </span>
            </div>
          </div>
        </div>

        <div class="container logo-wrap">
          <div class="row pt-5">
            <div class="col-12 text-center">
              <a class="absolute-toggle d-block d-md-none" data-toggle="collapse" href="#navbarMenu" role="button" aria-expanded="false" aria-controls="navbarMenu"><span class="burger-lines"></span></a>
              <h3 class="absolute-toggle d-block d-md-none menu-text">Menu</h3>
            </div>
          </div>
        </div>
        <nav class="navbar navbar-expand-md  navbar-light bg-light">
          <div class="container">
            <div class="collapse navbar-collapse" id="navbarMenu">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="nav-link" href="list">지역별 펜션</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="list?type=2">스파 펜션</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="list?type=3">풀빌라 펜션</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="list?type=4">워크샾/MT 펜션</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
<body>