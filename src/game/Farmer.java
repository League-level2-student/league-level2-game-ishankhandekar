package game;

import java.awt.Color;
import java.awt.Graphics;

public class Farmer extends GameObject{
	int speed;
	
	public Farmer(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		
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
}
