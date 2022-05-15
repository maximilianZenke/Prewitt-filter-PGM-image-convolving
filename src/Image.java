import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Image {

    /**
     * Magic number representing the binary PGM file type.
     */
    private static final String MAGIC = "P2";
    /**
     * Character indicating a comment.
     */
    private static final char COMMENT = '#';
    /**
     * The maximum gray value.
     */
    private static final int MAXVAL = 255;

    //TODO possible solution
    private static int maxVal;

    /**
     *
     * If I understand the assignment correctly, this class has a private attribute representing
     * the image as a 2d array
     * ->the readFromFile sets this.array
     * -> the write to file uses this.array and writes it into pgm file
     */
    //lets store the image in a 2d array...
    private int[][] ImageArray;

    private Image(){
        this.ImageArray = null;
        this.maxVal = 0;
    }

    //setting image Array translates to using readFromFile method
    public void setImageArray(String filename) {
        this.ImageArray = readFromFile(filename);
    }

    public void setImageArray(int[][] array){this.ImageArray = array;};


    /***
     * Function to translate 2d array using pgm file
     * @param filename file being translated
     * @return 2d array representation of filename.pgm (short[][] image)
     */
    public int[][] readFromFile(String filename){
        //original source for this code is from
        // https://github.com/prashantghimire/PGM-Image-Editing-using-Java/blob/master/getPGM.java
        // https://gist.github.com/armanbilge/3276d80030d1caa2ed7c
        int [][] image = null;
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("The File was not found: " + filename);
            System.exit(1);
        }
        try {
            String temp = scan.next();
            // verify P2 file...
            if (!temp.equals("P2")) {
                System.out.println("Image Value: " + temp);
                System.out.println("NOT A VALID PGM FILE... ONLY P2 FILES SUPPORTED HERE...");
                System.exit(1);
            }
            // remove comments...
            while (!scan.hasNextInt()) {
                scan.nextLine();
            }
            int cols = scan.nextInt();
            int rows = scan.nextInt();
            int maxVal = scan.nextInt();
            rows = rows;
            cols = cols;
            this.maxVal = maxVal; //TODO is this the right way to introduce maxval?

            image = new int[rows][cols];
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    image[i][j] = scan.nextInt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    /***
     * Function to create PGM file using 2d array
     * @param filename Name of PGM file being created
     */
    public void writeToFilename(String filename) throws IOException {
        if (maxVal >= MAXVAL)
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + MAXVAL + ".");
        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filename));
        try {
            stream.write(MAGIC.getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(ImageArray[0].length).getBytes());
            stream.write(" ".getBytes());
            stream.write(Integer.toString(ImageArray.length).getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(maxVal).getBytes());
            stream.write("\n".getBytes());


            for (int i = 0; i < ImageArray.length; ++i) {
                for (int j = 0; j < ImageArray[0].length; ++j) {
                    final int p = ImageArray[i][j]; // p = pixel


                    if (p < 0 || p > maxVal) {
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxVal + "].");
                    }

                    stream.write(Integer.toString(ImageArray[i][j]).getBytes());
                    stream.write("\n".getBytes());

                }
            }
        } finally {
            stream.close();

        }

    }

    public static int get_filtered_pixel(int i, int j, int[][] image, int [][] kernel, borderBehavior behavior) {
        int kernel_half = ((kernel.length-1) / 2 );
        int final_pixel_value = 0;

        //System.out.println("i: " + i + " | j: " + j + " ");
        for(int k=-kernel_half; k<=kernel_half; k++ ) {
            for (int l = -kernel_half; l <= kernel_half; l++) {
                //System.out.println("k: " + k + " | l: " + l + " ");
                int kernel_factor = kernel[k+kernel_half][l+kernel_half];
                int current_pixel = behavior.getPixelValue(i+k, j+l, image);
                final_pixel_value += current_pixel * kernel_factor;
                //System.out.println(final_pixel_value);
            }
        }
        //System.out.println(final_pixel_value);
        return final_pixel_value;
    }

    public Image convolve(int[][] Kernel, borderBehavior BB){

        Image convolvedImage = new Image();
        int[][] newImageArray = ImageArray;


        System.out.print ("__________CONVOLVE_START______________" + "\n");
        //if this.imageArray != null
        try{

            for(int m = 0; m< ImageArray.length; m++ ){
                for(int n = 0; n <ImageArray[0].length; n++){
                    int x = get_filtered_pixel(m, n, ImageArray, Kernel, BB);
                    newImageArray[m][n] = x;

                    System.out.println(newImageArray[m][n]);
                }
            }

            convolvedImage.setImageArray(newImageArray);
    /*
            for( int i = 0; i<ImageArray.length; i++){
                for( int j = 0; j< ImageArray[0].length; j++){
                    System.out.print (newImageArray[i][j] + " ");
                }
                System.out.print ("\n");
            }

     */


        }catch (NullPointerException e){
            System.out.println("Empty image cannot be convolved");
            e.printStackTrace();
        }


        System.out.print ("__________CONVOLVE_END______________" + "\n");
        return convolvedImage;
    }


    //TODO to string for displaying imageArray for ImageInstances ....

    public static void main(String[] args) throws IOException {
        //new image
        Image imageInstance = new Image();
        //set image-array attribute
        imageInstance.setImageArray("src/p2Pgm.pgm");

        int[][] checkImageArray = imageInstance.ImageArray;


        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray[i][j] + " ");
            }
            System.out.print ("\n");
        }

        System.out.print ("________________________" + "\n");


        //write image-array to new pgm file
        imageInstance.writeToFilename("TESTER.pgm");

        Image newImage = new Image();
        newImage.setImageArray("TESTER.pgm");
        int[][] checkImageArray2 = newImage.ImageArray;

        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray2[i][j] + " ");
            }
            System.out.print ("\n");
        }

        System.out.print ("________________________" + "\n");


        int [][] newk = KernelFactory.createBoringKernel();
        borderBehavior bor = new ZeroPaddingBorderBehavior();

        int[][] ara = {{10,13,12,7},
                {13,16,8,6},
                {14,13,12,5},
                {13,12,11,9}};

        Image testerImg = new Image();
        testerImg.ImageArray = ara;
        testerImg.convolve(newk, bor);
        int[][] checkImageArray3 = testerImg.ImageArray;

        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray3[i][j] + " ");
            }
            System.out.print ("\n");
        }

        //testerImg.writeToFilename("NewConvolve.pgm");

        /*
        Image convolvedImage = newImage.convolve(newk, bor);
        int[][] checkImageArray3 = convolvedImage.ImageArray;

        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray3[i][j] + " ");
            }
            System.out.print ("\n");
        }

        convolvedImage.writeToFilename("convolved.pgm");

         */

    }
}

