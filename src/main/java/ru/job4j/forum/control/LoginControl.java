package ru.job4j.forum.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {

    private UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(LoginControl.class.getName());
    private static final Marker MARKER = MarkerFactory.getMarker("Login");

    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute User user, HttpSession session) {
        LOG.info(MARKER, "User: {} try to login", user);
        User userFromStore = userService.getUserByName(user.getUsername());
        if (userFromStore != null
                && userFromStore.getUsername().equals(user.getUsername())
                && userFromStore.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", userFromStore);
            return "redirect:index";
        }
        return "login_error";
    }
}
