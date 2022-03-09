package primitives;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2, 3, 4);
    Vector v1 = new Vector(2, 5, 7);

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(3, p1.distanceSquared(p2), "Error: disanceSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(Math.sqrt(3), p1.distance(p2), "Error: distance() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Point(3, 7, 10), p1.add(v1), "Error: add() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Vector(-1, -1, -1), p1.subtract(p2), "Error: Subtract() wrong value");
    }
}