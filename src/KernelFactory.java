import java.util.Objects;

/**
 * class for creating Prewitt filter kernels
 */
public class KernelFactory {

    /**
     * creates Vertical Prewitt Kernel
     * @return 2d integer array representation of vertical Prewitt Kernel
     */
    public static int[][] createVerticalPrewittKernel(){
        int[][] vP={{-1, 0, 1},
                    {-1, 0, 1},
                    {-1, 0, 1}};
        return vP;
       }

    /**
     * creates horizontal Prewitt Kernel
     * @return 2d integer array representation of horizontal Prewitt Kernel
     */
    public static int[][] createHorizontalPrewittKernel(){
        int[][] hP={{-1,-1,-1},
                    {0, 0, 0},
                    {1, 1, 1}};
        return hP;
    }

    //TODO which kernel is that? Where is that from?
    /**
     * creates Vertical Prewitt Kernel
     * @return 2d integer array representation of vertical Prewitt Kernel
     */
    public static int[][] createBoringKernel(){
        //implement
        int[][] bK={{1,1,1},
                {1, 1, 1},
                {1, 1, 1}};
        return bK;
    }

    /**
     * creates box filter Kernel
     * @return 2d integer array representation of box filter kernel
     */
    public static double[][] createBoxFilter(int size){
        assert (size % 2) != 0 : "size has to be uneven";
        assert (size >= 3) : "size is 0, has to be 3 or more ";

        // new size Array
        double[][] bF = new double[size][size];

        for(int i=0; i<size; i++ ) {
            for(int j=0;j<size; j++) {
                // fill each with value 1 divided by Element Count
                bF[i][j] = 1.0/(size*size);
            }
        }
        return bF;
    }

    /**
     * IMPORTANT
     * This is our workaround for javas static typing restrictions. Please see Readme.md for further information.
     *
     * creates box filter Kernel
     * @return 2d Numbers array representation of box filter kernel
     */
    public static Number[][] createBoxFilter_Number(int size){
        assert (size % 2) != 0 : "size has to be uneven";
        assert (size >= 3) : "size is 0, has to be 3 or more ";
        // new size Array
        Number[][] bF = new Number[size][size];
        for(int i=0; i<size; i++ ) {
            for(int j=0;j<size; j++) {
                // fill each with value 1 divided by Element Count
                bF[i][j] = 1.0/(size*size);
            }
        }
        return bF;
    }


    //TODO
    public static int[][] createINTBoxFilter(int size) {
        assert (size % 2) != 0 : "size has to be uneven";
        assert size >= 3 : "size is 0, has to be 3 or more "; //I changed this expression, used to say size <3

        // new size Array
        int[][] bF = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // fill each with value 1 divided by Element Count
                (bF[i][j]) = (int) (1.0 / (size * size)); //TODO PROBLEM
            }
        }
        return bF;
    }
    /**
     * method to print 2d integer array representation of PGM image.
     * Used for testing
     * @param arr 2d integer array representation of PGM image.
     */
    public static void print2DArray(double arr[][]){

        assert (arr != null & Objects.requireNonNull(arr).length > 0): "array is empty";

        for (double[] doubles : arr) {
            for (int j = 0; j < arr.length; j++) {
                // fill each with value 1 divided by Element Count
                System.out.println(" " + doubles[j]);
            }
        }
    }

    //TODO delete main
    public static void main(String[] args) {
        System.out.println("Hello World!");

        //double[][] a = createBoxFilter(2);
        double[][] failure = {{}};
        print2DArray(failure);
       // print2DArray(createHorizontalPrewittKernel());
        // print2DArray(createBoxFilter(3));
    }
}
