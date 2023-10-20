package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;

public class Resizer {
    private int width;
    private int height;
    private ImageIcon icon;

    public Resizer(int width, int height, ImageIcon icon) {
        this.width = width;
        this.height = height;
        this.icon = icon;
    }

    public static ImageIcon resizeImage(int width, int height, String filename) {
        Image image = new ImageIcon(filename).getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
