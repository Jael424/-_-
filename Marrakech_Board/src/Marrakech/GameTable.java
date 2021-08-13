package Marrakech;


import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class GameTable extends JPanel {
	
	private final int NORTH_DIRECT = 0;
	private final int EAST_DIRECT = 1;
	private final int SOUTH_DIRECT = 2;
	private final int WEST_DIRECT = 3;
	// 방향을 나타내는 상수
	
	private static BigTable bigTable;
	
	private boolean isInput = false; // 플레이어가 버튼을 클릭했는지 확인하는 변수.
	
	private PlayerLabel playerLabel; 
	private GameLogLabel logLabel; // 게임의 진행 로그를 보여주는 라벨
	private PlayerLogLabel playerLogLabel_Dir; // 현재 진행중인 플레이어의 다르함 개수를 보여주는 라벨 
	private PlayerLogLabel playerLogLabel_Car; // 현재 진행중인 플레이어의 양탄자 수를 보여주는 라벨
	private DiceLogLabel diceLabel; // 주사위 결과를 보여주는 라벨
	private Assam assam = BigTable.getAssam();
	private DirectButton directButton;
	
	
	public GameTable(int playerNum) {

		setSize(1000,560 + 200);
		setLayout(null);
		
		bigTable = new BigTable();
		bigTable.setBounds(20, 20, 466, 466);
		
		playerLabel = new PlayerLabel(playerNum);
		playerLabel.setBounds(506, 0, 450,300);
		
		diceLabel = new DiceLogLabel();
		diceLabel.setBounds(526, 300, 150, 40);
		
		logLabel = new GameLogLabel();
		logLabel.setBounds(526, 300, 420, 186);
		
		playerLogLabel_Dir = new PlayerLogLabel();
		playerLogLabel_Dir.setBounds(20, 500, 466, 90);
		
		playerLogLabel_Car = new PlayerLogLabel();
		playerLogLabel_Car.setBounds(20, 590, 466, 100);
		
		directButton = new DirectButton();
		directButton.setBounds(526, 500, 200, 180);
		
		add(bigTable);
		
		add(playerLabel);
		add(diceLabel);
		add(logLabel);
		add(playerLogLabel_Dir);
		add(playerLogLabel_Car);
		
		add(directButton);
		
		setVisible(true);
	}
	
	
	public SmallTable getSmallTable(int x, int y) {
		return bigTable.getSmallTable(x, y);
	} // (x,y) 위치의 작은 테이블을 반환한다.
	
	
	public static void allResetHolding() {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				bigTable.getSmallTable(i, j).setHolding(false);
			}
		}
	}
	
	
	public void setAssamDirect(int direct) {
		assam.setDirect(direct);

	} // 아쌈의 방향을 설정
	
	public void setAssamLocation(int x, int y) {
		assam.setAssamLoc(x, y);
		bigTable.getSmallTable(assam.getAssamX(), assam.getAssamY()).add(assam);
	} // 아쌈 위치 설정
	
	public void buttonInActive() {
		directButton.southButton.setEnabled(false);
		directButton.northButton.setEnabled(false);
		directButton.eastButton.setEnabled(false);
		directButton.westButton.setEnabled(false);
	} // 버튼을 잠시 비활성화한다.
	
	public void buttonActive() {
		directButton.setDirect(assam.getDirect());
	} // 버튼을 다시 활성화시킨다.
	
	public void setHolding(int x, int y, boolean holding) {
		bigTable.getSmallTable(x, y).setHolding(holding);
	} // 홀딩하는 것을 허락할지 아닐지 설정하는 메소드
	
	public void setIsInput(boolean isInput) {
		this.isInput = isInput;
	} // 플레이어가 버튼을 클릭했는지 확인하는 변수를 변경할 수 있도록 하는 메서드
	
	public void setLogLebel(String log) {
		logLabel.setText(log);
		repaint();
	} // 게임 진행 기록 라벨 수정 
	
	public void setPlayerLog_Dir(String log) {
		playerLogLabel_Dir.setText(log);
		repaint();
	} // 현재 진행중인 플레이어의 다르함의 개수를 알려주는 라벨
	
	public void setPlayerLog_Car(String log) {
		playerLogLabel_Car.setText(log);
		repaint();
	} // 현재 진행중인 플레이어의 양탄자 개수를 알려주는 라벨
	
	
	public void setDiceLebel(String log) {
		diceLabel.setText(log);
		repaint();
	} // 주사위 결과 반영 라벨 수정 
	
	
	public void curentPlayerL(int i) {
		playerLabel.selectPlayerLabel(i);
		repaint();
	} // 현재 진행중인 플레이어의 여부를 알리는 라벨(색 변경)
	
	public void resetPlayerL(int i) {
		playerLabel.resetPlayerLabel(i);
		repaint();
	} // 진행이 끝난 플레이어의 라벨 색을 원상복귀(색 변경)
	
	public void setCurPlayer(Player player) {
		SmallTable.setCurPlayer(player);
	} // 현재 진행 중인 플레이어를 설정
	
	
	public boolean getIsInput() {
		return isInput;
	} // 플레이어가 버튼을 클릭했는지 확인하는 변수를 반환하는 메서드
	
	
	public int getAssamX() {
		return assam.getAssamX();
	} // 아쌈 현재 X위치 리턴 메서드
		
	public int getAssamY() {
		return assam.getAssamY();
	} // 아쌈 현재 Y위치 리턴 메소드
	
	public int getAssamDirect() {
		return assam.getDirect();
	} // 아쌈의 현재 방향을 반환

	
	class GameLogLabel extends JLabel{

		public GameLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("게임 로그");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("맑은 고딕", Font.BOLD, 25));
			setOpaque(true);
		}
	} // 게임 로그 라벨 
	
	class PlayerLogLabel extends JLabel{
		
		public PlayerLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("플레이어 로그 로그");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("맑은 고딕", Font.BOLD, 25));
			setOpaque(true);
		}
	} // 플레이어 로그 라벨 
	
	
	class DiceLogLabel extends JLabel{
		
		public DiceLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("주사위 로그");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("맑은 고딕", Font.BOLD, 20));
			setOpaque(true);
		}
	} // 주사위 결과값 반영 라벨
	
	
	class DirectButton extends JPanel{
		
		private JButton northButton = new JButton("NORTH");
		private JButton eastButton = new JButton("EAST");
		private JButton southButton = new JButton("SOUTH");
		private JButton westButton = new JButton("WEST");
		
		public DirectButton() {
			setSize(200,180);
			setLayout(new BorderLayout());
			
			northButton.setBackground(Color.DARK_GRAY);
			northButton.setForeground(Color.white);
			northButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
			eastButton.setBackground(Color.DARK_GRAY);
			eastButton.setForeground(Color.white);
			eastButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
			southButton.setBackground(Color.DARK_GRAY);
			southButton.setForeground(Color.white);
			southButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
			westButton.setBackground(Color.DARK_GRAY);
			westButton.setForeground(Color.white);
			westButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
			northButton.addActionListener(new NorthButtonA());
			eastButton.addActionListener(new EastButtonA());
			southButton.addActionListener(new SouthButtonA());
			westButton.addActionListener(new WestButtonA());
			
			add(northButton, BorderLayout.NORTH);
			add(eastButton, BorderLayout.EAST);
			add(southButton, BorderLayout.SOUTH);
			add(westButton, BorderLayout.WEST);
		}
		
		public void setDirect(int direct) {
			assam.setDirect(direct);
			if(assam.getDirect() == NORTH_DIRECT) {
				southButton.setEnabled(false);
				northButton.setEnabled(true);
				eastButton.setEnabled(true);
				westButton.setEnabled(true);
				repaint();
			}
			else if(assam.getDirect() == EAST_DIRECT) {
				westButton.setEnabled(false);
				northButton.setEnabled(true);
				eastButton.setEnabled(true);
				southButton.setEnabled(true);
				repaint();
			}
			else if(assam.getDirect() == SOUTH_DIRECT) {
				northButton.setEnabled(false);
				eastButton.setEnabled(true);
				southButton.setEnabled(true);
				westButton.setEnabled(true);
				repaint();
			}
			else if(assam.getDirect() == WEST_DIRECT) {
				eastButton.setEnabled(false);
				northButton.setEnabled(true);
				southButton.setEnabled(true);
				westButton.setEnabled(true);
				repaint();
			}
		} // 아쌈의 방향을 설정
		
		class NorthButtonA implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDirect(NORTH_DIRECT);
				isInput = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				isInput = false;
			}
			
		}
		
		class EastButtonA implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDirect(EAST_DIRECT);
				isInput = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				isInput = false;
			}
			
		}
		
		class SouthButtonA implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDirect(SOUTH_DIRECT);
				isInput = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				isInput = false;
			}
			
		}
		
		class WestButtonA implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDirect(WEST_DIRECT);
				isInput = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				isInput = false;
			}
			
		}
	}
}

