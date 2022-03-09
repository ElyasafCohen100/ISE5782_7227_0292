package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() {
            Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1, 10);

            // ============ Equivalence Partitions Tests ==============//
        // TC01: check the first base
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 0.5, 0)),
                "ERROR: The calculation of normal to the cylinder's first base is not calculated correctly");

        // TC02: check the second base
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 0.5, 10)),
                "ERROR: The calculation of normal to the cylinder's second base is not calculated correctly");

        // TC03: check the side of the cylinder
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 1, 5)),
                "ERROR: The calculation of normal to the cylinder's side is not calculated correctly");

        // =============== Boundary Values Tests ==================
        // TC10: check first center base normal (if p = o)
        assertEquals(cylinder._axisRay.getDir().scale(-1), cylinder.getNormal(new Point(0, 0, 0)),
                "ERROR: The calculation of normal to the first base center of the cylinder is not calculated correctly"
        );

        // TC11: check second center base normal (if p = o)
        assertEquals(cylinder._axisRay.getDir(), cylinder.getNormal(new Point(0, 0, 10)),
                "ERROR: The calculation of normal to the second base center of the cylinder is not calculated correctly");
    }
}