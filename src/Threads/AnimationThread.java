package Threads;

import GraphicalUserInterface.Scene;
import Models.Direction;
import Models.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static configurations.Config.*;

public class AnimationThread extends Thread {
    private final Entity entity;
    private ImageIcon image;
    private Scene scene;
    private ArrayList<ImageIcon> images = new ArrayList<>();

    public AnimationThread(Scene scene, Entity entity, ImageIcon[] images) {
        this.scene = scene;
        this.entity = entity;
        this.image = new ImageIcon(entity.getImage().getImage());
        setName("AnimationThread-"+hashCode());
        this.images.addAll(Arrays.asList(images));
    }
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                if (entity.isInMove()) {
                    ConcurrentLinkedQueue<ImageIcon> list = entity.getAnimationImages();
                    Iterator<ImageIcon> icons = list.iterator();
                    while (icons.hasNext()) {
                        ImageIcon imageIcon = icons.next();
                        if (!entity.isInMove()) break;
                        //entity.setImage(new ImageIcon(ImageIO.read(new File("src/Resources/left3.png"))));
                        entity.setImage(rotateImageIcon(imageIcon, entity.getDirection()));
                        scene.setEntity(entity, entity.getCords().y, entity.getCords().x);
                        //scene.repaint();
                        scene.update();
                        Thread.sleep(animationSpeed);
                        //
                        //Thread.sleep(animationSpeed);
                    }
                    continue;
                }
                entity.setImage(image);
                //scene.setEntity(entity, entity.getCords().x, entity.getCords().y);
                //scene.update();

                synchronized (entity) {
                    entity.notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void change(){
        for (int i = 0; i < images.size(); i++) {
            entity.setImage(images.get(i));
//            myJTable.setValueAt(entity);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){}
        }
        Collections.reverse(images);
    }
//    ImageIcon basicAnimation = Resizer.resizeImage(entity.

    public static ImageIcon rotateImageIcon(ImageIcon icon, Direction direction) {
        // Get the original image from the ImageIcon
        Image image = icon.getImage();

        AffineTransform transform = new AffineTransform();
        switch (direction) {
            case DOWN -> {
                transform.rotate(-Math.PI / 2, image.getWidth(null) / 2, image.getHeight(null) / 2);
            }
            case UP -> {
                transform.rotate(Math.PI / 2, image.getWidth(null) / 2, image.getHeight(null) / 2);
            }
            case RIGHT -> {
                transform.rotate(Math.PI, image.getWidth(null) / 2, image.getHeight(null) / 2);
            }
            case LEFT -> {
                transform.rotate(Math.PI / 0.5, image.getWidth(null) / 2, image.getHeight(null) / 2);
            }
        }

        // Create a new BufferedImage and draw the rotated image onto it
        BufferedImage rotatedImage = new BufferedImage(image.getHeight(null), image.getWidth(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.setTransform(transform);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // Create a new ImageIcon from the rotated image and return it
        return new ImageIcon(rotatedImage);
    }

}