package shop.noldaga.pension.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.noldaga.common.util.ConstPool;
import shop.noldaga.pension.service.PensionServiceImpl;

import static shop.noldaga.common.util.Util.*;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	Date today = new Date();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PensionServiceImpl dao = new PensionServiceImpl();
		String psidx = req.getParameter("psidx");
		String startDate = req.getParameter("startdate");
		String endDate = req.getParameter("enddate");
		System.out.println("is detailServlet................... psidx : "+psidx);
		
		if (isNotValidDate(startDate) || isDateStr(startDate)) {
			startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		if (isNotValidDate(endDate) || isDateStr(endDate) || startDate.compareTo(endDate) >= 0) {
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(toDate(startDate));
			endCal.add(Calendar.DATE, 1);
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(endCal.getTime());
		}
		System.out.println("start date: "+startDate);
		System.out.println("end date: "+endDate);
		List<Map<String, String>> list = dao.detailPension(psidx, startDate, endDate);
		System.out.println("------------------ room list ----------------------");
		list.forEach(System.out::println);
		req.setAttribute("dao", list.get(0));
		req.setAttribute("list", list);
		req.getRequestDispatcher(ConstPool.PENSION_PATH + "/pensionDetail.jsp").forward(req, resp);
	}
	/*private Date toDate(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return today;
		}
	}
	
	private boolean isDateStr(String str) {
		String pattern =  "^(\\d\\d\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$";
		return str.matches(pattern);
	}*/
	public static void main(String[] args) {
		String str1 = "2019-05-05";
		String str2 = "2019-05-25";
		System.out.println(str1.compareTo(str2));
		System.out.println(str2.compareTo(str2));
		System.out.println(str2.compareTo(str1));
	}
}