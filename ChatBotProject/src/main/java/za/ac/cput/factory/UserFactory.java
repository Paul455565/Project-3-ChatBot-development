package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class UserFactory {
    public static User buildUser(String email, String password, String confirmPassword){
        if(Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)
                || Helper.isNullOrEmpty(confirmPassword))
            return null;

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new User.Builder()
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(confirmPassword)
                .buildUser();
    }
}
