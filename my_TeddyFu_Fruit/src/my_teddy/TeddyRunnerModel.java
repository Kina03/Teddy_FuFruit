package my_teddy;

import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Timer;

public class TeddyRunnerModel {
	
	private int score;
	private Timer timer, appleTimer, enemyTimer,lemonTimer,grapeTimer;
	private Point bgPoint1, bgPoint2;
	private Teddy teddy;
	private CopyOnWriteArrayList<Missle> missles;
	private CopyOnWriteArrayList<Enemy> enemies;
	private CopyOnWriteArrayList<Apple> apples;
	private CopyOnWriteArrayList<Lemon> lemon;
	private CopyOnWriteArrayList<Grape> grape;
	private HealthBar healthBar;
	

	public TeddyRunnerModel() {
		this.score = 0;
		this.timer = new Timer(0, null);
		this.appleTimer = new Timer(0, null);
		this.lemonTimer = new Timer(0, null);
		this.grapeTimer = new Timer(0, null);
		this.enemyTimer = new Timer(0, null);
		this.bgPoint1 = new Point( 0, 0);
		this.bgPoint2 = new Point(0, 0);
		this.teddy = new Teddy();
		this.missles = new CopyOnWriteArrayList<Missle>();
		this.enemies = new CopyOnWriteArrayList<Enemy>();
		this.apples = new CopyOnWriteArrayList<Apple>();
		this.lemon = new CopyOnWriteArrayList<Lemon>();
		this.grape = new CopyOnWriteArrayList<Grape>();
		this.healthBar = new HealthBar();
	}

	public Timer getTimer() {
		return this.timer;
	}


	public void setTimer(Timer timer) {
		this.timer = timer;
	}


	public Timer getAppleTimer() {
		return this.appleTimer;
	}

	public void setAppleTimer(Timer appleTimer) {
		this.appleTimer = appleTimer;
	}


	public void setLemonTimer(Timer lemonTimer) {
		this.lemonTimer = lemonTimer;
	}


	public Timer getLemonTimer() {
		return this.lemonTimer;
	}


	public void setGrapeTimer(Timer grapeTimer) {
		this.grapeTimer = grapeTimer;
	}


	public Timer getGrapeTimer() {
		return this.grapeTimer;
	}


	public Timer getEnemyTimer() {
		return this.enemyTimer;
	}


	public void setEnemyTimer(Timer enemyTimer) {
		this.enemyTimer = enemyTimer;
	}
	
	public Point getBgPoint1() {
		return this.bgPoint1;
	}
	
	public Point getBgPoint2() {
		return this.bgPoint2;
	}
	public Teddy getTeddy() {
		return this.teddy;
	}
	
	public CopyOnWriteArrayList<Apple> getApples() {
		return this.apples;
	}
	
	public CopyOnWriteArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public CopyOnWriteArrayList<Missle> getMissles() {
		return this.missles;
	}

	public CopyOnWriteArrayList<Lemon> getLemon() {
		return this.lemon;
	}
	
	public CopyOnWriteArrayList<Grape> getGrape() {
		return this.grape;
	}
	

	public int getScore() {
		return this.score;
	}
	
	public void addAppleScore() {
		this.score += Constrants.APPLE_SCORE_VALUE;
	}

	public void addLemonScore() {
		this.score += Constrants.LEMON_SCORE_VALUE;
	}

	public void addGrapeScore() {
		this.score += Constrants.GRAPE_SCORE_VALUE;
	}
	
	
	public HealthBar getHealthBar() {
		return this.healthBar;
	}

}
