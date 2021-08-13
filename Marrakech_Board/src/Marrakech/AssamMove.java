package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.util.*;

public class AssamMove extends Thread{
	
	final static int NORTH_DIRECT = 0;
	final static int EAST_DIRECT = 1;
	final static int SOUTH_DIRECT = 2;
	final static int WEST_DIRECT = 3;
	
	private GameTable gameTable = null;
	private int diceNum;
	
	public AssamMove(GameTable gameTable,int diceNum) {
		this.gameTable = gameTable;
		this.diceNum = diceNum;
	}
	
	public void setDiceNum(int diceNum) {
		this.diceNum = diceNum;
	}
	
	@Override
	public void run() {
		
		gameTable.buttonInActive();
		
		for(int i = 0; i < diceNum; i++) {
			if(gameTable.getAssamDirect() == NORTH_DIRECT) {
				
				if(gameTable.getAssamY() == 0 && (gameTable.getAssamX() % 2) == 1) {
					gameTable.setAssamLocation(gameTable.getAssamX() + 1, gameTable.getAssamY());
					gameTable.setAssamDirect(SOUTH_DIRECT);
				}
				else if(gameTable.getAssamY() == 0 && gameTable.getAssamX() != 0 && (gameTable.getAssamX() % 2) == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX() - 1, gameTable.getAssamY());
					gameTable.setAssamDirect(SOUTH_DIRECT);
				}
				else if(gameTable.getAssamY() == 0 && gameTable.getAssamX() == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY());
					gameTable.setAssamDirect(EAST_DIRECT);
				}
				else {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() - 1);
				}
				
			}
			else if(gameTable.getAssamDirect() == EAST_DIRECT) {
				
				if(gameTable.getAssamX() == 6 && (gameTable.getAssamY() % 2) == 1) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() - 1);
					gameTable.setAssamDirect(WEST_DIRECT);
				}
				else if(gameTable.getAssamX() == 6 && gameTable.getAssamY() != 6 && (gameTable.getAssamY() % 2) == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() + 1);
					gameTable.setAssamDirect(WEST_DIRECT);
				}
				else if(gameTable.getAssamX() == 6 && gameTable.getAssamY() == 6) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY());
					gameTable.setAssamDirect(NORTH_DIRECT);
				}
				else {
					gameTable.setAssamLocation(gameTable.getAssamX() + 1, gameTable.getAssamY());
				}
				
			}
			else if(gameTable.getAssamDirect() == SOUTH_DIRECT) {
				
				if(gameTable.getAssamY() == 6 && (gameTable.getAssamX() % 2) == 1) {
					gameTable.setAssamLocation(gameTable.getAssamX() - 1, gameTable.getAssamY());
					gameTable.setAssamDirect(NORTH_DIRECT);
				}
				else if(gameTable.getAssamY() == 6 && gameTable.getAssamX() != 6 && (gameTable.getAssamX() % 2) == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX() + 1, gameTable.getAssamY());
					gameTable.setAssamDirect(NORTH_DIRECT);
				}
				else if(gameTable.getAssamY() == 6 && gameTable.getAssamX() == 6) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY());
					gameTable.setAssamDirect(WEST_DIRECT);
				}
				else {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() + 1);
				}
				
			}
			else if(gameTable.getAssamDirect() == WEST_DIRECT) {
				if(gameTable.getAssamX() == 0 && (gameTable.getAssamY() % 2) == 1) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() + 1);
					gameTable.setAssamDirect(EAST_DIRECT);
				}
				else if(gameTable.getAssamX() == 0 && gameTable.getAssamY() != 0 && (gameTable.getAssamY() % 2) == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY() - 1);
					gameTable.setAssamDirect(EAST_DIRECT);
				}
				else if(gameTable.getAssamX() == 0 && gameTable.getAssamY() == 0) {
					gameTable.setAssamLocation(gameTable.getAssamX(), gameTable.getAssamY());
					gameTable.setAssamDirect(SOUTH_DIRECT);
				}
				else {
					gameTable.setAssamLocation(gameTable.getAssamX() - 1, gameTable.getAssamY());
				}
			}
			gameTable.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
