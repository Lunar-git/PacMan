package Models;

import Threads.PacmanMovementThread;

import javax.swing.*;
import java.util.ArrayList;

public class PacMan extends Entity {

    private int score;
    private int lives;
    public volatile boolean inMove;

    public volatile Direction direction;


    public PacMan(String name, int x, int y, ImageIcon image, ArrayList<String> filenames){
        super(name, x, y, image, EntityType.PACKMAN, filenames);
        this.score = 0;
        this.lives = 3;
    }

    public void increaseScore(){
        score += 100;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isInMove() {
        return inMove;
    }

    public void setInMove(boolean inMove) {
        this.inMove = inMove;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }



}

