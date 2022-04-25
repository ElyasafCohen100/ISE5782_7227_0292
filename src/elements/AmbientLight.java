package elements;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all object in 3D space
 *
 * this class is represented us the Ambient Light
 */
public class AmbientLight {

    private final Color intensity; // intensity (עצמה) of ambient Light

    /**
     * @param Ia - Light illumination
     * @param Ka - Light factor - מקדם הנחתה של האור
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        //== for knowing the intensity after the light factor ==//
        intensity = Ia.scale(Ka);
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    /**
     * getter for intensity
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
