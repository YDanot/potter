package potter.pricer.discount;

import potter.Price;

import java.math.BigDecimal;

public class Discount {

    private final double percentValue;

    private Discount(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException();
        }
        this.percentValue = BigDecimal.valueOf(percentage).scaleByPowerOfTen(-2).doubleValue();
    }

    public static Discount of(int percentage) {
        return new Discount(percentage);
    }

    public Price applyOn(Price price) {
        return price.mul(1 - percentValue);
    }
}
