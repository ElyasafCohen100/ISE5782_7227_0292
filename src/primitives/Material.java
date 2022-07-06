package primitives;

//====== the class Material is for representing the object material type =====//

public class Material {
    // the Diffuse light factor of the object material type( מקדם הנחתה דפיוזי של סוג החומר של האובייקט) //
    public Double3 kD = new Double3(0, 0, 0);

    // the specular light factor of the object material type( מקדם הנחתה ספקולרי של סוג החומר של האובייקט) //
    public Double3 kS = new Double3(0, 0, 0);

    // the shininess factor of the object material type//
    public int nShininess = 0;

    public Double3 kT =new Double3(0,0,0); // the factor of transparency (מקדם שקיפות)
    public Double3 kR = new Double3(0,0,0); // the factor of reflection (מקדם השתקפות)

    /**
     * set kT function - the transparency factor (Double3)
     * @param kT
     * @return
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * set kT function - the transparency factor (double)
     * @param kT
     * @return
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * set kR function - the reflection factor (Double3)
     * @param kR
     * @return
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * set kR function - the reflection factor (double)
     * @param kR
     * @return
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**,
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (Double3)
     * @return
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (double)
     * @return
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * set kS function - the specular light factor
     *
     * @param kS light factor (Double3)
     * @return
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set kS function the specular light factor
     *
     * @param kS light factor (double)
     * @return
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set the shininess factor of the material
     *
     * @param nShininess shininess factor of the material (int)
     * @return this (Material)
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
