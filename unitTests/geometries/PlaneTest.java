package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testGetNormal() {
        Point p1 =new Point(1,2,3);
        Point p2 =new Point(4,5,6);
        Point p3 =new Point(7,8,3);

        Plane plane = new Plane(p1, p2, p3);






        assertEquals(??????);
    }
}