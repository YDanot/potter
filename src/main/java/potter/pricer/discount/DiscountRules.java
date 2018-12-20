package potter.pricer.discount;

import org.assertj.core.util.Lists;

import java.util.List;

import static potter.pricer.discount.DiscountRules.RuleBuilder.discount_of;

public class DiscountRules {

    private static final Rule NO_DISCOUNT = discount_of("0%").for_different_books_bought(0);

    private final List<Rule> ruleList;

    private DiscountRules(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }


    public static DiscountRules classic() {
        return new DiscountRules(Lists.newArrayList(
                discount_of("5%").for_different_books_bought(2),
                discount_of("10%").for_different_books_bought(3),
                discount_of("20%").for_different_books_bought(4),
                discount_of("25%").for_different_books_bought(5)
        ));
    }

    public Discount getDiscountFor(int nbDifferentBooks) {
        return ruleList.stream().filter(r -> r.nbDifferentBook == nbDifferentBooks).findFirst().orElse(NO_DISCOUNT).discount();
    }

    private static class Rule {
        private final Discount discount;
        private final int nbDifferentBook;

        private Rule(Discount discount, int nbDifferentBook) {
            this.discount = discount;
            this.nbDifferentBook = nbDifferentBook;
        }

        public Discount discount() {
            return discount;
        }

        public int nbDifferentBook() {
            return nbDifferentBook;
        }
    }

    public static class RuleBuilder {
        private Discount discount;

        public static RuleBuilder discount_of(String percent) {
            final RuleBuilder ruleBuilder = new RuleBuilder();
            ruleBuilder.discount = Discount.of(Integer.valueOf(percent.substring(0, percent.length() - 1)));
            return ruleBuilder;
        }

        public Rule for_different_books_bought(int nbBooks) {
            return new Rule(discount, nbBooks);
        }
    }

}
