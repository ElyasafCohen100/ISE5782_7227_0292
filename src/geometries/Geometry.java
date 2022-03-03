package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
    Vector getNormal(Point point);
}
