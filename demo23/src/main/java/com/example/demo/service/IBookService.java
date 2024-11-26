package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {

    Book saveBook(Book book);

    List<Book> getBookByTitle(String title);

    List<Book> getAllBooks();

    void deleteBook(String title);

    Book updateBook(String title, Book book);

    Page<Book> getBooksByPage(Pageable pageable);

    Page<Book> searchBooksByTitle(String query, Pageable pageable);

    Page<Book> getBooksByTitle(String trim, Pageable pageable);

    Book getBookById(Long bookId);

    Book borrowBook(Long bookId);
}
