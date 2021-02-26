package ru.job4j.forum.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    private Collection<User> users = new ArrayList<>();
    private AuthenticationManagerBuilder auth;
    private PasswordEncoder passwordEncoder;

    public UserService(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) {
        this.auth = auth;
        this.passwordEncoder = passwordEncoder;
        Authority userAth = Authority.of(1, "USER");
        Authority adminAth = Authority.of(2, "ADMIN");
        User user = User.of(
                1 ,
                "pass",
                "user",
                true);
        user.addAuthority(userAth);
        User admin = User.of(
                2 ,
                "secret",
                "admin",
                true);
        admin.addAuthority(adminAth);
        this.users.add(user);
        this.users.add(admin);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.users.add(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
