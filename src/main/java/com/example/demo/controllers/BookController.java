package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(
            @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bookService.createBook(request.book(), request.authorIds()));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(
            @PathVariable String isbn,
            @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(isbn, request.book(), request.authorIds()));
    }
    
 // GET /books/search?title=Some Title&authorName=Some Author
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String authorName) {
        return ResponseEntity.ok(bookService.searchBooks(title, authorName));
    }
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint — no token needed!";
    }
    
    // Must be an admin
    @DeleteMapping("{isbn}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBookByIsbn(@PathVariable String isbn) {
    	bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminEndpoint() {
//        return "You are an admin!";
//    }

    public record BookRequest(Book book, List<Long> authorIds) {}

}
