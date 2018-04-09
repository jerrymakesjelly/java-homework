import java.util.*;

public class Sale extends Observable {
    public ArrayList<SaleLineItem> items;

    public Sale() {
        items = new ArrayList<>();
    }

    public void addItem(SaleLineItem item) {
        items.add(item);
        setChanged();
        notifyObservers();
    }

    public void removeItem(int index) {
        items.remove(index);
        setChanged();
        notifyObservers();
    }

    public double getTotal() {
        double sum = 0;
        for (SaleLineItem x : items)
            sum += x.copies * (x.prodSpec.price - x.getSubTotal());
        return sum;
    }
}