package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    private int width;
    private int height;


    public Scene myJTable = new Scene();

    public MyWindow(String name, int width, int height) {
        super(name);
        this.height = height;
        this.width = width;
        setSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Menu menu = new Menu();
        add(myJTable);
        pack();
        setVisible(true);
    }


}
