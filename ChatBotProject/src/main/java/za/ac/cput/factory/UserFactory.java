package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class UserFactory {
    public static User buildUser(String name,String lastName,String email, String password ){
        if(Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)
            || Helper.isNullOrEmpty(name)
                || Helper.isNullOrEmpty(lastName))
            return null;

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new User.Builder().setName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .buildUser();
    }
}
