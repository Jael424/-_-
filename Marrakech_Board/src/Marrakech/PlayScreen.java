package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class PlayScreen extends JFrame {
	
	private GameTable table = new GameTable(4);
	private PlayMenu a = new PlayMenu();
	private JPanel panel = null;
	
	public PlayScreen(JPanel panel) {
		
		setTitle("�����ɽ� �������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,560 + 200);
		setLayout(null);
		setVisible(true);
		add(panel);
		
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
}
