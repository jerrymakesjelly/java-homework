public class PercentageStrategy implements IPricingStrategy {
    private int discountPercentage;

    public PercentageStrategy(int discount) {
        this.discountPercentage = discount;
    }

    public double getSubTotal(SaleLineItem item) {
        return item.prodSpec.price * (double)this.discountPercentage / 100.0;
    }
}