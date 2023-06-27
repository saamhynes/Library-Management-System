import java.util.Date;

public class BorrowedBook {
    private Book book;
    private int numCopies;
    private Patron patron;
    private Date dueDate;

    public BorrowedBook(Book book, int numCopies) {
        this.book = book;
        this.numCopies = numCopies;
        this.patron = null;
        this.dueDate = null;
    }

    public Book getBook() {
        return book;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
