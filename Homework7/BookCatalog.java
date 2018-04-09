import java.util.*;

public class BookCatalog {
    private ArrayList<BookSpecification> books;

    public BookCatalog() {
        books = new ArrayList<>();
    }

    public ArrayList<BookSpecification> getCatalog() {
        return this.books;
    }

    public BookSpecification getBookSpecification(String ISBN) {
        for (BookSpecification x : books)
            if (x.isbn.equals(ISBN))
                return x;
        return null;
    }

    public void addBook(BookSpecification book) {
        books.add(book);
    }
}