import java.util.*;
//import javafx.scene.layout.BorderStrokeStyle;

public class Controller {
    private static Controller instance;
    private BookCatalog bookCatalog;
    private Sale sale;
    private ShoppingCartUI scui;

    public Controller() {
        bookCatalog = new BookCatalog();
        sale = new Sale();
        scui = new ShoppingCartUI();
        sale.addObserver(scui);
    }

    public static synchronized Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public void addBook(BookSpecification book) {
        bookCatalog.addBook(book);
    }

    public BookCatalog getBookCatalog() {
        return this.bookCatalog;
    }

    public void buyBook(String ISBN, int quantity) {
        SaleLineItem sli = new SaleLineItem(quantity, 
            bookCatalog.getBookSpecification(ISBN));
        sale.addItem(sli);
    }

    public Sale getSale() {
        return this.sale;
    }

    public void openShoppingCart() {
        scui.setModal(false);
        scui.setVisible(true);
    }

    public void addCompositeStrategy(String strategyID, String strategyName, int type, ArrayList<String> strategies) {
        CompositeBestForCustomer cbfc = new CompositeBestForCustomer();
        for (String x : strategies)
            cbfc.strategies.add(x);
        String value = "";
        boolean isFirst = true;
        for (String x : strategies) {
            if (isFirst) {
                isFirst = false;
                value += x;
            } else {
                value += "," + x;
            }
        }
        PricingStrategyFactory.getInstance().addPricingStrategy(strategyID, strategyName, 3, type, value, cbfc);
    }

    public void addSimpleStrategy(String strategyID, String strategyName, String strategyType, int bookType, double value) {
        IPricingStrategy ips;
        int type = 5;
        String strValue = "0";
        if (strategyType.equals("AbsoluteValue")) {
            type = 2;
            strValue = String.valueOf(value);
            ips = new FlatRateStrategy(value);
        }
        else if(strategyType.equals("Percentage")) {
            type = 1;
            strValue = String.valueOf(value);
            ips = new PercentageStrategy((int)value);
        }
        else if(strategyType.equals("NoDiscount")) {
            type = 4;
            ips = new NoDiscountStrategy();
        }
        else
            ips = null;
        
        if (ips != null)
            PricingStrategyFactory.getInstance().addPricingStrategy(strategyID, strategyName, type, bookType, strValue, ips);
    }

    public void deleteStrategy(String strategyID) {
        PricingStrategyFactory.getInstance().deleteStrategy(strategyID);
    }

    public void updateStrategy(String strategyID, String strategyName, String strategyType, int bookType, String value) {
        Controller.getInstance().deleteStrategy(strategyID);
        if (strategyType.equals("Composite"))
            Controller.getInstance().addCompositeStrategy(strategyID, strategyName, bookType, new ArrayList<String>(Arrays.<String>asList(value.split(","))));
        else
            Controller.getInstance().addSimpleStrategy(strategyID, strategyName, strategyType, bookType, Double.parseDouble(value));
    }
}