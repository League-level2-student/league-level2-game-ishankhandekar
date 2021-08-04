package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myGame {
	static JFrame frame;
	public  static gamePanel panel;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
		public static void main(String[] args) {
			myGame applePicker = new myGame();
			applePicker.setup();}
			
	public myGame() {
		this.frame = new JFrame();
		panel = new gamePanel();
		frame.addKeyListener(panel);
		frame.add(panel);
	
	}
	public static void setup() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
