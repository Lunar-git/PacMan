package GraphicalUserInterface;

import javax.swing.*;
import java.util.HashMap;

public abstract class SceneWindow extends JPanel {

    public String name;
    public int width, height;

    public final HashMap<Class<?>, Object> listeners = new HashMap<>();

}
