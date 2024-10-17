package za.ac.cput.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private final  User user = UserFactory.buildUser("Lesedi","Modiba","SediM@gmail.com","Password!123");


    @Test
    void create() {
        User created = userService.create(user);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void read() {
        int userId = user.getUserId();
        User read = userService.read(userId);
        assertNull(read);
        System.out.println(read);
    }

    @Test
    void update() {
        User newUser = new User.Builder().copyUser(user)
                .setEmail("Thabang@gmail.com")
                .buildUser();
        User updated = userService.update(newUser);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Disabled
    void delete() {
    }

}
