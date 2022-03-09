package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void testZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Vector(0, 0, 0), "ERROR: zero vector does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        assertEquals(new Vector(1, 5, 1), v1.add(v3), "ERROR: add() function wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "ERROR: scale()function wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        //---- boundary case ---//
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        //--- Equilibrium partition ----//
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
    }

    /*** Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}***/
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()} (primitives.Vector)}.
     */
    @Test
    void testLengthSquared() {
        // test length..
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        assertEquals(Math.sqrt(14), v1.length(), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalized() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertFalse(v == n, "normalized() changes the vector itself");
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");

        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(n),
                "normalized vector is not in the same direction");

        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }
}