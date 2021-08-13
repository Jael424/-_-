package Marrakech;

import java.awt.*;
import java.awt.*;

import javax.swing.*;

public class Assam extends JPanel {
	final static int NORTH_DIRECT = 0;
	final static int EAST_DIRECT = 1;
	final static int SOUTH_DIRECT = 2;
	final static int WEST_DIRECT = 3;
	
	private int currentDirect= EAST_DIRECT;
	private int currentX = 3;
	private int currentY = 3;
	
	int []x = {0 + 23 + 7, -5 + 23 + 7 - 4, 5 + 23 + 7 + 4};
	int []y = {0 + 10 + 7 - 5, 20 + 10 + 7 + 10 - 5, 20 + 10 + 7 + 10 - 5};
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(65,81,104));
		g.fillOval(23, 10, 14, 14);
		g.fillPolygon(x, y, 3);
		g.fillRect(-5 + 23 + 7 - 4 - 3, 20 + 10 + 7 + 10 - 2 -5, 25, 7);
		
	}
	
	
	public void setAssamLoc(int x, int y) {
		this.currentX = x;
		this.currentY = y;
	}
	
	public void addX() {
		this.currentX++;
	}
	
	public void addY() {
		this.currentY++;
	}
	
	public void setDirect(int currentDirect) {
		this.currentDirect = currentDirect;
	}
	
	
	public int getDirect() {
		return currentDirect;
	}
	
	public int getAssamX() {
		return currentX;
	}
	
	public int getAssamY() {
		return currentY;
	}
	
}
