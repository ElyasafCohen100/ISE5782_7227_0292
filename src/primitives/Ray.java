package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

//Opening a Class for representation Ray in the space (3D)//
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
     * get Point at specific distance in the ray's direction
     *
     * @param t is a distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            throw new IllegalArgumentException("t is equal to 0 produce an illegal ZERO vector");
        }
        return this.p0.add(this.dir.scale(t));
    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Return the closest GeoPoint from all intersection GeoPoints
     *
     * @param geoPointList list of intersections
     * @return {@link GeoPoint}
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        GeoPoint closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance; // the distance between the "this.p0" to each point in the list

        if (!geoPointList.isEmpty()) {
            for (var geoPoint : geoPointList) {
                geoPointDistance = this.p0.distance(geoPoint.point);
                if (geoPointDistance < minDistance) {
                    minDistance = geoPointDistance;
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }
}
