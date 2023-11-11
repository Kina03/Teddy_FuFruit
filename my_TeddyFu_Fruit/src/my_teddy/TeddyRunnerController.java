package my_teddy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TeddyRunnerController {
	
	private final TeddyRunnerView view;
	private final TeddyRunnerModel model;

	private boolean inGame;
	private GameState gameState = GameState.HOME;

	public enum GameState { 
        HOME, PLAYING, GAME_OVER;
    }

	//Note: Is running two swing timers the best solution for two independent events
	// (moving the background, moving Teddy, moving apple, spawning apple etc...)
	public TeddyRunnerController(TeddyRunnerView view, TeddyRunnerModel model) {
		inGame = true;
		gameState = GameState.HOME;
		this.view = view;
		this.model = model;

		this.model.getBgPoint2().x = this.view.getBg2().getWidth(null);
		this.view.addPanel(new Panel());
		
		this.model.setTimer(new Timer(Constrants.MAIN_TIME_DELAY, new MainTimer()));
		this.view.addKeyListener(new TeddyKeyListener());
		
		this.model.setAppleTimer(new Timer(Constrants.APPLE_SPAWN_DELAY, new AppleSpawner()));
		this.model.getAppleTimer().start();

		this.model.setLemonTimer(new Timer(Constrants.LEMON_SPAWN_DELAY, new LemonSpawner()));
		this.model.getLemonTimer().start();

		this.model.setGrapeTimer(new Timer(Constrants.GRAPE_SPAWN_DELAY, new GrapeSpawner()));
		this.model.getGrapeTimer().start();
		
		this.model.setEnemyTimer(new Timer(Constrants.ENEMY_SPAWN_DELAY, new EnemySpawner()));
		this.model.getEnemyTimer().start();

		this.model.getTimer().start();
		this.view.addPanel(new Panel());
		view.setVisible(true);	
	}
	
	public class Panel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			switch (gameState) {
                case HOME:
					drawHome(g);
					
					startGame();
                    
                    break;
				case PLAYING:
					gameState = GameState.PLAYING;
					startGame();
                    
					drawGame(g);

				break;
                case GAME_OVER:

					drawGameOver(g);
					break;

			}
			view.repaint();
			
		}
		private void startGame() {
			
			model.getTimer().start();
			model.getAppleTimer().start();
			model.getLemonTimer().start();
			model.getEnemyTimer().start();
		}
	
//Game
		private void drawGame(Graphics g){
			if(inGame) {
				g.drawImage(view.getBg1(), model.getBgPoint1().x, model.getBgPoint1().y, 
				view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
				g.drawImage(view.getBg2(), model.getBgPoint2().x, model.getBgPoint2().y, 
				view.getBg2().getWidth(null), view.getBg2().getHeight(null), null);
				
				drawHealthBar(g);
				drawApple(g);
				drawLemon(g);
				drawGrape(g);
				drawEnemy(g);
				
				g.drawImage(view.getCurrTeddyImage(), model.getTeddy().getLocation().x, model.getTeddy()
				.getLocation().y, view.getTeddyImage().getWidth(null), view.getTeddyImage()
				.getHeight(null), null);
				
				for(Missle missle : model.getMissles()) {
					g.drawImage(view.getMissleImage(), missle.getLocation().x, missle.getLocation()
					.y, missle.getMissleLength(), missle.getMissleLength(), null);
				}
				
				drawScore(g);
			}
		}
//Gameover
		private void drawGameOver(Graphics g){
			this.setLayout(null);

            g.drawImage(view.getGover(), 0, 0, 900, 600, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.BOLD, 40));
            g.drawString("Score: " + model.getScore(), 340, Constrants.FRAME_HEIGHT / 2 + 100);
        }
    
//Home
		private void drawHome(Graphics g) {
			g.drawImage(view.getHomebg(), 0, 0, Constrants.FRAME_WIDTH, Constrants.FRAME_HEIGHT, 
			null);
			g.drawImage(view.getBstart(), Constrants.START_BUTTON_X, Constrants.START_BUTTON_Y, 
			Constrants.START_BUTTON_WIDTH, Constrants.START_BUTTON_HEIGHT, null);
		}

//HealthBar
		private void drawHealthBar(Graphics g) {
			// Draw Red Health Bar
			g.setColor(Constrants.RED_COLOR);
			g.fillRect(Constrants.HEALTHBAR_X, Constrants.HEALTHBAR_Y, Constrants.HEALTHBAR_WIDTH,
			 Constrants.HEALTHBAR_HEIGHT);
			
			// Draw Green Health Bar Over Top
			g.setColor(Constrants.GREEN_COLOR);
			g.fillRect(model.getHealthBar().getX(), Constrants.HEALTHBAR_Y, (int) (model.getHealthBar()
			.getHealthPercentage() * Constrants.HEALTHBAR_WIDTH), Constrants.HEALTHBAR_HEIGHT);
			
			g.setFont(new Font("Monospaced", Font.BOLD, Constrants.HEALTHBAR_FONT_SIZE));
			
			long healthPercentage = (long) (model.getHealthBar().getHealthPercentage() * 100);

			if(healthPercentage < 50 && healthPercentage > 0) {
				g.setColor(Constrants.RED_COLOR);
			}else if(healthPercentage <= 0) {
				g.setColor(Constrants.RED_COLOR);
				inGame = false;
			}
			
			String str = healthPercentage +"%";
			int strLength = g.getFontMetrics().stringWidth(str);
			g.drawString(str, Constrants.HEALTHBAR_PERCENT_X - strLength, Constrants.HEALTHBAR_PERCENT_Y);
	
		}
//Score
		private void drawScore(Graphics g) {
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			g.setFont(new Font("Monospaced", Font.BOLD, Constrants.SCORE_FONT_SIZE));
			g.setColor(Constrants.W_COLOR);
			g.drawString("Score: "+model.getScore(), Constrants.SCORE_X, Constrants.SCORE_Y);
		}
//Apple		
		private void drawApple(Graphics g) {
			for(Apple apple : model.getApples()) {
				((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, apple.getAlpha()));
				g.drawImage(view.getAppleImage(), apple.getLocation().x, apple.getLocation().y, view.getAppleImage().getWidth(null), view.getAppleImage().getHeight(null), null);
}
			((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
//Lemon
		private void drawLemon(Graphics g) {
			for(Lemon lemon : model.getLemon()) {
				((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lemon.getAl()));
				g.drawImage(view.getlemonImage(), lemon.getLocation().x, lemon.getLocation().y, view.getlemonImage().getWidth(null), view.getlemonImage().getHeight(null), null);
			}
			((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
//Grap
		private void drawGrape(Graphics g) {
			for(Grape grape : model.getGrape()) {
				((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, grape.getAlpha()));
				g.drawImage(view.getgrapeImage(), grape.getLocation().x, grape.getLocation().y, view.getgrapeImage().getWidth(null), view.getlemonImage().getHeight(null), null);
			}
			((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}

//Enemy		
		private void drawEnemy(Graphics g) {
			for(Enemy enemy : model.getEnemies()) {
				int shrinkFactor = Constrants.ENEMY_SIZE_FACTOR * (Constrants.ENEMY_HEALTH_MAX - enemy.getHealth());
				g.drawImage(view.getCurrEnemyImage(), enemy.getLocation().x , enemy.getLocation().y + (shrinkFactor), view.getEnemyImage().getWidth(null) - shrinkFactor, view.getEnemyImage().getHeight(null) - shrinkFactor, null);			
			}
		}
	}
//Maintimer	
	public class MainTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			inGame();
			moveBackground();
			moveTeddy();
			moveLemon();
			moveApple();
			moveGrape();
			moveMissle();
			moveEnemy();
			checkTeddyEnemyCollision();
			view.repaint();	
		}
		private void inGame() {
			if(!inGame) {
				model.getAppleTimer().stop();
				model.getLemonTimer().stop();
				model.getEnemyTimer().stop();
				model.getTimer().stop();
				gameState = GameState.GAME_OVER;
			}
		}
	}
//Teddykey	
	public class TeddyKeyListener implements KeyListener{
		boolean alreadyPressed = false;	
		@Override
		public void keyPressed(KeyEvent e) {
			gameState = GameState.PLAYING;
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				model.getTeddy().setMoveUp(true);
				break;
			case KeyEvent.VK_LEFT:
				model.getTeddy().setMoveLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				model.getTeddy().setMoveRight(true);
			case KeyEvent.VK_SPACE:
				if(!alreadyPressed) {
					fireMissle();
				}
				alreadyPressed = true;
				break;
			default:
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			//Move Up is handled in Teddy Class.
			case KeyEvent.VK_LEFT:
				model.getTeddy().setMoveLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				model.getTeddy().setMoveRight(false);
				break;
			case KeyEvent.VK_SPACE:
				alreadyPressed = false;
				break;
			default:
				break;
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub	
		}
	}
//AppleSpawner
	class AppleSpawner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(new Random().nextInt(5) + 1 == 5) {
				model.getApples().add(new Apple());
			}
		}
	}
//LemonSpawner		
	class LemonSpawner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(new Random().nextInt(3) + 1 == 3) {
				model.getLemon().add(new Lemon());
			}
		}
	}

//GrapeSpawner
	class GrapeSpawner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(new Random().nextInt(6) + 1 == 6) {
				model.getGrape().add(new Grape());
			}
		}
		
	}

//EnemySpawner	
	class EnemySpawner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model.getEnemies().add(new Enemy());
		}
		
	}
//MoveBg	
	private void moveBackground() {
		model.getBgPoint1().x -= Constrants.BG_DX;
		model.getBgPoint2().x -= Constrants.BG_DX;
		
		if(model.getBgPoint1().x <= view.getBg1().getWidth(null) * - 1) {
			model.getBgPoint1().x = view.getBg1().getWidth(null);
		}else if (model.getBgPoint2().x <= view.getBg2().getWidth(null) * - 1) {
			model.getBgPoint2().x = view.getBg2().getWidth(null);
		}
	}

//MoveTeddy
	private void moveTeddy() {
		if(model.getTeddy().isMoveLeft()) {
			if(model.getTeddy().getLocation().x <= 0) {
				model.getTeddy().setMoveLeft(false);
				return;
			}
			if(model.getTeddy().getLocation().x % 3 == 0) {
				view.switchCurrTeddyImage();
			}
			model.getTeddy().moveLeft();

		}else if(model.getTeddy().isMoveRight()) {
			
			if(model.getTeddy().getLocation().x >= Constrants.FRAME_WIDTH - 
			view.getTeddyImage().getWidth(null)) {
				model.getTeddy().setMoveRight(false);
				return;
			}
			if(model.getTeddy().getLocation().x % 3 == 0) {
				view.switchCurrTeddyImage();
			}
			model.getTeddy().moveRight();
		}
		if(model.getTeddy().isMoveUp()) {
			model.getTeddy().moveUp();
		}
	}
//moveapple
	private void moveApple() {
		int index = 0;
		for(Apple apple : model.getApples()) {
			apple.moveLeft();
			checkAppleTeddyCollision();
			
			if(apple.isEaten()) {
				apple.setLocation(apple.getX(), apple.getY() - Constrants.APPLE_EATEN_DY);
				apple.setAlpha(apple.getAlpha() - Constrants.APPLE_ALPHA_INCREMENT);
				if(apple.getAlpha() < 0) {
					model.getApples().remove(index);
				}
			}
			index++;
		}
		
		if(!model.getApples().isEmpty()) {
			if(model.getApples().get(0).getLocation().x < -1 * 
			view.getAppleImage().getWidth(null)) {
				model.getApples().remove(0);
			}
		}
	}

//movelemon
	private void moveLemon(){
		int index = 0;
		for(Lemon lemon  : model.getLemon()) {
			lemon.moveLeft();
			checkLemonTeddyCollision();
			if(lemon.isEaten()){
				lemon.setLocation(lemon.getX(), lemon.getY()-Constrants.LEMON_EATEN_DY);
				lemon.setAl(lemon.getAl()-Constrants.LEMON_ALPHA_INCREMENT);
				if(lemon.getAl()<0){
					model.getLemon().remove(index);
				}
			}
			index++;
		}
		if(!model.getLemon().isEmpty()){
			if(model.getLemon().get(0).getLocation().x<-1*
			view.getlemonImage().getWidth(null)){
				model.getLemon().remove(0);
			}
		}
	}

//moveGrape
	private void moveGrape(){
		int index = 0;
		for(Grape grape  : model.getGrape()) {
			grape.moveLeft();
			checkGrapeTeddyCollision();
			if(grape.isEaten()){
				grape.setLocation(grape.getX(), grape.getY()-Constrants.GRAPE_EATEN_DY);
				grape.setAlpha(grape.getAlpha()-Constrants.GRAPE_ALPHA_INCREMENT);
				if(grape.getAlpha()<0){
					model.getGrape().remove(index);
				}
			}
			index++;
		}
		if(!model.getGrape().isEmpty()){
			if(model.getGrape().get(0).getLocation().x<-1*
			view.getlemonImage().getWidth(null)){
				model.getGrape().remove(0);
			}
		}
	}

//checkAppleTeddyCollision
	private void checkAppleTeddyCollision() {
		int index = 0;
		
		for(Apple apple : model.getApples()) {
			int appleTeddyDistanceX = Math.abs((model.getTeddy().getLocation().x - apple.getLocation().x));
			int appleTeddyDistanceY = Math.abs((model.getTeddy().getLocation().y - apple.getLocation().y));
			int appleWidth = view.getAppleImage().getWidth(null);
			int appleHeight = view.getAppleImage().getHeight(null);
			
			if(appleTeddyDistanceY <= appleHeight && appleTeddyDistanceX < appleWidth) {
				if(!apple.isEaten()) {
					model.addAppleScore();
				}
				model.getApples().get(index).setEaten(true);
			}
			
			index++;
		}
	}
//checkLemonTeddyCollision()
	private void checkLemonTeddyCollision() {
		int index = 0;
		
		for(Lemon lemon : model.getLemon()) {
			int lemonTeddyDistanceX = Math.abs((model.getTeddy().getLocation().x - lemon.getLocation().x));
			int lemonTeddyDistanceY = Math.abs((model.getTeddy().getLocation().y - lemon.getLocation().y));
			int lemonWidth = view.getlemonImage().getWidth(null);
			int lemonHeight = view.getlemonImage().getHeight(null);
			
			if(lemonTeddyDistanceY <= lemonHeight && lemonTeddyDistanceX < lemonWidth) {
				if(!lemon.isEaten()) {
					model.addLemonScore();
				}
				model.getLemon().get(index).setEaten(true);
			}
			
			index++;
		}
	}

//checkGrapeTeddyCollision()
	private void checkGrapeTeddyCollision() {
		int index = 0;
		
		for(Grape grape : model.getGrape()) {
			int grapeTeddyDistanceX = Math.abs((model.getTeddy().getLocation().x - grape.getLocation().x));
			int grapeTeddyDistanceY = Math.abs((model.getTeddy().getLocation().y - grape.getLocation().y));
			int grapeWidth = view.getlemonImage().getWidth(null);
			int grapeHeight = view.getlemonImage().getHeight(null);
			
			if(grapeTeddyDistanceY <= grapeHeight && grapeTeddyDistanceX < grapeWidth) {
				if (!grape.isEaten()) {
					model.addGrapeScore(); // เพิ่มคะแนนองุ่น
				}
				model.getGrape().get(index).setEaten(true);
			}
			index++;
		}
	}


//checkMissleEnemyCollisio
	private void checkMissleEnemyCollision(Enemy enemy, int j) {
		int i = 0;
		for(Missle missle : model.getMissles()) {
			int enemyMissleDistanceX = Math.abs(missle.getLocation().x - enemy.getLocation().x);
			int enemyMissleDistanceY = Math.abs(missle.getLocation().y - enemy.getLocation().y);
			int missleWidth = view.getMissleImage().getWidth(null);
			int enemyHeight = view.getMissleImage().getHeight(null);
			
			if(enemyMissleDistanceX <= missleWidth / 2 && enemyMissleDistanceY <= enemyHeight) {
				enemy.setHealth(enemy.getHealth() - 1);
				model.getMissles().remove(i);
				
				if(enemy.getHealth() <= 0) {
					model.getEnemies().remove(j);
				}
			}
			
			i++;
		}
	}
//checkTeddyEnemyCollision()
	private void checkTeddyEnemyCollision() {
		
		for(Enemy enemy : model.getEnemies()) {
			int enemyTeddyDistanceX = Math.abs(model.getTeddy().getLocation().x - enemy.getLocation().x);
			int enemyTeddyDistanceY = Math.abs(model.getTeddy().getLocation().y - enemy.getLocation().y);
			int enemyWidth = view.getEnemyImage().getWidth(null);
			int enemyHeight = view.getEnemyImage().getHeight(null);
			
			if(enemyTeddyDistanceX < enemyWidth && enemyTeddyDistanceY <= enemyHeight) {
				if(model.getTeddy().getVelY() < 0) {
					enemy.setJumpedOn(true);
				}
				
				if(!enemy.isJumpedOn()) {
					model.getHealthBar().removeHealth();
				}
			}
		}
	}
//move missle
	private void moveMissle() {
		
		for(Missle missle : model.getMissles()) {
			missle.moveRight();
		}
		
		if(!model.getMissles().isEmpty()) {
			if(model.getMissles().get(0).getLocation().x > Constrants.FRAME_WIDTH) {
				model.getMissles().remove(0);
			}
		}
	}
//fireMissle
	private void fireMissle() {
		int x = model.getTeddy().getLocation().x + view.getTeddyImage().getWidth(null);
		int y = model.getTeddy().getLocation().y + Constrants.MISSLE_Y_OFFSET;
		
		model.getMissles().add(new Missle(x, y));
	}
//moveenemy	
	private void moveEnemy() {
		int index = 0;
		
		for(Enemy enemy : model.getEnemies()) {
			enemy.moveLeft();
			
			checkMissleEnemyCollision(enemy, index);
			
			if(enemy.isJumpedOn()) {
				enemy.setLocation(enemy.getX(), enemy.getY() + Constrants.ENEMY_JUMPED_ON_DY);
				if(enemy.getLocation().y > Constrants.FRAME_HEIGHT) {
					model.getEnemies().remove(index);
				}
			}
			
			index++;
		}
		
		if(!model.getEnemies().isEmpty()) {
			if(model.getEnemies().get(0).getLocation().x % 20 == 0) {
				view.switchCurrEnemyImage();
			}
			
			if(model.getEnemies().get(0).getLocation().x < -1 * view.getEnemyImage().getWidth(null)) {
				model.getEnemies().remove(0);
			}
		}
	}

}