package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

//Creating a class to represent a cylinder
public class Cylinder extends Tube {

    private final double height;

    // Creating a constructor for the class Cylinder.
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ",axisRay=" + axisRay +
                ",radius=" + radius +
                '}';
    }

    public Vector getNormal(Point point) {

        // define the center of cylinder's sides
        Vector cylinderCenterVector = axisRay.getDir();

        Point centerOfOneSide = axisRay.getP0();
        Point centerOfSecondSide = axisRay.getP0().add(axisRay.getDir().scale(this.height));

        //The normal at a base will be simply equal to central ray's
        //direction vector 𝑣 or opposite to it (−𝑣) so we check it
        if (point.equals(centerOfOneSide)) {
            return cylinderCenterVector.scale(-1);
        }
        else if (point.equals(centerOfSecondSide)){
            return cylinderCenterVector;
        }

        //If the point on one of the cylinder's bases, but it's not the center point
        double projection = cylinderCenterVector.dotProduct(point.subtract(centerOfOneSide));
        if (projection == 0) {
            Vector v1 = point.subtract(centerOfOneSide);
            return v1.normalize();
        }

        //If the point on the side of the cylinder.
        Point center = centerOfOneSide.add(cylinderCenterVector.scale(projection));
        Vector v = point.subtract(center);

        return v.normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = new LinkedList<>();
        Vector va = this.axisRay.getDir();
        Point p1 = this.axisRay.getP0();
        Point p2 = p1.add(this.axisRay.getDir().scale(this.height));

        Plane plane1 = new Plane(p1, this.axisRay.getDir()); //get plane of bottom base
        List<GeoPoint> result2 = plane1.findGeoIntersections(ray); //intersections with bottom's plane

        if (result2 != null) {
            //Add all intersections of bottom's plane that are in the base's bounders
            for (GeoPoint point : result2) {
                if (point.point.equals(p1)) { //to avoid vector ZERO
                    result.add(point);
                }
                //checks that point is inside the base
                else if ((point.point.subtract(p1).dotProduct(point.point.subtract(p1)) < this.radius * this.radius)) {
                    result.add(point);
                }
            }
        }

        List<GeoPoint> result1 = super.findGeoIntersections(ray); //get intersections for tube

        if (result1 != null) {
            //Add all intersections of tube that are in the cylinder's bounders
            for (GeoPoint point : result1) {
                if (va.dotProduct(point.point.subtract(p1)) > 0 && va.dotProduct(point.point.subtract(p2)) < 0) {
                    result.add(point);
                }
            }
        }

        Plane plane2 = new Plane(p2, this.axisRay.getDir()); //get plane of top base
        List<GeoPoint> result3 = plane2.findGeoIntersections(ray); //intersections with top's plane

        if (result3 != null) {
            for (GeoPoint point : result3) {
                if (point.point.equals(p2)) { //to avoid vector ZERO
                    result.add(point);
                }
                //Formula that checks that point is inside the base
                else if ((point.point.subtract(p2).dotProduct(point.point.subtract(p2)) < this.radius * this.radius)) {
                    result.add(point);
                }
            }
        }

        if (result.size() > 0) {
            return result;
        }

        return null;
    }
}