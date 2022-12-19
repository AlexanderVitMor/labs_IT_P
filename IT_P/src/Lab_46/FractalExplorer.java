package Lab_46;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private final int sizeDisplay;
    private final JImageDisplay imageDisplay;
    private final Mandelbrot mandelbrot = new Mandelbrot();
    private final Tricorn tricorn = new Tricorn();
    private final BurningShip burningShip = new BurningShip();
    private FractalGenerator fractalGenerator = tricorn;
    private final Rectangle2D.Double rectangle = new Rectangle2D.Double();
    private JButton saveButton;
    private JButton resetButton;
    private JComboBox<FractalGenerator> jComboBox;
    private int rowsRemaining;

    //Создание фрейма
    private final JFrame JDisplay = new JFrame("Fractal Explorer");

    /**
     * Основной метод, нужен для запуска всего проекта.
     */



    /**
     * Метод позволяет создать объект для исследования, что позволит использовать такие методы как:
     * createAndShowGUI
     * drawFractal
     *
     * @param sizeDisplay размер экрана
     */
    FractalExplorer(int sizeDisplay) {
        this.sizeDisplay = sizeDisplay;
        fractalGenerator.getInitialRange(rectangle);
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

        resetButton = new JButton("Reset Image");
        saveButton = new JButton("Save Image");
        JPanel panelButton = new JPanel();
        panelButton.add(saveButton);
        panelButton.add(resetButton);
        jComboBox = new JComboBox<>();
        ActionListener resetImage = new ButtonResetAction();
        ActionListener selectFractal = new ComboBoxListener();
        ActionListener saveFractal = new SaveBtnListener();
        saveButton.addActionListener(saveFractal);
        resetButton.addActionListener(resetImage);
        MouseListener mouseClicker = new MouseAction();
        jComboBox.addActionListener(selectFractal);

        jComboBox.addItem(mandelbrot);
        jComboBox.addItem(tricorn);
        jComboBox.addItem(burningShip);
        JDisplay.addMouseListener(mouseClicker);
        JDisplay.add(imageDisplay, BorderLayout.CENTER);
        JDisplay.add(panelButton, BorderLayout.SOUTH);
        JDisplay.add(jComboBox, BorderLayout.NORTH);

        JDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDisplay.pack();
        JDisplay.setVisible(true);
        JDisplay.setResizable(false);

    }

    /**
     * Метод закрашивающий каждый пиксель в соответствующий цвет, с учетом его итерации.
     */
    private void drawFractal() {
        enableUI(false);

        rowsRemaining = sizeDisplay;

        for (int x = 0; x < sizeDisplay; x++) {
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
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
            fractalGenerator.getInitialRange(rectangle);
            imageDisplay.clearImage();
            drawFractal();

        }
    }

    /**
     * Класс для реализации действий после нажатия на определенный участок курсором мышки.
     */
    public class MouseAction extends MouseAdapter{
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
            if (0 != rowsRemaining){
                return;
            }

            Point pointOfClickMouse = e.getPoint();
            double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, sizeDisplay, pointOfClickMouse.x);
            double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.height, sizeDisplay, pointOfClickMouse.y);
            fractalGenerator.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            fractalGenerator = (FractalGenerator) box.getSelectedItem();
            assert fractalGenerator != null;
            fractalGenerator.getInitialRange(rectangle);
            imageDisplay.repaint();
            drawFractal();
        }
    }

    public class SaveBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.showSaveDialog(JDisplay);
            try {
                ImageIO.write(imageDisplay.image, "PNG", fileChooser.getSelectedFile());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(JDisplay, ex.getMessage(), "Error saving", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private class FractalWorker extends SwingWorker<Object, Object> {
        /**
         * Поле для целочисленной координаты y строки, которая будет вычислена.
         */
        int yCoordinate;

        /**
         * Массив целых чисел для хранения вычисленных значений RGB для каждого пикселя в строке.
         */
        int[] computedRGBValues;

        /**
         * Конструктор принимает координату y в качестве
         * аргумента и сохраняет ее.
         */
        private FractalWorker(int row) {
            yCoordinate = row;
        }

        /**
         * Этот метод вызывается в фоновом потоке. Он вычисляет
         * значение RGB для всех пикселей в 1 строке и сохраняет его в
         * соответствующем элементе массива целых чисел. Возвращает значение null.
         */
        protected Object doInBackground() {

            computedRGBValues = new int[sizeDisplay];

            for (int i = 0; i < computedRGBValues.length; i++) {

                double xCoord = FractalGenerator.getCoord(rectangle.x,
                        rectangle.x + rectangle.width, sizeDisplay, i);
                double yCoord = FractalGenerator.getCoord(rectangle.y,
                        rectangle.y + rectangle.height, sizeDisplay, yCoordinate);


                int iteration = fractalGenerator.numIterations(xCoord, yCoord);

                if (iteration == -1) {
                    computedRGBValues[i] = 0;
                } else {

                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    computedRGBValues[i] = rgbColor;
                }
            }
            return null;

        }

        /**
         * Вызывается, когда фоновая задача завершена. Рисует пиксели
         * для текущей строки и обновляет отображение для этой строки.
         */
        protected void done() {
            for (int i = 0; i < computedRGBValues.length; i++) {
                imageDisplay.drawPixel(i, yCoordinate, computedRGBValues[i]);
            }
            imageDisplay.repaint(0, 0, yCoordinate, sizeDisplay, 1);

            rowsRemaining--;
            if (0 == rowsRemaining) {
                enableUI(true);
            }
        }
    }

    private void enableUI(boolean val) {

        jComboBox.setEnabled(val);
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();

    }

}
