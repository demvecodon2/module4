//package com.example.demo.controller;
//
//import com.example.demo.service.IReturnService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/return")
//public class ReturnController {
//
//    @Autowired
//    private IReturnService returnService;
//    @PostMapping
//    public ResponseEntity<String> returnBook(@RequestParam String borrowCode) {
//        try {
//            String result = returnService.returnBook(borrowCode);
//            return ResponseEntity.status(HttpStatus.OK).body(result);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: " + e.getMessage());
//        }
//    }
//}
