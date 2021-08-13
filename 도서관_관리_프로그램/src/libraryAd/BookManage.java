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
			System.out.printf("������ȣ : %s, ���� �� : %s, ���� �帣 : %s, ��� : %d\n",
					book.getBookNumber(), book.getName(), book.getGenre(), book.getStock());
		}
		// bookHash�� ����� ��� ���� ���� ���
	}
	
	boolean AddBook() {
		// (G)
		System.out.print("���� ��ȣ : ");
		String book_number = scanner.nextLine();

		if(bookHash.containsKey(book_number)) {
			System.out.println("�̹� �����ϴ� �����Դϴ�.");
			return false;
		}
		
		System.out.print("���� �̸� : ");
		String name = scanner.nextLine();
		
		System.out.print("���� �帣 : ");
		String genre = scanner.nextLine();
		
		System.out.print("���� ���� : ");
		int stock = scanner.nextInt();
		scanner.nextLine();
		while(stock < 0) {
			System.out.println("���ϴ� ��� ������ 0���� �۽��ϴ�, 0���� ū ���� ���Է� ���ֽʼ�.");
			System.out.print("���� ���� : ");
			stock = scanner.nextInt();
			scanner.nextLine();
		}
		
		bookHash.put(book_number, new Book(book_number, name, genre, stock));
		return true;
		// scanner ��ü�� ���� ���� ������ �Է� �޾� bookHash�� ����
		// ���� �������� ������ �ƴ� ������ �߰� �����ϵ��� ���� �ʿ�
		// ���ϴ� ��� ������ 0���� ������ �߰� �Ұ��� ���� �ʿ�
	}
	
	boolean UpdateBookStock() {
		// (H)
		System.out.print("��� �߰� ���� ��ȣ : ");
		String bookNumber = scanner.nextLine();
		if(bookHash.containsKey(bookNumber) == false) {
			System.out.println("���� �����Դϴ�.");
			return false;
		}
		System.out.print("�߰��ϰ��� �ϴ� ����� ���� : ");
		int bookStock = scanner.nextInt();
		scanner.nextLine();
		while(bookStock < 0) {
			System.out.println("�߰��ϰ��� �ϴ� ����� ������ 0���� �۾� ���� �Ұ����մϴ�.");
			System.out.print("�߰��ϰ��� �ϴ� ����� ���� : ");
			bookStock = scanner.nextInt();
			scanner.nextLine();
		}
		bookHash.get(bookNumber).updateStock(bookStock);
		System.out.printf("������ȣ : %s, ��� : %d\n",
				bookHash.get(bookNumber).getBookNumber(), bookHash.get(bookNumber).getStock());
		return true;
		// scanner�� ���� ���� ��ȣ�� bookHash�� �����Ͽ� ��� ������Ʈ
		// ���� �������� ������ �����ϵ��� ���� �ʿ�
		// �߰��ϰ��� �ϴ� ��� ������ 0���� ������ ���� �Ұ��� ���� �ʿ�
	}
	
	boolean ReturnBook(Member member) {
		// (I)
		System.out.print("���� ��ȣ : ");
		String bookNumber = scanner.nextLine();
		if(member.getbookHash().containsKey(bookNumber) == false) {
			System.out.println("�뿩 ���� ������ �ƴմϴ�.");
			return false;
		}
		bookHash.get(bookNumber).AddStock();
		member.getbookHash().remove(bookNumber);
		System.out.printf("[������ȣ : %s] + [������ : %s]�� ���������� �ݳ��Ǿ����ϴ�.\n", 
				bookNumber,  bookHash.get(bookNumber).getName());
		return true;
		// ���� ��ȣ�� �Է� �޾� ���� ȸ���� �뿩���� ���� �ݳ�
		// ���� ȸ���� �뿩���� ������ �ݳ� �����ϵ��� ���� �ʿ�
		// �뿩�ϴ� å�� ������ 1���� ����
	}
	
	boolean RentalBook(Member member) {
		// (J)
		System.out.print("���� ��ȣ : ");
		String bookNumber = scanner.nextLine();
		if(member.getbookHash().containsKey(bookNumber)) {
			System.out.println("�̹� �뿩�� å�Դϴ�.");
			return false;
		}
		if(bookHash.containsKey(bookNumber) == false) {
			System.out.println("å�� �������� �ʽ��ϴ�.");
			return false;
		}
		if(bookHash.get(bookNumber).getStock() == 0){
			System.out.println("å�� ��� 0 �Դϴ�.");
			return false;
		}
		bookHash.get(bookNumber).SubstractStock();
		member.getbookHash().put(bookNumber, bookHash.get(bookNumber));
		System.out.printf("[������ȣ : %s] + [������ : %s]�� ���������� �뿩�Ǿ����ϴ�.\n", 
				bookNumber, member.getbookHash().get(bookNumber).getName());
		return true;
		// ���� ��ȣ�� �Է� �޾� ���� ���� �뿩
		// ������ �������� �ʰų� ��� 0�� �̸� �뿩 �Ұ���
		// �뿩�ϴ� å�� ������ 1���� ����
	}
}
