package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User_Role",uniqueConstraints
        = {@UniqueConstraint(name = "USER_ROLE_UK",columnNames={"User_Id","Role_Id"})})
@Setter
@Getter
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "Id",nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_Id",nullable = false)
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(name = "Role_Id",nullable = false)
    private AppRole appRole;

}
