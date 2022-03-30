package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

//Opening a Class for representation Ray
public class Ray {

    private final Point p0;
    private final Vector dir;

    // Creating a constructor for the class Ray.
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    //Getters
    public Point getP0() {
        return this.p0;
    }

    public Vector getDir() {
        return this.dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return this.p0.equals(ray.p0) && this.dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.p0, this.dir);
    }

    @Override
    public String toString() {
        return "primitives.Ray{" +
                "p0=" + this.p0 +
                ", dir=" + this.dir +
                '}';
    }

    /**
     *get Point at specific distance in the ray's direction
     *
     * @param t is a distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        if(isZero(t)){
            throw new IllegalArgumentException("t is equal to 0 produce an illegal ZERO vector");
        }
        return this.p0.add(this.dir.scale(t));
    }
}
