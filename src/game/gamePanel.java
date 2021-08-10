package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gamePanel extends JPanel implements KeyListener,ActionListener {
	
	Timer frameDraw;
	myGame fruitFamer;
	Farmer farmer;
	Font titleFont;
	Font subTitleFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	
	int currentState = MENU;
	public gamePanel() {
		farmer = new Farmer(700,375, 50,50);
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		 subTitleFont = new Font("Arial", Font.PLAIN, 24);
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	public void drawMenuState(Graphics g) {
		g.setColor(Color.ORANGE);
		 g.fillRect(0, 0, myGame.WIDTH, myGame.HEIGHT);
		 g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("Fruit Farmer", 250, 100);
		
	}
	


	public void drawGameState(Graphics g) {
		g.setColor(new Color(173, 216, 230));
		 g.fillRect(0, 0, myGame.WIDTH, myGame.HEIGHT);
		 farmer.draw(g);
	}
	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		 g.fillRect(0, 0, myGame.WIDTH, myGame.HEIGHT);
	}
	public void updateMenuState() {
		
	}
	public void updateGameState() {
		
	}
	public void updateEndState() {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	
		        currentState = MENU;
		        
		    }else if(currentState == MENU) {
		    	currentState++;
		    	
		    }else if(currentState == GAME) {
		        currentState++;
		    }
		    }
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT && currentState == GAME && ( farmer.x < 741 )) {
			farmer.right();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT && currentState == GAME && ( farmer.x > 0 )) {
			farmer.left();
		}}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		repaint();
	}
	
	
	
}
