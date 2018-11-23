package potter;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

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

    public int size() {
        return books.size();
    }
}
