package libraryAd;

import java.util.*;

public class Book {
	private String book_number;
	private String name;
	private String genre;
	private int stock;
	
	public Book(String book_number, String name, String genre, int stock) {
		this.book_number = book_number;
		this.name = name;
		this.genre = genre;
		this.stock = stock;
	}
	
	public String getBookNumber() { return book_number; }
	public String getName() { return name; }
	public String getGenre(){ return genre;}
	public int getStock(){return stock;}

	
		// 원하는 수량 만큼 재고(남은 책 개수) 증가
	void updateStock(int new_stock) { this.stock += new_stock; }
	
	void AddStock() { this.stock += 1; }
	void SubstractStock() { this.stock -= 1;}
}
