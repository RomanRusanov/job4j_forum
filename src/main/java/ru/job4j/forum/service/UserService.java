package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    private Collection<User> users = new ArrayList<>();

    public UserService() {
        this.users.add(User.of(
                1 ,
                "pass",
                "user",
                Authority.of(1, "USER"),
                true));
        this.users.add(User.of(
                2 ,
                "secret",
                "admin",
                Authority.of(2, "ADMIN"),
                true));
    }

    public Collection<User> getAllUsers() {
        return new ArrayList<>(this.users);
    }

    public User getUserByName(String name) {
        return this.users.stream()
                .filter(user -> name.equals(user.getUsername()))
                .findFirst().orElseThrow();
    }

    public void save(User user) {
        this.users.add(user);
    }
}
