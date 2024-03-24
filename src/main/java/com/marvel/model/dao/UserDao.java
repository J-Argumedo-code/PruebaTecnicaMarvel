package com.marvel.model.dao;

import com.marvel.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmail(String email);
}
