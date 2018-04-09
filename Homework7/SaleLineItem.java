public class SaleLineItem {
    public int copies;
    public BookSpecification prodSpec;
    private IPricingStrategy strategy;

    public SaleLineItem(int copies, BookSpecification prodSpec) {
        this.copies = copies;
        this.prodSpec = prodSpec;
        this.strategy = PricingStrategyFactory.getInstance().getPricingStreategy(prodSpec.type);
    }

    public double getSubTotal() {
        return strategy.getSubTotal(this);
    }
}