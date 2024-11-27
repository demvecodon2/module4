package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowCode;
import com.example.demo.service.BorrowService;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/search/ajax")
    public ResponseEntity<Map<String, Object>> searchBooksAjax(@RequestParam(required = false) String title,
                                                               @RequestParam int page,
                                                               @RequestParam int size) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage;

        if (title == null || title.trim().isEmpty()) {
            bookPage = bookService.getBooksByPage(pageable);
        } else {
            bookPage = bookService.getBooksByTitle(title.trim(), pageable);
        }

        if (bookPage.hasContent()) {
            response.put("books", bookPage.getContent());
            response.put("totalPages", bookPage.getTotalPages());
            response.put("currentPage", bookPage.getNumber());
        } else {
            response.put("error", "Không tìm thấy sách!");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/load-more-posts")
    public ResponseEntity<Map<String, Object>> loadMorePosts(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getBooksByPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("posts", bookPage.getContent());
        response.put("totalPages", bookPage.getTotalPages());
        response.put("currentPage", bookPage.getNumber());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/books/add")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam int quantity, Model model) {
        try {
            // Giả sử bạn có một service để thêm sách
            bookService.addBook(new Book(title, author, quantity, model));
            model.addAttribute("message", "Thêm sách thành công!");
        } catch (Exception e) {
            model.addAttribute("error", "Có lỗi xảy ra khi thêm sách.");
        }
        return "redirect:/books";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        if (book != null && book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookService.saveBook(book);
            model.addAttribute("borrowCode", "Mã mượn: " + bookId);  // Giả sử mã mượn là bookId
            model.addAttribute("message", "Mượn sách thành công!");
        } else {
            model.addAttribute("error", "Không có sách để mượn.");
        }
        return "bookDetail";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, Model model) {
        if (borrowCode == null || borrowCode.isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập mã mượn!");
            return "redirect:/books";
        }
        try {
            boolean isReturned = borrowService.returnBook(borrowCode);
            if (isReturned) {
                model.addAttribute("message", "Sách đã được trả thành công.");
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/books/{title}")
    public String getBookDetail(@PathVariable Long title, Model model) {
        Book book = bookService.getBookById(title); // Lấy thông tin sách từ database

        if (book != null) {
            model.addAttribute("book", book);  // Truyền book vào model nếu sách tồn tại
        } else {
            model.addAttribute("error", "Sách không tồn tại.");  // Truyền lỗi nếu không tìm thấy sách
        }

        return "bookDetail";
    }
}
