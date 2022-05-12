import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class Image {

    /**
     * Magic number representing the binary PGM file type.
     */
    private static final String MAGIC = "P5";
    /**
     * Character indicating a comment.
     */
    private static final char COMMENT = '#';
    /**
     * The maximum gray value.
     */
    private static final int MAXVAL = 255;

    /**
     *
     * If I understand the assignment correctly, this class has a private attribute representing
     * the image as a 2d array
     * ->the readFromFile sets this.array
     * -> the write to file uses this.array and writes it into pgm file
     */
    //lets store the image in a 2d array...
    private short[][] ImageArray;

    private Image(){
        this.ImageArray = null;
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
    public short[][] readFromFile(String filename){
        //original source for this code is from
        // https://github.com/prashantghimire/PGM-Image-Editing-using-Java/blob/master  /getPGM.java
        short [][] image = null;
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
            //maxVal = maxVal; I don't think we need this. Can you check @Marcel ?

            image = new short[rows][cols];
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

    //TODO find out how we can best implement maxVal. It is needed for writeToFilename but not yet
    //properly implemented in the image class. Afterwards read/write methods should work as intended.


    /***
     * Function to create PGM file using 2d array
     * @param filename Name of PGM file being created
     */
    public void writeToFilename(String filename) throws IOException {
        if (maxval > MAXVAL)
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + MAXVAL + ".");
        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filename));
        try {
            stream.write(MAGIC.getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(ImageArray[0].length).getBytes());
            stream.write(" ".getBytes());
            stream.write(Integer.toString(ImageArray.length).getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(maxval).getBytes());
            stream.write("\n".getBytes());
            for (int i = 0; i < ImageArray.length; ++i) {
                for (int j = 0; j < ImageArray[0].length; ++j) {
                    final int p = ImageArray[i][j];
                    if (p < 0 || p > maxval)
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxval + "].");
                    stream.write(ImageArray[i][j]);
                }
            }
        } finally {
            stream.close();
        }

    }

    public void convolve(Array Kernel, borderBehavior BB){

    }

    public static void main(String[] args) {
        //Testing...
        Image imageInstance = new Image();
        short[][] pgmArray = imageInstance.readFromFile("src/p2Pgm.pgm");

        for (short[] row : pgmArray) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }

    }
}

