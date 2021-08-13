package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.util.*;

public class PayCarpetSet extends Thread {
	
	private final int NORTH_DIRECT = 0;
	private final int EAST_DIRECT = 1;
	private final int SOUTH_DIRECT = 2;
	private final int WEST_DIRECT = 3;
	
	private GameTable gameTable;
	private ArrayList<SmallTable> holdingCarpet = new ArrayList<SmallTable>();
	private Player holdingPlayer = null;
	private boolean isComputer = false;
	private ArrayList<Player> allPlayerList = OfflineGameEx.getAllPlayerList();
	
	public PayCarpetSet(GameTable gameTable, boolean isComputer) {
		this.gameTable = gameTable;
		this.isComputer = isComputer;
	}
	
	
	public void holdingCarpet(int x, int y, int num) {
		
		if(num == 0) {
			return;
		}

		if(gameTable.getSmallTable(x, y).getIsPlayerHold()) {
			
			if(x < 6 && holdingPlayer == gameTable.getSmallTable(x + 1, y).getHoldingPlayer()) {
				if(holdingCarpet.contains(gameTable.getSmallTable(x + 1, y)) == false ) {
					holdingCarpet.add(gameTable.getSmallTable(x + 1, y));
				
					holdingCarpet(x + 1, y, num - 1);
				}
			}
			
			if(x > 0 && holdingPlayer == gameTable.getSmallTable(x - 1, y).getHoldingPlayer()) {
				if(holdingCarpet.contains(gameTable.getSmallTable(x - 1, y)) == false ) {
					holdingCarpet.add(gameTable.getSmallTable(x - 1, y));

					holdingCarpet(x - 1, y, num - 1);
				}
			}
			
			if(y < 6 && holdingPlayer == gameTable.getSmallTable(x, y + 1).getHoldingPlayer()) {
				if(holdingCarpet.contains(gameTable.getSmallTable(x, y + 1)) == false ) {
					holdingCarpet.add(gameTable.getSmallTable(x, y + 1));
					
					holdingCarpet(x, y + 1, num - 1);
				}
			}
			
			if(y > 0 && holdingPlayer == gameTable.getSmallTable(x, y - 1).getHoldingPlayer()) {
				if(holdingCarpet.contains(gameTable.getSmallTable(x, y - 1)) == false) {
					holdingCarpet.add(gameTable.getSmallTable(x, y - 1));
				
					holdingCarpet(x, y - 1, num - 1);
				}
			}
		}
			
	}
	
	
	@Override
	public void run() {
		
		SmallTable.resetCheckPlayer();
		
		if((SmallTable.getCurPlayer() != (holdingPlayer = gameTable.getSmallTable(gameTable.getAssamX(), gameTable.getAssamY()).getHoldingPlayer()))  && gameTable.getSmallTable(gameTable.getAssamX(), gameTable.getAssamY()).getIsPlayerHold()) {
			
			SmallTable.resetCheckPlayer();
			
			holdingCarpet.add(gameTable.getSmallTable(gameTable.getAssamX(), gameTable.getAssamY()));
			
			holdingCarpet(gameTable.getAssamX(), gameTable.getAssamY(), 49);
			
			gameTable.setLogLebel("상대방의 양탄자를 밟으셨네요.");
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameTable.setLogLebel("그럼 돈을 내셔야죠.");
			
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameTable.setLogLebel("총 지불할 돈은 " +  holdingCarpet.size() + " 입니다." );
			
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int price = holdingCarpet.size();
			
			while(price > 0 && (SmallTable.getCurPlayer().getDirham_5() > 0 || SmallTable.getCurPlayer().getDirham_1() > 0)) {
				if(price >= 5 && SmallTable.getCurPlayer().getDirham_5() > 0) {
					SmallTable.getCurPlayer().minDirham(0, 1);
					holdingPlayer.plusDirham(0, 1);
					price -= 5;
					gameTable.setPlayerLog_Dir( "1 다르함 : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 다르함 : " + SmallTable.getCurPlayer().getDirham_5());
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					while(price > 0 && SmallTable.getCurPlayer().getDirham_1() > 0) {
						SmallTable.getCurPlayer().minDirham(1,0);
						holdingPlayer.plusDirham(1, 0);
						price--;
						gameTable.setPlayerLog_Dir( "1 다르함 : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 다르함 : " + SmallTable.getCurPlayer().getDirham_5());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if(price > 0 && SmallTable.getCurPlayer().getDirham_5() > 0 && SmallTable.getCurPlayer().getDirham_1() == 0) {
						boolean isExchange = true;
						for(int i = 0; i < allPlayerList.size(); i++) {
							if(allPlayerList.get(i).getDirham_1() > 4) {
								i++;
								allPlayerList.get(i).plusDirham(0, 1);
								SmallTable.getCurPlayer().minDirham(0, 1);
								
								allPlayerList.get(i).minDirham(5, 0);
								SmallTable.getCurPlayer().plusDirham(5, 0);
								
								isExchange = false;
								
								break;
							}
							
							gameTable.setPlayerLog_Dir( "1 다르함 : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 다르함 : " + SmallTable.getCurPlayer().getDirham_5());

							if(isExchange) {
							gameTable.setLogLebel("동전 교환 불가능, 게임 진행 불가");
						}

					} // 5원은 가지고 있으나, 1원은 가지고 있지 않은 상황에서 전체 플레이어 중에서 5원을 주고 1원으로 5원을 받음
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
			gameTable.setPlayerLog_Dir( "1 다르함 : " + SmallTable.getCurPlayer().getDirham_1() + "  ||  5 다르함 : " + SmallTable.getCurPlayer().getDirham_5());
			
			if((SmallTable.getCurPlayer().getDirham_5() == 0) && (SmallTable.getCurPlayer().getDirham_1() == 0)) {
				gameTable.setPlayerLog_Dir("당신의 다르함이 존재하지 않습니다.");
				return;
			}

			
		}
		
		gameTable.setLogLebel("양탄자를 설치하십쇼.");

		if(isComputer == false) {
			SmallTable.resetCheckPlayer();
			
			if(gameTable.getAssamX() < 6) {
				gameTable.setHolding(gameTable.getAssamX() + 1, gameTable.getAssamY(), true);
				
			}
			
			if(gameTable.getAssamX() > 0) {
				gameTable.setHolding(gameTable.getAssamX() - 1, gameTable.getAssamY(), true);
				
			}
			
			if(gameTable.getAssamY() < 6) {
				gameTable.setHolding(gameTable.getAssamX(), gameTable.getAssamY() + 1, true);
				
			}
			
			if(gameTable.getAssamY() > 0) {
				gameTable.setHolding(gameTable.getAssamX(), gameTable.getAssamY() - 1, true);
				
			}
			
			
			while(SmallTable.getInputWait()) { 
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(SmallTable.getSelectX() < 6) {
				gameTable.setHolding(SmallTable.getSelectX() + 1, SmallTable.getSelectY(), true);
			
			}
			
			if(SmallTable.getSelectX() > 0) {
				gameTable.setHolding(SmallTable.getSelectX() - 1, SmallTable.getSelectY(), true);
				
			}
			
			if(SmallTable.getSelectY() < 6) {
				gameTable.setHolding(SmallTable.getSelectX(), SmallTable.getSelectY() + 1, true);
				
			}
			
			if(SmallTable.getSelectY() > 0) {
				gameTable.setHolding(SmallTable.getSelectX(), SmallTable.getSelectY() - 1, true);
				
			}
			
			gameTable.setHolding(gameTable.getAssamX(), gameTable.getAssamY(), false);
			
			
			SmallTable.setInputWait(true);
			
			while(SmallTable.getInputWait()) {	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			SmallTable.getCurPlayer().carpetUse();
			gameTable.setPlayerLog_Car("현재 소유한 양탄자 개수 : " + SmallTable.getCurPlayer().carpetAmount());
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SmallTable.setInputWait(true);
		}
		else {
			
			SmallTable.resetCheckPlayer();
			
			ArrayList<Integer> comDirectList = new ArrayList<Integer>();
			comDirectList.add(NORTH_DIRECT);
			comDirectList.add(EAST_DIRECT);
			comDirectList.add(SOUTH_DIRECT);
			comDirectList.add(WEST_DIRECT);

			
			if(gameTable.getAssamY() == 0) {
				comDirectList.remove(((Integer)NORTH_DIRECT));
			}
			if(gameTable.getAssamX() == 6) {
				comDirectList.remove(((Integer)EAST_DIRECT));
			}
			if(gameTable.getAssamY() == 6) {
				comDirectList.remove(((Integer)SOUTH_DIRECT));
			}
			if(gameTable.getAssamX() == 0) {
				comDirectList.remove(((Integer)WEST_DIRECT));
			} 
			
			int comDirect = (int)(Math.random() * (comDirectList.size()));
			int changeX = -1, changeY = -1;
			
			
			if(comDirectList.get((int)comDirect) == 0) {
				SmallTable changeSmallTable = gameTable.getSmallTable(gameTable.getAssamX(), gameTable.getAssamY() - 1);
				
				if(changeSmallTable.getHoldingPlayer() != null) {
					holdingPlayer = changeSmallTable.getHoldingPlayer();
				}
				
				changeSmallTable.setHolding(true);
				changeSmallTable.setHoldPlayer(SmallTable.getCurPlayer());
				changeSmallTable.setHolding(false);
				
				changeX = gameTable.getAssamX(); changeY = gameTable.getAssamY() - 1;
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(comDirectList.get((int)comDirect) == 1) {
				SmallTable changeSmallTable = gameTable.getSmallTable(gameTable.getAssamX() + 1, gameTable.getAssamY());

				if(changeSmallTable.getHoldingPlayer() != null) {
					holdingPlayer = changeSmallTable.getHoldingPlayer();
				}
				
				changeSmallTable.setHolding(true);
				changeSmallTable.setHoldPlayer(SmallTable.getCurPlayer());
				changeSmallTable.setHolding(false);
				

				changeX = gameTable.getAssamX() + 1; changeY = gameTable.getAssamY();
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(comDirectList.get((int)comDirect) == 2) {
				SmallTable changeSmallTable = gameTable.getSmallTable(gameTable.getAssamX(), gameTable.getAssamY() + 1);
				
				if(changeSmallTable.getHoldingPlayer() != null) {
					holdingPlayer = changeSmallTable.getHoldingPlayer();
				}
				
				changeSmallTable.setHolding(true);
				changeSmallTable.setHoldPlayer(SmallTable.getCurPlayer());
				changeSmallTable.setHolding(false);
				
				changeX = gameTable.getAssamX(); changeY = gameTable.getAssamY() + 1;
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(comDirectList.get((int)comDirect) == 3) {
				SmallTable changeSmallTable = gameTable.getSmallTable(gameTable.getAssamX() - 1, gameTable.getAssamY());
				
				if(changeSmallTable.getHoldingPlayer() != null) {
					holdingPlayer = changeSmallTable.getHoldingPlayer();
				}
				
				changeSmallTable.setHolding(true);
				changeSmallTable.setHoldPlayer(SmallTable.getCurPlayer());
				changeSmallTable.setHolding(false);
				
				changeX = gameTable.getAssamX() - 1; changeY = gameTable.getAssamY();
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
				
			comDirectList.clear();
			
			comDirectList.add(NORTH_DIRECT);
			comDirectList.add(EAST_DIRECT);
			comDirectList.add(SOUTH_DIRECT);
			comDirectList.add(WEST_DIRECT);
			// 0 == 북쪽, 1 == 동쪽, 2 == 남쪽, 3 == 서쪽.
			
			
			if(changeY == 0) {
				comDirectList.remove((Integer)NORTH_DIRECT);
			}
			if(changeX == 6) {
				comDirectList.remove((Integer)EAST_DIRECT);
			}
			if(changeY == 6) {
				comDirectList.remove((Integer)SOUTH_DIRECT);
			}
			if(changeX == 0) {
				comDirectList.remove((Integer)WEST_DIRECT);
			} 
			
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(true) {
				comDirect = (int)(Math.random() * (comDirectList.size()));
				if(comDirectList.get(comDirect) == 0) {
					if((holdingPlayer != null) && holdingPlayer == gameTable.getSmallTable(changeX, changeY - 1).getHoldingPlayer()) {
						continue;
					}
					else {
						
						if((changeX == gameTable.getAssamX()) && (changeY - 1 == gameTable.getAssamY())) {
							continue;
						}
						
						gameTable.getSmallTable(changeX, changeY - 1).setHolding(true);
						gameTable.getSmallTable(changeX, changeY - 1).setHoldPlayer(SmallTable.getCurPlayer());
						gameTable.getSmallTable(changeX, changeY - 1).setHolding(false);
						break;
					}
				}
				else if(comDirectList.get(comDirect) == 1) {
					if((holdingPlayer != null) && holdingPlayer == gameTable.getSmallTable(changeX + 1, changeY).getHoldingPlayer()) {
						continue;
					}
					else {
						
						if((changeX + 1 == gameTable.getAssamX()) && (changeY == gameTable.getAssamY())) {
							continue;
						}
						
						gameTable.getSmallTable(changeX + 1, changeY).setHolding(true);
						gameTable.getSmallTable(changeX + 1, changeY).setHoldPlayer(SmallTable.getCurPlayer());
						gameTable.getSmallTable(changeX + 1, changeY).setHolding(false);
						break;
					}
				}
				else if(comDirectList.get(comDirect) == 2) {
					if((holdingPlayer != null) && holdingPlayer == gameTable.getSmallTable(changeX, changeY + 1).getHoldingPlayer()) {
						continue;
					}
					else {
						
						if((changeX == gameTable.getAssamX()) && (changeY + 1 == gameTable.getAssamY())) {
							continue;
						}
						
						gameTable.getSmallTable(changeX, changeY + 1).setHolding(true);
						gameTable.getSmallTable(changeX, changeY + 1).setHoldPlayer(SmallTable.getCurPlayer());
						gameTable.getSmallTable(changeX, changeY + 1).setHolding(false);
						break;
					}
				}
				else if(comDirectList.get(comDirect) == 3) {
					if((holdingPlayer != null) && holdingPlayer == gameTable.getSmallTable(changeX - 1, changeY).getHoldingPlayer()) {
						continue;
					}
					else {
						
						if((changeX - 1 == gameTable.getAssamX()) && (changeY == gameTable.getAssamY())) {
							continue;
						}
						
						gameTable.getSmallTable(changeX - 1, changeY ).setHolding(true);
						gameTable.getSmallTable(changeX - 1, changeY ).setHoldPlayer(SmallTable.getCurPlayer());
						gameTable.getSmallTable(changeX - 1, changeY ).setHolding(false);
						break;
					}
				} 
			}
			
			SmallTable.getCurPlayer().carpetUse();
			gameTable.setPlayerLog_Car("현재 소유한 양탄자 개수 : " + SmallTable.getCurPlayer().carpetAmount());
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}

	}
}
