package Models;

import Threads.PacmanMovementThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Entity {

    public final EntityType type;
    protected String name;
    protected Cords cords;
    protected ImageIcon image;

    // когда в foreach итератишь список, и в тот момент он меняется  - ConcurrentModificationException

    protected ConcurrentLinkedQueue<ImageIcon> animationImages = new ConcurrentLinkedQueue<>() {
    };
    protected boolean inMove;

    protected Direction direction;

    public Entity(String name, int x, int y, ImageIcon image, EntityType type, ArrayList<String> fileNames) {
        this.name = name;
        this.type = type;
        this.cords = new Cords(x,y);
        this.image = image;
        this.direction = null;
        this.inMove = false;

        for (String fileName : fileNames) {
            File absolute = new File(fileName);
            try {
                BufferedImage img = ImageIO.read(absolute);
                animationImages.add(new ImageIcon(img));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean isInMove() {
        return inMove;
    }

    public void setInMove(boolean inMove) {
        this.inMove = inMove;
    }

    public ConcurrentLinkedQueue<ImageIcon> getAnimationImages() {
        return animationImages;
    }

    public void setAnimationImages(ConcurrentLinkedQueue<ImageIcon> animationImages) {
        this.animationImages = animationImages;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Cords getCords() {
        return cords;
    }

    public void setCords(Cords cords) {
        this.cords = cords;
    }

    public EntityType getType() {
        return this.type;
    }
}
