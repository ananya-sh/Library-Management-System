-- Creating a Users table
CREATE TABLE Users (
    user_id INT PRIMARY KEY,
    user_name VARCHAR(100),
    email VARCHAR(100),
    registration_date DATE
);

-- Creating a Books table
CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    published_year INT,
    availability BOOLEAN DEFAULT TRUE
);

-- Creating a Borrowed_Books table to track borrowed books
CREATE TABLE Borrowed_Books (
    transaction_id INT PRIMARY KEY,
    user_id INT,
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

-- Inserting sample data into Users
INSERT INTO Users (user_id, user_name, email, registration_date)
VALUES 
(1, 'Alice Johnson', 'alice.j@example.com', '2022-01-10'),
(2, 'Bob Smith', 'bob.s@example.com', '2022-02-15');

-- Inserting sample data into Books
INSERT INTO Books (book_id, title, author, published_year)
VALUES 
(101, 'The Great Gatsby', 'F. Scott Fitzgerald', 1925),
(102, '1984', 'George Orwell', 1949),
(103, 'To Kill a Mockingbird', 'Harper Lee', 1960);

-- Inserting sample data into Borrowed_Books
INSERT INTO Borrowed_Books (transaction_id, user_id, book_id, borrow_date, return_date)
VALUES 
(1, 1, 101, '2023-09-01', NULL),
(2, 2, 102, '2023-08-20', '2023-09-05');


-- Updating book availability when borrowed
UPDATE Books
SET availability = FALSE
WHERE book_id = 101;

-- Marking a book as returned
UPDATE Borrowed_Books
SET return_date = '2023-09-07'
WHERE transaction_id = 1;


-- Retrieve all available books
SELECT * FROM Books
WHERE availability = TRUE;

-- Retrieve all books borrowed by a specific user
SELECT Books.title, Borrowed_Books.borrow_date, Borrowed_Books.return_date
FROM Borrowed_Books
JOIN Books ON Borrowed_Books.book_id = Books.book_id
WHERE Borrowed_Books.user_id = 1;

-- Retrieve all transactions
SELECT * FROM Borrowed_Books;


