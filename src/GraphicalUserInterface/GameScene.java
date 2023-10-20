package GraphicalUserInterface;

import Models.Cords;
import Models.Direction;
import Models.Ghost;
import Models.PacMan;
import Threads.*;
import starter.Main;
import utils.Statistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;

public class GameScene extends SceneWindow implements KeyListener {


    public final Scene scene = new Scene();
    private final PacMan pacMan;
    public final int startX;
    public final int startY;
    public final int maxX;
    public final int maxY;
    protected Dimension oldSize;
    public final ArrayList<Ghost> ghosts = new ArrayList<>();
    public static GameScene instance;

    public final JLabel scoreLabel;
    public final JLabel timer;

    public PacMan pacman(){
        return this.pacMan;
    }

    public GameScene(){
        instance = this;
        setLayout(new BorderLayout());
        JPanel topStat = new JPanel();
        topStat.setBounds(0,0,50,50);
        topStat.setLayout(new BorderLayout());
        topStat.add(scoreLabel = new JLabel("Score: 0"), BorderLayout.WEST);
        topStat.add(timer = new JLabel("00:00:00"), BorderLayout.EAST);
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        timer.setFont(new Font("Courier New", Font.BOLD, 24));

        topStat.setOpaque(true);
        topStat.setBackground(new Color(80, 40, 120));

        UIThread ui = new UIThread();
        ui.start();

        add(topStat, BorderLayout.NORTH);
        add(scene, BorderLayout.CENTER);

        startY = scene.getRowCount()/2;
        startX = scene.getColumnCount()/2;
        maxX = scene.getRowCount()-1;
        maxY = scene.getColumnCount()-1;

        this.name = "Pacman";
        this.width = -1;
        this.height = -1;

        pacMan = new PacMan("PACMAN", startX, startY, new ImageIcon("src/Resources/left1.png"),
                new ArrayList<>(Arrays.asList("src/Resources/left1.png","src/Resources/left2.png","src/Resources/left3.png"

                )));
        PacmanMovementThread movementThread = new PacmanMovementThread(scene);

        ghosts.add(new Ghost("Ghost1", 0, 0, new ImageIcon("src/Resources/inkyLeft.png"), new ArrayList<>()));
        ghosts.add(new Ghost("Ghost2", 0, scene.getRowCount()-1, new ImageIcon("src/Resources/inkyLeft.png"), new ArrayList<>()));
        ghosts.add(new Ghost("Ghost3", scene.getColumnCount()-1, 0, new ImageIcon("src/Resources/inkyLeft.png"), new ArrayList<>()));
        ghosts.add(new Ghost("Ghost4", scene.getColumnCount()-1, scene.getRowCount()-1, new ImageIcon("src/Resources/inkyLeft.png"), new ArrayList<>()));

        GhostsMovementThread gmt = new GhostsMovementThread(scene);
        gmt.spawn();
        gmt.start();

        GameOverListenerThread golt = new GameOverListenerThread();
        golt.start();

        movementThread.spawn();
        movementThread.start();
        AnimationThread animationThread = new AnimationThread(scene, pacMan, new ImageIcon[]{
                new ImageIcon("src/Resources/pacman.png"),
                new ImageIcon("src/Resources/pacmanDown.png")
        });
        animationThread.start();
        listeners.put(KeyListener.class, this);
        GameWindow.instance.addKeyListener(this);
        //pack();
        GameWindow.instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //GameWindow.instance.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        Runtime.getRuntime().addShutdownHook(new Thread(GameWindow::safeQuit));

        setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        SwingUtilities.invokeLater(() -> {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Q -> {
                    if(e.isShiftDown() && e.isControlDown()) {
                        GameWindow.instance.turn(MainMenu.class);
                    }
                }
                case KeyEvent.VK_UP -> {
                    pacMan.direction = Direction.UP;
                    pacMan.inMove = true;
                    System.out.println(pacMan.getCords().x + " " + pacMan.getCords().y);
                }
                case KeyEvent.VK_DOWN -> {
                    pacMan.direction = Direction.DOWN;
                    pacMan.inMove = true;
                    scene.repaint();
                    System.out.println(pacMan.getCords().x + " " + pacMan.getCords().y);
                }
                case KeyEvent.VK_LEFT -> {
                    pacMan.direction = Direction.LEFT;
                    pacMan.inMove = true;
                    scene.repaint();
                    System.out.println(pacMan.getCords().x + " " + pacMan.getCords().y);

                }
                case KeyEvent.VK_RIGHT -> {
                    pacMan.direction = Direction.RIGHT;
                    pacMan.inMove = true;
                    scene.repaint();
                    System.out.println(pacMan.getCords().x + " " + pacMan.getCords().y);

                }
            }
        });
    }



    static boolean over = false;
    public static void gameOver(PacmanMovementThread.GameOverReason lose) {
        if(over)return;
        over = true;
        PacMan pacMan = instance.pacman();
        if(lose == PacmanMovementThread.GameOverReason.LOSE){
            if( pacMan.getLives() > 1){
                for (Ghost ghost : instance.ghosts) {
                    ghost.respawn(instance.scene);
                }

                pacMan.setLives(pacMan.getLives()-1);
                pacMan.setInMove(false);
                pacMan.setDirection(Direction.LEFT);
                Cords spawnPosition = new Cords(instance.startX, instance.startY);
                pacMan.setCords(spawnPosition);
                instance.scene.setEntity(pacMan, pacMan.getCords().y, pacMan.getCords().x);
                instance.scene.update();

            }else{
                saveStatistic();
            }
        }else{
            saveStatistic();
        }
    }
    public static void saveStatistic(){
        GetUsername gu = new GetUsername();
        String name = gu.name();

        long time = UIThread.instance.passed();
        int score = instance.pacman().getScore();
        Statistic.StatisticData data = new Statistic.StatisticData(time, score);
        Main.stat.get().add(new AbstractMap.SimpleEntry<>(name, data));
        Main.stat.save();
        GameWindow.instance.turn(MainMenu.class);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
