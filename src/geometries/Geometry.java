package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for finding the Normal of any geometry shape
 *
 * As we said - because there is no necessary to implement the "getNormal" function in a complex object,
 * we separated the Geometry interface into two interfaces(ISP principle).
 *
 * so here we have left the "getNormal" function to find the Normal of any geometry shape
 */

public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK; // the geometry's default color

    /**
     * getEmission function
     * @return the geometry's color
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * setEmission function
     * @param emission
     * @return
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * return the normal to the vector in specific point
     * @param point
     * @return  the normal to the vector in specific point
     */
    public abstract Vector getNormal(Point point);
}
