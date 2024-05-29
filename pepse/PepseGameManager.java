package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import pepse.UI.UIManager;

/**
 * The PepseGameManager class manages the game and extends the GameManager class.
 */
public class PepseGameManager extends GameManager{

    /**
     * The main method to start the game.
     * @param args The command-line arguments.
     */
    public static void main(String[] args){
        new PepseGameManager().run();
    }

    /**
     * Initializes the game with the necessary components.
     * @param imageReader The image reader for loading images.
     * @param soundReader The sound reader for loading sounds.
     * @param inputListener The user input listener for capturing user input.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        new UIManager(this, windowController, inputListener, imageReader);
    }

    /**
     * Removes a game object from the game.
     * @param object The game object to be removed.
     * @param layer The layer from which the object should be removed.
     */
    public void removeObject(GameObject object, int layer) {
        gameObjects().removeGameObject(object, layer);
    }

    /**
     * Adds a game object to the game.
     * @param object The game object to be added.
     * @param layer The layer to which the object should be added.
     */
    public void addObject(GameObject object, int layer) {
        gameObjects().addGameObject(object, layer);
    }
}
