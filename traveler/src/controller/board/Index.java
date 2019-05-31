package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import vo.BoardVo;


@WebServlet("/index")
public class Index extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int maxPage = BoardDao.getInstance().getMaxPage();
		int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page")) > maxPage ? maxPage : Integer.parseInt(req.getParameter("page")) < 1 ?1:Integer.parseInt(req.getParameter("page"));
		//maxPage = 100;
		req.setAttribute("page", page);
		req.setAttribute("maxPage", maxPage);
		
		List<BoardVo> board = new ArrayList<>();
		board = BoardDao.getInstance().selectALLBoard(page);
		
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}
}
