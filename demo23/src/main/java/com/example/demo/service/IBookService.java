package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    Book saveBook(Book book);

    List<Book> getBookByTitle(String title);

    List<Book> getAllBooks();

    void deleteBook(String title);

    Book updateBook(String title, Book book);



}
