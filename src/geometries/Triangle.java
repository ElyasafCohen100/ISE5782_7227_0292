package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.stream.Collectors;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;

public class Triangle extends Polygon {

    /**
     * Constructor to initialize triangle
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + this.vertices.get(0) + ","+ this.vertices.get(1) + ","+ this.vertices.get(2)+
                '}';
    }

//    @Override
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

//        // we take three vectors from the same starting point and connect them to the triangle's vertices
//        // we get a pyramid
//
//        //Check if the ray intersect the plane.
//        if (this.plane.findGeoIntersectionsHelper(ray) == null) {
//            return null;
//        }
//        // the three vectors from the same starting point
//        Vector v1 = this.vertices.get(0).subtract(ray.getP0());
//        Vector v2 = this.vertices.get(1).subtract(ray.getP0());
//        Vector v3 = this.vertices.get(2).subtract(ray.getP0());
//
//        // עושים מכפלה ווקטורית כדי לקבל נורמל על כל "פאה" של הפרמידה שנוצרה לי ומנרמלים
//        //we want to get a normal for each pyramid's face so we do the crossProduct
//        Vector n1 = v1.crossProduct(v2).normalize();
//        Vector n2 = v2.crossProduct(v3).normalize();
//        Vector n3 = v3.crossProduct(v1).normalize();
//
//        // the ray's vector  - it has the same starting point as the three vectors from above
//        Vector v = ray.getDir();
//
//        // check if the vector's direction (from Subtraction between the ray's vector to each vector from above) are equal
//        // if not - there is no intersection point between the ray and the triangle
//        if ((alignZero(v.dotProduct(n1)) > 0 && alignZero(v.dotProduct(n2)) > 0 && alignZero(v.dotProduct(n3)) > 0) ||
//                (alignZero(v.dotProduct(n1)) < 0 && alignZero(v.dotProduct(n2)) < 0 && alignZero(v.dotProduct(n3)) < 0)){
//
//            return this.plane.findGeoIntersectionsHelper(ray);
//        }
//        return null;
//    }

    /**
     * Return list of intersections point between a ray and a triangle
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersections point
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        //Check if the ray intersect the plane.
        List<GeoPoint> intersections = plane.findGeoIntersections(ray);
        if (intersections == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        //Check every side of the triangle
        double s1 = v.dotProduct(v1.crossProduct(v2));

        if (isZero(s1)) return null;

        double s2 = v.dotProduct(v2.crossProduct(v3));

        if (isZero(s2)) return null;

        double s3 = v.dotProduct(v3.crossProduct(v1));

        if (isZero(s3)) return null;

        if (!((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))) return null;

        return List.of(new GeoPoint(this,intersections.get(0).point));
    }
}
