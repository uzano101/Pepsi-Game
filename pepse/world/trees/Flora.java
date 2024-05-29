package pepse.world.trees;

import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.util.ColorSupplier;
import pepse.world.Terrain;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the flora (trees) in the game world.
 * Provides a method to create a set of tree trunks within a specified range on the terrain.
 */
public class Flora {
    private final Terrain terrain;

    /**
     * Constructs a Flora object with the specified terrain.
     *
     * @param terrain The terrain in the game world where the flora will be placed.
     */
    public Flora(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * Creates a set of tree trunks within the specified range on the terrain.
     *
     * @param minX The minimum x-coordinate of the range.
     * @param maxX The maximum x-coordinate of the range.
     * @return A set of Trunk objects representing the trees within the specified range.
     */
    public Set<Trunk> createInRange(int minX, int maxX){
        Set<Trunk> forest = new HashSet<>();
        for (int i = minX; i < maxX; i++) {
            double isPlantTree = Math.random();
            if (isPlantTree <= Constants.TREE_PLANT_VALUE){
                Trunk treeTrunk = new Trunk(new Vector2(i,
                        terrain.groundHeightAt(i) - Constants.TREE_HEIGHT),
                        new Vector2(Constants.TREE_WIDTH, Constants.TREE_HEIGHT),
                        new RectangleRenderable(ColorSupplier.approximateColor(Constants.TREE_COLOR)));
                forest.add(treeTrunk);
            }
        }
        return forest;
    }
}
