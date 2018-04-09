public class SaleLineItem {
    public int copies;
    public ProductSpecification prodSpec;
    private IPricingStrategy strategy;

    public SaleLineItem(int copies, ProductSpecification prodSpec) {
        this.copies = copies;
        this.prodSpec = prodSpec;
        this.strategy = PricingStrategyFactory.getInstance().getPricingStreategy(this.prodSpec.type);
    }

    public double getSubTotal() {
        return strategy.getSubTotal(this);
    }
}