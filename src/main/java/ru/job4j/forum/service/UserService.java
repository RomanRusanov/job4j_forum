package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    private Collection<User> users = new ArrayList<>();

    public UserService() {
        User user = User.of("user", "123");
        User admin = User.of("admin", "123");
        this.users.add(user);
        this.users.add(admin);
    }
    public Collection<User> getAllUsers() {
        return new ArrayList<>(this.users);
    }

    public User getUserByName(String name) {
        return this.users.stream()
                .filter(user -> name.equals(user.getUsername()))
                .findFirst().get();
    }

    public boolean userExistInStore(String name) {
        return this.users.stream().anyMatch(user -> name.equals(user.getUsername()));
    }

    public void save(User user) {
        this.users.add(user);
    }
}
