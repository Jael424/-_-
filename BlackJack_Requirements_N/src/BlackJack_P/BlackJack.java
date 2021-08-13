package BlackJack_P;

import java.util.*;

//메인
public class BlackJack {
	
	public static int computeScore(ScoreTable table, Vector<Card> cards) {
		//public Vector<Card> getHand() 함수 이용
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
		// table 이용해 card 들의 점수 총합 리턴
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
		// table 이용해 card 들의 점수 총합이 21을 초과하는지 아닌지 리턴
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
		// 카드들을 받아 블랙잭인지 아닌지 리턴
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
		// time 만큼 pause 후 재개. (단위: 밀리세컨드)
	}
	
	public static void main(String[] args) throws InterruptedException {
		ScoreTable table = new ScoreTable();
		// 점수 테이블 생성
		
		Scanner sc = new Scanner(System.in);
		System.out.print("사용할 덱의 개수를 입력해주세요 >>");
		int dects_num = sc.nextInt();
		CardPool cardpool = new CardPool(dects_num);
		System.out.println(dects_num + "개의 덱, " + 52*dects_num + "개의 카드를 사용합니다.");
		// 덱 생성
		sleep(1);
		Player user = new Player();
		Player dealer = new Player();
		
		user.addCard(cardpool.drawCard());
		user.addCard(cardpool.drawCard());
		
		dealer.addCard(cardpool.drawCard());
		dealer.addCard(cardpool.drawCard());
		// 유저와 딜러의 덱 생성
		
		System.out.println("당신의 카드는 [" + user.get(0) + "," + user.get(1) + "] 입니다." );
		sleep(1);
		System.out.println("딜러가 공개한 카드는 " + dealer.get(0) + " 입니다.");
		sleep(1);
		if(checkBlackjack(user.getHand())) {
			System.out.println("당신은 BlackJack으로 승리하였다.");
		}
		else {
		while(true) {
			System.out.println("당신의 차례입니다.");
			System.out.print("카드를 더 받으시겠습니까 ? (hit/stand)");
			String userSelec = sc.next();
			if(userSelec.equals("stand")) {
				sleep(1);
				System.out.println("당신의 차례가 끝났습니다.");
				sleep(1);
				break;
			}
			else 
			{
				user.addCard(cardpool.drawCard());
				sleep(1);
				System.out.println(user.get(user.getHand().size() - 1) + "를 받았습니다.");
				System.out.print("당신의 카드는 [");
				for(int i = 0; i < user.getHand().size(); i++) {
					System.out.print(user.get(i));
					if(i < user.getHand().size() - 1) {
						System.out.print(", ");
					}
					else {
						System.out.println("] 입니다.");
					}
				}
			}
			if(is_bust(table, user.getHand())) {
				sleep(1);
				System.out.println("패의 총합이 21을 초과하여 패배하였습니다.");
				break;
			}
		}
		
		if(is_bust(table, user.getHand()) == false) {
			
			System.out.println("딜러의 차례입니다.");
			System.out.print("딜러의 카드는 [");
			for(int i = 0; i < dealer.getHand().size(); i++) {
				System.out.print(dealer.get(i));
				if(i < dealer.getHand().size() - 1) {
					System.out.print(", ");
				}
				else {
					System.out.println("] 입니다.");
				}
			}
			
			while(computeScore(table, dealer.getHand()) < 17) {
				if(checkBlackjack(dealer.getHand())) {
					break;
				}
				sleep(3);
				dealer.addCard(cardpool.drawCard());
				System.out.println(dealer.get(dealer.getHand().size() - 1) + "를 받았습니다.");
				System.out.print("딜러의 카드는 [");
				for(int i = 0; i < dealer.getHand().size(); i++) {
					System.out.print(dealer.get(i));
					if(i < dealer.getHand().size() - 1) {
						System.out.print(", ");
					}
					else {
						System.out.println("] 입니다.");
					}
				}
			}
			
			if(checkBlackjack(dealer.getHand())) {
				sleep(0);
				System.out.println("딜러의 BlackJack으로 패배하였습니다.");
			}
			else if(is_bust(table, dealer.getHand())) {
				sleep(1);
				System.out.println("딜러 패의 총합이 21을 초과하여 승리하였습니다.");
			}
			else {
				sleep(1);
				System.out.println("딜러의 차례가 끝났습니다.");
				sleep(1);
				System.out.printf("유저: %d vs 딜러: %d\n",computeScore(table, user.getHand()), computeScore(table, dealer.getHand()) );
				if(computeScore(table, user.getHand()) > computeScore(table, dealer.getHand())) {
					System.out.println("승리하였습니다.");
				}
				else if(computeScore(table, user.getHand()) < computeScore(table, dealer.getHand())) {
					System.out.println("패배하였습니다.");
				}
				else {
					System.out.println("무승부");
				}
			}
		}
		}
		
	}
		
		
		// (L)
		// 메인 실행 부분
}
