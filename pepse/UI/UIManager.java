package pepse.UI;

import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.Transition;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.PepseGameManager;
import pepse.util.ColorSupplier;
import pepse.world.*;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Flora;
import pepse.world.trees.Fruits;
import pepse.world.trees.LeafGenerator;
import pepse.world.trees.Trunk;

import java.awt.*;
import java.util.List;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Manages the user interface elements and interactions within the game.
 * Implements HpUpdateCallback and OnJump interfaces for handling HP updates and avatar jumps respectively.
 */
public class UIManager implements HpUpdateCallback, OnJump {
    private final TextRenderable lifeText;
    private LeafGenerator leafGenerator;
    private Fruits fruitsGenerator;
    private final Set<GameObject> leafs;
    private final Set<GameObject> fruits;
    private final Set<Trunk> forest;
    private final GameObject hpGameObject;
    private final GameObject sky;
    private final GameObject night;
    private final GameObject sun;
    private final GameObject sunHalo;
    private final GameObject avatar;
    private final List<Block> ground;
    private boolean colorSelector = true;

    /**
     * Constructs a UIManager object.
     * Initializes UI elements and game objects.
     *
     * @param gameManager       The PepseGameManager managing the game.
     * @param windowController  The WindowController managing the game window.
     * @param inputListener     The UserInputListener for handling user input.
     * @param imageReader       The ImageReader for reading images.
     */
    public UIManager(PepseGameManager gameManager, WindowController windowController,
                     UserInputListener inputListener, ImageReader imageReader) {
        super();
        this.lifeText = new TextRenderable(Float.toString(Constants.MAX_HP));
        this.leafs = new HashSet<>();
        this.fruits = new HashSet<>();
        this.hpGameObject = new HP(new Vector2(windowController.getWindowDimensions().x() / 2,
                Constants.BLOCK_SIZE), Vector2.ONES.mult(Constants.NUMBER_SIZE), lifeText);
        this.sky = Sky.create(windowController.getWindowDimensions());
        Terrain terrain = new Terrain(windowController.getWindowDimensions(),
                new Random().nextInt(Constants.MAX_SEED + Constants.MIN_SEED)
                        - Constants.MIN_SEED);
        this.ground = terrain.createInRange(0, (int) windowController.getWindowDimensions().x());
        this.night = Night.create(windowController.getWindowDimensions(), Constants.DAY_PERIOD);
        Flora flora = new Flora(terrain);
        this.forest = flora.createInRange(0, (int) windowController.getWindowDimensions().x());
        this.sun = Sun.create(windowController.getWindowDimensions(), Constants.DAY_PERIOD);
        this.sunHalo = SunHalo.create(sun);
        this.avatar = new Avatar(new Vector2(windowController.getWindowDimensions().x() / 2,
                terrain.groundHeightAt(windowController.getWindowDimensions().x() / 2) -
                        Constants.AVATAR_SIZE),
                inputListener, imageReader, this, this, gameManager);

        addingGameObjects(gameManager);
        makeTransitions(windowController);
    }

    /**
     * Sets up transitions for day and night cycles and sun movement.
     *
     * @param windowController The WindowController for the game window.
     */
    private void makeTransitions(WindowController windowController) {
        // Transition for day and night cycle
        new Transition<Float>(
                night,
                night.renderer()::setOpaqueness,
                Constants.NOON_OPACITY,
                Constants.MIDNIGHT_OPACITY,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                Constants.DAY_PERIOD / 2,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null
        );
        // Transition for sun movement
        Vector2 initialSunCenter = new Vector2(windowController.getWindowDimensions().x() / 2,
                windowController.getWindowDimensions().y() / 4);
        Vector2 cycleCenter = new Vector2(windowController.getWindowDimensions().x() / 2,
                windowController.getWindowDimensions().y() * (2f / 3f));
        new Transition<Float>(
                sun,
                (Float angle) -> sun.setCenter(initialSunCenter.subtract(cycleCenter).rotated(angle)
                        .add(cycleCenter)),
                Constants.MIN_ANGLE,
                Constants.MAX_ANGLE,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                Constants.DAY_PERIOD,
                Transition.TransitionType.TRANSITION_LOOP,
                null
        );
    }

    /**
     * Adds game objects to the game manager and generates tree elements.
     *
     * @param gameManager The PepseGameManager managing the game.
     */
    private void addingGameObjects(PepseGameManager gameManager) {
        for (Block block : ground){
            gameManager.addObject(block, Layer.STATIC_OBJECTS);
        }
        gameManager.addObject(sky, Layer.BACKGROUND);
        gameManager.addObject(night, Layer.UI);
        for (Trunk trunk : forest){
            gameManager.addObject(trunk, Layer.DEFAULT);
            leafGenerator = new LeafGenerator(trunk, gameManager);
            Set<GameObject> currentTreeLeafs = leafGenerator.generateLeafs();
            leafs.addAll(currentTreeLeafs);
            fruitsGenerator = new Fruits(trunk, gameManager);
            Set<GameObject> currentTreeFruits = fruitsGenerator.generateFruits();
            fruits.addAll(currentTreeFruits);
        }
        gameManager.addObject(sun, Layer.BACKGROUND);
        gameManager.addObject(sunHalo, Layer.BACKGROUND);
        sunHalo.addComponent(deltaTime -> sunHalo.setCenter(sun.getCenter()));
        gameManager.addObject(hpGameObject, Layer.UI);
        gameManager.addObject(avatar, Layer.DEFAULT);
    }

    /**
     * Updates the HP text when HP changes.
     *
     * @param hp The new HP value.
     */
    @Override
    public void onHpUpdate(float hp) {
        lifeText.setString(String.valueOf(hp));
    }

    /**
     * Handles actions to be taken when the avatar jumps.
     */
    @Override
    public void onJump() {
        for (GameObject leaf : leafs){
            leafGenerator.rotateLeaf(leaf);
        }

        Color colorToPaintFruits;
        Color colorToPaintTrunks;

        if (colorSelector){
            colorToPaintFruits = Constants.FRUIT_COLOR_VARIANT;
            colorToPaintTrunks = Constants.TREE_COLOR_VARIANT;
        }
        else {
            colorToPaintFruits = Constants.FRUIT_COLOR;
            colorToPaintTrunks = Constants.TREE_COLOR;
        }
        colorSelector = !colorSelector;
        for (GameObject fruit : fruits){
            fruitsGenerator.makeTransition(fruit, colorToPaintFruits);
        }
        for (Trunk trunk : forest){
            trunk.renderer().setRenderable(new RectangleRenderable(
                    ColorSupplier.approximateColor(colorToPaintTrunks)));
        }
    }
}
