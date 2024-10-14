package za.ac.cput.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class Helper {
    public static boolean isIntNotValid(int i) {    //checks int
        if (i < 0)
            return true;
        return false;
    }
    // Method to check if a string is null or empty
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    // Method to check if an integer is zero
    public static boolean isNull(int s) {
        return s == 0;
    }

    // Method to generate a unique ID using UUID
    public static String generateID() {
        return UUID.randomUUID().toString(); // Returns a randomly generated UUID converted to string
    }

    // Method to validate an email address using regex
    public static boolean isValidEmail(String email) {
        // Basic regex pattern for email validation
        String regexPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}

