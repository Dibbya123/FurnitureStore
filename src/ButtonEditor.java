

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private int selectedRow;

    public ButtonEditor(JCheckBox checkBox) {
        button = new JButton("Buy");
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Stop editing and fire the event
                JOptionPane.showMessageDialog(null, "Item Bought!");
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }
}