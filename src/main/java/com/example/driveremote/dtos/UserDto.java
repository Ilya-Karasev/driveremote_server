package com.example.driveremote.dtos;

import com.example.driveremote.models.Post;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    private Long id;
    private String surName;
    private String firstName;
    private String fatherName;
    private int age;
    private Post post;
    private String email;
    private String password;

    public UserDto(Long id, String surName, String firstName, String fatherName, int age, Post post, String email, String password) {
        this.id = id;
        this.surName = surName;
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.age = age;
        this.post = post;
        this.email = email;
        this.password = password;
    }

    public UserDto() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Введите фамилию!")
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }

    @NotNull(message = "Введите имя!")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @NotNull(message = "Введите возраст (от 18 и выше)!")
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @NotNull(message = "Выберите должность!")
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }

    @NotNull(message = "Введите электронную почту!")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Введите пароль!")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}