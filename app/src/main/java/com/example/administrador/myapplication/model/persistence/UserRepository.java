package com.example.administrador.myapplication.model.persistence;


import com.example.administrador.myapplication.model.entities.User;

import java.util.List;

public interface UserRepository {

    public abstract boolean save(User user);
    public abstract List<User> getAll();
    public abstract void delete(User user);
    public abstract User getUser(String userid, String password);
}