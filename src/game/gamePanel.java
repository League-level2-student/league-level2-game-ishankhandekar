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
	
	objectManager objManager;
	Timer fruitSpawn;
	Timer frameDraw;
	Timer speedTimer;
	myGame fruitFamer;
	Farmer farmer;
	Font titleFont;
	Font subTitleFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int fruitSpawnTime;
	int currentState = MENU;
	public gamePanel() {
		fruitSpawnTime = 1000;
		farmer = new Farmer(700,375, 50,50);
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	     objManager = new objectManager(farmer);
	    fruitSpawn = new Timer(3000,objManager);
	    speedTimer = new Timer(10000, this);
	   
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
	
	public void startGame() {
		
		fruitSpawn = new Timer(1000 , objManager);
	    fruitSpawn.start();
	    speedTimer = new Timer(30000 ,this);
	    speedTimer.start(); 
	}

	public void drawGameState(Graphics g) {
		g.setColor(new Color(173, 216, 230));
		 g.fillRect(0, 0, myGame.WIDTH, myGame.HEIGHT);
		 farmer.draw(g);
		 objManager.drawFruitsOrHazzardsOrPowerups(g);
	}
	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		 g.fillRect(0, 0, myGame.WIDTH, myGame.HEIGHT);
	}
	public void updateMenuState() {
		
	}
	public void updateGameState() {
		 objManager.update();
		 if (farmer.isActive == false) {
			currentState = END;
		}
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
		    	startGame();
		    	
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
		
		if (e.getSource() == speedTimer) {
			fruitSpawnTime += 20;
			fruitSpawn.setDelay(fruitSpawnTime);
			hazzards.speed += 1;
			fruit.speed += 1;
		}
		if (e.getSource() == frameDraw) {
		
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
			
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();}
		
	}
	
	
	
}
