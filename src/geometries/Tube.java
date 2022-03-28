package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube implements Geometry{
    protected Ray _axisRay;
    protected double _radius;

    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        Vector centeredVectorDirection = _axisRay.getDir();
        Point p0 = _axisRay.getP0();

        //If the projection equals to zero.
        double projection = centeredVectorDirection.dotProduct(point.subtract(p0));
        if (projection == 0) throw new IllegalArgumentException("The projection not allowed to be 0");

        //Calculate the point on the centered ray of the tube to calculate the normal with it.
        Point center = p0.add(centeredVectorDirection.scale(projection));

        //Calculate the normal
        Vector v = point.subtract(center);

        //Return the normalized normal
        return v.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
