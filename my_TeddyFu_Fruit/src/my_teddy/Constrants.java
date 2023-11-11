package my_teddy;

import java.awt.*;

public final class Constrants {
	private Constrants() {
		throw new UnsupportedOperationException();
	}
	
	public static final int MAIN_TIME_DELAY = 15;
	
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 600;
	public static final int SCORE_FONT_SIZE = 24;
	
	public static final int BG_DX = 2;
	
	public static final Color W_COLOR = new Color(0,0,0);
	public static final Color GREEN_COLOR = new Color(0, 176, 80);
	public static final Color RED_COLOR = new Color(237, 27, 36);
	
	public static final int SCORE_X = 10;
	public static final int SCORE_Y = 25;
	
	public static final int TEDDY_GRAVITY = 1;
	public static final int TEDDY_START_Y = 380;
	public static final int TEDDY_X_VELOCITY = 4;
	public static final int TEDDY_Y_VELOCITY = 20;
	
	public static final int APPLE_SPAWN_DELAY = 1000;
	public static final int APPLE_SCORE_VALUE = 10;
	public static final int APPLE_X_VELOCITY = 4;
	public static final int APPLE_START_Y = 225;
	public static final int APPLE_START_Y_2 = 280;
	public static final int APPLE_START_X = 800;
	public static final int APPLE_EATEN_DY = 5;
	public static final float APPLE_ALPHA_INCREMENT = 0.2f;

	public static final int LEMON_SPAWN_DELAY = 1000;
	public static final int LEMON_SCORE_VALUE = 7;
	public static final int LEMON_X_VELOCITY = 3;
	public static final int LEMON_START_Y = 270;
	public static final int LEMON_START_Y_2 = 300;
	public static final int LEMON_START_X = 800;
	public static final int LEMON_EATEN_DY = 9;
	public static final float LEMON_ALPHA_INCREMENT = 0.04f;

	public static final int GRAPE_SPAWN_DELAY = 1000;
	public static final int GRAPE_SCORE_VALUE = 5;
	public static final int GRAPE_X_VELOCITY = 3;
	public static final int GRAPE_START_Y = 300;
	public static final int GRAPE_START_Y_2 = 320;
	public static final int GRAPE_START_X = 800;
	public static final int GRAPE_EATEN_DY = 5;
	public static final float GRAPE_ALPHA_INCREMENT = 0.04f;

	public static final int HONEY_SPAWN_DELAY = 1000;
	public static final int HONEY_SCORE_VALUE = 5;
	public static final int HONEY_X_VELOCITY = 3;
	public static final int HONEY_START_Y = 270;
	public static final int HONEY_START_Y_2 = 300;
	public static final int HONEY_START_X = 800;
	public static final int HONEY_EATEN_DY = 5;
	public static final float HONEY_ALPHA_INCREMENT = 0.04f;
	
	public static final int MISSLE_X_VELOCITY = 10;
	public static final int MISSLE_LENGTH = 70;
	public static final int MISSLE_Y_OFFSET = 50;
	
	public static final int ENEMY_X_VELOCITY = 5;
	public static final int ENEMY_START_X = 1000;
	public static final int ENEMY_START_Y = 430;
	public static final int ENEMY_SPAWN_DELAY = 2000;
	public static final int ENEMY_HEALTH_MAX = 3;
	public static final int ENEMY_SIZE_FACTOR = 7;
	public static final int ENEMY_JUMPED_ON_DY = 7;
	
	public static final int HEALTH_MAX = 100;
	public static final int HEALTHBAR_WIDTH = 200;
	public static final int HEALTHBAR_HEIGHT = 25;
	public static final int HEALTHBAR_X = FRAME_WIDTH - HEALTHBAR_WIDTH - 10;
	public static final int HEALTHBAR_Y = 10;
	public static final int HEALTHBAR_FONT_SIZE = 18;
	public static final int HEALTHBAR_PERCENT_X = Constrants.HEALTHBAR_X - 5;
	public static final int HEALTHBAR_PERCENT_Y = Constrants.HEALTHBAR_Y + 20;

	public static final int STARTING_BLOOD_LEVEL = 100;
    public static final int BLOOD_LEVEL_INCREMENT = 30;	

	public static final int START_BUTTON_X=300;
	public static final int START_BUTTON_Y=190;
	public static final int START_BUTTON_WIDTH=100;
	public static final int START_BUTTON_HEIGHT=100;

	public static final int EXIT_BUTTON_X=390;
	public static final int EXIT_BUTTON_Y=190;
	public static final int EXIT_BUTTON_WIDTH=50;
	public static final int EXIT_BUTTON_HEIGHT=100;
}
