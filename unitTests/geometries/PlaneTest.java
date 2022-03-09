package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Plane plane = new Plane(p1, p2, p3);

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        Vector normalVector = v1.crossProduct(v2).normalize();

        // ============ Equivalence Partitions Tests ==============//
        // TC01: tests for calculation of normal to the plane//
        double sqrt3 = Math.sqrt(1d / 3); // the expected Normal

        //check the positive and negative direction of the normal//
        assertTrue(plane.getNormal().equals(new Vector(sqrt3, sqrt3, sqrt3)) ||
                plane.getNormal().scale(-1).equals(new Vector(sqrt3, sqrt3, sqrt3)), "ERROR: The calculation of normal to the plane is not calculated correctly");
    }
}