package Threads;

import GraphicalUserInterface.GameScene;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class UIThread extends Thread{

    public final long appStartTime;

    public UIThread(){
        instance = this;
        appStartTime = System.currentTimeMillis();
    }
    public static UIThread instance;

    public long passed(){
        return System.currentTimeMillis() - appStartTime;
    }
    @Override
    public void run() {
        while (true){
            SwingUtilities.invokeLater(() -> {
                long passed = System.currentTimeMillis()-appStartTime; // Сколько мс программа запущена
                long seconds = passed/1000;

                int sec = (int) seconds % 60;
                int minutes = (int) (seconds/60) % 60;
                int hours = (int) (seconds / 3600);
                GameScene.instance.timer.setText(hours+":"+minutes+":"+sec);

                GameScene.instance.scoreLabel.setText("Score: "+ GameScene.instance.pacman().getScore());
            });
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
