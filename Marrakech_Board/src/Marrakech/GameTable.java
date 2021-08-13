package Marrakech;


import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class GameTable extends JPanel {
	
	private final int NORTH_DIRECT = 0;
	private final int EAST_DIRECT = 1;
	private final int SOUTH_DIRECT = 2;
	private final int WEST_DIRECT = 3;
	// ������ ��Ÿ���� ���
	
	private static BigTable bigTable;
	
	private boolean isInput = false; // �÷��̾ ��ư�� Ŭ���ߴ��� Ȯ���ϴ� ����.
	
	private PlayerLabel playerLabel; 
	private GameLogLabel logLabel; // ������ ���� �α׸� �����ִ� ��
	private PlayerLogLabel playerLogLabel_Dir; // ���� �������� �÷��̾��� �ٸ��� ������ �����ִ� �� 
	private PlayerLogLabel playerLogLabel_Car; // ���� �������� �÷��̾��� ��ź�� ���� �����ִ� ��
	private DiceLogLabel diceLabel; // �ֻ��� ����� �����ִ� ��
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
	} // (x,y) ��ġ�� ���� ���̺��� ��ȯ�Ѵ�.
	
	
	public static void allResetHolding() {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				bigTable.getSmallTable(i, j).setHolding(false);
			}
		}
	}
	
	
	public void setAssamDirect(int direct) {
		assam.setDirect(direct);

	} // �ƽ��� ������ ����
	
	public void setAssamLocation(int x, int y) {
		assam.setAssamLoc(x, y);
		bigTable.getSmallTable(assam.getAssamX(), assam.getAssamY()).add(assam);
	} // �ƽ� ��ġ ����
	
	public void buttonInActive() {
		directButton.southButton.setEnabled(false);
		directButton.northButton.setEnabled(false);
		directButton.eastButton.setEnabled(false);
		directButton.westButton.setEnabled(false);
	} // ��ư�� ��� ��Ȱ��ȭ�Ѵ�.
	
	public void buttonActive() {
		directButton.setDirect(assam.getDirect());
	} // ��ư�� �ٽ� Ȱ��ȭ��Ų��.
	
	public void setHolding(int x, int y, boolean holding) {
		bigTable.getSmallTable(x, y).setHolding(holding);
	} // Ȧ���ϴ� ���� ������� �ƴ��� �����ϴ� �޼ҵ�
	
	public void setIsInput(boolean isInput) {
		this.isInput = isInput;
	} // �÷��̾ ��ư�� Ŭ���ߴ��� Ȯ���ϴ� ������ ������ �� �ֵ��� �ϴ� �޼���
	
	public void setLogLebel(String log) {
		logLabel.setText(log);
		repaint();
	} // ���� ���� ��� �� ���� 
	
	public void setPlayerLog_Dir(String log) {
		playerLogLabel_Dir.setText(log);
		repaint();
	} // ���� �������� �÷��̾��� �ٸ����� ������ �˷��ִ� ��
	
	public void setPlayerLog_Car(String log) {
		playerLogLabel_Car.setText(log);
		repaint();
	} // ���� �������� �÷��̾��� ��ź�� ������ �˷��ִ� ��
	
	
	public void setDiceLebel(String log) {
		diceLabel.setText(log);
		repaint();
	} // �ֻ��� ��� �ݿ� �� ���� 
	
	
	public void curentPlayerL(int i) {
		playerLabel.selectPlayerLabel(i);
		repaint();
	} // ���� �������� �÷��̾��� ���θ� �˸��� ��(�� ����)
	
	public void resetPlayerL(int i) {
		playerLabel.resetPlayerLabel(i);
		repaint();
	} // ������ ���� �÷��̾��� �� ���� ���󺹱�(�� ����)
	
	public void setCurPlayer(Player player) {
		SmallTable.setCurPlayer(player);
	} // ���� ���� ���� �÷��̾ ����
	
	
	public boolean getIsInput() {
		return isInput;
	} // �÷��̾ ��ư�� Ŭ���ߴ��� Ȯ���ϴ� ������ ��ȯ�ϴ� �޼���
	
	
	public int getAssamX() {
		return assam.getAssamX();
	} // �ƽ� ���� X��ġ ���� �޼���
		
	public int getAssamY() {
		return assam.getAssamY();
	} // �ƽ� ���� Y��ġ ���� �޼ҵ�
	
	public int getAssamDirect() {
		return assam.getDirect();
	} // �ƽ��� ���� ������ ��ȯ

	
	class GameLogLabel extends JLabel{

		public GameLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("���� �α�");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("���� ���", Font.BOLD, 25));
			setOpaque(true);
		}
	} // ���� �α� �� 
	
	class PlayerLogLabel extends JLabel{
		
		public PlayerLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("�÷��̾� �α� �α�");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("���� ���", Font.BOLD, 25));
			setOpaque(true);
		}
	} // �÷��̾� �α� �� 
	
	
	class DiceLogLabel extends JLabel{
		
		public DiceLogLabel() {
			setSize(20,40);
			setPreferredSize(new Dimension(20,40));
			setText("�ֻ��� �α�");
			setHorizontalAlignment(JLabel.CENTER);
			setBackground(Color.GRAY);
			setForeground(Color.white);
			setFont(new Font("���� ���", Font.BOLD, 20));
			setOpaque(true);
		}
	} // �ֻ��� ����� �ݿ� ��
	
	
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
			northButton.setFont(new Font("���� ���", Font.BOLD, 15));
			
			eastButton.setBackground(Color.DARK_GRAY);
			eastButton.setForeground(Color.white);
			eastButton.setFont(new Font("���� ���", Font.BOLD, 15));
			
			southButton.setBackground(Color.DARK_GRAY);
			southButton.setForeground(Color.white);
			southButton.setFont(new Font("���� ���", Font.BOLD, 15));
			
			westButton.setBackground(Color.DARK_GRAY);
			westButton.setForeground(Color.white);
			westButton.setFont(new Font("���� ���", Font.BOLD, 15));
			
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
		} // �ƽ��� ������ ����
		
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

