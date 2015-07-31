package com.example.administrador.myapplication.model.persistence;

import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserRepository implements UserRepository {

    private static MemoryUserRepository singletonInstance;
    private List<User> users;

    private MemoryUserRepository() {
        super();
        users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (MemoryUserRepository.singletonInstance == null) {
            MemoryUserRepository.singletonInstance = new MemoryUserRepository();
        }

        return MemoryUserRepository.singletonInstance;
    }

    @Override
    public boolean save(User user) {
        users.add(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    public User getUser(String userid, String password) {
        return User.getUser(userid,password);
    }
}
