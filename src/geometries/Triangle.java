package geometries;

import primitives.Point;
//Create a class for representation a Triangle
public class Triangle extends Polygon{

    // This is the constructor of the class Triangle. It is calling the constructor of the superclass Polygon.
    public Triangle(Point p1,Point p2, Point p3) {
        super(p1, p2,p3);
    }
}
