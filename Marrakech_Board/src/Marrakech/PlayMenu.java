package Marrakech;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class PlayMenu extends JPanel {
	
	private final int COMPUTER3 = 1;
	private final int COMPUTER2 = 2;
	private final int COMPUTER1 = 3;
	private final int GAMECREATE = 4;
	private final int GAMEPARTICIPATE = 5;
	private final int PREFERENCES = 6;
	private final int END = 7;
	
	private int playerSelect = 0;
	
	private JButton offlinePlayB;
	private JButton onlinePlayB;
	private JButton gameCreate;
	private JButton gamePartici;
	private JButton preferencesB;
	private JButton endB;
	private JButton computerPlayer3;
	private JButton computerPlayer2;
	private JButton computerPlayer1;
	
	public void setPlayerSelect(int playerSelect) {
		this.playerSelect = playerSelect;
	} // 유저가 버튼 선택시 PlayerSelect에 버튼 정보 저장
	
	public int getPlayerSelect() {
		return this.playerSelect;
	} // 유저가 선택한 버튼을 출력(정수형 상수)
	
	
	public PlayMenu() {
		setSize(1000, 1000);
		setLayout(null);
		
		offlinePlayB = new JButton("오프라인 플레이");
		offlinePlayB.setLocation(60, 40);
		offlinePlayB.setSize(400,65);
		offlinePlayB.addActionListener(new OfflinePlayL());
		offlinePlayB.setBackground(new Color(90,155,213));
		offlinePlayB.setForeground(Color.white);
		offlinePlayB.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(offlinePlayB);
		
		computerPlayer3 = new JButton("컴퓨터 플레이어 3명 추가");
		computerPlayer3.setLocation(520, 100);
		computerPlayer3.setSize(400,65);
		computerPlayer3.addActionListener(new Computplayer3());
		computerPlayer3.setBackground(new Color(90,155,243));
		computerPlayer3.setForeground(Color.white);
		computerPlayer3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
		computerPlayer2 = new JButton("컴퓨터 플레이어 2명 추가");
		computerPlayer2.setLocation(520, 220);
		computerPlayer2.setSize(400,65);
		computerPlayer2.addActionListener(new Computplayer2());
		computerPlayer2.setBackground(new Color(90,155,243));
		computerPlayer2.setForeground(Color.white);
		computerPlayer2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
		computerPlayer1 = new JButton("컴퓨터 플레이어 1명 추가");
		computerPlayer1.setLocation(520, 340);
		computerPlayer1.setSize(400,65);
		computerPlayer1.addActionListener(new Computplayer1());
		computerPlayer1.setBackground(new Color(90,155,243));
		computerPlayer1.setForeground(Color.white);
		computerPlayer1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		onlinePlayB = new JButton("온라인 플레이");
		onlinePlayB.setLocation(60, 160);
		onlinePlayB.setSize(400,65);
		onlinePlayB.addActionListener(new OnlinePlayL());
		onlinePlayB.setBackground(new Color(90,155,213));
		onlinePlayB.setForeground(Color.white);
		onlinePlayB.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(onlinePlayB);
		
        gameCreate = new JButton("게임 생성");
        gameCreate.setLocation(520, 160);
        gameCreate.setSize(400,65);
        gameCreate.addActionListener(new GameCreateL());
        gameCreate.setBackground(new Color(90,155,243));
        gameCreate.setForeground(Color.white);
        gameCreate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        gamePartici = new JButton("게임 참가");
        gamePartici.setLocation(520, 280);
        gamePartici.setSize(400,65);
        gamePartici.addActionListener(new GameParticiL());
        gamePartici.setBackground(new Color(90,155,243));
        gamePartici.setForeground(Color.white);
        gamePartici.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		preferencesB = new JButton("환경 설정");
		preferencesB.setLocation(60, 280);
		preferencesB.setSize(400,65);
		preferencesB.addActionListener(new PreferencesL());
		preferencesB.setBackground(new Color(90,155,213));
		preferencesB.setForeground(Color.white);
		preferencesB.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(preferencesB);
		
		endB = new JButton("프로그램 종료");
		endB.setLocation(60, 400);
		endB.setSize(400,65);
		endB.addActionListener(new EndL());
		endB.setBackground(new Color(90,155,213));
		endB.setForeground(Color.white);
		endB.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(endB);
		setVisible(true);
	}
	
	class Computplayer3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setPlayerSelect(COMPUTER3);
			add(computerPlayer1);
			add(computerPlayer2);
			add(computerPlayer3);
	        remove(gameCreate);
	        remove(gamePartici);
	        repaint();
		}
		
	}

	class Computplayer2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setPlayerSelect(COMPUTER2);
			add(computerPlayer1);
			add(computerPlayer2);
			add(computerPlayer3);
	        remove(gameCreate);
	        remove(gamePartici);
	        repaint();
		}
		
	}
	
	class Computplayer1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setPlayerSelect(COMPUTER1);
			add(computerPlayer1);
			add(computerPlayer2);
			add(computerPlayer3);
	        remove(gameCreate);
	        remove(gamePartici);
	        repaint();
		}
		
	}
	
	class OfflinePlayL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			add(computerPlayer1);
			add(computerPlayer2);
			add(computerPlayer3);
	        remove(gameCreate);
	        remove(gamePartici);
	        repaint();
		}
		
	}
	
	class OnlinePlayL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
	         add(gameCreate);
	         add(gamePartici);
	         
	         remove(computerPlayer3);
		     remove(computerPlayer2);
		     remove(computerPlayer1);
	         repaint();
		}
		
	}
	
	class GameCreateL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
	         setPlayerSelect(GAMECREATE);
	         repaint();
		}
	} 
	
	class GameParticiL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
	         setPlayerSelect(GAMEPARTICIPATE);
	         repaint();
		}
		
	}
	
	class PreferencesL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setPlayerSelect(PREFERENCES);
	        remove(gameCreate);
	        remove(gamePartici);
	        
	        remove(computerPlayer3);
	        remove(computerPlayer2);
	        remove(computerPlayer1);
	        repaint();
		}
		
	}
	
	class EndL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setPlayerSelect(END);
			playerSelect = END;
	        repaint();
		}
		
	}

}
