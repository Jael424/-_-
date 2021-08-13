package libraryAd;

import java.util.*;
/*
통합 관리 클래스는 사용자, 관리자로부터 정보를 받아 
사용자 정보와 도서 정보를 통합 관리하는 클래스. 회원을 관리
하는 MemberManage 클래스와 BookManage 클래스의 객체를 멤버 변수로
가지며 관리자, 회원에 따른 별도의 기능(ManageRun, MemberRun)을 제공
한다. MemberManage 클래스와 BookManage 클래스는 각각 회원과 도서를
저장하는 해시를 가지며 멤버함수를 통해 이용자로부터 정보를 입력받아 정
보를 업데이트 한다.
*/
public class TotalLibraryManage {
	MemberManage memberManage;
	BookManage bookManage;
	
	private Scanner scanner;
	
	public TotalLibraryManage() {
		memberManage = new MemberManage();
		bookManage = new BookManage();
		scanner = new Scanner(System.in);
	}
	
	public MemberManage getmemberManage() { return memberManage; }
	
	public BookManage getbookManage() { return bookManage; }

	public void ManagerRun(Person person) { // 관리자
		// (D)
		while(true) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. 전체 도서 목록 출력 | 2. 도서 등록 | 3. 도서 재고 추가 | 4. 회원 목록 보기 | 5. 종료 ");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("입력 >> ");
			int inp = scanner.nextInt();
			scanner.nextLine();
			
			switch(inp) {
				case 1:
					bookManage.printBookList();
					break;
				case 2:
					bookManage.AddBook();
					break;
				case 3:
					bookManage.UpdateBookStock();
					break;
				case 4:
					memberManage.PrintMemberList();
					break;
				default:
					break;
			}
			if(inp == 5) {
				break;
			}
		}
		//루프를 돌면서 관리자 기능 수행
		// 1. 전체 도서 목록 출력
		// 2. 도서 등록
		// 3. 도서 재고 추가
		// 4. 회원 목록 보기
		// 5. 돌아가기(루프 탈출)

	}
	
	public void MemberRun(Person person) { // 회원
		// (E)
		Member member = (Member)person;
		while(true) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("1. 전체 도서 목록 출력 | 2. 도서 대여 | 3. 도서 반납 | 4. 대여 도서 목록 | 5. 종료 ");
			System.out.println("-------------------------------------------------------------");
			System.out.print("입력 >> ");
			int inp = scanner.nextInt();
			
			switch(inp) {
				case 1:
					bookManage.printBookList();
					break;
				case 2:
					bookManage.RentalBook(member);
					break;
				case 3:
					bookManage.ReturnBook(member);
					break;
				case 4:
					member.PrintRentalList();
					break;
				default:
					break;
			}
		if(inp == 5) {
			break;
			}
		}
		//루프를 돌면서 회원 기능 수행
		// 1. 전체 도서 목록 출력
		// 2. 도서 대여
		// 3. 도서 반납
		// 4. 대여 도서 목록
		// 5. 돌아가기(루프 탈출)
	}
}
