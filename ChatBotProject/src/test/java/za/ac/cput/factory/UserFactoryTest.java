package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.UserFactory.buildUser;

public class UserFactoryTest {

    @Test
    void buildUserSuccess() {
        User user = buildUser(
                "paulmaja@gmail.com",
                "boka24",
                "boka24");

        assertNotNull(user);
        assertEquals("paulmaja@gmail.com", user.getEmail());
        assertEquals("boka24", user.getPassword());
        assertEquals("boka24", user.getConfirmPassword());
    }

    @Test
    void buildUserFailure() {
        User user = buildUser(
                null,
                null,
                null);

        assertNull(user);
    }
}
