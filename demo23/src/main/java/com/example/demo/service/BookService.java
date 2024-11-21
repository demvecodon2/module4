package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.reporitory.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
