import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        List<BorrowedBook> borrowedBooks = new ArrayList<>();

        // Create instances of the library, authors, books and patrons
        Author author1 = new Author("Colleen Hoover", "1985-09-30");
        Author author2 = new Author("J.K. Rowling", "1965-07-31");
        Author author3 = new Author("Stephen King", "1947-09-21");
        Author author4 = new Author("Agatha Christie", "1890-09-15");

        Book book1 = new Book("Ugly Love", author1, "978-0743273565", "Atria Books", 10);
        book1.setLibrary(library);
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", author2, "931-234567890", "Scribner", 1);
        Book book3 = new Book("The Shining", author3, "978-0385121675", "Doubleday", 1);
        Book book4 = new Book("Murder on the Orient Express", author4, "978-0062693662", "William Collins", 1);

        Patron patron1 = new Patron("Samantha Hynes", "34 Ossok", "7097466545");
        Patron patron2 = new Patron("Leia Hynes", "34 Ossok", "7097466545");

        // Add authors, books and patrons to the library
        library.getAuthors().add(author1);
        library.getAuthors().add(author2);
        library.getAuthors().add(author3);
        library.getAuthors().add(author4);

        library.getBooks().add(book1);
        library.getBooks().add(book2);
        library.getBooks().add(book3);
        library.getBooks().add(book4);

        library.getPatrons().add(patron1);
        library.getPatrons().add(patron2);

        Patron patron = null;

        while (true) {
            System.out.println();
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Search books by title");
            System.out.println("2. Search books by author");
            System.out.println("3. Search books by ISBN");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Add/Edit/Delete Authors");
            System.out.println("7. Patron Management");
            System.out.println("8. Exit");

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    library.searchBookByTitle(title);
                    break;

                case 2:
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    library.searchBooksByAuthor(authorName);
                    break;

                case 3:
                    System.out.print("Enter ISBN: ");
                    String ISBN = scanner.nextLine();
                    library.searchBooksByISBN(ISBN);
                    break;

                case 4:
                    System.out.print("Enter your name: ");
                    String patronName = scanner.nextLine();
                    Patron currentPatron = library.findPatronByName(patronName);
                    if (currentPatron != null) {
                        System.out.print("Enter the book title you want to borrow: ");
                        String bookTitle = scanner.nextLine();

                        library.searchBookByTitle(bookTitle);

                        if (library.getBooks().isEmpty()) {
                            System.out.println("Book not found.");
                        } else {
                            System.out.print("Enter the number of copies you want to borrow: ");
                            int numCopiesToBorrow = scanner.nextInt();
                            scanner.nextLine();

                            Book book = library.getBooks().get(0);
                            library.borrowBook(currentPatron, book, numCopiesToBorrow);
                        }

                    } else {
                        System.out.println("Patron not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter your name: ");
                    String returnPatronName = scanner.nextLine();

                    // find patron by name
                    Patron returnPatron = null;
                    for (Patron p : library.getPatrons()) {
                        if (p.getName().equalsIgnoreCase(returnPatronName)) {
                            returnPatron = p;
                            break;
                        }
                    }

                    if (returnPatron != null) {
                        System.out.println("Enter the book title you want to return: ");
                        String bookTitle = scanner.nextLine();

                        // find book by title
                        Book bookToReturn = null;
                        for (Book book : library.getBooks()) {
                            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                                bookToReturn = book;
                                break;
                            }
                        }

                        if (bookToReturn != null) {
                            System.out.print("Enter the number of copies you want to return:");
                            int numCopiesToReturn = scanner.nextInt();
                            scanner.nextLine();

                            int returnedCopies = bookToReturn.returnCopies(numCopiesToReturn);

                            if (returnedCopies == numCopiesToReturn) {
                                System.out.println(
                                        "Successfully returned " + numCopiesToReturn + " copies of " + bookTitle);
                            } else {
                                System.out.println("Invalid return request.");
                            }
                        } else {
                            System.out.println("Book not found.");
                        }
                    } else {
                        System.out.println("Patron not found.");
                    }
                    break;

                case 6:
                    System.out.println("Author Management");
                    System.out.println("1. Add an author");
                    System.out.println("2. Edit an author");
                    System.out.println("3. Delete an author");
                    System.out.println("Enter your choice:");
                    int authorChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (authorChoice) {
                        case 1:
                            System.out.print("Enter the author's name: ");
                            String newAuthorName = scanner.nextLine();
                            System.out.print("Enter the author's date of birth: ");
                            String newAuthorDOB = scanner.nextLine();

                            Author newAuthor = new Author(newAuthorName, newAuthorDOB);
                            library.getAuthors().add(newAuthor);
                            System.out.println("Author added successfully.");
                            break;

                        case 2:
                            System.out.print("Enter the name of the author you want to edit: ");
                            String authorNameToEdit = scanner.nextLine();

                            // find author by name
                            Author authorToEdit = null;
                            for (Author a : library.getAuthors()) {
                                if (a.getName().equalsIgnoreCase(authorNameToEdit)) {
                                    authorToEdit = a;
                                    break;
                                }
                            }

                            if (authorToEdit != null) {
                                System.out.print("Enter the new name for the author: ");
                                String editedAuthorName = scanner.nextLine();
                                System.out.print("Enter the new date of birth for the author: ");
                                String editedAuthorDOB = scanner.nextLine();

                                library.editAuthor(authorToEdit, editedAuthorName, editedAuthorDOB);
                            } else {
                                System.out.println("Author not found.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter the name of the author you want to delete: ");
                            String authorNameToDelete = scanner.nextLine();

                            // fine the author by name
                            Author authorToDelete = null;
                            for (Author a : library.getAuthors()) {
                                if (a.getName().equalsIgnoreCase(authorNameToDelete)) {
                                    authorToDelete = a;
                                    break;
                                }
                            }

                            if (authorToDelete != null) {
                                library.deleteAuthor(authorToDelete);
                                System.out.println("Author deleted successfully.");
                            } else {
                                System.out.println("Author not found.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 7:
                    int patronMenuChoice = -1;
                    while (patronMenuChoice != 0) {
                        System.out.println();
                        System.out.println("Patron Management Menu");
                        System.out.println("1. Add a Patron");
                        System.out.println("2. Edit a Patron");
                        System.out.println("3. Delete a Patron");
                        System.out.println("4. List Books Borrowed by Patron");
                        System.out.println("5. Back to Main Menu");
                        System.out.println("Enter your choice:");

                        patronMenuChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (patronMenuChoice) {
                            case 1:
                                // Add a patron
                                System.out.print("Enter the patron's name:");
                                String newPatronName = scanner.nextLine();

                                System.out.print("Enter the patron's address:");
                                String newPatronAddress = scanner.nextLine();

                                System.out.println("Enter the patron's phone number:");
                                String newPatronPhoneNumber = scanner.nextLine();

                                Patron newPatron = new Patron(newPatronName, newPatronAddress, newPatronPhoneNumber);
                                library.addPatron(newPatron);
                                System.out.println("Patron added successfully.");
                                break;
                            case 2:
                                // Edit a patron
                                System.out.println("Enter the name of the patron you want to edit:");
                                String patronNameToEdit = scanner.nextLine();
                                Patron patronToEdit = library.findPatronByName(patronNameToEdit);
                                if (patronToEdit != null) {
                                    System.out.print("Enter the new name for the patron: ");
                                    String editedPatronName = scanner.nextLine();
                                    System.out.print("Enter the new address for the patron: ");
                                    String editedPatronAddress = scanner.nextLine();
                                    System.out.println("Enter the new phone number for the patron:");
                                    String editedPatronPhoneNumber = scanner.nextLine();
                                    library.editPatron(patronToEdit, editedPatronName, editedPatronAddress,
                                            editedPatronPhoneNumber);
                                } else {
                                    System.out.println("Patron not found.");
                                }
                                break;
                            case 3:
                                // Delete a patron
                                System.out.println("Enter the name of the patron you want to delete:");
                                String patronNameToDelete = scanner.nextLine();
                                Patron patronToDelete = library.findPatronByName(patronNameToDelete);
                                if (patronToDelete != null) {
                                    library.deletePatron(patronToDelete);
                                    System.out.println("Patron deleted successfully.");
                                } else {
                                    System.out.println("Patron not found.");
                                }
                                break;

                            case 4:
                                // List Books Borrowed by Patron
                                System.out.println("Enter the name of the patron:");
                                String patronNameToSearch = scanner.nextLine();
                                Patron selectedPatron = library.findPatronByName(patronNameToSearch);
                                if (selectedPatron != null) {
                                    selectedPatron.listBorrowedBooks();
                                } else {
                                    System.out.println("Patron not found.");
                                }
                                break;

                            case 5:
                                // Back to main menu
                                patronMenuChoice = 0;
                                break;
                        }
                    }
                    break;

                case 8:
                    System.out.println("Exiting the library management system...");
                    System.out.println("Thank you for using the library management system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }
    }
}