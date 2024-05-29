package pepse.world.daynight;

import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import danogl.GameObject;

import java.awt.*;

/**
 * Represents the night background in the game world.
 * Provides a static method to create a night GameObject with a black rectangle renderable.
 */
public class Night {

    /**
     * Creates a night GameObject with a black rectangle renderable.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The length of the day-night cycle.
     * @return A GameObject representing the night background.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength){
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Color.BLACK));
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag("night");
        return night;
    }

}
