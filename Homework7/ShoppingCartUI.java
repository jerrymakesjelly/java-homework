import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShoppingCartUI extends JDialog implements Observer {
    private DefaultTableModel tableModel;
    private double totalPrice;
    private JLabel tpLabel;
    
    public ShoppingCartUI() {
        this.setTitle("Shopping Cart");
        this.setSize(640, 480);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Table
        String[] header = {"ISBN", "Book Name", "Unit Price", "Quantity", "Discount", "Total Price"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Total Price Label
        JPanel tpPanel = new JPanel();
        tpPanel.setLayout(new BoxLayout(tpPanel, BoxLayout.X_AXIS));
        tpLabel = new JLabel();
        tpPanel.add(tpLabel);
        bottomPanel.add(tpPanel);

        // Pay Button
        JPanel payPanel = new JPanel();
        payPanel.setLayout(new BoxLayout(payPanel, BoxLayout.X_AXIS));
        JButton payButton = new JButton("Pay");
        payPanel.add(Box.createHorizontalGlue());
        payPanel.add(payButton);
        payPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(payPanel);

        // Add Pay Button Listener
        payButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You have paid $"+String.valueOf(totalPrice)+".", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Panel Container
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridBagLayout());
        
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        c1.fill = GridBagConstraints.BOTH;
        panelContainer.add(topPanel, c1);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.weightx = 1.0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.HORIZONTAL;
        panelContainer.add(bottomPanel, c3);
        this.add(panelContainer);
    }

    public void RefreshTable() {
        Sale sale = Controller.getInstance().getSale();
        tableModel.setRowCount(0);
        totalPrice = 0;
        for (SaleLineItem x : sale.items) {
            double discount = x.getSubTotal() * x.copies;
            totalPrice += x.prodSpec.price*x.copies - discount;
            String[] rowValues = {x.prodSpec.isbn, x.prodSpec.title, 
                String.valueOf(x.prodSpec.price), String.valueOf(x.copies),
                String.valueOf(discount), String.valueOf(x.prodSpec.price*x.copies-discount)
            };
            tableModel.addRow(rowValues);
        }
        tpLabel.setText("Total Price: $"+String.valueOf(totalPrice));
    }

    public void update (Observable o, Object arg) {
        this.RefreshTable();
    }
}