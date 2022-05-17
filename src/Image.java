import java.io.*;
import java.util.Scanner;

/***
 * Represents an PGM image
 * includes methods to:
 * read PGM file into 2d array
 * write 2d array into PGM file
 * get filtered pixel value for convolve operation
 * convolve image using Kernel- and BorderBehaviour instances
 *
 */
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
     * The maximum possible gray value.
     */
    private static final int MAXVAL = 255;

    // maximum gray value of individual picture
    private static int maxVal;

    //TODO rewrite that ( or put it into doc )
    /**
     *
     * If I understand the assignment correctly, this class has a private attribute representing
     * the image as a 2d array
     * ->the readFromFile sets this.array
     * -> the write to file uses this.array and writes it into pgm file
     */

    // ImageArray attribute represents PGM image as 2d array of integers
    private int[][] ImageArray;

    /***
     * constructor for image class initializes default state:
     * imageArray null
     * max value of picture 0
     */
    public Image(){
        this.ImageArray = null;
        this.maxVal = 0;
    }

    //setting image Array translates to using readFromFile method

    /**
     * method to set ImageArray attribute
     * Uses readFromFile method to translate filename.pgm
     * into 2d array, then stores 2d array in ImageArray.
     * @param filename name of PGM file being translated and then stored into ImageArray
     */
    public void setImageArray(String filename) {
        this.ImageArray = readFromFile(filename);
    }

    /**
     * method to set ImageArray attribute.
     * Takes already existing 2d Array and stores it into ImageArray Attribute
     * @param array 2d array of integers being stored into ImageArray
     */
    public void setImageArray(int[][] array){
        this.ImageArray = array;
    }

    /**
     * getter got ImageArray attribute returns imageArray
     * @return 2d array of integers representing PGM image
     */
    public int[][] getImageArray(){
        return this.ImageArray;
    }

    /***
     * Function to translate 2d array using PGM file
     * @param filename file being translated
     * @return 2d integer array representation of filename.pgm
     */
    public int[][] readFromFile(String filename){
        //original source for this code is from
        // https://github.com/prashantghimire/PGM-Image-Editing-using-Java/blob/master/getPGM.java

        //check if file with path(filename) exists
        assert (new File(filename).isFile()): "File does not exist";

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
            // verify P2 file
            if (!temp.equals("P2")) {
                System.out.println("Image Value: " + temp);
                System.out.println("NOT A VALID PGM FILE... ONLY P2 FILES SUPPORTED HERE...");
                System.exit(1);
            }
            // remove comments
            while (!scan.hasNextInt()) {
                scan.nextLine();
            }
            int cols = scan.nextInt();
            int rows = scan.nextInt();
            int maxVal = scan.nextInt();
            rows = rows;
            cols = cols;
            this.maxVal = maxVal;

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
     * original source for this code is from
     * https://gist.github.com/armanbilge/3276d80030d1caa2ed7c
     */
    public void writeToFilename(String filename) throws IOException {

        //if length of filename is at least 5 characters (minimum would be one char with .pgm extension )
        assert ((filename.length() >= 5)): "filename has to have at least one character and end with '.pgm'";
        //check if filename is valid ( has .pgm extension )
        assert(filename.substring(filename.length() - 3).equals("pgm")): "filename must end with .pgm";

        maxVal = 255;
        //TODO tell why we did that -> he said we only have to read the example files ( which are always maxval 255 )!

        if (maxVal > MAXVAL)
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + MAXVAL + ".");
        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filename));

        try{
            stream.write(MAGIC.getBytes());
            /*
            //TODO Test!
            if(!stream.toString().contains("P2")){
                throw new IllegalArgumentException("PGM File can only be version P2");
            }
             */

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

                    /*
                    //TODO what about negative values?
                    if (p < 0 || p > maxVal) {
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxVal + "].");
                    }

                     */
                    stream.write(Integer.toString(ImageArray[i][j]).getBytes());
                    stream.write("\n".getBytes());
                }
            }
        } finally {
            stream.close();
        }
    }

    /**
     * method to get value of pixel if being filtered with given kernel and borderBehaviour.
     * Essentially a helper function for convolve method.
     * @param i x coordinate in 2d integer array representation of PGM image
     * @param j y coordinate in 2d integer array representation of PGM image
     * @param image 2d integer array representation of PGM image
     * @param kernel object used to change image in specified way
     * @param behavior object to specify way of changing image (e.g. border - behaviour of chosen kernel )
     * @return integer value of filtered pixel
     */
    public static int get_filtered_pixel(int i, int j, int[][] image, int [][] kernel, BorderBehavior behavior) {

        assert ( image != null && image.length > 0): "no image array given";
        assert ( kernel != null && kernel.length > 0): "no kernel array given";
        assert ( behavior != null ): "no border behavior given";

        int kernel_half = ((kernel.length-1) / 2 );
        int final_pixel_value = 0;
        int current_pixel = 0;
        for(int k=-kernel_half; k<=kernel_half; k++ ) {
            for (int l = -kernel_half; l <= kernel_half; l++) {
                int kernel_factor = kernel[k+kernel_half][l+kernel_half];
                current_pixel = behavior.getPixelValue(i+k, j+l, image);
                final_pixel_value += current_pixel * kernel_factor;
            }
        }
        return final_pixel_value;
    }

    /**
     * method to convolve an image with chosen kernel and borderbehavior
     * @param kernel object used to change image in specified way
     * @param behavior object to specify way of changing image (e.g. border - behaviour of chosen kernel )
     * @return image object with convolved pixel values.
     */
    public Image convolve(int[][] kernel, BorderBehavior behavior) {

        assert ( kernel != null && kernel.length > 0): "no kernel array given";
        assert ( behavior != null ): "no border behavior given";
        assert ( this.ImageArray != null): "image array is empty";

        int rows = ImageArray.length;
        int cols = ImageArray[0].length;
        int[][] convolvedImage = new int[rows][cols];
        Image finalImage = null;

        try {
            for (int m = 0; m < ImageArray.length; m++) {
                for (int n = 0; n < ImageArray[0].length; n++) {
                    int x = get_filtered_pixel(m, n, ImageArray, kernel, behavior);
                    convolvedImage[m][n] = x;
                }
            }
            finalImage = new Image();
            finalImage.setImageArray(convolvedImage);
        } catch (NullPointerException e) {
            System.out.println("Empty image cannot be convolved");
            e.printStackTrace();
        }
        return finalImage;
    }


    //TODO delete checking
    public static void main(String[] args) throws IOException {
        //new image
        Image imageInstance = new Image();
        //set image-array attribute
        imageInstance.setImageArray("src/p2Pgm.pgm");
        int [][] newk = KernelFactory.createBoringKernel();
        BorderBehavior bor = new ClampingBorderBehavior();

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
        int[][] checkImageArray2 = newImage.convolve(newk,bor).getImageArray();

        for( int i = 0; i<7; i++){
            for( int j = 0; j< 24; j++){
                System.out.print (checkImageArray2[i][j] + " ");
            }
            System.out.print ("\n");
        }

        System.out.print ("________________________" + "\n");




        int[][] ara = {{10,13,12,7},
                {13,16,8,6},
                {14,13,12,5},
                {13,12,11,9}};

        Image testerImg = new Image();

        testerImg.ImageArray = ara;

        Image finalImage = testerImg.convolve(newk, bor);
        int [][] checkImageArray3 = finalImage.getImageArray();

        for( int x = 0; x <checkImageArray3.length; ++x){
            for( int y = 0; y < checkImageArray3[0].length; ++y){
                System.out.print (checkImageArray3[x][y] + " ");
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

