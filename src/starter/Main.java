package starter;

import GraphicalUserInterface.*;
import utils.Statistic;

import javax.swing.*;
import java.io.File;

public class Main {

    public static Statistic stat;

    public static void main(String[] args) {
        stat = new Statistic();
        File file = new File(".stat");
        if(file.exists() && file.isFile()){
            stat.load();
        }
        System.out.println(stat.data);

        SwingUtilities.invokeLater(()->{
            GameWindow w = new GameWindow();
        });
    }
}