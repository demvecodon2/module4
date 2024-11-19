package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Setter
@Getter
@NoArgsConstructor
public class Users implements Validator {
    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users users = (Users) target;
        if (users.getName() == null || users.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "Name cannot be empty");
        } else if (users.getName().length() < 3) {
            errors.rejectValue("name", "name.length", "Name must have at least 3 characters");
        }
        if (users.getAge() < 18 || users.getAge() > 100) {
            errors.rejectValue("age", "age.range", "Age must be between 18 and 100");
        }
        if (users.getEmail() == null || !users.getEmail().matches("^[\\w-]+(?:\\.[\\w-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            errors.rejectValue("email", "email.invalid", "Email is not valid");
        }
        if (users.getPhoneNumber() == null || !users.getPhoneNumber().matches("^\\d{10}$")) {
            errors.rejectValue("phoneNumber", "phoneNumber.invalid", "Phone number must be 10 digits");
        }
    }
}
