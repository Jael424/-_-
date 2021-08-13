package BlackJack_P;

import java.util.*;

//����
public class BlackJack {
	
	public static int computeScore(ScoreTable table, Vector<Card> cards) {
		//public Vector<Card> getHand() �Լ� �̿�
		int score = 0;
		int A_Count = 0;
		
		for(int i = 0; i < cards.size(); i++)
		{
			int[] k;
			k = table.score(cards.get(i));
			if(cards.get(i).getRank().equals("A")) {
				if(score < 11) {
				A_Count++;
				score += k[1];
				}
				else {
					score += k[0];
				}
			}
			else {
				score += k[0];
			}
			while(score > 21 && A_Count > 0) {
				score -= 10;
				A_Count--;
			}
		}
		return score;
		// (H)
		// table �̿��� card ���� ���� ���� ����
	}
	
	public static boolean is_bust(ScoreTable table, Vector<Card> cards) {
		int score = computeScore(table, cards);
		if(score > 21) {
			return true;
		}
		else {
			return false;
		}
		// (I)
		// table �̿��� card ���� ���� ������ 21�� �ʰ��ϴ��� �ƴ��� ����
	}
	
	public static boolean checkBlackjack (Vector<Card> cards) {
		if(cards.get(0).getRank().equals("A")) {
			if(cards.get(1).getRank().equals("J") || cards.get(1).getRank().equals("Q") || cards.get(1).getRank().equals("K")) {
				return true;
			}
		}
		else if(cards.get(0).getRank().equals("J") || cards.get(0).getRank().equals("Q") || cards.get(0).getRank().equals("K")) {
			if(cards.get(1).getRank().equals("A")) {
				return true;
			}
		}
		else {
			return false;
		}
		// (J)
		// ī����� �޾� �������� �ƴ��� ����
		return false;
	}
	public static void sleep(int time) throws InterruptedException {
		try {
		Thread.sleep(1000 * time); 
		System.out.println();
		}
		catch(InterruptedException e) {
			}
		// (K)
		// time ��ŭ pause �� �簳. (����: �и�������)
	}
	
	public static void main(String[] args) throws InterruptedException {
		ScoreTable table = new ScoreTable();
		// ���� ���̺� ����
		
		Scanner sc = new Scanner(System.in);
		System.out.print("����� ���� ������ �Է����ּ��� >>");
		int dects_num = sc.nextInt();
		CardPool cardpool = new CardPool(dects_num);
		System.out.println(dects_num + "���� ��, " + 52*dects_num + "���� ī�带 ����մϴ�.");
		// �� ����
		sleep(1);
		Player user = new Player();
		Player dealer = new Player();
		
		user.addCard(cardpool.drawCard());
		user.addCard(cardpool.drawCard());
		
		dealer.addCard(cardpool.drawCard());
		dealer.addCard(cardpool.drawCard());
		// ������ ������ �� ����
		
		System.out.println("����� ī��� [" + user.get(0) + "," + user.get(1) + "] �Դϴ�." );
		sleep(1);
		System.out.println("������ ������ ī��� " + dealer.get(0) + " �Դϴ�.");
		sleep(1);
		if(checkBlackjack(user.getHand())) {
			System.out.println("����� BlackJack���� �¸��Ͽ���.");
		}
		else {
		while(true) {
			System.out.println("����� �����Դϴ�.");
			System.out.print("ī�带 �� �����ðڽ��ϱ� ? (hit/stand)");
			String userSelec = sc.next();
			if(userSelec.equals("stand")) {
				sleep(1);
				System.out.println("����� ���ʰ� �������ϴ�.");
				sleep(1);
				break;
			}
			else 
			{
				user.addCard(cardpool.drawCard());
				sleep(1);
				System.out.println(user.get(user.getHand().size() - 1) + "�� �޾ҽ��ϴ�.");
				System.out.print("����� ī��� [");
				for(int i = 0; i < user.getHand().size(); i++) {
					System.out.print(user.get(i));
					if(i < user.getHand().size() - 1) {
						System.out.print(", ");
					}
					else {
						System.out.println("] �Դϴ�.");
					}
				}
			}
			if(is_bust(table, user.getHand())) {
				sleep(1);
				System.out.println("���� ������ 21�� �ʰ��Ͽ� �й��Ͽ����ϴ�.");
				break;
			}
		}
		
		if(is_bust(table, user.getHand()) == false) {
			
			System.out.println("������ �����Դϴ�.");
			System.out.print("������ ī��� [");
			for(int i = 0; i < dealer.getHand().size(); i++) {
				System.out.print(dealer.get(i));
				if(i < dealer.getHand().size() - 1) {
					System.out.print(", ");
				}
				else {
					System.out.println("] �Դϴ�.");
				}
			}
			
			while(computeScore(table, dealer.getHand()) < 17) {
				if(checkBlackjack(dealer.getHand())) {
					break;
				}
				sleep(3);
				dealer.addCard(cardpool.drawCard());
				System.out.println(dealer.get(dealer.getHand().size() - 1) + "�� �޾ҽ��ϴ�.");
				System.out.print("������ ī��� [");
				for(int i = 0; i < dealer.getHand().size(); i++) {
					System.out.print(dealer.get(i));
					if(i < dealer.getHand().size() - 1) {
						System.out.print(", ");
					}
					else {
						System.out.println("] �Դϴ�.");
					}
				}
			}
			
			if(checkBlackjack(dealer.getHand())) {
				sleep(0);
				System.out.println("������ BlackJack���� �й��Ͽ����ϴ�.");
			}
			else if(is_bust(table, dealer.getHand())) {
				sleep(1);
				System.out.println("���� ���� ������ 21�� �ʰ��Ͽ� �¸��Ͽ����ϴ�.");
			}
			else {
				sleep(1);
				System.out.println("������ ���ʰ� �������ϴ�.");
				sleep(1);
				System.out.printf("����: %d vs ����: %d\n",computeScore(table, user.getHand()), computeScore(table, dealer.getHand()) );
				if(computeScore(table, user.getHand()) > computeScore(table, dealer.getHand())) {
					System.out.println("�¸��Ͽ����ϴ�.");
				}
				else if(computeScore(table, user.getHand()) < computeScore(table, dealer.getHand())) {
					System.out.println("�й��Ͽ����ϴ�.");
				}
				else {
					System.out.println("���º�");
				}
			}
		}
		}
		
	}
		
		
		// (L)
		// ���� ���� �κ�
}
