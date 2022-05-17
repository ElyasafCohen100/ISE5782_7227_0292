package lighting;

import primitives.Color;

/**
 * Light abstract class representing the light of the scene
 */
abstract class Light {

    protected Color intensity; //the geometry's color intensity light

    /**
     * constructor for the intensity
     *
     * @param color of the intensity of the source of the light
     */
    protected Light(Color color) {
        this.intensity = color;
    }

    /**
     * get the light's intensity
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
