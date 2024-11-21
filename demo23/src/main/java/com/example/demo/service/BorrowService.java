package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowCode;
import com.example.demo.reporitory.BookRepository;
import com.example.demo.reporitory.BorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class BorrowService implements IBorrowService {

    @Autowired
    private BorrowCodeRepository borrowCodeRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public BorrowCode borrowBook(Book book) {
        if (book.getQuantity() > 0) {
            String borrowCode = generateBorrowCode();
            BorrowCode code = new BorrowCode(borrowCode, book);
            borrowCodeRepository.save(code);
            book.decrementQuantity();
            bookRepository.save(book);

            return code;
        } else {
            throw new RuntimeException("Không còn sách để mượn.");
        }
    }
    private String generateBorrowCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public boolean returnBook(String borrowCode) {
        Optional<BorrowCode> borrowCodeOptional = borrowCodeRepository.findById(borrowCode);
        if (!borrowCodeOptional.isPresent()) {
            throw new RuntimeException("Mã mượn không hợp lệ.");
        }
        BorrowCode code = borrowCodeOptional.get();
        if (code.isReturned()) {
            throw new RuntimeException("Sách đã được trả trước đó.");
        }
        code.markAsReturned();
        borrowCodeRepository.save(code);
        Book book = code.getBook();
        book.incrementQuantity();
        bookRepository.save(book);
        return true;
    }
}
