package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Class to implement Camera to render the picture
 *
 * we want to get the camera to our project, so we will open the Camera class
 */
public class Camera {

    private Point p0; // the center's camera point
    private Vector vTo; // the vector towards the view plane
    private Vector vUp; // vector to camera's up direction
    private Vector vRight; // vector to camera's right direction
    private double distance; // the distance between the camera and the view plane

    //=== the view plane size ===//
    private double width;
    private double height;

    private ImageWriter imageWriter;
    private RayTracer rayTracer;

    //===== constructor to initialize camera =======//
     /**
     * @param p0  - camera's location
     * @param vTo - camera's towards direction
     * @param vUp - camera's up direction
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {

        //The vectors vTo and vUp must be orthogonal - the camera's struct//
        if(!isZero(vTo.dotProduct(vUp))){
            throw new IllegalArgumentException("vTo and vUp are not orthogonal");
        }

        this.p0 =p0;
        this.vTo =vTo.normalize();
        this.vUp =vUp.normalize();
        this.vRight =vTo.crossProduct(vUp);
    }

    public Point getP0() { return this.p0; }

    public Vector getvT0() { return this.vTo;}

    public Vector getvUp() { return this.vUp;}

    public Vector getvRight() { return this.vRight; }

    public double getWidth() { return this.width;}

    public double getHeight() {return this.height;}

    //Chaining methods.

    /**
     * Set view plane distance
     * @param distance - the distance between the camara and view plane
     * @return The camara distance
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Set view plane size
     * @param width width of the view plane
     * @param height height of the view plane
     * @return The view plane size
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height =height;
        return this;
    }

    /**
     * Constructs a ray from Camera location throw the center of a
     * pixel (i,j) in the view plane .
     *
     * @param Nx number of pixels in a row of view plane
     * @param Ny number of pixels in a column of view plane
     * @param j  number of the pixel in a row
     * @param i  number of the pixel in a column
     * @return The ray through pixel's center
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        // Ratio (pixel width & height) - for find the center of single pixel where the ray cross //
        double Ry= (double) this.height /Ny;
        double Rx = (double) this.width /Nx;

        //=== image (pixel[i,j]) center === //
        Point Pc = this.p0.add(this.vTo.scale(this.distance));
        Point Pij =Pc;
        double Yi = - (i-((Ny - 1)/2d))* Ry;
        double Xj = (j-((Nx - 1)/2d))* Rx;

        //move to middle of pixel [i,j]
        if (!isZero(Xj)) { // vRight need to be scaled with xj, so it cannot be zero
            Pij = Pij.add(this.vRight.scale(Xj));
        }
        if (!isZero(Yi)) {// vUp need to be scaled with yi, so it cannot be zero
            Pij = Pij.add(this.vUp.scale(Yi));
        }
        return new Ray(this.p0, Pij.subtract(this.p0));
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public void renderImage() {
        if (this.imageWriter == null)
            throw new UnsupportedOperationException("Missing imageWriter");
        if (this.rayTracer == null)
            throw new UnsupportedOperationException("Missing rayTracerBase");

        for (int i = 0; i < this.imageWriter.getNy(); i++) {
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                Color color = castRay(j,i);
                this.imageWriter.writePixel(j, i, color);
            }
        }
    }

    public void printGrid(int interval, Color color) {
        //=== running on the view plane===//
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                //=== create the net ===//
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    public void writeToImage() {
        this.imageWriter.writeToImage();
    }

    private Color castRay(int j,int i){
        Ray ray = constructRay(
                this.imageWriter.getNx(),
                this.imageWriter.getNy(),
                j,
                i);
        return this.rayTracer.traceRay(ray);
    }
}
