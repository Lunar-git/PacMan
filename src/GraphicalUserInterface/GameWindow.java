package GraphicalUserInterface;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class GameWindow extends JFrame implements ActionListener {
    public static GameWindow instance;
    JButton gameplayButton;
    JButton scoreboardButton;
    JPanel panel;
    public SceneWindow current;


    public GameWindow() {
        instance = this;
        panel = new JPanel();
        setContentPane(panel);



        //setUndecorated(true);
        turn(MainMenu.class); // по дефолту меню
        setLocationRelativeTo(null);
        setVisible(true);


    }

    public void turn(SceneWindow sceneWindow){
        if(current != null){
            for (Map.Entry<Class<?>, Object> e : current.listeners.entrySet()) {
                if(e.getKey().isAssignableFrom(KeyListener.class)){
                    removeKeyListener((KeyListener) e.getValue());
                }
            }
        }
        this.current = sceneWindow;
        this.panel.removeAll();
        this.panel.add(sceneWindow);
        if(sceneWindow.width != -1 && sceneWindow.height != -1)
        this.setSize(sceneWindow.width, sceneWindow.height);
        this.setTitle(sceneWindow.name);
        requestFocus();
        requestFocusInWindow();

    }
    public void turn(Class<? extends SceneWindow> clazz){
        try {
            SceneWindow scene = clazz.getConstructor().newInstance();
            this.turn(scene);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public static void safeQuit(){
        // TODO: Сохранение кеша, настроек и т.д.
    }
    public static void quit(){
        safeQuit();
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameplayButton) {
            System.out.println("Gameplay");

            turn(GameScene.class);
            // TO DO playGame();
        } else if (e.getSource() == scoreboardButton) {
            System.out.println("ScoreBoard");
            // TO DO scoreBoard();
        }
    }
}
//        gameplayButton = new JButton();
//        gameplayButton.addActionListener(this);
//        gameplayButton.setText("Play");
//        gameplayButton.setFocusable(false);
//        gameplayButton.setHorizontalTextPosition(JButton.RIGHT);
//        gameplayButton.setVerticalTextPosition(JButton.BOTTOM);
//        gameplayButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
//        gameplayButton.setForeground(Color.pink);
//        gameplayButton.setBackground(Color.black);
//        gameplayButton.setBorder(BorderFactory.createEtchedBorder());
//
//        scoreboardButton = new JButton();
//        scoreboardButton.addActionListener(this);
//        scoreboardButton.setText("LeaderBoard");
//        scoreboardButton.setFocusable(false);
//        scoreboardButton.setHorizontalTextPosition(JButton.RIGHT);
//        scoreboardButton.setVerticalTextPosition(JButton.BOTTOM);
//        scoreboardButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
//        scoreboardButton.setForeground(Color.pink);
//        scoreboardButton.setBackground(Color.black);
//        scoreboardButton.setBorder(BorderFactory.createEtchedBorder());
//
//        // Create a JPanel to hold the buttons
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Color.BLACK);
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 500));
//        buttonPanel.add(gameplayButton);
//        buttonPanel.add(scoreboardButton);
//
////        // Create a JPanel to hold the button panel and center it
////        JPanel centerPanel = new JPanel(new GridBagLayout());
////        centerPanel.setBackground(Color.BLACK);
////        centerPanel.add(buttonPanel);
//
////        // Create a JLabel to hold the GIF background
////        JLabel backgroundLabel = new JLabel(new ImageIcon("src/Resources/resize.gif"));
////        backgroundLabel.setLayout(new BorderLayout());
////        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);
//
//        // Set the content pane of the frame to the background label
////        this.setContentPane(backgroundLabel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(500, 500);
//        this.setResizable(true);
//        this.setVisible(true);
//    }
