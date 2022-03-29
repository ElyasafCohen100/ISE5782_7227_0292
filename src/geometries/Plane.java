package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

//======= Plane Class ========//

/**
 * A plane is a flat surface that extends infinitely in all directions
 */

public class Plane implements Geometry {
    final Point _q0; // a random point on the plane
    final Vector _normal; // a normal vector to the plane

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
     * getter for the normal vector of the plane
     *
     * @return The normal vector of the plane.
     */
    public Vector getNormal() {
        return _normal;
    }


    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
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
    public List<Point> findIntersections(Ray ray) {

        Point P0= ray.getP0(); // according to the illustration P0 is the same point of the ray's P0 (that's why the definition))
        Vector v = ray.getDir(); // according to the illustration v is the same vector of the ray's vector (that's why the definition))

        if(_q0.equals(P0)){ // if the ray starting from the plane it doesn't cut the plane at all
            return null; // so return null
        }

        Vector n = _normal; // the normal to the plane

        double nv = n.dotProduct(v); // the formula's denominator of "t" (t =(n*(Q-P0))/nv)

        // ray is lying on the plane axis
        if (isZero(nv)){ // can't divide by zero (nv is the denominator)
            return null;
        }

        Vector Q0_P0 = _q0.subtract(P0);
        double nP0Q0= alignZero(n.dotProduct(Q0_P0));

        // t should be bigger than 0
        if(isZero(nP0Q0)){
            return null;
        }

        double t =alignZero(nP0Q0 / nv);

        // t should be bigger than 0
        if(t<=0){
            return null;
        }

        return List.of(ray.getPoint(t));
    }
}
