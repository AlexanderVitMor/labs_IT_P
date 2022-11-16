package Lab_46;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс создает объект изображения содержит методы по установленю цветовой палитры и ее сброса.
 */
public class JImageDisplay extends javax.swing.JComponent {
    public BufferedImage image;

    /**
     * Создает объект окна.
     *
     * @param width  ширина окна.
     * @param height высота окна.
     */
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(width, height));

    }

    /**
     * Переопределение защищенного метода, для того что бы наши изображения рисовались правильно.
     *
     * @param g the <code>Graphics</code> object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    /**
     * Метод, который устанавливает цветовую палитру для всех пикселей в черный.
     */
    public void clearImage() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, 0);

            }
        }

    }

    /**
     * Метод, который устанавливает для определенного символа определенный цвет.
     *
     * @param x        координаты по х.
     * @param y        координаты по у.
     * @param rgbColor цветовая палитра.
     */
    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);

    }
}
