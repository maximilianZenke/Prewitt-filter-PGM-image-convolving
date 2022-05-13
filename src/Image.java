import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
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
                    image[i][j] = scan.nextShort();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    /**
     * Writes a grayscale image to a file in PGM format.
     * @param image a two-dimensional byte array representation of the image
     * @param file the file to write to
     * @param maxval the maximum gray value
     * @throws IllegalArgumentException
     * @throws IOException
     */
    /*
    public static void write(final int[][] image, final File file, final int maxval) throws IOException {
        if (maxval > MAXVAL)
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + MAXVAL + ".");
        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            stream.write(MAGIC.getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(image[0].length).getBytes());
            stream.write(" ".getBytes());
            stream.write(Integer.toString(image.length).getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(maxval).getBytes());
            stream.write("\n".getBytes());
            for (int i = 0; i < image.length; ++i) {
                for (int j = 0; j < image[0].length; ++j) {
                    final int p = image[i][j];
                    if (p < 0 || p > maxval)
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxval + "].");
                    stream.write(image[i][j]);
                }
            }
        } finally {
            stream.close();
        }
    }
    */

    //TODO find out how we can best implement maxVal. It is needed for writeToFilename but not yet
    //properly implemented in the image class. Afterwards read/write methods should work as intended.


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

            //here lies the problem!
            for (int i = 0; i < ImageArray.length; ++i) {
                for (int j = 0; j < ImageArray[0].length; ++j) {
                    final int p = ImageArray[i][j];
                    System.out.println (p);

                    if (p < 0 || p > maxVal) {
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxVal + "].");
                    }
                    //stream.write(ImageArray[i][j]);
                    stream.write(Integer.toString(ImageArray[i][j]).getBytes());

                }
            }
        } finally {
            stream.close();

        }

    }

    public void convolve(Array Kernel, borderBehavior BB){

    }

    //TODO to string for displaying imageArray for ImageInstances ....

    public static void main(String[] args) throws IOException {
        //new image
        Image imageInstance = new Image();
        //set image-array attribute
        imageInstance.setImageArray("src/p2Pgm.pgm");

        int[][] checkImageArray = imageInstance.ImageArray;

        /*
        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray[i][j] + " ");
            }
            System.out.print ("\n");
        }

         */


        //write image-array to new pgm file
        imageInstance.writeToFilename("TESTER.pgm");




    }
}

