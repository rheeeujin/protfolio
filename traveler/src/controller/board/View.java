package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;

import dao.BoardDao;
import dao.ReplyDao;
import vo.BoardVo;
import vo.FileVo;
import vo.ReplyVo;


@WebServlet("/view")
public class View extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bidx = req.getParameter("bidx") == null ? 0 : Integer.parseInt(req.getParameter("bidx"));

//		reply page
//		int maxPage = BoardDao.getInstance().getMaxPage();
//		int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page")) > maxPage ? maxPage : Integer.parseInt(req.getParameter("page"));
//		//maxPage = 100;
//		req.setAttribute("page", page);
//		req.setAttribute("maxPage", maxPage);
		
		if(bidx == 0) {
			resp.sendRedirect("index");
		}else {
			BoardVo vo = BoardDao.getInstance().selectBoard(bidx);
			req.setAttribute("board", vo);
			List<FileVo> files = BoardDao.getInstance().selectBoardFile(bidx);
			req.setAttribute("file", files);
			List<ReplyVo> replys = ReplyDao.getInstance().selectBoardReply(bidx);
			req.setAttribute("reply", replys);
			req.getRequestDispatcher("view.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		int bidx = Integer.parseInt(req.getParameter("bidx"));
		String reply = req.getParameter("reply");
		
		ReplyVo vo = new ReplyVo();
		vo.setBidx(bidx);
		vo.setUserid(userid);
		vo.setReply(reply);
		
		ReplyDao.getInstance().insertReply(vo);
		
		resp.sendRedirect("view?bidx="+bidx);
	}
	
}
