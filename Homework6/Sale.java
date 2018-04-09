import java.util.*;

public class Sale {
    public static void main(String args[]) {
        Sale s = new Sale();
        s.BuyingSimulator();
        System.out.println(s.getTotal());
    }

    private ArrayList<SaleLineItem> items;

    public Sale() {
        items = new ArrayList<>();
    }

    public void BuyingSimulator() {
        // Test Code
        items.add(new SaleLineItem(2, new ProductSpecification("", 18, "UML and pattern application", BookType.TeachingMaterial)));
        items.add(new SaleLineItem(2, new ProductSpecification("", 34, "Java and mode", BookType.ComputerBook)));
        items.add(new SaleLineItem(1, new ProductSpecification("", 58, "HeadFirst design pattern",BookType. ComputerBook)));
        items.add(new SaleLineItem(3, new ProductSpecification("", 30, "Alice's Adventures", BookType.ComicStrip)));
        items.add(new SaleLineItem(1, new ProductSpecification("", 20, "Encyclopedia of soup", BookType.Other)));
    }

    public double getTotal() {
        double sum = 0;
        for (SaleLineItem x : items)
            sum += x.copies * (x.prodSpec.price - x.getSubTotal());
        return sum;
    }
}