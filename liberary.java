import java.io.*;
import java.util.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
        } else {
            System.out.println("Book is not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}

class Library {
    private List<Book> books;
    private static final String FILE_NAME = "library_data.ser";

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    public void removeBook(String title) {
        Book bookToRemove = findBookByTitle(title);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void borrowBook(String title) {
        Book bookToBorrow = findBookByTitle(title);
        if (bookToBorrow != null) {
            bookToBorrow.borrowBook();
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(String title) {
        Book bookToReturn = findBookByTitle(title);
        if (bookToReturn != null) {
            bookToReturn.returnBook();
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void saveLibraryData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            System.out.println("Library data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLibraryData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) ois.readObject();
            System.out.println("Library data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library data: " + e.getMessage());
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Save Library Data");
            System.out.println("7. Load Library Data");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String removeTitle = scanner.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 3:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.saveLibraryData();
                    break;
                case 7:
                    library.loadLibraryData();
                    break;
                case 8:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
