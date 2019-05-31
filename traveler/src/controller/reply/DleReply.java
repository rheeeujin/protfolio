package controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReplyDao;

@WebServlet("/delreply")
public class DleReply extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ridx = Integer.parseInt(req.getParameter("ridx"));
		String userid = req.getParameter("userid");
		int bidx = Integer.parseInt(req.getParameter("bidx"));
		
		ReplyDao.getInstance().deleteReply(ridx, userid);
		resp.sendRedirect("view?bidx="+bidx);
	}
}
