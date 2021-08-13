package libraryAd;

import java.util.*;

public class Member extends Person {
	private HashMap<String, Book> bookHash; //대여 중인 도서를 저장하는 hash (String 은 도서 번호 저장.)
	
	public Member(String ID, String name, String passward) {
		// (B)
		super(ID, name, passward);
		this.bookHash = new HashMap<String, Book>();
		// 생성자(구현 필요)
	}
	public HashMap<String, Book> getbookHash() { return bookHash; }
	
	void PrintRentalList() {
		// (C)
		Set<String> keys = bookHash.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			Book book = bookHash.get(it.next());
			System.out.printf("도서 번호 : %s, 도서 명 : %s, 도서 장르 : %s\n", 
					book.getBookNumber(), book.getName(), book.getGenre());
		}
		// 대출 중인 도서를 출력
	}
}
