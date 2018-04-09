import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookUI extends JDialog {
    private DefaultTableModel tableModel;

    public AddBookUI() {
        this.setTitle("Add Books");
        this.setSize(640, 480);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Table
        String[] header = {"ISBN", "Book Name", "Price", "Book Type"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane);

        // Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        // ISBN Panel
        JPanel isbnPanel = new JPanel();
        isbnPanel.setLayout(new BoxLayout(isbnPanel, BoxLayout.X_AXIS));
        JLabel isbnLabel = new JLabel("ISBN:     ");
        JTextField isbnText = new JTextField();
        isbnPanel.add(isbnLabel);
        isbnPanel.add(isbnText);
        middlePanel.add(isbnPanel);

        // Book Name Panel
        JPanel bnPanel = new JPanel();
        bnPanel.setLayout(new BoxLayout(bnPanel, BoxLayout.X_AXIS));
        JLabel bnLabel = new JLabel("Book Name:");
        JTextField bnText = new JTextField();
        bnPanel.add(bnLabel);
        bnPanel.add(bnText);
        middlePanel.add(bnPanel);

        // Price Panel
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.X_AXIS));
        JLabel priceLabel = new JLabel("Price:    ");
        JTextField priceText = new JTextField();
        pricePanel.add(priceLabel);
        pricePanel.add(priceText);
        middlePanel.add(pricePanel);

        // Book Type Panel
        String[] bTypes = {"1: Non-teaching Material Computer Book", "2: Teaching Material",
            "3: Comic Strip", "4: Health", "5: Other"};
        JPanel btPanel = new JPanel();
        btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.X_AXIS));
        JLabel btLabel = new JLabel("Book Type:");
        JComboBox btBox = new JComboBox<>(bTypes);
        btPanel.add(btLabel);
        btPanel.add(btBox);
        middlePanel.add(btPanel);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        JButton addButton = new JButton("Add");
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(addButton);
        bottomPanel.add(Box.createHorizontalGlue());

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

        AddBookUI me = this;

        // Add Mouse Listener
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object isbn = tableModel.getValueAt(selectedRow, 0);
                Object bn = tableModel.getValueAt(selectedRow, 1);
                Object price = tableModel.getValueAt(selectedRow, 2);
                Object bt = tableModel.getValueAt(selectedRow, 3);
                isbnText.setText(isbn.toString());
                bnText.setText(bn.toString());
                priceText.setText(price.toString());
                btBox.setSelectedIndex(Integer.parseInt(bt.toString())-1);
            }
        });

        // Add Button Listener
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Controller.getInstance().getBookCatalog().getBookSpecification(isbnText.getText()) != null) { // Existed
                        JOptionPane.showMessageDialog(null, "The ISBN is existed.", "Adding Action", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Controller.getInstance().addBook(new BookSpecification(isbnText.getText(), Double.parseDouble(priceText.getText()), 
                        bnText.getText(), btBox.getSelectedIndex()+1));
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
                me.RefreshTable();
            }
        });

        // Refresh Table
        this.RefreshTable();
    }

    private void RefreshTable() {
        BookCatalog catalog = Controller.getInstance().getBookCatalog();
        tableModel.setRowCount(0);
        for (BookSpecification x : catalog.getCatalog()) {
            String[] rowValues = {x.isbn, x.title, String.valueOf(x.price), String.valueOf(x.type)};
            tableModel.addRow(rowValues);
        }
    }
}