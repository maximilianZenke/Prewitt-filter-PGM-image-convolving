import java.io.IOException;

public class Testing {
    public static void main(String[] args) throws IOException {
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
