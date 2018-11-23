package potter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Price {

    private final BigDecimal value;
    private final Currency currency;

    private Price(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Price of(String price) {
        final String[] split = price.split(" ");
        return new Price(new BigDecimal(split[1]), Currency.getInstance(split[0]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value) &&
                Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return currency.getCurrencyCode() +" "+ value ;
    }

    public Price mul(int size) {
        return new Price(value.multiply(BigDecimal.valueOf(size)), currency);
    }
}
