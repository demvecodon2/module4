package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "App_User", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_ROLE_NAME_UK", columnNames = "Role_Name")})
public class AppUser {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Getter
    @Column(name = "User_Name", nullable = false, length = 50)
    private String userName;

    @Getter
    @Column(name = "Encrypted_Password", nullable = false, length = 100)
    private String encryptedPassword;

    @Getter
    @Setter
    @Column(name = "Enabled", nullable = false)
    private Boolean enabled;

    @Getter
    @Setter
    @Column(name = "Role_Name", nullable = false, length = 50)
    private String roleName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "appUser")
    private Set<Blog> blogSet;
}
