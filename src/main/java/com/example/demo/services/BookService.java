package com.example.demo.services;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn)
            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
    }

    public Book createBook(Book book, List<Long> authorIds) {
        // Fetch and validate authors
        if (bookRepository.existsById(book.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists");
        }

        List<Author> authors = authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new RuntimeException("Some provided author IDs are not valid");
        }
        if (authors.isEmpty()) {
            throw new RuntimeException("No valid authors found for provided IDs");
        }
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book updatedBook, List<Long> authorIds) {
        Book existing = getBookByIsbn(isbn);
        existing.setTitle(updatedBook.getTitle());
        existing.setYear(updatedBook.getYear());
        existing.setPrice(updatedBook.getPrice());
        existing.setGenre(updatedBook.getGenre());

        // Update authors if provided
        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> authors = authorRepository.findAllById(authorIds);
            existing.setAuthors(authors);
        }

        return bookRepository.save(existing);
    }

    public void deleteBook(String isbn) {
        bookRepository.findById(isbn)
            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
        bookRepository.deleteById(isbn);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthors_Id(authorId);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> searchBooks(String title, String authorName) {
        if (title == null && authorName == null) {
            throw new RuntimeException("At least one search parameter (title or authorName) is required");
        }
        return bookRepository.findByTitleOrAuthorName(title, authorName);
    }

}