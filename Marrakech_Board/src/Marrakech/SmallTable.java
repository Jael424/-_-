package Marrakech;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SmallTable extends JPanel {
	
	private Color carpetColor = new Color(222,234,246);
	private Player holdingPlayer = new Player(1); // ���� ���̺��� ��ź�ڷ� ����� �÷��̾ ��Ÿ��,
	private boolean isPlayerHold = false; // ���� �ٸ� �÷��̾ �� ���� ���̺��� ����ִ��� Ȯ���ϴ� ����.
	
	private int horizontal = 0;  // �� ���̺��� x��ǥ
	private int vertical = 0; // �� ���̺��� y��ǥ
	
	
	private boolean inMouse = false; // ���콺�� �ش� ���̺� �ö�Դ��� Ȯ���ϴ� ����
	private boolean holding = false; // �� �����ÿ� ��ź�ڸ� ��ġ�� �� �ִ��� �˷��ִ� ���� true�� ��� ��ġ ����
	
	private static Player curPlayer = null; // ���� ������ ���� ���� �÷��̾ �˷��ִ� ����
	private static int currentX; // ���� ���콺�� ��ġ�� x�� ��Ÿ���� ����
	private static int currentY; // ���� ���콺�� ��ġ�� y�� ��Ÿ���� ����
	private static int selectX; // �÷��̾ ������ �����ߴ� x�� ��Ÿ���� ����
	private static int selectY; // �÷��̾ ������ �����ߴ� y�� ��Ÿ���� ����
	
	private static boolean inputWait = true; // �÷��̾ ��ư�� Ŭ���ߴ��� �˷��ִ� ���� (Ŭ���� ��� false�� �ٲ�)

	private static Player checkPlayer = new Player(1);
	
	
	public SmallTable(int x, int y) {
		this.horizontal = x;
		this.vertical = y;
		addMouseListener(new InCheck());
	}
	
	
	public static void resetCheckPlayer() {
		checkPlayer = new Player(1);
	} // üũ�ϴ� �÷��̾ �ʱ�ȭ�ϴ� �޼���
	
	
	public static void setSelectLoc(int x, int y) {
		selectX = x;
		selectY = y;
	}
	
	public static void setCurPlayer(Player player) {
		curPlayer = player;
	}
	
	public static void setInputWait(boolean inputWait) {
		SmallTable.inputWait = inputWait;
	} // �÷��̾ Ŭ���ߴ��� �˷��ִ� ������ �����ϴ� �޼���
	
	
	public static int getCurrentX() {
		return currentX;
	} // ���� ���콺�� ��ġ�� x���� ��ȯ
	
	public static int getSelectX() {
		return selectX;
	} 
	
	public static int getSelectY() {
		return selectY;
	}
	
	public static int getCurrentY() {
		return currentY;
	} // ���� ���콺�� ��ġ�� y���� ��ȯ
	
	
	public static boolean getInputWait() {
		return inputWait;
	} // �÷��̾ Ŭ���ߴ��� �˷��ִ� ������ ��ȯ�ϴ� �޼���
	

	
	public static Player getCurPlayer() {
		return curPlayer;
	} // ���� ���� ���� ���� �÷��̾ �˷��ִ� ������ ��ȯ�ϴ� �޼��� 

	
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
	} // Ȧ���ϴ� ���� ������� �ƴ��� �����ϴ� �޼ҵ�
	
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
	}// holdingPlayer ���� �� �ش� ���̺� �� ����
	
	
	public boolean getHolding() {
		return holding;
	}
	
	public boolean getIsPlayerHold() {
		return isPlayerHold;
	} // ���� �ٸ� �÷��̾ �ش� ���� ���̺��� �����ߴ��� �ƴ��� �˷��ִ� ������ ��ȯ�ϴ� �޼���
	
	
	
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
