package shop.noldaga.reserve.controller;

import static shop.noldaga.common.util.Util.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.fastinfoset.tools.VocabularyGenerator;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Util;
import shop.noldaga.pension.service.PensionServiceImpl;
import shop.noldaga.reserve.service.ReserveService;
import shop.noldaga.reserve.service.ReserveServiceImpl;
import shop.noldaga.reserve.vo.ReserveVo;

@WebServlet("/reserve")
public class Reserve extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String psidx = req.getParameter("psidx");
		String rmidx = req.getParameter("rmidx");
		PensionServiceImpl dao = new PensionServiceImpl();
		
		if (rmidx == null || psidx == null
				|| isDateStr(startDate)
				|| isDateStr(endDate)
				|| isNotValidDate(startDate)
				|| isNotValidDate(endDate)
				|| startDate.compareTo(endDate) >= 0) {
			System.out.println("잘못된 접근입니다.");
			System.out.println(startDate);
			System.out.println(endDate);
			System.out.println(isDateStr(startDate));
			System.out.println(isDateStr(endDate));
			System.out.println(isNotValidDate(startDate));
			System.out.println(isNotValidDate(endDate));
			req.getRequestDispatcher(ConstPool.SOURCE+"/404.jsp").forward(req, resp);
			
		}else {
			
			int pi = 0;
			int ri = 0;
			try {
				pi = Integer.parseInt(psidx);
				ri = Integer.parseInt(rmidx);
				ReserveVo vo = new ReserveVo();
				vo.setPsidx(pi);
				vo.setRmidx(ri);
				vo.setStartdate(startDate);
				vo.setEnddate(endDate);
				
				System.out.println(vo);
				vo = dao.reserveDetail(vo);
				
				System.out.println("------------------------");
				System.out.println(vo);
				
				req.setAttribute("vo", vo);
				req.getRequestDispatcher(ConstPool.RESERVE_PATH+"/reserve.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("잘못된 접근입니다"); //forward
				req.getRequestDispatcher(ConstPool.SOURCE+"/404.jsp").forward(req, resp);
			}
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReserveVo vo = new ReserveVo();
		int psidx = Util.getParameterNumber(req.getParameter("psidx"));
		int rmidx = Util.getParameterNumber(req.getParameter("rmidx"));
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String startdate = req.getParameter("startdate");
		String enddate = req.getParameter("enddate");
		int days = Util.getParameterNumber(req.getParameter("days"));
		int money = Util.getParameterNumber(req.getParameter("money"));
		int moneyunit = Util.getParameterNumber(req.getParameter("moneyunit"));
		String pstitle = req.getParameter("pstitle");
		String rmtitle = req.getParameter("rmtitle");
		
		vo = new ReserveVo(psidx, rmidx, email, name, tel, startdate, enddate, days, money, moneyunit, pstitle, rmtitle);
		
		System.out.println(vo);
		
		ReserveService dao = new ReserveServiceImpl();
		dao.insert(vo);
		System.out.println(vo);
		
		resp.sendRedirect("reserveResult?rsidx="+vo.getRsidx());
	}
	
}
