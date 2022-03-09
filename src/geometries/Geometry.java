package geometries;

import primitives.Point;
import primitives.Vector;

// Geometry interface //
public interface Geometry {
    Vector getNormal(Point point);
}
