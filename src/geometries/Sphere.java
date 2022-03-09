package geometries;

import primitives.Point;
import primitives.Vector;

//Creating a class to represent a Sphere
public class Sphere implements Geometry {
    private Point _center;
    private double _radius;

    // Creating a constructor for the Sphere class.
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * Return the normal to the sphere in the receiving point
     * @param point Point on the sphere
     * @return Normal to the sphere in the receiving point (Vector)
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = point.subtract(_center);
        return v.normalize();
    }
}
