
package pepse.world.trees;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a tree trunk GameObject in the game world.
 */
public class Trunk extends GameObject {

    /**
     * Constructs a Trunk object with the specified parameters.
     *
     * @param topLeftCorner The position of the top-left corner of the trunk.
     * @param dimensions    The dimensions (width and height) of the trunk.
     * @param renderable    The renderable component for the trunk.
     */
    public Trunk(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }

}
