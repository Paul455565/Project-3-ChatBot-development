package za.ac.cput.service;

import za.ac.cput.domain.User;

import java.util.List;

public interface IUserService extends IService<User, Integer>{
    List<User> getAll();
}
