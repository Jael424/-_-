package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import Marrakech.PlayMenu.GameCreateL;

import java.io.*;

import java.net.*;

import java.util.*;

public class SeverEx {
	/*
	private GameTable gameTable = new GameTable(4);
	private PlayMenu playmenu = null;
	private PlayScreen playScreen = null;
	private int[] randomNum = {1,2,2,3,3,4};
	
	static Socket socket_1 = null;
	static Socket socket_2 = null;
	static Socket socket_3 = null;
	static int connectPlayer = 1;
	
	static BufferedReader in_1 = null;
	static BufferedWriter out_1 = null;
	
	static BufferedReader in_2 = null;
	static BufferedWriter out_2 = null;
	
	static BufferedReader in_3 = null;
	static BufferedWriter out_3 = null;
	
	static class WaitFrame extends JFrame implements ActionListener{
        private PlayerLabel connectCircum;
        private JButton gameStart;
        private JLabel label;
    	private Color connectColor = new Color(90,155,243);
    	private int playerNum;
    	private MakeSocket makeSocket;
		public WaitFrame(int playerNum) {
			this.playerNum = playerNum;
			setTitle("dddd");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1000,560);
			setLayout(null);
			
	        gameStart = new JButton("게임 시작");
	        gameStart.setLocation(70, 90);
	        gameStart.setSize(400,70);
	        gameStart.addActionListener(this);
	        gameStart.setBackground(new Color(90,155,243));
	        gameStart.setForeground(Color.white);
	        gameStart.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	        add(gameStart);
	        
	        label = new JLabel();
	        label.setSize(400,200);
	        label.setLocation(70, 235);
	        label.setPreferredSize(new Dimension(400,200));
	        label.setText("대 기 상 태");
	        label.setHorizontalAlignment(JLabel.CENTER);
	        label.setBackground(Color.DARK_GRAY);
	        label.setForeground(Color.white);
	        label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
	        label.setOpaque(true);
	        add(label);
			
	        connectCircum = new PlayerLabel(playerNum);
	        
	        for(int i = 1; i < playerNum; i++) {
		        connectCircum.settingColor(Color.GRAY, i);
	        }
	        connectCircum.setLocation(485, 90);
	        connectCircum.setSize(500, 500);
	        add(connectCircum);
			setVisible(true);
		}
		
		public void setMakeSocket(MakeSocket makeSocket) {
			this.makeSocket = makeSocket;
		}
		
		public void setConnect(int playerNum) {
			connectCircum.settingColor(connectColor, playerNum);
			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
					makeSocket.interrupt();

			}
	}  // 대기 상태 프레임 생성
	
	
	static class MakeSocket extends Thread{
		ServerSocket listener = null;
		WaitFrame waitFrame = null;
		int playerNum;
		public MakeSocket(ServerSocket listener, WaitFrame waitFrame, int playerNum) {
			this.listener = listener;
			this.waitFrame = waitFrame;
			this.playerNum = playerNum;
		}
		
		public synchronized void run() {
			try {
			sleep(20);
			if(playerNum == 4){
					try {
						socket_1 = listener.accept();
						
						in_1 = new BufferedReader(new InputStreamReader(socket_1.getInputStream()));

						out_1 = new BufferedWriter(new OutputStreamWriter(socket_1.getOutputStream()));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					waitFrame.setConnect(1);
					connectPlayer++;
				
					try {
						socket_2 = listener.accept();
						
						in_2 = new BufferedReader(new InputStreamReader(socket_2.getInputStream()));
		
						out_2 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));
	
					


					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					waitFrame.setConnect(2);
					connectPlayer++;
				
					try {
						socket_3 = listener.accept();
					
						in_3 = new BufferedReader(new InputStreamReader(socket_3.getInputStream()));
						out_3 = new BufferedWriter(new OutputStreamWriter(socket_3.getOutputStream()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					waitFrame.setConnect(3);
					connectPlayer++;
			}
			if(playerNum == 3){
					try {
						socket_1 = listener.accept();
						in_1 = new BufferedReader(new InputStreamReader(socket_2.getInputStream()));
						out_1 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					waitFrame.setConnect(1);
					connectPlayer++;
				
					try {
						socket_2 = listener.accept();
						
						in_2 = new BufferedReader(new InputStreamReader(socket_2.getInputStream()));

						out_2 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));

					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					waitFrame.setConnect(2);
					connectPlayer++;
					
			
			}
			
			if(playerNum == 2){
				try {
					socket_1 = listener.accept();
					
					in_1 = new BufferedReader(new InputStreamReader(socket_2.getInputStream()));

					out_1 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));

				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				waitFrame.setConnect(1);
				connectPlayer++;
			
			}
		} catch(InterruptedException e) {
			waitFrame.setConnect(1);
			waitFrame.setConnect(2);
			waitFrame.setConnect(3);
			System.out.println("aaaaaaa");
			return;
		}
			
		}
	}
	

	
	public static void main(String[] args) throws InterruptedException {	
		int playerNumber;
		Vector<BufferedReader> readerVector = new Vector<BufferedReader>();
		Vector<BufferedWriter> writerVector = new Vector<BufferedWriter>();
		GameTable gameTable = new GameTable(4);
		PlayScreen playScreen;
		ServerSocket listener = null;
		WaitFrame waitFrame = null;
		int[] randomNum = {1,2,2,3,3,4};

		
		playScreen = null;


		try {
			listener = new ServerSocket(9989); // 서버 소켓 생성
			while(true) {
				playerNumber = Integer.valueOf(JOptionPane.showInputDialog("플레이어 최대 인원수(본인 포함): "));
				if(playerNumber <= 4 && playerNumber >= 1) {
					waitFrame = new WaitFrame(playerNumber);

					MakeSocket makeSocket = new MakeSocket(listener, waitFrame, playerNumber);
					waitFrame.setMakeSocket(makeSocket);
					makeSocket.start();
					makeSocket.join();
					break;
				}
				else {
					continue;
				}
			}
		// 클라이언트로부터 연결 요청 대기
		}catch(IOException e) {
			try {
				socket_1.close();
				listener.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}
	*/
}
