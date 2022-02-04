package com.example.sample.service;

// import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sample.exception.NotFoundException;
import com.example.sample.model.User;
import com.example.sample.model.Role;
import com.example.sample.model.ERole;
import com.example.sample.repository.UserRepository;
import com.example.sample.repository.RoleRepository;
import com.example.sample.logic.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User addUser(User user) {
        String codeStr = RandomStringUtils.randomAlphanumeric(10);
        user.setUserCode(codeStr);
        user.setUsername(user.getName());
        user.setLastLoginDate(UserUtil.getNowDate());
        user.setWin("0");
        user.setLose("0");
        Set<Role> roles = new HashSet<>();
        Role userRoleData = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: ユーザ権限のデータがありません"));
        roles.add(userRoleData);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        user.setUsername(user.getName());
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new NotFoundException("Error: No." + id + "に該当するデータがありません"));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsUserById(id)) {
            userRepository.deleteUserById(id);
        } else {
            throw new NotFoundException("Error: No." + id + "に該当するデータがありません");
        }
    }

    public void isValidEmailUniqe(User user) {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new NotFoundException("Error: このメールアドレスは既に登録されています");
        }
    }

    public void isValidNameUniqe(User user) {
        if (userRepository.existsUserByName(user.getName())) {
            throw new NotFoundException("Error: この名前は既に登録されています");
        }
    }

    public void isValidEmailUniqeExceptOwn(User user) {
        if (userRepository.existsUserByEmailAndIdNot(user.getEmail(), user.getId())) {
            throw new NotFoundException("Error: このメールアドレスは既に登録されています");
        }
    }

    public void isValidNameUniqeExceptOwn(User user) {
        if (userRepository.existsUserByNameAndIdNot(user.getName(), user.getId())) {
            throw new NotFoundException("Error: この名前は既に登録されています");
        }
    }

    public List<User> findUserByName(String name) {
        return userRepository.findUserByNameLike(name);
    }
}