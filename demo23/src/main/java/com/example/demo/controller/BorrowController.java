package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowCode;
import com.example.demo.service.IBorrowService;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private IBookService bookService;

    @GetMapping("/")
    public String showBookList(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }


    @GetMapping("/borrow")
    public String borrowBook(@RequestParam String title, Model model) {
        if (title == null || title.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tiêu đề sách để mượn!");
            return "borrowResult";
        }


        Book book = (Book) bookService.getBookByTitle(title);


        if (book == null) {
            model.addAttribute("error", "Sách không tồn tại!");  // Nếu không tìm thấy sách
            return "borrowResult";
        }


        if (book.getQuantity() <= 0) {
            model.addAttribute("error", "Không còn sách để mượn!");
            return "borrowResult";
        }

        try {

            BorrowCode borrowCode = borrowService.borrowBook(book);
            model.addAttribute("borrowCode", borrowCode.getCode());
            model.addAttribute("book", book);
            model.addAttribute("message", "Mượn sách thành công! Mã mượn: " + borrowCode.getCode());  // Thêm thông báo thành công
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "borrowResult";
    }
}
