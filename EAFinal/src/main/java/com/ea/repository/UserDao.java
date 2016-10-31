package com.ea.repository;

import com.ea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ossama on 9/25/2016.
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(Integer id);
    @Query("from User u where u.email = :email")
    List<User> findUserByEmail(@Param("email")String email);
}
