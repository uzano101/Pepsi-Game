package pepse.UI;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

/**
 * Represents a graphical element for displaying hit points (HP) in a user interface.
 * Extends the GameObject class to inherit basic rendering functionality.
 * This class utilizes a TextRenderable object for rendering HP text.
 */
public class HP extends GameObject {

    /**
     * Constructs an HP object with the specified parameters.
     *
     * @param topLeftCorner The position of the top-left corner of the HP element.
     * @param dimensions    The dimensions (width and height) of the HP element.
     * @param renderer      The TextRenderable object used for rendering HP text.
     */
    public HP(Vector2 topLeftCorner, Vector2 dimensions, TextRenderable renderer) {
        super(topLeftCorner, dimensions, renderer);
    }
}
