package Marrakech;

import java.awt.*;

import javax.swing.*;

public class Player {

	private int dirham_5 = 20, dirham_1 = 20;
	private int carpet = 0;
	private int score = 0;
	private Color playerColor;
	private String playerName;
	
	
	public Player(int playerNumber) {
		playerColor = new Color((int)(Math.random()*255) + 1,(int)(Math.random()*255) + 1,(int)(Math.random()*255) + 1 );
		
		switch(playerNumber) {
		case 4:
			carpet = 12;
			break;
		case 3:
			carpet = 15;
			break;
		case 2:
			carpet = 24;
			break;
		}

	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	} // �÷��̾��� �̸��� �����ϴ� �޼���
	
	public void plusDirham(int num_1, int num_5) {
		this.dirham_1 += num_1;
		this.dirham_5 += num_5;
	} // �ٸ����� ���ϴ� ����
	
	public void minDirham(int num_1, int num_5) {
		this.dirham_1 -= num_1;
		this.dirham_5 -= num_5;
	} // �ٸ����� ���� ����
	
	public void carpetUse() {
		this.carpet -= 1;
	}
	
	public void plusScore(int score) {
		this.score += score;
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public int getDirham_5() {
		return dirham_5;
	} // �ٸ��� 5�� ������ ��ȯ�ϴ� �޼ҵ�
	
	public int getDirham_1() {
		return dirham_1;
	} // �ٸ��� 1�� ������ ��ȯ�ϴ� �޼ҵ�
	
	public int carpetAmount() {
		return this.carpet;
	}
	
	
	public String getPlayerName() {
		return playerName;
	} // �÷��̾��� �̸��� ��ȯ�ϴ� �޼ҵ�
	
	public Color getColor() {
		return this.playerColor;
	}
}
