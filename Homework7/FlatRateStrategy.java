public class FlatRateStrategy implements IPricingStrategy {
    private double discountPerBook;

    public FlatRateStrategy(double discount) {
        this.discountPerBook = discount;
    }

    public double getSubTotal(SaleLineItem item) {
        return this.discountPerBook;
    }
}