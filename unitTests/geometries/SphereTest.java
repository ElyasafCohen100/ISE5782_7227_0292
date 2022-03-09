package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {

        Sphere sphere = new Sphere(new Point(0, 0, 0), 5);

        // ============ Equivalence Partitions Tests ==============//
        // TC01: tests for calculation of normal to the plane//
        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 5)), "ERROR: The calculation of normal to the Sphere is not calculated correctly");
    }
}