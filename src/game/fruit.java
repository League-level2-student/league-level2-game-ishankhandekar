package game;

import java.awt.Color;
import java.awt.Graphics;

public class fruit extends GameObject{
	int speed;
	public fruit(int x, int y, int width, int height) {
		super( x, y,  width,  height);
		speed = 1;
	}
	public void update() {
		y += speed;
		super.update();
	}
	public void draw(Graphics g) {
		
			
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
	}
}
