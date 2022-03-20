package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

//Create a class for representation a tube
public class Tube implements Geometry {

    protected Ray _axisRay;
    protected double _radius;

    // Creating a constructor for the class Tube.
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    // Returning a vector that is perpendicular to the surface of the tube.
    public Vector getNormal(Point point) {

        Vector tubeCenterVector = _axisRay.getDir();
        Point p0 = _axisRay.getP0();

        double projection = tubeCenterVector.dotProduct(point.subtract(p0));
        if (projection == 0) {
            throw new IllegalArgumentException("the projection must not be 0");
        }

        // Calculating O when O is a point on direction tube vector (o = p0 + proj * v)//
        Point tubeCenterPoint = p0.add(tubeCenterVector.scale(projection));

        //Calculate the normal
        Vector normalVector = point.subtract(tubeCenterPoint).normalize();

        return normalVector;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
