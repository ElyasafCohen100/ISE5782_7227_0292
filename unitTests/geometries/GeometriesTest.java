package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        Plane plane = new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(1.5, 0, 1));
        Sphere sphere = new Sphere(new Point(1, 0, 1), 1);
        Triangle triangle = new Triangle(new Point(0, 2, 0), new Point(2, 2, 0), new Point(1.5, 2, 2));
        Geometries geometries = new Geometries(plane, sphere, triangle);


        // ============ Equivalence Partitions Tests ==============
        //TC01: More then one object intersect (but not all the objects)
        Ray rayManyObjectIntersect = new Ray(new Point(1, 1.5, 1), new Vector(0, -1, 0));
        assertEquals(3, geometries.findIntersections(rayManyObjectIntersect).size(),
                "More then one object intersect (but not all the objects)");

        // =============== Boundary Values Tests ==================
        //TC10: Empty list
        Geometries geometriesEmptyList = new Geometries();
        Ray rayEmptyList = new Ray(new Point(1, 1, 1), new Vector(0, -1, 0));

        assertNull(geometriesEmptyList.findIntersections(rayEmptyList), "The List empty");

        // TC11: No intersection with the objects
        Ray rayNoIntersections = new Ray(new Point(1, -1, 1), new Vector(0, -1, 0));

        assertNull(geometries.findIntersections(rayNoIntersections), "The ray suppose not intersect the objects");

        //TC12: One object intersect
        Ray rayOneObjectIntersect = new Ray(new Point(1.5, 1.5, 0.5), new Vector(0, 1, 0));
        assertEquals(1, geometries.findIntersections(rayOneObjectIntersect).size(),
                "Suppose to be one intersection point (one object intersect)");

        //TC13: All the objects intersect
        Ray rayAllObjectIntersect = new Ray(new Point(1, 2.5, 1), new Vector(0, -1, 0));
        assertEquals(4, geometries.findIntersections(rayAllObjectIntersect).size(),
                "Suppose to be 4 intersection points");

    }
}