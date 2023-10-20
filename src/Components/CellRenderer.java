package Components;

import GraphicalUserInterface.GameScene;
import Models.Entity;
import Models.Ghost;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    public CellRenderer() {
        setHorizontalAlignment(CENTER);
        setIconTextGap(0);
        setBorder(null);
        setBackground(Color.black);
    }

    @Override
    protected void setValue(Object value) {
        if (value == null) {
            setIcon(null);
//            setText("0");
            return;
        }
        int integer = -1;
        if(value instanceof Entity){
            integer = ((Entity) value).type.asInteger();
        }else if(value instanceof Integer){
            integer = (int) value;
        }
        switch (integer) {
            case (1): {
                setIcon(new ImageIcon("src/Resources/wall.png"));
                break;
            }
            case (2): {
                setIcon(new ImageIcon("src/Resources/point.png"));
                break;
            }
            case (3): {
                setIcon(new ImageIcon("src/Resources/upgrade.png"));
                break;
            }
            case (4): {
                setIcon(GameScene.instance.pacman().getImage());
                break;
            }
            case (5): {
                setIcon(((Ghost) value).getImage());
                break;
            }
            case (6): {
                setIcon(new ImageIcon("src/Resources/up2.png"));
                break;
            }
            case (7): {
                setIcon(new ImageIcon("src/Resources/up3.png"));
                break;
            }
            case (8): {
                setIcon(new ImageIcon("src/Resources/down1.png"));
                break;
            }
            case (9): {
                setIcon(new ImageIcon("src/Resources/down2.png"));
                break;
            }
            case (10): {
                setIcon(new ImageIcon("src/Resources/down3.png"));
                break;
            }
            case (11): {
                setIcon(new ImageIcon("src/Resources/left1.png"));
                break;
            }
            case (12): {
                setIcon(new ImageIcon("src/Resources/left2.png"));
                break;
            }
            case (13): {
                setIcon(new ImageIcon("src/Resources/left3.png"));
                break;
            }
            case (14): {
                setIcon(new ImageIcon("src/Resources/right1.png"));
                break;
            }
            case (15): {
                setIcon(new ImageIcon("src/Resources/right2.png"));
                break;
            }
            case (16): {
                setIcon(new ImageIcon("src/Resources/right3.png"));
                break;
            }
            case (17): {
                setIcon(new ImageIcon("src/Resources/inkyLeft.png"));
                break;
            }
            case (18): {
                setIcon(new ImageIcon("src/Resources/inkyRight.png"));
                break;
            }
            case (19): {
                setIcon(new ImageIcon("src/Resources/inkyUp.png"));
                break;
            }
            case (20): {
                setIcon(new ImageIcon("src/Resources/inkyDown.png"));
                break;
            }

            default: {
                setIcon(null);
            }
        }
    }
}
