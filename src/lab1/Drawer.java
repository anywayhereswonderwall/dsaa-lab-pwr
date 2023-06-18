package lab1;//package dsaa.lab01;

public class Drawer {
    private static void drawLine(int n, char ch) {
        // TODO
        int i=0;
        while(i<n) {
            System.out.print(ch);
            i++;
        }
    }
    public static void drawPyramid(int n, int k) {
        int currentLine = 1;
        while(currentLine <= n) {
            drawLine(k - currentLine,'.');
            drawLine(2 * currentLine - 1,'X');
            drawLine(k - currentLine,'.');
            System.out.println();
            currentLine++;
        }
    }

    public static void drawPyramid(int n) {
        drawPyramid(n, 0);
    }


    public static void drawChristmassTree(int n) {
        // TODO
        for (int i = 1; i <= n; i++) {
            drawPyramid(i, n);
        }
    }
    public static void drawSquare(int n) {
        drawLine(n,'X');

        for (int i = 2; i < n; i++) {
            System.out.println();
            drawLine(1,'X');
            drawLine(n-2,'.');
            drawLine(1,'X');
//            System.out.println();
        }
        System.out.println();
        drawLine(n,'X');
        System.out.println();
    }
//    public static void main(String[] args) {
//        drawPyramid(5, 10);
//    }
}