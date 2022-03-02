package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * A plane is a flat surface that extends infinitely in all directions
 */
public class Plane implements Geometry {
    final Point _q0;
    final Vector _normal;

    // Creating a plane from a point and a vector.
    public Plane(Point q0, Vector vector) {
        _q0 = q0;
        _normal = vector.normalize();
    }

    // Creating a plane from three points.
    public Plane(Point p1, Point p2, Point p3) {

        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector W = U.crossProduct(V);

        _normal = W.normalize();
    }

    //Getters
    public Point getQ0() {
        return _q0;
    }

    /**
     * Returns the normal vector of the plane
     *
     * @return The normal vector of the plane.
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    // A method that returns null.
    public Vector getNormaL(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }
}
