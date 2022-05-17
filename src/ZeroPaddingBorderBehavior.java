import java.util.Objects;

/**
 * specification of abstract class borderBehavior realizing zero padding as border behavior
 */
public class ZeroPaddingBorderBehavior extends BorderBehavior {
    /**
     * specification of getPixelValue method from abstract parent class.
     * zero padding = return 0 for coordinates i, j outside of image
     *
     * @param i x coordinate in 2d integer array representation of PGM image
     * @param j y coordinate in 2d integer array representation of PGM image
     * @param image 2d integer array representation of PGM image
     * @return int value of pixel
     */
    @Override
    public int getPixelValue(int i, int j, int [][] image) {
        int result = 0;

        assert (image != null & Objects.requireNonNull(image).length > 0) : "image array is empty";

        if (i <= image.length - 1 && i >= 0) if (j <= image[0].length - 1 && j >= 0) {
            result = image[i][j];
        }
        return result;
    }

    //TODO delete main
    public static void main(String[] args) {

        int[][] ara = {{5,10,14,10,2},
                    {7,9,12, 0, 5},
                    {-1,12,2, 0, 4}};
        System.out.println(ara.length);
        System.out.println(ara[0].length);
        System.out.println(new ZeroPaddingBorderBehavior().getPixelValue(2, 5, ara));
    }
}
