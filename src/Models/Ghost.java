package Models;

import GraphicalUserInterface.Scene;
import Threads.PacmanMovementThread;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Entity{
    public volatile Direction direction;

    public final int startX, startY;
    public boolean onPoint = true;

    public Ghost(String name, int x, int y, ImageIcon image, ArrayList<String> filenames){
        super(name, x, y, image, EntityType.GHOST, filenames);
        startX = x;
        startY = y;
        ArrayList<Direction> available = getAvailableDirections(getCords());
        if(available.size() != 0)direction = available.get(new Random().nextInt(available.size()));
        else direction = Direction.LEFT;
    }
    public void respawn(Scene scene){
        scene.setAt(EntityType.NOTHING, getCords().y, getCords().x);
        setCords(new Cords(startX, startY));
        scene.setEntity(this, getCords().y, getCords().x);
        scene.update();
        onPoint = true;
    }
    public static ArrayList<Direction> getAvailableDirections(Cords at){
        ArrayList<Direction> available = new ArrayList<>();

        for (Direction value : Direction.values()) {
            if(PacmanMovementThread.check(PacmanMovementThread.getNewCords(value, at))){
                available.add(value);
            }
        }

        return available;
    }
}
