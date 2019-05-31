<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"	prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"	prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<section>
				<form method="post" action="#">
					<div class="fields">
						<div class="field">
							<label for="name">Name</label> <input type="text" name="name" id="name" placeholder="이름을 입력해주세요" value="${member.name}"/>
						</div>
						<div class="field">
							<label for="email">Email</label> <input type="text" name="email" id="email" placeholder="email을 입력해주세요" value="${member.email }"/>
						</div>
						<div class="field">
							<label for="message">Message</label>
							<textarea name="message" id="message" rows="3" placeholder="내용을 입력해주세요"></textarea>
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Send Message" /></li>
					</ul>
				</form>
			</section>
			<section class="split contact">
				<section class="alt">
					<h3>Address</h3>
					<p>
						1234 Somewhere Road #87257<br /> Nashville, TN 00000-0000
					</p>
				</section>
				<section>
					<h3>Phone</h3>
					<p>
						<a href="#">(000) 000-0000</a>
					</p>
				</section>
				<section>
					<h3>Email</h3>
					<p>
						<a href="#">email@email.com</a>
					</p>
				</section>
				<section>
					<h3>Social</h3>
					<ul class="icons alt">
						<li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
					</ul>
				</section>
			</section>
</body>
</html>