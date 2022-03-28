package primitives;

import java.util.Objects;

// Creating a new class for point representation
public class Point {

    final Double3 _xyz;

    // Creating a constructor that takes a Double3 object and creates a new Point object.
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    // Creating a constructor that takes three doubles and creates a new Point object.
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

    // Calculating the distance between two points.
    public double distanceSquared(Point point) {
        double x1 = _xyz._d1;
        double y1 = _xyz._d2;
        double z1 = _xyz._d3;

        double x2 = point._xyz._d1;
        double y2 = point._xyz._d2;
        double z2 = point._xyz._d3;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    /**
     * Given a point, return the distance between the point and this point
     *
     * @param point The point to measure the distance to.
     * @return The distance between the two points.
     */
    double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    /**
     * Add a vector to a point
     *
     * @param vector The vector to add to the point.
     * @return A new Point object.
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }


    // Subtracting the point from the vector.
    public Vector subtract(Point point) {

        Double3 result = _xyz.subtract(point._xyz);

        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return new Vector(result);
    }

    public double getX() {
        return _xyz._d1;
    }
    public double getY() {
        return _xyz._d2;
    }
    public double getZ() {
        return _xyz._d3;
    }
}