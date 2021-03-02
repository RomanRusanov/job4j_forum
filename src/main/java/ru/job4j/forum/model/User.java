package ru.job4j.forum.model;

import java.util.Objects;

public class User {

    private String password;
    private String username;

    public static User of(String username,
                          String password) {
        User user = new User();
        user.password = password;
        user.username = username;
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return password.equals(user.password) && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    @Override
    public String toString() {
        return "User{"
                + "password='" + password + '\''
                + ", username='" + username + '\''
                + '}';
    }
}