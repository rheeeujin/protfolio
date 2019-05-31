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
import vo.BoardVo;
import vo.FileVo;
import vo.MemberVo;


@WebServlet("/write")
public class Write extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect("login?message=no");
		}else {
			req.getRequestDispatcher("write.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String dir = getServletContext().getRealPath("/upload");
		System.out.println("write");
		System.out.println(dir);
		File file = new File(dir);
		if(!file.exists()) {
			file.mkdirs();
		}
		HttpSession session =req.getSession();
		MultipartRequest multi = new MultipartRequest(req, dir, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String userid = ((MemberVo)session.getAttribute("member")).getUserid();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setUserid(userid);
		vo.setBcontent(content);
		vo.setPreview(multi.getFilesystemName("file1"));
		//2
		List<FileVo> list = new ArrayList<FileVo>();
		
		for (int i = 0; i < 3; i++) {
			String origin = multi.getOriginalFileName("file"+(i+1));
			String realName = multi.getFilesystemName("file"+(i+1));
			if(!(origin == null || realName == null || origin.equals("") || realName.equals(""))) {
				list.add(new FileVo(origin, realName));
			}
		}
		System.out.println(vo+" :: "+list);
		
		BoardDao.getInstance().insertBoard(vo, list);
		resp.sendRedirect("index");
	}
	
}
