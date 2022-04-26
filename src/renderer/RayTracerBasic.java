package renderer;

import geometries.Geometries;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracer {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    Color traceRay(Ray ray) {
        Geometries geometries = super.scene.getGeometries();

        return null;
    }
}
