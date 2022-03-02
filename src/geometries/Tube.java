package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{

    protected Ray _axisRay;
    protected double _radius;

    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    public Vector getNormaL(Point point) {
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
