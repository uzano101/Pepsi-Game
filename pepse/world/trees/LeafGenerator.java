package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.PepseGameManager;
import pepse.util.ColorSupplier;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Generates and manages leaf GameObjects for a tree trunk in the game world.
 * Provides methods to generate leafs and rotate them.
 */
public class LeafGenerator {
    private final Vector2 trunkTopLeftCorner;
    private final PepseGameManager gameManager;

    /**
     * Constructs a LeafGenerator object for a given trunk and game manager.
     *
     * @param trunkGenerator The trunk to which the leaves belong.
     * @param gameManager    The PepseGameManager managing the game.
     */
    public LeafGenerator(Trunk trunkGenerator, PepseGameManager gameManager) {
        this.trunkTopLeftCorner = trunkGenerator.getTopLeftCorner();
        this.gameManager = gameManager;
    }

    /**
     * Generates leaf GameObjects around the trunk.
     *
     * @return A set of GameObjects representing the leaves.
     */
    public Set<GameObject> generateLeafs(){
        HashSet<GameObject> result = new HashSet<>();
        float xBasePos = trunkTopLeftCorner.x();
        float yBasePos = trunkTopLeftCorner.y();
        Random randPosGenerator = new Random();
        for (int i = 0; i < Constants.NUMBER_OF_LEAFS; i++) {
            float xOffset = randPosGenerator.nextFloat(2 * Constants.TREE_LEAFS_RADIUS + 1) -
                    Constants.TREE_LEAFS_RADIUS;
            float yOffset = randPosGenerator.nextFloat(2 * Constants.TREE_LEAFS_RADIUS + 1) -
                    Constants.TREE_LEAFS_RADIUS;
            GameObject leaf = new GameObject(new Vector2(xBasePos + xOffset, yBasePos + yOffset),
                    Vector2.ONES.mult(Constants.BLOCK_SIZE),
                    new RectangleRenderable(ColorSupplier.approximateColor(Constants.LEAF_COLOR)));
            leaf.setTag("leaf");
            gameManager.addObject(leaf, Layer.DEFAULT);
            result.add(leaf);
            makeTransition(leaf);
            new Transition<Float>(
                    leaf,
                    (Float dim) -> leaf.setDimensions(new Vector2(dim, leaf.getDimensions().y())),
                    leaf.getDimensions().x() * (3 / 4f),
                    leaf.getDimensions().x(),
                    Transition.CUBIC_INTERPOLATOR_FLOAT,
                    Constants.DAY_PERIOD / 2,
                    Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                    null
            );
        }
        return result;
    }

    /**
     * Initiates a rotation transition for a leaf GameObject.
     *
     * @param leaf The leaf GameObject to rotate.
     */
    private void makeTransition(GameObject leaf){
        new ScheduledTask(leaf, Constants.DAY_PERIOD / 2, true, () -> {
            new Transition<Float>(
                    leaf,
                    (Float angle) -> leaf.renderer().setRenderableAngle(angle),
                    Constants.MIN_ANGLE,
                    Constants.MAX_ANGLE_FOR_LEAFS,
                    Transition.CUBIC_INTERPOLATOR_FLOAT,
                    Constants.LEAFS_ROTATE_PERIOD,
                    Transition.TransitionType.TRANSITION_ONCE,
                    null
            );
        });
    }

    /**
     * Rotates a leaf GameObject.
     *
     * @param leaf The leaf GameObject to rotate.
     */
    public void rotateLeaf(GameObject leaf){
        new Transition<Float>(
                leaf,
                (Float angle) -> leaf.renderer().setRenderableAngle(angle),
                Constants.MIN_ANGLE,
                Constants.MAX_ANGLE_FOR_LEAFS,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                Constants.LEAFS_ROTATE_PERIOD,
                Transition.TransitionType.TRANSITION_ONCE,
                null
        );
    }
}
