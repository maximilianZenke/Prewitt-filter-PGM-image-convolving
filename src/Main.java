public class Main {

    public static void main(String[] args) {

        // TODO javadoc -> DONE
        // TODO assertions
        // TODO beautify code
        // TODO MaxValue ( IMage ) -> DONE ( with max=225 )
        // TODO ONLY read p2! Exeption handling -> Done but not tested
        // TODO say why we chose to do kernel with double ... -> discussed but yet to write
        // TODO you talked about TWO PICS ? Only one example ( wir haben nur abb 6) -> Done





        System.out.println("Hello World!");
        int[][] ara = {{10,13,12,7},
                        {13,16,8,6},
                        {14,13,12,5},
                        {13,12,11,9}};

        KernelFactory factory = new KernelFactory();
        int [][] newk = factory.createBoringKernel();
        borderBehavior bor = new ZeroPaddingBorderBehavior();

        System.out.println(get_filtered_pixel(3,3,ara,newk,bor));
        for(int i = 0; i < ara.length; i++) {
            for(int j = 0; j < ara[0].length; j++) {
                System.out.print(get_filtered_pixel(i,j,ara,newk,bor)+" ");
            }
            System.out.println();
        }

        for(int i = 0; i < ara.length; i++) {
            for(int j = 0; j < ara[0].length; j++) {
                System.out.print(get_filtered_pixel(i,j,ara,newk,bor)+" ");
            }
            System.out.println();
        }

    }

    public static int get_filtered_pixel(int i, int j, int[][] image, int [][] kernel, borderBehavior behavior) {
        int kernel_half = ((kernel.length-1) / 2 );
        int final_pixel_value = 0;

        for(int k=-kernel_half; k<=kernel_half; k++ ) {
            for (int l = -kernel_half; l <= kernel_half; l++) {
                int kernel_factor = kernel[k+kernel_half][l+kernel_half];
                int current_pixel = behavior.getPixelValue(i+k, j+l, image);
                final_pixel_value += current_pixel * kernel_factor;
            }
        }
        return final_pixel_value;
        }
    }

