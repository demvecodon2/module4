package com.example.demo.service;

import com.example.demo.model.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private com.example.demo.repository.BookRepository bookRepository;

    // Lưu sách
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Tìm sách theo tên (không phân trang)
    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Xóa sách theo tiêu đề
    @Override
    public void deleteBook(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        bookRepository.deleteAll(books);
    }

    // Cập nhật thông tin sách
    @Override
    public Book updateBook(String title, Book book) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        if (!books.isEmpty()) {
            Book existingBook = books.get(0);
            // Cập nhật các thuộc tính của sách
            existingBook.setAuthor(book.getAuthor());
            existingBook.setTitle(book.getTitle());
            existingBook.setQuantity(book.getQuantity());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    // Lấy tất cả sách với phân trang
    @Override
    public Page<Book> getBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> searchBooksByTitle(String query, Pageable pageable) {
        return bookRepository.findByTitleContaining(query, pageable);
    }

    @Override
    public Page<Book> getBooksByTitle(String trim, Pageable pageable) {
        return bookRepository.findByTitleContaining(trim, pageable);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);

    }
}
