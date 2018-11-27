package potter;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import potter.pricer.Pricer;

import java.math.BigDecimal;

import static potter.Book.*;

public class PotterTest {

    @Test
    public void one_book_should_cost_8_eur() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_2))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_3))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_4))).isEqualTo("EUR 8.00");
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_5))).isEqualTo("EUR 8.00");
    }

    @Test
    public void order_with_twice_a_book_should_cost_16_eur() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_1))).isEqualTo("EUR 16.00");
    }

    @Test
    public void order_with_three_times_a_book_should_cost_24_eur() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1))).isEqualTo("EUR 24.00");
    }

    @Test
    public void order_with_two_different_books_should_be_discounted_with_5_percent() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2))).isEqualTo(price(8 * 2 * 0.95));
    }

    @Test
    public void order_with_3_different_books_should_be_discounted_with_10_percent() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3))).isEqualTo(price(8 * 3 * 0.90));
    }

    @Test
    public void order_with_4_different_books_should_be_discounted_with_20_percent() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4))).isEqualTo(price(8 * 4 * 0.80));
    }

    @Test
    public void order_with_5_different_books_should_be_discounted_with_25_percent() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4, HARRY_POTTER_5))).isEqualTo(price(8 * 5 * 0.75));
    }

    @Test
    public void order_with_twice_2_different_books_should_be_discounted_with_5_percent() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_1, HARRY_POTTER_2))).isEqualTo(price(2 * (8 * 2 * 0.95)));
    }

    @Test
    public void order_with_twice_2_different_books_should_be_discounted_with_5_percent3() {
        Assertions.assertThat(priceOf(orderOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_1, HARRY_POTTER_2))).isEqualTo(price(3 * (8 * 2 * 0.95)));
    }

    @Test
    public void order_with_twice_2_different_books_should_be_discounted_with_5_percent5() {
        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4))).isEqualTo(price((8 * 4 * 0.8) + (8 * 2 * 0.95)));
    }

    @Test
    public void order_11_22_33_4_5() {

        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4,
                HARRY_POTTER_5))).isEqualTo(price(2 * (8 * 4 * 0.8)));
    }

    @Test
    public void order_11_22_3_4_5() {
        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3,
                HARRY_POTTER_4,
                HARRY_POTTER_5))).isEqualTo(price((8 * 5 * 0.75) + 8 * 2 * 0.95));
    }

    @Test
    public void order_11111_22222_3333_44444_5555() {

        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4,
                HARRY_POTTER_5, HARRY_POTTER_5, HARRY_POTTER_5, HARRY_POTTER_5))).isEqualTo(price(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8)));
    }

    @Test
    public void aTest() {
        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4, HARRY_POTTER_4,
                HARRY_POTTER_5))).isEqualTo(price(78.80));

        Assertions.assertThat(priceOf(orderOf(
                HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4,
                HARRY_POTTER_5,HARRY_POTTER_5,HARRY_POTTER_5,HARRY_POTTER_5,HARRY_POTTER_5))).isEqualTo(price(100));
    }

    private String price(double d) {
        return Price.of("EUR " + BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_CEILING)).toString();
    }

    private String priceOf(Order order) {
        return new Pricer().priceOf(order).toString();
    }

    public static Order orderOf(Book... books) {
        Order order = new Order();
        for (Book book : books) {
            order = order.add(book);
        }
        return order;
    }

}
