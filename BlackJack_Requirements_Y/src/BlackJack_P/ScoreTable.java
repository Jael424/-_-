package BlackJack_P;

import java.util.*;

public class ScoreTable {
	private HashMap<String, int[]> table = new HashMap<>();

	ScoreTable(){
		for(int i = 1; i < 11; i++ ) {
			if(i > 1) 
			{
				int[] k = {i};
				if(i == 10) {
					table.put(Integer.toString(i), k);
					table.put("J", k);
					table.put("Q", k);
					table.put("K", k);

				}
				else {
					table.put(Integer.toString(i), k);
				}
			}
			else {
				int[] k = {1, 11};
				table.put("A", k);
			}
		}
		//(F)
		// key = 랭크 : value = 점수 쌍의 테이블을 생성
	}
	public int[] score(Card card) {
		return table.get(card.getRank());
		// (G)
		/*
	 	card 의 점수를 리턴, A의 경우 1 또는 11이며, 
		이 경우에 해당 점수들로 구성된 lenth가 2인 정수 배열이 리턴 
	    */
	}
	
}
