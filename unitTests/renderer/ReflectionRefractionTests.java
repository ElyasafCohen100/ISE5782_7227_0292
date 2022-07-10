package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        this.scene.getGeometries().add( //

                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkS(0.5).setkS(0.5).setnShininess(100)));

        this.scene.getLights().add( //
                new SpotLight(new Color(1000, 600, 0),
                        new Point(-100, -100, 500),
                        new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        // Setting the ambient light of the scene to be white with a brightness of 0.1.
        this.scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        this.scene.getGeometries().add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),

                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),

                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(1)),

                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(0.5)));

        this.scene.getLights().add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setkL(0.00001).setkQ(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        this.scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        this.scene.getGeometries().add( //
                new Triangle(new Point(-150, -150, -115),
                        new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(60)), //

                new Triangle(new Point(-150, -150, -115),
                        new Point(-70, 70, -140),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(60)), //

                new Sphere(new Point(60, 50, -50), 30d)
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).
                                setnShininess(30).setkT(0.6)));

        scene.getLights().add(new SpotLight(new Color(700, 400, 400),
                new Point(60, 50, 0),
                new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of five objects lighted by a spot light and point light
     * to show all the effects in one picture
     */
    @Test
    public void reflectionRefractionFiveObjectsTest() {
        Camera camera = new Camera(new Point(0, 0, 2000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                //
                .setVPSize(200, 200).setVPDistance(1000);

        this.scene.setAmbientLight(new AmbientLight(new Color(YELLOW), new Double3(0.15)));

        this.scene.getGeometries().add( //
                new Triangle(new Point(-150, -150, -115),
                        new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(60)), //

                new Triangle(new Point(-150, -150, -115),
                        new Point(-70, 70, -140),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(60)), //

                new Sphere(new Point(60, 50, -50), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6)),

                new Sphere(new Point(-50, -100, 100), 25.7) //
                        .setEmission(new Color(green)) //
                        .setMaterial(new Material().setkD(0.002).setkS(0.2)
                                .setnShininess(30).setkT(0.9)),

                new Sphere(new Point(-50, -80, 100), 17) //
                        .setEmission(new Color(green)) //
                        .setMaterial(new Material().setkD(0.002).setkS(0.2)
                                .setnShininess(30).setkT(0.9)),

                new Sphere(new Point(30, -60, 100), 22) //
                        .setEmission(new Color(cyan)) //
                        .setMaterial(new Material().setkD(0.002).setkS(0.2)
                                .setnShininess(30).setkT(0.9)),

                new Sphere(new Point(-60, 50, 100), 10) //
                        .setEmission(new Color(yellow)) //
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(2).setkT(0.8)));

        this.scene.getLights().add(new SpotLight(new Color(700, 400, 400),
                new Point(30, 25, 0),
                new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));

        this.scene.getLights().add(new PointLight(new Color(160, 80, 240),
                new Point(-100, -100, 100))//
                .setkL(0.00000000001).setkQ(0.0000000001));

        ImageWriter imageWriter = new ImageWriter("reflectionRefractionFiveObjectsTest", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }


    //=========== MINI PROJECT TEST ============//
    @Test
    public void GlassCube() {

        Scene scene = new Scene.SceneBuilder("MINI_PROJECT_TEST1").build();
        Camera camera = (new Camera(new Point(0, 0, -2000),
                new Vector(0, 0, 1),
                new Vector(0, 1, 0)))
                .setVPDistance(1000).setVPSize(150, 150);


        scene.setAmbientLight(new AmbientLight(Color.BLACK, new Double3(0)));

        scene.getGeometries().add(new Polygon(
                        new Point(0, 0, 0),
                        new Point(0, 70, 0),
                        new Point(-50, 70, 50),
                        new Point(-50, 0, 50))
                        .setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0)),

                new Polygon(
                        new Point(0, 70, 0),
                        new Point(-50, 70, 50),
                        new Point(0, 70, 100),
                        new Point(50, 70, 50))
                        .setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0)),

                new Polygon(
                        new Point(-50, 0, 50),
                        new Point(-50, 70, 50),
                        new Point(0, 70, 100),
                        new Point(0, 0, 100))
                        .setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0)),

                new Polygon(
                        new Point(0, 0, 100),
                        new Point(0, 70, 100),
                        new Point(50, 70, 50),
                        new Point(50, 0, 50)).setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0)),

                new Polygon(
                        new Point(50, 0, 50),
                        new Point(50, 70, 50),
                        new Point(0, 70, 0),
                        new Point(0, 0, 0))
                        .setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0)),

                new Polygon(
                        new Point(0, 0, 0),
                        new Point(-50, 0, 50),
                        new Point(0, 0, 100),
                        new Point(50, 0, 50))
                        .setEmission(new Color(105, 105, 105))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30).setkT(0.6).setkR(0))

                , new Sphere(new Point(0, 35, 50), 25).setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(100))

                ,
                new Plane(new Point(0, -5, 0), // the lower plane
                        new Vector(0, 1, 0))
                        .setEmission(new Color(DARK_GRAY))
                        .setMaterial(new Material().setkD(0.2).setkS(0)
                                .setnShininess(50).setkT(0).setkR(0.8))

                ,
                new Sphere(new Point(-100, 35, 0), 20) // the right sphere
                        .setEmission(new Color(15, 100, 0))
                        .setMaterial(new Material().setkD(0.3).setkS(0.5)
                                .setnShininess(900).setkT(0).setkR(0.1))
                ,
                new Sphere(new Point(100, 35, 0), 30) // the left sphere
                        .setEmission(new Color(215, 200, 120))
                        .setMaterial(new Material().setkD(0.3).setkS(0)
                                .setnShininess(900).setkT(0).setkR(0))

        );


        ImageWriter imageWriter = new ImageWriter("MINI_PROJECT_TEST", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

        scene.getLights().addAll(List.of(
                new SpotLight(new Color(1000, 600, 1000),
                        new Point(-100, 100, 100),
                        new Vector(1, -0.4, -1)).setkC(1)
                        .setkL(0.0001).setkQ(0.00005),
                new DirectionalLight(new Color(255, 215, 0),
                        new Vector(-1, -0.4, 1))));

        int p1 = 500;

        // whitin glussy
        ImageWriter imageWriter2 = new ImageWriter("GlassCube without glussy", p1, p1);

        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, -1000),
                new Vector(0, 0, 1),
                new Vector(0, -1, 0))
                .setVPDistance(1000).setVPSize(300, 300);

        scene1.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene1.getGeometries().add( //
                new Polygon(new Point(-400, -400, 150),
                        new Point(400, -400, 150),
                        new Point(400, 400, 150),
                        new Point(-400, 400, 150))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2)
                                .setnShininess(30)), //

                new Sphere(new Point(0, 0, 130), 20)
                        .setEmission(new Color(green))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)), //

                // cube:
                new Polygon(new Point(-80, -80, 150),
                        new Point(-50, -80, 150),
                        new Point(-50, -80, 120),
                        new Point(-80, -80, 120))
                        .setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)), //

                new Polygon(new Point(-50, -80, 150),
                        new Point(-50, -50, 150),
                        new Point(-50, -50, 120),
                        new Point(-50, -80, 120))
                        .setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)), //

                new Polygon(new Point(-80, -50, 150),
                        new Point(-50, -50, 150),
                        new Point(-50, -50, 120),
                        new Point(-80, -50, 120))
                        .setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)), //

                new Polygon(new Point(-80, -80, 150),
                        new Point(-80, -50, 150),
                        new Point(-80, -50, 120),
                        new Point(-80, -80, 120))
                        .setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)), //

                new Polygon(new Point(-80, -80, 120),
                        new Point(-50, -80, 120),
                        new Point(-50, -50, 120),
                        new Point(-80, -50, 120))
                        .setEmission(new Color(red))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)),

                new Cylinder(new Ray(new Point(80, -65, 150),
                        new Vector(0, 0, -1)), 20, 20)
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5)
                                .setnShininess(30)));


        scene1.getLights().addAll(List.of(new PointLight(new primitives.Color(700, 400, 400), //
                new Point(0, -80, 80)).setkC(1).setkL(4E-4).setkQ(2E-5)));
        int p2 = 700;

        ImageWriter imageWriter1 = new ImageWriter("Soft Shadow Test", p2, p2);
        ImageWriter imageWriter3 = new ImageWriter("Soft Shadow Test without soft", p2, p2);
        ImageWriter imageWriter4 = new ImageWriter("Cube scene  ", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}