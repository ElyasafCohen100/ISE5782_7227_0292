package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracer {
    protected Scene scene;

    protected RayTracer(Scene scene){
        this.scene = scene;
    }
    abstract Color traceRay(Ray ray);
}
