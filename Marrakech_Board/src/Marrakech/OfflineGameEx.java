package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.util.*;

public class OfflineGameEx extends Thread{
	
	private final int COMPUTER3 = 1;
	private final int COMPUTER2 = 2;
	private final int COMPUTER1 = 3;
	private final int GAMECREATE = 4;
	private final int GAMEPARTICIPATE = 5;
	private final int PREFERENCES = 6;
	private final int END = 7;
	
	private int[] dice = {1,2,2,3,3,4};
	private PlayScreen playScreen; 
	private GameTable gameTable;
	private PlayMenu playMenu;
	
	private Player player4 = null;
	private Player player3 = null;
	private Player player2 = null;
	private Player player1 = null;
	// ������ �÷��̾�
	
	private Player computerPlayer4;
	private Player computerPlayer3;
	private Player computerPlayer2;
	// ��ǻ�Ϳ� �÷��̾�

	private ArrayList<Player> personPlayerList = new ArrayList<Player>();
	private static ArrayList<Player> allPlayerList = new ArrayList<Player>();
	private ArrayList<Player> computerPlayerList = new ArrayList<Player>();
	private ArrayList<Player> loserPlayerList = new ArrayList<Player>();
	
	private Thread assamMove; 
	private Thread payCarpetSet;
	
	private boolean remainPlayer = true;
	
	private Player winPlayer;
	
	public OfflineGameEx(int computerNum,PlayScreen screen)  {
		playScreen = screen; // ������ �Է�
		if(computerNum == COMPUTER3) {
			player1 = new Player(4);
			player1.setPlayerName("player1");
			personPlayerList.add(player1);
			allPlayerList.add(player1);
			
			gameTable = new GameTable(4);
			
			computerPlayer2 = new Player(4);
			computerPlayer2.setPlayerName("computerPlayer2");
			computerPlayerList.add(computerPlayer2);
			allPlayerList.add(computerPlayer2);

			computerPlayer3 = new Player(4);
			computerPlayer3.setPlayerName("computerPlayer3");
			computerPlayerList.add(computerPlayer3);
			allPlayerList.add(computerPlayer3);

			computerPlayer4 = new Player(4);
			computerPlayer4.setPlayerName("computerPlayer4");
			computerPlayerList.add(computerPlayer4);
			allPlayerList.add(computerPlayer4);
			
		}
		else if(computerNum == COMPUTER2) {
			player1 = new Player(3);
			player1.setPlayerName("player1");
			personPlayerList.add(player1);
			allPlayerList.add(player1);
			
			gameTable = new GameTable(3);
			
			computerPlayer2 = new Player(3);
			computerPlayer2.setPlayerName("computerPlayer2");
			computerPlayerList.add(computerPlayer2);
			allPlayerList.add(computerPlayer2);
		
			computerPlayer3 = new Player(3);
			computerPlayer3.setPlayerName("computerPlayer3");
			computerPlayerList.add(computerPlayer3);
			allPlayerList.add(computerPlayer3);
			

		}
		else if(computerNum == COMPUTER1) {
			player1 = new Player(2);
			player1.setPlayerName("player1");
			personPlayerList.add(player1);
			allPlayerList.add(player1);
			
			gameTable = new GameTable(2);

			computerPlayer2 = new Player(2);
			computerPlayer2.setPlayerName("computerPlayer2");
			computerPlayerList.add(computerPlayer2);
			allPlayerList.add(computerPlayer2);
			

		}
	} // ��ǻ�� �÷��̾� ó��
	
	
	public static ArrayList<Player> getAllPlayerList(){
		return allPlayerList;
	}
	
	public void run() {
		playScreen.add(gameTable);
		playScreen.repaint();
		int playerOrder = 0;
		String diceLog;
		int diceNumber;

		while(remainPlayer) {
			
			gameTable.buttonInActive();
			playerOrder %= (personPlayerList.size() + computerPlayerList.size());
			
			
			gameTable.setLogLebel("�ֻ����� �������ϴ�.");
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int timeCheck = 0;
			
			while(true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				diceNumber = dice[(int)(Math.random()*6)];
				diceLog = "�ֻ��� ����: " + Integer.toString(diceNumber);
				gameTable.setDiceLebel(diceLog);
				if((timeCheck += 50) == 1000) {
					break;
				}
			}
			
			diceLog = "�ֻ��� ����: " + Integer.toString(diceNumber);
			gameTable.setDiceLebel(diceLog);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			// �Ϲ� ���� �÷���
			if(playerOrder < personPlayerList.size()) {
				
				gameTable.setCurPlayer(personPlayerList.get(playerOrder));
				
				if(loserPlayerList.contains(SmallTable.getCurPlayer()) == false) {
					
					gameTable.setLogLebel(Integer.toString(playerOrder + 1) + " �÷��̾� �����Դϴ�.");
					
					gameTable.setPlayerLog_Dir( "1 �ٸ��� : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 �ٸ��� : " + SmallTable.getCurPlayer().getDirham_5());
					
					gameTable.setPlayerLog_Car("���� ������ ��ź�� ���� : " + SmallTable.getCurPlayer().carpetAmount());
					
					gameTable.curentPlayerL(playerOrder);
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					gameTable.buttonActive();
					
					assamMove = new AssamMove(gameTable, diceNumber);
					
					
					while(gameTable.getIsInput() == false) { 
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					gameTable.buttonInActive();
					
					assamMove.start();
					
					try {
						assamMove.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					payCarpetSet = new PayCarpetSet(gameTable, false);
					payCarpetSet.start();
					
					try {
						payCarpetSet.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(SmallTable.getCurPlayer().getDirham_1() == 0 && SmallTable.getCurPlayer().getDirham_5() == 0) {
							loserPlayerList.add(personPlayerList.get(playerOrder));
					}
				}
				
			}
			// ��ǻ�� �÷���
			else {
				
				gameTable.setCurPlayer(computerPlayerList.get(playerOrder - personPlayerList.size()));
				
				if(loserPlayerList.contains(SmallTable.getCurPlayer()) == false) {
					
					gameTable.setLogLebel(Integer.toString(playerOrder + 1) + " �÷��̾� �����Դϴ�.");
					gameTable.setPlayerLog_Dir( "1 �ٸ��� : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 �ٸ��� : " + SmallTable.getCurPlayer().getDirham_5());
					gameTable.setPlayerLog_Car("���� ������ ��ź�� ���� : " + SmallTable.getCurPlayer().carpetAmount());
					gameTable.curentPlayerL(playerOrder);
		
					Thread ComputerPlayer = new ComputerPlayer(gameTable, 
							computerPlayerList.get(playerOrder - personPlayerList.size()),
							diceNumber); // ��ǻ�� �÷��̾� ������ ����
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					ComputerPlayer.start();
					
					try {
						ComputerPlayer.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(SmallTable.getCurPlayer().getDirham_1() == 0 && SmallTable.getCurPlayer().getDirham_5() == 0) {
						
							loserPlayerList.add(computerPlayerList.get(playerOrder - personPlayerList.size()));
					}
				}
			}
			gameTable.resetPlayerL(playerOrder);
				
			playerOrder++;
			
			if(loserPlayerList.size() == allPlayerList.size() - 1) {
				remainPlayer = false;
			}
			
			int notPlayerNumber = 0; // ��ź���� ������ ���� ���� �÷��̾� ���� ���� ���� �����ϴ� ����.
			
			for(int i = 0; i < allPlayerList.size(); i++) {
				if(allPlayerList.get(i).carpetAmount() == 0) {
					notPlayerNumber++;
				}
			}
			
			if(allPlayerList.size() == notPlayerNumber + loserPlayerList.size()) {
				break;
			}
			
		}
		// remainPlayer�� true�� ���� ������ ������� �� ���� �÷��̾ 1�� �̻��� ���.
		// false�� ���� 1 ���� �÷��̾ ���� ���.
		
		if(remainPlayer) {
			
			allPlayerList.removeAll(loserPlayerList);
			
			for(int i = 0; i < 7; i++) {
				for(int j = 0; j < 7; j++) {
					Player holdingPlayer = gameTable.getSmallTable(j, i).getHoldingPlayer();

					if(allPlayerList.contains(holdingPlayer)) {
						holdingPlayer.plusScore(1);
					}
					
				}
			}
			
			for(int j = 0; j < allPlayerList.size(); j++) {
				Player player =	allPlayerList.get(j);
				player.plusScore(player.getDirham_1());
				player.plusScore(player.getDirham_5() * 5);
			}
			
			Player winPlayer = allPlayerList.get(0);
			
			for(int k = 1; k < allPlayerList.size(); k++) {
				if(winPlayer.getScore() < allPlayerList.get(k).getScore()) {
					winPlayer = allPlayerList.get(k);
				}
			}
			
			gameTable.setPlayerLog_Dir("������ ����Ǿ����ϴ�.");
			gameTable.setPlayerLog_Car("�� " + winPlayer.getScore() + "������ �¸��Ͽ����ϴ�." );
			gameTable.setLogLebel(winPlayer.getPlayerName() + "�� �¸��Ͽ����ϴ�!");
			
		}
		else {
			
			allPlayerList.removeAll(loserPlayerList);
			
			gameTable.setLogLebel("�� ���� ������ ��� �÷��̾");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameTable.setLogLebel("Ż���Ͽ����ϴ�. ����, ");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(allPlayerList.contains(player1)) {
				gameTable.setLogLebel("player1�� �¸��Ͽ����ϴ�.");				
			}
			else if(allPlayerList.contains(player2)) {
				gameTable.setLogLebel("player2�� �¸��Ͽ����ϴ�.");				
			}
			else if(allPlayerList.contains(player3)) {
				gameTable.setLogLebel("player3�� �¸��Ͽ����ϴ�.");				
			}
			else if(allPlayerList.contains(player4)) {
				gameTable.setLogLebel("player4�� �¸��Ͽ����ϴ�.");				
			}
			else if(allPlayerList.contains(computerPlayer2)) {
				gameTable.setLogLebel("computerPlayer2�� �¸��Ͽ����ϴ�.");		
			}
			else if(allPlayerList.contains(computerPlayer3)) {
				gameTable.setLogLebel("computerPlayer3�� �¸��Ͽ����ϴ�.");		
			}
			else if(allPlayerList.contains(computerPlayer4)) {
				gameTable.setLogLebel("computerPlayer4�� �¸��Ͽ����ϴ�.");		
			}
			// �� �÷��̾ ������ ��� �÷��̾ ����Ͽ� ������ ����� ���.
			
		}
	}
}
