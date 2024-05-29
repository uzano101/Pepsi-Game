
package pepse.world;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.util.Vector2;
import pepse.Animator;
import pepse.Constants;
import pepse.PepseGameManager;

import java.awt.event.KeyEvent;

/**
 * Represents the player's avatar in the game world.
 */
public class Avatar extends GameObject {
    private static final float VELOCITY_X = 400;
    private static final float VELOCITY_Y = -650;
    private static final float GRAVITY = 600;
    private final HpUpdateCallback hpUpdateCallback;
    private final OnJump onPlayerJump;
    private final Animator animator;
    private float hp;
    private final PepseGameManager gameManager;
    private final UserInputListener inputListener;

    /**
     * Constructs an Avatar object with the specified parameters.
     *
     * @param pos              The initial position of the avatar.
     * @param inputListener    The input listener to handle user input.
     * @param imageReader      The image reader to load avatar images.
     * @param hpUpdateCallback The callback for updating the avatar's health points.
     * @param onPlayerJump     The callback for handling avatar jumps.
     * @param gameManager      The game manager managing the game.
     */
    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader,
                  HpUpdateCallback hpUpdateCallback, OnJump onPlayerJump, PepseGameManager gameManager) {
        super(pos, Vector2.ONES.mult(Constants.AVATAR_SIZE), imageReader.readImage(
                Constants.PLAYER_PATH, false));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(GRAVITY);
        this.inputListener = inputListener;
        this.gameManager = gameManager;
        hp = Constants.MAX_HP;
        this.hpUpdateCallback = hpUpdateCallback;
        this.onPlayerJump = onPlayerJump;
        animator = new Animator(imageReader);
    }

    /**
     * Updates the avatar's state and behavior.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        if (hp == Constants.MIN_HP) {
            this.renderer().setRenderable(animator.getIdleAnimator());
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT) && hp >= Constants.MIN_HP_TO_MOVE) {
            this.renderer().setRenderable(animator.getRunAnimator());
            this.renderer().setIsFlippedHorizontally(true);
            xVel -= VELOCITY_X;
            updateHP(Constants.MOVE_HP_LOSE);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT) && hp >= Constants.MIN_HP_TO_MOVE) {
            this.renderer().setRenderable(animator.getRunAnimator());
            this.renderer().setIsFlippedHorizontally(false);
            xVel += VELOCITY_X;
            updateHP(Constants.MOVE_HP_LOSE);
        }
        transform().setVelocityX(xVel);
        if (inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0 &&
                hp >= Constants.MIN_HP_TO_JUMP) {
            this.renderer().setRenderable(animator.getJumpAnimator());
            onPlayerJump.onJump();
            transform().setVelocityY(VELOCITY_Y);
            updateHP(Constants.JUMP_HP_LOSE);
        }
        if (!(inputListener.isKeyPressed(KeyEvent.VK_SPACE) ||
                inputListener.isKeyPressed(KeyEvent.VK_RIGHT) ||
                inputListener.isKeyPressed(KeyEvent.VK_LEFT)) && getVelocity().y() == 0) {
            this.renderer().setRenderable(animator.getIdleAnimator());
            updateHP(Constants.IDLE_HP_GAIN);
        }
    }

    /**
     * Updates the avatar's health points and invokes the callback.
     *
     * @param rate The rate of change in health points.
     */
    public void updateHP(float rate) {
        hp += rate;
        hp = Math.min(hp, Constants.MAX_HP);
        hp = Math.max(hp, Constants.MIN_HP);
        hpUpdateCallback.onHpUpdate(hp);
    }

    /**
     * Handles collision events with other GameObjects.
     *
     * @param other     The GameObject collided with.
     * @param collision The details of the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals(Constants.FRUIT_TAG)) {
            updateHP(Constants.FRUIT_GAIN_HP);
            gameManager.removeObject(other, Layer.DEFAULT);
        }
    }
}
