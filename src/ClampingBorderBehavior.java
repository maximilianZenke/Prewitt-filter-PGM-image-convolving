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

    public int getPixelValue(int i, int j, int[][] image) {

        assert( image != null && image.length > 0): "image array is empty";

        int width_i = image[0].length-1;
        int height_i = image.length-1;

        //TODO this might be a better way to go
        /*
        if( (i<0) || (j<0) ){
            i = 0;
            j = 0;
         }
         ...
         */

        if (i < 0) {
            i = 0;
            }
        if (i > height_i){
            i = height_i;
        }
        if (j < 0) {
            j = 0;
        }
        if (j>width_i){
            j = width_i;
        }
        return image[i][j];
    }

    //TODO delete main
    public static void main(String[] args) {

        int[][] ara = {{5,10,14,10,2},
                        {7,9,12, 0, 5},
                        {-1,12,2, 0, 4}};
        System.out.println(ara[0][ara[0].length-1]);
        System.out.println(new ClampingBorderBehavior().getPixelValue(3, 2, ara));
    }
}
