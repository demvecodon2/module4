    package com.example.demo.controller;

    import com.example.demo.model.Book;
    import com.example.demo.model.BorrowCode;
    import com.example.demo.service.IBookService;
    import com.example.demo.service.BorrowService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.List;

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

        @GetMapping("/book")
        public String getBookByTitle(@RequestParam String title, Model model) {
            if (title == null || title.trim().isEmpty()) {
                model.addAttribute("error", "Please enter a book title.");
                return "bookDetail";
            }

            List<Book> books = bookService.getBookByTitle(title.trim());

            if (books != null && !books.isEmpty()) {
                Book book = books.get(0);
                model.addAttribute("book", book);
            } else {
                model.addAttribute("error", "No books found with the title \"" + title + "\".");
            }

            return "bookDetail";
        }

        @GetMapping("/addBook")
        public String showAddBookForm() {
            return "addBook";
        }

        @PostMapping("/addBook")
        public String addBook(Book book, Model model) {
            if (book != null) {
                Book savedBook = bookService.saveBook(book);
                model.addAttribute("book", savedBook);
                model.addAttribute("message", "Sách đã được thêm thành công!");
            } else {
                model.addAttribute("error", "Có lỗi xảy ra khi thêm sách.");
            }
            return "bookDetail";
        }

        @GetMapping("/search")
        public String searchBooks(@RequestParam(required = false) String title, Model model) {
            if (title == null || title.isEmpty()) {
                model.addAttribute("error", "Vui lòng nhập tên sách!");
                return "books";
            }

            Book book = (Book) bookService.getBookByTitle(title);

            if (book != null) {
                model.addAttribute("book", book);
            } else {
                model.addAttribute("error", "Không tìm thấy sách!");
            }

            return "books";
        }




        @PostMapping("/borrow")
        public String borrowBook(@RequestParam String title, Model model) {
            if (title == null || title.isEmpty()) {
                model.addAttribute("error", "Vui lòng nhập tên sách!");
                return "bookDetail";
            }
            List<Book> books = bookService.getBookByTitle(title);
            if (books != null && !books.isEmpty()) {
                Book book = books.get(0);
                if (book.getQuantity() > 0) {
                    try {
                        BorrowCode borrowCode = borrowService.borrowBook(book);
                        model.addAttribute("borrowCode", borrowCode.getCode());
                        model.addAttribute("book", book);
                        model.addAttribute("message", "Mượn sách thành công! Mã mượn: " + borrowCode.getCode());
                    } catch (RuntimeException e) {
                        model.addAttribute("error", e.getMessage());
                    }
                } else {
                    model.addAttribute("error", "Không còn sách để mượn.");
                }
            } else {
                model.addAttribute("error", "Không tìm thấy sách với tên \"" + title + "\".");
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
    }
