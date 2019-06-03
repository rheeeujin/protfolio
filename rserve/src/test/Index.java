package test;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class Index extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		String url = null;
		String path = req.getServletContext().getRealPath("") + "data\\"; // 폴더 경로
		File Folder = new File(path);
		System.out.println(path);
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		System.out.println("type : " + type);
		System.out.println("keyword : " + keyword);

		type = type.trim();
		if (type.equals("Nblog")) {
			url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query="+keyword;
			//System.out.println(req.getServletContext().getRealPath("") + "WebContent\\data\\");
			
			try {
				File file = new File(path+"rcrawl.png");
				 
				if( file.exists() ){
					if(file.delete()){
						System.out.println("파일삭제 성공");
					}else{
						System.out.println("파일삭제 실패");
					}
				}else{
					System.out.println("파일이 존재하지 않습니다.");
				}
				req.setAttribute("data", RserverTest.getWordCloud(path, url, type));
				resp.getWriter().print(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("data", e);
				resp.getWriter().print("error");
			}
		} else if (type.equals("Nnews")) {

		} else if (type.equals("Dblog")) {

		} else if (type.equals("Dnews")) {

		} else if (type.equals("google")) {

		}
	}

}
