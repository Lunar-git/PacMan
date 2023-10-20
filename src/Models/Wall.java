package Models;

import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Wall extends Entity{
    public Wall(int x, int y) {
        super("Wall", x, y, new ImageIcon("./wall.png"), EntityType.WALL, new ArrayList<>());
    }
}
