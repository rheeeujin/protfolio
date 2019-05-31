package shop.noldaga.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.member.service.MemberServiceImpl;
import shop.noldaga.member.vo.Member;
import shop.noldaga.reserve.service.ReserveServiceImpl;
@WebServlet("/myreserve")
public class MyReserve extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (req.getSession().getAttribute("member") == null) {
			resp.sendRedirect("mypage");
		}
		String email = ((Member)req.getSession().getAttribute("member")).getEmail();
		req.setAttribute("list", new ReserveServiceImpl().selectByEmail(email));
		req.getRequestDispatcher(ConstPool.MEMBER_PATH+"/myreserve.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String email=req.getParameter("email");	
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String address= req.getParameter("address");
		String tel= req.getParameter("tel");

	    Member vo = new Member();
	
        vo.setEmail(email); 
		vo.setPw(pw);
		vo.setName(name);
		vo.setAddress(address);
		vo.setTel(tel);
		new MemberServiceImpl().mypage(vo);
		req.getSession().setAttribute("member", vo);
		
		resp.sendRedirect("index");
	}

	
}
