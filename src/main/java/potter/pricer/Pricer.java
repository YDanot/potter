package potter.pricer;

import potter.Order;
import potter.Price;
import potter.pricer.discount.Bundle;
import potter.pricer.discount.Bundler;
import potter.pricer.discount.Discount;
import potter.pricer.discount.DiscountRules;

import java.util.List;

public class Pricer {

    private final DiscountRules discountRules;

    public Pricer(DiscountRules discountRules) {
        this.discountRules = discountRules;
    }

    public Price priceOf(Order order) {
        return splitInBundles(order).stream().map(this::priceOf).reduce(Price::add).orElse(Price.ZERO);
    }

    private List<Bundle> splitInBundles(Order order) {
        return new Bundler().split(order);
    }

    private Price priceOf(Bundle order) {
        final Price unitPrice = Price.of("EUR 8.00");

        return discountRules.getDiscountFor(order.books().size()).applyOn(unitPrice.mul(order.size()));
    }

    
}