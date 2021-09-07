package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class objectManager implements ActionListener {
	Random r;
	ArrayList <fruit> fruits;
	ArrayList <hazzards> hazzards;
	Integer score;
	Farmer farmer;
	fruit apples;
	hazzards hazzard;
	public objectManager(Farmer farmer) {
		score = 20;
		r = new Random();
		this.farmer = farmer;
		fruits = new  ArrayList<fruit>();
		hazzards = new  ArrayList<hazzards>();
	}
	Integer getScore() {
		return score;
	}
	public void addFruitOrHazzards() {
		int fruitOrHazzard = new Random().nextInt(2);
		if(fruitOrHazzard == 0) {
			fruits.add(new fruit(r.nextInt(myGame.WIDTH),0,50,50));
		}else if(fruitOrHazzard == 1) {
			hazzards.add(new hazzards(r.nextInt(myGame.WIDTH),0,50,50));
		}
		
		
	}

	public void update() {
		
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).update();
			if (fruits.get(i).y >= myGame.HEIGHT) {
			fruits.get(i).isActive = false;
			}
		}
		for (int i = 0; i < hazzards.size(); i++) {
			hazzards.get(i).update();
			if (hazzards.get(i).y >= myGame.HEIGHT) {
			hazzards.get(i).isActive = false;
			}
		}
		farmer.update();
		checkCollision();
		purgeObjects();
		
	}
	public void drawFruitsOrHazzards(Graphics g) {
		
		
		
		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).draw(g);
		}
		for (int i = 0; i < hazzards.size(); i++) {
			hazzards.get(i).draw(g);
		}
		farmer.draw(g);
			
	}
public void checkCollision() {
		
		for (int i = 0; i < fruits.size(); i++) {
			if (farmer.collisionBox.intersects(fruits.get(i)
					.collisionBox)) {
				fruits.get(i).isActive = false;
				score++;
				
			}
			
		}
		for (int i = 0; i < hazzards.size(); i++) {
			if (farmer.collisionBox.intersects(hazzards.get(i)
					.collisionBox)) {
				hazzards.get(i).isActive = false;
				if (hazzards.get(i).whatHazzard.equals("Stick")) {
					score -= 5;
				}else if(hazzards.get(i).whatHazzard.equals("Rock")) {
					score -= 8;
				}else if(hazzards.get(i).whatHazzard.equals("Rotten Fruit")) {
					score -= 3;
				}
				
				
			}
			
		}
			}
public void purgeObjects() {
		for (int i = fruits.size()-1; i >= 0; i--) {
			if (fruits.get(i).isActive == false) {
			fruits.remove(i);
			}
		}
		for (int i = hazzards.size()-1; i >= 0; i--) {
			if (hazzards.get(i).isActive == false) {
			hazzards.remove(i);
			}
		}
		
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
		addFruitOrHazzards();
	
	
	
	
}
	
	
}
