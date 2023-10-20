package Components;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;

public class ColumnRenderer extends DefaultTableColumnModel {
    public ColumnRenderer() {
        super();
        setColumnSelectionAllowed(false);
        setColumnMargin(0);
    }
}
