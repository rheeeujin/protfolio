package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.ReplyDao;
import vo.BoardVo;
import vo.MemberVo;
import vo.ReplyVo;

@WebServlet("/modify")
public class Modify extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		if(session.getAttribute("member") == null) {
			resp.sendRedirect("login?message=no");
		}else {
			List<ReplyVo> reply = 
					ReplyDao.getInstance().selectMyReply(
							((MemberVo)session.getAttribute("member")).getUserid()
							);
			req.setAttribute("reply", reply);
			List<BoardVo> board = BoardDao.getInstance().selectMemberBoard(((MemberVo)session.getAttribute("member")));
			req.setAttribute("board", board);
			
			req.getRequestDispatcher("mypage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//수정사항저장
	}
	
}
