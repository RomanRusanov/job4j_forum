package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {

    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute User user) {
        return "reg";
    }
}
