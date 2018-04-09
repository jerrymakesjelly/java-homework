import java.util.*;

public class CopositeStrategy {
    protected ArrayList<String> strategies; // Strategies ID

    public CopositeStrategy() {
        strategies = new ArrayList<>();
    }

    public void add(String strategyID) {
        strategies.add(strategyID);
    }
}