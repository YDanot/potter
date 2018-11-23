package potter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PotterTest {

    @Test
    public void one_book_should_cost_8_eur() {
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_1))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_2))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_3))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_4))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_5))).isEqualTo("EUR 8.00");
    }

    @Test
    public void order_with_twice_a_book_should_cost_16_eur() {
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_1, Book.HARRY_POTTER_1))).isEqualTo("EUR 16.00");
    }

    @Test
    public void order_with_three_times_a_book_should_cost_24_eur() {
        Assertions.assertThat(priceOf(orderOf(Book.HARRY_POTTER_1, Book.HARRY_POTTER_1, Book.HARRY_POTTER_1))).isEqualTo("EUR 24.00");
    }

    private String priceOf(Order order) {
        return new Pricer().priceOf(order).toString();
    }

    private Order orderOf(Book... books) {
        Order order = new Order();
        for (Book book : books) {
            order = order.add(book);
        }
        return order;
    }

}
