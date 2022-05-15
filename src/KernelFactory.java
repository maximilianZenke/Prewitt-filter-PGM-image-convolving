public class KernelFactory {

    public static int[][] createVerticalPrewittKernel(){
        //implement 
        int[][] vP={{-1, 0, 1},
                    {-1, 0, 1},
                    {-1, 0, 1}};
        return vP;
       }

    public static int[][] createHorizontalPrewittKernel(){
        //implement
        int[][] hP={{-1,-1,-1},
                    {0, 0, 0},
                    {1, 1, 1}};
        return hP;
    }
    public static int[][] createBoringKernel(){
        //implement
        int[][] bK={{1,1,1},
                {1, 1, 1},
                {1, 1, 1}};
        return bK;
    }

    public static double[][] createBoxFilter(int size){
        //implement
        assert (size % 2) != 0 : "size has to be uneven";
        assert size < 3 : "size is 0, has to be 3 or more ";

        if (size % 2 == 0){
            size -= 1;
        }

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
    public static void print2DArray(double arr[][]){
        for(int i=0; i<arr.length; i++ ) {
            for(int j=0;j<arr.length; j++) {
                // fill each with value 1 divided by Element Count
                System.out.println(" "+ arr[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
       // print2DArray(createVerticalPrewittKernel());
       // print2DArray(createHorizontalPrewittKernel());
        // print2DArray(createBoxFilter(3));




    }
}
