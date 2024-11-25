package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.reporitory.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteBook(String title) {
        List<Book> books = bookRepository.findByTitleIgnoreCase(title);

        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
        } else {
            throw new RuntimeException("No books found with title: " + title);
        }
    }

    @Override
    public Book updateBook(String title, Book book) {

        List<Book> books = bookRepository.findByTitleIgnoreCase(title);
        if (!books.isEmpty()) {
            Book existingBook = books.get(0);
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());


            return bookRepository.save(existingBook);
        } else {
            throw new RuntimeException("No books found with title: " + title);
        }
    }
}
