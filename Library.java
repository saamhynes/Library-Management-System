
import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books;
    private List<Author> authors;
    private List<Patron> patrons;
    private Patron currentPatron;

    public Patron getCurrentPatron() {
        return currentPatron;
    }

    public Library() {
        books = new ArrayList<Book>();
        authors = new ArrayList<Author>();
        patrons = new ArrayList<Patron>();
        currentPatron = null;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public void setCurrentPatron(Patron patron) {
        currentPatron = patron;
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
        setCurrentPatron(patron);
    }

    public void editPatron(Patron patron, String newName, String newAddress, String newPhoneNumber) {
        if (patrons.contains(patron)) {
            patron.setName(newName);
            patron.setAddress(newAddress);
            patron.setPhoneNumber(newPhoneNumber);
            System.out.println("Patron details updated successfully.");
        } else {
            System.out.println("Patron not found.");
        }
    }

    public void deletePatron(Patron patron) {
        patrons.remove(patron);
    }

    public void searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
            }
        }
    }

    public Patron findPatronByName(String name) {
        for (Patron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                return patron;
            }
        }
        return null; // Patron not found
    }

    public void searchBooksByAuthor(String authorName) {
        for (Book book : books) {
            if (book.getAuthor().getName().equals(authorName)) {
                System.out.println(book);
            }
        }
    }

    public void searchBooksByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                System.out.println(book);
            }
        }
    }

    public void editAuthor(Author author, String newName, String newDateOfBirth) {
        if (authors.contains(author)) {
            author.setName(newName);
            author.setDateOfBirth(newDateOfBirth);
            System.out.println("Author details updated successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    public void deleteAuthor(Author author) {
        if (authors.contains(author)) {
            authors.remove(author);
            System.out.println("Author removed from the library.");
        } else {
            System.out.println("Author not found.");
        }
    }

    public void borrowBook(Patron patron, Book book, int numCopiesToBorrow) {
        if (patron == null) {
            System.out.println("Cannot borrow a book without a valid patron.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.getStatus() == Book.Status.CHECKED_OUT) {
            System.out.println("Book is already checked out.");
            return;
        }

        if (book.getNumAvailableCopies() < numCopiesToBorrow) {
            System.out.println("Not enough available copies to borrow.");
            return;
        }

        if (patron.hasBorrowedBook(book, numCopiesToBorrow)) {
            System.out.println("You have already borrowed this book.");
            return;
        }

        Book borrowedBook = book.borrowBook(numCopiesToBorrow, patron);
        if (borrowedBook != null) {
            System.out.println("Successfully borrowed " + numCopiesToBorrow + " copies of " + borrowedBook.getTitle());
            borrowedBook.setStatus(Book.Status.CHECKED_OUT);
        }
    }

    public void returnBook(Patron patron, Book book, int numCopiesToReturn) {
        if (numCopiesToReturn <= 0) {
            System.out.println("Invalid number of copies to return.");
            return;
        }

        BorrowedBook borrowedBookToReturn = patron.findBorrowedBook(book);
        if (borrowedBookToReturn != null) {
            int copiesReturned = book.returnCopies(numCopiesToReturn); // Call returnCopies method
            if (copiesReturned > 0) {
                System.out.println("Successfully returned " + copiesReturned + " copies of " + book.getTitle());

                if (book.getAvailableCopies() == book.getNumCopies()) {
                    book.setStatus(Status.AVAILABLE);
                }

                patron.returnBook(book, copiesReturned); // Update the patron's list of borrowed books
            } else {
                System.out.println("Invalid return request.");
            }
        } else {
            System.out.println("The patron has not borrowed this book.");
        }
    }

}
