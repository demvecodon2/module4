package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ui.Model;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int quantity;
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<BorrowCode> borrowCodes;

    public Book(String title, String author, int quantity, Model model) {
    }

    public void decrementQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    public void incrementQuantity() {
        this.quantity++;
    }

}
