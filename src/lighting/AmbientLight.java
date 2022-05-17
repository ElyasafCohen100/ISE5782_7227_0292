package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all object in 3D space
 * <p>
 * this class represented us the Ambient Light (תאורה סביבתית)
 */
public class AmbientLight extends Light {

    /**
     * constructor for knowing the intensity after the light factor
     *
     * @param Ia - Light illumination (RGB עצמת האור לפי קומפוננטות)
     * @param Ka - Light factor - מקדם הנחתה של האור
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        //calculation of the intensity after the light factor -> Ip = Ia* Ka //
        super(Ia.scale(Ka));
    }

    //== default constructor for initialize the background to black ==//
    public AmbientLight() {
        super(Color.BLACK);
    }
}
