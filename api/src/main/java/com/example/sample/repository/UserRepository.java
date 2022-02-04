package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.example.sample.model.User;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    void deleteUserById(Long id);
    
    Optional<User> findUserById(Long id);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByName(String name);

    Boolean existsUserByEmailAndIdNot(String email,Long id);

    Boolean existsUserByNameAndIdNot(String username, Long id);

    Boolean existsUserById(Long id);

    List<User> findUserByNameLike(String name);
    
}