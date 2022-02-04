package com.example.sample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Email;

@Entity
// @Table(    name = "user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotBlank(message="名前は入力必須です")
    @Size(min=3, max=20, message="名前は{min}文字から{max}文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="名前は半角英数で入力してください")
    private String name;

    private String username;

    @NotBlank(message="メールアドレスは入力必須です")
    @Size(min=10, max=255, message="メールアドレスは{min}文字から{max}文字で入力してください")
    @Email(message="メールアドレス形式で入力してください")
    private String email;

    @NotBlank(message="パスワードは入力必須です")
    @Size(min=3, max=20, message="パスワードは{min}文字から{max}文字で入力してください")
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="パスワードは半角英数で入力してください")
    private String pass;

    private String win;
    
    private String lose;
    
    private String lastLoginDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", 
                joinColumns = @JoinColumn(name = "user_id"), 
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    @Column(nullable = false, updatable = false)
    private String userCode;

    public User() {}

    public User(String name, String username, String pass, String email, String win, String lose, String lastLoginDate, String userCode) {
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.win = win;
        this.lose = lose;
        this.lastLoginDate = lastLoginDate;
        this.userCode = userCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserame() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", win='" + win + '\'' +
                ", lose='" + lose + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                '}';
    }
}