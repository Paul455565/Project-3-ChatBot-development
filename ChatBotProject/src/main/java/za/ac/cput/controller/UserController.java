package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping("/read/{userId}")
    public User read(@PathVariable Integer userId) {
        return service.read(userId);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/delete/{userId}")
    public boolean delete(@PathVariable Integer userId) {
        return service.delete(userId);
    }

    @GetMapping("/allUsers")
    public List<User> getAll() {
        return service.getAll();
    }
}