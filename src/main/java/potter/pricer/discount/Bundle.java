package potter.pricer.discount;

import potter.Book;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bundle {

    private final Set<Book> books;

    public Bundle() {
        this(new HashSet<>());
    }

    private Bundle(Set<Book> books) {
        this.books = books;
    }

    private static Bundle of(Set<Book> books) {
        return new Bundle(books);
    }

    public Bundle add(Book book) {
        final HashSet<Book> books = new HashSet<>(this.books);
        books.add(book);
        return of(books);
    }

    public int size() {
        return books.size();
    }

    public Set<Book> books() {
        return books;
    }

    public boolean contains2DifferentBooks() {
        return containsDifferentBooks(2);
    }

    public boolean contains3DifferentBooks() {
        return containsDifferentBooks(3);
    }

    public boolean contains4DifferentBooks() {
        return containsDifferentBooks(4);
    }

    public boolean contains5DifferentBooks() {
        return containsDifferentBooks(5);
    }

    private boolean containsDifferentBooks(int nb) {
        return books.stream().distinct().count() == nb;
    }

    public void addAll(Set<Book> books) {
        this.books.addAll(books);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bundle)) return false;
        Bundle bundle = (Bundle) o;


        return this.books.containsAll(bundle.books) && bundle.books.containsAll(this.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }

    @Override
    public String toString() {
        return "Bundle{" +
                books +
                '}';
    }
}
