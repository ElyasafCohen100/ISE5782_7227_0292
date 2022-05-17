package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

import java.util.Objects;

/**
 * an abstract class for finding the Normal of any geometry shape
 * <p>
 * As we said - because there is no necessary to implement the "getNormal" function in a complex object,
 * we separated the Geometry interface (Targil 5) into two interfaces(ISP principle).
 * (from Targil 6 on, it's becomes to be an abstract class)
 * <p>
 * so here we have left the "getNormal" function to find the Normal of any geometry shape
 */

public abstract class Geometry extends Intersectable {

    private Color emission = Color.BLACK; // the geometry's default color
    private Material material = new Material(); // the material the geometry has made of

    /**
     * getEmission function
     *
     * @return the geometry's color
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * setEmission function
     *
     * @param emission
     * @return
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Get material of the geometry
     *
     * @return Material of the geometry
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Set material of the geometry
     *
     * @param material the Material of the geometry
     * @return the geometry itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emission);
    }

    /**
     * return the normal to the vector in specific point
     *
     * @param point
     * @return the normal to the vector in specific point
     */
    public abstract Vector getNormal(Point point);
}
