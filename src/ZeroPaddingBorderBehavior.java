/**
 * specification of abstract class borderBehavior realizing zero padding as border behavior
 */
public class ZeroPaddingBorderBehavior extends borderBehavior{
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

        assert( image != null && image.length > 0): "image array is empty";

        if (i > image.length-1 || i < 0){
            return 0;
        }
        else if(j > image[0].length-1 || j < 0){
            return 0;
        }
        else return image[i][j];
    }

    //TODO fix multiple return statements
    //possible fix for multiple return statements but not tested yet:
    /*
    @Override
    public int getPixelValue(int i, int j, int [][] image) {

        int tmpImage = image[i][j];

        if (i > image.length-1 || i < 0){
            tmpImage = 0;
        }
        else if(j > image[0].length-1 || j < 0){
             tmpImage = 0;
        }
        else return tmpImage;
    }
     */

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
