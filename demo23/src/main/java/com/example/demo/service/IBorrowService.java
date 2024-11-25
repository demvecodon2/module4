package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowCode;

public interface IBorrowService {
    BorrowCode borrowBook(Book book);

    boolean returnBook(String borrowCode);
}
