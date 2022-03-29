package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Class to implement Camera to render the picture
 */
public class Camera {

    private Point _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;
    private double _distance;

    private double _width;
    private double _height;

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

        _p0=p0;
        _vTo =vTo.normalize();
        _vUp =vUp.normalize();
        _vRight =vTo.crossProduct(vUp);
    }

    public Point getP0() { return _p0; }

    public Vector getvT0() { return _vTo;}

    public Vector getvUp() { return _vUp;}

    public Vector getvRight() { return _vRight; }

    public double getWidth() { return _width;}

    public double getHeight() {return _height;}

    //Chaining methods.

    /**
     * Set view plane distance
     * @param distance - the distance between the camara and view plane
     * @return The camara distance
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * Set view plane size
     * @param width width of the view plane
     * @param height height of the view plane
     * @return The view plane size
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height =height;
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
        double Ry= (double) _height /Ny;
        double Rx = (double) _width /Nx;

        //image center//
        Point Pc = _p0.add(_vTo.scale(_distance));
        Point Pij =Pc;
        double Yi = - (i-((Ny - 1)/2d))* Ry;
        double Xj = (j-((Nx - 1)/2d))* Rx;

        //move to middle of pixel i,j
        if (!isZero(Xj)) { // vRight need to be scaled with xj, so it cannot be zero
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if (!isZero(Yi)) {// vUp need to be scaled with yi, so it cannot be zero
            Pij = Pij.add(_vUp.scale(Yi));
        }
        return new Ray(_p0, Pij.subtract(_p0));
    }
}
