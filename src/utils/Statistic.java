package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistic implements Serializable {

    public ArrayList<Map.Entry<String, StatisticData>> data;


    public Statistic(){
        data = new ArrayList<>();
    }
    public ArrayList<Map.Entry<String, StatisticData>> get(){
        return this.data;
    }

    public void load(){
        try {
            FileInputStream fis = new FileInputStream(".stat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Statistic s = (Statistic) ois.readObject();

            this.data = s.data;

            ois.close();
            fis.close();
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void save(){
        try {
            FileOutputStream file = new FileOutputStream(".stat");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static class StatisticData implements Serializable {
        public final long time;
        public final int score;
        public StatisticData(long time, int score){
            this.time = time;
            this.score = score;
        }
    }
}
