import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//original source for this code is from
// https://github.com/prashantghimire/PGM-Image-Editing-using-Java/blob/master/getPGM.java

public class PgmToArray {
    private short[][] image;
    private int cols, rows, maxVal;

    public PgmToArray(String filename) {
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
            this.rows = rows;
            this.cols = cols;
            this.maxVal = maxVal;
            image = new short[rows][cols];
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    image[i][j] = scan.nextShort();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public short[][] getImage() {
        return image;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMaxValue() {
        return maxVal;
    }

    public short getPixel(int i, int j) {
        return image[i][j];
    }

    public static void main(String[] args) {

        PgmToArray pgm = new PgmToArray("src/p2Pgm.pgm");
        short [][] pgmArray = pgm.getImage();

        for (short[] row : pgmArray) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }


    }
}
