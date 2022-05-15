import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ZeroPaddingBorderBehavior extends borderBehavior{
    @Override
    public int getPixelValue(int i, int j, int [][] image) {

        if (i > image.length-1 || i < 0){
            return 0;
        }
        else if(j > image[0].length-1 || j < 0){
            return 0;
        }
        else return image[i][j];

        //return 0

        /*
        def get_pixel_value(i: int, j: int, img: Image) -> int:
        return 0 if out_of_range(i, j) else img.scanlines[j][i]

         */
    }
    public static void main(String[] args) {

        int[][] ara = {{5,10,14,10,2},
                    {7,9,12, 0, 5},
                    {-1,12,2, 0, 4}};
        System.out.println(ara.length);
        System.out.println(ara[0].length);
        System.out.println(new ZeroPaddingBorderBehavior().getPixelValue(2, 5, ara));




    }
}
