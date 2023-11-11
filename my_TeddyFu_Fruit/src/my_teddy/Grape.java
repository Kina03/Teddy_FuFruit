package my_teddy;

import java.awt.Point;
import java.util.Random;

public class Grape extends Point{
	
	private static final long serialVersionUID = 1L;
	
	private boolean eaten;
	private float alpha;

	public Grape() {	
		this.alpha = 1.0f;
		int startY = Constrants.GRAPE_START_Y;
		if(new Random().nextInt(2) + 1 == 2) {
			startY = Constrants.GRAPE_START_Y_2;
		}
		this.setLocation(new Point(Constrants.GRAPE_START_X, startY));
	}
	
	public void moveLeft() {
		this.setLocation(this.getX() - Constrants.GRAPE_X_VELOCITY, this.getY());
	}
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
}
