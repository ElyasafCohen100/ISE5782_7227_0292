package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * an abstract class for finding the intersection point between the ray and the complex object
 *
 * As we said - because there is no necessary to implement the "getNormal" function in a complex object,
 * we separated the Geometry interface (Targil 5) into two interfaces (ISP principle), and this is one of them.
 * (from Targil 6 on, it's becomes to be an abstract class)
 *
 * We define here only the "findIntersection" function to find intersection points between the ray
 * and the complex object
 */
public abstract class Intersectable {
    /*
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersection Point between the ray and the object
     */
    public final List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    //======== the NVI design pattern =======//
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * this class has been written because we want to know the specific geometry the ray cross it over
     * because we added the emission light for each geometry and if we want to calculate the color at the point
     * we have to mind the geometry's color (this class is PDS)
     */
    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;

        //--- constructor ---//
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
