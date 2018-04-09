public class PricingStrategyFactory {
    private static PricingStrategyFactory instance;

    public static synchronized PricingStrategyFactory getInstance() {
        if (instance == null)
            instance = new PricingStrategyFactory();
        return instance;
    }

    public static IPricingStrategy getPricingStreategy(BookType type) {
        switch (type) {
            case TeachingMaterial:
                return new FlatRateStrategy(1);
            case ComicStrip:
                return new PercentageStrategy(7);
            case ComputerBook:
                return new PercentageStrategy(3);
            default:
                return new NoDiscountStrategy();
        }
    }
}