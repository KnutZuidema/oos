package praktikum1;

public class PascalsTriangle {
    private static final int triangle_height = 10;

    public static void main(String[] args) {
        int triangle[][] = new int[triangle_height][];
        for (int i = 0; i < triangle_height; i++) {
            triangle[i] = new int[i + 1];
        }
        triangle[0][0] = 1;
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j > 0) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                }
                if (j < triangle[i - 1].length) {
                    triangle[i][j] += triangle[i - 1][j];
                }
            }
        }
        for (int line[] : triangle) {
            for (int number : line) {
                System.out.print(String.format("%4d ", number));
            }
            System.out.println();
        }
    }
}
