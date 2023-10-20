package GraphicalUserInterface;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends SceneWindow {
    public MainMenu() {
        setSize(240, 180);
        setLayout(new GridLayout(3, 0, 10, 5));
        ImageIcon newGamePNG = Resizer.resizeImage(this.getWidth()/2, this.getHeight()/2, "src/Resources/play.png");
        ImageIcon highScoresPNG = Resizer.resizeImage(30, 30, "src/Resources/highScore.png");
        ImageIcon exitPNG = Resizer.resizeImage(20, 20, "src/Resources/rock.png");

        this.name = "Pacman menu";
        this.width = 500;
        this.height = 400;
        GameWindow.instance.setSize(getSize());

        int offset = GameWindow.instance.getHeight() - getHeight();

                JButton newGame = new JButton();
        newGame.setText("New Game");
        newGame.setVerticalTextPosition(AbstractButton.CENTER);
        newGame.setHorizontalTextPosition(AbstractButton.LEADING);
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

                newGame.setIcon(Resizer.resizeImage(
                        e.getComponent().getWidth()/4,
                        e.getComponent().getWidth()/4,
                        "src/Resources/pla.png"));
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        JButton highScores = new JButton();
        highScores.setText("High Scores");
        highScores.setVerticalTextPosition(AbstractButton.CENTER);
        highScores.setHorizontalTextPosition(AbstractButton.LEADING);

        JButton exit = new JButton();
        exit.setText("Exit");
        exit.setVerticalTextPosition(AbstractButton.CENTER);
        exit.setHorizontalTextPosition(AbstractButton.LEADING);
        newGame.addActionListener(event -> {
            GameWindow.instance.turn(GameScene.class);
            //SwingUtilities.invokeLater();
        });

        highScores.addActionListener(event -> {
            GameWindow.instance.turn(ScoreboardScene.class);
            //SwingUtilities.invokeLater();
        });

        exit.addActionListener(event -> {
            GameWindow.quit();
        });

        List<JButton> buttonList = new ArrayList<>();
        buttonList.add(newGame);
        buttonList.add(highScores);
        buttonList.add(exit);
        for(JButton jButton : buttonList) {
            add(jButton);
            jButton.setPreferredSize(new Dimension(width, 100 ));
            jButton.setBackground(new Color(112, 41, 99));
            jButton.setForeground(Color.WHITE);
            jButton.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            jButton.setFocusPainted(false);
        }
        Color background = new Color(85, 40, 90);
        setBackground(background);
        GameWindow.instance.getContentPane().setBackground(background);

        ImageIcon icon = new ImageIcon("Resources/pacman.png");
        //setIconImage(icon.getImage());

        //add(jPanel);
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
