package my_teddy;

import java.awt.Point;

public class Teddy extends Point{
	
	private static final long serialVersionUID = 1L;

	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private int velY;
	private int velX;
	
	public Teddy() {
		this.setLocation(new Point(0, Constrants.TEDDY_START_Y));
		this.velX = Constrants.TEDDY_X_VELOCITY;
		this.velY = Constrants.TEDDY_Y_VELOCITY;
		this.moveLeft = false;
		this.moveRight = false;
		this.moveUp = false;
	}
	public boolean isMoveRight() {
		return moveRight;
	}
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	public boolean isMoveLeft() {
		return moveLeft;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public boolean isMoveUp() {
		return moveUp;
	}
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
	public void moveLeft() {
		this.setLocation(this.getX() - velX, this.getY());
	}
	public void moveRight() {
		this.setLocation(this.getX() + velX, this.getY());
	}
	public void moveUp() {
		if(this.velY >= -Constrants.TEDDY_Y_VELOCITY) {
			this.setLocation(this.getX(), this.getY() - velY);
			this.velY -= Constrants.TEDDY_GRAVITY;
		}else {
			this.moveUp = false;
			this.velY = Constrants.TEDDY_Y_VELOCITY;
		}
	}
	public int getVelY() {
		return velY;
	}
}
