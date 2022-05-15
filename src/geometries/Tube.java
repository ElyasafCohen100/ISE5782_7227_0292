package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends Geometry {
    protected Ray axisRay;
    protected double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + this.axisRay +
                ", radius=" + this.radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        Vector centeredVectorDirection = this.axisRay.getDir();
        Point p0 = this.axisRay.getP0();

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
