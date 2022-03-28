package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p1=new Point(1,2,3);
    Point p2=new Vector(0,-2,1);

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}
     */
    @Test
    void add() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Point(0,0,0),p1.add(new Vector(-1, -2, -3)),"ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}
     */
    @Test
    void subtract() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1, 1, 1),new Point(2, 3, 4).subtract(p1),"ERROR: Point - Point does not work correctly");
    }
    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}
     */
    @Test
    void distanceSquared() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(21 ,p1.distanceSquared(p2),"ERROR: distanceSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}
     */
    @Test
    void distance() {

        // ============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(21) ,p1.distance(p2),"ERROR: distanceSquared() wrong value");
    }
}