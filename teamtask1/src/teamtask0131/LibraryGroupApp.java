package teamtask0131;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author 이유진
 * @since 01/18 로그인 후 페이지{ 로그아웃 추가 로그아웃시 앱 첫페이지로 이동 로그인 되었습니다 문구 삭제
 *
 */
public class LibraryGroupApp {
	public static void main(String[] args) throws ClassNotFoundException {
		LibraryGroupService ls = new LibraryGroupService();
		String memfile = "memberlist.txt";
		String libfile = "liblist.txt";
		String bookfile = "booklist.txt";
		String rbookfile = "rbooklist.txt";
		FileInputStream fis;
		BufferedInputStream bis;
		ObjectInputStream ois;

		try {
			fis = new FileInputStream(memfile);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			ls.members = (ArrayList<Member>) ois.readObject();
			ois.close();

		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			fis = new FileInputStream(libfile);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			ls.libraries = (ArrayList<Library>) ois.readObject();
			ois.close();
		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			fis = new FileInputStream(bookfile);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			ls.books = (ArrayList<Book>) ois.readObject();
			ois.close();
		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			fis = new FileInputStream(rbookfile);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			ls.rbook = (ArrayList<Book>) ois.readObject();
			ois.close();

		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Scanner scanner = new Scanner(System.in);

		// 앱첫페이지
		System.out.println();
		System.out.println();
		System.out.println("                       ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("                       ■                                                        ■");
		System.out.println("                       ■        +--------------------------------------+        ■");
		System.out.println("                       ■        |          2조 더나은 도서관           |        ■");
		System.out.println("                       ■        +--------------------------------------+        ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    |   | |   | |   | |   |  |   | |   | |   | |   |    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    |   | |   | |   | |   |  |   | |   | |   | |   |    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■                ◆◆◆◆◆◆◆◆◆◆◆◆                ■");
		System.out.println("                       ■                ◆◆◆◆◆◆◆◆◆◆◆◆                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		
		while (true) {
			System.out.println("                                               더나은 도서관");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 1. 로그인      |");
			System.out.println("                                            +----------------+");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 2. 회원가입    |");
			System.out.println("                                            +----------------+");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 0. 종료        |");
			System.out.println("                                            +----------------+");

			try {
				
				String input = scanner.nextLine();

				switch (Integer.parseInt(input)) {
				case 1: // 로그인
					// 로그인 후 페이지
					boolean logck = ls.logIn();

					while (logck) {
						System.out.println("1. 내 책장 2. 도서 검색 3. 도서 신청 4. 사서 0.종료");
						input = scanner.nextLine();
						switch (Integer.parseInt(input)) {
						case 1: // 내 책장
							System.out.println("현재 회원님이 대여하신 책(들)입니다.");
							ls.nowUser.myBookList.forEach(System.out::println);
							System.out.println("현재 회원님이 신청하신 책(들)입니다.");
							ls.nowUser.reqBookList.forEach(System.out::println);
							break;
						case 2: // 도서 검색
							System.out.println("1. 도서명 검색 2. 작가 검색 3. 출판사명 검색");
							input = scanner.nextLine();
							switch (Integer.parseInt(input)) {
							case 1: // 도서명 검색
								System.out.println("도서명을 입력해주세요");
								input = scanner.nextLine();
								ls.findBook("도서명", input);
								break;
							case 2: // 작가 검색
								System.out.println("작가를 입력해주세요");
								input = scanner.nextLine();
								ls.findBook("작가", input);
								break;
							case 3: // 출판사명 검색
								System.out.println("출판사를 입력해주세요");
								input = scanner.nextLine();
								ls.findBook("출판사", input);
								break;
							default:
								System.out.println("잘못 누르셨습니다. 이전 화면으로 돌아갑니다.");
								break;
							}
							break;
						case 3: // 도서 신청
							ls.reqBook();
							break;
						case 4: // 사서
							Librarian: while (ls.librarian()) {
								System.out
										.println("1. 대여/반납 현황 2. 신권 추가 3. 연체자 목록 4. 도서 삭제 5. 도서 신청 목록 6. 사서 추가 0. 복귀");
								input = scanner.nextLine();
								switch (Integer.parseInt(input)) {
								case 1: // 대여/반납 현황
									System.out.println("도서관 책 목록");
									ls.nowLib.myBookList.forEach(System.out::println);

									System.out.println("1. 대여중인 도서현황 2. 대여가능한 도서현황");
									input = scanner.nextLine();
									switch (Integer.parseInt(input)) {
									case 1:
										ls.nowLib.borrowList(1);
										break;
									case 2:
										ls.nowLib.borrowList(2);
										break;
									default:
										ls.nowLib.borrowList(0);
										break;
									}
									break;
								case 2: // 신권 추가
									System.out.println("도서명을 입력해주세요");
									String name = scanner.nextLine();
									System.out.println("작가명을 입력해주세요");
									String writer = scanner.nextLine();
									System.out.println("출판사를 입력해주세요");
									String publisher = scanner.nextLine();

									Book addBook = new Book(name, writer, publisher, ls.nowLib.getName(), null);

									if ((addBook = ls.nowLib.bookAdd(addBook)) != null) {
										ls.books.add(addBook);
									} else {
										System.out.println("도서 추가 실패");
									}

									// ls.libraries.get(ls.libraries.indexOf(ls.nowLib)).myBookList.add(addBook);
									// ls.books.add(addBook);
									break;
								case 3: // 연체자 목록
									System.out.println("연체자 목록입니다.");
									ls.nowLib.aDelinquent.forEach(System.out::println);
									break;
								case 4: // 도서 삭제
									System.out.println("도서코드를 입력해주세요.");
									String delCode = scanner.nextLine();
									ls.nowLib.deleteBook(delCode);
									break;
								case 5: // 도서 신청 목록
									System.out.println("도서 신청 목록입니다.");
									ls.nowLib.reqBookList.forEach(System.out::println);
									break;
								case 6: // 사서 추가
									System.out.println("등록 하실 사서 ID를 입력해주세요");
									String librarianId = scanner.nextLine();
									for (Member member : ls.members) {
										if (member.getId().equals(librarianId)) {
											ls.nowLib.librarian.add(member);
											System.out.println("사서 추가 완료");
											break;
										}
									}
									System.out.println("회원가입한 회원만 사서로 추가 가능합니다.");
									break;
								case 0:
									break Librarian;
								default:
									break;
								}
							}
							continue;
						case 0:
							System.out.println("======logout============");
							logck = false;
							break;
						default:
							System.out.println("잘못 누르셨습니다. 올바른 값을 입력해주세요(1~4)");
							break;
						}
						if (!logck) {
							break;
						}
					}
					continue;
				case 2: // 회원가입
					ls.signUp();
					continue;
				case 0: // 종료
					System.out.println("APP exit");
					break;
//				case 9: // 관리자
//					System.out.println("관리자 기능입니다.");
//					System.out.println("id >>");
//					if (scanner.nextLine().equals("libAppSys")) {
//						System.out.println("pw >>");
//						if (scanner.nextLine().equals("sys123")) {
//
//						}
//					}
//					System.out.println("관리자 기능은 관리자만 사용가능합니다. app을 종료합니다.");
//					break;
				default:
					System.out.println("알맞은 값을 입력해주세요");
					continue;
				}

				break;
			} catch (Exception e) {
				System.err.println("숫자만 입력해주세요");
			}
		}

		scanner.close();

		FileOutputStream fos;
		BufferedOutputStream bos;
		ObjectOutputStream oos;
		try {

			// memberlist file
			fos = new FileOutputStream(memfile);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(ls.members);
			oos.close();

		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			fos = new FileOutputStream(libfile);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(ls.libraries);
			oos.close();

		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			fos = new FileOutputStream(bookfile);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(ls.books);
			oos.close();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			fos = new FileOutputStream(rbookfile);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(ls.rbook);
			oos.close();

		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
