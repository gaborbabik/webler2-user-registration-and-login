package com.csaba79coder.webler2userregistrationandlogin.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException{
    private String message;
}
