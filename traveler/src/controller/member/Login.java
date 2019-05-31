package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

@WebServlet("/login")	
public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userid = req.getParameter("userid");
		String pw = req.getParameter("pw");
		MemberVo vo = MemberDao.getInstance().selectMember(userid, pw);
		System.out.println(vo+" :: "+userid+" :: "+pw);
		if (vo != null) {
			req.getSession().setAttribute("member", vo);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("login?message=fail");
		}
	}
	
}
