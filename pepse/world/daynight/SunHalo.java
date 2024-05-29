package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import pepse.Constants;

/**
 * Represents the halo around the sun in the game world.
 * Provides a static method to create a sun halo GameObject with an oval renderable.
 */
public class SunHalo {

    /**
     * Creates a sun halo GameObject with an oval renderable.
     *
     * @param sun The GameObject representing the sun around which the halo is created.
     * @return A GameObject representing the sun halo.
     */
    public static GameObject create(GameObject sun){
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner(), sun.getDimensions().mult(1.3f),
                new OvalRenderable(Constants.SUN_HALO_COLOR));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag("sunHalo");
        return sunHalo;
    }
}
