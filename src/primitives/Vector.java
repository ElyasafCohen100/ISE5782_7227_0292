package primitives;

public class Vector extends Point {

    public Vector(Double3 xyz) {
        super(xyz);

        //Check if the coordinates create ZERO vector.
        if(_xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (_xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    public Vector add(Vector vector) {
        return new Vector(_xyz.add(vector._xyz));
    }
    public Vector scale(double scalar){return new Vector(_xyz.scale(scalar));}

    public double dotProduct(Vector vector) {

        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    public Vector crossProduct(Vector vector) {

        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = vector._xyz._d1;
        double v2 = vector._xyz._d2;
        double v3 = vector._xyz._d3;

        return new Vector((u2*v3-v2*u3),-(u1*v3-v1*u3),(u1*v2-v1*u2));
    }

    public double lengthSquared() {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double len = length();
        return new Vector(_xyz.reduce(len));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Vector" + _xyz;
    }
}
