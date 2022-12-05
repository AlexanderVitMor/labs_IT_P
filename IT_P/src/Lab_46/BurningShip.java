package Lab_46;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{

    public static final int MAX_ITERATIONS = 2_000;

    @Override
    public void getInitialRange(Rectangle2D.Double range){
        range.x = -2;
        range.y = -2;
        range.height = 4;
        range.width = 4;
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
                zComprehensive = 2 * Math.abs(zReal) * Math.abs(zComprehensive) + y;
                zReal = checkReal;
            } else break;
        }
        if (iteration == MAX_ITERATIONS) return -1;
        else return iteration;

    }

    @Override
    public String toString() {
        return "Burning Ship";
    }

}
