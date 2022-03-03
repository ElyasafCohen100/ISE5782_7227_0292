package geometries;

import primitives.Point;
import primitives.Vector;

//Creating a class to represent a Sphere
public class Sphere implements Geometry{
     private Point _center;
     private double _radius;

    // Creating a constructor for the Sphere class.
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }
}
