package my_teddy;

import java.awt.Point;
import java.util.Random;

public class Lemon extends Point{
	private boolean eaten;
	private float al;

	public Lemon() {	
		this.al = 1.0f;
		int startY = Constrants.LEMON_START_Y;
		if(new Random().nextInt(2) + 1 == 1) {
			startY = Constrants.LEMON_START_Y_2;
		}
		this.setLocation(new Point(Constrants.LEMON_START_X, startY));
	}
	
	public void moveLeft() {
		this.setLocation(this.getX() - Constrants.LEMON_X_VELOCITY, this.getY());
	}
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	
	public void setAl(float al) {
		this.al = al;
	}
	
	public float getAl() {
		return al;
	}
}

