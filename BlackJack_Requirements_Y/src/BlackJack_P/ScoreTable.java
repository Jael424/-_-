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
		// key = ��ũ : value = ���� ���� ���̺��� ����
	}
	public int[] score(Card card) {
		return table.get(card.getRank());
		// (G)
		/*
	 	card �� ������ ����, A�� ��� 1 �Ǵ� 11�̸�, 
		�� ��쿡 �ش� ������� ������ lenth�� 2�� ���� �迭�� ���� 
	    */
	}
	
}
