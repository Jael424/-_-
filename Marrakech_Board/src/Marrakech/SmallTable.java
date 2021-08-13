package Marrakech;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SmallTable extends JPanel {
	
	private Color carpetColor = new Color(222,234,246);
	private Player holdingPlayer = new Player(1); // 현재 태이블을 양탄자로 장악한 플레이어를 나타냄,
	private boolean isPlayerHold = false; // 현재 다른 플레이어가 이 작은 테이블을 잡고있는지 확인하는 변수.
	
	private int horizontal = 0;  // 이 테이블의 x좌표
	private int vertical = 0; // 이 테이블의 y좌표
	
	
	private boolean inMouse = false; // 마우스가 해당 테이블에 올라왔는지 확인하는 변수
	private boolean holding = false; // 이 테이플에 양탄자를 설치할 수 있는지 알려주는 변수 true일 경우 설치 가능
	
	private static Player curPlayer = null; // 현재 게임을 진행 중인 플레이어를 알려주는 변수
	private static int currentX; // 현재 마우스가 위치한 x를 나타내는 변수
	private static int currentY; // 현재 마우스가 위치한 y를 나타내는 변수
	private static int selectX; // 플레이어가 이전에 선택했던 x를 나타내는 변수
	private static int selectY; // 플레이어가 이전에 선택했던 y를 나타내는 변수
	
	private static boolean inputWait = true; // 플레이어가 버튼을 클릭했는지 알려주는 변수 (클릭할 경우 false로 바뀜)

	private static Player checkPlayer = new Player(1);
	
	
	public SmallTable(int x, int y) {
		this.horizontal = x;
		this.vertical = y;
		addMouseListener(new InCheck());
	}
	
	
	public static void resetCheckPlayer() {
		checkPlayer = new Player(1);
	} // 체크하는 플레이어를 초기화하는 메서드
	
	
	public static void setSelectLoc(int x, int y) {
		selectX = x;
		selectY = y;
	}
	
	public static void setCurPlayer(Player player) {
		curPlayer = player;
	}
	
	public static void setInputWait(boolean inputWait) {
		SmallTable.inputWait = inputWait;
	} // 플레이어가 클릭했는지 알려주는 변수를 변경하는 메서드
	
	
	public static int getCurrentX() {
		return currentX;
	} // 현재 마우스가 위치한 x값을 반환
	
	public static int getSelectX() {
		return selectX;
	} 
	
	public static int getSelectY() {
		return selectY;
	}
	
	public static int getCurrentY() {
		return currentY;
	} // 현재 마우스가 위치한 y값을 반환
	
	
	public static boolean getInputWait() {
		return inputWait;
	} // 플레이어가 클릭했는지 알려주는 변수를 반환하는 메서드
	

	
	public static Player getCurPlayer() {
		return curPlayer;
	} // 현재 게임 진행 중인 플레이어를 알려주는 변수를 반환하는 메서드 

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(carpetColor);
		g.fillRoundRect(0, 0, 58, 58, 20, 20);

		if(inMouse == true && holding == true) {
			g.setColor(curPlayer.getColor());
			g.fillRoundRect(0, 0, 58, 58, 20, 20);
			g.setColor(carpetColor);
			g.fillRoundRect(3, 3, 52, 52, 18, 18);
		}

	} 
	
	public void setCurColor() {
		this.carpetColor = curPlayer.getColor();
		repaint();
	}
	
	public void resetColor() {
		this.carpetColor = holdingPlayer.getColor();
		repaint();
	}
	
	public void setHolding(boolean holding) {
		this.holding = holding;
	} // 홀딩하는 것을 허락할지 아닐지 설정하는 메소드
	
	public void setCarpet(Color carpetColor) {
		this.carpetColor = carpetColor;
	}
	
	public void setHoldPlayer(Player holdingPlayer) {
		if(holding) {
			this.holdingPlayer = holdingPlayer;
			this.carpetColor = holdingPlayer.getColor();
			GameTable.allResetHolding();
			isPlayerHold = true;
			repaint();
		}
	}// holdingPlayer 변경 및 해당 테이블 색 변경
	
	
	public boolean getHolding() {
		return holding;
	}
	
	public boolean getIsPlayerHold() {
		return isPlayerHold;
	} // 현재 다른 플레이어가 해당 작은 테이블을 차지했는지 아닌지 알려주는 변수를 반환하는 메서드
	
	
	
	public int getHorizon() {
		return this.horizontal;
	}
	
	public int getVert() {
		return this.vertical;
	}

	public Player getHoldingPlayer() {
		return holdingPlayer;
	}
	
	public Color getCarpet() {
		return carpetColor;
	}
	
	public class InCheck implements MouseListener {

		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			if(holding) {

				if(checkPlayer != holdingPlayer) {
					
					if(holdingPlayer != null) {
						
						checkPlayer = holdingPlayer;
					}
					
					setHoldPlayer(SmallTable.curPlayer);
					selectX = currentX;
					selectY = currentY;
					inputWait = false;
				}
			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			inMouse = true;
			currentX = horizontal;
			currentY = vertical;

			if(checkPlayer == holdingPlayer) {
				holding = false;
			}
			
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
			inMouse = false;
			repaint();
		}
	}
}
