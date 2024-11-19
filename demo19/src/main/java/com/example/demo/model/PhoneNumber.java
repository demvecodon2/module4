package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber implements Validator {
    private String phoneNumber;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
if (phoneNumber.getPhoneNumber() == null || phoneNumber.getPhoneNumber().isEmpty()) {
    errors.rejectValue("phoneNumber", "required", "Số điện thoại không được trống.");
}if (!phoneNumber.getPhoneNumber().matches("(^$|[0-9]*$)")){
    errors.rejectValue("phoneNumber", "invalid", "Số điện thoại không h��p lệ.");
        }
    }
}
