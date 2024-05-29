package pepse;

import java.awt.*;

/**
 * This class holds constants used throughout the game.
 */
public class Constants {
    /**
     * Sky Color
     */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");

    /**
     * Block Size
     */
    public static final int BLOCK_SIZE = 30;

    /**
     * Ground Color
     */
    public static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);

    /**
     * Sun Halo Color
     */
    public static final Color SUN_HALO_COLOR = new Color(255, 255, 0, 20);

    /**
     * Terrain Depth
     */
    public static final int TERRAIN_DEPTH = 20;

    /**
     * Day Period
     */
    public static final float DAY_PERIOD = 30;

    /**
     * Opacity at Noon
     */
    public static final float NOON_OPACITY = 0;

    /**
     * Opacity at Midnight
     */
    public static final float MIDNIGHT_OPACITY = 0.5f;

    /**
     * Sun Size
     */
    public static final float SUN_SIZE = 100;

    /**
     * Min Angle
     */
    public static final float MIN_ANGLE = 0;

    /**
     * Max Angle
     */
    public static final float MAX_ANGLE = 360;

    /**
     * Max Angle for Leafs
     */
    public static final float MAX_ANGLE_FOR_LEAFS = 90;

    /**
     * Player Path
     */
    public static final String PLAYER_PATH = "src/assets/assets/idle_0.png";

    /**
     * Max HP
     */
    public static final float MAX_HP = 100;

    /**
     * Min HP
     */
    public static final float MIN_HP = 0;

    /**
     * HP Gain While Idle
     */
    public static final float IDLE_HP_GAIN = 1;

    /**
     * Min HP to Jump
     */
    public static final float MIN_HP_TO_JUMP = 10;

    /**
     * HP Lose While Jumping
     */
    public static final float JUMP_HP_LOSE = -10;

    /**
     * HP Lose While Moving
     */
    public static final float MOVE_HP_LOSE = -0.5f;

    /**
     * Min HP to Move
     */
    public static final float MIN_HP_TO_MOVE = 0.5f;

    /**
     * Number Size
     */
    public static final int NUMBER_SIZE = 40;

    /**
     * Idle Animation Time Lapse
     */
    public static final double IDLE_TIME_LAP = 0.4;

    /**
     * Jump Animation Time Lapse
     */
    public static final double JUMP_TIME_LAP = 0.2;

    /**
     * Run Animation Time Lapse
     */
    public static final double RUN_TIME_LAP = 0.2;

    /**
     * Tree Width
     */
    public static final float TREE_WIDTH = 30;

    /**
     * Tree Height
     */
    public static final float TREE_HEIGHT = 210;

    /**
     * Tree Plant Value
     */
    public static final double TREE_PLANT_VALUE = 0.002;

    /**
     * Tree Color
     */
    public static final Color TREE_COLOR = new Color(100, 50, 20);

    /**
     * Leaf Color
     */
    public static final Color LEAF_COLOR = new Color(50, 200, 30);

    /**
     * Number of Leafs
     */
    public static final int NUMBER_OF_LEAFS = 50;

    /**
     * Tree Leafs Radius
     */
    public static final float TREE_LEAFS_RADIUS = 90;

    /**
     * Avatar Size
     */
    public static final float AVATAR_SIZE = 50;

    /**
     * Fruit Tag
     */
    public static final String FRUIT_TAG = "fruit";

    /**
     * Fruit Gain HP
     */
    public static final float FRUIT_GAIN_HP = 10;

    /**
     * Tree Fruits Radius
     */
    public static final float TREE_FRUITS_RADIUS = 30;

    /**
     * Fruit Color
     */
    public static final Color FRUIT_COLOR = new Color(250, 50, 50);

    /**
     * Number of Fruits
     */
    public static final int NUMBER_OF_FRUITS = 10;

    /**
     * Variant Fruit Color
     */
    public static final Color FRUIT_COLOR_VARIANT = new Color(250, 150, 50);

    /**
     * Leafs Rotate Period
     */
    public static final float LEAFS_ROTATE_PERIOD = 3;

    /**
     * Variant Tree Color
     */
    public static final Color TREE_COLOR_VARIANT = new Color(143, 102, 63);
    /**
     * Max value for the ground seed
     */
    public static final int MAX_SEED = 1500;
    /**
     * Min value for the ground seed
     */
    public static final int MIN_SEED = 100;
    /**
     * Path for IDLE Animation sprite 0
     */
    public static final String IDLE_0 = "src/assets/assets/idle_0.png";
    /**
     * Path for IDLE Animation sprite 1
     */
    public static final String IDLE_1 = "src/assets/assets/idle_1.png";
    /**
     * Path for IDLE Animation sprite 2
     */
    public static final String IDLE_2 = "src/assets/assets/idle_2.png";
    /**
     * Path for IDLE Animation sprite 3
     */
    public static final String IDLE_3 = "src/assets/assets/idle_3.png";
    /**
     * Path for JUMP Animation sprite 0
     */
    public static final String JUMP_0 = "src/assets/assets/jump_0.png";
    /**
     * Path for JUMP Animation sprite 1
     */
    public static final String JUMP_1 = "src/assets/assets/jump_1.png";
    /**
     * Path for JUMP Animation sprite 2
     */
    public static final String JUMP_2 = "src/assets/assets/jump_2.png";
    /**
     * Path for JUMP Animation sprite 3
     */
    public static final String JUMP_3 = "src/assets/assets/jump_3.png";
    /**
     * Path for RUN Animation sprite 0
     */
    public static final String RUN_0 = "src/assets/assets/run_0.png";
    /**
     * Path for RUN Animation sprite 1
     */
    public static final String RUN_1 = "src/assets/assets/run_1.png";
    /**
     * Path for RUN Animation sprite 2
     */
    public static final String RUN_2 = "src/assets/assets/run_2.png";
    /**
     * Path for RUN Animation sprite 3
     */
    public static final String RUN_3 = "src/assets/assets/run_3.png";
}
