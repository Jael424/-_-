package BlackJack_P;

import java.util.*;

public class Player {
	private Vector<Card> hand = new Vector<Card>();
	
	public void addCard(Card card) {
		hand.add(card);
		//(A)
		//ī�带 �߰��Ѵ�.
	}
	public Card get(int item) {
		return hand.get(item);
		// (B)
		// item ��° ī�带 �����Ѵ�.
	}
	public Vector<Card> getHand(){
		return hand;
		//(C)
		// ������ �ִ� �и� ��� ����
		
	}
}
