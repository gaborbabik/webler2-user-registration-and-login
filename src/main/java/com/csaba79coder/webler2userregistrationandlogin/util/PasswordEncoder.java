package com.csaba79coder.webler2userregistrationandlogin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(plainPassword, hashedPassword);
    }

    private PasswordEncoder() {

    }
}
