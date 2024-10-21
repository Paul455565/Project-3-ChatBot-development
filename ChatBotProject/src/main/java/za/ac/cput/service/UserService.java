package za.ac.cput.service;

import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        // Ensure password hashing if you plan to secure it
        return repository.save(user);
    }

    @Override
    public User read(Integer userID) {
        return repository.findById(userID).orElse(null);
    }

    @Override
    public User update(User user) {
        // Ensure the user exists before updating
        if (repository.existsById(user.getUserId())) {
            return repository.save(user);
        }
        return null; // or throw an exception
    }

    @Override
    public boolean delete(Integer userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        }
        return false; // or throw an exception
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public boolean authenticateUser(String email, String password) {
        User user = repository.findByEmail(email);
        // Ensure the user is not null before checking password
        return user != null && user.getPassword().equals(password);
    }
}
