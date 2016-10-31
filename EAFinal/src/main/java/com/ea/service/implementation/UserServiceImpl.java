package com.ea.service.implementation;

import com.ea.domain.User;
import com.ea.repository.UserDao;
import com.ea.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ossama Hani on 9/25/2016.
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getUserById(Integer id){
        return userDao.findById(id);
    }

    @Override
    public List<User> findUserByEmail(String email){
        return userDao.findUserByEmail(email);
    }
}
