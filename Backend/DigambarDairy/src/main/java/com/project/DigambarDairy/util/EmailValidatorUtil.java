package com.project.DigambarDairy.util;

import java.util.regex.Pattern;

public class EmailValidatorUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValid(String email) {
        return email != null && pattern.matcher(email).matches();
    }
}
