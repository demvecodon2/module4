package com.example.demo.reporitory;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    Book getBookByTitle(String title);

    List<Book> findByTitleIgnoreCase(String title); // Tìm kiếm sách theo tên không phân biệt chữ hoa chữ thường
}
