import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Image_old {

    public short[][] readFromFile(String filename){
        //original source for this code is from
        // https://github.com/prashantghimire/PGM-Image-Editing-using-Java/blob/master/getPGM.java
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
            maxVal = maxVal;
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

    public void writeToFilename(String filename){



    }

    public void convolve(Array Kernel, borderBehavior BB){

    }

    public static void main(String[] args) {
        //Testing...
        Image_old imageInstance = new Image_old();
        short[][] pgmArray = imageInstance.readFromFile("src/p2Pgm.pgm");

        for (short[] row : pgmArray) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }

    }
}

