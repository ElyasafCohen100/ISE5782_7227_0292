package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries extends Intersectable {
    List<Intersectable> intersectables;

    public Geometries() {
        intersectables = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... intersectables) {
        this.intersectables = new LinkedList<Intersectable>();
        Collections.addAll(this.intersectables, intersectables);
    }

    public void add(Intersectable... intersectables) {
        Collections.addAll(this.intersectables, intersectables);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        LinkedList<Point> points = null; // החזקת נקודות חיתוך עם כל אחתד מהקאומטרים
        for (var geometry : intersectables) {
            var geometryList = geometry.findIntersections(ray);
            if (geometryList != null) {
                if (points == null) {
                    points = new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
