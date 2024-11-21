package com.example.demo.reporitory;


import com.example.demo.model.BorrowCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowCodeRepository extends JpaRepository<BorrowCode, String> {
    BorrowCode findByCode(String code);
}
