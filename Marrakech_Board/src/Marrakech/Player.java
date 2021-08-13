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
	} // 플레이어의 이름을 설정하는 메서드
	
	public void plusDirham(int num_1, int num_5) {
		this.dirham_1 += num_1;
		this.dirham_5 += num_5;
	} // 다르함을 더하는 변수
	
	public void minDirham(int num_1, int num_5) {
		this.dirham_1 -= num_1;
		this.dirham_5 -= num_5;
	} // 다르함을 빼는 변수
	
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
	} // 다르함 5의 개수를 반환하는 메소드
	
	public int getDirham_1() {
		return dirham_1;
	} // 다르함 1의 개수를 반환하는 메소드
	
	public int carpetAmount() {
		return this.carpet;
	}
	
	
	public String getPlayerName() {
		return playerName;
	} // 플레이어의 이름을 반환하는 메소드
	
	public Color getColor() {
		return this.playerColor;
	}
}
