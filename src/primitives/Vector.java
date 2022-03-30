package primitives;

// Create a class for representation Vector
public class Vector extends Point {

    // Creating a constructor for the class Vector.
    public Vector(Double3 xyz) {
        super(xyz);

        //Check if the coordinates create ZERO vector.
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
    }

    // Creating a constructor for the class Vector.
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    /**
     * Add the vector to this vector and return the result
     *
     * @param vector The vector to add to this vector.
     * @return A new Vector object.
     */
    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }

    /**
     * This function returns a new vector that is the result of multiplying the current vector by a scalar
     *
     * @param scalar the scalar to multiply the vector by
     * @return A new Vector object.
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    // Calculating the dot product of the current vector and the vector passed as a parameter.
    public double dotProduct(Vector vector) {

        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        double v1 = vector.xyz.d1;
        double v2 = vector.xyz.d2;
        double v3 = vector.xyz.d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    // Creating a new vector that is the result of the cross product of the current vector and the vector passed as a
    // parameter.
    public Vector crossProduct(Vector vector) {

        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        double v1 = vector.xyz.d1;
        double v2 = vector.xyz.d2;
        double v3 = vector.xyz.d3;

        return new Vector((u2 * v3 - v2 * u3), -(u1 * v3 - v1 * u3), (u1 * v2 - v1 * u2));
    }

    // Calculating the length of the vector squared.
    public double lengthSquared() {
        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    // Calculating the length of the vector.
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    // Creating a new vector that is the result of the normalization of the current vector.
    public Vector normalize() {
        double len = length();
        return new Vector(this.xyz.reduce(len));
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
        return "Vector" +this.xyz;
    }
}
