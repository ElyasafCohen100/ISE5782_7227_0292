package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{
     private Point _center;
     private double _radius;

    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    @Override
    public Vector getNormaL(Point point) {
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
