//package com.example.demo.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//public class ReturnCode {
//
//    @Id
//    private String code;
//
//    @Column(nullable = false)
//    private boolean isReturned;
//
//    public ReturnCode() {
//    }
//
//    public ReturnCode(String code) {
//        this.code = code;
//        this.isReturned = false;
//    }
//
//    public void markAsReturned() {
//        this.isReturned = true;
//    }
//}
