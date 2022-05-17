import java.io.IOException;

public class Testing {
    public static void main(String[] args) throws IOException {

        //this is our additional testing te see if our algorithms would match the expected outcome
        //from the tutorial website. Please see Readme for further information.
        int [][] horizontalKernel = KernelFactory.createHorizontalPrewittKernel();
        int [][] verticalKernel = KernelFactory.createVerticalPrewittKernel();
        BorderBehavior clampingBorderBehavior = new ClampingBorderBehavior();
        BorderBehavior zeroPaddingBorderBehavior = new ZeroPaddingBorderBehavior();

        Image testImage = new Image();
        testImage.setImageArray("TestingPictures/prewitt1.pgm");

        Image testImageHC = testImage.convolve(horizontalKernel,clampingBorderBehavior);
        Image testImageVC = testImage.convolve(verticalKernel,clampingBorderBehavior);

        testImageHC.writeToFilename("TestingPictures/horizontalTest.pgm");
        testImageVC.writeToFilename("TestingPictures/verticalTest.pgm");
    }
}
