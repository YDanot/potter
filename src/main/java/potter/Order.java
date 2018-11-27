package potter;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private final List<Book> books;

    Order() {
        this(new ArrayList<>());
    }

    private Order(List<Book> books) {
        this.books = books;
    }

    public Order add(Book book) {
        final ArrayList<Book> books = Lists.newArrayList(this.books);
        books.add(book);
        return new Order(books);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(books, order.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }

    public List<Book> books() {
        return books;
    }
}
