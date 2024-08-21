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
        return repository.save(user);
    }

    @Override
    public User read(Integer userID) {
        return repository.findById(userID).orElse(null);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(Integer userID) {
        if (repository.existsById(userID)) {
            repository.deleteById(userID);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}

