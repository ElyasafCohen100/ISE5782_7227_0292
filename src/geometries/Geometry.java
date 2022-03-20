package geometries;

import primitives.Point;
import primitives.Vector;

// This interface will serve all geometric classes //
public interface Geometry extends Intersectable {
    /**
     * return the normal to the vector in specific point
     * @param point
     * @return  the normal to the vector in specific point
     */
    Vector getNormal(Point point);
}
