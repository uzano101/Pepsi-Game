package pepse.world;

import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.util.ColorSupplier;
import pepse.util.NoiseGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the terrain in the game world.
 */
public class Terrain {
    private final float groundHeightAtX0;
    private final NoiseGenerator noiseGenerator;

    /**
     * Constructs a Terrain object with the specified window dimensions and seed.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param seed             The seed for noise generation.
     */
    public Terrain(Vector2 windowDimensions, int seed) {
        this.groundHeightAtX0 = windowDimensions.y() * (2f / 3f);
        this.noiseGenerator = new NoiseGenerator(seed, (int) groundHeightAtX0);
    }

    /**
     * Calculates the height of the terrain at the given x-coordinate.
     *
     * @param x The x-coordinate.
     * @return The height of the terrain at the given x-coordinate.
     */
    public float groundHeightAt(float x){
        return groundHeightAtX0 + (float)noiseGenerator.noise(x, Constants.BLOCK_SIZE * 7);
    }

    /**
     * Creates blocks representing the terrain within the specified x-range.
     *
     * @param minX The minimum x-coordinate.
     * @param maxX The maximum x-coordinate.
     * @return A list of Block objects representing the terrain.
     */
    public List<Block> createInRange(int minX, int maxX){
        RectangleRenderable groundRenderer;
        List<Block> res = new ArrayList<>();
        for (int i = minX; i <= maxX; i += Constants.BLOCK_SIZE) {
            float xPos = i - Constants.BLOCK_SIZE;
            float yPos = (float)Math.floor(groundHeightAt(xPos) / Constants.BLOCK_SIZE) *
                    Constants.BLOCK_SIZE;
            for (int j = 0; j < Constants.TERRAIN_DEPTH * Constants.BLOCK_SIZE; j += Constants.BLOCK_SIZE) {
                groundRenderer =
                        new RectangleRenderable(ColorSupplier.approximateColor(Constants.BASE_GROUND_COLOR));
                var ground = new Block(new Vector2(xPos, yPos + j),
                        new Vector2(Constants.BLOCK_SIZE, Constants.BLOCK_SIZE), groundRenderer);
                ground.setTag("ground");
                res.add(ground);
            }
        }
        return res;
    }
}
