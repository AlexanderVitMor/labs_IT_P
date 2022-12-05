package Lab_46;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2_000;

    /**
     * Метод изменяет поля прямоугольника для отображения правильного начального диапазона для фрактала.
     *
     * @param range прямоугольный объект.
     */

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    /**
     * Метод принимает на вход две константы для комплексной плоскости и вычисляет количество итераций,
     * проходящей для прорисовки этого пикселя. Количество итераций ограничено в нашем случае (2000).
     * Так же подсчет итераций происходит до тех пор пока |Z| < 2 в противном случае точка не принадлежит множеству Манделброта.
     * Функцмя Манделброта Z(n) = Z(n-1)^2 + С, |Z| < 2, Z(0) = 0.
     *
     * @param x Константы действительной части.
     * @param y Константы мнимой части.
     * @return количество итераций для данной точки, иначе -1 если количество = 2000.
     */
    @Override
    public int numIterations(double x, double y) {
        double zReal = 0;
        double zComprehensive = 0;
        int iteration = 0;
        for (; iteration < MAX_ITERATIONS; iteration++) {

            double comparisonModule = Math.sqrt(zReal * zReal + zComprehensive * zComprehensive);

            if (comparisonModule * comparisonModule < 4) {
                double checkReal = zReal * zReal - zComprehensive * zComprehensive + x;
                zComprehensive = -2 * zReal * zComprehensive + y;
                zReal = checkReal;
            } else break;
        }
        if (iteration == MAX_ITERATIONS) return -1;
        else return iteration;
    }

    @Override
    public String toString() {
        return "Tricorn";
    }
}
