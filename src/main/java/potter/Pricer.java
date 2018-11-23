package potter;

public class Pricer {
    public Pricer() {
    }

    public Price priceOf(Order order) {
        return Price.of("EUR 8.00").mul(order.size());
    }
}