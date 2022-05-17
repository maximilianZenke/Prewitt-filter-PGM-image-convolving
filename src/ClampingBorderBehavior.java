import java.util.ArrayList;
import java.util.Objects;

/**
 * specification of abstract class borderBehavior realizing clamping as border behavior
 */
public class ClampingBorderBehavior extends BorderBehavior {
    /**
     * specification of getPixelValue method from abstract parent class.
     * Clamping = return next edge pixel value for coordinates i, j outside of image
     *
     * @param i x coordinate in 2d integer array representation of PGM image
     * @param j y coordinate in 2d integer array representation of PGM image
     * @param image 2d integer array representation of PGM image
     * @return int value of pixel
     */
    @Override
    public int getPixelValue(int i, int j, int[][] image) {

        assert( image != null & Objects.requireNonNull(image).length > 0): "image array is empty";


        int width_i = image[0].length-1;
        int height_i = image.length-1;


        /*
        Several if Statements improve Stability compared to + and -1 for new border Values
        Especially if Negative Values greater than -1 or very Large filters should be Applied.
        */
        if (i < 0) {
            i = 0;
        }

        if (j < 0) {
            j = 0;
        }

        if (i > height_i){
            i = height_i;
        }

        if (j>width_i){
            j = width_i;
        }

        return image[i][j];
    }
}
