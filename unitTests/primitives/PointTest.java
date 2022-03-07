package primitives;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point (2, 3, 4);
    Vector v1 = new Vector(2,5,7);

    @Test
    void testDistanceSquared() {
        assertEquals(3,p1.distanceSquared(p2),"Error: disanceSquared() wrong value");
    }

    @Test
    void testDistance(){
        assertEquals(Math.sqrt(3),p1.distance(p2),"Error: distance() wrong value");
    }

    @Test
    void testAdd() {
       assertEquals(new Point(3,7,10), p1.add(v1),"Error: add() wrong value");
    }

    @Test
    void testSubtract() {
        assertEquals(new Vector(-1,-1,-1),p1.subtract(p2),"Error: Subtract() wrong value");
    }
}