package pepse.world;

/**
 * Defines a callback interface for updating health points (HP).
 */
public interface HpUpdateCallback {

    /**
     * Callback method invoked to update health points (HP).
     *
     * @param hp The updated value of health points.
     */
    void onHpUpdate(float hp);
}
