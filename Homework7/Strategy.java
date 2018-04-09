public class Strategy {
    public String strategyName;
    public String strategyID;
    public int strategyType;
    public int bookType;
    public String value;
    public IPricingStrategy pricingStrategy;

    public Strategy(String strategyID, String strategyName, int strategyType, int bookType, String value, IPricingStrategy pricingStrategy) {
        this.strategyName = strategyName;
        this.strategyID = strategyID;
        this.strategyType = strategyType;
        this.bookType = bookType;
        this.value = value;
        this.pricingStrategy = pricingStrategy;
    }
}