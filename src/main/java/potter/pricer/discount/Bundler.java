package potter.pricer.discount;

import potter.Book;
import potter.Order;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Bundler {

    public List<Bundle> split(Order order) {
        return optimize(distinctDistributionOf(order.books()));
    }

    private List<Bundle> distinctDistributionOf(List<Book> books) {
        final Map<Book, Long> bookByOccurence =
                books.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final List<Bundle> distribution = new ArrayList<>();

        while (remainsBookNotDistributed(bookByOccurence)) {
            Bundle bundle = new Bundle();
            for (Map.Entry<Book, Long> entry : bookByOccurence.entrySet()) {
                if (entry.getValue() > 0) {
                    bundle = bundle.add(entry.getKey());
                    bookByOccurence.put(entry.getKey(), entry.getValue() - 1);
                }
            }
            distribution.add(bundle);
        }
        return distribution;
    }

    private boolean remainsBookNotDistributed(Map<Book, Long> bookOccurences) {
        return !bookOccurences.values().stream().allMatch(nbOccurence -> nbOccurence == 0);
    }

    private List<Bundle> optimize(List<Bundle> bundles) {
        List<Bundle> optimized = new ArrayList<>(bundles);
        Map<Integer, List<Bundle>> bundleBySize = bundleBySize(optimized);
        Set<Integer> sizes = bundleBySize.keySet();

        while (sizes.contains(5) && sizes.contains(3)) {

            final BundleCouple bundleCouple = new BundleCouple(bundleBySize.get(5).get(0), bundleBySize.get(3).get(0));
            final BundleCouple balanced = bundleCouple.balance();

            optimized.add(balanced.bundle1());
            optimized.add(balanced.bundle2());
            optimized.remove(bundleCouple.bundle1());
            optimized.remove(bundleCouple.bundle2());
            bundleBySize = bundleBySize(optimized);
            sizes = bundleBySize.keySet();
        }
        return optimized;
    }

    private Map<Integer, List<Bundle>> bundleBySize(List<Bundle> bundles) {
        return bundles.stream().collect(Collectors.groupingBy(Bundle::size));
    }

    private class BundleCouple {
        final Bundle bigBundle;
        final Bundle littleBundle;

        public BundleCouple(Bundle b1, Bundle b2) {
            if (b1.size() > b2.size()) {
                this.bigBundle = b1;
                this.littleBundle = b2;
            } else {
                this.bigBundle = b2;
                this.littleBundle = b1;
            }
        }

        public Bundle bundle1() {
            return bigBundle;
        }

        public Bundle bundle2() {
            return littleBundle;
        }

        public BundleCouple balance() {

            final Bundle b1 = new Bundle();
            final Bundle b2 = new Bundle();

            b1.addAll(littleBundle.books());

            for (Book book : bigBundle.books()) {
                if (!b1.books().contains(book)) {
                    b1.books().add(book);
                    b2.addAll(bigBundle.books());
                    b2.books().remove(book);
                    break;
                }
            }
            return new BundleCouple(b1,b2);
        }
    }
}
