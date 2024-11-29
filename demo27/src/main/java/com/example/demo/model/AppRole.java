package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "App_Role", uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name")}) // Đảm bảo tên cột là "Role_Name"
@Getter
@Setter
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", nullable = false, length = 30)
    private String roleName;

}
