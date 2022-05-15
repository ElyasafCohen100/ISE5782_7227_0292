package geometries;

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

public abstract class Geometry implements Intersectable {
    /**
     * return the normal to the vector in specific point
     * @param point
     * @return  the normal to the vector in specific point
     */
    public abstract Vector getNormal(Point point);
}
