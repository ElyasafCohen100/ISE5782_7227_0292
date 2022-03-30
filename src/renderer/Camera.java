package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Class to implement Camera to render the picture
 */
public class Camera {

    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;

    private double width;
    private double height;

    /**
     * constructor to initialize camera
     *
     * @param p0  - camera's location
     * @param vTo - camera's towards direction
     * @param vUp - camera's up direction
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {

        //The vectors vTo and vUp must be orthogonal//
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
     * pixel (i,j) in the view plane.
     *
     * @param Nx number of pixels in a row of view plane
     * @param Ny number of pixels in a column of view plane
     * @param j  number of the pixel in a row
     * @param i  number of the pixel in a column
     * @return The ray through pixel's center
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        // Ratio (pixel width & height)//
        double Ry= (double) this.height /Ny;
        double Rx = (double) this.width /Nx;

        //image center//
        Point Pc = this.p0.add(this.vTo.scale(this.distance));
        Point Pij =Pc;
        double Yi = - (i-((Ny - 1)/2d))* Ry;
        double Xj = (j-((Nx - 1)/2d))* Rx;

        //move to middle of pixel i,j
        if (!isZero(Xj)) { // vRight need to be scaled with xj, so it cannot be zero
            Pij = Pij.add(this.vRight.scale(Xj));
        }
        if (!isZero(Yi)) {// vUp need to be scaled with yi, so it cannot be zero
            Pij = Pij.add(this.vUp.scale(Yi));
        }
        return new Ray(this.p0, Pij.subtract(this.p0));
    }
}
