//package live.xjames.framew;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class pathfinder extends JFrame implements ActionListener {

	private static String windowName = "Pathfinder";
	private JButton[][] b = new JButton[25][25]; 
	private int[][] ctr = new int[25][25];
	private int[][] endStart = new int[2][2];
	
	private JButton startButton = new JButton("Start Pathfinder!");
	
	public static void main(String[] args) {
		pathfinder m = new pathfinder();
	}
	
	public window() {
		super(windowName);
		init();
	}
	
	public void init() {
		super.setBounds(50, 50, 400, 400);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(null);
		initButtons();
		startButton.setBounds(50, 725, 300, 100);
		startButton.addActionListener(this);
		super.getContentPane().add(startButton);
		appear();
	}
	
	public void initButtons() {
		int x = 0;
		int y = 0;
		
		
		for (int row = 0; row < b.length; row++){ 
			for (int col = 0; col < b[0].length; col++) {
				b[row][col] = new JButton();
				b[row][col].setBounds(x, y, 25, 25);
				b[row][col].addActionListener(this);
				b[row][col].setBackground(Color.WHITE);
				ctr[row][col] = 0;
				super.getContentPane().add(b[row][col]);
				x += 25;
			}
			y += 25;
			x = 0;
		}
	}
	
	public void appear() {
		super.setVisible(true);
	}
	
	public void disappear() {
		super.setVisible(false);
	}
	
	public void makeWall(JButton e) {
		e.setBackground(Color.DARK_GRAY);
	}
	
	public void makeStart(JButton e) {
		e.setBackground(Color.GREEN);
	}
	
	public void makeEnd(JButton e) {
		e.setBackground(Color.ORANGE);
	}
	
	public void makeReg(JButton e, int row, int col) {
		e.setBackground(Color.WHITE);
		ctr[row][col] = 0;
	}

	public void calc() {
		int currentButton[][] = new int[1][2];
		currentButton[0][0] = endStart[0][0];
		currentButton[0][1] = endStart[0][1];
		
		while (currentButton [0][0] != endStart[1][0] && currentButton [0][1] != endStart[1][1] )
		{
			// TODO : ADD DIAGANOLS
		int distanceRight = (int) Math.sqrt(((currentButton[0][0] + 1 - endStart[1][0]) * (currentButton[0][0] + 1 - endStart[1][0])) + ((currentButton[0][1] - endStart[1][1]) * (currentButton[0][1] - endStart[1][1])));
		int distanceLeft = (int) Math.sqrt(((currentButton[0][0] - 1 - endStart[1][0]) * (currentButton[0][0] - 1 - endStart[1][0])) + ((currentButton[0][1] - endStart[1][1]) * (currentButton[0][1] - endStart[1][1])));
		int distanceTop = (int) Math.sqrt(((currentButton[0][0] - endStart[1][0]) * (currentButton[0][0] + 1 - endStart[1][0])) + ((currentButton[0][1] + 1 - endStart[1][1]) * (currentButton[0][1] - endStart[1][1])));
		int distanceBottom = (int) Math.sqrt(((currentButton[0][0] - endStart[1][0]) * (currentButton[0][0] + 1 - endStart[1][0])) + ((currentButton[0][1] - 1 - endStart[1][1]) * (currentButton[0][1] - endStart[1][1])));
		if (distanceRight < distanceLeft && distanceRight < distanceTop && distanceRight < distanceBottom )
				currentButton[0][0] += 1;
		else if (distanceTop < distanceBottom && distanceTop < distanceRight && distanceTop < distanceLeft )
			currentButton[0][1] += 1;
		else if (distanceBottom < distanceTop && distanceBottom < distanceRight && distanceBottom < distanceLeft) 
			currentButton[0][1] -= 1;
		else
			currentButton[0][0] -= 1;
		
		b[currentButton[0][0]][currentButton[0][1]].setBackground(Color.BLUE);
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(startButton)) {
			
			System.out.println("Starting Sim:\nStart : " + endStart[0][0] + " , " + endStart[0][1] + "\nEnd : " + endStart[1][0] + " , " + endStart[1][1]);
			calc();
		}
		else
		for (int row = 0; row < b.length; row++) {
			for (int col = 0; col < b[0].length; col++) {
				if (e.getSource().equals(b[row][col])) {
					ctr[row][col]++;
					switch(ctr[row][col]) {
						case 1:
							makeWall(b[row][col]);
							break;
						case 2:
							makeStart(b[row][col]);
							endStart[0][0] = row;
							endStart[0][1] = col;
							break;
						case 3:
							makeEnd(b[row][col]);
							endStart[1][0] = row;
							endStart[1][1] = col;
							break;
						case 4:
							makeReg(b[row][col], row, col);
							break;
					}
				}
			}
		}
	}

}
