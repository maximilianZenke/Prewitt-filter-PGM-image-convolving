public class ClampingBorderBehavior extends borderBehavior{
    @Override
    public int getPixelValue(int i, int j, int[][] image) {
        //return val of adjacent pixel


        int width_i = image[0].length-1;
        int height_i = image.length-1;

        if (i < 0) {
            i = 0;
            }
        if (i > height_i){
            i = height_i;
        }
        if (j < 0) {
            j = 0;
        }
        if (j>width_i){
            j = width_i;
        }

        return image[i][j];
    }

    public static void main(String[] args) {

        int[][] ara = {{5,10,14,10,2},
                        {7,9,12, 0, 5},
                        {-1,12,2, 0, 4}};
        System.out.println(ara[0][ara[0].length-1]);

        System.out.println(new ClampingBorderBehavior().getPixelValue(3, 2, ara));




    }
}
