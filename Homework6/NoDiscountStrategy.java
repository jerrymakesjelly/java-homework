public class NoDiscountStrategy implements IPricingStrategy {
    public double getSubTotal(SaleLineItem item) {
        return 0;
    }
}