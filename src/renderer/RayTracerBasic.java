package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracer {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    Color traceRay(Ray ray) {
        return null;
    }
}
