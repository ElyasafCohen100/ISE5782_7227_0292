package primitives;

import java.util.Objects;

//Opening a Class for representation Ray
public class Ray {

    final Point _p0;
    final Vector _dir;

    // Creating a constructor for the class Ray.
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    //Getters
    public Point getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "primitives.Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }
}
