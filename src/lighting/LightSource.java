package lighting;

import primitives.*;

public interface LightSource {

    /**
     * Get the intensity of the light at a point
     *
     * @param p origin of the light
     * @return the intensity
     */
    public Color getIntensity(Point p);

    /**
     * Get the direction of the light from a point
     *
     * @param p the point
     * @return the direction
     */
    public Vector getL(Point p);
}
