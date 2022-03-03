package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

//Creating a class to represent a cylinder
public class Cylinder extends Tube implements Geometry {

    private double height;


    // Creating a constructor for the class Cylinder.
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

}
