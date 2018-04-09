import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import java.util.*;

public class MainUI extends JFrame {
    public static void main(String[] args) {
        MainUI mui = new MainUI();
        mui.setVisible(true);
    }

    private void generateTestData() {
        // Add some strategies
        Controller.getInstance().addSimpleStrategy("Discount001", "Absolute Value Prefered", "AbsoluteValue", 2, 1);
        Controller.getInstance().addSimpleStrategy("Discount002", "Percentage Prefered 1", "Percentage", 3, 7);
        Controller.getInstance().addSimpleStrategy("Discount003", "Percentage Prefered 2", "Percentage", 1, 3);
        Controller.getInstance().addCompositeStrategy("Discount004", "Composite Strategy", 4, 
            new ArrayList<String>(Arrays.<String>asList("Discount001,Discount003".split(","))));
        
        // Add some books
        Controller.getInstance().addBook(new BookSpecification("978-7-302-2", 18, "UML and pattern application", 2));
        Controller.getInstance().addBook(new BookSpecification("978-7-312-3", 34, "Java and mode", 1));
        Controller.getInstance().addBook(new BookSpecification("968-6-302-1", 58, "HeadFirst design pattern", 1));
        Controller.getInstance().addBook(new BookSpecification("958-1-302-2", 30, "Alice's Adventures", 3));
        Controller.getInstance().addBook(new BookSpecification("900-7-392-2", 20, "Encyclopedia of soup", 4));
    }

    public MainUI() {
        this.generateTestData();

        this.setTitle("Antarctica - Buy Books Online!!");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        // Add Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu buyUIMenu = new JMenu("Buy Books");
        JMenu shoppingCartUIMenu = new JMenu("View Shopping Cart");
        JMenu manageMenu = new JMenu("Manage");
        JMenuItem buyUIMenuItem = new JMenuItem("Buy!");
        JMenuItem shoppingCartUIMenuItem = new JMenuItem("View");
        JMenuItem addBookUIItem = new JMenuItem("Books");
        JMenuItem strategiesUIItem = new JMenuItem("Strategies");
        JMenuItem exitItem = new JMenuItem("Exit");

        menuBar.add(buyUIMenu);
        menuBar.add(shoppingCartUIMenu);
        menuBar.add(manageMenu);
        buyUIMenu.add(buyUIMenuItem);
        shoppingCartUIMenu.add(shoppingCartUIMenuItem);
        manageMenu.add(addBookUIItem);
        manageMenu.add(strategiesUIItem);
        manageMenu.addSeparator();
        manageMenu.add(exitItem);

        this.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalGlue());
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        JLabel firstLine = new JLabel("Welcome to Antarctica!");
        firstPanel.add(Box.createHorizontalGlue());
        firstPanel.add(firstLine);
        firstPanel.add(Box.createHorizontalGlue());
        mainPanel.add(firstPanel);
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
        JLabel secondLine = new JLabel("Click Buy Books -> Buy! to buy a lot of books!");
        secondPanel.add(Box.createHorizontalGlue());
        secondPanel.add(secondLine);
        secondPanel.add(Box.createHorizontalGlue());
        mainPanel.add(secondPanel);
        mainPanel.add(Box.createVerticalGlue());
        this.add(mainPanel);

        /*MenuListener menuAL = new MenuListener() {
            public void menuSelected(MenuEvent e) {
                if (e.getSource() == buyUIMenu) { // BuyUI
                   BuyUI bui = new BuyUI();
                   bui.setModal(false);
                   bui.setVisible(true);
                } else if (e.getSource() == shoppingCartUIMenu) { // ShoppingCartUI
                    ShoppingCartUI scui = new ShoppingCartUI();
                    scui.setModal(false);
                    scui.setVisible(true);
                }
            }
            public void menuCanceled(MenuEvent e) {
                // Nothing
            }
            public void menuDeselected(MenuEvent e) {
                // Nothing
            }
        };
        buyUIMenu.addMenuListener(menuAL);
        shoppingCartUIMenu.addMenuListener(menuAL);*/

        JFrame me = this;

        // Set Manage Action Listener
        ActionListener manageAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buyUIMenuItem) { // BuyUI
                    BuyUI bui = new BuyUI();
                    bui.setModal(false);
                    bui.setVisible(true);
                 } else if (e.getSource() == shoppingCartUIMenuItem) { // ShoppingCartUI
                     Controller.getInstance().openShoppingCart();
                 } else if (e.getSource() == addBookUIItem) { // AddBookUI
                    AddBookUI aui = new AddBookUI();
                    aui.setModal(true);
                    aui.setVisible(true);
                } else if (e.getSource() == strategiesUIItem) { // StrategiesUI
                    StrategiesUI sui = new StrategiesUI();
                    sui.setModal(true); // stay here
                    sui.setVisible(true); 
                } else if (e.getSource() == exitItem) { // Exit
                    System.exit(0);
                }
            }
        };
        buyUIMenuItem.addActionListener(manageAL);
        shoppingCartUIMenuItem.addActionListener(manageAL);
        addBookUIItem.addActionListener(manageAL);
        strategiesUIItem.addActionListener(manageAL);
        exitItem.addActionListener(manageAL);
    }
}