package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Farmer extends GameObject implements ActionListener{
	int speed;
	final int normalSizeX= 50;
	
	final int expandedSizeX= 100;
	Timer largerCollisionboxTimer;
	boolean isLargerCollisionboxActive = false;
	
	
	public Farmer(int x, int y, int width, int height) {
		super(x, y, width, height);
		largerCollisionboxTimer = new Timer(13000, this);
		speed = 10;
		
	}
	public void update() {
		if (isLargerCollisionboxActive) {
			this.width = expandedSizeX;
			isLargerCollisionboxActive = false;
		}
		super.update();
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	public void right() {
        x+=speed;
    }
	public void left() {
        x-=speed;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == largerCollisionboxTimer) {
			this.width = normalSizeX;
		}
	}
}
