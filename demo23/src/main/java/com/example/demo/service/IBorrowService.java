package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowCode;

public interface IBorrowService {
    BorrowCode borrowBook(Book book);  // Phương thức mượn sách

    boolean returnBook(String borrowCode);
}
