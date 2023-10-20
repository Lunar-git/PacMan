package Threads;

import GraphicalUserInterface.GameScene;
import GraphicalUserInterface.GameWindow;
import GraphicalUserInterface.MainMenu;
import GraphicalUserInterface.Scene;
import Models.*;
import utils.GhostMovement;

import java.util.ArrayList;
import java.util.Random;

import static Models.Direction.*;

public class GhostsMovementThread extends Thread{
    public void spawn() {
        for (Ghost ghost : GameScene.instance.ghosts) {
            scene.setEntity(ghost, ghost.getCords().x, ghost.getCords().y);
        }
        scene.update();
    }


    private Scene scene;
    public static GhostsMovementThread instance;

    public GhostsMovementThread(Scene tableComponent) {
        instance = this;
        this.scene = tableComponent;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if(!(GameWindow.instance.current instanceof GameScene)){
                continue;
            }


            for (Ghost ghost : GameScene.instance.ghosts) {
                if(ghost.direction == null)continue;
                ArrayList<Direction> available = Ghost.getAvailableDirections(ghost.getCords());
                available.remove(ghost.direction.invert());
                Cords np;
                if(available.size() == 0){
                    ghost.direction = ghost.direction.invert();
                    np = PacmanMovementThread.getNewCords(ghost.direction, ghost.getCords());
                    moveIt(ghost.direction, ghost);
                }else{
                    Direction d = available.get(new Random().nextInt(available.size()));
                    ghost.direction = d;
                    np = PacmanMovementThread.getNewCords(ghost.direction, ghost.getCords());
                    moveIt(d, ghost);
                }
                if(GameScene.instance.pacman().getCords().equals(np)){
                    GameScene.gameOver(PacmanMovementThread.GameOverReason.LOSE);
                }
            }


            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scene.update();
        }
    }

    public void moveIt(Direction direction, Entity entity){
        Cords newCords = PacmanMovementThread.getNewCords(direction, entity.getCords());
        if(!PacmanMovementThread.check(newCords)){
            return;
        }
        EntityType what = EntityType.NOTHING;
        if(entity instanceof Ghost){
            Ghost g = (Ghost) entity;
            what = g.onPoint ? EntityType.POINT : EntityType.NOTHING;
        }
        Object o = scene.getValueAt(newCords.y, newCords.x);
        if(o instanceof Integer) {
            if (entity instanceof Ghost) {
                Ghost g = (Ghost) entity;
                if ((int) o == EntityType.POINT.asInteger()) {
                    g.onPoint = true;
                }else{
                    g.onPoint = false;
                }

            }
        }

        Cords old = entity.getCords();
        scene.setAt(what, old.y, old.x);
        scene.setEntity(entity, newCords.y, newCords.x);
        scene.update();
        entity.setCords(newCords);
    }




}
