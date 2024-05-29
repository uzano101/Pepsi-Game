package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;

/**
 * Represents the sky GameObject in the game world.
 */
public class Sky {

    /**
     * Creates a sky GameObject with the specified window dimensions.
     *
     * @param windowDimensions The dimensions of the game window.
     * @return A GameObject representing the sky.
     */
    public static GameObject create(Vector2 windowDimensions) {
        // Initialize a new blue rectangle representing the sky.
        GameObject sky = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Constants.BASIC_SKY_COLOR));
        // Sets the coordinate space of the sky to move with the camera.
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        // Sets a tag for the sky for debugging purposes.
        sky.setTag("sky");
        return sky;
    }
}
