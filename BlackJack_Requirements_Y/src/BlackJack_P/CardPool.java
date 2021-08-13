package BlackJack_P;

import java.util.*;

public class CardPool {
	
	private Stack<Card> cards = new Stack<Card>();
	
	void cardPush(int j, String k) {
		switch(j) 
		{
		case 0:
			cards.push(new Card("Clubs", k));
			break;
		case 1:
			cards.push(new Card("Diamonds", k));
			break;
		case 2:
			cards.push(new Card("Hearts", k));
			break;
		case 3:
			cards.push(new Card("Spades", k));
			break;
		}
	}
	// 카드 삽입 함수
	
	void shuffle(int j) {
		Card c = cards.pop();
		cards.add(j, c);
	}
	// 카드를 섞는 함수

	CardPool(int num_dects){
		for (int i = 0; i < num_dects; i++ ) 
		{
			for(int j = 0; j < 4; j++) 
			{
				for(int k = 1; k < 14; k++) 
				{
					if(k > 1 && k < 11) 
					{
						cardPush(j,Integer.toString(k));
					}
					else {
						switch(k)
						{
							case 1:
								cardPush(j,"A");
								break;
							case 11:
								cardPush(j,"J");
								break;
							case 12:
								cardPush(j,"Q");
								break;
							case 13:
								cardPush(j,"K");
								break;
								
						}
					}
				}
			}
		}
			for (int p = 0; p < 156 * num_dects; p++) {
				int j = (int)(Math.random() * 52 * num_dects);
				shuffle(j);
			}
		// 덱 생성

		//(D)
		// num_decks 개의 덱을 생성하고 셔플 한 후 추가한다

	}
	public Card drawCard() {
		return cards.pop();
		// (E)
		//카드들로부터 카드를 한장 뽑는다.
	}
}
