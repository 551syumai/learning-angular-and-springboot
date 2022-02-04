package com.example.sample.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.sample.model.User;
import com.example.sample.service.UserService;
import com.example.sample.logic.UserUtil;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById (@PathVariable("id") @NotBlank Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
        // バリデーション
        UserUtil.checkRequestValidation(result);
        userService.isValidEmailUniqe(user);
        userService.isValidNameUniqe(user);

        // 登録
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") @NotBlank Long id, @Valid @RequestBody User user, BindingResult result) {
        // バリデーション
        UserUtil.checkRequestValidation(result);
        userService.isValidEmailUniqeExceptOwn(user);
        userService.isValidNameUniqeExceptOwn(user);

        // 更新
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") @NotBlank Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<User>> getUserByName (@PathVariable("name") String name) {
        List<User> users = userService.findUserByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}