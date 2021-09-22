package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class objectManager implements ActionListener {
	Random r;
	int currentSpeed = 1;
	ArrayList<fruit> fruits;
	ArrayList<hazzards> hazzards;
	ArrayList<Powerups> powerups;
	Integer score;
	Farmer farmer;
	fruit apples;
	hazzards hazzard;
	
	Timer MagnetTimer;
	Timer TimeChangerTimer;
	boolean isMagnetActive = false;
	boolean isTimeChangerActive = false;
	

	public objectManager(Farmer farmer) {
		score = 20;
		r = new Random();
		this.farmer = farmer;
		ActionListener actionListener;
		
		MagnetTimer = new Timer(13000, this);
		TimeChangerTimer = new Timer(13000, this);
		
		
		fruits = new ArrayList<fruit>();
		hazzards = new ArrayList<hazzards>();
		powerups = new ArrayList<Powerups>();
	}

	Integer getScore() {
		return score;
	}

	public void addFruitOrHazzards() {
		int fruitOrHazzardOrPowerups = new Random().nextInt(3);
		if (fruitOrHazzardOrPowerups == 0) {
			fruits.add(new fruit(r.nextInt(myGame.WIDTH), 0, 50, 50));
		} else if (fruitOrHazzardOrPowerups == 1) {
			hazzards.add(new hazzards(r.nextInt(myGame.WIDTH), 0, 50, 50));
		} else if (fruitOrHazzardOrPowerups == 2) {
			powerups.add(new Powerups(r.nextInt(myGame.WIDTH), 0, 50, 50));
		}

	}

	public void update() {
		if (isTimeChangerActive) {
			for (int i = 0; i < fruits.size(); i++) {
				currentSpeed = fruits.get(i).speed;
				fruits.get(i).speed = 1;
				
			}
		}
		if (isMagnetActive) {
			for (int i = 0; i < fruits.size(); i++) {
				if (fruits.get(i).x > farmer.x && (fruits.get(i).x - farmer.x) > 100) {
					fruits.get(i).x -= 3;
				} else if (fruits.get(i).x < farmer.x && (farmer.x - fruits.get(i).x) > 100) {
					fruits.get(i).x += 3;
				}
			}
		}
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
		for (int i = 0; i < powerups.size(); i++) {
			powerups.get(i).update();
			if (powerups.get(i).y >= myGame.HEIGHT) {
				powerups.get(i).isActive = false;
			}
		}
		farmer.update();
		checkCollision();
		purgeObjects();

	}

	public void drawFruitsOrHazzardsOrPowerups(Graphics g) {

		for (int i = 0; i < fruits.size(); i++) {
			fruits.get(i).draw(g);
		}
		for (int i = 0; i < hazzards.size(); i++) {
			hazzards.get(i).draw(g);
		}
		for (int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
		}
		farmer.draw(g);

	}

	public void checkCollision() {

		for (int i = 0; i < fruits.size(); i++) {

			if (farmer.collisionBox.intersects(fruits.get(i).collisionBox)) {
				

				fruits.get(i).isActive = false;
				score++;

			}

		}
		for (int i = 0; i < hazzards.size(); i++) {
			if (farmer.collisionBox.intersects(hazzards.get(i).collisionBox)) {

				hazzards.get(i).isActive = false;
				if (hazzards.get(i).whatHazzard.equals("Stick")) {
					score -= 5;
				} else if (hazzards.get(i).whatHazzard.equals("Rock")) {
					score -= 8;
				} else if (hazzards.get(i).whatHazzard.equals("Rotten Fruit")) {
					score -= 3;
				}

			}

		}
		for (int i = 0; i < powerups.size(); i++) {
			if (farmer.collisionBox.intersects(powerups.get(i).collisionBox)) {
				powerups.get(i).isActive = false;
				if (powerups.get(i).whatPowerup.equals("Magnet")) {
					isMagnetActive = true;
					MagnetTimer.start();
				} else if (powerups.get(i).whatPowerup.equals("larger collisionbox")) {
					farmer.isLargerCollisionboxActive = true;
					farmer.largerCollisionboxTimer.start();
				} else if (powerups.get(i).whatPowerup.equals("Time Changer")) {
					isTimeChangerActive = true;
					TimeChangerTimer.start();
				}

			}

		}
	}

	public void purgeObjects() {
		for (int i = fruits.size() - 1; i >= 0; i--) {
			if (fruits.get(i).isActive == false) {
				fruits.remove(i);
			}
		}
		for (int i = hazzards.size() - 1; i >= 0; i--) {
			if (hazzards.get(i).isActive == false) {
				hazzards.remove(i);
			}
		}
		for (int i = powerups.size() - 1; i >= 0; i--) {
			if (powerups.get(i).isActive == false) {
				powerups.remove(i);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		addFruitOrHazzards();
		if (e.getSource() == farmer.largerCollisionboxTimer) {
			farmer.largerCollisionboxTimer.stop();
		}
		if (e.getSource() == MagnetTimer) {
			MagnetTimer.stop();
			isMagnetActive = false;
		}
		if (e.getSource() == TimeChangerTimer) {
			TimeChangerTimer.stop();
			isTimeChangerActive = false;
						 
		}
	}

}
