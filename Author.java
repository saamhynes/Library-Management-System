import java.util.List;
import java.util.ArrayList;

public class Author {
    private String name;
    private String dateOfBirth;
    private List<Book> booksWritten;

    public Author(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.booksWritten = new ArrayList<Book>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addBook(Book book) {
        booksWritten.add(book);
    }

    public void editBook(Book book) {
        int index = booksWritten.indexOf(book);
        if (index != -1) {
            booksWritten.set(index, book);
        }
    }

    public void deleteBook(Book book) {
        booksWritten.remove(book);
    }

    // Getters and setters for properties (name, dateOfBirth, booksWritten)

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Book> getBooksWritten() {
        return booksWritten;
    }

}
