package com.example.demo.repository;

import com.example.demo.model.UserRole;
import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByAppUser(AppUser appUser);  // Sửa tên phương thức thành 'findByAppUser'
}
