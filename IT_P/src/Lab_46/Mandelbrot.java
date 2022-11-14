package Lab_46;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2.5;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    @Override
    public int numIterations(double x, double y) {
        double zReal = 0;
        double zComprehensive = 0;
        int iteration = 0;
        for (; iteration < MAX_ITERATIONS; iteration++) {

            double comparisonModule = Math.sqrt(zReal * zReal + zComprehensive * zComprehensive);

            if (comparisonModule * comparisonModule < 4) {
                double checkReal = zReal * zReal - zComprehensive * zComprehensive + x;
                zComprehensive = 2 * zReal * zComprehensive + y;
                zReal = checkReal;
            } else break;
        }
        if (iteration == MAX_ITERATIONS) return -1;
        else return iteration;

    }
}
