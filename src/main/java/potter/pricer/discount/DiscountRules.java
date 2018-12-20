package potter.pricer.discount;

import java.util.ArrayList;
import java.util.List;

import static potter.pricer.discount.DiscountRules.RuleBuilder.discount_of;

public class DiscountRules {

    List<Rule> ruleList = new ArrayList<>();

    public static DiscountRules classic(){
        //discount_of("5%").for_different_books_bought(2);
        return null;
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
            ruleBuilder.discount = Discount.of(Integer.valueOf(percent.substring(0, percent.length())));
            return ruleBuilder;
        }

        public Rule for_different_books_bought(int nbBooks) {
            return new Rule(discount, nbBooks);
        }
    }

}
