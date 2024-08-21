package za.ac.cput.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class Helper {
    // Method to check if a string is null or empty
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
    public static boolean isNull(int s) {
        return s == 0 ;
    }

    // Method to generate a unique ID using UUID
    public static String generateID() {
        return UUID.randomUUID().toString(); // Returns a randomly generated UUID converted to string
    }

    public static boolean isValidEmail(String email) {
        String regexPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
