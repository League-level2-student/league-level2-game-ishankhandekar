package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class hazzards extends GameObject  {
	
	 final String[] hazzardTypes = {"Stick","Rock","Rotten Fruit"};
	 final String[] hazzardPoints = {"5","8","3"};
	String whatHazzard;
	static int speed = 1;
	public hazzards(int x, int y, int width, int height) {
		super( x, y,  width,  height);
		whatHazzard = hazzardTypes[new Random().nextInt(3)];
		
	}
	public void update() {
		y += speed;
		super.update();
	}
	public void draw(Graphics g) {
			if(whatHazzard.equals("Stick") ) {
				g.setColor(Color.LIGHT_GRAY);
			}else if(whatHazzard.equals("Rock")){
				g.setColor(Color.darkGray);
			}else if(whatHazzard.equals("Rotten Fruit") ) {
				g.setColor(Color.orange);
			}
			
			
			g.fillRect(x, y, width, height);
	}
}
