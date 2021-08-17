package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class objectManager implements ActionListener {
	Random r;
	ArrayList <fruit> fruits;
	Integer score;
	Farmer farmer;
	public objectManager(Farmer farmer) {
		score = 0;
		r = new Random();
		this.farmer = farmer;
		fruits = new <fruit> ArrayList();
	}
	Integer getScore() {
		return score;
	}
	public void addFruit() {
		fruits.add(new fruit(r.nextInt(myGame.WIDTH),0,50,50));
	
	}

	public void update() {
		
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).update();
			if (fruits.get(i).y >= myGame.HEIGHT) {
			fruits.get(i).isActive = false;
			}
		}
		farmer.update();
		checkCollision();
		purgeObjects();
		
	}
	public void drawFruits(Graphics g) {
		farmer.draw(g);
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).draw(g);
		}	
	}
public void checkCollision() {
		
		for (int i = 0; i < fruits.size(); i++) {
			if (farmer.collisionBox.intersects(fruits.get(i)
					.collisionBox)) {
				fruits.get(i).isActive = false;
				farmer.isActive = false;
				
			}
		}
			}
public void purgeObjects() {
		for (int i = fruits.size()-1; i >= 0; i--) {
			if (fruits.get(i).isActive == false) {
			fruits.remove(i);
			}
		}
		
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	addFruit();
}
	
	
}
