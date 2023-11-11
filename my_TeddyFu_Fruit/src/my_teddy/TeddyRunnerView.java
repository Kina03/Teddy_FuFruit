package my_teddy;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TeddyRunnerView extends JFrame{
	private static final long serialVersionUID = 1L;

	private BufferedImage bg1, bg2,bg3,bg4, homebg,gover;
	private BufferedImage TeddyImage, TeddyImage2, TeddyImage3;
	private BufferedImage currTeddyImage;
	private BufferedImage appleImage,lemonImage,grapeImage;
	private BufferedImage missleImage;
	private BufferedImage enemyImage, enemyImage2;
	private BufferedImage currEnemyImage;
	private BufferedImage Bstart;

	TeddyRunnerView view;
	public enum GameState{
		HOME,PLAYING,GAME_OVER;
	}
	
	public TeddyRunnerView() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Teddy Fu Furit");
		this.setResizable(false);
		this.getContentPane().setPreferredSize(new Dimension
		(Constrants.FRAME_WIDTH, Constrants.FRAME_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.view = this;
		
		this.bg1 = null;
		this.bg2 = null;
		this.bg3 = null;
		this.bg4 = null;
		this.homebg = null;
		this.gover = null;
		this.Bstart = null;
		this.TeddyImage = null;
		this.TeddyImage2 = null;
		this.TeddyImage3 = null;
		this.appleImage = null;
		this.lemonImage = null;
		this.grapeImage = null;
		this.missleImage = null;
		this.enemyImage = null;
		this.enemyImage2 = null;

		
		try {
			this.bg1 = ImageIO.read(new File("images/bg1.png"));
			this.bg2 = ImageIO.read(new File("images/bg1.png"));
			this.bg3 = ImageIO.read(new File("images/bg2.png"));
			this.bg4 = ImageIO.read(new File("images/bg2.png"));
			this.homebg = ImageIO.read(new File("images/home.png"));
			this.gover = ImageIO.read(new File("images/gover.png"));
			this.Bstart = ImageIO.read(new File("images/BStart.png"));
			this.TeddyImage = ImageIO.read(new File("images/teddy1.png"));
			this.TeddyImage2 = ImageIO.read(new File("images/teddy2.png"));
			this.TeddyImage3 = ImageIO.read(new File("images/teddy3.png"));
			this.appleImage = ImageIO.read(new File("images/Apple.png"));
			this.lemonImage = ImageIO.read(new File("images/lemon.png"));
			this.grapeImage = ImageIO.read(new File("images/grape.png"));
			this.missleImage = ImageIO.read(new File("images/mis.png"));
			this.enemyImage = ImageIO.read(new File("images/obs1.png"));
			this.enemyImage2 = ImageIO.read(new File("images/obs2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.currTeddyImage = this.TeddyImage;
		this.currEnemyImage = this.enemyImage;
	
	}
	
	public Image getBg1() {
		return this.bg1;
	}
	public Image getBg2() {
		return this.bg2;
	}
		public Image getBg3() {
		return this.bg3;
	}
	public Image getBg4() {
		return this.bg4;
	}
	public Image getHomebg() {
		return this.homebg;
	}
	public Image getGover() {
		return this.gover;
	}
	public Image getBstart() {
		return this.Bstart;
	}
	public Image getTeddyImage() {
		return this.TeddyImage;
	}
	public Image getTeddyImage2() {
		return this.TeddyImage2;
	}
	public Image getCurrTeddyImage() {
		return this.currTeddyImage;
	}
	public Image getlemonImage() {
		return this.lemonImage;
	}
	public Image getgrapeImage() {
		return this.grapeImage;
	}
	public Image getAppleImage() {
		return this.appleImage;
	}
	public Image getMissleImage() {
		return this.missleImage;
	}
	public Image getEnemyImage() {
		return this.enemyImage;
	}
	public Image getEnemyImage2() {
		return this.enemyImage2;
	}
	public Image getCurrEnemyImage() {
		return this.currEnemyImage;
	}
	public void switchCurrEnemyImage() {
		if(this.currEnemyImage == this.enemyImage) {
			this.currEnemyImage = this.enemyImage2;
		}else {
			this.currEnemyImage = this.enemyImage;
		}
	}
	public void switchCurrTeddyImage() {
		if(this.currTeddyImage == this.TeddyImage) {
			this.currTeddyImage = this.TeddyImage2;
		}else if(this.currTeddyImage == this.TeddyImage2) {
			this.currTeddyImage = this.TeddyImage3;
		}else {
			this.currTeddyImage = this.TeddyImage;
		}
	}
	
	public 	void addPanel(JPanel panel) {
		this.add(panel);
	}
	
	public static void main(String[] args) {
		TeddyRunnerModel model = new TeddyRunnerModel();
		TeddyRunnerView view = new TeddyRunnerView();
		TeddyRunnerController controller = new TeddyRunnerController(view, model);

		view.setVisible(true);
	}

}
