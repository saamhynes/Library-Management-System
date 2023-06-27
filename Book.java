public class Book implements Borrowable {

    private String title;
    private Author author;
    private String ISBN;
    private String publisher;
    private int numCopies;
    private int availableCopies;
    private Status status;
    private Patron patron;
    private Library library;

    public Book(String title, Author author, String ISBN, String publisher, int numCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.numCopies = numCopies;
        this.availableCopies = numCopies;
        this.status = Status.AVAILABLE;
    }

    @Override
    public void borrowBook(int numCopies) {
        if (numCopies <= 0 || numCopies > this.availableCopies) {
            System.out.println("Invalid number of copies to borrow.");
        } else {
            if (status == Status.AVAILABLE) {
                this.availableCopies -= numCopies;
                status = Status.CHECKED_OUT;
                System.out.println("Successfully borrowed " + numCopies + " copies of " + title);

                // Create a BorrowedBook instance and add it to the list of borrowed books
                BorrowedBook borrowedBook = new BorrowedBook(this, numCopies);

                borrowedBook.setPatron(library.getCurrentPatron());
                library.getCurrentPatron().addBorrowedBook(borrowedBook);
                patron.addBorrowedBook(borrowedBook);
            } else {
                System.out.println("Sorry, " + title + " is currently checked out.");
            }
        }
    }

    @Override
    public void returnBook(Book book, int numCopies) {
        if (status == Status.CHECKED_OUT && numCopies > 0) {
            if (numCopies <= availableCopies) {
                availableCopies += numCopies;
                if (availableCopies == numCopies) {
                    status = Status.AVAILABLE;
                }
                System.out.println("Successfully returned " + numCopies + " copies of " + title);

                // Find the borrowed book in the patron's list and remove it
                patron.removeBorrowedBook(this, numCopies);
            } else {
                System.out.println("Invalid return request.");
            }
        } else {
            System.out.println("Invalid return request.");
        }
    }

    @Override
    public boolean hasBorrowedBook(int numCopies) {
        return numCopies <= availableCopies;
    }

    public int returnCopies(int numCopies) {
        if (numCopies <= 0 || numCopies > availableCopies) {
            System.out.println("Invalid number of copies to return.");
            return 0;
        } else {
            availableCopies += numCopies;

            if (status == Status.CHECKED_OUT) {
                status = availableCopies == 0 ? Status.CHECKED_OUT : Status.AVAILABLE;
            }

            System.out.println("Successfully returned " + numCopies + " copies of " + title);

            // Remove the book from the patron's list of borrowed books
            BorrowedBook bookToRemove = null;
            for (BorrowedBook borrowedBook : patron.getBorrowedBooks()) {
                if (borrowedBook.getBook() == this) {
                    if (borrowedBook.getNumCopies() == numCopies) {
                        bookToRemove = borrowedBook;
                        break;
                    }
                }
            }
            if (bookToRemove != null) {
                patron.removeBorrowedBook(this, numCopies);
            }
        }

        return numCopies;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public String getStatusString() {
        switch (status) {
            case AVAILABLE:
                return "Available";
            case CHECKED_OUT:
                return "Checked Out";
            case OVERDUE:
                return "Overdue";
            default:
                return "Unknown";
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author.getName() + "\nISBN: " + ISBN + "\nPublisher: " + publisher
                + "\nNumber of copies: " + numCopies + "\nNumber available: " + availableCopies + "\nStatus: "
                + getStatusString();
    }

}
