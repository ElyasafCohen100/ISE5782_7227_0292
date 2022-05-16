package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

//======= Plane Class ========//

/**
 * A plane is a flat surface that extends infinitely in all directions
 */

public class Plane extends Geometry {
    final Point q0; // a random point on the plane
    final Vector normal; // a normal vector to the plane

    /**
     * Constructor to initialize plane
     * @param q0 point in the plane
     * @param vector vector that orthogonal to the plain
     */
    public Plane(Point q0, Vector vector) {
        this.q0 = q0;
        this.normal = vector.normalize();
    }

    /**
     * Constructor to initialize plane
     * @param p1 one point in the plane
     * @param p2 second point in the plane
     * @param p3 third point in the plane
     */
    public Plane(Point p1, Point p2, Point p3) {

        this.q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector W = U.crossProduct(V);

        this.normal = W.normalize();
    }

    //Getters
    public Point getQ0() {
        return this.q0;
    }

    /**
     * getter for the normal vector of the plane
     *
     * @return The normal vector of the plane.
     */
    public Vector getNormal() {
        return this.normal;
    }


    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ",normal=" + normal +
                '}';
    }

    /**
     * overriding getNormal of Geometry
     *
     * @param point referred point to the normal
     * @return normal to the Geometry
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0(); // according to the illustration P0 is the same point of the ray's P0 (that's why the definition))
        Vector v = ray.getDir(); // according to the illustration v is the same vector of the ray's vector (that's why the definition))

        if (this.q0.equals(P0)) { // if the ray starting from the plane it doesn't cut the plane at all
            return null; // so return null
        }

        Vector n = this.normal; // the normal to the plane

        double nv = n.dotProduct(v); // the formula's denominator of "t" (t =(n*(Q-P0))/nv)

        // ray is lying on the plane axis
        if (isZero(nv)) { // can't divide by zero (nv is the denominator)
            return null;
        }

        Vector Q0_P0 = this.q0.subtract(P0);
        double nP0Q0 = alignZero(n.dotProduct(Q0_P0));

        // t should be bigger than 0
        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        // t should be bigger than 0
        if (t <= 0) {
            return null;
        }

        // "this" - the specific geometry, "rey.getPoint(t)" - the point that the ray
        // cross the geometry
        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
