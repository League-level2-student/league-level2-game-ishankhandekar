package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Powerups extends GameObject{
	final String[] powerupTypes = {"Magnet","Time Changer","larger collisionbox"};
	
	String whatPowerup;
	static int speed = 1;
	public Powerups(int x, int y, int width, int height) {
		super( x, y,  width,  height);
		whatPowerup = powerupTypes[new Random().nextInt(3)];
		
	}
	public void update() {
		y += speed;
		super.update();
	}
	public void draw(Graphics g) {
			if(whatPowerup.equals("Magnet") ) {
				g.setColor(Color.RED);
			}else if(whatPowerup.equals("Time Changer")){
				g.setColor(Color.GREEN);
			}else if(whatPowerup.equals("larger collisionbox") ) {
				g.setColor(Color.WHITE);
			}
			
			
			g.fillRect(x, y, width, height);
	}
}
