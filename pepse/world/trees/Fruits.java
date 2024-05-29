package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.PepseGameManager;
import pepse.util.ColorSupplier;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Represents the fruits growing on a tree in the game world.
 * Provides methods to generate fruits and transition their colors.
 */
public class Fruits {
    private final PepseGameManager gameManager;
    private final Vector2 trunkTopLeftCorner;

    /**
     * Constructs a Fruits object for a given trunk and game manager.
     *
     * @param trunk       The trunk to which the fruits belong.
     * @param gameManager The PepseGameManager managing the game.
     */
    public Fruits(Trunk trunk, PepseGameManager gameManager) {
        this.gameManager = gameManager;
        this.trunkTopLeftCorner = trunk.getTopLeftCorner();
    }

    /**
     * Generates a set of fruit GameObjects around the trunk.
     *
     * @return A set of GameObjects representing the fruits.
     */
    public Set<GameObject> generateFruits() {
        Set<GameObject> result = new HashSet<>();
        float xBasePos = trunkTopLeftCorner.x();
        float yBasePos = trunkTopLeftCorner.y();
        Random randPosGenerator = new Random();
        for (int i = 0; i < Constants.NUMBER_OF_FRUITS; i++) {
            float xOffset = randPosGenerator.nextFloat(2 * Constants.TREE_LEAFS_RADIUS + 1) -
                    Constants.TREE_LEAFS_RADIUS;
            float yOffset = randPosGenerator.nextFloat(2 * Constants.TREE_LEAFS_RADIUS + 1) -
                    Constants.TREE_LEAFS_RADIUS;
            GameObject leaf = new GameObject(new Vector2(xBasePos + xOffset, yBasePos + yOffset),
                    Vector2.ONES.mult(randPosGenerator.nextFloat(
                            Constants.TREE_FRUITS_RADIUS / 5) + Constants.TREE_FRUITS_RADIUS),
                    new OvalRenderable(ColorSupplier.approximateColor(Constants.FRUIT_COLOR)));
            leaf.setTag("fruit");
            result.add(leaf);
            gameManager.addObject(leaf, Layer.DEFAULT);
        }
        return result;
    }

    /**
     * Transitions the color of a fruit GameObject.
     *
     * @param fruit       The fruit GameObject to transition.
     * @param colorToPaint The color to paint the fruit.
     */
    public void makeTransition(GameObject fruit, Color colorToPaint){
        fruit.renderer().setRenderable(new OvalRenderable(ColorSupplier.approximateColor(colorToPaint)));
    }
}
