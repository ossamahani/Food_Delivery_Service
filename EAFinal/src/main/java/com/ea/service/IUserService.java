package com.ea.service;

import com.ea.domain.User;

import java.util.List;

/**
 * Created by Ossama on 9/25/2016.
 */
public interface IUserService {
    void save(User user);
    User getUserByUsername(String username);
    User getUserById(Integer id);
    List<User> findUserByEmail(String email);
}
