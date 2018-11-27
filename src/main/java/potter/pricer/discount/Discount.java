package potter.pricer.discount;

import potter.Price;

import java.math.BigDecimal;

public class Discount {

    private final int percent;

    private Discount(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException();
        }
        this.percent = percent;
    }


    public static Discount of(int percent) {
        return new Discount(percent);
    }

    public Price applyOn(Price price) {
        return price.mul(1 - BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100), BigDecimal.ROUND_CEILING).doubleValue());
    }
}
