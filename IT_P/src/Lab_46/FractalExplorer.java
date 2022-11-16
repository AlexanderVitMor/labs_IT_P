package Lab_46;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private final int sizeDisplay;
    private final JImageDisplay imageDisplay;
    private final Mandelbrot mandelbrot = new Mandelbrot();
    private final FractalGenerator fractalMandelbrot = mandelbrot;
    private final Rectangle2D.Double rectangle = new Rectangle2D.Double();

    //Создание фрейма
    private final JFrame JDisplay = new JFrame("Fractal Explorer");

    /**
     * Основной метод, нужен для запуска всего проекта.
     */
    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(1_200);
        fractal.createAndShowGUI();
        fractal.drawFractal();

    }


    /**
     * Метод позволяет создать объект для исследования, что позволит использовать такие методы как:
     * createAndShowGUI
     * drawFractal
     *
     * @param sizeDisplay размер экрана
     */
    FractalExplorer(int sizeDisplay) {
        this.sizeDisplay = sizeDisplay;
        fractalMandelbrot.getInitialRange(rectangle);
        imageDisplay = new JImageDisplay(sizeDisplay, sizeDisplay);
    }

    /**
     * Метод создающий графический интерфейз.
     * Включает в себя на этапе Lab_4:
     * - Кнопку для сброса изображения в исходное состояние
     * - И функцию исследования фрактал, т.е. мы можем нажатием мыши увеличить масштаб иображения,
     * и рассмотреть его детальнее.
     * - Так же задает нужные параметра для стабильной отрисовки изображения и закрытия окна.
     */
    private void createAndShowGUI() {

        JButton resetButton = new JButton("Reset Image");
        ActionListener resetImage = new ButtonResetAction();
        resetButton.addActionListener(resetImage);
        MouseListener mouseClicker = new MouseAction();

        JDisplay.addMouseListener(mouseClicker);
        JDisplay.add(imageDisplay, BorderLayout.CENTER);
        JDisplay.add(resetButton, BorderLayout.SOUTH);

        JDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDisplay.pack();
        JDisplay.setVisible(true);
        JDisplay.setResizable(false);


    }

    /**
     * Метод закрашивающий каждый пиксель в соответствующий цвет, с учетом его итерации.
     */
    private void drawFractal() {
        int rgbColor;
        for (int x = 0; x < sizeDisplay; x++) {
            for (int y = 0; y < sizeDisplay; y++) {
                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, sizeDisplay, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, sizeDisplay, y);
                int iterations = fractalMandelbrot.numIterations(xCoord, yCoord);
                if (iterations == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iterations / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }

    /**
     * Класс для реализации действий кнопки для сброса изображения.
     */
    public class ButtonResetAction implements ActionListener {
        /**
         * Для этого переопределяем нужный нам метод, и пишем последовательность действий,
         * которые произойдут после нажатия на кнпоку.
         * В нашем случае сбрасывает диапазон до начально, "стирается изображение" и перерисовывается.
         *
         * @param e the event to be processed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalMandelbrot.getInitialRange(rectangle);
            imageDisplay.clearImage();
            drawFractal();

        }
    }

    /**
     * Класс для реализации действий после нажатия на определенный участок курсором мышки.
     */
    public class MouseAction extends MouseAdapter implements MouseListener {
        /**
         * Для этого переопределяем нужный нам метод, и пишем последовательность действий,
         * которые произойдут после нажатия на мышь.
         * В нашем случае получаем координаты с место клика, увеличиваем зум через нужный нам метод
         * и перерисовываем изображение.
         *
         * @param e the event to be processed.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            Point pointOfClickMouse = e.getPoint();
            double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, sizeDisplay, pointOfClickMouse.x);
            double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, sizeDisplay, pointOfClickMouse.y);
            fractalMandelbrot.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }


}
