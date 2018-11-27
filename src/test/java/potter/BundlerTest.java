package potter;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import potter.pricer.discount.Bundle;
import potter.pricer.discount.Bundler;

import java.util.List;

import static potter.Book.*;
import static potter.PotterTest.orderOf;

public class BundlerTest {

    @Test
    public void one_book_order_should_be_bundled_1_bundle_of_1_book() {
        Assertions.assertThat(
                bundles(orderOf(HARRY_POTTER_1)))
                .containsExactlyInAnyOrder(bundleOf(HARRY_POTTER_1));
    }

    @Test
    public void book_order_should_be_bundled_1_bundle_of_1_book() {
        Assertions.assertThat(
                bundles(orderOf(
                        HARRY_POTTER_1, HARRY_POTTER_1,
                        HARRY_POTTER_2,
                        HARRY_POTTER_3, HARRY_POTTER_3,
                        HARRY_POTTER_4)))
                .containsExactlyInAnyOrder(
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_3));
    }


    @Test
    public void order_12345_123__bundled__1234_1234() {

        Assertions.assertThat(bundles(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4,
                HARRY_POTTER_5)))
                .containsExactlyInAnyOrder(
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_5));
    }

    @Test
    public void order_12345_12345_12345_12345_124__bundled__12345_12345_12345_1234_1245() {
        Assertions.assertThat(bundles(orderOf(
                HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1, HARRY_POTTER_1,
                HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2, HARRY_POTTER_2,
                HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3, HARRY_POTTER_3,
                HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4, HARRY_POTTER_4,
                HARRY_POTTER_5, HARRY_POTTER_5, HARRY_POTTER_5, HARRY_POTTER_5)))
                .containsExactlyInAnyOrder(
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4, HARRY_POTTER_5),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4, HARRY_POTTER_5),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3, HARRY_POTTER_4, HARRY_POTTER_5),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_4, HARRY_POTTER_3),
                        bundleOf(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_4, HARRY_POTTER_5));
    }

    private static Bundle bundleOf(Book... books) {
        Bundle bundle = new Bundle();
        for (Book book : books) {
            bundle = bundle.add(book);
        }
        return bundle;
    }

    private List<Bundle> bundles(Order order) {
        return new Bundler().split(order);
    }
}