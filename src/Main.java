public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] ara = {{10,13,12,7},
                        {13,16,8,6},
                        {14,13,12,5},
                        {13,12,11,9}};

        KernelFactory factory = new KernelFactory();
        int [][] newk = factory.createBoringKernel();
        borderBehavior bor = new ZeroPaddingBorderBehavior();

        System.out.println(get_filtered_pixel(3,1,ara,newk,bor));

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

