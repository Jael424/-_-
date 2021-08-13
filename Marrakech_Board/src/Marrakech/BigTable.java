package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;


public class BigTable extends JPanel{
	
	private SmallTable [][] table = new SmallTable[7][7];
	private static Assam assam = new Assam();
	
	public BigTable() {
		
		this.setSize(466,466);
		this.setLayout(null);
		this.setBackground(new Color(157,195,231));
		
		for(int i = 0; i < 7; i++) {

			for (int j = 0; j < 7; j++) {
				table[i][j] = new SmallTable(j, i);
				table[i][j].setLocation(j * 63  + 15, i * 63 + 15);
				table[i][j].setSize(58,58);
				table[i][j].setBackground(new Color(157,195,231));
				table[i][j].setLayout(null);
				this.add(table[i][j]);
				
			}
		}
		MoveLine moveLine = new MoveLine();
		moveLine.setBounds(0,0,466,466);
		moveLine.setBackground(new Color(157,195,231));
		this.add(moveLine);
		
		assam.setBounds(0,0,57,57);
		assam.setBackground(new Color(255,0,0,0));
		
		assam.setAssamLoc(3,3);
		getSmallTable(assam.getAssamX(), assam.getAssamY()).add(assam);
		
		// 마라케시 아쌈 초기 위치
	}
	
	public static Assam getAssam() {
		return assam;
	}
	
	public SmallTable getSmallTable(int x, int y) {
		return table[y][x];
	} // (x,y) 위치의 작은 테이블을 반환한다.
	
	class MoveLine extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(71,112,197));
			g.fillRoundRect(0, 0, 50, 50, 8, 8);
			g.setColor(new Color(157,195,231));
			g.fillRoundRect(8, 8, 33, 33, 3, 3);
				
			g.setColor(new Color(71,112,197));
			g.fillRoundRect(466 - 50, 466 - 50, 50, 50, 8, 8);
			g.setColor(new Color(157,195,231));
			g.fillRoundRect(466 - 50 + 8, 466 - 50 + 8, 33, 33, 3, 3);
				
			for (int i = 0; i < 3; i++) {
				g.setColor(new Color(71,112,197));
				g.fillRoundRect(0 + 105 + 126 * i, 0, 50 + 21, 50, 8, 8);
				g.setColor(new Color(157,195,231));
				g.fillRoundRect(8 + 105 + 126 * i, 8, 33 + 21, 33, 3, 3);
				g.fillRoundRect(8 + 105 + 126 * i, 8 + 20, 33 + 21, 33, 3, 3);
				
				g.setColor(new Color(71,112,197));
				g.fillRoundRect(466 - 50, 50 - 10 + 126 * i, 50 , 50 + 21, 8, 8);
				g.setColor(new Color(157,195,231));
				g.fillRoundRect(466 + 8 - 50 , 58 - 10 + 126 * i , 33 , 33 + 21, 3, 3);
				g.fillRoundRect(466 - 20 - 50 , 58 - 10 + 126 * i, 33 + 21, 33, 3, 3);
					
				g.setColor(new Color(71,112,197));
				g.fillRoundRect(0 + 41 + 126 * i, 466 - 50, 50 + 21, 50, 8, 8);
				g.setColor(new Color(157,195,231));
				g.fillRoundRect(8 + 41 + 126 * i, 466 - 8 - 33, 33 + 21, 33, 3, 3);
				g.fillRoundRect(8 + 41 + 126 * i, 466 - (8 + 20) - 33, 33 + 21, 33, 3, 3);
					
				g.setColor(new Color(71,112,197));
				g.fillRoundRect(0, 50 - 10 + 63 + 126 * i, 50 , 50 + 21, 8, 8);
				g.setColor(new Color(157,195,231));
				g.fillRoundRect(8, 58 - 10 + 63 + 126 * i , 33 , 33 + 21, 3, 3);
				g.fillRoundRect(20 , 58 - 10 + 63 + 126 * i, 33 + 21, 33, 3, 3);
					
			}
		}
	}
	
}
