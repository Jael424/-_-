package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.util.*;

public class GameEx {
	
	private static final int COMPUTER3 = 1;
	private static final int COMPUTER2 = 2;
	private static final int COMPUTER1 = 3;
	private static final int GAMECREATE = 4;
	private static final int GAMEPARTICIPATE = 5;
	private static final int PREFERENCES = 6;
	private static final int END = 7;
	// 유저 게임 메뉴 선택 상수
	
	private static PlayScreen playScreen = null; 
	private static PlayMenu playMenu = null;
	private static Thread offlineGame = null;
	
	public static void main(String[] args) {
		
		playMenu = new PlayMenu();
		playScreen = new PlayScreen(playMenu);
		
		while(true) {
			int i = playMenu.getPlayerSelect();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(i == COMPUTER3) {
				playScreen.remove(playMenu);
				offlineGame = new OfflineGameEx(i, playScreen);
				offlineGame.start();
				try {
					offlineGame.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			else if(i == COMPUTER2) {
				playScreen.remove(playMenu);
				offlineGame = new OfflineGameEx(i, playScreen);
				offlineGame.start();
				try {
					offlineGame.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			else if(i == COMPUTER1) {
				playScreen.remove(playMenu);
				offlineGame = new OfflineGameEx(i, playScreen);
				offlineGame.start();
				try {
					offlineGame.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			else if(i == GAMECREATE) {
			}
			else if(i == GAMEPARTICIPATE) {
			}
			else if(i == PREFERENCES) {
			}
			else if(i == END) {
				playScreen.dispose();
				break;
			}
		}
	}
	
}
