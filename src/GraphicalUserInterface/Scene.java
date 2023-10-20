package GraphicalUserInterface;

import Components.*;
import Models.Cords;
import Models.Entity;
import Models.EntityType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JTable {
    public Scene() {
        setModel(new Board(20, 20));
        setAutoResizeMode(AUTO_RESIZE_OFF);
        DefaultTableColumnModel defaultTableColumnModel = new DefaultTableColumnModel();
        defaultTableColumnModel.setColumnMargin(0);
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setBackground(Color.black);
        defaultTableCellRenderer.setText("PacMan");
        defaultTableCellRenderer.setSize(new Dimension(100, 100));
        defaultTableCellRenderer.setIcon(new ImageIcon("pacman.png"));
        defaultTableCellRenderer.setHorizontalTextPosition(JLabel.CENTER);
        defaultTableCellRenderer.setPreferredSize(new Dimension(20,20));
        defaultTableCellRenderer.setForeground(Color.blue);

        ColumnRenderer columnRenderer = new ColumnRenderer();
        CellRenderer cellRenderer = new CellRenderer();
        for (int i = 0; i < getColumnCount() ; i++) {
            TableColumn tableColumn = new TableColumn(i);
            tableColumn.setPreferredWidth(45);
            tableColumn.setCellRenderer(cellRenderer);
            columnRenderer.addColumn(tableColumn);
        }
        setColumnModel(columnRenderer);
        setRowMargin(0);
        setShowGrid(false);
        setCellSelectionEnabled(false);
        setColumnSelectionAllowed(false);
        setShowHorizontalLines(false);
        setShowVerticalLines(false);
        setRowHeight(45);
        setColumnSelectionInterval(0,0);
        setRowSelectionInterval(0,0);
        setRowHeight(45);
        setFocusable(false);
        setVisible(true);
    }
    public void setEntity(Entity e, int x, int y){
        this.setValueAt(e, x, y);
    }
    public ArrayList<Cords> getWalls(){
        TableModel tm = this.getModel();
        // вот пример
        //if(tm instanceof DefaultTableColumnModel){ ... }
        if(tm instanceof Board b){
            return b.getWalls();
        }
        return new ArrayList<>();
    }
    public void setAt(EntityType e, int x, int y){
        this.setValueAt(e.asInteger(), x, y);
    }
    public void update(){
        revalidate();
        repaint();
    }

}
