package Lab_46;

import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int sizeDisplay;
    private JImageDisplay imageDisplay;
    private Mandelbrot mandelbrot = new Mandelbrot();
    private FractalGenerator fractalGenerator = mandelbrot;
    private Rectangle2D.Double rectangle = new Rectangle2D.Double();

    FractalExplorer(int sizeDisplay){
        this.sizeDisplay = sizeDisplay;
        fractalGenerator.getInitialRange(rectangle);
        imageDisplay = new JImageDisplay(sizeDisplay, sizeDisplay);
    }


}
