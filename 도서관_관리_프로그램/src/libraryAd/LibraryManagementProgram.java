package libraryAd;

import java.util.*;
/* 
도서 관리 메인 클래스. 메인 메뉴를
시작으로 관리자 모드와 회원 모드로 나뉘며 각각 로그인 한 후 해당하는 작
업을 수행할 수 있다. 관리자는 별도의 회원 가입을 필요로 하지 않으며 미리
정의한(pre-dened) 관리자 정보와 입력한 ID, 비밀번호가 일치하면 로그인
할 수 있다 
*/
public class LibraryManagementProgram {
	private static Scanner scanner = new Scanner(System.in);
	private static final String managerID = "0000";
	private static final String managerName = "홍길동";
	private static final String managerPWD = "1234";
	static Manager manager = new Manager(managerID, managerName, managerPWD);
	private static TotalLibraryManage libManager = new TotalLibraryManage();
	static Person currentPerson = null;
	
	public static void main(String[] args) {
		// (N)
	while (true) {
		printMainMenu();
		System.out.print("입력 >> ");
		int selectNum = scanner.nextInt();
		scanner.nextLine();
		if (selectNum == 1) { // 관리자 모드
			System.out.println("-------관리자 모드입니다. 로그인 하십시오.---------------");
			System.out.print("ID : ");
			String id = scanner.nextLine();
			
			System.out.print("비밀번호 : ");
			String pw = scanner.nextLine();
			if(id.equals(managerID) == false || pw.equals(managerPWD) == false) {
				System.out.println("\n관리자 아이디 혹은 비밀번호가 일치하지 않아 초기 화면으로 돌아갑니다.\n");
				continue;
			}
			// 로그인 후 이용 가능. 정보가 일치하지 않으면 초기 메뉴로...
			while(true) {
				printManagerMenu();
				System.out.print("<선택> ");
				int inp = scanner.nextInt();
				scanner.nextLine();
				if(inp == 1) {
					currentPerson = manager;
					libManager.ManagerRun(currentPerson);
				}
				else if(inp == 2) {
					currentPerson = null;
					break;
				}
				// 1. 도서 관리
				// 2. Logout (currentPerson = null; 후 break;)
			}
		}  else if (selectNum == 2) { // 회원 메뉴
			while(true) {
				printMemberMenu();
				System.out.print("<선택> ");
				int inp = scanner.nextInt();
				scanner.nextLine();
				if(inp == 1) {
					libManager.getmemberManage().signup();
					continue;
				}
				else if (inp == 2) {
					currentPerson = libManager.getmemberManage().Login();
					if(currentPerson == null) {
						System.out.println("없는 아이디이거나 비밀번호가 틀렸습니다.");
						continue;
					}
					libManager.MemberRun(currentPerson);
					continue;
				}
				else if (inp == 3) {
					System.out.println("로그인 해야 이용할 수 있습니다.");
					continue;
				}
				else if (inp == 4) {
				currentPerson = null;
				break;
				}
				else {
					continue;
				}
				// 1. 회원 가입
				// 2. 로그인
				// 3. 도서 대출(로그인 후 이용 가능 제약)
				// 4. Logout (currentPerson = null; 후 break;)
			}
		} else if (selectNum == 3) { // 종료
			System.out.println("프로그램을 종료합니다.");
			break;
		}

	}
	}
	public static void printMainMenu() {
		// (O)
		System.out.println("------전북대학교 컴퓨터 공학과 도서관 관리 프로그램입니다.---------");
		System.out.println("1. 관리자 메뉴 | 2. 회원 메뉴 | 3. 종료");
		System.out.println("------------------------------------------------------");
		// 메인 메뉴 작성
	}
	
	public static void printManagerMenu() {
		// (P)
		System.out.println("----------------관리자 모드----------------");
		System.out.println("1. 도서 관리 | 2. 로그아웃" );
		System.out.println("----------------------------------------");
		// 관리자 메뉴 작성
	}
	
	public static void printMemberMenu() {
		// (Q)
		System.out.println("--------------------------회원 모드--------------------------");
		System.out.println("1. 회원가입 | 2. 로그인 | 3. 도서 대출 | 4. 로그아웃" );
		System.out.println("-----------------------------------------------------------");
		// 회원 메뉴 작성
	}
}
