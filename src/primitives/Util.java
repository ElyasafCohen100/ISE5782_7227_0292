package primitives;

/**
 * Util class is used for some internal utilities, e.g. controlling accuracy
 *
 * @author Dan
 */
public abstract class Util {
    // It is binary, equivalent to ~1/1,000,000,000,000 in decimal (12 digits)
    private static final int ACCURACY = -40;

    /**
     * Empty private constructor to hide the public one
     */
    private Util() {
    }

    // double store format (bit level):
    //    seee eeee eeee (1.)mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
    // 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
    // the number is m+2^e where 1<=m<2
    // NB: exponent is stored "normalized" (i.e. always positive by adding 1023)
    private static int getExp(double num) {
        // 1. doubleToRawLongBits: "convert" the stored number to set of bits
        // 2. Shift all 52 bits to the right (removing mantissa)
        // 3. Zero the sign of number bit by mask 0x7FF
        // 4. "De-normalize" the exponent by subtracting 1023
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    /**
     * Checks whether the number is [almost] zero
     *
     * @param number the number to check
     * @return true if the number is zero or almost zero, false otherwise
     */
    public static boolean isZero(double number) {
        return getExp(number) < ACCURACY;
    }

    /**
     * Aligns the number to zero if it is almost zero
     *
     * @param number the number to align
     * @return 0.0 if the number is very close to zero, the number itself otherwise
     */
    public static double alignZero(double number) {
        return getExp(number) < ACCURACY ? 0.0 : number;
    }

    /**
     * Check whether two numbers have the same sign
     *
     * @param n1 1st number
     * @param n2 2nd number
     * @return true if the numbers have the same sign
     */
    public static boolean checkSign(double n1, double n2) {
        return (n1 < 0 && n2 < 0) || (n1 > 0 && n2 > 0);
    }

    /**
     * Provide a real random number in range between min and max
     *
     * @param min value (included)
     * @param max value (excluded)
     * @return the random value
     */
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

}
