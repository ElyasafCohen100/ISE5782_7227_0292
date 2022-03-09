package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        Tube tube = new Tube(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1);

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Vector(0, 0, 1),
                tube.getNormal(new Point(1, 0, 1)),
                "ERROR: The calculation of normal to the tube is not calculated correctly");

        // =============== Boundary Values Tests ==================
        //Test when the point is orthogonal to the ray's head goes to the ZERO vector
        assertThrows(IllegalArgumentException.class, () -> {
                    tube.getNormal(new Point(0, 0, 1));
                },
                "ZERO vector is not allowed");
    }
}