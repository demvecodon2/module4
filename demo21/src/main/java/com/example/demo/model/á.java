//package com.example.demo.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.processing.Pattern;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;
//import org.springframework.data.annotation.Id;
//
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class User {
//
//    @Getter
//    @Setter
//    @jakarta.persistence.Id
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "First name is required")
//    @Size(min = 5, max = 45, message = "First name must be between 5 and 45 characters")
//    private String firstName;
//
//    @NotBlank(message = "Last name is required")
//    @Size(min = 5, max = 45, message = "Last name must be between 5 and 45 characters")
//    private String lastName;
//
//    @Min(value = 18, message = "tuổi phaizz")
//    private int age;
//
//    @Email(message = "Email must be valid")
//    private String email;
//   @Size(min = 10 ,max = 12, message = "số điện thoại không phù hợp ")
//    private String phoneNumber;
//
//
//}
