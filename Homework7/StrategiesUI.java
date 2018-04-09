import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StrategiesUI extends JDialog {
    private DefaultTableModel tableModel;

    public StrategiesUI() {
        this.setTitle("Strategies Manager");
        this.setSize(640, 480);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Table
        String[] header = {"ID", "Strategy Name", "Strategy Type", "Book Type", "Discount"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane);

        // Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        // ID Panel
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        JLabel idLabel = new JLabel("Strategy ID:     ");
        JTextField idText = new JTextField();
        idPanel.add(idLabel);
        idPanel.add(idText);
        middlePanel.add(idPanel);

        // Name Panel
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Strategy Name: ");
        JTextField nameText = new JTextField();
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        middlePanel.add(namePanel);

        // Strategy Type
        String[] sTypes = {"1: Simple Percentage", "2: Simple Absolute Value", "3: Combination"};
        JPanel stPanel = new JPanel();
        stPanel.setLayout(new BoxLayout(stPanel, BoxLayout.X_AXIS));
        JLabel stLabel = new JLabel("Strategy Type:  ");
        JComboBox stBox = new JComboBox<>(sTypes);
        stPanel.add(stLabel);
        stPanel.add(stBox);
        middlePanel.add(stPanel);

        // Book Type
        String[] bTypes = {"1: Non-teaching Material Computer Book", "2: Teaching Material",
            "3: Comic Strip", "4: Health", "5: Other"};
        JPanel btPanel = new JPanel();
        btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.X_AXIS));
        JLabel btLabel = new JLabel("Book Type:       ");
        JComboBox btBox = new JComboBox<>(bTypes);
        btPanel.add(btLabel);
        btPanel.add(btBox);
        middlePanel.add(btPanel);

        // Discount Value
        JPanel dvPanel = new JPanel();
        dvPanel.setLayout(new BoxLayout(dvPanel, BoxLayout.X_AXIS));
        JLabel dvLabel = new JLabel("Discount Value:");
        JTextField dvText = new JTextField();
        dvPanel.add(dvLabel);
        dvPanel.add(dvText);
        middlePanel.add(dvPanel);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        JButton addButton = new JButton("Add");
        JButton saveButton = new JButton("Save");
        JButton deleteButton = new JButton("Delete");
        bottomPanel.add(addButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(saveButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(deleteButton);

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
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 1.0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.HORIZONTAL;
        panelContainer.add(middlePanel, c2);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.weightx = 1.0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.HORIZONTAL;
        panelContainer.add(bottomPanel, c3);
        this.add(panelContainer);

        // Add Mouse Listener
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object id = tableModel.getValueAt(selectedRow, 0);
                Object sn = tableModel.getValueAt(selectedRow, 1);
                Object st = tableModel.getValueAt(selectedRow, 2);
                Object bt = tableModel.getValueAt(selectedRow, 3);
                Object discount = tableModel.getValueAt(selectedRow, 4);
                idText.setText(id.toString());
                nameText.setText(sn.toString());
                stBox.setSelectedIndex(Integer.parseInt(st.toString())-1);
                btBox.setSelectedIndex(Integer.parseInt(bt.toString())-1);
                dvText.setText(discount.toString());
            }
        });

        // Point to myself
        StrategiesUI me = this;

        // Add Button Listener
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    if (PricingStrategyFactory.getInstance().isPricingStrategyExist(btBox.getSelectedIndex()+1)) {
                        JOptionPane.showMessageDialog(null, "The price strategy of this book type is existed.", 
                            "Adding Action", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (stBox.getSelectedIndex() == 2) // Composite
                        Controller.getInstance().addCompositeStrategy(idText.getText(), nameText.getText(), 
                            btBox.getSelectedIndex()+1, 
                            new ArrayList<String>(Arrays.asList(dvText.getText().split(","))));
                    else {
                        if (stBox.getSelectedIndex() == 0) // Percentage
                            Controller.getInstance().addSimpleStrategy(idText.getText(), nameText.getText(), 
                                "Percentage", btBox.getSelectedIndex()+1, Integer.parseInt(dvText.getText()));
                        else // Absolute Value
                            Controller.getInstance().addSimpleStrategy(idText.getText(), nameText.getText(), 
                                "AbsoluteValue", btBox.getSelectedIndex()+1, Double.parseDouble(dvText.getText()));
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
                me.RefreshTable();
            }
        });
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!PricingStrategyFactory.getInstance().isPricingStrategyExist(btBox.getSelectedIndex()+1)) {
                        JOptionPane.showMessageDialog(null, "The price strategy of this book type is not existed.", 
                            "Saving Action", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    String[] sType = {"Percentage", "AbsoluteValue", "Composite"};
                    Controller.getInstance().updateStrategy(idText.getText(), nameText.getText(), 
                        sType[stBox.getSelectedIndex()], btBox.getSelectedIndex()+1, dvText.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
                me.RefreshTable();
            }
        });
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1)
                        Controller.getInstance().deleteStrategy(tableModel.getValueAt(selectedRow, 0).toString());
                    else
                        JOptionPane.showMessageDialog(null, "No rows selected.", "Deleting Action", JOptionPane.WARNING_MESSAGE);
                    me.RefreshTable();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
                me.RefreshTable();
            }
        });

        // Refresh Table
        this.RefreshTable();
    }

    // Refresh
    private void RefreshTable() {
        StrategyCatalog catalog = PricingStrategyFactory.getInstance().getCatalog();
        tableModel.setRowCount(0);
        for (Strategy x : catalog.strategies.values()) {
            String[] rowValues = {x.strategyID, x.strategyName, String.valueOf(x.strategyType), String.valueOf(x.bookType), x.value};
            tableModel.addRow(rowValues);
        }
    }
}