package teamtask0202;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author 정공명
 * 
 * @date 2019-01-26 bookDelete() 메서드 생성 * => Book클래스 : deleteCode(필드), get/set
 *       생성 , toString deleteCode 생성 => 메서드 작성 내용: myBookList의 도서명과 삭제할 도서명 일치하고
 *       deleteCode가 일치할 경우 myBookList에서 해당 책을 삭제.
 * 
 *       void bookAdd() 메서드 생성 => 메서드 작성 내용: myBookList의 도서명/작가/출판사가 일치 할 경우
 *       myBookList에 Book 생성자를 가져와서 name, writer, publisher, posLibrary,
 *       primaryCode 추가
 * 
 *       List<Book> add = new ArrayList<>() 생성
 * 
 * @date 2019-01-27 void aDelinquentList() 메서드 생성 (연체자 리스트) => getEndDay() 와
 *       현재시간을 비교 했을 때 0보다 클 경우(좌측값이 클 경우 1, 우측값이 클 경우 -1, 같을 경우 0) book에 연체자
 *       리스트 출력
 **/

public class Library extends User implements Serializable {
	List<String> aDelinquent = new ArrayList<>(); // 연체자 리스트
	List<Member> librarian = new ArrayList<>();

	Scanner scanner = new Scanner(System.in);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	// LibraryGroupService lgs = new LibraryGroupService();
	LibraryGroupApp lga = new LibraryGroupApp();
	Calendar cal = Calendar.getInstance();
	String sday = sdf.format(cal.getTime());

//		cal.add(Calendar.DATE, 7);
//		String eday = sdf.format(cal.getTime());
	public Library(String name, String id, String password) {
		super();
		setName(name);
		setId(id);
		setPassword(password);
	}

	void deleteBook(LibraryGroupService ls, String primaryCode) { // 책 파기
		for (Book book : myBookList) {
			if (book.getPrimaryCode().equals(primaryCode)) {
				System.out.println(book.getName() + "이(가) 파기 되었습니다.");
				ls.books.remove(book);
				myBookList.remove(book);
				ls.books.forEach(System.out::println);
				return;
			}
		}
		System.out.println("잘못된 코드입니다.");
	}

	Book bookAdd(Book addBook) { // 신권 추가
		boolean coi = true;
		addBook.setName(addBook.getName().trim());
		addBook.setWriter(addBook.getWriter().trim());
		addBook.setPublisher(addBook.getPublisher().trim());
		if (addBook.getName().equals(null) || addBook.getWriter().equals(null) || addBook.getPublisher().equals(null)
				|| addBook.getName().isEmpty() || addBook.getWriter().isEmpty() || addBook.getPublisher().isEmpty()) {
			System.out.println("책제목, 작가, 출판사는 필수값입니다.");
			return null;
		}
		for (Book book : myBookList) {
			if (book.getName().equals(addBook.getName()) && book.getWriter().equals(addBook.getWriter())
					&& book.getPublisher().equals(addBook.getPublisher())) {
				System.out.println("이미 도서관에 존재 하는 책입니다. 추가 하시겠습니까?(y/n)");
				Scanner scanner2 = new Scanner(System.in);
				String yesNo = scanner2.nextLine();
				if (yesNo.equals("n")) {
					coi = false;
					break;
				} else {
					break;
				}
			}
		}
		if (coi) {
			for (Book book : reqBookList) {
				if (book.getName().equals(addBook.getName()) && book.getWriter().equals(addBook.getWriter())
						&& book.getPublisher().equals(addBook.getPublisher())) {
					reqBookList.remove(addBook);
					System.out.println("신청목록에서 " + addBook.getName() + "을 제거했습니다.");
					break;
				}
			}
			DecimalFormat df = new DecimalFormat("0000000000");
			int tmp1;
			if (myBookList.size() != 0) {
				String tmpstr = myBookList.get(myBookList.size() - 1).getPrimaryCode();
				tmpstr = tmpstr.substring((tmpstr.indexOf("_") + 1));
				tmp1 = (Integer.parseInt(tmpstr));
			} else {
				tmp1 = 0;
			}
			tmp1 = tmp1 + 1;
			String priCode = getName() + "_" + df.format(tmp1);
			addBook.setPrimaryCode(priCode);
			addBook.setPosLibrary(getName());
			myBookList.add(addBook);
			System.out.println(addBook.getName() + "이 추가 되었습니다");
			return addBook;
		} else {
			System.out.println("책을 추가하지 않았습니다.");
			return null;
		}
	}

	void returnBook() { // 반납
		System.out.println("책 코드를 입력해주세요.");
		String primaryCode = scanner.nextLine();
		Calendar cal = Calendar.getInstance();
		for (Book book : myBookList) {
			if (book.getPrimaryCode().equals(primaryCode) && !book.isState()) {
				int returnDay = book.getEndDay().compareTo(sdf.format(cal.getTime()));
				try {
					for (int i = 0; i < aDelinquent.size(); i++) {
						if (book.getBorrowUser()
								.equals(aDelinquent.get(i).substring(0, aDelinquent.get(i).indexOf(",")))) {
							String[] tmp = aDelinquent.get(i).split(",");
							int returnDays = Integer.parseInt(tmp[1]) + Math.abs(returnDay);
							aDelinquent.get(i).replace(aDelinquent.get(i),
									(aDelinquent.get(i).substring(0, aDelinquent.get(i).indexOf(",")) + ","
											+ returnDays));
							// 멤버에 필드로 연체일 추가해서 대여할때 그거로 조건문 잡으면 쉬울듯.. 이거 얘기하기.
							book.setState(true);
							book.setStartDay("");
							book.setEndDay("");
							book.setBorrowUser("");
							System.out.println("지난 연체가 있습니다.");
							System.out.println("반납일로부터 총 연체 기간 동안 책을 대여하실 수 없습니다.");
						}
					}
				} catch (NullPointerException e) {
				} catch (Exception e) {
				}
				if (!(returnDay < 0)) {
					book.setState(true);
					book.setStartDay("");
					book.setEndDay("");
					book.setBorrowUser("");
					System.out.println("정상적으로 반납되었습니다.");
				} else {
					cal.add(Calendar.DATE, Math.abs(returnDay));
					aDelinquent.add(book.getBorrowUser() + "," + Math.abs(returnDay));
					book.setState(true);
					book.setStartDay("");
					book.setEndDay("");
					book.setBorrowUser("");
					System.out.println("반납일이 지난 책입니다.");
					System.out.println("반납일로부터 연체 기간 동안 책을 대여하실 수 없습니다.");
				}
			} else {
				System.out.println("잘못된 코드 이거나 현재 대여 중이 아닌 도서입니다.");
			}
		}
	}

	void borrowBook(LibraryGroupService ls) { // 대여
		System.out.println("아이디를 입력해주세요");
		String id = new Scanner(System.in).nextLine();
//		Calendar cal = Calendar.getInstance();
//		String sday = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 7);
		String eday = sdf.format(cal.getTime());
		for (Book book : myBookList) {
			if (book.getBorrowUser().equals(id)) {
				System.out.println("연체기간 동안 책을 대여하실 수 없습니다.");
			}
		}
		for (Member member : ls.members) {
			if (member.getId().equals(id)) {
				if (!(member.myBookList.size() < 5)) {
					System.out.println("더 이상 책을 대여하실 수 없습니다.");
				} else {
					System.out.println("책 코드를 입력해주세요");
					String primaryCode = new Scanner(System.in).nextLine();
					myBookList.forEach(System.out::println);
					for (Book book : myBookList) {
						if (book.getPrimaryCode().equals(primaryCode) && (book.isState())) {
//							
							System.out.println(sday);
							
							book.setStartDay(sday);
							book.setEndDay(eday);
							book.setState(false);
							book.setBorrowUser(member.getId());
							System.out.println("정상적으로 대여되었습니다.");
						} else {
							System.out.println("잘못된 코드 이거나 현재 대여 중인 도서입니다");
						}
					}
				}
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	void borrowBook(LibraryGroupService ls, String id) { // 대여
		Calendar cal = Calendar.getInstance();
		for(Book book : myBookList) {
			if(book.getBorrowUser().equals(id)) {
				System.out.println("연체기간 동안 책을 대여하실 수 없습니다.");
				return;
			}
		}
		for (Member member : ls.members) {
			if (member.getId().equals(id)) {
				if (!(member.myBookList.size() < 5)) {
					System.out.println("더 이상 책을 대여하실 수 없습니다.");
				} else {
					System.out.println("책 코드를 입력해주세요");
					String primaryCode = scanner.nextLine();
					for (Book book : myBookList) {
						if (book.getPrimaryCode().equals(primaryCode) && (book.isState() == true)) {
							book.setStartDay(sdf.format(cal.getTime()));
							cal.add(Calendar.DATE, 7);
							book.setEndDay(sdf.format(cal.getTime()));
							book.setState(false);
							book.setBorrowUser(member.getId());
							System.out.println("정상적으로 대여되었습니다.");
							return;
						}
					}
					System.out.println("잘못된 코드 이거나 현재 대여 중인 도서입니다");
				}
			}
		}
	}

	void borrowList(int input) { // 대여반납 현황 보는거만
		switch (input) {
		case 1:
			// 대여중인거
			for (Book book : myBookList) {
				if (!book.isState()) {
					System.out.println(book);
				}
			}
			break;
		case 2:
			// 대여가능한거
			for (Book book : myBookList) {
				if (book.isState()) {
					System.out.println(book);
				}
			}
			break;
		default:
			// 잘못누른거
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

	public void addLibrarian(Member member) {
		// TODO Auto-generated method stub
		System.out.println("기존 회원을 사서로 등록");
		librarian.add(member);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getName());
		out.writeUTF(getId());
		out.writeUTF(getPassword());
		out.writeObject(myBookList);
		out.writeObject(reqBookList);
		out.writeObject(librarian);
		out.writeObject(aDelinquent);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setName(in.readUTF());
		setId(in.readUTF());
		setPassword(in.readUTF());
		myBookList = (ArrayList<Book>) in.readObject();
		reqBookList = (ArrayList<Book>) in.readObject();
		librarian = (List<Member>) in.readObject();
		aDelinquent = (List<String>) in.readObject();
	}
}