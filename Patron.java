// import java.util.ArrayList;
// import java.util.List;

// public class Patron {
//     private String name;
//     private String address;
//     private String phoneNumber;
//     private List<Book> borrowedBooks;
//     private List<Integer> borrowedBooksCopies;

//     public Patron(String name, String address, String phoneNumber) {
//         this.name = name;
//         this.address = address;
//         this.phoneNumber = phoneNumber;
//         this.borrowedBooks = new ArrayList<>();
//         this.borrowedBooksCopies = new ArrayList<>();
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public void setPhoneNumber(String phoneNumber) {
//         this.phoneNumber = phoneNumber;
//     }

//     public void borrowBook(Book book, int numCopies) {
//         borrowedBooks.add(book);
//         borrowedBooksCopies.add(numCopies);
//     }

//     public boolean hasBorrowedBook(Book book, int numCopies) {
//         for (BorrowedBook borrowedBook : borrowedBooks) {
//             if (borrowedBook.getBook().equals(book) && borrowedBook.getNumCopies() >= numCopies) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     public int returnBook(Book book, int numCopies) {
//         int index = borrowedBooks.indexOf(book);

//         if (index != -1) {
//             int currentCopies = borrowedBooksCopies.get(index);

//             if (numCopies <= currentCopies) {
//                 currentCopies -= numCopies;

//                 if (currentCopies == 0) {
//                     borrowedBooksCopies.remove(index);
//                     borrowedBooks.remove(index);
//                 } else {
//                     borrowedBooksCopies.set(index, currentCopies);
//                 }

//                 return numCopies;
//             }
//         }
//         return 0; // Return 0 if the book was not found or not enough copies were borrowed
//     }

//     public void addBorrowedBook(Book book, int numCopies) {
//         borrowedBooks.add(book);
//         borrowedBooksCopies.add(numCopies);
//     }

//     public void editBorrowedBook(Book book, int numCopies) {
//         int index = borrowedBooks.indexOf(book);

//         if (index != -1) {
//             borrowedBooksCopies.set(index, numCopies);
//         }
//     }

//     public void deleteBorrowedBook(Book book) {
//         int index = borrowedBooks.indexOf(book);

//         if (index != -1) {
//             borrowedBooksCopies.remove(index);
//             borrowedBooks.remove(index);
//         }
//     }

//     public String getName() {
//         return name;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public String getPhoneNumber() {
//         return phoneNumber;
//     }

//     public List<Book> getBorrowedBooks() {
//         return borrowedBooks;
//     }
// }

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String address;
    private String phoneNumber;
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void borrowBook(Book book, int numCopies) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                borrowedBook.setNumCopies(borrowedBook.getNumCopies() + numCopies);
                return;
            }
        }
        BorrowedBook borrowedBook = new BorrowedBook(book, numCopies);
        borrowedBooks.add(borrowedBook);
    }

    public boolean hasBorrowedBook(Book book, int numCopies) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book) && borrowedBook.getNumCopies() >= numCopies) {
                return true;
            }
        }
        return false;
    }

    public int returnBook(Book book, int numCopies) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                int currentCopies = borrowedBook.getNumCopies();
                if (numCopies <= currentCopies) {
                    currentCopies -= numCopies;
                    if (currentCopies == 0) {
                        borrowedBooks.remove(borrowedBook);
                    } else {
                        borrowedBook.setNumCopies(currentCopies);
                    }
                    return numCopies; // Return the actual number of copies returned
                }
            }
        }
        return 0;
    }

    public void addBorrowedBook(BorrowedBook borrowedBook) {
        for (BorrowedBook existingBorrowedBook : borrowedBooks) {
            if (existingBorrowedBook.getBook().equals(borrowedBook.getBook())) {
                existingBorrowedBook.setNumCopies(existingBorrowedBook.getNumCopies() + borrowedBook.getNumCopies());
                return;
            }
        }
        borrowedBooks.add(borrowedBook);
    }

    public void editBorrowedBook(Book book, int numCopies) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                borrowedBook.setNumCopies(numCopies);
            }
        }
    }

    public BorrowedBook findBorrowedBook(Book book) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                return borrowedBook;
            }
        }
        return null;
    }

    public void listBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");

        for (BorrowedBook borrowedBook : borrowedBooks) {
            System.out.println("Title: " + borrowedBook.getBook().getTitle());
            System.out.println("Number of Copies Borrowed: " + borrowedBook.getNumCopies());
            System.out.println(); // Add a separator for readability
        }
    }

    public void removeBorrowedBook(Book book, int numCopies) {
        borrowedBooks.removeIf(
                borrowedBook -> borrowedBook.getBook().equals(book) && borrowedBook.getNumCopies() == numCopies);
    }

    public void deleteBorrowedBook(Book book) {
        borrowedBooks.removeIf(borrowedBook -> borrowedBook.getBook().equals(book));
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }
}
