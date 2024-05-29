
package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.Constants;

import java.awt.*;

/**
 * Represents the sun in the game world.
 * Provides a static method to create a sun GameObject with a yellow oval renderable.
 */
public class Sun {

    /**
     * Creates a sun GameObject with a yellow oval renderable.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The length of the day-night cycle.
     * @return A GameObject representing the sun.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength){
        GameObject sun = new GameObject(new Vector2(windowDimensions.x() / 2,
                windowDimensions.y() / 4),
                Vector2.ONES.mult(Constants.SUN_SIZE), new OvalRenderable(Color.YELLOW));
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag("sun");
        return sun;
    }
}
