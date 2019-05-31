package teamtask0202;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testclass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println("아이디입력");
//		String id = scanner.nextLine();
//		
//		String idPattern = "^(?=.*[a-z])(?=.*[0-9])[a-z[0-9]]{3,10}";
//		Matcher matcher2 = Pattern.compile(idPattern).matcher(id);
//		idPattern = "(.)\\1\\1";
//		Matcher matcher3 = Pattern.compile(idPattern).matcher(id);
//		
//		if (!(matcher2.matches())) {
//			System.out.println("숫자,영문 포함 3~12글자만 가능합니다.");
//		}
//		if (matcher3.find()) {
//			System.out.println("같은 문자 3개 이상 사용불가합니다.");
//		}
//		if (id.contains(" ")) {
//			System.out.println("아이디에 공백을 사용하실수 없습니다.");
//		}

//		System.out.println("비번입력");
//		String pw = scanner.nextLine();
//		String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";
//		Matcher matcher = Pattern.compile(pwPattern).matcher(pw);
//		
//		pwPattern = "(.)\\1\\1\\1";
//		Matcher matcher1 = Pattern.compile(pwPattern).matcher(pw);
//		
////		String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
////		Matcher matcher = Pattern.compile(pwPattern).matcher(pw);
//		
//		if (!(matcher.matches())) {
//			System.out.println("숫자,영문,특수문자 포함 9~12글자만 가능합니다.");
//		}
//		if (matcher1.find()) {
//			System.out.println("같은 문자 4개 이상 사용불가");
//		}
////		if (pw.contains(id)) {
////			System.out.println("비밀번호에 아이디를 사용하실수 없습니다.");
////		}
//		if (pw.contains(" ")) {
//			System.out.println("비밀번호에 공백을 사용하실수 없습니다.");
//		}

//		String target = "나는 2008년도에 입학했다a!";
//		String regEx = ".*\\d{1}.*";
//		// String regEx = ".*[0-9].*"; 와 동일함
//		String num = ".*[a-z].*";
//		String num1 = "^[`~!@#$%^&*()_-+=]";
//		if (target.matches(regEx)) {
//			System.out.println("일치");
//
//		} else {
//			System.out.println("불일치");
//		}
//		if (target.matches(num)) {
//			System.out.println("일치");
//
//		} else {
//			System.out.println("불일치");
//		}
//		if (target.matches(num1)) {
//			System.out.println("일치");
//
//		} else {
//			System.out.println("불일치");
//		}
		
//		System.out.println("이름을 입력");
//		String name = scanner.nextLine();
//		String namePattern = "^(?=.[ㄱ-ㅎㅏ-ㅣ가-힣])[ㄱ-ㅎㅏ-ㅣ가-힣]{2,}$";
//		Matcher matcher = Pattern.compile(namePattern).matcher(name);
//		if (!(matcher.matches())) {
//			System.out.println("2글자이상 한글만 입력가능합니다.");
//		}
//		System.out.println("책이름");
//		String bookName = scanner.nextLine();
//		String pattern = "^[A-za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣( )(.)]*$";
//		String pattern = "^[a-zA-Z0-9]*$";
//		Matcher matcher = Pattern.compile(pattern).matcher(bookName);
//		if (!(matcher.matches())) {
//			System.out.println("그외의 잡다한것들만");
//		}else {
//			System.out.println("알파벳 소문자 대문자 숫자 한글 공백포함");
//		}
//		System.out.println("하나의숫자");
//		String str = scanner.nextLine();
//		int num = Integer.parseInt(str);
//		for (int i = 0; i < 10; i++) {
//			if (num==i) {
//				System.out.println("쓸수없어");
//			}
//		}
//		
		
	}
}