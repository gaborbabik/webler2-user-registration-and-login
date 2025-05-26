package com.csaba79coder.webler2userregistrationandlogin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateModel {

    private String username;
    private String password;
    private String repeatedPassword;
}
