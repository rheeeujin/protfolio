package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

@WebServlet("/signup")	
public class Signup extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		MemberVo vo = new MemberVo();
		vo.setUserid(userid);
		vo.setPw(req.getParameter("pw"));
		vo.setName(req.getParameter("name"));
		vo.setEmail(req.getParameter("email"));
		MemberDao.getInstance().insertMember(vo);
		resp.sendRedirect("login?id="+vo.getUserid());
	}
	
}
