import java.util.*;

public class CompositeBestForCustomer extends CopositeStrategy implements IPricingStrategy{
    public double getSubTotal(SaleLineItem item) {
        double subTotal = Double.MAX_VALUE;
        for (String x : strategies) {
            IPricingStrategy ips = PricingStrategyFactory.getInstance().getPricingStrategy(x);
            if (ips.getSubTotal(item) < subTotal)
                subTotal = ips.getSubTotal(item);
        }
        return subTotal;
    }
}