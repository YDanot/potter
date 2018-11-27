package potter.pricer;

import potter.Order;
import potter.Price;
import potter.pricer.discount.Bundle;
import potter.pricer.discount.Bundler;
import potter.pricer.discount.Discount;

import java.util.List;

public class Pricer {

    public Price priceOf(Order order) {
        return splitInBundles(order).stream().map(this::priceOf).reduce(Price::add).get();
    }

    private List<Bundle> splitInBundles(Order order) {
        return new Bundler().split(order);
    }

    private Price priceOf(Bundle order) {
        final Price unitPrice = Price.of("EUR 8.00");
        Price price = unitPrice.mul(order.size());

        if (order.contains2DifferentBooks()) {
            price = Discount.of(5).applyOn(price);
        }

        if (order.contains3DifferentBooks()) {
            price = Discount.of(10).applyOn(price);
        }

        if (order.contains4DifferentBooks()) {
            price = Discount.of(20).applyOn(price);
        }

        if (order.contains5DifferentBooks()) {
            price = Discount.of(25).applyOn(price);
        }

        return price;
    }

    
}