package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

//Creating a class to represent a cylinder
public class Cylinder extends Tube, Geometry {

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
}
