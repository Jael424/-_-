package libraryAd;

import java.util.*;

public class BookManage {
	private Scanner scanner;
	private HashMap<String, Book> bookHash;
	
	public BookManage() {
		this.scanner = new Scanner(System.in);
		this.bookHash = new HashMap<String, Book>();
	}
	
	void printBookList() {
		// (F)
		Iterator<String> bookInf = bookHash.keySet().iterator();
		while(bookInf.hasNext()) {
			Book book = bookHash.get(bookInf.next());
			System.out.printf("도서번호 : %s, 도서 명 : %s, 도서 장르 : %s, 재고 : %d\n",
					book.getBookNumber(), book.getName(), book.getGenre(), book.getStock());
		}
		// bookHash에 저장된 모든 도서 정보 출력
	}
	
	boolean AddBook() {
		// (G)
		System.out.print("도서 번호 : ");
		String book_number = scanner.nextLine();

		if(bookHash.containsKey(book_number)) {
			System.out.println("이미 존재하는 도서입니다.");
			return false;
		}
		
		System.out.print("도서 이름 : ");
		String name = scanner.nextLine();
		
		System.out.print("도서 장르 : ");
		String genre = scanner.nextLine();
		
		System.out.print("도서 수량 : ");
		int stock = scanner.nextInt();
		scanner.nextLine();
		while(stock < 0) {
			System.out.println("원하는 재고 수량이 0보다 작습니다, 0보다 큰 수로 재입력 해주십쇼.");
			System.out.print("도서 수량 : ");
			stock = scanner.nextInt();
			scanner.nextLine();
		}
		
		bookHash.put(book_number, new Book(book_number, name, genre, stock));
		return true;
		// scanner 객체를 통해 도서 정보를 입력 받아 bookHash에 저장
		// 현재 보유중인 도서가 아닌 도서만 추가 가능하도록 제약 필요
		// 원하는 재고 수량이 0보다 작으면 추가 불가능 제약 필요
	}
	
	boolean UpdateBookStock() {
		// (H)
		System.out.print("재고 추가 도서 번호 : ");
		String bookNumber = scanner.nextLine();
		if(bookHash.containsKey(bookNumber) == false) {
			System.out.println("없는 도서입니다.");
			return false;
		}
		System.out.print("추가하고자 하는 재고의 수량 : ");
		int bookStock = scanner.nextInt();
		scanner.nextLine();
		while(bookStock < 0) {
			System.out.println("추가하고자 하는 재고의 수량이 0보다 작아 갱신 불가능합니다.");
			System.out.print("추가하고자 하는 재고의 수량 : ");
			bookStock = scanner.nextInt();
			scanner.nextLine();
		}
		bookHash.get(bookNumber).updateStock(bookStock);
		System.out.printf("도서번호 : %s, 재고 : %d\n",
				bookHash.get(bookNumber).getBookNumber(), bookHash.get(bookNumber).getStock());
		return true;
		// scanner를 통해 도서 번호로 bookHash에 접근하여 재고 업데이트
		// 현재 보유중인 도서만 가능하도록 제약 필요
		// 추가하고자 하는 재고 수량이 0보다 작으면 갱신 불가능 제약 필요
	}
	
	boolean ReturnBook(Member member) {
		// (I)
		System.out.print("도서 번호 : ");
		String bookNumber = scanner.nextLine();
		if(member.getbookHash().containsKey(bookNumber) == false) {
			System.out.println("대여 중인 도서가 아닙니다.");
			return false;
		}
		bookHash.get(bookNumber).AddStock();
		member.getbookHash().remove(bookNumber);
		System.out.printf("[도서번호 : %s] + [도서명 : %s]이 정상적으로 반납되었습니다.\n", 
				bookNumber,  bookHash.get(bookNumber).getName());
		return true;
		// 도서 번호를 입력 받아 현재 회원이 대여중인 도서 반납
		// 현재 회원이 대여중인 도서만 반납 가능하도록 제약 필요
		// 대여하는 책의 수량은 1개만 가능
	}
	
	boolean RentalBook(Member member) {
		// (J)
		System.out.print("도서 번호 : ");
		String bookNumber = scanner.nextLine();
		if(member.getbookHash().containsKey(bookNumber)) {
			System.out.println("이미 대여된 책입니다.");
			return false;
		}
		if(bookHash.containsKey(bookNumber) == false) {
			System.out.println("책이 존재하지 않습니다.");
			return false;
		}
		if(bookHash.get(bookNumber).getStock() == 0){
			System.out.println("책의 재고가 0 입니다.");
			return false;
		}
		bookHash.get(bookNumber).SubstractStock();
		member.getbookHash().put(bookNumber, bookHash.get(bookNumber));
		System.out.printf("[도서번호 : %s] + [도서명 : %s]이 정상적으로 대여되었습니다.\n", 
				bookNumber, member.getbookHash().get(bookNumber).getName());
		return true;
		// 도서 번호를 입력 받아 현재 도서 대여
		// 도서가 존재하지 않거나 재고가 0개 이면 대여 불가능
		// 대여하는 책의 수량은 1개만 가능
	}
}
