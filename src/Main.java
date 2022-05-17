import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //set up kernels
        int [][] horizontalKernel = KernelFactory.createHorizontalPrewittKernel();
        int [][] verticalKernel = KernelFactory.createVerticalPrewittKernel();
        //TODO WTF ???
        int [][] boxKernel3 = KernelFactory.createINTBoxFilter(3);
        int [][] boxKernel11 = KernelFactory.createINTBoxFilter(11);
        int [][] boxKernel27 = KernelFactory.createINTBoxFilter(27);

        //set up border behaviours
        BorderBehavior clampingBorderBehavior = new ClampingBorderBehavior();
        BorderBehavior zeroPaddingBorderBehavior = new ZeroPaddingBorderBehavior();

        //Pictures...
        Image bild1 = new Image();
        bild1.setImageArray("Pictures/Bild 1.pgm");
        Image bild2 = new Image();
        bild2.setImageArray("Pictures/Bild 2.pgm");

        //colvolve bild 1
        //TODO wieso anders Ergebnis?
        Image bild1ConvolvedHC = bild1.convolve(horizontalKernel,clampingBorderBehavior);
        Image bild1ColvolcedVC = bild1.convolve(verticalKernel, clampingBorderBehavior);
        Image bild1ColvolcedHZ = bild1.convolve(horizontalKernel, zeroPaddingBorderBehavior);
        Image bild1ColvolcedVZ = bild1.convolve(verticalKernel, zeroPaddingBorderBehavior);

        Image bild2ConvolvedB3C = bild2.convolve(boxKernel3,clampingBorderBehavior);
        Image bild2ConvolvedB11C = bild2.convolve(boxKernel11, clampingBorderBehavior);
        Image bild2ConvolvedB27C = bild2.convolve(boxKernel27, clampingBorderBehavior);

        //write bild 1 convolved to file
        bild1ConvolvedHC.writeToFilename("Clamped/1_horizontal.pgm");
        bild1ColvolcedVC.writeToFilename("Clamped/1_vertical.pgm");
        bild1ColvolcedHZ.writeToFilename("ZeroPadded/1_horizontal.pgm");
        bild1ColvolcedVZ.writeToFilename("ZeroPadded/1_vertical.pgm");

        //write bild 2 colvolved to file
        bild2ConvolvedB3C.writeToFilename("Clamped/2_5.pgm");
        bild2ConvolvedB11C.writeToFilename("Clamped/2_11.pgm");
        bild2ConvolvedB27C.writeToFilename("Clamped/2_27.pgm");

        //This is the testing of our workaround for the problem with doubles and integers regarding boxFilters.
        //Please see Readme.md for further information

        Number[][] NumberBoxKernel3 = KernelFactory.createBoxFilter_Number(3);
        Number[][] NumberBoxKernel11 = KernelFactory.createBoxFilter_Number(11);
        Number[][] NumberBoxKernel27 = KernelFactory.createBoxFilter_Number(27);

        Image numberImage = new Image ();
        numberImage.setImageArray("Pictures/Bild 2.pgm");

        //clamping test
        Image numberImageConvolved3 = numberImage.convolve_Number(NumberBoxKernel3,clampingBorderBehavior);
        Image numberImageConvolved11 = numberImage.convolve_Number(NumberBoxKernel11,clampingBorderBehavior);
        Image numberImageConvolved27 = numberImage.convolve_Number(NumberBoxKernel27,clampingBorderBehavior);

        //zero test
        Image numberImageConvolvedZeroPadding3 = numberImage.convolve_Number(NumberBoxKernel3,zeroPaddingBorderBehavior);

        numberImageConvolved3.writeToFilename("Clamped/number_workaround_2_5.pgm");
        numberImageConvolved11.writeToFilename("Clamped/number_workaround_2_11.pgm");
        numberImageConvolved27.writeToFilename("Clamped/number_workaround_2_27.pgm");

        numberImageConvolvedZeroPadding3.writeToFilename("Clamped/number_workaround_2_5_zeroPadding.pgm");

        /*
        int[][] ara = {{10,13,12,7},
                        {13,16,8,6},
                        {14,13,12,5},
                        {13,12,11,9}};



        KernelFactory factory = new KernelFactory();
        int [][] newk = factory.createBoringKernel();
        BorderBehavior bor = new ZeroPaddingBorderBehavior();

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

    public static int get_filtered_pixel(int i, int j, int[][] image, int [][] kernel, BorderBehavior behavior) {
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

         */
    }
}

