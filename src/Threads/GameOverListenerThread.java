package Threads;

import GraphicalUserInterface.GameScene;
import GraphicalUserInterface.GameWindow;
import Models.Ghost;
import Models.PacMan;

import java.util.concurrent.TimeUnit;

public class GameOverListenerThread extends Thread{
    @Override
    public void run() {
        while (true){
            if(!(GameWindow.instance.current instanceof GameScene)){
                continue;
            }
            PacMan pacman = pacman = ((GameScene) GameWindow.instance.current).pacman();

            for (Ghost ghost : GameScene.instance.ghosts) {
                if(ghost.getCords().x == pacman.getCords().x){
                    System.out.println("x");
                    if(ghost.getCords().y == pacman.getCords().y){
                        System.out.println("full");
                    }
                }
                if(ghost.getCords().equals(pacman.getCords())){
                    GameScene.gameOver(PacmanMovementThread.GameOverReason.LOSE);
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
