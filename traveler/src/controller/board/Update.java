package controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;
import dao.ReplyDao;
import vo.BoardVo;
import vo.FileVo;
import vo.MemberVo;
import vo.ReplyVo;


@WebServlet("/update")
public class Update extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect("login?message=no");
		}else {
			int bidx = req.getParameter("bidx") == null ? 0 : Integer.parseInt(req.getParameter("bidx"));
			if(bidx == 0) {
				resp.sendRedirect("index");
			}else {
				BoardVo vo = BoardDao.getInstance().selectBoard(bidx);
				req.setAttribute("board", vo);
				List<FileVo> files = BoardDao.getInstance().selectBoardFile(bidx);
				req.setAttribute("file", files);
				List<ReplyVo> replys = ReplyDao.getInstance().selectBoardReply(bidx);
				req.setAttribute("reply", replys);
				req.getRequestDispatcher("update.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String dir = getServletContext().getRealPath("/upload");
		System.out.println(dir);
		File file = new File(dir);
		if(!file.exists()) {
			file.mkdirs();
		}
		HttpSession session =req.getSession();
		MultipartRequest multi = new MultipartRequest(req, dir, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		int bidx = Integer.parseInt(multi.getParameter("bidx"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setBidx(bidx);
		vo.setBcontent(content);
		vo.setPreview(multi.getFilesystemName("file1"));
		//2
		List<FileVo> list = new ArrayList<FileVo>();
		
		BoardDao.getInstance().deleteFiles(bidx);
		for (int i = 0; i < 3; i++) {
			String origin = multi.getOriginalFileName("file"+(i+1));
			String realName = multi.getFilesystemName("file"+(i+1));
			if(!(origin == null || realName == null || origin.equals("") || realName.equals(""))) {
				list.add(new FileVo(origin, realName));
			}
		}
		System.out.println(vo+" :: "+list);
		
		BoardDao.getInstance().updateBoard(vo, list);
		resp.sendRedirect("index");
	}
	
}
