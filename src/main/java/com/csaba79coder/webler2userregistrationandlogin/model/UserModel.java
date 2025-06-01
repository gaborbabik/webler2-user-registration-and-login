package com.csaba79coder.webler2userregistrationandlogin.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Long id;
    private String email;
    private String username;
}
