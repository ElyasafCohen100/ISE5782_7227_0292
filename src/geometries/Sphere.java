package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

public class Sphere extends Geometry {
    private final Point center;
    private final double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return this.center;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ",radius=" + radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere.radius, this.radius) == 0 && this.center.equals(sphere.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.center, this.radius);
    }

    /**
     * Return the normal to the sphere in the receiving point
     * @param point Point on the sphere
     * @return Normal to the sphere in the receiving point (Vector)
     */
    @Override
    public Vector getNormal(Point point) {
        Vector v = point.subtract(this.center);
        return v.normalize(); //Return normalize normal vector.
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        Point p0 = ray.getP0(); // ray's starting point
        Point O = this.center; //the sphere's center point
        Vector V = ray.getDir(); // "the v vector" from the presentation

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (O.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(this.radius));
            return List.of(newPoint);
        }

        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= this.radius) {
            return null;
        }

        double th = Math.sqrt(this.radius * this.radius - d * d);
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
