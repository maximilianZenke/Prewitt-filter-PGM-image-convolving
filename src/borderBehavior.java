/**
 * abstract class used to later specify borderbehavior when convolving image
 */
public abstract class borderBehavior {
    /**
     * method to return pixel value of given image at given coordinate
     * @param i x coordinate in 2d integer array representation of PGM image
     * @param j y coordinate in 2d integer array representation of PGM image
     * @param image 2d integer array representation of PGM image
     * @return integer value of pixel at given coordinate
     */
    public abstract int getPixelValue(int i, int j,  int[][]image);
}
