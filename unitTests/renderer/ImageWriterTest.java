package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    //==== the size of the view plane =====//
    int nX = 800;
    int nY = 500;

    //Color yellowColor = new Color(java.awt.Color.YELLOW);
    Color yellowColor = new Color(255d, 255d, 0d); // Yellow is a combination of red & green (for the yellow Square)
    Color redColor = new Color(255d, 0d, 0d); // if r=255 the color is red (for the net)

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("testYellow", nX, nY);
        //=== running on the view plane===//
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                //=== create the net ===//
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, redColor);
                }
                else {
                    imageWriter.writePixel(i, j, yellowColor);
                }
            }
        }
    }
}