public class ProductSpecification {
    public String isbn;
    public double price;
    public String title;
    public BookType type;

    public ProductSpecification(String isbn, double price, String title, BookType type) {
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }
}