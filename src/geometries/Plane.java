package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final Point _q0;
    final Vector _normal;

    public Plane(Point q0, Vector vector) {
        _q0 = q0;
        _normal = vector.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {

        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector W = U.crossProduct(V);

        _normal = W.normalize();
    }

    public Point getQ0() {
        return _q0;
    }

    public Vector getNormal() {
        return _normal;
    }

    @Override
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
