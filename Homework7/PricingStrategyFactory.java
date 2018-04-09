public class PricingStrategyFactory {
    private static PricingStrategyFactory instance;
    private StrategyCatalog catalog;

    private PricingStrategyFactory() {
        catalog = new StrategyCatalog();
    }

    public static synchronized PricingStrategyFactory getInstance() {
        if (instance == null)
            instance = new PricingStrategyFactory();
        return instance;
    }

    public void addPricingStrategy(String strategyID, String strategyName, int strategyType, int bookType, String value, IPricingStrategy ips) {
        Strategy sty = new Strategy(strategyID, strategyName, strategyType, bookType, value, ips);
        catalog.strategies.put(bookType, sty);
    }

    public void deleteStrategy(String strategyID) {
        Strategy preDelete = null;
        for (Strategy x : catalog.strategies.values())
            if (x.strategyID.equals(strategyID)) {
                preDelete = x;
                break;
            }
        if (preDelete != null)
            catalog.strategies.remove(preDelete.bookType);
    }

    public StrategyCatalog getCatalog() {
        return this.catalog;
    }

    public boolean isPricingStrategyExist(int type) {
        Strategy result = catalog.strategies.get(type);
        return result != null;
    }

    public IPricingStrategy getPricingStreategy(int type) {
        Strategy result = catalog.strategies.get(type);
        if (result == null)
            return new NoDiscountStrategy();
        else
            return result.pricingStrategy;
    }

    public IPricingStrategy getPricingStrategy(String strategyID) {
        for (Strategy x : catalog.strategies.values())
            if (x.strategyID.equals(strategyID))
                return x.pricingStrategy;
        return new NoDiscountStrategy();
    }
}