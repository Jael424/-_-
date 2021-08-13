package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class PlayerLabel extends JPanel{
	
	private EachPlayerLabel[] eachPlayerLabel;
	
	public PlayerLabel(int playerNum) {
		setSize(450,450);
		setLayout(null);
		
		eachPlayerLabel = new EachPlayerLabel[playerNum];
		for(int i = 0; i < playerNum; i++) {
			String player = "player ";
			player += Integer.toString(i+1);
			eachPlayerLabel[i] = new EachPlayerLabel(player,i+1);
			eachPlayerLabel[i].setLocation(20, 20 +72 * i);
			eachPlayerLabel[i].setSize(420,55);
			add(eachPlayerLabel[i]);
		}

	}
	
	
	public void selectPlayerLabel(int i) {
		eachPlayerLabel[i].setBackground(new Color(90,155,255));
	}
	
	public void resetPlayerLabel(int i) {
		eachPlayerLabel[i].setBackground(new Color(90,155,180));
	}
	
	public void settingColor(Color color, int playerLabel) {
		this.eachPlayerLabel[playerLabel].setBackground(color);
		repaint();
	}
	
	class EachPlayerLabel extends JLabel{
		private int playerNum;
		public EachPlayerLabel(String labelName, int playerNum) {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText(labelName);
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(new Color(90,155,180));
			setForeground(Color.white);
			setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
			setOpaque(true);
			this.playerNum = playerNum;
		}
	}
}
