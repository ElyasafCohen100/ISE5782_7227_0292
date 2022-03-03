package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

//Create a class for representation a tube
public class Tube implements Geometry{

    protected Ray _axisRay;
    protected double _radius;

    // Creating a constructor for the class Tube.
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    // Returning a vector that is perpendicular to the surface of the tube.
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
