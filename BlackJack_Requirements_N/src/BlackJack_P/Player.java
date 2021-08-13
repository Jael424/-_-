package BlackJack_P;

import java.util.*;

public class Player {
	private Vector<Card> hand = new Vector<Card>();
	
	public void addCard(Card card) {
		hand.add(card);
		//(A)
		//카드를 추가한다.
	}
	public Card get(int item) {
		return hand.get(item);
		// (B)
		// item 번째 카드를 리턴한다.
	}
	public Vector<Card> getHand(){
		return hand;
		//(C)
		// 가지고 있는 패를 모두 리턴
		
	}
}
