package teamtask0202;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Test123 {
	static List<Book> myBookList = new ArrayList<>();
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	static Book book = new Book("해리포터", "조앤롤링", "호그와트", "libaaa", "libaaa_0000000001");
	static void borrowBook(LibraryGroupService ls) { // 대여
		Calendar cal = Calendar.getInstance();
////		System.out.println("아이디를 입력해주세요");
////		String id = new Scanner(System.in).nextLine();
////		for (Book book : myBookList) {
////			if (book.getBorrowUser().equals(id)) {
////				System.out.println("연체기간 동안 책을 대여하실 수 없습니다.");
////			}
////		}
//		for (Member member : ls.members) {
//			if (member.getId().equals(id)) {
//				if (!(member.myBookList.size() < 5)) {
//					System.out.println("더 이상 책을 대여하실 수 없습니다.");
//				} else {
					System.out.println("책 코드를 입력해주세요");
					String primaryCode = new Scanner(System.in).nextLine();
					myBookList.forEach(System.out::println);
					for (Book book : myBookList) {
						if (book.getPrimaryCode().equals(primaryCode) && (book.isState())) {
//							book.setStartDay(sdf.format(cal.getTime()));
							System.out.println(book.getStartDay()+"getstartday");
							System.out.println(sdf.format(Calendar.getInstance().getTime()));
							
							book.setStartDay(sdf.format(cal.getTime()));
							cal.add(Calendar.DATE, 7);
							book.setEndDay(sdf.format(cal.getTime()));
							book.setState(false);
//							book.setBorrowUser(member.getId());
							System.out.println("정상적으로 대여되었습니다.");
						} else {
							System.out.println("잘못된 코드 이거나 현재 대여 중인 도서입니다");
						}
					}
				}

	public static void main(String[] args) {
		Test123.borrowBook(new LibraryGroupService());
	}
}
