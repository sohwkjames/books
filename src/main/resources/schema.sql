CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birthday VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publish_year INT NOT NULL,
    price DOUBLE NOT NULL,
    genre VARCHAR(100) NOT NULL

);

CREATE TABLE IF NOT EXISTS book_authors (
    book_isbn VARCHAR(20),
    author_id BIGINT,
    PRIMARY KEY (book_isbn, author_id),
    FOREIGN KEY (book_isbn) REFERENCES books(isbn),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
