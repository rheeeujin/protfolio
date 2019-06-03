package shop.noldaga.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {
	public static int getParameterNumber(String str) {
		if (str == null || str.equals("")) {
			return 1;
		} else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return 1;
			}
		}
	}

	public static String displayTime(Timestamp timestamp) {
		long gap = new Date().getTime() - timestamp.getTime();
		SimpleDateFormat sdf = null;
		if (gap < 1000 * 60 * 60 * 24) {
			sdf = new SimpleDateFormat("hh-mm-ss");
		} else {
			sdf = new SimpleDateFormat("yy-MM-dd");
		}
		return sdf.format(new Date(timestamp.getTime()));
	}

	public static int sendMail(String email) {
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
		p.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587"); // gmail 포트

		Authenticator auth = new MyAuthentication();

		// session 생성 및 MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try {
			// email = "noldagaman@gmail.com";
			String key = SecurityUtil.encryptSHA256(email);

			// 편지보낸시간
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress();

			from = new InternetAddress(
					new String("놀다가".getBytes("utf-8"), "8859_1") + "(NolDaGa)<noldagaman@gmail.com>");

			// 이메일 발신자
			msg.setFrom(from);

			// 이메일 수신자
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목
			msg.setSubject("놀다가(NolDaGa) 회원 인증 메일입니다.", "UTF-8");

			// 이메일 내용
			String realText = "";

			realText = "<!DOCTYPE html>\r\n" + "<html lang = 'ko'>\r\n" + "<head>\r\n" + "<meta charset='UTF-8'>\r\n"
					+ "<title>Noldaga</title>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "	<div style='width: 500px; margin: 0 auto; text-align: center; border: 1px solid #ddd; padding: 15px;'>\r\n"
					+ "		<h1 style='color: #e55e54; margin-top: 0;'>NolDaGa 가입을 환영합니다.</h1>\r\n"
					+ "		<p style='color: #777;'>아래 링크 클릭시 회원 인증이 완료됩니다.</p>\r\n"
					+ "		<a style='border: 0; text-decoration: none; padding: 5px 20px; color: #ddd; background: #e55e54;' href='http://localhost:8080/joinAuth?"
					+ "key=" + key + "&user=" + URLEncoder.encode(email, "utf-8") + "'>\r\n" + "		클릭</a>\r\n"
					+ "	</div>\r\n" + "</body>\r\n" + "</html>";
			msg.setText(realText, "UTF-8");

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			// 메일보내기
			javax.mail.Transport.send(msg);

		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	// 내부 클래스
	static class MyAuthentication extends Authenticator {
		PasswordAuthentication pa;

		public MyAuthentication() {

			String id = "email@gmail.com"; // 구글 ID
			String pw = "password"; // 구글 비밀번호

			// ID와 비밀번호를 입력한다.
			pa = new PasswordAuthentication(id, pw);
		}

		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}

	public static Date toDate(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
	}

	public static boolean isDateStr(String str) {
		String pattern = "^(\\d\\d\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$";
		if (str == null) {
			return false;
		}
		return !str.matches(pattern);
	}

	public static boolean isNotValidDate(String dateStr) {
		if (dateStr == null) {
			return true;
		}
		return new SimpleDateFormat("YYYY-MM-DD").format(new Date()).compareTo(dateStr) >= 0;
	}

	public static final String getJosa(String name, String firstValue, String secondValue) {
		if (name == null) {
			return null;
		}
		char lastName = name.charAt(name.length() - 1);
		if (lastName < 0xAC00 || lastName > 0xD7A3) {
			return name;
		}
		String seletedValue = (lastName - 0xAC00) % 28 > 0 ? firstValue : secondValue;
		return name + seletedValue;
	}
	
	public static void main(String[] args) {
		System.out.println(getJosa("가나다라", "이", "가"));
		System.out.println(getJosa("가나다랄", "이", "가"));
		System.out.println(getJosa("가나다랄", "을", "를"));
		System.out.println(getJosa("asdf", "이", "가"));
	}

}
