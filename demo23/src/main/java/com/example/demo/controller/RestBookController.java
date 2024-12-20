package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class RestBookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        List<Book> existingBooks = bookService.getBookByTitle(book.getTitle());
        if (existingBooks != null && !existingBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book newBook = bookService.saveBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }


    @GetMapping("/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        List<Book> books = bookService.getBookByTitle(title);
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books.get(0), HttpStatus.OK);
    }

    @PutMapping("/{title}")
    public ResponseEntity<Book> updateBook(@PathVariable String title, @RequestBody Book book) {
        List<Book> existingBooks = bookService.getBookByTitle(title);
        if (existingBooks == null || existingBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setTitle(title);
        Book updatedBook = bookService.saveBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String title) {
        List<Book> existingBooks = bookService.getBookByTitle(title);
        if (existingBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/paged")
    public ResponseEntity<List<Book>> getBooksWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getBooksByPage(pageable);
        if (bookPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookPage.getContent(), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookService.searchBooksByTitle(query, pageable);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books.getContent(), HttpStatus.OK);
    }
}
