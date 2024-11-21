package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "borrow_codes")
public class BorrowCode {

    @Id
    @Column(name = "code", unique = true)
    private String code;

    @ManyToOne
    private Book book;
    @Column(name = "is_returned", nullable = false)
    private boolean isReturned = false;


    public BorrowCode(String code, Book book) {
        this.code = code;
        this.book = book;
    }


    public void markAsReturned() {
        this.isReturned = true;
    }
}
