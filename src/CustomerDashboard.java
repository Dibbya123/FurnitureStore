
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerDashboard extends JFrame {
    private JTable furnitureTable;
    private DefaultTableModel tableModel;
    private ArrayList<Furniture> itemList;

    public CustomerDashboard() {
        itemList = new ArrayList<>();
        setTitle("Customer Dashboard - Purchase Items");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.BOLD, 14);

        // Table for items
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Quantity", "Description"}, 0);
        furnitureTable = new JTable(tableModel);
        add(new JScrollPane(furnitureTable), BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton buyButton = new JButton("Buy");
        JButton cancelButton = new JButton("Cancel");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(buyButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        buyButton.addActionListener(e -> buyItem());
        cancelButton.addActionListener(e -> cancelTransaction());
        logoutButton.addActionListener(e -> logout());

        loadItems(); // Load sample items
        setVisible(true);
    }

    private void loadItems() {
        itemList.add(new Furniture(1, "Sofa", "Living Room", 199.99, 5, "Comfortable 3-seater sofa"));
        itemList.add(new Furniture(2, "Dining Table", "Dining", 299.99, 3, "Wooden dining table with 4 chairs"));

        tableModel.setRowCount(0);
        for (Furniture item : itemList) {
            tableModel.addRow(new Object[]{item.getId(), item.getName(), item.getCategory(), item.getPrice(), item.getQuantity(), item.getDescription()});
        }
    }

    private void buyItem() {
        int selectedRow = furnitureTable.getSelectedRow();
        if (selectedRow != -1) {
            int quantity = (int) tableModel.getValueAt(selectedRow, 4);
            if (quantity > 0) {
                tableModel.setValueAt(quantity - 1, selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Item purchased successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, item is out of stock!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to buy.");
        }
    }

    private void cancelTransaction() {
        JOptionPane.showMessageDialog(this, "Transaction canceled.");
    }

    private void logout() {
        dispose();
        new Login();
    }

    public static void main(String[] args) {
        new CustomerDashboard();
    }
}
