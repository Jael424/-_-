package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.util.*;

public class ComputerPlayer extends Thread {
	private final static int NORTH_DIRECT = 0;
	private final static int EAST_DIRECT = 1;
	private final static int SOUTH_DIRECT = 2;
	private final static int WEST_DIRECT = 3;
	
	private Player computerPlayer;
	private GameTable gameTable;
	private int diceNumber;
	
	private ArrayList<Integer> direct = new ArrayList<Integer>();
	
    public ComputerPlayer(GameTable gameTable, Player computerPlayer, int diceNumber) {
    	this.gameTable = gameTable;
    	this.computerPlayer= computerPlayer;
    	this.diceNumber = diceNumber;
    }
    
    public Player getComputerPlayer() {
    	return computerPlayer;
    }
    
    public void setDiceNumber(int diceNumber) {
    	this.diceNumber = diceNumber;
    	
    }
    
    public void run() {
    	
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	gameTable.setLogLebel("컴퓨터 플레이어 입니다.");
		
    	direct.clear();
    	direct.add(NORTH_DIRECT);
    	direct.add(EAST_DIRECT);
    	direct.add(SOUTH_DIRECT);
    	direct.add(WEST_DIRECT);
    	
    	try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	direct.remove((gameTable.getAssamDirect() + 2) % 4);
    	
    	int computerSelect = direct.get((int)(Math.random() * 2));
    	
    	gameTable.setAssamDirect(direct.get(computerSelect));
    	
    	gameTable.setLogLebel(diceNumber + " 만큼 이동합니다.");
    	
		Thread assamMove = new AssamMove(gameTable, diceNumber);
		
    	try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		gameTable.setIsInput(true);
		assamMove.start();
		gameTable.setIsInput(false);
		
		try {
			assamMove.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread payCarpetSet = new PayCarpetSet(gameTable, true);
		payCarpetSet.start();
		
		try {
			payCarpetSet.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
