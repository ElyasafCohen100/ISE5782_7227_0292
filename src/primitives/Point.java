package primitives;

import java.util.Objects;

public class Point {

    final Double3 _xyz;

    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _xyz.equals(point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point " + _xyz;
    }

    public double distanceSquared(Point point) {
        double x1 = _xyz._d1;
        double y1 = _xyz._d2;
        double z1 = _xyz._d3;

        double x2 = point._xyz._d1;
        double y2 = point._xyz._d2;
        double z2 = point._xyz._d3;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    double distance(Point point) {
        return this.distanceSquared(point);
    }

    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }

    public Vector subtract(Point point) {

        Double3 result = _xyz.subtract(point._xyz);

        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return new Vector(result);
    }
}