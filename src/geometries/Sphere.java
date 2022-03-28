package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

public class Sphere implements Geometry{
    private final Point _center;
    private final double _radius;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere._radius, _radius) == 0 && _center.equals(sphere._center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    /**
     * Return the normal to the sphere in the receiving point
     * @param point Point on the sphere
     * @return Normal to the sphere in the receiving point (Vector)
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = point.subtract(_center);
        return v.normalize(); //Return normalize normal vector.
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Point O = _center;
        Vector V = ray.getDir();

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (O.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(_radius));
            return List.of(newPoint);
        }

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= _radius) {
            return null;
        }

        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }

        return null;
    }
}
