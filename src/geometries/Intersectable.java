package geometries;
import primitives.*;
import java.util.List;

/**
 *  interface for finding the intersection point between the ray and the complex object
 *
 * As we said - because there is no necessary to implement the "getNormal" function in a complex object,
 * we separated the Geometry interface into two interfaces (ISP principle), and this is one of them.
 *
 * We define here only the "findIntersection" function to find intersection points between the ray
 * and the complex object
 */
public interface Intersectable {
    /*
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersection Point between the ray and the object
     */
   List<Point> findIntersections(Ray ray);
}
