package GraphicalUserInterface;

import starter.Main;
import utils.Statistic;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ScoreboardScene extends SceneWindow{
    public ScoreboardScene(){
        setSize(240, 180);
        this.name = "Scoreboard";
        this.width = 240;
        this.height=180;
        setLayout(new BorderLayout());
        JList<String> list = new JList<>();
        DefaultListModel<String> model  = new DefaultListModel<>();
        for (Map.Entry<String, Statistic.StatisticData> datum : Main.stat.data) {
            long millis=datum.getValue().time;
            long seconds = millis/1000;
            int sec = (int) seconds % 60;
            int minutes = (int) (seconds/60) % 60;
            int hours = (int) (seconds/60/60) % 24;

            model.addElement(datum.getKey()+": score - "+datum.getValue().score+", time - "+hours+":"+minutes+":"+sec);
        }
        list.setModel(model);
        add(list, BorderLayout.CENTER);
    }
}
