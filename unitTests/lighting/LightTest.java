package lighting;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class LightTest {

    private Scene scene1 = new Scene.SceneBuilder("Test scene").build();
    private Scene scene2 = new Scene.SceneBuilder("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).build();

    private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);

    private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(200, 200) //
            .setVPDistance(1000);

    private Point[] p = { // The Triangles' vertices:
            new Point(-110, -110, -150), // the shared left-bottom
            new Point(95, 100, -150), // the shared right-top
            new Point(110, -110, -150), // the right-bottom
            new Point(-75, 78, 100)}; // the left-top

    private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
    private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light

    private Material material = new Material().setkD(0.5).setkS(0.5).setnShininess(300);
    private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
    private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
    private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new PointLight(spCL, spPL).setkL(0.001).setkQ(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setkL(0.001).setkQ(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new DirectionalLight(trCL, trDL));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new PointLight(trCL, trPL).setkL(0.001).setkQ(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new SpotLight(trCL, trPL, trDL).setkL(0.001).setkQ(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a narrow spot light
     */
    @Test
    public void sphereSpotSharp() {
        scene1.getGeometries().add(sphere);
        scene1.getLights()
                .add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setkL(0.001).setkQ(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a narrow spot light
     */
    @Test
    public void trianglesSpotSharp() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new SpotLight(trCL, trPL, trDL).setkL(0.001).setkQ(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a one sphere lighted by a directional light, PointLight and spotlight.
     */
    @Test
    public void sphereIntegrationTest() {
        scene1.getGeometries().add(sphere);
        DirectionalLight directionalLight = new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1));
        PointLight pointLight = new PointLight(new Color(500, 300, 0), new Point(-300, 200, 100))//
                .setkL(0.00001).setkQ(0.000001);
        SpotLight spotLight = new SpotLight(new Color(500, 300, 0), new Point(250, 200, 100), new Vector(-1, -1, -1));

        scene1.getLights().add(directionalLight);
        scene1.getLights().add(pointLight);
        scene1.getLights().add(spotLight);

        ImageWriter imageWriter = new ImageWriter("sphereIntegrationTest", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a directional light, PointLight and spotlight.
     * and the sphere we have added
     */
    @Test
    public void multipleGeometriesAndLightings() {

        scene2.getGeometries().add(
                triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
                triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),




                new Sphere(new Point(70,50,22),15).setEmission(new Color(BLUE).reduce(2))
                .setMaterial(new Material().setkD(0.97).setkS(1.0).setnShininess(300))
                );






        //== because we are in List that get only single value each time we separated to some section==//
        scene2.getLights()
                .add(new SpotLight(new Color(350, 350, 0), new Point(10, -10, -130), new Vector(-2, -2, -1))
                        .setkL(0.0001)
                        .setkQ(0.000005));

        scene2.getLights()
                .add(new PointLight(new Color(0, 300, 350), new Point(10, -10, -130))
                        .setkL(0.0005).setkQ(0.0002));

        scene2.getLights()
                .add(new DirectionalLight(new Color(120, 5, 5), new Vector(0, 0, -1)));

        //=== another spotlight for our new sphere ===//
        scene2.getLights()
                .add(new SpotLight(new Color(400,250,800), new Point(2.5,6.3,7.5),new Vector(2.6,4.8,6.3)));

        ImageWriter imageWriter = new ImageWriter("multipleGeometriesAndLightings", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }
}
