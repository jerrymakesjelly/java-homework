public class BookSpecification {
    public String isbn;
    public double price;
    public String title;
    public int type;

    public BookSpecification(String isbn, double price, String title, int type) {
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }
}