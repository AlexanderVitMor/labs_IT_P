package Lab_46;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer{
    private final int sizeDisplay;
    private final JImageDisplay imageDisplay;
    private final Mandelbrot mandelbrot = new Mandelbrot();
    private final FractalGenerator fractalMandelbort = mandelbrot;
    private final Rectangle2D.Double rectangle = new Rectangle2D.Double();
    private final JFrame JDisplay = new JFrame("Fractal Explorer");

    public static void main(String[] args){
        FractalExplorer fractal = new FractalExplorer(1000);
        fractal.createAndShowGUI();
        fractal.drawFractal();

    }


    FractalExplorer(int sizeDisplay){
        this.sizeDisplay = sizeDisplay;
        fractalMandelbort.getInitialRange(rectangle);
        imageDisplay = new JImageDisplay(sizeDisplay, sizeDisplay);
    }

    private void createAndShowGUI(){

        JButton resetImage = new JButton("Reset Image");
        ActionListener resetButton = new ButtonResetAction();
        resetImage.addActionListener(resetButton);
        MouseListener mouseClicker = new MouseAction();

        JDisplay.addMouseListener(mouseClicker);
        JDisplay.add(imageDisplay, BorderLayout.CENTER);
        JDisplay.add(resetImage, BorderLayout.SOUTH);

        JDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDisplay.pack();
        JDisplay.setVisible(true);
        JDisplay.setResizable(false);


    }

    private void drawFractal(){
        int rgbColor;
        for (int x = 0; x < sizeDisplay; x++) {
            for (int y = 0; y < sizeDisplay; y++) {
                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, sizeDisplay, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, sizeDisplay, y);
                int iterations = fractalMandelbort.numIterations(xCoord, yCoord);
                if (iterations == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) iterations / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }

    public class ButtonResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalMandelbort.getInitialRange(rectangle);
            imageDisplay.clearImage();
            drawFractal();

        }
    }

    public class MouseAction extends MouseAdapter implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            Point pointOfClickMouse = e.getPoint();
            double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, sizeDisplay, pointOfClickMouse.x);
            double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, sizeDisplay, pointOfClickMouse.y);
            fractalMandelbort.recenterAndZoomRange(rectangle ,xCoord, yCoord, 0.5);
            drawFractal();
        }
    }



}
