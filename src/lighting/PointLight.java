package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource {

    private Point position; //The position point of the light source in space
    private double kC = 1, kL = 0, kQ = 0; // Light factor -> constant, linear and Quadratic

    /**
     * constructor for the intensity
     *
     * @param color of the intensity of the source of the light
     */
    protected PointLight(Color color, Point position) {
        super(color);
        this.position = position;
    }

    /**
     * Calculate and return the intensity light on specific point
     *
     * @param point the point on the object (Point)
     * @return the intensity (Color)
     */
    @Override
    public Color getIntensity(Point point) {
        // The intensity of the color of the light
        // (the distribution of the light in the surface area)
        // is proportional to squared distance

        //Calculate the denominator of the proportion
        double distance = this.position.distance(point);
        double distanceSquared = distance * distance;
        double factor = this.kC + this.kL * distance + this.kQ * distanceSquared;

        //Return the final intensity
        return getIntensity().reduce(factor);
    }

    /**
     * Return normalize direction vector from the light source to the object
     *
     * @param point the point on the object (Point)
     * @return normalize direction vector from the light source to the object (Vector)
     */
    @Override
    public Vector getL(Point point) {
        return point.subtract(this.position).normalize();
    }

    /**
     * setkC function
     * @param kC
     * @return
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setkL function
     * @param kL
     * @return
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setkQ function
     * @param kQ
     * @return
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
