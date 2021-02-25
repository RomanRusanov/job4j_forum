package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthorityService {

    private final Collection<Authority> authority = new ArrayList<>();

    public AuthorityService() {
        this.authority.add(Authority.of(1, "ADMIN"));
        this.authority.add(Authority.of(2, "USER"));
    }

    public Collection<Authority> getAllAuthority() {
        return new ArrayList<>(this.authority);
    }

    public Authority getAuthorityByName(String name) {
        return this.authority.stream()
                .filter(authority -> name.equals(authority.getName()))
                .findFirst().get();
    }

    public Authority findByAuthority(String roleUser) {
        return this.authority.stream()
                .filter(authority -> roleUser.equals(authority.getName()))
                .findFirst().get();
    }
}
