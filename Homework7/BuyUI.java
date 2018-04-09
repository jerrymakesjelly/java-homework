import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class BuyUI extends JDialog {
    private DefaultTableModel tableModel;

    public BuyUI() {
        this.setTitle("Buy Books");
        this.setSize(640, 480);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Table
        String[] header = {"ISBN", "Book Name", "Unit Price"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane);

        // Middle Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // ISBN Panel
        JPanel isbnPanel = new JPanel();
        isbnPanel.setLayout(new BoxLayout(isbnPanel, BoxLayout.X_AXIS));
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnText = new JTextField();
        isbnPanel.add(isbnLabel);
        isbnPanel.add(isbnText);
        bottomPanel.add(isbnPanel);

        // Purchase Quantity
        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.X_AXIS));
        JLabel pqLabel = new JLabel("Purchase Quantity:");
        JTextField pqText = new JTextField();
        JButton purchaseButton = new JButton("Purchase");
        purchasePanel.add(pqLabel);
        purchasePanel.add(pqText);
        purchasePanel.add(Box.createHorizontalGlue());
        purchasePanel.add(purchaseButton);
        bottomPanel.add(purchasePanel);

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

        BuyUI me = this;

        // Add Mouse Listener
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object isbn = tableModel.getValueAt(selectedRow, 0);
                isbnText.setText(isbn.toString());
                pqText.setText("1");
            }
        });
        purchaseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().buyBook(isbnText.getText(), Integer.parseInt(pqText.getText()));
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, exc.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.RefreshTable();
    }

    public void RefreshTable() {
        BookCatalog catalog = Controller.getInstance().getBookCatalog();
        tableModel.setRowCount(0);
        for (BookSpecification x : catalog.getCatalog()) {
            String[] rowValues = {x.isbn, x.title, String.valueOf(x.price)};
            tableModel.addRow(rowValues);
        }
    }
}