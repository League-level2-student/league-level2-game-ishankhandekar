package game;

import java.awt.Rectangle;

public class GameObject {
	int x;
	 int y;
	 int width;
	 int height;
	 boolean isActive = true;
	 Rectangle collisionBox;
	public GameObject(int x, int y, int width, int height){
		this.collisionBox = new Rectangle(); 
		 this.y = y;
		 this.x = x;
		 this.width = width;
		 this.height = height;
	}
	public void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
