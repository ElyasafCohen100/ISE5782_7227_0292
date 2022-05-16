package renderer;

import lighting.AmbientLight;
import org.junit.jupiter.api.Test;
import scene.Scene;

import geometries.*;
import primitives.*;

import static java.awt.Color.*; // for recognize all the static color (".COLOR")

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene.SceneBuilder("Test scene")
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), new Double3(1, 1, 1)))
				.setBackground(new Color(75, 127, 90))
				.setGeometries(new Geometries(new Sphere(new Point(0, 0, -100), 50),
						new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
						// left
						new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
						// left
						new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100)))).build(); // down
		// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
				.setVPDistance(100)
				.setVPSize(500, 500)
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(50, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}


// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid
	 */
	@Test
	public void basicRenderMultiColorTest() {
		Scene scene = new Scene.SceneBuilder("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))).build(); //

		scene.getGeometries().add( //
				new Sphere(new Point(0, 0, -100), 50),
				// up left
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
						.setEmission(new Color(GREEN)),
				// down left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(RED)),
				// down right
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
						.setEmission(new Color(BLUE)));

		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("color render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}
}

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	/**
	 * Test for XML based scene - for bonus
	 */
	//@Test
//	public void basicRenderXml() {
//		Scene.SceneBuilder scene = new Scene.SceneBuilder("XML Test scene");
//
//		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//				.setVPDistance(100) //
//				.setVPSize(500, 500)
//				.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
//				.setRayTracer(new RayTracerBasic(scene.build()));
//		camera.renderImage();
//		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
//		camera.writeToImage();
//	}