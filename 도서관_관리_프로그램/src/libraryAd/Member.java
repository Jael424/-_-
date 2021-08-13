package libraryAd;

import java.util.*;

public class Member extends Person {
	private HashMap<String, Book> bookHash; //�뿩 ���� ������ �����ϴ� hash (String �� ���� ��ȣ ����.)
	
	public Member(String ID, String name, String passward) {
		// (B)
		super(ID, name, passward);
		this.bookHash = new HashMap<String, Book>();
		// ������(���� �ʿ�)
	}
	public HashMap<String, Book> getbookHash() { return bookHash; }
	
	void PrintRentalList() {
		// (C)
		Set<String> keys = bookHash.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			Book book = bookHash.get(it.next());
			System.out.printf("���� ��ȣ : %s, ���� �� : %s, ���� �帣 : %s\n", 
					book.getBookNumber(), book.getName(), book.getGenre());
		}
		// ���� ���� ������ ���
	}
}
