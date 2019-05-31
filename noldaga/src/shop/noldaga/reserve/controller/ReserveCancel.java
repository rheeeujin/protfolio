package shop.noldaga.reserve.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.common.util.Util;
import shop.noldaga.reserve.service.ReserveService;
import shop.noldaga.reserve.service.ReserveServiceImpl;

@WebServlet("/reserveCancel")
public class ReserveCancel extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("!!");
		int rsidx = Util.getParameterNumber(req.getParameter("rsidx"));
		
		new ReserveServiceImpl().cancel(rsidx);
		
		resp.sendRedirect("myreserve");
	}
}
