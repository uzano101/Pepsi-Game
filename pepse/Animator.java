package pepse;

import danogl.gui.ImageReader;
import danogl.gui.rendering.AnimationRenderable;
import danogl.gui.rendering.Renderable;

/**
 * Manages animations for game characters.
 */
public class Animator {

    private final AnimationRenderable idleAnimation;  // Animation for idle state
    private final AnimationRenderable jumpAnimation;  // Animation for jumping state
    private final AnimationRenderable runAnimation;   // Animation for running state

    /**
     * Constructs an Animator object with animations loaded from the given ImageReader.
     *
     * @param reader The ImageReader used to load animation frames.
     */
    public Animator(ImageReader reader) {
        // Load animation frames for idle state
        Renderable[] idleClip = {reader.readImage(Constants.IDLE_0, false),
                reader.readImage(Constants.IDLE_1, false),
                reader.readImage(Constants.IDLE_2, false),
                reader.readImage(Constants.IDLE_3, false)};
        // Load animation frames for jumping state
        Renderable[] jumpClip = {reader.readImage(Constants.JUMP_0, false),
                reader.readImage(Constants.JUMP_1, false),
                reader.readImage(Constants.JUMP_2, false),
                reader.readImage(Constants.JUMP_3, false)};
        // Load animation frames for running state
        Renderable[] runClip = {reader.readImage(Constants.RUN_0, false),
                reader.readImage(Constants.RUN_1, false),
                reader.readImage(Constants.RUN_2, false),
                reader.readImage(Constants.RUN_3, false)};

        // Create animation renderables for each animation state
        this.idleAnimation = new AnimationRenderable(idleClip, Constants.IDLE_TIME_LAP);
        this.jumpAnimation = new AnimationRenderable(jumpClip, Constants.JUMP_TIME_LAP);
        this.runAnimation = new AnimationRenderable(runClip, Constants.RUN_TIME_LAP);
    }

    /**
     * Returns the animation renderable for the idle state.
     *
     * @return The animation renderable for idle state.
     */
    public AnimationRenderable getIdleAnimator(){
        return idleAnimation;
    }

    /**
     * Returns the animation renderable for the jumping state.
     *
     * @return The animation renderable for jumping state.
     */
    public AnimationRenderable getJumpAnimator(){
        return jumpAnimation;
    }

    /**
     * Returns the animation renderable for the running state.
     *
     * @return The animation renderable for running state.
     */
    public AnimationRenderable getRunAnimator(){
        return runAnimation;
    }
}
