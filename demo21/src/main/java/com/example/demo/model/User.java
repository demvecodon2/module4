package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "không thể để  tên trống")
    @Size(min = 5, max = 45, message = "First name phải từ 5 đến 45 kí tự")
    private String firstName;

    @NotBlank(message = "Last name có")
    @Size(min = 5, max = 45, message = "Last name phải từ 5 đến 45 kí tự")
    private String lastName;

    @Min(value = 18, message = "tuổi phải trên 18 tuổi")
    private int age;

    @Email(message = "Email must be valid")
    private String email;
    @Size(min = 10 ,max = 12, message = "số điện thoại không phù hợp ")
    private String phoneNumber;

}
