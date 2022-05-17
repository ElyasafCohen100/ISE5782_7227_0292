package lighting;

import primitives.*;

//==== the SpotLight represented source light like Spot =====//

public class SpotLight extends PointLight {

    private Vector dir;

    /**
     * constructor for the intensity
     *
     * @param color     of the intensity of the source of the light
     * @param direction
     */
    protected SpotLight(Color color, Point position, Vector direction) {
        super(color, position);
        this.dir = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double projection = this.dir.dotProduct(getL(point));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }

        double factor = Math.max(0, projection);
        Color pointLightIntensity = super.getIntensity(point);

        return (pointLightIntensity.scale(factor));
    }
}
