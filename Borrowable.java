public interface Borrowable {
    void borrowBook(int numCopies);

    void returnBook(Book book, int numCopies);

    boolean hasBorrowedBook(int numCopies);

    int returnCopies(int numCopies);
}