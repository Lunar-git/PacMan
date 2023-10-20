package Threads;

import GraphicalUserInterface.GameScene;
import GraphicalUserInterface.GameWindow;
import GraphicalUserInterface.MainMenu;
import GraphicalUserInterface.Scene;
import Models.*;
import configurations.Config;

public class PacmanMovementThread extends Thread {

    public void spawn() {
        Cords c = GameScene.instance.pacman().getCords();
        scene.setAt(EntityType.PACKMAN, c.x,c.y);

    }

    public enum GameOverReason {
        LOSE, WIN;
    }

    private static Scene scene;
    public static PacmanMovementThread instance;
    public PacmanMovementThread(Scene tableComponent) {
        instance = this;
        this.scene = tableComponent;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if(!(GameWindow.instance.current instanceof GameScene)){
                continue;
            }


            PacMan pacman = pacman = ((GameScene) GameWindow.instance.current).pacman();


            if (pacman.inMove && pacman.direction != null) {
                Cords newCords = getNewCords(pacman.direction, pacman.getCords());
                if (check(newCords)) {
                    for (Ghost ghost : GameScene.instance.ghosts) {
                        if(ghost.getCords().equals(newCords)){
                            GameScene.gameOver(GameOverReason.LOSE);
                        }
                    }
                    pacman.inMove = true;
                    moveIt(pacman.direction,pacman);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    pacman.inMove = false;
                    pacman.direction = null;
                }
            }
            scene.update();
        }
    }


    public boolean someoneOnPos(Cords pos){
        for (Ghost ghost : GameScene.instance.ghosts) {
            if(ghost.getCords().equals(pos))return true;
        }
        return false;
    }

    public void moveIt(Direction direction, Entity entity){
        Cords newCords = getNewCords(direction, entity.getCords());
        if(!check(newCords)){
            return;
        }
        Object v = scene.getValueAt(newCords.y, newCords.x);
        if(v instanceof Integer){
            if((int)v == EntityType.POINT.asInteger()){
                GameScene.instance.pacman().increaseScore();
            }
        }
        Cords old = entity.getCords();
        scene.setAt(EntityType.NOTHING, old.y, old.x);
        scene.setEntity(entity, newCords.y, newCords.x);
        scene.update();
        entity.setCords(newCords);
    }

    public static Cords getNewCords(Direction direction, Cords currentCords) {
//        Cords currentCords = pacman.getCords();
        int newX = currentCords.x;
        int newY = currentCords.y;

        switch (direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        return new Cords(newX, newY);
    }

    public static boolean check(Cords cords) {
        try {
            Object value = scene.getValueAt(cords.y, cords.x);
            if(value instanceof Integer){
                if ((Integer) value != EntityType.WALL.asInteger()) {
                    return true;
                }
            }else if(value instanceof Entity){
                if(!Config.allowGhostStuck) if(((Entity)value).type == EntityType.GHOST)return false;
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
